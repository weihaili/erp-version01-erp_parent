$(function(){
	var _url = 'storeDetail_storealertList';
	$('#grid').datagrid({
		url:_url,
		singleSelect:true,
		pagination:true,
		rownumbers:true,
		columns:[[
		    {field:'uuid',title:'goods uuid',width:100,align:'center'},      
		    {field:'name',title:'goods name',width:100,align:'center'},      
		    {field:'storenum',title:'inventory',width:100,align:'center'},      
		    {field:'outnum',title:'need to release',width:100,align:'center'},      
		]],
		toolbar:[
		    {
		    	text:"send warning mail",
		    	iconCls:'icon-add',
		    	handler:function(){
		    		$.ajax({
		    			url:'storeDetail_sendStorealertMail',
		    			dataType:'json',
		    			type:'post',
		    			success:function(_data){
		    				$.messager.alert('system prompt message',_data.message,'info');
		    			}
		    		});
		    	}
		    }
		]
	});
	
	$('#searchBtn').bind('click',function(){
		var _formdata=$('#searchForm').serializeJSON();
		alert(JSON.stringify(_formdata));
		$('#grid').datagrid('load',_formdata);
	});
});