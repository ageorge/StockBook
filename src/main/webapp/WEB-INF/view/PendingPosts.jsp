<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@include file="header.jsp" %>
<link href="<c:url value="/resources/css/header.css"/>" rel="stylesheet"/>
<title>Traders</title>
</head>
<body>
<%@include file="navBar.jsp" %>
<div align="center">
       <div align="center">
        <h2>Pending Posts List</h2>
       
        <table border="1" class="table table-striped">
            <tr>
	            <th>UserID</th>
	            <th>PostName</th>
	            <th>Message</th>
	           <th>Attachment</th>
	           <th>Post Status</th>
	            <th>Action</th>
 			</tr>
            <c:forEach var="post" items="${listPost}">
            <c:if test = "${post.post_status == 'HOLD'}">
                <tr>
                    <td>${post.user.userid}</td>
                    <td>${post.postid}</td>
                    <td>${post.message}</td>
                    <td>${post.filename}</td>
                    <td>${post.post_status}</td>
                    <td><a href="approvePost?postid=${post.postid}">Approve</a>
                             <a href="cancelPost?postid=${post.postid}">Cancel</a></td>
                </tr>
                </c:if>
            </c:forEach>
        </table>
    </div>
 </div>
</body>
</html>