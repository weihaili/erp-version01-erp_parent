$(function(){
	var url='order_listByCondition?t1.type=1';
	
	//check business need add state equal zero limit order,only pick out unChecked order
	if(Request['oper']=='doCheck'){
		url +='&t1.state=0';
	}
	
	//confirm business need add state equal one limit order,only pick out has been checked order
	if(Request['oper']=='doStart'){
		url +='&t1.state=1';
	}
	
	if(Request['oper']=='doInStore'){
		url +='&t1.state=2';
	}
	
	$('#grid').datagrid({
		url : url,
		singleSelect:true,
		pagination:true,
		rownumbers:true,
		//autoRowHeight:true
		striped:true,
		//fitColumns:true,
		columns:[[    
	        {field:'uuid',title:'Id',width:30,align:'center'},
	        {field:'createTime',title:'create time',width:125,align:'center',formatter:formatDateTime},
		    {field:'checkTime',title:'check time',width:125,align:'center',formatter:formatDateTime},
		    {field:'startTime',title:'start time',width:125,align:'center',formatter:formatDateTime},
		    {field:'endTime',title:'end time',width:125,align:'center',formatter:formatDateTime},
	        {field:'createrName',title:'creater',width:100,align:'center'},    
	        {field:'checkerName',title:'checker',width:100,align:'center'},
	        {field:'starterName',title:'starter',width:100,align:'center'},    
	        {field:'enderName',title:'ender',width:100,align:'center'},    
	        {field:'supplierName',title:'supplierId',width:100,align:'center'},    
	        {field:'totalMoney',title:'total moeny($)',width:100,align:'center',formatter:function(value,row,rowIndex){
	        	return fractionalFormat(value,2);
	        }},    
	        {field:'state',title:'state',width:120,align:'center',formatter:getState},
	        {field:'wayBillsn',title:'wayBillNo',width:90,align:'center'}
		]],
		onDblClickRow:function(rowIndex, rowData){
			//rowIndex:row index
			//rowDate:this row data
			//console.log(rowData);
			//alert(JSON.stringify(rowData));
			//display detail
			$('#uuid').html(rowData.uuid);
			$('#supplierName').html(rowData.supplierName);
			$('#state').html(getState(rowData.state));
			$('#creater').html(rowData.createrName);
			$('#checker').html(rowData.checkerName);
			$('#starter').html(rowData.starterName);
			$('#ender').html(rowData.enderName);
			$('#createTime').html(formatDateTime(rowData.createTime));
			$('#checkTime').html(formatDateTime(rowData.checkTime));
			$('#startTime').html(formatDateTime(rowData.startTime));
			$('#endTime').html(formatDateTime(rowData.endTime));
			$('#itemgrid').datagrid('loadData',rowData.orderDetails);
			//open dialog
			$('#ordersDlg').dialog('open');
		}
	
	
	});
	
	//order detail initialization
	$('#itemgrid').datagrid({
		singleSelect:true,
		fitColumns:true,
		columns:[[
			{field:'uuid',title:'id',width:30,align:'center'},
			{field:'goodsuuid',title:'goods id',width:50,align:'center'},
			{field:'goodsname',title:'goods name',width:80,align:'center'},
			{field:'price',title:'price',width:50,align:'center'},
			{field:'num',title:'quantity',width:60,align:'center'},
			{field:'money',title:'money(￥)',width:60,align:'center'},
			//{field:'enderName',title:'enderName',width:60,align:'center'},
			{field:'state',title:'state',width:100,align:'center',formatter:getDetailState}   
		]]
		
	});
	
	//add checked(audit) button
	if(Request['oper']=='doCheck'){
		$('#ordersDlg').dialog({
			toolbar:[
			   {
				   text:'Audit',
				   iconCls:'icon-search',
				   handler:doCheck
			   }
			]
		});
	}
	
	//add confirm button
	if(Request['oper']=='doStart'){
		$('#ordersDlg').dialog({
			toolbar:[
			   {
				   text:'confirm',
				   iconCls:'icon-search',
				   handler:doStart
			   }
			]
		});
	}
	
	//orderDetail list add doubleClick event pop in store dialog
	if(Request['oper']=='doInStore'){
		$('#itemgrid').datagrid({
			onDblClickRow:function(rowIndex, rowData){
				//display data and open itemDlg
				$('#itemuuid').val(rowData.uuid);
				$('#goodsuuid').html(rowData.goodsuuid);
				$('#goodsname').html(rowData.goodsname);
				$('#goodsnum').html(rowData.num);
				$('#itemDlg').dialog('open');
			}
		});
	}
	
	//order in store dialog
	$('#itemDlg').dialog({
		singleSelect:true,
		fitColumns:true
		width:300,
		height:200,
		title:'in store',
		modal:true,
		closed:true,
		buttons:[{
				text:'inStore',
				iconCls:'icon-save',
				handler:doInStore
			},{
				text:'cancel',
				iconCls:'icon-cancel',
				handler:function(){}
			}
		]

	});
	
	
});

