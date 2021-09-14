Ext.require( [ 'Ext.grid.*', 'Ext.data.*', 'Ext.form.*',
		'Ext.layout.container.Column', 'Ext.tab.Panel' ]);
Ext.Loader.setConfig( {
	enabled : true
});
Ext.tip.QuickTipManager.init();

var webColumns = [ {
	header : 'Web Site Id',
	dataIndex : 'webId',
	sortable : true,
	width : 100
}, {
	header : 'Web Site URL',
	dataIndex : 'webUrl',
	sortable : true,
	width : 200,
	renderer : function(value) {
		return '<a href=' + value + '>' + value + '</a>';

	}
}, {
	header : 'Web Site Data',
	dataIndex : 'webSiteData',
	sortable : true,
	width : 500,
	renderer : function(value, metadata, record, rowIndex, colIndex, store) {
		metadata.tdAttr = 'data-qtip="' + value + '"';
		return value;

	}
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
			name : 'webId',
			mapping : 'webId',
			type : 'int'
		}, {
			name : 'webUrl',
			mapping : 'webUrl',
			type : 'string'
		}, {
			name : 'webSiteData',
			mapping : 'webSiteData',
			type : 'string'
		},

		]

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
			url : contextPath + '/rankweb/viewWebSitesData.do',
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
		var webId;
		var webSiteData;
		for (i = 0; i < lenOfRec; i++) {
			webUrl = dataObj[i].webUrl;
			webId = dataObj[i].webId;
			webSiteData = dataObj[i].webSiteData;

			webStore.add( {
				"webId" : webId,
				"webUrl" : webUrl,
				"webSiteData" : webSiteData
			});
		}
		
	}
	loadMask.hide();
}
},
autoLoad : true
	});

	var webSiteTableGrid = Ext.create('Ext.grid.Panel', {
		title : 'HTML Parser Output',
		forceFit : true,
		id : 'webSiteGrid',
		store : webStore,
		columns : webColumns,
		width:500,
		autoFit : true,
		stripRows : true,
		renderTo : 'webSiteContainer',
		collapsible : true
	});

});
