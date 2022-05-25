<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login/Register</title>
<link href="https://fonts.googleapis.com/css2?family=Balsamiq+Sans&display=swap" rel="stylesheet">
<link rel="stylesheet" href="/cs/style.css">
</head>
<body>
<h1>Login Registration</h1>
<div>
<form:form action="/register" method="post" modelAttribute="newUser">
        <div class="form-group">
            <label>Name:</label>
            <form:input path="userName" class="form-control" />
            <form:errors path="userName" class="text-danger" />
        </div>
        <div class="form-group">
            <label>Email:</label>
            <form:input path="email" class="form-control" />
            <form:errors path="email" class="text-danger" />
        </div>
        <div class="form-group">
            <label>Password:</label>
            <form:password path="password" class="form-control" />
            <form:errors path="password" class="text-danger" />
        </div>
        <div class="form-group">
            <label>Confirm Password:</label>
            <form:password path="confirm" class="form-control" />
            <form:errors path="confirm" class="text-danger" />
        </div>
        <input type="submit" value="Register" class="btn btn-primary" />
    </form:form>
    
    <form:form action="/login" method="post" modelAttribute="newLogin">
        <div class="form-group">
            <label>Email:</label>
            <form:input path="email" class="form-control" />
            <form:errors path="email" class="text-danger" />
        </div>
        <div class="form-group">
            <label>Password:</label>
            <form:password path="password" class="form-control" />
            <form:errors path="password" class="text-danger" />
        </div>
        <input type="submit" value="Login" class="btn btn-success" />
    </form:form>
</div>
</body>
</html>