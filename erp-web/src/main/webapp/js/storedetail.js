$(function(){
	var _url='storeDetail_listByCondition';
	$('#grid').datagrid({
		url:_url,
		singleSelect:true,
		pagination:true,
		rownumbers:true,
		striped:true,
		fitColumns:true,
		columns:[[    
	        {field:'uuid',title:'Id',width:60,align:'center'},    
	        {field:'storename',title:'warehouse',width:120,align:'center'},    
	        {field:'goodsname',title:'goods',width:120,align:'center'},    
	        {field:'num',title:'quantity',width:120,align:'center'},    
	    ]],
	    onDblClickRow:function(rowIndex,rowData){
	    	$('#inventoryChangeRecord').dialog('open');
	    	$('#goodsname').html(rowData.goodsname);
	    	$('#warehouse').html(rowData.storename);
	    	var goodsuuid=rowData.goodsuuid;
	    	var storeuuid=rowData.storeuuid;
	    	var pageObj=$('#inventoryOperationDetail').datagrid('getPager');
	    	var page=$('#inventoryOperationDetail').datagrid('options').pageNumber;
	    	var size=$('#inventoryOperationDetail').datagrid('options').pageSize;
	    	var _queryParam={'page':page,'rows':size,'t1.storeuuid':storeuuid,'t1.goodsuuid':goodsuuid};
	    	var _inventoryurl ='storeOper_listByCondition';
	    	$.ajax({
	    		url:_inventoryurl,
	    		type:'post',
	    		dataType:'json',
	    		data:_queryParam,
	    		success:function(_data){
	    			$('#inventoryOperationDetail').datagrid('loadData',_data);
	    		}
	    	});
	    }
		
	});
	
	//bind click event on search button
	$('#btn_search').bind('click',function(){
		var _formdata=$('#searchForm').serializeJSON();
		$('#grid').datagrid('load',_formdata);
	});
	
	$('#inventoryChangeRecord').dialog({
		pagination:true,
		rownumbers:true,
		singleSelect:true,
		fitColumns:true,
		width:700,
		height:400,
		modal:true,
		closed:true,
		title:'Inventory change record',
		
	});
	
	$('#inventoryOperationDetail').datagrid({
		//url:'storeOper_listByCondition',
		pagination:true,
		rownumbers:true,
		singleSelect:true,
		fitColumns:true,
		columns:[[
			{field:'uuid',title:'id',width:60,align:'center'},
		    {field:'empName',title:'employee',width:60,align:'center'},
		    {field:'opertime',title:'operate time',width:60,align:'center',formatter:formatdatatime},
		    {field:'num',title:'quantity',width:60,align:'center'},
		    {field:'type',title:'type',width:60,align:'center',formatter:getType}   
		]]
	});
});

/**
 * dataTime formatter
 * @param value
 * @returns
 */
function formatdatatime(value){
	return new Date(value).Format('yyyy-MM-dd hh:mm:ss');
}

/**
 * type convert to value
 * @param value
 */
function getType(value){
	switch (value*1) {
	case 1:return "inStore";
	case 2:return "outStore";
		
	default:return '';
		
	}
}