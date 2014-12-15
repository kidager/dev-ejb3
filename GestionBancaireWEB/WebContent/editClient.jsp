<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8" />
  <meta http-equiv="Content-Type" content="text/html" />
  <title>Edit client <c:out value="${client.cin}" /> </title>
  <link rel="stylesheet" type="text/css" href="css/style.css">
  <link rel="icon" type="image/png" href="images/credit-card--plus.png" />
  <style type="text/css">
    input[readonly] {
      background-color: #777777;
      color: #FFFFFF;
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
          Main Page
        </a></li>
        <li><a href="ShowClient?cin=${client.cin}"><span class="icon-user"></span>
          Go back to client
        </a></li>
      </ul>
    </div>
    <div id="content">
			<form action="EditClient" method="POST">
				<table>
          <c:if test="${not empty requestScope.error}">
            <tr>
              <td colspan="3" style="color:#F00"><c:out value="${requestScope.error}" /></td>
            </tr>
          </c:if>
          <tr valign="top">
            <td>Client CIN</td>
            <td>:</td>
            <td>
              <input type="text" size="8" name="cin" value="${client.cin}" readonly="readonly" required="required">
              <div id="cinError" style="color:#F00;font-style:italic"></div>
            </td>
          </tr>
					<tr valign="top">
            <td>Client first name</td>
            <td>:</td>
            <td><input type="text" name="firstName" value="${client.firstName}" required="required"></td>
          </tr>
          <tr valign="top">
            <td>Client last name</td>
            <td>:</td>
            <td><input type="text" name="lastName" value="${client.lastName}" required="required"></td>
          </tr>
          <tr valign="top">
            <td>Client address</td>
            <td>:</td>
            <td><textarea name="address" required="required" rows="2"><c:out value="${client.address}"/></textarea></td>
          </tr>
					<tr valign="top">
						<td colspan="3"><input type="submit" value="Edit Client"></td>
					</tr>
				</table>
			</form>
		</div>
  </div>
  <script src="jquery-ui/external/jquery/jquery.js"></script>
  <script src="jquery-ui/jquery-ui.js"></script>
  <script type="text/javascript">
  $(function() {
	  $("input[name='cin']").change(function(){
		  $(this).val("${client.firstName}");
		});
  });
  </script>
</body>
</html>