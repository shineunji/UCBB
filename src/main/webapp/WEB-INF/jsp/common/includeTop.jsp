<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fnc" uri="http://java.sun.com/jsp/jstl/functions" %>


<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>UCBB ADMIN</title>
    <script src="https://code.jquery.com/jquery-3.1.1.slim.min.js"></script>
    <script src="/skin/js/datepicker.min.js"></script>
    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="/skin/css/bootstrap.min.css">
    <link rel="stylesheet" href="/skin/css/datepicker.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="/skin/css/dashboard.css">
    <!-- Custom styles for this template -->
</head>

<body>
<header>
    <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-primary">
        <a class="navbar-brand" href="/api/buildHistories">UCBB</a>
    </nav>
</header>

<div class="container-fluid">
    <div class="row">
        <nav class="col-sm-3 col-md-2 d-none d-sm-block bg-light sidebar">
            <ul class="nav nav-pills flex-column">
                <li class="nav-item">
                    <a class="nav-link<c:if test="${param.menu == 'systemProperty'}"> active</c:if>"
                       href="<c:url value="/api/systemProperty"/>">UCBB System Property 설정<c:if
                            test="${param.menu == 'systemProperty'}"><span
                            class="sr-only">(current)</span> </c:if></a></li>
                <li class="nav-item">
                    <a class="nav-link<c:if test="${param.menu == 'buildList'}"> active</c:if>"
                       href="<c:url value="/api/buildList"/>">Build 진행 현황<c:if
                            test="${param.menu == 'systemProperty'}"><span
                            class="sr-only">(current)</span> </c:if></a></li>
                <li class="nav-item">
                    <a class="nav-link<c:if test="${param.menu == 'buildHistories'}"> active</c:if>"
                       href="<c:url value="/api/buildHistories"/>">Build 이력 조회<c:if
                            test="${param.menu == 'systemProperty'}"><span
                            class="sr-only">(current)</span> </c:if></a>
                </li>
            </ul>
        </nav>

        <main role="main" class="col-sm-9 ml-sm-auto col-md-10 pt-3">