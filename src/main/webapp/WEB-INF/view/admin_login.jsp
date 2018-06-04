<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page errorPage="error.jsp" %> 
<!DOCTYPE html>
<html>
<head>
  <title>Stock Center</title>
  <meta charset="utf-8">
  <link rel="icon" href="resources/images/logo.png" type="image/png" sizes="16x16">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <link href="https://fonts.googleapis.com/css?family=Lato" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet" type="text/css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <link rel="stylesheet" href="resources/css/header.css">
</head>
<body>

<c:url value="j_spring_security_check" var="securl"/> 

<div class="container">
          <h4 class="modal-title">ADMIN LOGIN</h4>
        <form:form action="login" method="POST" modelAttribute="user">
	        <table class="table">
	        <tr>
		        <td><b>User Name</b></td>
		        <td><form:input path="user_name"/></td>
	        </tr>
	        <tr>
		        <td><b>Password</b></td>
		        <td><form:input type="password" path="password"/></td>
	        </tr>
	        <tr>
	        		<td colspan="2" align="center"> <button type="submit" class="btn btn-default">Login</button></td>
	        </tr>
	        </table>
	        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	     </form:form>
    </div>

</body>
</html>