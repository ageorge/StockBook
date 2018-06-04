<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page errorPage="error.jsp" %> 
<!DOCTYPE html>
<html>
<head>
  <title>Officer Home</title>
  <meta charset="utf-8">
  <link rel="icon" href="resources/images/logo.png" type="image/png" sizes="16x16">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <link href="https://fonts.googleapis.com/css?family=Lato" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet" type="text/css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <link rel="stylesheet" href="resources/css/header.css">
</head>
<body onload="showmsg()">

<nav class="navbar navbar-default navbar-fixed-top">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
      <a class="navbar-brand" href="#"><img src="resources/images/logo.png"/></a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
       	<li class="active"><a href="stockHome">STOCK HOME</a></li>
        <li><a onclick="searchusers()"><span class="glyphicon glyphicon-search"></span>SEARCH</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="#profile"><span class="glyphicon glyphicon-user"></span>Profile</a></li>
        <li><a href="home"><span class="glyphicon glyphicon-log-in"></span>Logout</a></li>
      </ul>
    </div>
  </div>
</nav>

<input type="text" id="mssg" value="${mssg}" hidden="true"/>

<script>
	function showmsg() {
		var msg = document.getElementById("mssg").value;
		if(msg != "") {
			$('#myModalmsg').modal('show');
		}
	}
	function searchusers() {
		$('#myModalsearch').modal('show');
	}
</script>

<div class="modal fade" id="myModalmsg" role="dialog">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h4 class="modal-title">MESSAGE</h4>
        </div>
        <div class="modal-body">
        		<p>${mssg }</p>
        </div>
        <div class="modal-footer">
       		<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
     	</div>
      </div>
    </div>
</div>

 <div class="modal fade" id="myModalsearch" role="dialog">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h4 class="modal-title">Search Users</h4>
        </div>
        <div class="modal-body">
        <form action="search" method="post">
	        <table class="table">
	         <tr>
		        <td> <label><b>Stock*</b></label></td>
		        <td>
		        <select name="stock_name" required="required">
				<option>---Select---</option>
				<c:forEach items="${stocknames}" var="st">
						<option value="${st}">${st}</option>
				</c:forEach>
				</select>
				 </td>
		      </tr> 
	        <tr>
	        		<td colspan="2" align="center"> <button type="submit" class="btn btn-default">Find</button></td>
	        </tr>
	        </table>
	     </form>
        </div>
        <div class="modal-footer">
       		<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
     	</div>
      </div>
    </div>
  </div> 

<div class="container" id = "catalogue">
	<h3 class="text-center">Stock Catalogue</h3>
		<table class="table table-striped">
			<tr>
			<th>Stock ID</th>
			<th>Stock Name</th>
			<th>Stock Price</th>
			<th>Date</th>
			</tr>
			<c:forEach items="${stocks}" var="stock" varStatus="index">
				<tr>
					<td>${stock.stockid}</td>
					<td>${stock.stock_name}</td>
					<td>${stock.stock_price}</td>
					<td>${stock.stock_date}</td>
				</tr>
			</c:forEach>
		</table>
</div>

<c:if test="${not empty userlist }">
<div class="container" id="user_res">
	<h3 class="text-center">Search Result</h3>
		<table class="table table-striped">
			<tr>
			<th>User ID</th>
			<th>User Name</th>
			<th>Role</th>
			</tr>
			<c:forEach items="${userlist}" var="user" varStatus="index">
				<tr>
					<td>${user.userid}</td>
					<td>${user.user_name}</td>
					<td>${user.role}</td>
				</tr>
			</c:forEach>
		</table>
</div>
</c:if>

<div class="container bg-1" id="profile">
	<h3 class="text-center">My Account</h3>
	<table class="table">
		<tr>
			<td>User ID</td>
			<td>${user.userid}</td>
		</tr>
		<tr>
			<td>User Name</td>
			<td>${user.user_name}</td>
		</tr>
		<tr>
			<td>Full Name</td>
			<td>${user.name}</td>
		</tr>
		<tr>
			<td>Role</td>
			<td>${user.role}</td>
		</tr>
		<tr>
			<td>Account Status</td>
			<td>${user.account_status}</td>
		</tr>
	</table>
</div>

</body>
</html>