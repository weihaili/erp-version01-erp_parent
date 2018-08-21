var existEditIndex = -1;//edit row index currently
$(function(){
	$('#grid').datagrid({
		columns:[[
			{field:'goodsuuid',title:'goods id',width:60,align:'center',editor:{
				type:'numberbox',
				options:{
					//set forbidden edit
					disabled:true
				}
			}},
			{field:'goodsname',title:'goods name',width:120,align:'center',editor:{type:'combobox',options:{
				url:'goods_list',
				valueField:'name',
				textField:'name',
				editable:false,
				onSelect:function(record){
					//record :represent selected object ,equivalent goods object here 
					var goodsuuidEditor=getEditor('goodsuuid');
					
					//target : represent used element
					$(goodsuuidEditor.target).val(record.uuid);
					
					var priceEditor=getEditor("price");
					$(priceEditor.target).val(record.inprice);
					
					//focus on column quantity
					var numEditor =getEditor('num');
					//choose quantity enter area
					$(numEditor.target).select();
					
					//bind keyboard event(calculate the row total amount)
					bindGridEditor();
					
					//after choose goods calculate row amount
					calculate_total();
					calculate_sum();
				}
			}}},
			{field:'price',title:'price(￥)',width:90,align:'center',editor:{type:'numberbox',options:{
				disabled:true,
				value:0.0,
				min:0.0,
				precision:2
			}}},
			{field:'num',title:'quantity',width:90,align:'center',editor:{type:'numberbox',options:{
				min:0,
				precision:1
			}}},
			{field:'money',title:'money(￥)',width:90,align:'center',editor:{type:'numberbox',options:{
				disabled:true,
				min:0.00,
				precision:2
			}}},
			{field:'-',title:'operation',width:90,align:'center',formatter:function(value,row,rowIndex){
				var oper='';
				if(row.num!='total'){
					oper='<a href="javascript:void(0)" onclick="deleteRow('+rowIndex+');">delete</a>';
				}
				return oper;
			}}
		]],
		singleSelect:true,//only choose one line
		rownumbers: true,//display row number start at one
		showFooter:true,//display row footer
		toolbar: [
	        {
			    text: 'add',
				iconCls: 'icon-add',
				handler: function(){
					//close editor last
					if(existEditIndex >-1){
						$('#grid').datagrid('endEdit',existEditIndex);
					}
					//append new row
					$('#grid').datagrid('appendRow',{
						num:0,
						money:0
					});
					//get all rows (array)
					var rows=$('#grid').datagrid('getRows');
					
					//set edit row index currently
					existEditIndex=rows.length-1;
					
					//open edit
					$('#grid').datagrid('beginEdit',existEditIndex);
				}
	        },'-',{
	        	text: 'submit',
				iconCls: 'icon-save',
				handler: function(){
					//1. close edit state
					if(existEditIndex>-1){
						$('#grid').datagrid('endEdit',existEditIndex);
					}
					//2. only after adding goods can be submit
					//get all goods detail
					var rows=$('#grid').datagrid('getRows');
					if(rows.length==0){
						return ;
					}
					//3. submit
					var formdata=$('#orderForm').serializeJSON();
					//convert data to json string
					//add attribute json on formdata  
					formdata.json=JSON.stringify(rows);
					//equivalent 
					//fordata['json']=JSON.stringify(rows);
					//alert(JSON.stringify(formdata));
					$.ajax({
						url:'order_add',
						data:formdata,
						type:'post',
						dataType:'json',
						success:function(data){
							$.messager.alert('system prompt message',data.message,'info',function(){
								if (data.success) {
									//clear supplier
									$('#supplier').combogrid('clear');
									//clear grid(table)
									//alert(JSON.stringify($('#grid').datagrid('getData')));
									$('#grid').datagrid('loadData', {
										total : 0,
										rows : [],
										footer : [ {
											num : 'total',
											money : 0
										} ]
									});
								}
							});
						}
					});
				}
	        }
		],
		
		//row click event
		//rowIndex:represent index current row
		//rowData: represent record object current row
		onClickRow:function(rowIndex, rowData){
			//close editor last
			$('#grid').datagrid('endEdit',existEditIndex);
			
			//set edit row index currently
			existEditIndex=rowIndex;
			
			//open edit currently
			$('#grid').datagrid('beginEdit',existEditIndex);
			
			//bind keyboard event(calculate the row total amount)
			bindGridEditor();
		}
	
	
	
	});
	
	//load row footer
	$('#grid').datagrid('reloadFooter', [ 
	   {
		num : 'total',
		money : 0
	   }
	]);
	
	//load supplier combogrid
	$('#supplier').combogrid({    
	    panelWidth:750,    
	    value:'',    
	     
	    idField:'uuid',    
	    textField:'name',    
	    url:'supplier_list?t1.type=1',    
	    columns:[[    
	        {field:'uuid',title:'id',width:60,align:'center'},    
	        {field:'name',title:'name',width:120,align:'center'}, 
	        {field:'address',title:'address',width:120,align:'center'},
	        {field:'contact',title:'contact',width:120,align:'center'},
	        {field:'tele',title:'telephone',width:120,align:'center'},
	        {field:'email',title:'email',width:160,align:'center'}    
	    ]]    
	}); 

	
});

/**
 * get edit row of editor currently
 * 
 * @param _field:cloumn field
 *            
 */
function getEditor(_field){
	return $('#grid').datagrid('getEditor',{index:existEditIndex,field:_field});
}

/**
 * calculate the row total amount
 * this event bind on calculate total button
 */
function calculate_total(){
	//get price editor
	var priceEditor=getEditor('price');
	//get price(data)
	var price=$(priceEditor.target).val();
	
	//var nunEditor=getEditor('num');
	//get quantity
	var num=$(getEditor('num').target).val();
	
	//keep two decimal places after the decimal point
	var money=(price*num).toFixed(2);
	
	//set total
	$(getEditor('money').target).val(money);
	
	//set row total value into datagrid
	$('#grid').datagrid('getRows')[existEditIndex].money=money;
}

/**
 * calculate all row total amount
 */
function calculate_sum(){
	var total=0;
	//get all row
	var rows=$('#grid').datagrid('getRows');
	//traversing
	$.each(rows,function(i,row){
		//total+=parseFloat(row.money);//addition need convert to number 1. use parseFloat parseInteger parseDouble 2. object*1
		total+=(row.money)*1
	});
	total=total.toFixed(2);
	//set all row total amount into row footer
	$('#grid').datagrid('reloadFooter', [ 
	   {
		num : 'total',
		money : total
	   }
	]);
}

/**
 * bind keyboard enter event
 */
function bindGridEditor(){
	//get column quantity editor
	var numEditor=getEditor('num');
	//bind click event
	$(numEditor.target).bind('keyup',function(){
		//bind calculate the row total amount
		calculate_total();
		//bind calculate all row total amount
		calculate_sum();
	});
	
	//bind price editor
	var priceEditor=getEditor('price');
	$(priceEditor.target).bind('keyup',function(){
		//bind calculate the row total amount
		calculate_total();
		//bind calculate all row total amount
		calculate_sum();
	});
}

/**
 * delete row
 */
function deleteRow(rowIndex){
	//before delete row close edit 
	$('#grid').datagrid('endEdit',existEditIndex);
	
	$('#grid').datagrid('deleteRow',rowIndex);
	
	//get table all data
	var data=$('#grid').datagrid('getData');
	
	//reload table data
	$('#grid').datagrid('loadData',data);
	
	calculate_sum();
}

