var name='storeOper';
var _method='';
$(function(){
	var _url=name+'_listByCondition';
	$('#grid').datagrid({
		url:_url,
		singleSelect: true,
	    rownumbers: true,
	    pagePosition: 'both',
	    pagination: true ,
	    fitcolumns:true,
	    columns:[[
			{field:'uuid',title:'operation id',width:60,align:'center'},    
			{field:'empName',title:'emploeyee',width:90,align:'center'}, 
			{field:'opertime',title:'operationTime',width:130,align:'center',formatter:formatterdatatime},   
			{field:'storeName',title:'warehouse',width:120,align:'center'},    
			{field:'goodsName',title:'goods',width:60,align:'center'},    
			{field:'num',title:'quantity',width:60,align:'center'},
			{field:'type',title:'type',width:120,align:'center',formatter:function(value){
				if(1==value*1){
					return "inStore";
				}
				if(2==value*1){
					return "outStore";
				}
			}},   
			{field:'-',title:'operation',width:100,align:'center',
				formatter: function(value,row,index){
			    	var oper= ' <a href="javascript:void(0)" onclick="edit('+row.uuid+');">update</a>';
			    	oper+= ' <a href="javascript:void(0)" onclick="del('+row.uuid+');">delete</a>';
					return oper;
				}
			}    
	  ]],
	  toolbar: [{
		    text: 'add',
			iconCls: 'icon-add',
			handler: function(){
				_method="add";
				$('#editDlg').dialog('open');
				$('#editForm').form('clear');
			}
		}]
	
	});
	
	$('#btn_search').bind('click',function(){
		var _formdata=$('#searchForm').serializeJSON();
		if($.isEmptyObject(_formdata)){
			return ;
		}
		$('#grid').datagrid('load',_formdata);
	});
	
	$('#btnSave').bind('click',function(){
		var _formdata=$('#editForm').serializeJSON();
		if($.isEmptyObject(_formdata)){
			return;
		}
		$.ajax({
			url:name+'_'+_method,
			data:_formdata,
			type:'post',
			dataType:'json',
			success:function(_data){
				$.messager.alert('system prompt message',_data.message,'info',function(){
					if(_data.success){
						$('#editDlg').dialog('close');
						$('#grid').datagrid('reload');
					}
				});
				
				//$('#grid').datagrid('loadData',_data);
			}
		
		});
		
	});
	
	$('#btnCancel').bind('click',function(){
		$('#editDlg').dialog('close');
	});
	
	$('#editDlg').dialog({
		title : 'edit',
		width : 320,
		height : 220,
		closed : true,
		modal : true
	});
});

function formatterdatatime(value){
	return new Date(value).Format('yyyy-MM-dd hh:mm:ss');
}

//operation del
function del(uuid) {
	$.messager.confirm('confirm', 'Are you sure you want to delete the record?', function(yes) {
		if (yes) {
			$.ajax({
				url : name+'_delete?id=' + uuid,
				dataType : 'json',
				type : 'post',
				success : function() {
					$('#grid').datagrid('reload');
				}
			});
		}
	});
};

// operation edit update department information
function edit(uuid) {
	// pop-up window
	$('#editDlg').dialog('open');

	// load data
	// first clear form data then load new data
	$('#editForm').form('clear');
	_method = "update";

	$('#editForm').form('load', name+'_get?id=' + uuid); // 读取表单的URL

};