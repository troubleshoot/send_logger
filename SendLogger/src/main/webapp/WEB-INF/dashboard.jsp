<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>DashBoard</title>

<link rel="stylesheet" type="text/css" href="/css/styles.css">

</head>
<body>
	<div id = "wrapper">
	<div class = "tableBar">
		<div class = "titleClimb">
			<h1>Logged Sends</h1>
		</div>
		<h1><a href="/users/logout">Logout</a></h1>
	</div>
	<table>
		<tr>
			<th class = "nameColumn"><h3>Name: </h3></th>
			<th class = "nameColumn"><h3>Grade: </h3></th>
			<th class = "nameColumn"><h3>Location: </h3></th>
			<th class = "creatorColumn"><h3>Sender:</h3></th>
			<th class = "likesColumn"><h3>Likes: </h3></th>
			<th class = "actionsColumn"><h3>Actions: </h3></th>
		</tr>
		
		<c:forEach items="${ climbs }" var = "climb">
			<tr>
				<td><h3><a href="/climbs/${ climb.id }">${ climb.climb }</a></h3></td>
				<td><h4>${ climb.grade }</h4></td>
				<td><h4>${ climb.location }</h4></td>
				<td><h4>${ climb.user.firstName }</h4></td>
				<td><h4>${ climb.getUsers().size() }</h4></td>
				<td>
					<c:if test = "${ !climb.getUsers().contains(userObj) }">
						<form action = "/climbs/like/${ climb.id }" method = "POST">
							<input type = "submit" value = "Like">
						</form>
					</c:if>
					<c:if test = "${ climb.getUsers().contains(userObj) }">
						<form action = "/climbs/unlike/${ climb.id }" method = "POST">
							<input type = "submit" value = "Un-Like">
						</form>
					</c:if>
				</td>
			</tr>
		</c:forEach>
		
	</table>
	
	<form action = "/climbs/new" method = "get">
		<input type = "submit" value = "Create An Climb">
	</form>
	</div>
	

</body>
</html>