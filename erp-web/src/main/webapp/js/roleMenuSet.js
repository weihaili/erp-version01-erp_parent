var _url='role_readRoleMenus?id=';
$(function(){
	$('#tree').tree({
		animate:true,
		checkbox:true,
		cascadeCheck:true,
		lines:true,
		dnd:true
		//url:_url+0,
	});
	
	$('#grid').datagrid({
		url:'role_list',
		singleSelect: true,
	    rownumbers: true,
		columns:[[
			{field:'uuid',title:'Id',width:90,align:'center'},    
			{field:'name',title:'Name',width:160,align:'center'}
		]],
		onClickRow:function(rowIndex,rowData){
			
			$('#tree').tree({
				animate:true,
				checkbox:true,
				cascadeCheck:true,
				lines:true,
				dnd:true,
				url:_url+(rowData.uuid)
			});
		}
	});

	$('#btnSave').bind('click',function(){
		var _nodes=$('#tree').tree('getChecked','checked');
		var ids=new Array();
		$.each(_nodes,function(i,_node){
			ids.push(_node.id);
		});
		var _checkedMenuIds = ids.join(',');//separate the elements in the array with a comma and stitch then into a string 
		alert(JSON.stringify(_checkedMenuIds));
		if(_checkedMenuIds.length == 0){
			$.messager.alert("system prompt message",'please checked menu list and chose','info');
		}
		
		var _row=$('#grid').datagrid('getSelected');
		//alert(JSON.stringify(_row));
		
		$.ajax({
			url:'role_updateRoleMenus',
			data:{id:_row.uuid,checkedMenuIds:_checkedMenuIds},
			type:'post',
			dataType:'json',
			success:function(_data){
				$.messager.alert('system prompt message',_data.message,'info',function(){
					if(_data.success){
						$('#tree').tree('reload');
					}
				});
			}
		});
	});
 
});