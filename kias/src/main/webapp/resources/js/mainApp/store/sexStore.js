/*
 * 建立性别store
 */
Ext.define('resources.js.mainApp.store.sexStore', {  
    extend:'Ext.data.Store',  
    alias: 'sexStore',  
    fields: ['code', 'name'],
 	data: [{code: '1', name: '男'},{code: '0', name: '女'}]
}); 
