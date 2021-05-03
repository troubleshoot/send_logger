<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Info</title>

<link rel="stylesheet" type="text/css" href="/css/styles.css">
<link rel="stylesheet" type="text/css" href="/css/InfoStyle.css">
</head>
<body>
	<div id = "wrapper">
	<div class = "tableBar">
		<h1>${ climb.climb }</h1>
		<div>
			<h2 class = "nav"><a href="/climbs">Back To DashBoard</a> | <a href="/users/logout">Logout</a></h2> 
		</div>
	</div>
	<hr/>
	<h3>Created By: ${ climb.user.firstName } ${ climb.user.lastName }</h3>
	<h3>Contact Creator: ${ climb.user.email }</h3>
	
	<hr/>
	
	<h3>Users Who Have Liked This Climb:</h3>
	
	<table>
		<tr>
			<th><h4>First Name:</h4></th>
			<th><h4>Last Name:</h4></th>
			<th><h4>Email:</h4></th>
		</tr>
		<c:forEach items="${ climb.getUsers() }" var = "user">
			<tr>
				<td><h4>${ user.firstName }</h4></td>
				<td><h4>${ user.lastName }</h4></td>
				<td><h4>${ user.email }</h4></td>
			</tr>
		</c:forEach>
	</table>
	
	<c:if test = "${ isUser }">
		<form action = "/climbs/edit/${ climb.id }" method = "get">
			<input type = "submit" value = "Edit">
		</form>
	</c:if>
	</div>
	
</body>
</html>