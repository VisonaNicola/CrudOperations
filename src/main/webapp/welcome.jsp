<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form method="get" action="gettracks.jsp">
		<input type="submit" name="submit1" value="Ottieni la lista delle canzoni">
	</form><br>
	<form method="get" action="inserttrack.jsp">
		<input type="submit" name="submit2" value="Inserisci una nuova canzone">
	</form><br>
	<form method="get" action="updatetrack.jsp">
		<input type="submit" name="submit3" value="Modifica le informazioni di una canzone">
	</form><br>
	<form method="get" action="deletetrack.jsp">
		<input type="submit" name="submit4" value="Elimina una canzone">
	</form><br>
</body>
</html>