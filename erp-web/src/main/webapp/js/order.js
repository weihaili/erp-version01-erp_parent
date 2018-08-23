$(function(){
	var url='order_listByCondition';
	var btnText='';
	var inOutTitle='';
	
	//my purchase order
	if(Request['oper']=='myOrder'){
		if(Request['type']*1==1){
			url='order_myListByPage?t1.type=1';
			document.title='my purchase order';
			btnText='purchase requisition';
			
			$('#addOrdersSupplier').html('supplier :');
			
		}
		if(Request['type']*1==2){
			url='order_myListByPage?t1.type=2&t1.state=0';
			document.title='my sale order';
			$('addOrdersSupplier').html('client');
			btnText='sale order entry';
			
			$('#addOrdersSupplier').html('client');
		}
	}
	
	//purchase order 
	if(Request['oper']=='order'){
		url +='?t1.type=1';
		document.title='purchase order tracking';
	}
	
	//check business need add state equal zero limit order,only pick out unChecked order
	if(Request['oper']=='doCheck'){
		url +='?t1.type=1&t1.state=0';
		document.title='purchase order audit';
	}
	
	//confirm business need add state equal one limit order,only pick out has been checked order
	if(Request['oper']=='doStart'){
		url +='?t1.type=1&t1.state=1';
		document.title='purchase order confime';
	}
	
	//inStore
	if(Request['oper']=='doInStore'){
		url +='?t1.type=1&t1.state=2';
		inOutTitle='inStore';
		document.title='purchase order goods inStore';
	}
	
	//outStore
	if(Request['oper']=='doOutStore'){
		url +='?t1.type=2&t1.state=0';
		inOutTitle='outStore';
		document.title='sale order outStore';
	}
	
	$('#grid').datagrid({
		url : url,
		singleSelect:true,
		pagination:true,
		rownumbers:true,
		//autoRowHeight:true
		striped:true,
		//fitColumns:true,
		columns:getColumns(),
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
				   text:'audit',
				   iconCls:'icon-search',
				   handler:doCheck
			   },'-',{
				   text:'cancel order',
				   iconCls:'icon-undo',
				   handler:function(){
					   $('#ordersDlg').dialog('close');
				   }
			   }
			]
		});
	}
	
	//add confirm button
	if(Request['oper']=='doStart'){
		$('#ordersDlg').dialog({
			title:'',
			toolbar:[
			   {
				   text:'confirm',
				   iconCls:'icon-search',
				   handler:doStart
			   },'-',{
				   text:'back to audit',
				   iconCls:'icon-undo',
				   handler:function(){
					   $('#ordersDlg').dialog('close'); 
				   }
			   },'-',{
				   text:'export',
				   iconCls:'icon-excel',
				   handler:doExport
			   }
			]
		});
	}
	
	//orderDetail list add doubleClick event pop in store dialog
	if(Request['oper']=='doInStore' || Request['oper']=='doOutStore'){
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
	
	//order in out store dialog
	$('#itemDlg').dialog({
		width:330,
		height:200,
		title:inOutTitle,
		modal:true,
		closed:true,
		buttons:[{
				text:inOutTitle,
				iconCls:'icon-save',
				handler:doInOutStore
			},{
				text:'cancel',
				iconCls:'icon-cancel',
				handler:function(){
					$('#itemDlg').dialog('close');
				}
			}
		]

	});
	
	$('#addOrderDlg').dialog({
		title:inOutTitle,
		width:900,
		height:500,
		modal:true,
		closed:true
	});
	
	//add button (new purchase order) on my order list grid
	if(Request['oper']=='myOrder'){
		$('#grid').datagrid({
			toolbar: [{
				text:btnText,
				iconCls: 'icon-add',
				handler: function(){
					$('#addOrderDlg').dialog('open');
				}
			},'-',{
				text:'back',
				iconCls: 'icon-cancel',
				handler: function(){
					$('#addOrderDlg').dialog('close');;
				}
			}]

		});
	};
	
	
});

/**
 * in out storage operation
 * on button storage 
 */
