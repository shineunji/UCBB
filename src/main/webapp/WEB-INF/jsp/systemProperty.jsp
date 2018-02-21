<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>RestTemplate Demo</title>
    <meta charset="utf-8"/>
</head>
<body>
<h1>SystemProperty</h1>

<form action="#" th:action="@{/posts}" th:object="${lockEntity}" method="post">
    <p>Model Name: <input type="text" th:field="*{modelName}" /></p>
    <p>Request At: <input type="text" th:field="*{requestAt}" /></p>
    <p><input type="submit" value="Submit" /> <input type="reset" value="Reset" /></p>
</form>
</body>
</html>