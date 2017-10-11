/*
 * 资源的store
 */
Ext.define("resources.js.mainApp.store.ResTreeStore",{
	extend: 'Ext.data.TreeStore',
	alias: 'resTreeStore',
    proxy:{   
        type:'ajax',
        url:'res/operate.do'        	
    },  
    autoLoad : false,
    model:'resources.js.mainApp.model.resource'	
});