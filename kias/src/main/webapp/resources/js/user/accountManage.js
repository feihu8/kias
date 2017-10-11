Ext.require('resources.js.commons.sexStore');
Ext.require('resources.js.commons.combox');
Ext.onReady(function() {
	Ext.ns("authority.user");
	user =authority.user;
	//所有请求的路径
	user = {
	}
	//定义本地数据源
	//性别
	var storeSex= Ext.create('sexStore');
	
	//定义上部的查询面板
	user.topSearch = Ext.create('Ext.panel.Panel',{
		region:'north',
		layout:'column',
		bodyPadding:5,
		bodyStyle:  'background: #dfe9f6',
		border:     false,
		frame:      false,
		defaults:   {width: 120, margin: '2 5 2 5', labelWidth: 12*2, labelAlign: 'right', labelSeparator: ''},
		items: [
			{xtype:'textfield',fieldLabel: '工号',id: 'searchLoginName', width: 120, labelWidth: 12*2},
			{xtype:'textfield',fieldLabel: '姓名',id: 'searchRealName', width: 120, labelWidth: 12*2},
			Ext.create('dcombo',{fieldLabel: '性别', id: 'searchSex',	width: 120, labelWidth: 12*2, store: storeSex}),
			{xtype: 'textfield',fieldLabel: '身份证号',id: 'searchCardNo',	width: 120, labelWidth: 12*4},
			{xtype: 'textfield',fieldLabel: '手机',id: 'searchTelephone',width: 120, labelWidth: 12*2},
			
			
		]
	});

	
	
})