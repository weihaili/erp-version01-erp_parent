$(function(){
	$('#grid').datagrid({    
	    url:'emp_listByCondition',   
	    columns:[[    
			        {field:'uuid',title:'Id',width:50,align:'center'},    
			        {field:'username',title:'username',width:120,align:'center'},    
			        //{field:'pwd',title:'password',width:90,align:'center'},
			        {field:'name',title:'name',width:120,align:'center'},    
			        {field:'gender',title:'gender',width:40,align:'center',formatter:function(value,row,index){
						if(1==value*1) return 'male';
						if(0==value*1) return 'female';
				    }},    
			        {field:'tele',title:'telephone',width:90,align:'center'},
			        {field:'address',title:'address',width:120,align:'center'},
			        {field:'birthday',title:'birthday',width:90,align:'center',formatter:function(value){
						return new Date(value).Format('yyyy-MM-dd');
				    }},
			        {field:'dep',title:'department',width:90,align:'center',formatter:function(value,row,index){
						return value.name;
				    }},
			        {field:'-',title:'operation',width:140,align:'center',
			        	formatter: function(value,row,index){
				        	var oper= ' <a href="javascript:void(0)" onclick="updatePwd_reset('+row.uuid+');">reset password</a>';
				        	oper+= ' <a href="javascript:void(0)" onclick="del('+row.uuid+');">delete</a>';
							return oper;
						}
				    }    
			    ]],
	    singleSelect: true,
	    rownumbers: true,
	    pagePosition: 'both',
	    pagination: true 
	});
	
	//initialization reset password dialog
	$('#editDlg').dialog({
		title : 'reset password',
		iconCls:'icon-save',
		width : 260,
		height : 120,
		closed : true,// the windown whether close status,if it is true,the window is close
		// cache: false, //whether demand cache
		modal : true,
		buttons:[{
			text:'save',
			iconCls:'icon-save',
			handler:function(){
				var formData=$('#editForm').serializeJSON();
				$.ajax({
					url:'emp_updatePwd_reset',
					data:formData,
					dataType:'json',
					type:'post',
					success:function(data){
						$.messager.alert('system prompt system',data.message,'info',function(){
							if(data.success){
								$('#editDlg').dialog('close');
								$('#newPwd').val('');
							}
						});
						
					}
				});
			}
		},{
			text:'cancel',
			iconCls:'icon-concel',
			handler:function(){
				$('#editDlg').dialog('close');
			}
		}]
	});
	
	
});

//button updatePwd_reset method
function updatePwd_reset(uuid){
	//open reset password dialog
	$('#editDlg').dialog('open');
	
	//clear form data
	$('#editForm').form('clear');
	
	//initialization dialog value
	$('#editForm').form('load',{id:uuid,newPwd:''});
	
};

//delete 
function del(uuid){
	$.messager.confirm('confirm','are you sure delete the record?',function(yes){
		if(yes){
			$.ajax({
				url:'',
				dataType:'json',
				success:function(data){
					$.messager.alert('system prompt message',data.message,'info',function(){
						if(data.success){
							$('#grid').datagrid('reload');
						}
					});
				}
			});
		}
	});
};