function doInOutStore(){
	var _message='';
	var _url='';
	if(Request['type']*1==1){
		_message="you will put it in warehousing?";
		_url='orderDetail_doInStore';
	}
	if(Request['type']*1==2){
		_message="you will put it out warehousing?";
		_url='orderDetail_doOutStore';
	}
	
	var formData=$('#itemForm').serializeJSON();
	if(''==formData.storeuuid){
		$.messager.alert('system prompt message','please choose a storage warehouse');
		return;
	}
	//console.log(formData);
	//alert(JSON.stringify(formData));
	$.messager.confirm('confirm',_message,function(yes){
		if(yes){
			$.ajax({
				url:_url,
				dataType:'json',
				type:'post',
				data:formData,
				success:function(result){
					$.messager.alert('system prompt message',result.message,'info',function(){
						if(result.success){
							//close inStore dialog
							$('#itemDlg').dialog('close');
							
							//refresh orderDetail grid display when inStore one orderDetail record
							var selectedRow=$('#itemgrid').datagrid('getSelected');
							selectedRow.state='1';
							var data = $('#itemgrid').datagrid('getData');
							
							$('#itemgrid').datagrid('loadData',data);
							
							//close orderDetail when all orderDetail record inStore successful
							var allInStoreSuccess=true;
							$.each(data.rows,function(i,row){
								if(0==row.state*1){
									allInStoreSuccess=false;
									//jump out of the loop
									return false;
								}
							});
							if(allInStoreSuccess){
								//close orderDetail dialog
								$('#ordersDlg').dialog('close');
								//refresh orderList(table grid) display data
								$('#grid').datagrid('reload');
							}
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
	if (Request['type']*1==1) {
		switch (value * 1) {
		case 0:
			return 'not in storage';
		case 1:
			return 'wareHousing';
		default:
			return '';
		}
	}
	if (Request['type']*1==2) {
		switch (value * 1) {
		case 0:
			return 'not out of storage';
		case 1:
			return 'out of storage';
		default:
			return '';
		}
	}
}

/**
 * get purchase order state.result represent
 * purchase order(type=1) state
 *   0. unReview
 *   1. checked
 *   2. confirmed
 *   3. wareHousing
 * sale order(type=2) state
 * 	 0. not out of storage
 *   1. out of storage
 */
function getState(value){
	
	if (Request['type']*1==1) {
		switch (value * 1) {
			case 0:
				return 'unReview';
			case 1:
				return 'checked';
			case 2:
				return 'confirmed';
			case 3:
				return 'wareHousing';
			default:
				return '';
		}
	}
	if(Request['type']*1==2){
		switch (value * 1) {
			case 0:
				return 'not out of storage';
			case 1:
				return 'out of storage';
			
			default:
				return '';
		}
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

/**
 * initialization order list(grid) column group by purchase and sale
 */
function getColumns(){
	if(Request['type']*1==1){
		return [[    
	        {field:'uuid',title:'Id',width:30,align:'center'},
	        {field:'createTime',title:'create time',width:125,align:'center',formatter:formatDateTime},
		    {field:'checkTime',title:'check time',width:125,align:'center',formatter:formatDateTime},
		    {field:'startTime',title:'start time',width:125,align:'center',formatter:formatDateTime},
		    {field:'endTime',title:'end time',width:125,align:'center',formatter:formatDateTime},
	        {field:'createrName',title:'creater',width:100,align:'center'},    
	        {field:'checkerName',title:'checker',width:100,align:'center'},
	        {field:'starterName',title:'starter',width:100,align:'center'},    
	        {field:'enderName',title:'ender',width:100,align:'center'},    
	        {field:'supplierName',title:'supplier',width:100,align:'center'},    
	        {field:'totalMoney',title:'total moeny($)',width:100,align:'center',formatter:function(value,row,rowIndex){
	        	return fractionalFormat(value,2);
	        }},    
	        {field:'state',title:'state',width:120,align:'center',formatter:getState},
	        {field:'wayBillsn',title:'wayBillNo',width:90,align:'center'}
		]];
	}
	if(Request['type']*1==2){
		return [[    
	        {field:'uuid',title:'Id',width:30,align:'center'},
	        {field:'createTime',title:'create time',width:125,align:'center',formatter:formatDateTime},
		    {field:'endTime',title:'outStore time',width:125,align:'center',formatter:formatDateTime},
	        {field:'createrName',title:'creater',width:100,align:'center'},    
	        {field:'enderName',title:'ender',width:100,align:'center'},    
	        {field:'supplierName',title:'client',width:100,align:'center'},    
	        {field:'totalMoney',title:'total moeny($)',width:100,align:'center',formatter:function(value,row,rowIndex){
	        	return fractionalFormat(value,2);
	        }},    
	        {field:'state',title:'state',width:120,align:'center',formatter:getState},
	        {field:'wayBillsn',title:'wayBillNo',width:90,align:'center'}
		]];
	}
}

function doExport(){
	$.download('order_export',{"id":$('#uuid').html()});
}