<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Post</title>
</head>
<body>
<div>
        <table border="1">
 			<tr>
	            <th>UserID</th>
	            <th>PostName</th>
	            <th>Message</th>
	            <!--  <th>Attachment</th>-->
	            <th>Action</th>
 			</tr>
            <c:forEach var="post" items="${listPost}">
            <c:if test = "${post.post_status == 'ACTIVE'}">
                <tr>
                    <td>${post.userid}</td>
                    <td>${post.postid}</td>
                    <td>${post.message}</td>
                    <!--  <td>${post.getAttachment}</td>-->
                    <td><a href="approvePost?postid=${post.userid}">Approve</a>
                             <a href="cancelPost?postid=${post.userid}">Cancel</a></td>
                </tr>
                </c:if>
            </c:forEach>
        </table>
    </div>
</body>
</html>