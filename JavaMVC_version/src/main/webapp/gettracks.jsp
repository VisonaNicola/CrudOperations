<%@page import="org.example.Bean.Track"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<jsp:useBean id="tracks" scope="session" class="org.example.Bean.Tracks"></jsp:useBean>
	<%
		if(tracks==null || tracks.getTracks().size()==0)//if the list of tracks is empty get it with a servlet
			response.sendRedirect("/CrudOperations-0.0.1-SNAPSHOT/ServletSelect");
	%>
	<form method="get" action="welcome.jsp">
		<input type="submit" name="submit" value="Torna alla home">
	</form><br>
	<table>
		<tr><td>id</td><td>Track Name</td><td>Album Title</td><td>Genre</td><td>Composer</td></tr>
		<% 
			for(Track t: tracks.getTracks()){%>
				<tr><td><%=t.getTrackid()%></td><td><%=t.getName() %></td><td><%=t.getAlbum() %></td><td><%=t.getGenre() %></td><td><%=t.getComposer() %></td></tr>
			<%}
		%>
	</table>
	
</body>
</html>