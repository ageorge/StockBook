<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@include file="header.jsp" %>
<link href="<c:url value="/resources/css/header.css"/>" rel="stylesheet"/>
<title>Experts</title>
</head>
<body>
<%@include file="navBar.jsp" %>

<div align="center">
        <h1>Experts List</h1>
       
        <table border="1" class="table table-striped">
 			<tr>
 			 	<th>UserName</th>
	            <th>Name</th>
	            <th>SSN</th>
	            <th>Action</th>
 			</tr>
            <c:forEach var="user" items="${expertsList}">
            <c:if test = "${user.role == 'Expert'}">
            <c:if test = "${user.account_status == 'ACTIVE'}">
                <tr>
                	<td>${user.user_name}</td>
                    <td>${user.name}</td>
                    <td>${user.ssn}</td>
                    <td><a href="deleteUser?userid=${user.userid}">Delete</a>
                          <a href="viewPosts?userid=${user.userid}">View Posts</a></td>
                </tr>
                </c:if>
                </c:if>
            </c:forEach>
        </table>
    </div>
</body>
</html>