<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>RestTemplate Demo</title>
    <meta charset="utf-8"/>
</head>
<body>
<h1>New</h1>

<form:form commandName="post">
    <p>title: <form:input path="title"/> <input type="text" th:field="${title}"/></p>
    <p>content: </p>
    <p><form:textarea path="content" rows="3" cols="20"></form:textarea></p>
    <p><input type="submit" value="Submit"/> <input type="reset" value="Reset"/></p>
</form:form>
</body>
</html>