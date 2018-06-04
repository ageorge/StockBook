<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
  <link href="https://fonts.googleapis.com/css?family=Lato" rel="stylesheet" type="text/css"/>
  <link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet" type="text/css"/>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <link rel="stylesheet" href="resources/css/header.css">
</head>
<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
<c:url value="/j_spring_security_logout" var="logoutUrl" />
<nav class="navbar navbar-default navbar-fixed-top navbar-offset">
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
      <ul class="nav navbar-nav" style="font-size: 13px">
      <li><a href="admin">ADMIN</a></li>
        <li><a href="tradersList">TRADERS</a></li>
        <li><a href="expertsList">EXPERTS</a></li>
        <li><a href="placementOfficersList">OFFICERS</a></li>
        <li><a href="staffList">STAFF</a></li>
         <li><a href="PendingPosts">POSTS</a></li>
        <li><a href="UserForm "><span class="glyphicon glyphicon-plus" style="font-size: 13px"></span>USER</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right" style="font-size: 13px">
        <li><a href="home"><span class="glyphicon glyphicon-log-in"></span>Logout</a></li>
      </ul>
    </div>
  </div>
</nav>
<br>
<br>
<br>