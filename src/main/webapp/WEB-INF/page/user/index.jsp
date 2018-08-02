<!doctype html>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core"%>
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

    <!-- SEO: If your mobile URL is different from the desktop URL, add a canonical link to the desktop page https://developers.google.com/webmasters/smartphone-sites/feature-phones -->
    <!--
    <link rel="canonical" href="http://www.example.com/">
    -->

    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:regular,bold,italic,thin,light,bolditalic,black,medium&amp;lang=en">
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
                <span>${user.name}</span>
            </div>
        </header>
        <nav class="demo-navigation mdl-navigation mdl-color--blue-grey-800">
            <a class="mdl-navigation__link" href="personal.mvc"><i class="mdl-color-text--blue-grey-400 material-icons" role="presentation">home</i>个人信息</a>
            <a class="mdl-navigation__link" href="schedule.mvc"><i class="mdl-color-text--blue-grey-400 material-icons" role="presentation">inbox</i>课程安排</a>
            <a class="mdl-navigation__link" href="attendence.mvc"><i class="mdl-color-text--blue-grey-400 material-icons" role="presentation">delete</i>考勤情况</a>
            <div class="mdl-layout-spacer"></div>
            <a class="mdl-navigation__link" href=""><i class="mdl-color-text--blue-grey-400 material-icons" role="presentation">help_outline</i><span class="visuallyhidden">Help</span></a>
        </nav>
    </div>
    <main class="mdl-layout__content mdl-color--grey-100" >
        <div>
            <div class="mdl-card mdl-shadow--2dp" style="margin-left: 20px; margin-top: 20px; width: 47%; float: left">
                <div class="mdl-card__title" style="background-color: #ef9a9a;">
                    <h2 class="mdl-card__title-text" >欢迎使用考勤系统</h2>
                </div>
                <div class="mdl-card__supporting-text">
                    欢迎使用考勤系统，今天是2018-4-23
                </div>
            </div>
            <div class="mdl-card mdl-shadow--2dp" style="margin-left: 20px; margin-top: 20px; width: 47%; float: left">
                <div class="mdl-card__title" style="background-color: #ef9a9a;">
                    <h2 class="mdl-card__title-text" >系统公告</h2>
                </div>
                <div class="mdl-card__supporting-text">
                    <table>
                        <thead>
                        <tr>
                            <td width="70%">标题</td>
                            <td width="15%">发布时间</td>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>关于领取本学期教材费用结算单的通知</td>
                            <td>2017-04-14</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>

            <div class="mdl-card mdl-shadow--2dp" style="margin-left: 20px; margin-top: 20px; width: 47%; float: left">
                <div class="mdl-card__title" style="background-color: #ef9a9a;">
                    <h2 class="mdl-card__title-text" >文件下载</h2>
                </div>
                <div class="mdl-card__supporting-text">
                    <table>
                        <thead>
                        <tr>
                            <td width="70%">文档标题</td>
                            <td width="15%">发布时间</td>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>量化评教-学生操作手册.pdf</td>
                            <td>2017-04-14</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="mdl-card mdl-shadow--2dp" style="margin-left: 20px; margin-top: 20px; width: 47%; float: left">
                <div class="mdl-card__title" style="background-color: #ef9a9a;">
                    <h2 class="mdl-card__title-text" >未读消息</h2>
                </div>
                <div class="mdl-card__supporting-text">
                    <table>
                        <thead>
                        <tr>
                            <td width="30%">主题</td>
                            <td width="30%">发送人</td>
                            <td width="30%">发送时间</td>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>量化评教-学生操作手册.pdf</td>
                            <td>2017-04-14</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </main>
</div>
<script src="${pageContext.request.contextPath}/style/mdl/material.min.js"></script>
</body>
</html>
