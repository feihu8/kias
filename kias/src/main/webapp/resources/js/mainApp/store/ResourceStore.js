/*
 * 资源的store
 */
Ext.define("resources.js.mainApp.store.ResourceStore",{
	extend: 'Ext.data.Store',
	alias: 'resourceStore',
    proxy:{   
        type:'ajax',
        url:'res/operate.do',
        reader:{   
            type:'json',   
            totalProperty:'total',   
            root:'list' 
        } 
    },  
    autoLoad : false,
    model:'resources.js.mainApp.model.resource'	
});