<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html><html>
<head>
	<meta charset="utf-8">
 <link rel="icon" href="https://static.jianshukeji.com/highcharts/images/favicon.ico">
<meta name="viewport" content="width=device-width, initial-scale=1">	<meta name="description" content="">
	<title>中国地图</title>
	<script src="https://img.hcharts.cn/jquery/jquery-1.8.3.min.js"></script>
	<script src="https://img.hcharts.cn/highmaps/highmaps.js"></script>
	<script src="https://img.hcharts.cn/highcharts/modules/drilldown.js"></script>
	<script src="https://img.hcharts.cn/highcharts/modules/exporting.js"></script>
</head>

<body>

<div id="container" style="height:650px;"></div>

	<script>
	refresh();
	function refresh()
	{
		
		Highcharts.setOptions({
		    lang: {
		       drillUpText: '< 返回 “{series.name}”'
		    }
		});
		var map = null
		    geochina = 'https://data.jianshukeji.com/jsonp?filename=geochina/',
		    unDrilldown = ['taiwan', 'xianggang', 'aomen'];
		// 获取中国地图数据并初始化图表
		   $.getJSON(geochina + 'china.json&callback=?', function(mapdata) {
			   Highcharts.each(mapdata.features, function(md, index) {  
		        });
		    $.getJSON('ChinaMapServlet', function(mdata) {
		        var data = [];
		       
		        Highcharts.each(mdata.features, function(mda, index) {
		            data.push({
		                name: mda.properties.name,
		                drilldown: mda.properties.filename,
		                value: mda.properties.num// 生成 1 ~ 100 随机值
		            });
		           
		        });
		    map = new Highcharts.Map('container', {
		        title: {
		            text: '中国地图'
		        },
		        subtitle: {
		            text: 'app实时在线图'
		        },
		        mapNavigation: {
		            enabled: true,
		            buttonOptions: {
		                verticalAlign: 'bottom'
		            }
		        },
		        tooltip: {
		            useHTML: true,
		            headerFormat: '<table><tr><td>{point.name}</td></tr>'
		        },
		        colorAxis: {
		            min: 0,
		            minColor: '#fff',
		            maxColor: '#006cee',
		            labels:{
		                style:{
		                    "color":"red","fontWeight":"bold"
		                }
		            }
		        },
		        series: [{
		            data: data,
		            mapData: mapdata,
		            joinBy: 'name',
		            name: '中国地图',
		            states: {
		                hover: {
		                    color: '#a4edba'
		                }
		            }
		        }]
		    });
		})});
	}
	setInterval('refresh()',20000);
	
</script>

</body></html>
