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
                td.html("<span>" + "ȱ�δ�����" + td.attr("count") + "</span>");

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
        // ��name��css display����Ϊinline ��������Ϊnone
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
    // ����׼���õ�dom����ʼ��echartsʵ��
    var myChart = echarts.init(document.getElementById('lineChart'));

    $http({
        methond: 'GET',
        url: '/java_ee_design/attendence/studentAttendenceChart.mvc'
    }).then(function successCallback(response) {
        createLineChart(response.data['weekStatistices'], "line", "lineChart", "��ȱ�ڱ�")
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

    // ָ��ͼ��������������
    var option = {
        title: {
            text: chartTitle
        },
        tooltip: {},
        legend: {
            data: ['ȱ�ڴ���'],
            axisLabel: {
                formatter: '�� {value} ��'
            }
        },
        xAxis: {
            data: xAxisData,
            axisLabel: {
                formatter: '{value} ��'
            }
        },
        yAxis: {
            splitNumber: 1,
            type: 'value',
            axisLabel: {
                formatter: 'ȱ�� {value} ��'
            }
        },
        series: [{
            name: 'ȱ�ڴ���',
            type: type,
            data: yAxisData
        }]
    };

    // ʹ�ø�ָ�����������������ʾͼ��
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