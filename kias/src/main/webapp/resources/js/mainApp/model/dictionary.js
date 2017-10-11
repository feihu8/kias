Ext.define('resources.js.mainApp.model.dictionary', {
extend: 'Ext.data.Model',
fields:[
	{name:'id', type:'int'},
	{name:'types', type:'string'},
	{name:'typename', type:'string'},
	{name:'code', type:'string'},
	{name:'name', type:'string'},
	{name:'sort', type:'int'},
	{name:'enabled', type:'string',defaultValue: '1' },
	{name:'remarks', type:'string'}
	]
});