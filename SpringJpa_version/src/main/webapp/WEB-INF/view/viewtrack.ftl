<!DOCTYPE html>
<html>
	<head>
		<title>test</title>
	</head>
	<body>
		<h3><#if track.trackId??>${track.trackId}</#if></h3>
		<h3><#if track.name??>${track.name}</#if></h3>
		<h3><#if track.albumId??>${track.albumId}</#if></h3>
		<h3><#if track.mediaTypeId??>${track.mediaTypeId}</#if></h3>
		<td><#if t.genre??><#if t.genre.name??>${t.genre.name}</#if></#if></td>
		<h3><#if track.composer??>${track.composer}</#if></h3>
		<h3><#if track.milliseconds??>${track.milliseconds}</#if></h3>
		<h3><#if track.bytes??>${track.bytes}</#if></h3>
		<h3><#if track.unitPrice??>${track.unitPrice}</#if></h3>
	</body>
</html>