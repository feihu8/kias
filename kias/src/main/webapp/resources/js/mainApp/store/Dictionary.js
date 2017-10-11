/*
 * 字典库的store
 */
Ext.define("resources.js.mainApp.store.Dictionary",{
	extend: 'Ext.data.Store',
	alias: 'dictStore',
    proxy:{   
        type:'ajax',
        api: {
        	read: 'dict/operate.do?action=r',
        	update: 'dict/operate.do?action=u',
        	create: 'dict/operate.do?action=c',
        	destroy: 'dict/operate.do?action=d'
        	},
        reader:{   
            type:'json',   
            totalProperty:'total',   
            root:'list' 
        }   
    },  
    autoLoad : false,
    model:'resources.js.mainApp.model.dictionary'	
});