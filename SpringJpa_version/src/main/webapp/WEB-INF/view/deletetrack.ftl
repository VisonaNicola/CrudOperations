<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
	<body>
	<form method="GET" action="/springjpa/track/">
		<input type="submit" name="submit" value="Home">
	</form>
		<form method="post" action="deleteTrack">
			<select name="trackid" required>
			  	<#list tracks as t>
			  		<option value=${t.trackId}>${t.name}</option>
			  	</#list>
		  	</select>
		  	<input type="submit" name="submit" value="Invia">
		</form>
	</body>
</html>