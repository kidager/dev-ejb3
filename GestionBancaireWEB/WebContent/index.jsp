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
			<h1><a href="ListAllAccounts">Bank Account Management</a></h1>
		</div>
		<div id="menu">
			<ul>
        <li><a href="AddClient"><span class="icon-user-plus"></span>
          Add new client
        </a></li>
        <li><a href="DownloadAsExcelFile"><span class="icon-excel"></span>
          Download as Excel file
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
						<td style="width:25%;">CIN</td>
						<td style="width:25%;">First Name</td>
						<td style="width:25%;">Last Name</td>
						<td style="width:25%;">Address</td>
					</tr>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${not empty requestScope.allClients}">
							<c:forEach items="${requestScope.allClients}" var="ba">
								<tr style="cursor: pointer;" onclick="window.location='ShowClient?cin=${ba.cin}'">
									<td><c:out value="${ba.cin}" /></td>
									<td><c:out value="${ba.firstName}" /></td>
									<td><c:out value="${ba.lastName}" /></td>
									<td><c:out value="${ba.address}" /></td>
								</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr>
								<td colspan="4" style="text-align:center">No clients  yet, or page called directly</td>
							</tr>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>