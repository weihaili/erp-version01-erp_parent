
window.onload = function(){
	$('#loading-mask').fadeOut();
}
var onlyOpenTitle="欢迎使用";//不允许关闭的标签的标题

var _menus={
		/*"icon":"icon-sys",
		"menuid":"0",
		"menuname":"system menu",
		"menus":
			[
			 	
			 	{
			 		"icon":"icon-sys","menuid":"100","menuname":"basic data","menus":
			 		[
			 			 {"icon":"icon-sys","menuid":"101","menuname":"goodsType management","url":"goodsType.html"},
			 			 {"icon":"icon-sys","menuid":"102","menuname":"goods management","url":"goods.html"},
			 			 {"icon":"icon-sys","menuid":"105","menuname":"store management","url":"store.html"}						
			 		 ]
			 	},
			 	
			 	{
			 		"icon":"icon-sys","menuid":"200","menuname":"HR management","menus":
					[
						{"icon":"icon-sys","menuid":"201","menuname":"department management","url":"dep.html"}	,
						{"icon":"icon-sys","menuid":"202","menuname":"employee management","url":"emp.html"}						
					]
			 	}
			 	
			 ]*/
		};


$(function(){	
	/**
	 * display login userName
	 */
	showName();
	
	/**
	 * get menu data
	 */
	$.ajax({
		url: "menu_getMenuTree",
		type: "post",
		dataType: "json",
		success: function(data){
			//initialization menu
			_menus = data;
			//$.messager.alert('note',JSON.stringify(_menus),'info');
			InitLeftMenu();
		}
	});
	
	tabClose();
	tabCloseEven();
	
	
	/**
	 * secure logout
	 */
	$('#loginOut').bind('click',function(){
		$.ajax({
			url:'login_logout',
			success:function(){
				location.href='login.html';
			}
		});
	});
})

/**
 * display login userName
 */
function showName(){
	$.ajax({
		url:'login_showName',
		type:'post',
		dataType:'json',
		success:function(rtn){
			//judge user login state,if login successful then display userName
			//if no operation for a long time lead to user session time out,prompt user login again
			if(rtn.success){
				$('#username').html(rtn.message);
			}else{
				//$.messager.alert('note',rtn.message,'info');
				location.href='login.html';
			}
		}
		/*,error:function(){
			$.messager.alert('note','The system is under maintenance,please try again later','info');
		}*/
	});
}


//初始化左侧
function InitLeftMenu() {
	$("#nav").accordion({animate:false,fit:true,border:false});
	var selectedPanelname = '';
	
	    $.each(_menus.menus, function(i, n) {
	    	//$.messager.alert('note',JSON.stringify(_menus.menus),'info');
			var menulist ='';
			menulist +='<ul class="navlist">';
	        $.each(n.menus, function(j, o) {
				menulist += '<li><div ><a ref="'+o.menuid+'" href="#" rel="' + o.url + '" ><span class="icon '+o.icon+'" >&nbsp;</span><span class="nav">' + o.menuname + '</span></a></div> ';
				
				if(o.child && o.child.length>0)
				{
					li.find('div').addClass('icon-arrow');
	
					menulist += '<ul class="third_ul">';
					$.each(o.child,function(k,p){
						menulist += '<li><div><a ref="'+p.menuid+'" href="#" rel="' + p.url + '" ><span class="icon '+p.icon+'" >&nbsp;</span><span class="nav">' + p.menuname + '</span></a></div> </li>'
					});
					menulist += '</ul>';
				}
				
				menulist+='</li>';
	        })
			menulist += '</ul>';
	
			$('#nav').accordion('add', {
	            title: n.menuname,
	            content: menulist,
					border:false,
	            iconCls: 'icon ' + n.icon
	        });
	
			if(i==0)
				selectedPanelname =n.menuname;
	
	    });
	
	$('#nav').accordion('select',selectedPanelname);



	$('.navlist li a').click(function(){
		var tabTitle = $(this).children('.nav').text();

		var url = $(this).attr("rel");
		var menuid = $(this).attr("ref");
		var icon = $(this).find('.icon').attr('class');

		var third = find(menuid);
		if(third && third.child && third.child.length>0)
		{
			$('.third_ul').slideUp();

			var ul =$(this).parent().next();
			if(ul.is(":hidden"))
				ul.slideDown();
			else
				ul.slideUp();



		}
		else{
			addTab(tabTitle,url,icon);
			$('.navlist li div').removeClass("selected");
			$(this).parent().addClass("selected");
		}
	}).hover(function(){
		$(this).parent().addClass("hover");
	},function(){
		$(this).parent().removeClass("hover");
	});





	//选中第一个
	//var panels = $('#nav').accordion('panels');
	//var t = panels[0].panel('options').title;
    //$('#nav').accordion('select', t);
}
//获取左侧导航的图标
function getIcon(menuid){
	var icon = 'icon ';
	$.each(_menus.menus, function(i, n) {
		 $.each(n.menus, function(j, o) {
		 	if(o.menuid==menuid){
				icon += o.icon;
			}
		 })
	})

	return icon;
}

