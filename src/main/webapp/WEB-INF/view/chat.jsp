<%@ page errorPage="error.jsp" %> 
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
<meta name="description" content="" />
<meta name="author" content="" />
<link rel="icon" href="resources/images/logo.png" type="image/png" sizes="16x16">
<title>My Chat</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <link href="https://fonts.googleapis.com/css?family=Lato" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet" type="text/css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <link rel="stylesheet" href="resources/css/header.css">
<script type="text/javascript">
var webSocket;
var messages = document.getElementById("messages"); 
var newConnect = '';
var ip = document.getElementById("serverIP");
window.onload = function () { 
	openSocket();;
} 
function openSocket()
{
if(webSocket !== undefined && webSocket.readyState !== WebSocket.CLOSED)
{
  writeResponse("WebSocket is already opened.");
  return; 
}
var ip = document.getElementById("serverIP").value;
webSocket = new WebSocket("ws://" + ip + ":8081/StockBook/chatserver");
webSocket.onopen = function(event)
{
var user = document.getElementById("thisUser").value; 
var image = document.getElementById("img").value;
newConnect = '<li class="media">' +
      '<div class="media-body">' +
      '<div class="media">' +
      '<a class="pull-left" href="#">' +
      '<img class="media-object img-circle " style="max-height:40px;" src="resources/images/' + image + '.png" height="50px"/>' +
      '</a>' +
'<div class="media-body" >' +
'<h5>' + user + '| User </h5>' +
'<small class="text-muted">' + new Date() + '</small>' + '</div>' +
'</div>' +
'</div>' +
'</li>';
var users = document.getElementById("connectedusers");
users.innerHTML = users.innerHTML + "<br/><li>" + newConnect + "<li>";
if(event.data === undefined) 
	return;
};
webSocket.onmessage = function(event) {
// Determine if MSSG or CONN message 
var mssg = event.data;
if (mssg.startsWith('MSSG')) {
  mssg = mssg.substring(5); // simple mssg
  writeResponse(mssg);
}
else if (mssg.startsWith('CONN')) {
mssg = mssg.substring(5); // new connection
var users = document.getElementById("connectedusers");
                        users.innerHTML = users.innerHTML +
"<br/><li>" + mssg + "<li>";
}};
webSocket.onclose = function(event) {
  writeResponse("Connection closed");
  alert("Connection closed");
};
}  // openSocket
function send()
{
var text = document.getElementById("mytext").value; var user = document.getElementById("thisUser").value; var image = document.getElementById("img").value;
if (text =="") {
  alert("no message!");
return; }
var fullmssg = 'MSSG:<li class="media">' +
'<div class="media-body">'+
'<div class="media">' +
'<a class="pull-left" href="#">' +
'<img class="media-object img-circle " src="resources/images/' +
                image + '.png" height="50px"/>' +
     '</a>' +
     '<div class="media-body" >' +
      text +
     '<br />' +
     '<small class="text-muted">' +
user +
' | ' + new Date() + '</small>' +
'<hr />' + '</div>' + '</div>' + '</div>' + '</li>';
// send to all others via the Server Endpoint
webSocket.send(fullmssg);
// send mssg to yourself
writeResponse(fullmssg.substring(5));  
}
function closeSocket() {
  webSocket.close();
}
function writeResponse(theMessage) {
var mssg = document.getElementById("messages");
var text = theMessage;
mssg.innerHTML = "<br/><li>" + text + "<li>" + mssg.innerHTML; 
document.getElementById("mytext").value = "";
}
</script>
</head>
<body style="font-family:Verdana">
<input type="hidden" id="thisUser" value="${user}"> 
<input type="hidden" id="img" value="${img}"> 
<input type="hidden" id="serverIP" value="${ip}">
<div class="container" style="align:center">
<div class="row "  style="padding-top:40px; align:center">
<h3 class="text-center" >My Chat: ${user}</h3>
<br /><br />
<div class="col-md-8">
<div class="panel panel-info">
<div class="panel-heading" style="background-color:#2d2d30; color:white;">RECENT CHAT HISTORY</div>
<div class="panel-body">
<ul id="messages" class="media-list"> <li class="media">
<div class="media-body">
<div class="media">
<a class="pull-left" href="#">
<img class="media-object img-circle " src="resources/images/${img}.png" height="50px"/>
</a>
<div class="media-body" >
Online
<br />
<small class="text-muted"> ${user} | <%= new java.util.Date() %></small>
<hr />
</div>
</div>
</div>
</li>
</ul>
</div>
<div class="panel-footer">
<div class="input-group">
<input id="mytext" type="text" class="form-control" placeholder="Enter Message" />
<span class="input-group-btn">
<button class="btn btn-info" type="button" onClick="send()">SEND</button>
</span>
</div>
</div>
</div>
</div>
<div class="col-md-4">
<div class="panel panel-primary">
<div class="panel-heading" style="background-color:#2d2d30; color:white;">ONLINE USERS
</div>
<div class="panel-body">
<ul id="connectedusers" class="media-list"> <!-- new users go here -->
</ul>
</div>
</div>
</div>
</div>
</div>
</body>
</html>