var _url='emp_readEmpRoles?id=';
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
		url:'emp_list',
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
		var _checkedEmpRoleIds = ids.join(',');//separate the elements in the array with a comma and stitch then into a string 
		alert(JSON.stringify(_checkedEmpRoleIds));
		if(_checkedEmpRoleIds.length == 0){
			$.messager.alert("system prompt message",'please checked role list and chose','info');
		}
		
		var _row=$('#grid').datagrid('getSelected');
		//alert(JSON.stringify(_row));
		
		$.ajax({
			url:'emp_updateEmpRoles',
			data:{id:_row.uuid,checkedEmpRoleIds:_checkedEmpRoleIds},
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