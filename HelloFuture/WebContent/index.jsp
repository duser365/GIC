<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome</title>
</head>
<body>
	<h1>Welcome to the future!</h1>
	<form action="futureServlet" method="post" enctype="multipart/form-data">
		Upload photo for facial authentication:
		<br/><br/>
		<input type="file" name="inputPhoto" /> 
		<br/><br/>
		<input type="submit" value="Upload" />
	</form>
</body>
</html>