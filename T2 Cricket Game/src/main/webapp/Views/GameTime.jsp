<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="org.junit.runner.Request"%>
<%@page
	import="org.apache.taglibs.standard.tag.common.core.ForEachSupport"%>
<%@ page import="studyeasy.entity.Team"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>GameTime</title>
</head>
<body>

	<%!String myObjectId;
	List<Team> teams;
	boolean isGameOver;
	int ball;%>

	<%
	myObjectId = (String) request.getAttribute("myObjectId");
	System.out.println("UUID " + myObjectId);
	teams = (List<Team>) request.getSession().getAttribute(myObjectId);
	isGameOver = (boolean) (request.getAttribute("isGameOver"));
	System.out.println("isGameOver = " + isGameOver);
	%>
	<table>
		<tr>
			<th><%=teams.get(0).getName() + "(" + teams.get(0).getPosition() + ")"%>
			</th>
		</tr>
		<tr>
			<td><%=teams.get(0).getScore() + "/" + teams.get(0).getWickets()%>
			</td>
			<td><%="Overs:"%><%=teams.get(0).getOvers()%>. <%=teams.get(0).getBalls()%></td>
		</tr>
		<tr>
			<th><%=teams.get(1).getName() + "(" + teams.get(1).getPosition() + ")"%>
			</th>
		</tr>
		<tr>
			<td><%=teams.get(1).getScore() + "/" + teams.get(1).getWickets()%>
			</td>
			<td><%="Overs:"%><%=teams.get(1).getOvers()%>. <%=teams.get(1).getBalls()%></td>
		</tr>
		<tr>
			<td>
				<h1>
					<%
					if (isGameOver) {
						if (teams.get(0).getScore() > teams.get(1).getScore()) {
							out.print("Team " + teams.get(0).getName() + " Won");
						} else if (teams.get(0).getScore() == teams.get(1).getScore()) {
							out.print("Teams " + teams.get(0).getName() + " and " + teams.get(1).getName() + " draws");
						} else {
							out.print("Team " + teams.get(1).getName() + " Won");
						}
					} else {
						out.print(request.getAttribute("batScore"));
					}
					%>
				</h1>
			</td>
		</tr>
		<%
		String GameForm = "<tr><td><form action=PlayNextBall method='GET'>" + "<input type='hidden' name='myObjectId' value='"
				+ myObjectId + "'>" + "<input type='Submit' value='Play Next Ball'>" + "</form></td></tr>";
		String GameOverForm = "<tr><td><form action=/T2Game method='GET'>" + "<input type='Submit' value='Go to List'>"
				+ "</form></td></tr>";
		if (isGameOver) {
			out.println(GameOverForm);
		} else {
			out.println(GameForm);
		}
		%>
	</table>

</body>
</html>