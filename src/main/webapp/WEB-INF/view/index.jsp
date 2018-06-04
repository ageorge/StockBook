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
        <li class="active"><a href="#">HOME</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li><a onclick="login()"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
        <li><a onclick="register()"><span class="glyphicon glyphicon-plus"></span> Register</a></li>
        <li><a onclick="admin()"><span class="glyphicon glyphicon-log-in"></span> Admin</a></li>
      </ul>
    </div>
  </div>
</nav>
<script type="text/javascript">
function login() {
	$('#myModalLogin').modal('show');
}
function register() {
	$('#myModalReg').modal('show');
}
function admin() {
	$('#myModalAdmin').modal('show');
}
function showmsg() {
	var msg = document.getElementById("mssg").value;
	if(msg != "") {
		$('#myModalmsg').modal('show');
	}
}
</script>

<input type="text" id="mssg" value="${mssg}" hidden="true"/>

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

<div class="modal fade" id="myModalAdmin" role="dialog">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h4 class="modal-title">ADMIN LOGIN</h4>
        </div>
        <div class="modal-body">
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
	     </form:form>
        </div>
        <div class="modal-footer">
       		<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
     	</div>
      </div>
    </div>
  </div>

<div class="modal fade" id="myModalLogin" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <h4 class="modal-title">LOGIN</h4>
        </div>
        <div class="modal-body">
	        <form:form action="login" method="POST" modelAttribute="user">
		        <table class="table">
			        <tr>
				        <td><label for="user_name"><b>Username</b></label></td>
				        <td colspan="1"><form:input path="user_name"/></td>
			        </tr>
			        <tr>
				        <td> <label for="password"><b>Password</b></label></td>
				        <td><form:input type="password" path="password"/></td>
			        </tr>
			        <tr>
				        <td colspan="2" align="center"> <button type="submit" class="btn btn-default">Login</button></td>
			        </tr>
		        </table>
		     </form:form>
        </div>
        <div class="modal-footer">
       		<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
     	</div>
      </div>
    </div>
  </div>
  
  <div class="modal fade" id="myModalReg" role="dialog">
		<div class="modal-dialog">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title"><b>REGISTER</b></h4>
				</div>
				<div class="modal-body">
					<form:form action="register" method="POST" modelAttribute="user">
						<table class="table">
							<tr>
								<td colspan="2"><p>Please fill in this form to create an account.</p></td>
							</tr>
							<tr>
								<td>User Name</td>
								<td><form:input path="user_name"/></td>
							</tr>
							<tr>
								<td>Name</td>
								<td><form:input path="name"/></td>
							</tr>
							<tr>
								<td><label for="psw">Password</label></td>
								<td><form:input type="password" path="password"/></td>
							</tr>
							<tr>
								<td><label for="ssn">SSN</label></td>
								<td><form:input path="ssn"/></td>
							</tr>
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
								<td colspan="2" align="center"><button type="submit" class="btn btn-default">Sign Up</button></td>
								<td></td>
							</tr>
						</table>
					</form:form>
				</div>
				<div class="modal-footer">
       				 <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
     			</div>
			</div>
		</div>
	</div>
	
<div id="myCarousel" class="carousel slide" data-ride="carousel">
    <!-- Indicators -->
    <ol class="carousel-indicators">
      <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
      <li data-target="#myCarousel" data-slide-to="1"></li>
      <li data-target="#myCarousel" data-slide-to="2"></li>
      <li data-target="#myCarousel" data-slide-to="3"></li>
    </ol>

    <!-- Wrapper for slides -->
    <div class="carousel-inner" role="listbox">
      <div class="item active">
        <img src="resources/images/scroll1.jpg" alt="Soccer" width="1200" height="345">
      </div>

      <div class="item">
        <img src="resources/images/scroll2.jpg" alt="UB" width="1200" height="345">     
      </div>
    
      <div class="item">
        <img src="resources/images/scroll3.jpg" alt="Soccer" width="1200" height="345">
      </div>
      
      <div class="item">
        <img src="resources/images/scroll4.png" alt="Soccer" width="1200" max-height="345">
      </div>
    </div>

    <!-- Left and right controls -->
    <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
      <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
      <span class="sr-only">Previous</span>
    </a>
    <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
      <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
      <span class="sr-only">Next</span>
    </a>
</div>

</body>
</html>