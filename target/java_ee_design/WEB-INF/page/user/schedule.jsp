<!doctype html>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0">
    <meta name="userType" content="${userType}">
    <title>Material Design Lite</title>

    <!-- Add to homescreen for Chrome on Android -->
    <meta name="mobile-web-app-capable" content="yes">
    <link rel="icon" sizes="192x192" href="../../../style/skins/images/android-desktop.png">

    <!-- Add to homescreen for Safari on iOS -->
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-title" content="Material Design Lite">
    <link rel="apple-touch-icon-precomposed" href="${pageContext.request.contextPath}/style/skins/images/ios-desktop.png">

    <!-- Tile icon for Win8 (144x144 + tile color) -->
    <meta name="msapplication-TileImage" content="${pageContext.request.contextPath}/style/skins/images/touch/ms-touch-icon-144x144-precomposed.png">
    <meta name="msapplication-TileColor" content="#3372DF">

    <link rel="shortcut icon" href="${pageContext.request.contextPath}/style/skins/images/favicon.png">

    <!-- SEO: If your mobile URL is different from the desktop URL, add a canonical link to the desktop page https://developers.google.com/webmasters/smartphone-sites/feature-phones -->
    <!--
    <link rel="canonical" href="http://www.example.com/">
    -->


    <link rel="stylesheet"
          href="https://fonts.googleapis.com/css?family=Roboto:regular,bold,italic,thin,light,bolditalic,black,medium&amp;lang=en">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/mdl/material.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/css/styles.css">
    <link href="https://cdn.bootcss.com/angular-material/1.1.9/angular-material.css" rel="stylesheet">
    <script src="https://cdn.bootcss.com/angular.js/1.6.9/angular.min.js"></script>
    <script src="https://cdn.bootcss.com/angular.js/1.6.9/angular-animate.min.js"></script>
    <script src="https://cdn.bootcss.com/angular.js/1.6.9/angular-aria.min.js"></script>
    <script src="https://cdn.bootcss.com/angular-material/1.1.9/angular-material.min.js"></script>
    <style>
        .mdl-data-table td {
            text-align: center;
        }

        .mdl-data-table th {
            text-align: center;
        }
    </style>

    <script>

    </script>
</head>
<body>
<div class="demo-layout mdl-layout mdl-js-layout mdl-layout--fixed-drawer mdl-layout--fixed-header">
    <header class="demo-header mdl-layout__header mdl-color--grey-100 mdl-color-text--grey-600">
        <div class="mdl-layout__header-row">
            <span class="mdl-layout-title">上海电力学院考勤系统</span>
            <div class="mdl-layout-spacer" style="display: inline">
            </div>
            <div class="mdl-textfield mdl-js-textfield mdl-textfield--expandable">
                <label class="mdl-button mdl-js-button mdl-button--icon" for="search">
                    <i class="material-icons">search</i>
                </label>
                <div class="mdl-textfield__expandable-holder">
                    <input class="mdl-textfield__input" type="text" id="search">
                    <label class="mdl-textfield__label" for="search">Enter your query...</label>
                </div>
            </div>
            <button class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--icon" id="hdrbtn">
                <i class="material-icons">more_vert</i>
            </button>
            <ul class="mdl-menu mdl-js-menu mdl-js-ripple-effect mdl-menu--bottom-right" for="hdrbtn">
                <li class="mdl-menu__item">About</li>
                <li class="mdl-menu__item">Contact</li>
                <li class="mdl-menu__item">Legal information</li>
            </ul>
        </div>
    </header>
    <div class="demo-drawer mdl-layout__drawer mdl-color--blue-grey-900 mdl-color-text--blue-grey-50">
        <header class="demo-drawer-header">
            <img src="${pageContext.request.contextPath}/style/skins/images/user.jpg" class="demo-avatar">
            <div class="demo-avatar-dropdown">
                <span>${user.name}</span>
            </div>
        </header>
        <nav class="demo-navigation mdl-navigation mdl-color--blue-grey-800">
            <a class="mdl-navigation__link" href="personal.mvc"><i class="mdl-color-text--blue-grey-400 material-icons"
                                                                   role="presentation">home</i>个人信息</a>
            <a class="mdl-navigation__link" href="schedule.mvc"><i class="mdl-color-text--blue-grey-400 material-icons"
                                                                   role="presentation">inbox</i>课程安排</a>
            <a class="mdl-navigation__link" href="attendence.mvc"><i
                    class="mdl-color-text--blue-grey-400 material-icons" role="presentation">delete</i>考勤情况</a>
            <div class="mdl-layout-spacer"></div>
            <a class="mdl-navigation__link" href=""><i class="mdl-color-text--blue-grey-400 material-icons"
                                                       role="presentation">help_outline</i><span class="visuallyhidden">Help</span></a>
        </nav>
    </div>
    <main class="mdl-layout__content mdl-color--grey-100" ng-app="schedule" ng-controller="getSchedule">
        <c:if test="${userType eq 1}">
        <md-input-container style="margin-top: 20px;margin-left: 5%;">
            <md-select placeholder="周数" ng-model="week">
                <md-option ng-repeat="weekItem in weekItems" ng-value="weekItem">第{{weekItem}}周</md-option>
            </md-select>
        </md-input-container>
        </c:if>
        <table class="mdl-data-table mdl-js-data-table mdl-shadow--2dp" style="margin-left: 5%;width: 90%;">
            <thead>
            <tr>
                <th>节次</th>
                <th>星期一</th>
                <th>星期二</th>
                <th>星期三</th>
                <th>星期四</th>
                <th>星期五</th>
                <th>星期六</th>
                <th>星期日</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>第一节</td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
            </tr>
            <tr>
                <td>第二节</td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
            </tr>
            <tr>
                <td>第三节</td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
            </tr>
            <tr>
                <td>第四节</td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
            </tr>
            </tbody>
        </table>
</div>
</main>

</div>
<script src="${pageContext.request.contextPath}/style/mdl/material.min.js"></script>
<script src="${pageContext.request.contextPath}/script/jquery-3.3.1.min.js"></script>
<c:if test="${userType eq 1}">
    <script src="${pageContext.request.contextPath}/script/user/teacher/teacherSchedule.js"></script>
</c:if>
<c:if test="${userType eq 0}">
    <script src="${pageContext.request.contextPath}/script/user/student/studentSchedule.js"></script>
</c:if>
</body>
</html>
