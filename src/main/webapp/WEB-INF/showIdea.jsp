<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Idea</title>
<link href="https://fonts.googleapis.com/css2?family=Balsamiq+Sans&display=swap" rel="stylesheet">
<link rel="stylesheet" href="/cs/style.css">
</head>
<body>
	<h1>${ideaObj.name}</h1>
	<h2>Created by: ${ideaObj.uploader.userName}</h2>
	
	<br>
	<br>
	
	<a href= "/ideas/${ideaObj.id}/edit">Edit</a>
</body>
</html>