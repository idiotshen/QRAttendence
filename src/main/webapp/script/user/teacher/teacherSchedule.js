var teacherSchedule = angular.module('schedule', ['ngMaterial', 'ngAnimate']);
teacherSchedule.controller('getSchedule', function ($scope, $http, $mdDialog, $compile) {
        $http({
            methond: 'GET',
            url: '/java_ee_design/scheduleDetail.mvc'
        }).then(
            function successCallback(response) {
                var scheduleJSON = response.data;

                scheduleJSON.forEach(function (item, index, array) {
                    var td = $("tr").eq(item['dtime']).find("td").eq(item['dday']);
                    var html = "<md-button ng-click='showAlert($event)'>" + item.classroomId + "<br>" + item.courseName + "<br>" + item.teacherName + "</md-button>";
                    var template = angular.element(html);
                    template.attr("dday",item['dday']);
                    template.attr("dtime", item['dtime']);
                    template.attr("lessonid", item['lessonId']);
                    var button = $compile(template)($scope);
                    td.append(button);
                    td.css("background", "#CFD8DC");
                });
            },
            function errorCallback(reason) {
            }
        );

        $scope.showAlert = function (ev) {
            var button = ev.target;
            $scope.dday = button.getAttribute("dday");
            $scope.dtime = button.getAttribute("dtime");
            $scope.lessonId = button.getAttribute("lessonId")
            $mdDialog.show({
                locals:{dday:$scope.dday, dtime:$scope.dtime, week: $scope.week, lessonId:$scope.lessonId},
                controller: DialogController,
                templateUrl: '/java_ee_design/templete.html',
                parent: angular.element(document.body),
                targetEvent: ev,
                clickOutsideToClose: true,
                fullscreen: $scope.customFullscreen // Only for -xs, -sm breakpoints.
            })
        };

        function DialogController($scope, $mdDialog, dtime, dday, week, lessonId) {
            $scope.dtime = dtime;
            $scope.dday = dday;
            $scope.week = week;
            $scope.lessonId = lessonId;
            $scope.hide = function () {
                $mdDialog.hide();
            };

            $scope.cancel = function () {
                $mdDialog.cancel();
            };

            $scope.answer = function (answer) {
                $mdDialog.hide(answer);
            };
        }

        $scope.weekItems = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16];
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