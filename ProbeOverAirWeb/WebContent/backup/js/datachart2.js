
$(function () {
    $('#container').highcharts({
        chart: {
            zoomType: 'xy'
        },
        title: {
            text: 'Temperature vs Meter_Demo'
        },
        xAxis: [{
            categories: ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11']
        }],
        yAxis: [{ // Secondary yAxis
                max: 80,
                 min: 50,
            title: {
                text: 'Temperature',
                style: {
                    color: Highcharts.getOptions().colors[0]
                }
            },
            labels: {
                format: '{value} °F',
                style: {
                    color: Highcharts.getOptions().colors[0]
                }
            },
            opposite: true
        },
            { // Primary yAxis
                 max: 20,
                 min: 0,
            labels: {
                format: '{value} kwh',
                style: {
                    color: Highcharts.getOptions().colors[1]
                }
            },
            title: {
                text: 'Meter-Demo',
                style: {
                    color: Highcharts.getOptions().colors[1]
                }
            }
        }],

        tooltip: {
            shared: true
        },

        series: [{
            name: 'Meter_Demo',
            type: 'column',
            yAxis: 1,
            data: [4, 4, 4, 4, 7, 8, 9, 10, 11, 12, 13, 15],
            tooltip: {
                pointFormat: '<span style="font-weight: bold; color: {series.color}">{series.name}</span>: <b>{point.y:.1f} kwh</b> '
            }
        }, {
            name: 'Meter_Demo error',
            type: 'errorbar',
            yAxis: 1,
            /*data: [[8, 6], [8, 13], [14, 20], [128, 136], [140, 150], [171, 179], [135, 143], [142, 149], [204, 220], [189, 199], [95, 110], [52, 56]],*/
            tooltip: {
                pointFormat: '(error range: {point.low}-{point.high} kwh)<br/>'
            }
        }, {
            name: 'Temperature',
            type: 'spline',
            data: [70, 65, 60, 55, 60, 70,75, 80, 70, 75, 80,88],
            tooltip: {
                pointFormat: '<span style="font-weight: bold; color: {series.color}">{series.name}</span>: <b>{point.y:.1f}°F</b> '
            }
        }, {
            name: 'Temperature error',
            type: 'errorbar',
            /*data: [[50, 54], [3, 7.6], [9.4, 10.4], [14.1, 15.9], [18.0, 20.1], [21.0, 24.0], [23.2, 25.3], [26.1, 27.8], [23.2, 23.9], [18.0, 21.1], [12.9, 14.0], [7.6, 10.0]],*/
            tooltip: {
                pointFormat: '(error range: {point.low}-{point.high}°F)<br/>'
            }
        }]
    });
});

