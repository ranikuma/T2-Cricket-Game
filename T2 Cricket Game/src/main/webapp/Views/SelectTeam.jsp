<%@page import="javax.swing.plaf.basic.BasicComboBoxUI.ItemHandler"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="org.junit.runner.Request"%>
<%@page
	import="org.apache.taglibs.standard.tag.common.core.ForEachSupport"%>
<%@ page import="studyeasy.entity.Team"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cricket App</title>
</head>
<body>
	<%!String path = null;
	String name = null;
	String flag = null;
	List<Team> teams = null;
	List<Team> activePlayers = null;%>
	<table border="1">
		<tr>
			<th>Select Teams
			<th>
				<%
				String myObjectId = (String) request.getAttribute("myObjectId");
				teams = (List<Team>) request.getSession().getAttribute(myObjectId);
				System.out.println(teams.size());
				String myObjectId3 = (String) request.getAttribute("myObjectId3");
				activePlayers = (List<Team>) request.getSession().getAttribute(myObjectId3);
				if ((activePlayers != null) && (activePlayers.size() == 2)) {
					System.out.println("activeUsers = " + activePlayers.size());
					for (Team team : teams) {
						out.println("<tr><td>" + "<img src='" + team.getFlag() + "'>" + "</td><td>'" + team.getName() + "'</td></tr>");
					}
					if (activePlayers.size() == 2) {
						out.println("<tr><td><form action='GameTime' method='GET'>" + "<input type='hidden' name='myObjectId3' value='"
						+ myObjectId3 + "'>" + "<input type='submit' value='START GAME'></form><td></tr>");
					}
				} else
					for (Team team : teams) {
						out.println("<tr><td>" + "<form action='OtherTeamSelect' method='GET'>"
						+ "<input type='hidden' name='myObjectId3' value='" + myObjectId3 + "'>"
						+ "<input type='hidden' name='myObjectId' value='" + myObjectId + "'>"
						+ "<input type='hidden' name='name' value='" + team.getName() + "'>"
						+ "<input type='hidden' name='flag' value='" + team.getFlag() + "'>" + "<input type='image' src='"
						+ team.getFlag() + "'>" + "</form> " + team.getName() + "</td></tr>");
					}

				//		teams.clear();
				%>
			
		</tr>
	</table>
</body>
</html>