function find(menuid){
	var obj=null;
	$.each(_menus.menus, function(i, n) {
		 $.each(n.menus, function(j, o) {
		 	if(o.menuid==menuid){
				obj = o;
			}
		 });
	});

	return obj;
}

function addTab(subtitle,url,icon){
	if(!$('#tabs').tabs('exists',subtitle)){
		$('#tabs').tabs('add',{
			title:subtitle,
			content:createFrame(url),
			closable:true,
			icon:icon
		});
	}else{
		$('#tabs').tabs('select',subtitle);
		$('#mm-tabupdate').click();
	}
	tabClose();
}

function createFrame(url)
{
	var s = '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';
	return s;
}

function tabClose()
{
	/*双击关闭TAB选项卡*/
	$(".tabs-inner").dblclick(function(){
		var subtitle = $(this).children(".tabs-closable").text();
		$('#tabs').tabs('close',subtitle);
	})
	/*为选项卡绑定右键*/
	$(".tabs-inner").bind('contextmenu',function(e){
		$('#mm').menu('show', {
			left: e.pageX,
			top: e.pageY
		});

		var subtitle =$(this).children(".tabs-closable").text();

		$('#mm').data("currtab",subtitle);
		$('#tabs').tabs('select',subtitle);
		return false;
	});
}


//绑定右键菜单事件
function tabCloseEven() {

    $('#mm').menu({
        onClick: function (item) {
            closeTab(item.id);
        }
    });

    return false;
}

function closeTab(action)
{
    var alltabs = $('#tabs').tabs('tabs');
    var currentTab =$('#tabs').tabs('getSelected');
	var allTabtitle = [];
	$.each(alltabs,function(i,n){
		allTabtitle.push($(n).panel('options').title);
	})


    switch (action) {
        case "refresh":
            var iframe = $(currentTab.panel('options').content);
            var src = iframe.attr('src');
            $('#tabs').tabs('update', {
                tab: currentTab,
                options: {
                    content: createFrame(src)
                }
            })
            break;
        case "close":
            var currtab_title = currentTab.panel('options').title;
            $('#tabs').tabs('close', currtab_title);
            break;
        case "closeall":
            $.each(allTabtitle, function (i, n) {
                if (n != onlyOpenTitle){
                    $('#tabs').tabs('close', n);
				}
            });
            break;
        case "closeother":
            var currtab_title = currentTab.panel('options').title;
            $.each(allTabtitle, function (i, n) {
                if (n != currtab_title && n != onlyOpenTitle)
				{
                    $('#tabs').tabs('close', n);
				}
            });
            break;
        case "closeright":
            var tabIndex = $('#tabs').tabs('getTabIndex', currentTab);

            if (tabIndex == alltabs.length - 1){
                alert('亲，后边没有啦 ^@^!!');
                return false;
            }
            $.each(allTabtitle, function (i, n) {
                if (i > tabIndex) {
                    if (n != onlyOpenTitle){
                        $('#tabs').tabs('close', n);
					}
                }
            });

            break;
        case "closeleft":
            var tabIndex = $('#tabs').tabs('getTabIndex', currentTab);
            if (tabIndex == 1) {
                alert('亲，前边那个上头有人，咱惹不起哦。 ^@^!!');
                return false;
            }
            $.each(allTabtitle, function (i, n) {
                if (i < tabIndex) {
                    if (n != onlyOpenTitle){
                        $('#tabs').tabs('close', n);
					}
                }
            });

            break;
        case "exit":
            $('#closeMenu').menu('hide');
            break;
    }
}


