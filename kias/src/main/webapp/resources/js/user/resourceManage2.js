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
	//定义父级菜单store
	resource.parentStore = Ext.create('resourceStore');
	resource.parentStore.load({params :{parentCode:'0',action:'r'}});
	
	//定义菜单列
	resource.columns = [
		new Ext.grid.RowNumberer({region:'center',width:30}), //显示行号
		{ xtype: 'treecolumn',text:'资源名称',dataIndex:'resourcename',width:200,editor:{allowBlank:true}},
		{text:'资源代码',dataIndex:'resourcecode',editor:{allowBlank:true}},
		{text:'URL',dataIndex:'url',width:200,editor:{allowBlank:true}},
		{text:'类型',dataIndex:'type',width:60,editor: resource.typeCombo,renderer:resource.getTypeComboDisplay},
		{text:'图标css',dataIndex:'iconcls',editor:{allowBlank:true}},
		{text:'排序',dataIndex:'priority',editor:{allowBlank:true}},
		{text:'权限代码标识',dataIndex:'permission',editor:{allowBlank:true}},
		{text:'是否叶子',dataIndex:'leaf',editor:new Ext.form.Checkbox({allowBlank: false}), renderer:function(value) {return value=='1' ? '是' : '否';}},
		{text:'是否可用',dataIndex:'available',editor:new Ext.form.Checkbox({allowBlank: false}), renderer:function(value) {return value=='1' ? '是' : '否';}},
		{text:'备注(参数)',dataIndex:'remarks',editor:{allowBlank:true}}
	];
	var sm = new Ext.selection.CheckboxModel({checkOnly:true});
	resource.restree = Ext.create('Ext.tree.Panel', {  
        title: '资源管理',  
       	height: main.rightPanel.getHeight()-26,
	 	width: main.rightPanel.getWidth()-20,
        renderTo: 'resourceGrid',  
        collapsible: true,  
        useArrows: true,  
        rootVisible: false,  
        store: resource.parentStore,  
        multiSelect: true,  
        singleExpand: true,
        columns:resource.columns,
        selType: 'cellmodel',
        selModel:sm,
        plugins:[   
                 Ext.create('Ext.grid.plugin.CellEditing',{   
                     clicksToEdit:2 //设置单击单元格编辑   
                 })
        ],
         //顶部工具栏  添加一行   
        tbar:['-',{   
            text:'保存',
            handler:function(){
            	//var p ={resourcename:'',resourcecode:'', url:'',type:'0',iconcls:'',priority:'',permission:'',leaf:'1',available:'1',remarks:''}; 
//               resource.parentStore.insert(0,p);
            	//var selectedNode = resource.restree.getSelectionModel().getSelection();
				//selectedNode.appendChild(p);  
				var node= resource.restree.getChecked();
				alert(node);
				for (var i = 0; i < node.length; i++) {    
                    alert(node[i].resourcecode+"==="+node[i].resourcename);        
                }    
            }
         }]
	});
})