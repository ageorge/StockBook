<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page errorPage="error.jsp" %>
<!DOCTYPE html>
<html>
<head>
  <title>Stock Home</title>
  <meta charset="utf-8">
  <link rel="icon" href="resources/images/logo.png" type="image/png" sizes="16x16">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <link href="https://fonts.googleapis.com/css?family=Lato" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet" type="text/css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <link rel="stylesheet" href="resources/css/header.css">
  <link rel="stylesheet" href="resources/css/timeline.css">
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
      <a class="navbar-brand" href="stockHome"><img src="resources/images/logo.png"/></a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
       	<li class="active"><a href="#">HOME</a></li>
        <li><a onclick="addnewpost()"><span class="glyphicon glyphicon-plus"></span>POST</a></li>
        <li><a href="chat?userid=${user.userid }" target="_blank">CHAT</a></li>
        <li><a onclick="addreplypost()"><span class="glyphicon glyphicon-comment"></span>REPLY</a></li>
        <li><a href="#allusers">USERS</a></li>
        <c:if test="${user.role == 'Officer' }">
        <li><a href="officer">OFFICER-HOME</a></li>
        </c:if>
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
	function addnewpost() {
		$('#myModaladdpost').modal('show');
	}
	function addreplypost() {
		$('#myModalreplypost').modal('show');
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

<div class="modal fade" id="myModaladdpost" role="dialog">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h4 class="modal-title">New Post</h4>
        </div>
        <div class="modal-body">
        		<form:form action="addpost" method="post" modelAttribute="newpost" enctype="multipart/form-data">
		        <input name="userid" value = "${user.userid}" hidden="true"/>  
		        <table class="table">
		        <tr>
		        <tr>
		        <td> <label><b>Post Name</b></label></td>
		        <td><form:input path="postname"/></td>
		        </tr>
		        <tr>
		        <td> <label><b>Message</b></label></td>
		        <td><form:input path="message"/></td>
		        </tr>
		        <tr>
		        <td> <label><b>Stock</b></label></td>
		        <td>
		        <select id="stock" name="stockid">
				<option>---Select---</option>
				<c:forEach items="${stocks}" var="st">
						<option value="${st.stockid}">${st}</option>
				</c:forEach>
				</select>
				 </td>
		        </tr> 
		        <tr>
		        <td> <label><b>Attachments</b></label></td>
		        <td><input type="file" name="file"/></td>
		        </tr> 
		        <tr>
		        <td colspan="2" align="center"> <input type="submit" class="btn btn-default" value="Post"></td>
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

<div class="modal fade" id="myModalreplypost" role="dialog">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h4 class="modal-title">Reply to a Post</h4>
        </div>
        <div class="modal-body">
        		<form:form action="replypost" method="post" modelAttribute="reply">
		        <input name="userid" value = "${user.userid}" hidden="true"/>  
		        <table class="table">
		        <tr>
		        <tr>
		        <td> <label><b>Post ID</b></label></td>
		        <td><input name="postid"/></td>
		        </tr>
		        <tr>
		        <td> <label><b>Message</b></label></td>
		        <td><form:input path="message"/></td>
		        </tr>
		        <tr>
		        <td colspan="2" align="center"> <input type="submit" class="btn btn-default" value="Reply"></td>
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

<div class="container">
   <h1 class="text-center">News Feed</h1>
  <c:forEach items="${posts}" var="allpost" varStatus="index">
  <ul class="timeline">
    <li>
      <div class="timeline-badge"><i class="glyphicon glyphicon-comment"></i></div>
      <div class="timeline-panel">
        <div class="timeline-heading">
          <h4 class="timeline-title">${allpost.post.postid}: ${allpost.post.postname}</h4>
          <h5>Stock Ref: ${allpost.post.stock} </h5>
          <h6>by ${allpost.post.user.user_name} </h6>
        </div>
        <div class="timeline-body">
          <p>${allpost.post.message}</p>
        </div>
      	</div>
	      <c:if test="${not empty allpost.image}">
	      	<li class="timeline-inverted">
		      <div class="timeline-panel">
		        <div class="timeline-body">
		          <p><img src="data:image/jpeg;base64,${allpost.image}" alt="${allpost.post.filename}" height="150px" width="200px"/></p>
		        </div>
		      </div>
		      </li>
	   	 </c:if>
	   	  <c:if test="${not empty allpost.post.replies}">
	      	<li class="timeline-inverted">
		      <div class="timeline-panel">
		        <div class="timeline-body">
		        <c:forEach items="${allpost.post.replies}" var="reply">
		          <h5>Reply by: ${reply.user.userid} </h5>
         		 <h6> ${reply.message} </h6> <br>
         		 </c:forEach>
		        </div>
		      </div>
		      </li>
	   	 </c:if>
    </li>
  </ul>
  </c:forEach>
</div>

<%-- <c:if test="${not empty users }"> --%>
<div class="container" id="allusers">
	<h3 class="text-center">User List</h3>
		<table class="table table-striped">
			<tr>
			<th>User ID</th>
			<th>User Name</th>
			<th>Full Name</th>
			<th>Role</th>
			</tr>
			<c:forEach items="${users}" var="user">
				<tr>
					<td>${user.userid}</td>
					<td>${user.user_name}</td>
					<td>${user.name}</td>
					<td>${user.role}</td>
				</tr>
			</c:forEach>
		</table>
</div>
<%-- </c:if> --%>

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