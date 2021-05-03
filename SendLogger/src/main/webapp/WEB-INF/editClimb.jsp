<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit</title>

<link rel="stylesheet" type="text/css" href="/css/styles.css">
<link rel="stylesheet" type="text/css" href="/css/editStyle.css">
</head>
<body>
	<div id = "wrapper">
	<div class = "tableBar">
		<h1>Edit: ${ climb.getClimb() }</h1>
		<div>
			<h3><a href="/climbs/${ climb.getId() }">Back</a> | <a href="/climbs">Back To DashBoard</a> | <a href="/users/logout">Logout</a></h3>
		</div>
	</div>
	<hr/>
	
	<h2>Creator: ${ climb.getUser().lastName }</h2>
	<h2>Number Of Likes: ${ climb.getUsers().size() }</h2>
	
	<hr/>
	
	
	<table>
		<tr>
			<form:form method = "POST" action = "/climbs/edit/${ climb.id }" modelAttribute = "climb">
				<td valign="middle" class = "word">Climb:</td>
				<td>
					<form:label path="climb" class = "word">Climb: </form:label>
				</td>
				<td>
					<form:input path="climb" id="inputClimb"></form:input>
				</td>
				<td>
					<form:label path="grade" class = "word">Grade: </form:label>
				</td>
				<td>
					<form:input path="grade" id="inputGrade"></form:input>
				</td>
				<td>
					<form:label path="location" class = "word">Location: </form:label>
				</td>
				<td>
					<form:input path="location" id="inputLocation"></form:input>
				</td>
				<c:if test = "errors">
					<td><form:errors path="climb" class = "error"></form:errors></td>
				</c:if>
				<td class = "minimize">
					<input type = "submit" value = "Edit Climb">
				</td>
			</form:form>
			<td class = "minimize">
				<form action = "/climbs/delete/${ climb.id }" method = "POST">
					<input type = "hidden" name = "_method" value = "delete">
					<input type = "submit" value = "Delete">
				</form>
			</td>
		</tr>
	</table>
	</div>
</body>
</html>