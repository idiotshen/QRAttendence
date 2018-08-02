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
    <meta name="msapplication-TileImage" content="${pageContext.request.contextPath}/skins/images/touch/ms-touch-icon-144x144-precomposed.png">
    <meta name="msapplication-TileColor" content="#3372DF">

    <link rel="shortcut icon" href="${pageContext.request.contextPath}/style/skins/images/favicon.png">

    <link href="https://cdn.bootcss.com/angular-material/1.1.9/angular-material.css" rel="stylesheet">
    <script src="https://cdn.bootcss.com/angular.js/1.6.9/angular.min.js"></script>
    <script src="https://cdn.bootcss.com/angular.js/1.6.9/angular-animate.min.js"></script>
    <script src="https://cdn.bootcss.com/angular.js/1.6.9/angular-aria.min.js"></script>
    <script src="https://cdn.bootcss.com/angular-material/1.1.9/angular-material.min.js"></script>
    <script src="${pageContext.request.contextPath}/script/angular-file-upload.js"></script>
    <script src="${pageContext.request.contextPath}/script/jquery-3.3.1.min.js"></script>

    <script src="${pageContext.request.contextPath}/script/admin/adminStudent.js"></script>
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
            <a class="mdl-navigation__link" href=""><i class="mdl-color-text--blue-grey-400 material-icons" role="presentation">help_outline</i><span class="visuallyhidden">Help</span></a></nav>
    </div>
    <main class="mdl-layout__content mdl-color--grey-100" ng-app="material" >
        <div style="margin-top: 20px;margin-left: 5%;width: 90%;" ng-controller="AppController" nv-file-drop=""
             uploader="uploader" filters="queueLimit, customFilter">
            <div class="container">
                <div class="row">
                    <div class="col-md-3">
                        <h3>选择文件</h3>
                        <div ng-show="uploader.isHTML5">
                            <!-- 3. nv-file-over uploader="link" over-class="className" -->
                            <div class="well my-drop-zone" nv-file-over="" uploader="uploader">
                                上传文件需为xlsx<br>
                                格式为: 姓名  学号  班级  专业  所在学院
                            </div>

                        </div>

                        <!-- Example: nv-file-select="" uploader="{Object}" options="{Object}" filters="{String}" -->
                        Multiple
                        <input type="file" nv-file-select="" uploader="uploader" multiple/><br/>

                        Single
                        <input type="file" nv-file-select="" uploader="uploader"/>
                    </div>

                    <div class="col-md-9" style="margin-bottom: 40px">

                        <h3>上传列表</h3>
                        <p>待上传文件数 {{ uploader.queue.length }}</p>

                        <table class="table">
                            <thead>
                            <tr>
                                <th width="50%">Name</th>
                                <th ng-show="uploader.isHTML5">Size</th>
                                <th ng-show="uploader.isHTML5">Progress</th>
                                <th>Status</th>
                                <th>Actions</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr ng-repeat="item in uploader.queue">
                                <td><strong>{{ item.file.name }}</strong></td>
                                <td ng-show="uploader.isHTML5" nowrap>{{ item.file.size/1024/1024|number:2 }} MB</td>
                                <td ng-show="uploader.isHTML5">
                                    <div class="progress" style="margin-bottom: 0;">
                                        <div class="progress-bar" role="progressbar"
                                             ng-style="{ 'width': item.progress + '%' }"></div>
                                    </div>
                                </td>
                                <td class="text-center">
                                    <span ng-show="item.isSuccess"><i class="glyphicon glyphicon-ok"></i></span>
                                    <span ng-show="item.isCancel"><i class="glyphicon glyphicon-ban-circle"></i></span>
                                    <span ng-show="item.isError"><i class="glyphicon glyphicon-remove"></i></span>
                                </td>
                                <td nowrap>
                                    <button type="button" class="btn btn-success btn-xs" ng-click="item.upload()"
                                            ng-disabled="item.isReady || item.isUploading || item.isSuccess">
                                        <span class="glyphicon glyphicon-upload"></span> Upload
                                    </button>
                                    <button type="button" class="btn btn-warning btn-xs" ng-click="item.cancel()"
                                            ng-disabled="!item.isUploading">
                                        <span class="glyphicon glyphicon-ban-circle"></span> Cancel
                                    </button>
                                    <button type="button" class="btn btn-danger btn-xs" ng-click="item.remove()">
                                        <span class="glyphicon glyphicon-trash"></span> Remove
                                    </button>
                                </td>
                            </tr>
                            </tbody>
                        </table>

                        <div>
                            <div>
                                Queue progress:
                                <div class="progress" style="">
                                    <div class="progress-bar" role="progressbar"
                                         ng-style="{ 'width': uploader.progress + '%' }"></div>
                                </div>
                            </div>
                            <button type="button" class="btn btn-success btn-s" ng-click="uploader.uploadAll()"
                                    ng-disabled="!uploader.getNotUploadedItems().length">
                                <span class="glyphicon glyphicon-upload"></span> Upload all
                            </button>
                            <button type="button" class="btn btn-warning btn-s" ng-click="uploader.cancelAll()"
                                    ng-disabled="!uploader.isUploading">
                                <span class="glyphicon glyphicon-ban-circle"></span> Cancel all
                            </button>
                            <button type="button" class="btn btn-danger btn-s" ng-click="uploader.clearQueue()"
                                    ng-disabled="!uploader.queue.length">
                                <span class="glyphicon glyphicon-trash"></span> Remove all
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </main>
</div>
<script src="${pageContext.request.contextPath}/style/mdl/material.min.js"></script>
</body>
</html>
