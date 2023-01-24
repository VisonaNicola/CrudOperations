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
			response.sendRedirect("/CrudOperations-0.0.1-SNAPSHOT/ServletUpdate");
	%>
	<form method="get" action="welcome.jsp">
		<input type="submit" name="submit" value="Torna alla home">
	</form><br>
	<form method="post" action="/CrudOperations-0.0.1-SNAPSHOT/ServletUpdate">
	<select name="trackid" required>
		  	<%for(Track a: tracks.getTracks()){%>
		  		<option value="<%=a.getTrackid() %>"><%=a.getName() %></option>
		  	<%} %>
		  </select>
		Update:	<select name="choice" required>
			  		<option value="1">Name</option>
			  		<option value="2">AlbumId</option>
			  		<option value="3">GenreId</option>
			  		<option value="4">Composer</option>
		  		</select>
		  		<input type="text" name="value" required>
		  		<input type="submit" name="submit" value="Invia">  			
	</form>
</body>
</html>