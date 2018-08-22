$(function(){
	var _url='report_orderReport';
	$('#grid').datagrid({
		singleSelect:true,
		pagination:true,
		rownumbers: true,
		url:_url,
		columns:[[
		    {field:'name',title:'procuct type',width:100,align:'center'},
		    {field:'y',title:'sales',width:100,align:'center'}
		]],
		onLoadSuccess:function(data){
			displayChart(data.rows);
		}
	});
	
	//button btnSearch click event(query sales order statistics data)
	$('#btnSearch').bind('click',function(){
		var _formdata=$('#searchForm').serializeJSON();
		if(_formdata.endDate !=''){
			_formdata.endDate +=' 23:59:59';
		}
		alert(JSON.stringify(_formdata));
		$('#grid').datagrid('load',_formdata);
	});
	
	//pie chart
	
});

function displayChart(_data){
	$('#pieChart').highcharts({
	    chart: {
	        plotBackgroundColor: null,
	        plotBorderWidth: null,
	        plotShadow: false,
	        type: 'pie'
	    },
	    title: {
	        text: 'Sales order statistics report'
	    },
	    credits:{enabled:false},
	    exporting:{enabled: true},
	    tooltip: {
	        pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
	    },
	    plotOptions: {
	        pie: {
	            allowPointSelect: true,
	            cursor: 'pointer',
	            dataLabels: {
	                enabled: true
	            },
	            showInLegend: true
	        }
	    },
	    series: [{
	        name: 'proportion(ratio)',
	        colorByPoint: true,
	        data: _data
	    }]
	});
}