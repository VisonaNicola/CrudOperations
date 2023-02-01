<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
	<body>
		<form method="GET" action="/springboot/track">
			<input type="submit" name="submit" value="Home">
		</form>
		<form method="post" action="updateTrack">
		<select name="trackid" required>
		  	<#list tracks as t>
		  		<option value=${t.trackId}>${t.name}</option>
		  	</#list>
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