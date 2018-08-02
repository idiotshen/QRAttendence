var materialApp = angular.module('materialApp', ['ngMaterial', 'ngAnimate']);

materialApp.controller("freshList", function ($scope, $http, $mdDialog, $compile) {
        $http({
            methond: 'GET',
            url: '/java_ee_design/scheduleDetail.mvc'
        }).then(
            function successCallback(response) {
                var scheduleJSON = response.data;

                scheduleJSON.forEach(function (item, index, array) {
                    var td = $("tr").eq(item['dtime']).find("td").eq(item['dday']);
                    var html = "<md-button ng-click='getAttendences($event)' '>" + item.classroomId + "<br>" + item.courseName + "<br>" + item.classId + "</md-button>";
                    var template = angular.element(html);
                    template.attr("lessonid", item['lessonId']);
                    var button = $compile(template)($scope);
                    td.append(button);
                    td.css("background", "#CFD8DC");
                });
            },
            function errorCallback(reason) {
            }
        );

        $scope.getAttendences = function (ev) {
            var button = ev.target;
            $http({
                methond: 'GET',
                url: '/java_ee_design/attendence/teacherUnattendence.mvc',
                params: {
                    "lessonId":button.getAttribute("lessonId")
                }
            }).then(function successCallback(response) {
                var unattendences = response.data['unattendences'];

                var tbody = $("#unattendenceList>tbody");

                tbody.children().remove();

                for(var i = 0; i < unattendences.length; i++) {
                    tbody.append("<tr><td>"+unattendences[i][0]+"</td></tr>");

                    var tr = $("#unattendenceList>tbody>tr:eq("+i+")");

                    var weekOfTeam = 5;

                    for(var k = 0;k < weekOfTeam; k++) {
                        tr.append("<td>��</td>")
                    }

                    for(var m = weekOfTeam; m < 18; m++) {
                        tr.append("<td>-</td>")
                    }

                    for(var j = 0; j < unattendences[i][1].length; j++){
                        $("#unattendenceList>tbody>tr:eq("+i+")>td:eq(+"+(j+1)+")").html("X");
                    }
                }
            });

        }
    }
);

materialApp.controller("getAttendence", function ($scope, $http) {

}, function errorCallback(response) {

});

materialApp.controller('switchContent', function ($scope) {
    $scope.goto = function (name) {
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
    $http({
        methond: 'GET',
        url: '/java_ee_design/attendence/teacherAttendenceChart.mvc'
    }).then(function successCallback(response) {
        createLineChart(response.data['weekStatistices'], "line", "lineChart", "��ȱ�ڱ�")
        createPieChart(response.data['lessonStatistices'], "pieChart", "��ȱ�ڱ�")
    }, function (reason) {
    });
});

function createPieChart(statistices, chartId, chartTitle) {
    var myChart = echarts.init(document.getElementById(chartId));

    var data = [];
    statistices.forEach(function (item, index) {
        data.push({name: item['statisticsName'], value: item['unattendenceCount']})
    });

    // ָ��ͼ��������������
    var option = {
        title: {
            text: chartTitle,
            x: 'center'
        },
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>�γ̱��: {b} : �ۼ�ȱ��{c} ��"
        },
        legend: {
            orient: 'vertical',
            data: data.map(function (value) {
                return value['name'];
            }),
            formatter: "�γ̱��: {name}",
            x: "left"
        },
        series: [{
            name: 'ȱ�ڴ���',
            type: 'pie',
            data: data
        }]
    };

    // ʹ�ø�ָ�����������������ʾͼ��
    myChart.setOption(option);
    $("#" + chartId).css("margin-left", "100px");
}

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
                formatter: '�� {value} ��'
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