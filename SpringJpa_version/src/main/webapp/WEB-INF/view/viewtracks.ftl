<!DOCTYPE html>
<html>
	<head>
		<title>test</title>
	</head>
	<body>
		<form method="GET" action="/springjpa/track">
			<input type="submit" name="submit" value="Home">
		</form>
		<table>
		<tr><td>trackId</td><td>name</td><td>albumId</td><td>mediaTypeId</td><td>genreId</td><td>composer</td><td>milliseconds</td><td>bytes</td><td>unitPrice</td>
		<#list tracks as t>
			<tr>
				<td><#if t.trackId??>${t.trackId}</#if></td>
				<td><#if t.name??>${t.name}</#if></td>
				<td><#if t.album??><#if t.album.title??>${t.album.title}</#if></#if></td>
				<td><#if t.mediaTypeId??>${t.mediaTypeId}</#if></td>
				<td><#if t.genre??><#if t.genre.name??>${t.genre.name}</#if></#if></td>
				<td><#if t.composer??>${t.composer}</#if></td>
				<td><#if t.milliseconds??>${t.milliseconds}</#if></td>
				<td><#if t.bytes??>${t.bytes}</#if></td>
				<td><#if t.unitPrice??>${t.unitPrice}</#if></td>
			</tr>
		</#list>
		</table>
	</body>
</html>