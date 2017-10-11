Ext.onReady(function() {
	Ext.ns("authority.main"); // 自定义一个命名空间
	main = authority.main; // 定义命名空间的别名
	main = {
	    logout: ctx + "/user/logout.do",
	    menu: ctx+"/res/getByroleRes.do",
	    welcome: ctx + "/views/commons/404.jsp"
	}
	/**
	 * 框架是上部
	 */
	main.topPannel = Ext.create('Ext.panel.Panel',{
		region:'north',
		height:58,
		//html:'<img src="images/top.gif"/><img src="images/topbg.gif"/><img  src="images/topright.gif"/>',
		split : true,
		items:new Ext.Component({
            contentEl:'topmg',
            height:30
        }),
		bbar : [{  
                    iconCls : 'icon-user',  
                    text :'登录用户：'+namese
                },'-',{  
                    text : Ext.Date.format(new Date(),'Y年m月d日')  
                },'->',{  
                    text : '退出',  
                    iconCls : 'icon-logout',
                    handler : function() {
					     //window.location.href=getRootPath()+"/user/quit.do";
            	        Ext.Msg.confirm("警告", "确定要退出吗？", function (btn) {
				            if (btn == "yes") {
				                // 发送请求
				                Share.AjaxRequest({
				                    url: main.logout,
				                    showMsg: false,
				                    callback: function (json) {
				                        Share.getWin().location = ctx;
				                    }
				                });
				            }
				        });
					 }
                }]                
	});
	/**
	 * 框架左边菜单
	 */
	main.leftPanel = Ext.create('Ext.panel.Panel', {
		region:'west',
		title : "系统菜单",
//		width : 180,
		width : 150,
		iconCls : "icon-tree",  
        autoScroll : false,  
		layout : 'accordion',  
        collapsible : true,
        layoutConfig : {  
            animate : true  
        },  
        split : true  //可以拖动
	});
     /** 
         * 右部 主体区域
         */  
//    this.rightPanel = Ext.create('Ext.tab.Panel', {  
//        region : 'center',  
//        layout : 'fit',
//        id:'rightPanel',
//        name:'rightPanel',
//        tabWidth : 120, 
//        activeTab: 1,
//        items : [{  
//                    title : '首页'  
//                },{
//                    title : "上报事件",  
//                    closable : true,  
//                    iconCls : 'icon-activity',  
//                    html : ''  
//                }]  
//    });
    // tab主面板
main.rightPanel = new Ext.TabPanel({
    id: 'mainTabPanel',
    region: 'center',
    //tabWidth : 120,
    activeTab: 0,
    deferredRender: false,
    enableTabScroll: true,
    // bodyStyle:'height:100%',
    width: '100%',
    defaults: {
        layout: 'fit',
        autoScroll: true
    },
    items: [{
        id: 'home',
        title: '我的主页',
        iconCls: 'home',
        closable: false,
        autoScroll: true,
        autoLoad: {
            url: main.welcome,
            scripts: true,
            nocache: true
        }
    }],
    listeners: {
        'bodyresize': function (panel, neww, newh) {
            // 自动调整tab下面的panel的大小
            var tab = panel.getActiveTab();
            var centerpanel = Ext.getCmp(tab.id + "_div_panel");
            if (centerpanel) {
                centerpanel.setHeight(newh - 2);
                centerpanel.setWidth(neww - 2);
            }
        }
    }
});
    
    
    /**
     * 底部版权信息
     */
    main.bottomPanel = Ext.create('Ext.panel.Panel',{
    	region: 'south',
    	split: false,
    	height: 30,
    	collapsible: true,
    	//html: '"版权所有，翻版必究'
    	margins: '0 0 0 0',
    	bbar:[
    	'->','版权所有：******','->',
		   {pressed:false,text:'与我们联系:0371-******',iconCls: 'tabs'}
		 ]
    });
    /**
     * 结合,
     */
    Ext.create('Ext.container.Viewport', {  
        layout : 'border',  
        renderTo : Ext.getBody(),  
        items : [main.topPannel, main.leftPanel, main.rightPanel, main.bottomPanel],
        listeners : {  
	        afterrender : function(){  
	            Ext.getBody().mask('正在加载系统菜单....');  
//	            ajax({  
//	                //url : "user/menu.do",// 获取面板的地址
//	            	url: main.menu,
//	                params : {  
//	                    parent_code : "0"	                    
//	                },  
//	                callback : addTree  
//	            });
	             Share.AjaxRequest({
            		url:main.menu,
                    params: {
                       parentCode : "0"
                    },
                    showMsg: false,
                    callback: addTree,
                    falseFun : function(json) {//失败后想做的个性化操作函数
                    	alert(json);
					}
            	});
	        }  
	    } 
     });
     //加载菜单
     function addTree(data) {
    	Ext.getBody().unmask();
		for (var i = 0; i < data.length; i++) {
			main.leftPanel.add(Ext.create("Ext.tree.Panel", {
				title : data[i].resourcename,
				iconCls : data[i].iconcls,
				autoScroll : true,
                rootVisible : false,
                viewConfig : {
                    loadingText : "正在加载..."
                },
                store : createStore(data[i].resourcecode),
				listeners : {
					 afterlayout : function() {  
                        if (this.getView().el) {  
                            var el = this.getView().el;  
                            var table = el  
                                    .down("table.x-grid-table");  
                            if (table) {  
                                table.setWidth(el.getWidth());  
                            }  
                        }  
                    },
                    itemclick : function(view,node){ 
                    	// if (node.isLeaf()) {
                    	if (node.data.leaf=='1') {
                    	 	if(node.data.type === '0'){
                    	 		 Share.openTab(node, ctx +"/views/"+node.data.url);
//	                    	 	 var panel = Ext.create('Ext.panel.Panel',{  
//	                                    title : node.data.text,  
//	                                    closable : true,  
//	                                    iconCls : 'icon-activity',  
//	                                    html : '<iframe width="100%" height="100%" frameborder="0" src='+node.data.url+'></iframe>'  
//	                                });  
//                                rightPanel.add(panel);  
//                                rightPanel.setActiveTab(panel);  
                    	 	}else if(node.data.type === 'COMPONENT'){  
                                        var panel = Ext.create(node.data.component,{  
                                            title : node.data.text,  
                                            closable : true,  
                                            iconCls : 'icon-activity'  
                                        });  
                                        rightPanel.add(panel);  
                                        rightPanel.setActiveTab(panel);   
                               }  
                        }  
                    }  
                 }  
              }));
             main.leftPanel.doLayout(); 
		}
     }
     var model = Ext.define("TreeModel", { // 定义树节点数据模型  
                extend : "Ext.data.Model",  
                fields : [{name : "id",type : "string"},  
                        {name : "text",mapping:"resourcename",type : "string"},
                        {name : "resourcecode",type : "string"},
                        {name : "url",type : "string"},
                        {name : "iconcls",type : "string"},  
                        {name : "leaf",type : "boolean"},  
                        {name : 'type',type : "string"},  
                        {name : 'parentCode',type : "string"}]
            });
      var createStore = function(id) { // 创建树面板数据源  
                var me = this;  
                return Ext.create("Ext.data.TreeStore", {  
                            defaultRootId : id, // 默认的根节点id  
                            model : model,
                            proxy : {  
                                type : "ajax", // 获取方式  
                                url : main.menu // 获取树节点的地址  
                            },
                            clearOnLoad : true,  
                            nodeParam : "parentCode"// 设置传递给后台的参数名,值是树节点的id属性  
                        });  
            };  
});