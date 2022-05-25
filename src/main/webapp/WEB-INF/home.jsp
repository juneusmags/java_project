<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Dashboard</title>
<link href="https://fonts.googleapis.com/css2?family=Balsamiq+Sans&display=swap" rel="stylesheet">
<link rel="stylesheet" href="/cs/style.css">
</head>
<body>
	<h1>Welcome, ${userlog.userName}</h1>
	
	<h2> Ideas </h2>
	<table style="width:100%">
  		<tr>
    		<th>Idea</th>
    		<th>Created By : </th>
    		<th>Action</th>
  		</tr>
 		<c:forEach items = "${allIdeas}" var  = "ideas">
  		<tr>
    		<td><a href = "/ideas/${ideas.id}">${ideas.name}</a></td>
   		 	<td>${ideas.uploader.userName}</td>
    		<td><a href = "/ideas/${ideas.id}/edit">Edit</a> || <a href = "/idea/${ideas.id}/delete">Delete</a></td>
  		</tr>
  		</c:forEach>
  		
	</table>
	<br>
	<br>
	
	<a href = "/ideas/new">Create an idea</a>
	<br>
	<br>
	
	<a href = "/logout">Logout</a>
</body>
</html>