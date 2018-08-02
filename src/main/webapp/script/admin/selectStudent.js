var selectStudent = angular.module('selectStudent', ['ngMaterial', 'ngAnimate']);
selectStudent.controller("getStudent", function ($scope, $http) {
    $scope.student = {};

    /*
        实现点击下一页功能
     */
        $scope.nextPage = function () {
            if ($scope.page === $scope.countPage) {
                alert("已到最后一页");
                return;
            }

            $http({
                methond: 'get',
                url: '/java_ee_design/selectStudentInformation.mvc',
                // 查询所需参数
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
                    // 将作用域的studentInformations设置为 response 中的 studentInformations
                    $scope.studentInformations = response.data['studentInformations'];
                    // 当前页数加 1
                    $scope.page += 1;
                    // 起始位置向后偏移
                    $scope.offsetNumber += $scope.limit;
                },
                function errorCallback(reason) {
                }
        );
    };

    /*
        实现点击上一页功能
     */
    $scope.previousPage = function () {
        if ($scope.page === 1) {
            alert("已到首页");
            return;
        }

        $http({
            methond: 'get',
            url: '/java_ee_design/selectStudentInformation.mvc',
            // 查询所需参数
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
                $scope.page -= 1;
                $scope.offsetNumber -= $scope.limit;
            },
            function errorCallback(reason) {
            }
        );
    };

    $scope.selectStudent = function () {
        // 查询起始位置为0 每次查询返回数据量限制为 10
        $scope.offsetNumber = 0;
        $scope.limit = 10;

        $http({
            methond: 'get',
            url: '/java_ee_design/selectStudentInformation.mvc',
            params: {
                offset: $scope.offsetNumber,
                limit: $scope.limit,
                // 检测是否含有Id属性，没有则将参数设置为空字符串
                Id: $scope.student.hasOwnProperty("Id") ? $scope.student["Id"] : '',
                name: $scope.student.hasOwnProperty("name") ? $scope.student["name"] : '',
                classId: $scope.student.hasOwnProperty("classId") ? $scope.student["classId"] : '',
                sex: $scope.student.hasOwnProperty("sex") ? $scope.student["sex"] : '',
                major: $scope.student.hasOwnProperty("major") ? $scope.student["major"] : '',
                college: $scope.student.hasOwnProperty("college") ? $scope.student["college"] : ''
            }
        }).then(
            function successCallback(response) {
                $scope.studentInformations = response.data['studentInformations'];
                // 获取数据查询的总数
                $scope.count = response.data['count'];
                // 获取页面的总数
                $scope.countPage = Math.ceil($scope.count / 10);
                $scope.page = 1;
            },
            function errorCallback(reason) {
            }
        );
    }
});