<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>New Climbs</title>
<link rel="stylesheet" type="text/css" href="/css/newStyle.css">
<link rel="stylesheet" type="text/css" href="/css/styles.css">
</head>
<body>
	<div id = "wrapper">
	<div class = "tableBar">
		<h1>Create A New Climb</h1>
		<div>
			<h2><a href="/climbs">Back To DashBoard</a> | <a href="/users/logout">Logout</a></h2>
		</div>
	</div>
	<hr/>
	<form:form method = "POST" action = "/climbs/new" modelAttribute = "creClimb">
	<table>
		<tr>
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
			<td class = "minimize">
				<input type = "submit" value = "Log Send">
			</td>
		</tr>
	</table>
	<p><form:errors path="climb" class = "error"></form:errors></p>
	<p><form:errors path="grade" class = "error"></form:errors></p>
	<p><form:errors path="location" class = "error"></form:errors></p>
	</form:form>
		
		
	</div>
</body>
</html>