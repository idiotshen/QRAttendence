var materialApp = angular.module('materialApp', ['ngMaterial', 'ngAnimate']);

materialApp.controller("freshList", function ($scope, $http) {
        $http({
            methond: 'GET',
            url: '/java_ee_design/attendence/studentUnattendence.mvc'
        }).then(function successCallback(response) {
            $scope.unattendences = response.data['unattendences'];
            $scope.lessonIds = response.data['lessonIds'];
            $scope.classroomIds = response.data['classroomIds'];
            $scope.ddays = response.data['ddays'];
            $scope.dtimes = response.data['dtimes'];
            $scope.weeks = response.data['weeks'];

            $scope.unattendences.push("");
            $scope.lessonIds.push("");
            $scope.classroomIds.push("");
            $scope.ddays.push("");
            $scope.dtimes.push("");
            $scope.weeks.push("");

            var unattendenceStatistices = response.data['unattendenceStatistices'];
            unattendenceStatistices.forEach(function (item, index, array) {
                var td = $("tr").eq(item['dtime']).find("td").eq(item['dday']);
                td.attr("count", item['count']);
                td.html("<span>" + "缺课次数：" + td.attr("count") + "</span>");

            });
        }, function errorCallback(response) {
        });
    }
);

materialApp.controller("getAttendence", function ($scope, $http) {
    $http({
        methond: 'GET',
        url: '/java_ee_design/attendence/studentUnattendence.mvc'
    }).then(function successCallback(response) {
        $scope.unattendences = response.data['unattendences'];

    });
}, function errorCallback(response) {

});


materialApp.controller('switchContent', function ($scope) {
    $scope.goto = function (name) {
        // 将name的css display设置为inline 其余设置为none
        if (name === 'attendenceDetail') {
            $("#attendenceDetail").css("display", "inline");
            $("#chart").css("display", "none");
        }

        if (name === 'chartDetail') {
            $("#attendenceDetail").css("display", "none");
            $("#chart").css("display", "inline");
        }
    }
});

materialApp.controller('getChart', function ($scope, $http) {
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('lineChart'));

    $http({
        methond: 'GET',
        url: '/java_ee_design/attendence/studentAttendenceChart.mvc'
    }).then(function successCallback(response) {
        createLineChart(response.data['weekStatistices'], "line", "lineChart", "周缺勤表")
    }, function (reason) {
    });


});


function createLineChart(statistices, type, chartId, chartTitle) {
    var myChart = echarts.init(document.getElementById(chartId));

    xAxisData = [];
    yAxisData = [];
    statistices.forEach(function (item, index, array) {
        xAxisData.push(item['statisticsName']);
        yAxisData.push(item['unattendenceCount']);
    });

    // 指定图表的配置项和数据
    var option = {
        title: {
            text: chartTitle
        },
        tooltip: {},
        legend: {
            data: ['缺勤次数'],
            axisLabel: {
                formatter: '第 {value} 周'
            }
        },
        xAxis: {
            data: xAxisData,
            axisLabel: {
                formatter: '{value} 次'
            }
        },
        yAxis: {
            splitNumber: 1,
            type: 'value',
            axisLabel: {
                formatter: '缺勤 {value} 人'
            }
        },
        series: [{
            name: '缺勤次数',
            type: type,
            data: yAxisData
        }]
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
    $("#" + chartId).css("margin-left", "100px");
}

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