Ext.require([ 'Ext.grid.*', 'Ext.data.*', 'Ext.form.*',
		'Ext.layout.container.Column', 'Ext.tab.Panel' ]);
Ext.Loader.setConfig({
	enabled : true
});
Ext.tip.QuickTipManager.init();

var keycolumns = [{
	header : 'Frequency',
	dataIndex : 'frequency',
	sortable : true,
	width : 80
}, {
	header : 'Recency',
	dataIndex : 'recency',
	sortable : true,
	width : 80
}, {
	header : 'Monetory',
	dataIndex : 'monetory',
	sortable : true,
	width : 80
}, {
	header : 'User Id',
	dataIndex : 'userId',
	sortable : true,
	width : 200

}, {
	header : 'Recency Label',
	dataIndex : 'recencyWeight',
	sortable : true,
	width : 80

},{
	header : 'Freq Label',
	dataIndex : 'freqWeight',
	sortable : true,
	width : 80

}, {
	header :'Monetory Label',
	dataIndex : 'monetoryWeight',
	sortable : true,
	width : 180

},{
	header :'RFM Weight',
	dataIndex : 'rfmWeight',
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
	
	Ext.define('keywordModel', {
		extend : 'Ext.data.Model',
		fields : [ {
			name : 'frequency',
			mapping : 'frequency',
			type : 'double'
		},{
			name : 'recency',
			mapping : 'recency',
			type : 'double'
		}, {
			name : 'monetory',
			mapping : 'monetory',
			type : 'double'
		}, {
			name : 'userId',
			mapping : 'userId',
			type : 'string'
		}, {
			name : 'freqWeight',
			mapping : 'freqWeight',
			type : 'double'
		}, {
			name : 'recencyWeight',
			mapping : 'recencyWeight',
			type : 'double'
		}, {
			name : 'monetoryWeight',
			mapping : 'monetoryWeight',
			type : 'double'
		}, {
			name : 'rfmWeight',
			mapping : 'rfmWeight',
			type : 'double'
		}
		]
	});

	keyStore = Ext.create('Ext.data.Store', {
		id : 'keyStoreId',
		name : 'keyStoreName',
		model : 'keywordModel',
		proxy : {
			type : 'ajax',
			url : contextPath + '/book/viewRFM.do',
			autoLoad: {start: 0, limit: 25},
			pageSize:25,
			actionMethods : {
				read : 'POST'
			},
			reader : {
				type : 'json',
				root : 'model',
				totalProperty: 'total'
					
			}
		},
		listeners : {
			'load' : function(store, records) {

			}
		},
		autoLoad : true
	});
	
	keyStore.load();

		var keyGrid = Ext.create('Ext.grid.Panel', {
		title : 'RFM Clustering Output',
		forceFit : true,
		id : 'keyGrid',
		store : keyStore,
		columns : keycolumns,
		width : 1000,
		height : 300,
		autoFit : true,
		autoscroll : true,
		stripRows : true,
		renderTo : 'keyContainer',
		collapsible : true,
		overflowY : 'auto',
		 bbar: Ext.create('Ext.PagingToolbar', {
	            store: keyStore,
	            displayInfo: true,
	            displayMsg: 'Displaying RFM {0} - {1} of {2}',
	            emptyMsg: "No topics to RFM",
	            inputItemWidth: 35
	     })
	});

});
