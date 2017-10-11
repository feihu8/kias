Ext.require('resources.js.mainApp.store.organizationStore');
Ext.onReady(function() {
	   //使用表单提示  
       Ext.QuickTips.init();  
       login = {
    	        login: ctx + "/user/login.do",
    	        main: ctx + "/views/programMain.jsp"
    	    };
       Ext.form.Field.prototype.msgTarget = 'side'; 
       var uname = new Ext.form.TextField( {  
	        id :'uname',  
	        fieldLabel : '用户名',
	        labelAlign:'right',
	        name : 'name',//元素名称  
	        //anchor:'95%',//也可用此定义自适应宽度  
	        allowBlank : false,//不允许为空  
	        blankText : '用户名不能为空',//错误提示内容  
	        listeners: {
	        	"blur":function (){
              		var loginName = Ext.getCmp('uname').getValue();
          		    var  departCom= Ext.getCmp('departCom').setValue(null);
              		departStore.load({params:{loginName:loginName}});
		        },
		        "specialKey" : function(field, e) {  
                    if (e.getKey() == Ext.EventObject.ENTER) {//响应回车  
						pwd.focus();
                    }  
                }  
	        }
	    }); 
	    var pwd = new Ext.form.TextField( {  
	        id : 'pwd',  
	        //xtype: 'textfield',  
	        inputType : 'password',
	        labelAlign:'right',
	        fieldLabel : '密　码',  
	        //anchor:'95%',  
	        maxLength : 10,  
	        name : 'password',  
	        allowBlank : false,  
	        blankText : '密码不能为空',
	        listeners: {
	        	"specialKey" : function(field, e) {  
                    if (e.getKey() == Ext.EventObject.ENTER) {//响应回车  
						save();
						//Ext.getCmp("sb").handler();
                    }  
                }
	        }
	    }); 
	   var departStore = Ext.create('resources.js.mainApp.store.organizationStore');
	   	departStore.on('load', function(){
		    Ext.getCmp('departCom').select(departStore.getAt(0));
		});
         // 定义表单  
       var loginForm = new Ext.FormPanel({
       		frame : true,
       		id : 'login', 
       		width : 360,
       		buttonAlign : 'center', 
       		items : [{
       			xtype : 'box', 
       			autoEl : {  
                  width : 350,  
                  height : 100,  
                  tag : 'img',  
                  // type : 'image',  
                  src : 'resources/images/top1.gif',  
                  style : 'filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale);margin-bottom:10px;', 
                  complete : 'off',  
                  id : 'imageBrowse' 
              }
       		},uname,pwd,
       		{
       			fieldLabel:'登陆科室',
       			labelAlign:'right',
       			xtype:'combo',
       			hiddenName : 'orgcode',
       			displayField : 'orgname',// 定义要显示的字段  
	            valueField : 'orgcode',  
       			triggerAction:'all',
       			mode:"remote",
       			editable: false,
       			emptyText:'请选择登陆科室', 
				allowBlank:false,
				name:'departCom',
				id:'departCom',
				store:departStore,
				listeners: {  
	              'expand': function(f){ 
	              		var loginName = Ext.getCmp('uname').getValue();
              		    var  departCom= Ext.getCmp('departCom').setValue(null);
	              		departStore.load({params:{loginName:loginName}});
 							              		
	              }  
	        	}
       		}],
       		buttons : [ {  
	            text : '登录',  
	            type : 'submit',  
	            id : 'sb',  
	            //定义表单提交事件  
	            handler :function(){
	                if (Ext.isIE) {
	                    if (!Ext.isIE8 && !Ext.isIE9) {
	                        Ext.Msg.confirm('温馨提示', '系统检测到您正在使用IE浏览器<br>我们强烈建议立即切换到<b>' +  '或者<b><a href="http://www.google.com/chrome/?hl=zh-CN" target="_blank">' + 'GoogleChrome</a></b>浏览器体验飞一般的感觉!' + '<br>如果您还是坚持使用IE,那么请使用基于IE8内核或者更高内核的浏览器登录!', function (btn, text) {
	                            if (btn == 'yes') {
	                            	save;
	                            }
	                        });
	                        return;
	                    }
	                }
	                save()
	            } 
		        }, {  
		            text : '重置',  
		            handler : function() {  
		                loginForm.form.reset();  
		            }  
		        } ] 	
       });
       function save() {  
                var userName = uname.getValue();  
                var userPass = pwd.getValue(); 
                var  departCom= Ext.getCmp('departCom').getValue();
                if(departCom==null || departCom==""){
                	 Ext.Msg.alert('提示','请选择登陆科室！');  
                }
                
                //验证合法后使用加载进度条  
                if (loginForm.form.isValid()) { 
                	Share.AjaxRequest({
                		url:login.login,
                        params: {
                          accountname : userName,  
                          password : userPass,
                          departCom:departCom
                        },
                        showMsg: false,
                        callback: function (json) {
                        	window.location.href=login.main;
                        },
                        falseFun : function(json) {//失败后想做的个性化操作函数
    						if (json.msg.indexOf('密码错误') > -1) {
    							$("#password").focus().val('');
    							return;
    						}
    					}
                	});
//                	Ext.Ajax.request({
//                		url: 'login.do',
//                		params : {
//                		  userName : userName,  
//                          userPass : userPass,
//                          departCom:departCom
//                		},
//			            method : "POST",  
//			            success : function(response) {  
//								var r=response.responseText;
//								if(r=="true"){
//									window.location.href =ctx+'/user/proMain.jsp';
//								}else{
//									 Ext.Msg.alert('提示',r);  
//								}
//			                },  
//			                failure : function(response) {  
//			                    Ext.Msg.alert('提示',  
//			                            "操作失败：请你检查网络！！！");  
//			                }                	
//                	});
                }  
            };
                   //定义窗体  
            var win = new Ext.Window({  
            	title:'用户登录',
            	// 窗口面板宽度  
		        width : 360,  
		        // 不容许任意伸缩大小  
		        resizable : false,  
		        // 高度  
		        autoHeight : true,  
                id : 'win',  
                layout : 'fit', //自适应布局     
                align : 'center',   
                frame:true,
                plain:true, //窗体主体部分背景颜色透明
                draggable : false,  
                border : false, 
                buttonAlign:"center",
                maximizable : false,//禁止最大化  
                closeAction : 'close',  
                closable : false,//禁止关闭,  
                items : loginForm  
            //将表单作为窗体元素嵌套布局  
            });  
            win.show();//显示窗体  
            uname.focus(false, 100); 
            // 窗口大小改变时，从新设置窗口位置
            window.onresize = function () {
                var left = ($(window).width() - win.getWidth()) / 2;
                win.setPosition(left);
            };
});