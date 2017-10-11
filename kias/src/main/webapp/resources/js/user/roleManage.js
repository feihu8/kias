Ext.require('resources.js.mainApp.store.RoleStore');
Ext.require('resources.js.mainApp.store.ResTreeStore');
Ext.onReady(function() {
	Ext.ns("authority.roles"); // 自定义一个命名空间
	roles = authority.roles; // 定义命名空间的别名
	roles = {
	    operate: ctx + "/role/operate.do"
	}
	//****start左侧角色表格内容
	//定义列
	roles.columns = [
		new Ext.grid.RowNumberer({region:'center',width:30}), //显示行号
		{header:'角色名称',dataIndex:'rolename',editor:{allowBlank:true}},
		{header:'角色代码',dataIndex:'rolecode',width:30,editor:{allowBlank:true}},
		{header:'是否可用',dataIndex:'available',width:20,editor:new Ext.form.Checkbox({allowBlank: false}), renderer:function(value) {return (value=='1' || value=='true') ? '是' : '否';}},
		{header:'描述',dataIndex:'description',editor:{allowBlank:true}}		
	];
	roles.roleStore = Ext.create('roleStore',{pageSize:100});
	roles.roleStore.load();
	var sm = new Ext.selection.CheckboxModel({checkOnly:true});
	roles.manageGrid = new Ext.grid.GridPanel({
		title:'角色列表',
		layout:'fit',
		store:roles.roleStore,
	 	renderTo:'roleGrid', //渲染位置   
	 	height: main.rightPanel.getHeight()-50,
	 	width: main.rightPanel.getWidth()-300,
        columns:roles.columns, //显示列
        stripeRows:true, //斑马线效果   
		forceFit: true, //列表宽度自适应
        selType: 'cellmodel',
        selModel:sm,
        plugins:[   
                 Ext.create('Ext.grid.plugin.CellEditing',{   
                     clicksToEdit:1 //设置单击单元格编辑   
                 })   
        ],
               //顶部工具栏  添加一行   
        tbar:['-',{   
            text:'添加角色',
            handler:function(){ 
            	var p ={rolename:'',rolecode:'', description:'',available:'1'}; 
            	roles.roleStore.insert(0,p);
            }   
         },'-',{
            text:'删除',   
            handler:function(){
	    	     Ext.Msg.confirm('系统提示','确定要删除？(删除可能会造成业务数据错误,请谨慎操作。)',function(btn){   
	                if(btn=='yes'){  
	                	  var setIds =null;
	                      var rows = roles.manageGrid.getSelectionModel().getSelection();//获取所选行数
	                      for (var i = 0; i < rows.length; i++) {
	                      	 setIds += rows[i].get("id");
	                         if (i < rows.length - 1) setIds += ',';
	                         roles.roleStore.remove(rows[i]); 
	                      }
		                 Ext.Ajax.request({   
		                    method:'POST',   
		                    url:roles.operate,
		                    success:function(response){   
		                        Ext.Msg.alert('系统提示','删除成功！删除了'+response.responseText+'条记录',function(){   
		                            roles.roleStore.load();   
		                        });   
		                    },   
		                    failure:function(){   
		                        Ext.Msg.alert("错误","与后台联系的时候出了问题。");   
		                    },   
		                    params:'action=d&id='+setIds
		                });                              
	                }   
	            });
            }
         },'-',{
        	text:'保存角色',   
            handler:function(){
                var m = roles.roleStore.getModifiedRecords().slice(0); 
                var jsonArray = [];   
                Ext.each(m,function(item){
                    jsonArray.push(item.data);   
                });  
                if(jsonArray.length>0){
                  Ext.Ajax.request({   
	  	              method:'POST',   
	  	              url:roles.operate,
	  	              success:function(response){                   	
	  	                  Ext.Msg.alert('系统提示',"保存成功，保存"+response.responseText+"条",function(){                    	
	  	                      roles.roleStore.load();                          
	  	                      roles.roleStore.removeAll();                           
	  	                      roles.roleStore.load();
	  	                  });
	  	              },
	  	              failure:function(){
	  	                  Ext.Msg.alert("错误","与后台联系的时候出了问题。");
	  	              }, 
	  	              params:'action=c&data='+Ext.encode(jsonArray).replace("%","%25") 
	  	          });
                }else{
                	Ext.Msg.alert("错误","你没有新增或修改的项目！");
                }
            }
        },'-',{
        	text:'保存角色资源关系',   
            handler:function(){
            	var node = roles.restree.getChecked();
            	var jsonArray = [];
            	for (var i = 0; i < node.length; i++) {  
                    jsonArray.push(node[i].data.resourcecode);                    
                }
              var a = roles.manageGrid.getSelectionModel().getSelection();
              var rs="";
              Ext.each(a,function(rec){
              	rs=rec.get('id');
              });
                if(jsonArray.length>0){
                  Ext.Ajax.request({   
	  	              method:'POST',   
	  	              url:roles.operate,
	  	              success:function(response){                   	
	  	                  Ext.Msg.alert('系统提示',"保存成功!");
	  	              },
	  	              failure:function(){
	  	                  Ext.Msg.alert("错误","与后台联系的时候出了问题。");
	  	              }, 
	  	              params:'action=g&id='+rs+'&data='+jsonArray.toString() 
	  	          });
                }else{
                	Ext.Msg.alert("错误","你没有新增或修改的项目！");
                }
            }
        }]
	});	
	//end 左侧角色管理
	//定义父级菜单store	
	roles.resStore = Ext.create('resTreeStore');
	roles.resStore.load({params :{action:'t'}});
	//定义菜单列
	roles.resClumns = [
		new Ext.grid.RowNumberer({region:'center',width:30}), //显示行号
		{ xtype: 'treecolumn',text:'资源名称',dataIndex:'resourcename',width:200,editor:{allowBlank:true}},
		{text:'权限代码标识',dataIndex:'permission',editor:{allowBlank:true}}
	];
	roles.restree = Ext.create('Ext.tree.Panel', {  
        title: '资源管理',  
       	height: main.rightPanel.getHeight()-50,
	 	width: 290,
        renderTo: 'resGrid',  
        collapsible: true,  
        useArrows: true,  
        rootVisible: false,  
        store: roles.resStore,
        checkModel:"multiple",
        multiSelect: true,
        columns:roles.resClumns
	});
	//测试
	roles.resStore.on('load',function(store,records,success,options) {  
		var nodes = roles.restree.getRootNode().childNodes; 
		 for (var j = 0; j < nodes.length; j++) {  
		     var node = roles.restree.getRootNode().childNodes[j];  
		     if (node.hasChildNodes()) {  
		         for (var i = 0; i < node.childNodes.length; i++) {
		        	 node.eachChild(function(child) {         //循环下一级的所有子节点  
		                 if(child.data.checked){
		                	 child.parentNode.expand();
		                	 child.expand();
		                 }  
		             }); 
		         }  
		     }  
		 }  
    });  
	
	roles.restree.on('checkchange', function (node, checked) { 
         treeclick(node, checked);  
     }, roles.restree);
	function treeclick(node, checked)  
    {  
        node.expand();  
        node.checked = checked;  
        if (node.hasChildNodes()) {                 //是否有子节点  
            node.eachChild(function(child) {         //循环下一级的所有子节点  
                child.set('checked', checked);     //赋值  
                treeclick(child, checked);                   //递归选中子节点  
            });  
        }  
    }
	//单击刷新资源
	roles.manageGrid.on("cellclick",function( grid, rowIndex, colIndex,record,event ){
		roles.resStore.load({params :{action:'t',roles:record.get("rolecode")}});
	});
});