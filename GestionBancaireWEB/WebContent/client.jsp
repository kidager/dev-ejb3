<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta http-equiv="Content-Type" content="text/html" />
<title>Bank Account management</title>
<link href="jquery-ui/jquery-ui.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="icon" type="image/png" href="images/icon.png" />
<style>
table, th, td {
	border: 1px solid #000;
}

table {
	border-collapse: collapse;
	margin: 20px auto;
	width: 80%;
	padding: 0px;
}

tr {
	padding: 0px;
	margin: 0px;
}

td {
	text-align: left;
	padding: 5px;
	margin: 0px;
}

thead tr td {
	background-color: #87BAF5;
	text-align: center;
	text-transform: uppercase;
	font-weight: bold;
}

form#addAmountForm {
	padding: 10px !important;
	margin: 0px !important;
	width: 80% !important;
}
</style>
</head>
<body>
	<div id="page">
		<div class="title">
			<h1><a href="ListAllAccounts">Bank Account Management</a></h1>
		</div>
		<div id="menu">
			<ul>
				<li><a href="ListAllAccounts"><span class="icon-bank"></span>
						Main Page </a></li>
				<li><a href="AddBankAccount?cin=${client.cin}"><span
						class="icon-plus"></span> Add new account </a></li>
				<li><a href="EditClient?cin=${client.cin}"><span
						class="icon-pencil"></span> Edit client </a></li>
			</ul>
			<ul class="right">
				<li><a id="deleteClient" href="DeleteClient?cin=${client.cin}"><span
						class="icon-user-minus"></span> Delete client </a></li>
			</ul>
		</div>
		<div id="content">
			<c:if test="${not empty requestScope.msg}">
				<div style="width: 100%; text-align: center;">
					<c:out value="${requestScope.msg}" />
				</div>
			</c:if>
			<table>
       <tbody>
         <tr style="background-color:#87BAF5">
           <td style="text-align:center"><strong>CIN : <c:out value="${client.cin}"/></strong></td>
           <td style="text-align:center"><strong><c:out value="${client.firstName}"/> <c:out value="${client.lastName}"/></strong></td>
           <td style="text-align:center"><strong><c:out value="${client.address}"/></strong></td>
         </tr>
       </tbody>
			</table>
			<table>
				<thead>
					<tr>
						<td style="width: 30%;">RIB</td>
						<td style="width: 45%;">Balance</td>
						<td style="width: 15%;">Edit</td>
						<td style="width: 10%;">Delete</td>
					</tr>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${not empty requestScope.accountList}">
							<c:forEach items="${requestScope.accountList}" var="account">
								<tr>
									<td><c:out value="${account.accountRib}" /></td>
									<td><c:out value="${account.balance}" /> TND</td>
									<td style="text-align: center;"><a id="dialog-debit"
										data-rib="${account.accountRib}"
										href="#EditBankAccount?rib=${account.accountRib}"> <span
											class="icon-minus"></span>
									</a> &nbsp;&nbsp;&nbsp; <a id="dialog-credit"
										data-rib="${account.accountRib}"
										href="#EditBankAccount?rib=${account.accountRib}"> <span
											class="icon-plus"></span>
									</a></td>
									<td style="text-align: center;"><a
										href="DeleteBankAccount?rib=${account.accountRib}"> <span
											class="icon-delete"></span>
									</a></td>
								</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr>
								<td colspan="4" style="text-align: center">No accounts yet!</td>
							</tr>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
		</div>
	</div>

	<script src="jquery-ui/external/jquery/jquery.js"></script>
	<script src="jquery-ui/jquery-ui.js"></script>
	<script>
		$(function() {
			// Init $.alert addon
			$.extend({
				alert : function(message, title) {
					$("<div></div>").dialog({
						buttons : {
							"OK" : function() {
								$(this).dialog("close");
							}
						},
						close : function(event, ui) {
							$(this).remove();
						},
						resizable : false,
						title : title,
						modal : true
					}).text(message);
				}
			});

			$
					.extend({
						amountDialog : function(title, formUrl, ribId) {
							$(
									'<div><form id="amountForm">'
											+ '<label for="amountValue">Amount : </label>'
											+ '<input type="number" id="amountValue" name="amount" required="required">'
											+ '</form></div>')
									.dialog(
											{
												modal : true,
												resizable : false,
												width : 500,
												title : title,
												show : {
													effect : "puff"
												},
												buttons : [
														{
															text : "OK",
															click : function() {
																amount = $(
																		"#amountValue")
																		.val();
																if (amount == ""
																		|| amount <= 0) {
																	$(this)
																			.effect(
																					"shake");
																	$
																			.alert("Please enter a positive amount");
																	return;
																}

																if (ribId === undefined
																		|| ribId == null
																		|| ribId == "") {
																	$(this)
																			.effect(
																					"shake");
																	$
																			.alert("RIB is not valid !");
																	return;
																}

																$
																		.ajax(
																				{
																					type : "POST",
																					url : formUrl,
																					data : {
																						amount : amount,
																						rib : ribId
																					},
																					dataType : "json"
																				})
																		.done(
																				function(
																						data) {
																					$(
																							"a[id^='dialog-'][data-rib='"
																									+ ribId
																									+ "']")
																							.parent()
																							.parent()
																							.find(
																									"td:nth-child(2)")
																							.text(
																									data.balance
																											+ " TND");
																					$
																							.alert(
																									data.msg,
																									"Account "
																											+ ribId
																											+ " edited");
																				});

																$(this)
																		.dialog(
																				"close");
															}
														},
														{
															text : "Cancel",
															click : function() {
																$(this)
																		.effect(
																				"explode");
																$(this)
																		.dialog(
																				"close");
															}
														} ],
												close : function(event, ui) {
													$(this).remove();
												},
												open : function(event, ui) {
													$("form#amountForm")
															.submit(
																	function(
																			event) {
																		event
																				.preventDefault();
																	});
												}
											})
						}
					});

			$("a#dialog-credit").click(
					function(event) {
						event.preventDefault();
						$.amountDialog("Credit amount", "CreditBankAccount", $(
								this).data("rib"));
					});

			$("a#dialog-debit").click(
					function(event) {
						event.preventDefault();
						$.amountDialog("Debit amount", "DebitBankAccount", $(
								this).data("rib"));
					});

			$("a#deleteClient")
					.click(
							function(event) {
								event.preventDefault();
								$("<div></div>")
										.dialog(
												{
													buttons : {
														"Yes" : function() {
															window.location = $(
																	"a#deleteClient")
																	.first()
																	.attr(
																			"href");
															$(this).dialog(
																	"close");
														},
														"No" : function() {
															$(this).dialog(
																	"close");
														}
													},
													close : function(event, ui) {
														$(this).remove();
													},
													resizable : false,
													title : "Delete",
													modal : true
												})
										.text(
												"Do you really want to delete this client and all his accounts ?");
							});
		});
	</script>

</body>
</html>