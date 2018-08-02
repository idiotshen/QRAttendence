var studentSchedule = angular.module('schedule', ['ngMaterial', 'ngAnimate']);
studentSchedule.controller('getSchedule', function ($scope, $http, $mdDialog, $compile) {
        var table = $("table");
        table.css("margin-top", "20px");

        $http({
            methond: 'GET',
            url: '/java_ee_design/scheduleDetail.mvc'
        }).then(
            function successCallback(response) {
                var scheduleJSON = response.data;

                scheduleJSON.forEach(function (item, index, array) {
                    var td = $("tr").eq(item['dtime']).find("td").eq(item['dday']);
                    td.html("<span>" + scheduleJSON[index].classroomId + "</span>" + "<br>"
                        + "<span>" + scheduleJSON[index].courseName + "</span>" + "<br>"
                        + "<span>" + scheduleJSON[index].teacherName + "</span>");
                    td.css("background", "#CFD8DC");
                });
            },
            function errorCallback(reason) {
            }
        );
    }
);

function addLoadEvent(func) {
    var oldOnLoad = window.onload;

    if (typeof window.onload !== 'function') {
        window.onload = func;
    } else {
        window.onload = function () {
            oldOnLoad();
            func();
        }
    }
}