/**
 * in storage operation
 * on button storage 
 */
function doInStore(){
	$.messager.confirm('confirm','Confirm all has been in storage?',function(yes){
		if(yes){
			var id=$('#uuid').html();
			var storeuuid=$('#storeuuid').html();
			$.ajax({
				url:'orderDetail_doInStore?id='+id+'&storeuuid='+storeuuid,
				dataType:'json',
				type:'post',
				success:function(data){
					$.messager.alert('system prompt message',data.message,'info',function(){
						if(data.success){
							$('#itemDlg').dialog('close');
							$('#grid').datagrid('reload');
						}
					})
				}
			
			});
		}
	});
}

/**
 * order confirm 
 * on button confirm
 */
function doStart(){
	$.messager.confirm('confirm','Confirm the approval passed',function(ok){
		if(ok){
			var id=$('#uuid').html();
			$.ajax({
				url:'order_doStart?id='+id,
				dataType:'json',
				type:'post',
				success:function(data){
					$.messager.alert('system prompt message',data.message,'info',function(){
						if(data.success){
							$('#ordersDlg').dialog('close');
							$('#grid').datagrid('reload');
						}
					});
				}
			});
		}
	});
}

/**
 * checked(audit)
 */
function doCheck(){
	$.messager.confirm('confirm','Confirm the approval passed?',function(ok){
		if(ok){
			var id=$('#uuid').html();
			$.ajax({
				url:'order_doCheck?id='+id,
				dataType:'json',
				type:'post',
				success:function(data){
					$.messager.alert('system prompt message',data.message,'info',function(){
						if(data.success){
							//close order detail dialog
							$('#ordersDlg').dialog('close');
							//refresh grid table
							$('#grid').datagrid('reload');
						}
					});
				}
			});
		}
	});
}

/**
 * get order detail state
 * 0:not in storage
 * 1:wareHousing
 * @param value
 */
function getDetailState(value){
	switch (value*1) {
		case 0: return 'not in storage';
		case 1: return 'wareHousing';
		
		default:return '';
	}
}

/**
 * get purchase order state.result represent
 * 0. unReview
 * 1. checked
 * 2. confirmed
 * 3. wareHousing
 */
function getState(value){
	switch (value*1) {
		case 0:return 'unReview';
		case 1:return 'checked';
		case 2:return 'confirmed';
		case 3:return 'wareHousing';
		default:return '';
	}
}

/**
 *date formatter 
 */
function formatDate(value){
	return new Date(value).Format('yyyy-MM-dd');
}

/**
 *dateTime formatter 
 */
function formatDateTime(value){
	return new Date(value).Format('yyyy-MM-dd hh:mm:ss');
}

/**
 * format decimal display style
 * @param value :need format decimal
 * @param _precision : number of digits after decimal point
 * @returns display decimal you want
 */
function fractionalFormat(value,_precision){
	return (value*1).toFixed(Math.floor((_precision>>>0)));
}