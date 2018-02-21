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
<%--
    <script src="https://code.jquery.com/jquery-3.1.1.slim.min.js"></script>
    <script src="/skin/js/datepicker.min.js"></script>
--%>

    <script src="https://code.getmdl.io/1.3.0/material.min.js"></script>
    <!-- Bootstrap core CSS --><%--
    <link rel="stylesheet" href="/skin/css/bootstrap.min.css">
    <link rel="stylesheet" href="/skin/css/datepicker.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="/skin/css/dashboard.css">--%>

    <!-- Page styles -->
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:regular,bold,italic,thin,light,bolditalic,black,medium&amp;lang=en">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://code.getmdl.io/1.3.0/material.min.css">
    <link rel="stylesheet" href="/skin/css/styles.css">
    <!-- Custom styles for this template -->
</head>

<body>

<div class="mdl-layout mdl-js-layout mdl-layout--fixed-header">

    <div class="android-header mdl-layout__header mdl-layout__header--waterfall">
        <div class="mdl-layout__header-row">
          <span class="android-title mdl-layout-title">
            대화<%--<img class="android-logo-image" src="images/android-logo.png">--%>
          </span>
            <!-- Add spacer, to align navigation to the right in desktop -->
            <div class="android-header-spacer mdl-layout-spacer"></div>

        </div>
    </div>

    <div class="android-drawer mdl-layout__drawer">
        <span class="mdl-layout-title">
          대화
        </span>
        <nav class="mdl-navigation">
            <a class="mdl-navigation__link" href="">Dashboard</a>
            <a class="mdl-navigation__link" href="">대화 Trigger 설정</a>
            <a class="mdl-navigation__link" href="">Entity-Attribute 관리</a>
            <a class="mdl-navigation__link" href="">Entity-Attribute 업데이트</a>
        </nav>
    </div>

    <div class="android-content mdl-layout__content">