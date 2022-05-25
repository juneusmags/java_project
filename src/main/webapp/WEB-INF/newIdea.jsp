<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>   
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create</title>
<link href="https://fonts.googleapis.com/css2?family=Balsamiq+Sans&display=swap" rel="stylesheet">
<link rel="stylesheet" href="/cs/style.css">
</head>
<body>
	<h1>Hello</h1>
	
	<form:form action="/idea/create" method="post" modelAttribute="idea">
    <p>
        <form:label path="name">Content : </form:label>
        <form:errors path="name"/>
        <form:input path="name"/>
    </p>   
    <input type="submit" value="Create"/>
</form:form>    
</body>
</html>