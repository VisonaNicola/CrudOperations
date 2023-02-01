<!DOCTYPE html>
<html>
<head>
<title>test</title>
</head>
<body>
<form method="GET" action="/springboot/track">
			<input type="submit" name="submit" value="Home">
		</form>
		<form method="POST" action="addTrack" id="trackdata">
			<label for="name">Nome:</label><input type="text" name="name" id="name"><br>
			
			<select name="albumId" id="albumId">
				<#list model.albums as a>
					<option value=${a.albumId}>${a.title}</option>
				</#list>
			</select><br>	
			
			MediaTypeId<input type="number" name="mediaTypeId" id="mediaTypeId"><br>
			
			<select name="genreId" id="genreId">
				<#list model.genres as g>
					<option value=${g.genreId}>${g.name}</option>
				</#list>
			</select><br>
			
			Composer<input type="text" name="composer" id="composer"><br>
			
			Milliseconds<input type="number" name="milliseconds" id="milliseconds"><br>
			
			Bytes<input type="number" name="bytes" id="bytes"><br>
			
			UnitPrice<input type="number" step="0.01" name="unitPrice" id="unitPrice"><br>
			
			<input type="submit" name="submit" value="Invia">
		</form>
	</body>
</html>