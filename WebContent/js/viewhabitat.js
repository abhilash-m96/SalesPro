Ext.require([ 'Ext.grid.*', 'Ext.data.*', 'Ext.form.*',
		'Ext.layout.container.Column', 'Ext.tab.Panel' ]);
Ext.Loader.setConfig({
	enabled : true
});
Ext.tip.QuickTipManager.init();

var keycolumns = [ {
	header : 'SESSION ID',
	dataIndex : 'sessionId',
	sortable : true,
	width : 80
}, {
	header : 'SESSION NAME',
	dataIndex : 'sessionName',
	sortable : true,
	width : 80
}, {
	header : 'Action Name',
	dataIndex : 'actionName',
	sortable : true,
	width : 80
}, {
	header : 'Action Type',
	dataIndex : 'actionType',
	sortable : true,
	width : 80
},
{
	header : 'IP Address',
	dataIndex : 'ipAddress',
	sortable : true,
	width : 80

},
{
	header : 'User Name',
	dataIndex : 'userName',
	sortable : true,
	width : 80

},
{
	header : 'Date',
	dataIndex : 'date',
	sortable : true,
	width : 80

},
{
	header : 'Time',
	dataIndex : 'timeOfAction',
	sortable : true,
	width : 180

}

];

var hideConfirmationMsg;
var showConfirmationMsg;
/* Hide the Confirmation Message */
hideConfirmationMsg = function() {
	var confMsgDiv = Ext.get('confirmationMessage');
	confMsgDiv.dom.innerHTML = "";
	confMsgDiv.dom.style.display = 'none';
}
/* Show Confirmation Message */
showConfirmationMsg = function(msg) {
	var confMsgDiv = Ext.get('confirmationMessage');
	confMsgDiv.dom.innerHTML = msg;
	confMsgDiv.dom.style.display = 'inline-block';
}
var keyStore;
Ext.onReady(function() {

	hideConfirmationMsg();

	var loadMask = new Ext.LoadMask(Ext.getBody(), {
		msg : "Loading"
	});
	loadMask.show();

	Ext.define('keywordModel', {
		extend : 'Ext.data.Model',
		fields : [ {
			name : 'sessionId',
			mapping : 'sessionId',
			type : 'string'
		}, {
			name : 'sessionName',
			mapping : 'sessionName',
			type : 'string'
		}, {
			name : 'actionName',
			mapping : 'actionName',
			type : 'string'
		},
		{
			name : 'actionType',
			mapping : 'actionType',
			type : 'string'
		},
		{
			name : 'ipAddress',
			mapping : 'ipAddress',
			type : 'string'
		},
		{
			name : 'userName',
			mapping : 'userName',
			type : 'string'
		},
		{
			name : 'date',
			mapping : 'date',
			type : 'string'
		},
		{
			name:'timeOfAction',
			mapping:'timeStampInStr',
			type:'string'
		}
		
		]
	});

	keyStore = Ext.create('Ext.data.Store', {
		id : 'keyStoreId',
		name : 'keyStoreName',
		model : 'keywordModel',
		proxy : {
			type : 'ajax',
			url : contextPath + '/book/viewSessionsForUser.do',
			extraParams : {},
			actionMethods : {
				read : 'POST'
			},
			reader : {
				type : 'json',
				root : 'model'
			}
		},
		listeners : {
			'load' : function(store, records) {

			}
		},
		autoLoad : true
	});
	
	keyGridStore = Ext.create('Ext.data.Store', {
		id : 'keyGridStoreId',
		name : 'keyGridStoreName',
		model : 'keywordModel',
		proxy : {
			type : 'ajax',
			url : contextPath + '/book/viewHabitatFileForUserIdAndSessionId.do',
			extraParams : {},
			actionMethods : {
				read : 'POST'
			},
			reader : {
				type : 'json',
				root : 'model'
			}
		},
		listeners : {
			'load' : function(store, records) {

			}
		},
		autoLoad : true
	});

	var keyGrid = Ext.create('Ext.grid.Panel', {
		title : 'View Habitat Data',
		forceFit : true,
		id : 'keyGrid',
		store : keyGridStore,
		columns : keycolumns,
		width : 800,
		height : 300,
		autoFit : true,
		autoscroll : true,
		stripRows : true,
		renderTo : 'keyContainer',
		collapsible : true,
		overflowY : 'auto'
	});
	
	Ext.define('userModel', {
		extend : 'Ext.data.Model',
		fields : [ 
		{
			name : 'loginId',
			mapping : 'loginId',
			type : 'string'
		}		
		]
	});
	
	
	userStore = Ext.create('Ext.data.Store', {
		id : 'userStoreId',
		name : 'userStoreName',
		model : 'userModel',
		proxy : {
			type : 'ajax',
			url : contextPath + '/book/viewUsers.do',
			extraParams : {},
			actionMethods : {
				read : 'POST'
			},
			reader : {
				type : 'json',
				root : 'model'
			}
		},
		listeners : {
			'load' : function(store, records) {

				loadMask.hide();
			}
		},
		autoLoad : true
	});
	
	Ext.define('sessionNameModel', {
		extend : 'Ext.data.Model',
		fields : [ 
		{
			name : 'sessionName',
			mapping : 'sessionName',
			type : 'string'
		}		
		]
	});
	
	
	sessionNameStore = Ext.create('Ext.data.Store', {
		id : 'sessionNameStoreId',
		name : 'sessionNameStoreName',
		model : 'sessionNameModel',
		proxy : {
			type : 'ajax',
			url : contextPath + '/book/viewSessionsForUser.do',
			extraParams : {},
			actionMethods : {
				read : 'POST'
			},
			reader : {
				type : 'json',
				root : 'model'
			}
		},
		listeners : {
			'load' : function(store, records) {

				loadMask.hide();
			}
		},
		autoLoad : true
	});
	
	var licensePanel = Ext
	.create(
			'Ext.form.Panel',
			{
				id : 'licensePanel',
				height : 220,
				width : 500,
				margin:'25 25 25 25',
				style : {
					border : 0
				},
				title:'Apply Licenses',
				layout: {
				type: 'table',
				columns: 1
				},
				items : [
					{
						xtype : 'combo',
						labelAlign : 'top',
						fieldLabel : 'User Name',
						id : 'loginId',
						name : 'loginId',
						queryMode : 'local',
						displayField : 'loginId',
						valueField : 'loginId',
						triggerAction : 'all',
						store : userStore,
						listeners : {	
							'select' : function(combo,records) {
						
									var urlLink = contextPath + '/book/viewSessionsForUser.do';
									var obj={};
									obj.userId=Ext.getCmp('loginId').getValue();
									doSessionName(urlLink,obj);
								}
							}
					},
					{
						xtype : 'combo',
						labelAlign : 'top',
						fieldLabel : 'Session Name',
						id : 'sessionName',
						name : 'sessionName',
						queryMode : 'local',
						displayField : 'sessionName',
						valueField : 'sessionName',
						triggerAction : 'all',
						store : sessionNameStore,
						width:400,
						listeners : {	
							'select' : function(combo,records) {
						
									var urlLink = contextPath + '/book/viewHabitatFileForUserIdAndSessionId.do';
									var obj={};
									obj.userId=Ext.getCmp('loginId').getValue();
									obj.sessionId=Ext.getCmp('sessionName').getValue();
									doReqWithSessioNameAndUrl(urlLink,obj);
								}
							}
					}],
				renderTo : 'habitatContainer'
			});

	
	function doSessionName(urlLink,obj)
	{
		var store=Ext.getCmp('sessionName').getStore();
		store.load(
			{
				url :urlLink,
				params:{
							userId:obj.userId,
						}
			}
		);
			
	}
	
	function doReqWithSessioNameAndUrl(urlLink,obj)
	{
		var store=Ext.getCmp('keyGrid').getStore();
		store.load(
			{
				url :urlLink,
				params:{
							userId:obj.userId,
							sessionId:obj.sessionId
						}
			}
		);
			
	}
});
