Ext.require('resources.js.mainApp.store.ResourceStore');
Ext.onReady(function(){
	Ext.ns("authority.resource"); // 自定义一个命名空间
	resource = authority.resource; // 定义命名空间的别名
	resource = {
	    operate: ctx + "/res/operate.do"
	}
	//定义资源类型下拉列表
	resource.typeCombo= new Ext.form.ComboBox({
	  		emptyText : '请选择',  
            lazyRender : true,  
            mode : 'local',// 远程模式  
            triggerAction : 'all',// 单击触发按钮显示全部数据  
            hiddenName : 'type',  
            displayField : 'name',// 定义要显示的字段  
            valueField : 'typecode',  
            readOnly : false,
            store:Ext.create('Ext.data.Store', {
                    fields: ['typecode', 'name'],
                    data: [                        
                        {"typecode":"0","name":"菜单"},
                        {"typecode":"1","name":"按钮"}
                    ]
                }),
            editable: false
	});
	//获取定义资源类型
	resource.getTypeComboDisplay = function(value, meta, record) {
		var rowIndex = resource.typeCombo.store.find("typecode", "" + value,0,false,false,true);
	    //meta.style="background-color:#dff9f6";
	    if (rowIndex < 0)
	    return '';
	    var record = resource.typeCombo.store.getAt(rowIndex);
	    return record ? record.get('name') : '';
	};
	//定义父级store
	resource.parentStore = Ext.create('resourceStore');
	resource.parentStore.load({params :{ty:'0',action:'rn'}});
		//定义资源类型下拉列表
	resource.parentCombo= new Ext.form.ComboBox({
	  		emptyText : '请选择',  
            lazyRender : true,  
            mode : 'remote',// 远程模式  
            triggerAction : 'all',// 单击触发按钮显示全部数据  
            hiddenName : 'resourcecode',  
            displayField : 'resourcename',// 定义要显示的字段  
            valueField : 'resourcecode',  
            readOnly : false,
            store:resource.parentStore,
            listeners : {
            	expand : function(combo) {
            		resource.parentStore.reload({params :{ty:'0',action:'rn'}});
            	}            
            },
            editable: true
	});
	resource.getParentComboDisplay = function(value, meta, record) {
		var rowIndex = resource.parentCombo.store.find("resourcecode", "" + value,0,false,false,true);
	    //meta.style="background-color:#dff9f6";
	    if (rowIndex < 0)
	    return '';
	    var record =resource.parentCombo.store.getAt(rowIndex);
	    return record ? record.get('resourcename') : '';
	};
	//定义资源store
	resource.gridStore = Ext.create('resourceStore');
	resource.gridStore.load({params :{ty:'0',action:'r',pageSize:100}});
	
	//定义菜单列
	resource.columns = [
		new Ext.grid.RowNumberer({region:'center',width:30}), //显示行号
		{text:'资源名称',dataIndex:'resourcename',width:200,editor:{allowBlank:true}},
		{text:'资源代码',dataIndex:'resourcecode',editor:{allowBlank:true}},
		{text:'上级资源',dataIndex:'parentCode',width:100,editor: resource.parentCombo,renderer:resource.getParentComboDisplay},
		{text:'URL',dataIndex:'url',width:200,editor:{allowBlank:true}},
		{text:'类型',dataIndex:'type',width:60,editor: resource.typeCombo,renderer:resource.getTypeComboDisplay},
		{text:'图标css',dataIndex:'iconcls',editor:{allowBlank:true}},
		{text:'排序',dataIndex:'priority',editor:{allowBlank:true}},
		{text:'权限代码标识',dataIndex:'permission',editor:{allowBlank:true}},
		{text:'是否叶子',dataIndex:'leaf',editor:new Ext.form.Checkbox({allowBlank: false}), renderer:function(value) {return (value=='1' || value=='true') ? '是' : '否';}},
		{text:'是否可用',dataIndex:'available',editor:new Ext.form.Checkbox({allowBlank: false}), renderer:function(value) {return (value=='1' || value=='true') ? '是' : '否';}},
		{text:'备注(参数)',dataIndex:'remarks',editor:{allowBlank:true}}
	];
	var sm = new Ext.selection.CheckboxModel({checkOnly:true});
	resource.restree = Ext.create('Ext.grid.GridPanel', {  
        title: '资源管理',  
       	height: main.rightPanel.getHeight()-26,
	 	width: main.rightPanel.getWidth()-20,
        renderTo: 'resourceGrid',  
        collapsible: true,  
        useArrows: true,  
        rootVisible: false,  
        store: resource.gridStore,  
        multiSelect: true, 
        stripeRows:true, //斑马线效果   
		forceFit: true, //列表宽度自适应
        selType: 'cellmodel',
        selModel:sm,
        columns:resource.columns,
        selType: 'cellmodel',
        selModel:sm,
        plugins:[   
                 Ext.create('Ext.grid.plugin.CellEditing',{   
                     clicksToEdit:1 //设置单击单元格编辑   
                 })
        ],
         //顶部工具栏  添加一行   
        tbar:['-',{   
            text:'添加资源',
            handler:function(){
            	var p ={resourcename:'',resourcecode:'', url:'',type:'0',iconcls:'',priority:'',permission:'',leaf:'1',available:'1',remarks:''}; 
               resource.gridStore.insert(0,p);
            }
         },'-',{
        	text:'保存',   
            handler:function(){   
                var m = resource.gridStore.getModifiedRecords().slice(0);   
                var jsonArray = [];   
                Ext.each(m,function(item){
                    jsonArray.push(item.data);   
                });   
                Ext.Ajax.request({   
                    method:'POST',   
                    url:'res/operate.do',
                    success:function(response){   
                        Ext.Msg.alert('系统提示',response.responseText,function(){   
                            resource.gridStore.load({params :{ty:'0',action:'r',pageSize:100}});   
                        });   
                    },   
                    failure:function(){   
                        Ext.Msg.alert("错误","与后台联系的时候出了问题。");   
                    },   
                    //params:'data='+encodeURIComponent(Ext.encode(jsonArray))
                    params:'action=c&data='+Ext.encode(jsonArray)
                });   
            }
        },'-',{
                text:'删除',   
                handler:function(){   
                    Ext.Msg.confirm('系统提示','确定要删除？',function(btn){   
                        if(btn=='yes'){   
                        	  var setIds =null;
                              var rows = resource.restree.getSelectionModel().getSelection();//获取所选行数
                              for (var i = 0; i < rows.length; i++) {
                              	 setIds += rows[i].get("id");
                                 if (i < rows.length - 1) setIds += ',';
                                 resource.gridStore.remove(rows[i]); 
                              }
			                 Ext.Ajax.request({   
			                    method:'POST',   
			                    url:'res/operate.do',
			                    success:function(response){   
			                        Ext.Msg.alert('系统提示',response.responseText,function(){   
			                            resource.gridStore.load({params :{ty:'0',action:'r',pageSize:100}});  
			                        });   
			                    },   
			                    failure:function(){   
			                        Ext.Msg.alert("错误","与后台联系的时候出了问题。");   
			                    },   
			                    //params:'data='+encodeURIComponent(Ext.encode(jsonArray))
			                    params:'action=d&id='+setIds
			                });                              
                        }   
                    });   
                }   
        }],
                 //底部分页
        bbar:new Ext.PagingToolbar({
        pageSize:100,
        store:resource.gridStore,    
        displayInfo:true, //是否显示数据信息   
        beforePageText:"第",
	    afterPageText:"/ {0}页",
	    firstText:"首页",
	    prevText:"上一页",
	    nextText:"下一页",
	    lastText:"尾页",
	    refreshText:"刷新",
        displayMsg:'显示第 {0} 条到 {1} 条记录，一共  {2} 条', //只要当displayInfo为true时才有效，用来显示有数据时的提示信息，{0},{1},{2}会自动被替换成对应的数据   
        emptyMsg: "没有记录" //没有数据时显示信息   
        })
	});
})