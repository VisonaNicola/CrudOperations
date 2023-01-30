<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
	<body>
		<form method="GET" action="/springjpa/track">
			<input type="submit" name="submit" value="Home">
		</form>
		<table>
		<tr><td>genreId</td><td>name</td></tr>
		<#list genres as g>
			<tr>
				<td><#if g.genreId??>${g.genreId}</#if></td>
				<td><#if g.name??>${g.name}</#if></td>
			</tr>
		</#list>
		</table>
	</body>
</html>