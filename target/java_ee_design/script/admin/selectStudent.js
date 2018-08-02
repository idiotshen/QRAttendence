var selectStudent = angular.module('selectStudent', ['ngMaterial', 'ngAnimate']);
selectStudent.controller("getStudent", function ($scope, $http) {
    $scope.offsetNumber = 0;
    $scope.limit = 10;

    $scope.student = {};

    $scope.nextPage = function () {
        if ($scope.page === $scope.countPage) {
            alert("已到最后一页");
            return;
        }

        $http({
            methond: 'get',
            url: '/java_ee_design/selectStudentInformation.mvc',
            params: {
                offset: $scope.offsetNumber + $scope.limit,
                limit: $scope.limit,
                Id: $scope.student["Id"],
                name: $scope.student["name"],
                classId: $scope.student["classId"],
                sex: $scope.student["sex"],
                major: $scope.student["major"],
                college: $scope.student["college"]
            }
        }).then(
            function successCallback(response) {
                $scope.studentInformations = response.data['studentInformations'];
                $scope.count = response.data['count'];
                $scope.countPage = Math.floor($scope.count / 10) + 1;
                $scope.page += 1;
                $scope.offsetNumber += $scope.limit;
            },
            function errorCallback(reason) {
            }
        );
    };

    $scope.previousPage = function () {
        if ($scope.page === 1) {
            alert("已到首页");
            return;
        }

        $http({
            methond: 'get',
            url: '/java_ee_design/selectStudentInformation.mvc',
            params: {
                offset: $scope.offsetNumber - $scope.limit,
                limit: $scope.limit,
                Id: $scope.student["Id"],
                name: $scope.student["name"],
                classId: $scope.student["classId"],
                sex: $scope.student["sex"],
                major: $scope.student["major"],
                college: $scope.student["college"]
            }
        }).then(
            function successCallback(response) {
                $scope.studentInformations = response.data['studentInformations'];
                $scope.count = response.data['count'];
                $scope.countPage = Math.floor($scope.count / 10) + 1;
                $scope.page -= 1;
                $scope.offsetNumber -= $scope.limit;
            },
            function errorCallback(reason) {
            }
        );
    };

    $scope.selectStudent = function () {
        $scope.offsetNumber = 0;
        $scope.limit = 10;

        $http({
            methond: 'get',
            url: '/java_ee_design/selectStudentInformation.mvc',
            params: {
                offset: $scope.offsetNumber,
                limit: $scope.limit,
                Id: typeof  $scope.student.hasOwnProperty("Id")? '': $scope.student["Id"],
                name: typeof $scope.student.hasOwnProperty("name")=== undefined?'':$scope.student["name"],
                classId: typeof $scope.student["classId"]=== undefined? '':$scope.student["classId"],
                sex: typeof $scope.student["sex"] === undefined ? '': $scope.student["sex"],
                major:typeof  $scope.student["major"] === undefined ? '' :$scope.student["major"],
                college: typeof $scope.student["college"] === undefined ? '' : $scope.student["college"]
            }
        }).then(
            function successCallback(response) {
                $scope.studentInformations = response.data['studentInformations'];
                $scope.count = response.data['count'];
                $scope.countPage = Math.floor($scope.count / 10) + 1;
                $scope.page = 1;
            },
            function errorCallback(reason) {
            }
        );
    }
});