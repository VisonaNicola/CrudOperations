<%@page import="org.example.Bean.Genre"%>
<%@page import="org.example.Bean.Album"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<jsp:useBean id="albums" scope="session" class="org.example.Bean.Albums"></jsp:useBean>
	<jsp:useBean id="genres" scope="session" class="org.example.Bean.Genres"></jsp:useBean>
	<%	//if the list of albums or the list of genres is empty get them with a servlet
		if(albums == null || genres == null || albums.getAlbums().size()==0 || genres.getGenres().size()==0)
			response.sendRedirect("/CrudOperations-0.0.1-SNAPSHOT/ServletInsert");
	%>
	<form method="get" action="welcome.jsp">
		<input type="submit" name="submit" value="Torna alla home">
	</form><br>
	<form method="POST" action="/CrudOperations-0.0.1-SNAPSHOT/ServletInsert">
		Track Name:<input type="text" name="name" required><br>
		Album:<select name="albumid" required>
		  	<%for(Album a: albums.getAlbums()){%>
		  		<option value="<%=a.getAlbumId() %>"><%=a.getName() %></option>
		  	<%} %>
		</select><br>
		Genre:<select name="genreid" required>
		  	<%for(Genre a: genres.getGenres()){%>
		  		<option value="<%=a.getGenreId() %>"><%=a.getName() %></option>
		  	<%} %>
		</select><br>
		Composer:<input type="text" name="composer" required><br>
		<input type="submit" name="submit" value="Invia">
	</form>
</body>
</html>