Ext.require( [ 'Ext.grid.*', 'Ext.data.*', 'Ext.form.*',
		'Ext.layout.container.Column', 'Ext.tab.Panel' ]);
Ext.Loader.setConfig( {
	enabled : true
});
Ext.tip.QuickTipManager.init();

var webColumns = [ {
	header : 'Web Site Id',
	dataIndex : 'webSiteId',
	sortable : true,
	width : 100
}, {
	header : 'Web Site URL',
	dataIndex : 'webUrl',
	sortable : true,
	width : 250,
	renderer : function(value) {
		return '<a href=' + value + '>' + value + '</a>';

	}
}, {
	header : 'Token Name',
	dataIndex : 'tokenName',
	sortable : true,
	width : 80
}, {
	header : 'Token Id',
	dataIndex : 'tokenId',
	sortable : true,
	width : 60
} ];

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
var webSiteStore;
Ext.onReady(function() {
	
	var loadMask = new Ext.LoadMask(Ext.getBody(), {msg:"Loading"});
	loadMask.show();

	Ext.define('webModel', {
		extend : 'Ext.data.Model',
		fields : [ {
			name : 'webSiteId',
			mapping : 'webSiteId',
			type : 'int'
		}, {
			name : 'webUrl',
			mapping : 'webUrl',
			type : 'string'
		}, {
			name : 'tokenName',
			mapping : 'tokenName',
			type : 'string'
		}, {
			name : 'tokenId',
			mapping : 'tokenId',
			type : 'int'
		} ]
	});

	Ext.define('webSiteModel', {
		extend : 'Ext.data.Model',
		fields : [ {
			name : 'model',
			mapping : 'model',
			type : 'object'
		}, {
			name : 'ebErrors',
			mapping : 'ebErrors',
			type : 'object'
		}, {
			name : 'status',
			mapping : 'status',
			type : 'boolean'
		} ]
	});

	var webStore = Ext.create('Ext.data.Store', {
		id : 'webSiteStoreId',
		name : 'webSiteStoreName',
		model : 'webModel'
	});

	webSiteStore = Ext.create('Ext.data.Store', {
		id : 'webSiteStoreId',
		name : 'webSiteStoreName',
		model : 'webSiteModel',
		proxy : {
			type : 'ajax',
			url : contextPath + '/rankweb/tokenization.do',
			extraParams : {},
			actionMethods : {
				read : 'POST'
			},
			reader : {
				type : 'json',
				totalProperty : 'totalSize'
			}
		},
		listeners : {
			'load' : function(store, records) {

				var outStatus = store.getAt(0).get('status');
				if (outStatus == false) {

					webSiteTableGrid.hide();
					//Write Logic to display error message
	} else {
		//create objects for local store
		var dataObj = store.getAt(0).get('model');
		var lenOfRec = dataObj.length;
		var webUrl;
		var webSiteId;
		var tokenName;
		var tokenId;
		for (i = 0; i < lenOfRec; i++) {
			webUrl = dataObj[i].webUrl;
			webSiteId = dataObj[i].webSiteId;
			tokenName = dataObj[i].tokenName;
			tokenId = dataObj[i].tokenId;

			webStore.add( {
				"webSiteId" : webSiteId,
				"webUrl" : webUrl,
				"tokenName" : tokenName,
				"tokenId" : tokenId
			});
		}
	}
	loadMask.hide();
}
},
autoLoad : true
	});

	var webSiteTableGrid = Ext.create('Ext.grid.Panel', {
		title : 'Tokenization Output',
		forceFit : true,
		id : 'webSiteGrid',
		store : webStore,
		columns : webColumns,
		width:500,
		height:300,
		autoFit : true,
		stripRows : true,
		renderTo : 'webSiteContainer',
		collapsible : true
	});

});
