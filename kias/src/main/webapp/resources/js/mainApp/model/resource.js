Ext.define('resources.js.mainApp.model.resource', {
extend: 'Ext.data.Model',
fields:[
	{name:'id', type:'int'},
	{name:'resourcename', type:'string'},
	{name:'resourcecode', type:'string'},
	{name:'url', type:'string'},
	{name:'type', type:'string'},
	{name:'iconcls',type:'string'},
	{name:'priority',type:'int'},
	{name:'parentCode',type:'string'},
	{name:'parentIds',type:'string'},
	{name:'permission',type:'string'},
	{name:'leaf',type:'string'},	
	{name:'available', type:'string',defaultValue: '1' },
	{name:'remarks', type:'string'},
	{name:'children',type:'string'}
	]
});