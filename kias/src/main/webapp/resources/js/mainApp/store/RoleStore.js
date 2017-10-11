/*
 * 资源的store
 */
Ext.define("resources.js.mainApp.store.RoleStore",{
	extend: 'Ext.data.Store',
	alias: 'roleStore',
    proxy:{   
        type:'ajax',
        api: {
        	read: 'role/operate.do?action=r',
        	update: 'role/operate.do?action=c',
        	create: 'role/operate.do?action=c',
        	destroy: 'role/operate.do?action=d'
        	},
        reader:{   
            type:'json',   
            totalProperty:'total',   
            root:'list' 
        }   
    },  
    autoLoad : false,
    model:'resources.js.mainApp.model.role'	
});