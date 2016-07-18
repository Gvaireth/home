<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Statistics</title>
</head>

<style>
table, th, td {
	border: 1px solid black;
	border-collapse: collapse;
	padding: 3px;
}
</style>
<body>
	Statistics
	<br>
	<br> All Activities Summary
	<br>
	<br>
	<table>
		<tr>
			<td>Workouts</td>
			<td>Duration</td>
			<td>Distance km</td>
			<td>Calories</td>
		</tr>
		<tr>
			<td>${statistics.total.workoutsNo }</td>
			<td>${statistics.total.readableDuration}</td>
			<td>${statistics.total.distance}</td>
			<td>${statistics.total.calories}</td>
		</tr>
	</table>
	<br>
	<br>
	Summary by Activity
	<br>
	<br>
	<table>
		<tr>
			<td>Activity</td>
			<td>Workouts</td>
			<td>Duration</td>
			<td>Distance km</td>
			<td>Calories</td>
		</tr>
		<c:forEach items="${totalPerSport}" var="workout"
			varStatus="status">
			<tr>
				<td>${workout.sport}</td>
				<td>${workout.workoutsNo}</td>
				<td>${workout.readableDuration}</td>
				<td>${workout.distance}</td>
				<td>${workout.calories}</td>
			</tr>
		</c:forEach>
	</table>
	<br><br>
	Longest Workouts
	<br><br>
	<table>
	<tr>
	<td>Start</td>
	<td>Duration</td>
	<td>Distance km</td>
	<td>Calories</td>
	<td>Avg km/h</td>
	<td>Activity</td>
	</tr>
		<c:forEach items="${greatestDuration}" var="workout" varStatus="status">
			<tr>
				<td>${workout.readableStartTime} </td>
				<td>${workout.readableDuration} </td>
				<td>${workout.distance}</td>
				<td>${workout.calories} </td>
				<td>${workout.speedAvg} </td>
				<td>${workout.sport} </td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>