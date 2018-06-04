<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@include file="header.jsp" %>
<title>Add/Edit User</title>
</head>
<body>
<%@include file="navBar.jsp" %>
    <div align="center">
        <h1 class="text-center">Add New User</h1>
        <form:form action="saveUser" method="post" modelAttribute="user">
        
         
        <table class="table table-striped">
        
            <form:hidden path="userid"/>
            <form:hidden path="account_status"/>
            <tr>
                <td>User Name:</td>
                <td><form:input path="user_name" /></td>
            </tr>
            <tr>
                <td>Name:</td>
                <td><form:input path="name" /></td>
            </tr>
            <tr>
                <td>SSN:</td>
                <td><form:input path="ssn" /></td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><form:input path="password" type="password"/></td>
            </tr>
            <%-- <tr>
                <td>Role:</td>
                <td><form:input path="role"/></td>
            </tr> --%>
            <tr>
				<td><label for="role">Role</label></td>
				<td><form:select path="role">
					   <form:option value="Trader" label="Trader"/>
					   <form:option value="Expert" label="Expert"/>
					   <form:option value="Officer" label="Officer"/>
					   <form:option value="Staff" label="Staff"/>
					</form:select>
				</td>
			</tr>
            <tr>
                <td colspan="2" align="center"><input type="submit" value="Save"></td>
            </tr>
        </table>
        </form:form>
    </div>
</body>
</html>