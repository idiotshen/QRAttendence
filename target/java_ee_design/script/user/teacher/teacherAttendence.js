var materialApp = angular.module('materialApp', ['ngMaterial', 'ngAnimate']);

materialApp.controller("freshList", function ($scope, $http) {
        $http({
            methond: 'GET',
            url: '/java_ee_design/attendence/teacherUnattendence.mvc'
        }).then(function successCallback(response) {
            $scope.unattendences = response.data['unattendences'];
            $scope.studentIds = response.data['studentIds'];
            $scope.lessonIds = response.data['lessonIds'];
            $scope.classroomIds = response.data['classroomIds'];
            $scope.ddays = response.data['ddays'];
            $scope.dtimes = response.data['dtimes'];
            $scope.weeks = response.data['weeks'];

            $scope.unattendences.push("");
            $scope.lessonIds.push("");
            $scope.studentIds.push("");
            $scope.classroomIds.push("");
            $scope.ddays.push("");
            $scope.dtimes.push("");
            $scope.weeks.push("");

            var unattendenceStatistices = response.data['unattendenceStatistices'];
            unattendenceStatistices.forEach(function (item, index, array) {
                var td = $("tr").eq(item['dtime']).find("td").eq(item['dday']);
                td.attr("count", item['count']);
                td.html("<span>" + "累计缺课：" + td.attr("count") + "人</span>");

            });
        }, function errorCallback(response) {
        });
    }
);

materialApp.controller('switchContent', function ($scope) {
    $scope.goto = function (name) {
        if(name === 'attendenceDetail') {
            $("#attendenceDetail").css("display","inline");
            $("#chart").css("display","none");
        }

        if(name === 'chartDetail') {
            $("#attendenceDetail").css("display","none");
            $("#chart").css("display","inline");
        }
    }
});

materialApp.controller('getChart', function ($scope, $http) {
    $http({
        methond: 'GET',
        url: '/java_ee_design/attendence/teacherAttendenceChart.mvc'
    }).then(function successCallback(response) {
        createLineChart(response.data['weekStatistices'], "line", "lineChart", "周缺勤表")
        createPieChart(response.data['lessonStatistices'], "pieChart", "课缺勤表")
    },function (reason) {  });
});

function createPieChart(statistices,chartId, chartTitle) {
    var myChart = echarts.init(document.getElementById(chartId));

    var data = [];
    statistices.forEach(function (item,index) {
       data.push({name:item['statisticsName'], value:item['unattendenceCount']})
    });

    // 指定图表的配置项和数据
    var option = {
        title: {
            text: chartTitle,
            x:'center'
        },
        tooltip : {
            trigger: 'item',
            formatter: "{a} <br/>课程编号: {b} : 累计缺勤{c} 人"
        },
        legend: {
            orient : 'vertical',
            data:data.map(function (value) {
                return value['name'];
            }),
            formatter: "课程编号: {name}",
            x:"left"
        },
        series: [{
            name: '缺勤次数',
            type: 'pie',
            data: data
        }]
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
    $("#"+chartId).css("margin-left","100px");
}

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
            data:['缺勤次数'],
            axisLabel : {
                formatter: '第 {value} 周'
            }
        },
        xAxis: {
            data: xAxisData,
            axisLabel : {
                formatter: '{value} 次'
            }
        },
        yAxis: {
            splitNumber:1,
            type : 'value',
            axisLabel : {
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
    $("#"+chartId).css("margin-left","100px");
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