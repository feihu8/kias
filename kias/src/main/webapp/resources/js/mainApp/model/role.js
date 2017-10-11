Ext.define('resources.js.mainApp.model.role', {
extend: 'Ext.data.Model',
fields:[
	{name:'id', type:'int'},
	{name:'rolename', type:'string'},
	{name:'rolecode', type:'string'},
	{name:'description', type:'string'},
	{name:'resourceCodes', type:'string'},	
	{name:'available', type:'string',defaultValue: '1' }
	]
});