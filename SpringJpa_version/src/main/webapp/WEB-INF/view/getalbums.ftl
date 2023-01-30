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
		<tr><td>albumId</td><td>name</td></tr>
		<#list albums as a>
			<tr>
				<td><#if a.albumId??>${a.albumId}</#if></td>
				<td><#if a.title??>${a.title}</#if></td>
				
			</tr>
		</#list>
		</table>
	</body>
</html>