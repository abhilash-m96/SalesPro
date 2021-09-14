Ext.require( [ 'Ext.grid.*', 'Ext.data.*', 'Ext.form.*',
		'Ext.layout.container.Column', 'Ext.tab.Panel' ]);
Ext.Loader.setConfig( {
	enabled : true
});
Ext.tip.QuickTipManager.init();

var webColumns = [

{
	header : 'Feature Vector Id',
	dataIndex : 'fvId',
	sortable : true,
	width : 150
}, {
	header : 'Web Site Id',
	dataIndex : 'websiteId',
	sortable : true,
	width : 150
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
	width : 150
}, {
	header : 'Frequency',
	dataIndex : 'frequency',
	sortable : true,
	width : 150
}, {
	header : 'IDFT',
	dataIndex : 'idft',
	sortable : true,
	width : 100
}, {
	header : 'Feature Vector',
	dataIndex : 'featureVector',
	sortable : true,
	width : 150
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
			name : 'fvId',
			mapping : 'fvId',
			type : 'int'
		}, {
			name : 'websiteId',
			mapping : 'websiteId',
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
			name : 'frequency',
			mapping : 'frequency',
			type : 'double'
		}, {
			name : 'idft',
			mapping : 'idft',
			type : 'double'
		}, {
			name : 'featureVector',
			mapping : 'featureVector',
			type : 'double'
		}

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
			url : contextPath + '/rankweb/idftandfv.do',
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
					// Write Logic to display error message
	} else {
		// create objects for local store
		var dataObj = store.getAt(0).get('model');
		var lenOfRec = dataObj.length;
		var fvId;
		var webUrl;
		var websiteId;
		var tokenName;
		var frequency;
		var idft;
		var featureVector;
		for (i = 0; i < lenOfRec; i++) {
			fvId = dataObj[i].fvId;
			webUrl = dataObj[i].webUrl;
			websiteId = dataObj[i].websiteId;
			tokenName = dataObj[i].tokenName;
			frequency = dataObj[i].frequency;
			idft = dataObj[i].idft;
			featureVector = dataObj[i].featureVector;
			webStore.add( {
				"fvId" : fvId,
				"websiteId" : websiteId,
				"webUrl" : webUrl,
				"tokenName" : tokenName,
				"frequency" : frequency,
				"idft" : idft,
				"featureVector" : featureVector
			});
		}
	}
	loadMask.hide();
}
},
autoLoad : true
	});

	var webSiteTableGrid = Ext.create('Ext.grid.Panel', {
		title : 'Feature Vector Output',
		forceFit : true,
		id : 'webSiteGrid',
		store : webStore,
		columns : webColumns,
		width:800,
		height:300,
		autoFit : true,
		stripRows : true,
		renderTo : 'webSiteContainer',
		collapsible : true
	});

});
