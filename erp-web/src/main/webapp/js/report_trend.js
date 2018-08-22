$(function(){
	
	var _url='report_trendReport';
	$('#grid').datagrid({
		singleSelect:true,
		pagination:true,
		rownumbers: true,
		url:_url,
		columns:[[
		    {field:'month',title:'month',width:100,align:'center'},
		    {field:'y',title:'sales($)',width:100,align:'center'}
		]],
		onLoadSuccess:function(_data){
			//alert(JSON.stringify(_data));
			showLineChart();
		}
	});
	
	//button btnSearch click event(query sales order statistics data)
	$('#btnSearch').bind('click',function(){
		var _formdata=$('#searchForm').serializeJSON();
		$('#grid').datagrid('load',_formdata);
	});
});

function showLineChart(){
	var monthData = new Array();
	for (var i = 0; i <= 12; i++) {
		monthData.push(i+'month');
	}
	
	var choseYear=$('#year').combobox('getValue');
	if($.isEmptyObject(choseYear)){
		choseYear=new Date().getFullYear();
	}
	
	$('#lineChart').highcharts({
		title: {
			text: choseYear+' every month sales statistics trend graph',
			x:-20
	},
	credits:{enabled:false},
	exporting:{enabled: true},
	subtitle: {
			text: 'source：org.cn.kkl.com',
			x:-20
	},
	xAxis:{
		categories:monthData
	},
	yAxis: {
			title: {
					text: 'sales($)'
			},plotLines: [{
                value: 0,
                width: 1,
                color: '#808080'
            }]

	},
	tooltip:{
		valueSuffix:'$'
	},
	legend: {
			layout: 'vertical',
			align: 'center',
			verticalAlign: 'bottom'
	},
	/*plotOptions: {
			series: {
					label: {
							connectorAllowed: false
					},
					pointStart: 2010
			}
	},*/
	series: [{name:'sales ',data:$('#grid').datagrid('getRows')}],
	/*responsive: {
			rules: [{
					condition: {
							maxWidth: 500
					},
					chartOptions: {
							legend: {
									layout: 'horizontal',
									align: 'center',
									verticalAlign: 'bottom'
							}
					}
			}]
	}*/
});
}