 Ext.define('resources.js.mainApp.store.organizationStore', {
            extend: 'Ext.data.Store',
            model: 'resources.js.mainApp.model.organization',
            autoLoad: true,
            pageSize:60, //每页显示几条数据  
            constructor: function (config) {
            	if(typeof(config)!='undefined'){
                	this.proxy.extraParams = config;
            	}
                this.callParent();
            },
            proxy: {
                type: 'ajax',
                //url: 'user/getOrg.do',
                api: {
		        	read: 'user/getOrg.do',
		        	update: 'user/getOrg.do?action=update',
		        	create: 'user/getOrg.do?action=create',
		        	destroy: 'user/getOrg.do?action=del'
	        	},
                reader: {
			        type: "json",
			        rootProperty: "root",
			        totalProperty:'total'  
                }
            }
        });