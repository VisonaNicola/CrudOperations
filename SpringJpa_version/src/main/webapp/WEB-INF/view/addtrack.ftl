<!DOCTYPE html>
<html>
	<head>
		<title>test</title>
	</head>
	<body>
		<form method="GET" action="/springjpa/track">
			<input type="submit" name="submit" value="Home">
		</form>
		<form method="POST" action="addTrack" id="trackdata">
			Nome:<input type="text" name="name" id="name"><br>
			AlbumId<input type="number" name="albumId" id="albumId"><br>
			MediaTypeId<input type="number" name="mediaTypeId" id="mediaTypeId"><br>
			GenreId<input type="number" name="genreId" id="genreId"><br>
			Composer<input type="text" name="composer" id="composer"><br>
			Milliseconds<input type="number" name="milliseconds" id="milliseconds"><br>
			Bytes<input type="number" name="bytes" id="bytes"><br>
			UnitPrice<input type="number" step="0.01" name="unitPrice" id="unitPrice"><br>
			<input type="submit" name="submit" value="Invia">
		</form>
	</body>
</html>