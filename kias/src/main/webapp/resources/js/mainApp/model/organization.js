Ext.define('resources.js.mainApp.model.organization', {
extend: 'Ext.data.Model',
fields:[
	{name:'id', type:'int'},
	{name:'orgname', type:'string'},
	{name:'orgcode', type:'string'},
	{name:'priority', type:'string'},
	{name:'type', type:'string'},
	{name:'division', type:'string'},
	{name:'jp', type:'string'},
	{name: 'phone', type:'string'},	
	{name:'address', type:'string'},
	{name:'introduction', type:'string'},
	{name: 'available', type:'string'},
	{name:'remarks', type:'string'}
	]
});