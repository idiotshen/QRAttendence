<!doctype html>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0">
    <title>Material Design Lite</title>

    <!-- Add to homescreen for Chrome on Android -->
    <meta name="mobile-web-app-capable" content="yes">
    <link rel="icon" sizes="192x192" href="${pageContext.request.contextPath}/style/skins/images/android-desktop.png">

    <!-- Add to homescreen for Safari on iOS -->
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-title" content="Material Design Lite">
    <link rel="apple-touch-icon-precomposed" href="${pageContext.request.contextPath}/style/skins/images/ios-desktop.png">

    <!-- Tile icon for Win8 (144x144 + tile color) -->
    <meta name="msapplication-TileImage" content="${pageContext.request.contextPath}/style/skins/images/touch/ms-touch-icon-144x144-precomposed.png">
    <meta name="msapplication-TileColor" content="#3372DF">

    <link rel="shortcut icon" href="${pageContext.request.contextPath}/style/skins/images/favicon.png">

    <link href="https://cdn.bootcss.com/angular-material/1.1.9/angular-material.css" rel="stylesheet">
    <script src="https://cdn.bootcss.com/angular.js/1.6.9/angular.min.js"></script>
    <script src="https://cdn.bootcss.com/angular.js/1.6.9/angular-animate.min.js"></script>
    <script src="https://cdn.bootcss.com/angular.js/1.6.9/angular-aria.min.js"></script>
    <script src="https://cdn.bootcss.com/angular-material/1.1.9/angular-material.min.js"></script>
    <script src="${pageContext.request.contextPath}/script/angular-file-upload.js"></script>
    <script src="${pageContext.request.contextPath}/script/jquery-3.3.1.min.js"></script>

    <script src="${pageContext.request.contextPath}/script/admin/selectStudent.js"></script>
    <!-- SEO: If your mobile URL is different from the desktop URL, add a canonical link to the desktop page https://developers.google.com/webmasters/smartphone-sites/feature-phones -->
    <!--
    <link rel="canonical" href="http://www.example.com/">
    -->

    <link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css"/>

    <link rel="stylesheet"
          href="https://fonts.googleapis.com/css?family=Roboto:regular,bold,italic,thin,light,bolditalic,black,medium&amp;lang=en">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link href="https://cdn.bootcss.com/material-design-lite/1.3.0/material.cyan-light_blue.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/css/styles.css">
    <style>
        #view-source {
            position: fixed;
            display: block;
            right: 0;
            bottom: 0;
            margin-right: 40px;
            margin-bottom: 40px;
            z-index: 900;
        }
    </style>
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
                <span>管理员</span>
            </div>
        </header>
        <nav class="demo-navigation mdl-navigation mdl-color--blue-grey-800">
            <a class="mdl-navigation__link" href="adminStudent.mvc"><i
                    class="mdl-color-text--blue-grey-400 material-icons" role="presentation">inbox</i>上传学生信息</a>
            <a class="mdl-navigation__link" href="selectStudent.mvc"><i
                    class="mdl-color-text--blue-grey-400 material-icons" role="presentation">inbox</i>查询学生信息</a>
            <a class="mdl-navigation__link" href=""><i class="mdl-color-text--blue-grey-400 material-icons" role="presentation">help_outline</i><span class="visuallyhidden">Help</span></a>  </nav>
    </div>
    <main class="mdl-layout__content mdl-color--grey-100" ng-app="selectStudent">
        <div ng-controller="getStudent">
            <md-card style="margin-top: 20px;margin-left: 5%;width: 90%">
                <div style="margin-left: 3%;margin-top: 20px;">
                    <md-card-title-text>
                        <md-input-container>
                            <label>学号</label>
                            <input ng-model="student.Id">
                        </md-input-container>
                        <md-input-container>
                            <label>姓名</label>
                            <input ng-model="student.name">
                        </md-input-container>
                        <md-input-container>
                            <label>班级</label>
                            <input ng-model="student.classId">
                        </md-input-container>
                        <md-input-container>
                            <label>性别</label>
                            <input ng-model="student.sex">
                        </md-input-container>
                        <md-input-container>
                            <label>专业</label>
                            <input ng-model="student.major">
                        </md-input-container>
                        <md-input-container>
                            <label>学院</label>
                            <input ng-model="student.college">
                        </md-input-container>
                    </md-card-title-text>
                </div>
                <md-button style="width: 5%;margin-left: 3%; margin-bottom: 20px" ng-click="selectStudent()">
                    <strong>查询</strong></md-button>
            </md-card>

            <div id="attendenceDetail" style="margin-bottom: 20px">
                <table class="mdl-data-table mdl-js-data-table mdl-shadow--2dp"
                       style="margin-top: 20px;margin-left: 5%;width: 90%;" id="unattendenceList">
                    <thead>
                    <tr>
                        <th>学号</th>
                        <th>姓名</th>
                        <th>班级</th>
                        <th>专业</th>
                        <th>学院</th>
                        <th>性别</th>
                        <th>密码</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr ng-repeat="studentInformation in studentInformations">
                        <td>{{studentInformation.Id}}</td>
                        <td>{{studentInformation.name}}</td>
                        <td>{{studentInformation.classId}}</td>
                        <td>{{studentInformation.major}}</td>
                        <td>{{studentInformation.college}}</td>
                        <td>{{studentInformation.sex}}</td>
                        <td>{{studentInformation.password}}</td>
                    </tr>
                    </tbody>
                </table>

                <br>
                <span style="margin-left: 5%">共{{count}}条记录  共{{countPage}}页  当前第{{page}}页</span>
                <a href="#" onclick="firstPage();">首页</a>
                <a href="#" ng-click="nextPage();">下一页</a>
                <a href="#" ng-click="previousPage();">上一页</a>
                <a href="#" onblur="lastPage();">尾页</a>
            </div>
        </div>

    </main>
</div>
<script src="${pageContext.request.contextPath}/style/mdl/material.min.js"></script>
</body>
</html>
