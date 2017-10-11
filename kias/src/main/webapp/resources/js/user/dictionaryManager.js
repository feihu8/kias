Ext.require('resources.js.mainApp.store.Dictionary');
Ext.onReady(function(){
	Ext.ns("authority.dict"); // 自定义一个命名空间
	dict = authority.dict; // 定义命名空间的别名
	dict = {
	    operate: ctx + "/dict/operate.do"
	}
	dict.dictTypeStore = Ext.create('dictStore');
	dict.dictTypeStore.load({params :{tp:'types'}});
	//定义列
	var columns = [
		new Ext.grid.RowNumberer({region:'center',width:30}), //显示行号
		{header:'类型',dataIndex:'types',editor:{allowBlank:true}},
		{header:'类型名称',dataIndex:'typename',editor:{allowBlank:true}},
		{header:'编码',dataIndex:'code',editor:{allowBlank:true}},
		{header:'名称',dataIndex:'name',editor:{allowBlank:true}},
		{header:'排序',dataIndex:'sort',editor:{allowBlank:true}},
		{header:'是否可用',dataIndex:'enabled',editor:new Ext.form.Checkbox({allowBlank: false}), renderer:function(value) {return value=='1' ? '是' : '否';}},
		{header:'备注(参数)',dataIndex:'remarks',editor:{allowBlank:true}}
	];	
	dict.dictStore = Ext.create('dictStore',{pageSize:100});
	dict.dictStore.load();
     //点击下一页时传递搜索框值到后台  
    dict.dictStore.on('beforeload', function (store, options) {  
         var types = Ext.getCmp('typesCombo').getValue(); //获取文本框值       
         var name = Ext.getCmp('')
         var new_params = { 
             types: types,
             enabled:"1"
         	};    
         Ext.apply(store.proxy.extraParams, new_params);    
     });      
     var sm = new Ext.selection.CheckboxModel({checkOnly:true});
     //定义表格
	 dict.grid = new Ext.grid.GridPanel({
	 	renderTo:'grid', //渲染位置   
	 	title: '字典表',
	 	height: main.rightPanel.getHeight()-26,
	 	width: main.rightPanel.getWidth()-20,
        store:dict.dictStore,   
        columns:columns, //显示列
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
            text:'添加字典',
            handler:function(){   
                var p ={   
                        types:'',   
                        typename:'',
                        code:'',
                        name:'',
                        sort:'',
                        enabled:'1',
                        remarks:''
                        }; 
                    dict.dictStore.insert(0,p);
                }   
            },'-',{
                text:'删除',   
                handler:function(){   
                    Ext.Msg.confirm('系统提示','确定要删除？(删除可能会造成业务数据错误,请谨慎操作。)',function(btn){   
                        if(btn=='yes'){  
                        	  var setIds =null;
                              var rows = dict.grid.getSelectionModel().getSelection();//获取所选行数
                              for (var i = 0; i < rows.length; i++) {
                              	 setIds += rows[i].get("id");
                                 if (i < rows.length - 1) setIds += ',';
                                 dict.dictStore.remove(rows[i]); 
                              }
			                 Ext.Ajax.request({   
			                    method:'POST',   
			                    url:dict.operate,
			                    success:function(response){   
			                        Ext.Msg.alert('系统提示','删除成功！删除了'+response.responseText+'条记录',function(){   
			                            dict.dictStore.load();   
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
        	text:'保存',   
            handler:function(){
                var m = dict.dictStore.getModifiedRecords().slice(0);   
                var jsonArray = [];   
                Ext.each(m,function(item){
                    jsonArray.push(item.data);   
                });   
                Ext.Ajax.request({   
                    method:'POST',   
                    url:dict.operate,
                    success:function(response){                   	
                        Ext.Msg.alert('系统提示',"保存成功，保存"+response.responseText+"条",function(){                        	
                            dictionaryStore.load();                              
                            dictionaryStore.removeAll();                            
                            dictionaryStore.load();
                        });   
                    },   
                    failure:function(){   
                        Ext.Msg.alert("错误","与后台联系的时候出了问题。");   
                    },                 
                    params:'action=c&data='+Ext.encode(jsonArray).replace("%","%25") 
                });   
            }
        },'-', {  
            xtype : 'combobox',      //放一个combobox，用法以后再介绍  
            id : 'typesCombo',
            emptyText : '全部',
            store : dict.dictTypeStore,  
            queryMode : 'remote', 
            displayField : 'typename',
            valueField : 'types',
            typeAhead: true,  
            triggerAction: 'all'
        },'-',{
			xtype : "button",
			text : "查询",
			margin: '0 0 0 0',
            handler: function () {
            	dict.dictStore.loadPage(1);
            }
		}],
        //底部分页
        bbar:new Ext.PagingToolbar({
        pageSize:100,
        store:dict.dictStore,    
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
	 });