//弹出信息窗口 title:标题 msgString:提示信息 msgType:信息类型 [error,info,question,warning]
function msgShow(title, msgString, msgType) {
	$.messager.alert(title, msgString, msgType);
}




//use window implement modify password
/*function openPwd() {
    $('#w').window({
        title: 'modify password',
        width: 300,
        height: 180,
        modal: true,
        shadow: true,
        closed: true,
        iconCls:'icon-edit',
        resizable:false//adjustable size

    });
};*/

//use dialog implement modify password
function openPwd() {
    $('#w').dialog({
        title: 'modify password',
        width: 300,
        height: 190,
        modal: true,
        //shadow: true,
        closed: true,
        iconCls:'icon-edit',
        //resizable:false//adjustable size
        buttons:[{
			text:'save',
			iconCls: 'icon-save',
			handler:function(){
				//submit save
				/**
				 * 1. validation
				 * empty check: 1. if(oldPwd == '')
				 *              2. if(!oldPwd)
				 * 2. submit
				 * 3. after modifying successfully,close dialog and clear enter all password content.
				 */
				var oldPwd=$('#texOldPass').val();
				var newPwd=$('#texNewPass').val();
				var rePwd=$('#texRePass').val();
				
				if(!oldPwd){
					$.messager.alert('system prompt message','please enter original password !','warning');
					return;
				}
				if(newPwd==''){
					$.messager.alert('system prompt message','please enter new password !','warning');
					return;
				}
				if(rePwd==''){
					$.messager.alert('system prompt message','please enter new password again!','warning');
					return ;
				}
				if(newPwd != rePwd){
					$.messager.alert('system prompt message','inconsistent password entered twice,please enter again','warning');
					return ;
				}
				
				$.ajax({
					url:'emp_updatePwd',
					data:{'oldPwd':oldPwd,"newPwd":newPwd},
					type:'post',
					dataType:'json',
					success:function(data){
						$.messager.alert('system prompt message','modify password successful,your new password is:'+data.message,'info',function(){
							if(data.success){
								$('#w').dialog('close');
								$('#txtOldPass').val('');
								$('#txtNewPass').val('');
								$('#txtRePass').val('');
							}
						});
					}
				});
				
				
				
			}
		},{
			text:'close',
			iconCls: 'icon-cancel',
			handler:function(){
				closePwd();
			}
		}]

    });
}
//关闭登录窗口
function closePwd() {
    $('#w').window('close');
}



//修改密码
function serverLogin() {
	var $oldpass = $('#texOldPass');
    var $newpass = $('#texNewPass');
    var $rePass = $('#txtRePass');
    
    if ($oldpass.val() == '') {
        msgShow('system prompt message', 'please enter the original password ！', 'warning');
        return false;
    }

    if ($newpass.val() == '') {
        msgShow('system prompt message', 'please enter new password ！', 'warning');
        return false;
    }
    if ($rePass.val() == '') {
        msgShow('system prompt message', 'please enter new password again ！', 'warning');
        return false;
    }

    if ($newpass.val() != $rePass.val()) {
        msgShow('system prompt message', 'inconsistent password entered twice,please enter again', 'warning');
        return false;
    }

    $.post('/ajax/editpassword.ashx?newpass=' + $newpass.val(), function(msg) {
        msgShow('系统提示', '恭喜，密码修改成功！<br>您的新密码为：' + msg, 'info');
        $newpass.val('');
        $rePass.val('');
        close();
    })
    
}

$(function() {

    openPwd();

    $('#editpass').click(function() {
        $('#w').window('open');
    });

   /* $('#btnEp').click(function() {
        serverLogin();
    })

	$('#btnCancel').click(function(){closePwd();})*/
    
    $('#btnCf').bind('click',function(){
    	serverLogin();
    });
    
    $('#btnCc').bind('click',function(){
    	closePwd();
    });
   
});


