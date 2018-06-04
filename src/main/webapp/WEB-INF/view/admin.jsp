<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page errorPage="error.jsp" %> 

<!DOCTYPE html>
<html>
<head>
<%@include file="header.jsp" %>
  <title>Administration</title>
</head>

<body>


<%@include file="navBar.jsp" %>
<div align="center">
        <h2>Pending User List</h2>
       
        <table border="1" class="table table-striped">
 			<tr>
	            <th>Name</th>
	            <th>SSN</th>
	            <th>Role</th>
	            <th>Account Status</th>
	            <th>Action</th>
 			</tr>
            <c:forEach var="user" items="${listUser}">
            <c:if test = "${user.account_status == 'HOLD'}">
                <tr>
                    <td>${user.name}</td>
                    <td>${user.ssn}</td>
                    <td>${user.role}</td>
                    <td>${user.account_status}</td>
                    <td><a href="approveUser?userid=${user.userid}">Approve</a>
                             <a href="cancelUser?userid=${user.userid}">Cancel</a></td>
                </tr>
                </c:if>
            </c:forEach>
        </table>
    </div>
</body>
</html>