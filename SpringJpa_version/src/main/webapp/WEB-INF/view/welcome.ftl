<!DOCTYPE html>
<html>
	<head>
		<title>test</title>
	</head>
	<body>
		<form method="GET" action="/springjpa/track/getTracks">
			<input type="submit" name="submit" value="Visualizza la lista delle tracce">
		</form>
		<form method="GET" action="/springjpa/track/addTrack">
			<input type="submit" name="submit" value="Aggiungi una traccia">
		</form>
		<form method="GET" action="/springjpa/track/updateTrack">
			<input type="submit" name="submit" value="Modifica una traccia">
		</form>
		<form method="GET" action="/springjpa/track/deleteTrack">
			<input type="submit" name="submit" value="Elimina una traccia">
		</form>
	</body>
</html>