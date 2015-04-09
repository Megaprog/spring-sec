<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
	<title>Welcome page</title>
	<meta charset="UTF-8"/>
</head>
<body>
	<h1>Title : ${title}</h1>	
	<h1>Message : ${message}</h1>

	<form action="/data" method="post">
		<input type="text" name="line"/>
		<input type="submit"/>

		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	</form>
</body>
</html>