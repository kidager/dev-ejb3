<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="Content-Type" content="text/html" />
    <title>Bank Account management</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <link rel="icon" type="image/png" href="images/icon.png" />
    <style>
      table, th, td {
        border: 1px solid #000;
      }
      table {
        border-collapse: collapse;
        margin: 20px auto;
        width:80%;
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
    </style>
  </head>
<body>
	<div id="page">
		<div class="title">
			<h1>Bank Account Management</h1>
		</div>
		<div id="menu">
			<ul>
				<li><a href="ListAllAccounts"><span class="icon-bank"></span>
          Main Page
        </a></li>
        <li><a href="AddBankAccount?cin=${client.cin}"><span class="icon-plus"></span>
          Add new account
        </a></li>
        <li><a href="EditClient?cin=${client.cin}"><span class="icon-pencil"></span>
          Edit client
        </a></li>
			</ul>
			<ul class="right">
        <li><a href="DeleteClient?cin=${client.cin}"><span class="icon-delete"></span>
          Delete account
        </a></li>
      </ul>
		</div>
		<div id="content">
		  <c:if test="${not empty requestScope.msg}">
		    <div style="width:100%;text-align:center;">
		      <c:out value="${requestScope.msg}" />
		    </div>
      </c:if>
			<table>
				<thead>
					<tr>
						<td style="width:30%;">RIB</td>
						<td style="width:45%;">Balance</td>
						<td style="width:15%;">Edit</td>
						<td style="width:10%;">Delete</td>
					</tr>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${not empty requestScope.accountList}">
							<c:forEach items="${requestScope.accountList}" var="account">
								<tr style="cursor: pointer;" onclick="">
									<td><c:out value="${account.accountRib}" /></td>
									<td><c:out value="${account.balance}" /> TND</td>
									<td style="text-align: center;">
									  <a href="EditBankAccount?rib=${account.accountRib}"><span class="icon-minus"></span></a>
									  &nbsp;&nbsp;&nbsp;
									  <a href="EditBankAccount?rib=${account.accountRib}"><span class="icon-plus"></span></a>
									</td>
                  <td style="text-align: center;">
                    <a href="DeleteBankAccount?rib=${account.accountRib}"><span class="icon-delete"></span></a>
                  </td>
								</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr>
								<td colspan="4" style="text-align:center">No accounts yet!</td>
							</tr>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>