Ext.require([ 'Ext.grid.*', 'Ext.data.*', 'Ext.form.*',
		'Ext.layout.container.Column', 'Ext.tab.Panel' ]);
Ext.Loader.setConfig({
	enabled : true
});
Ext.tip.QuickTipManager.init();

var keycolumns = [ {
	header : 'Product ID',
	dataIndex : 'productId',
	sortable : true,
	width : 80
	},
	{
	header : 'Product Name',
	dataIndex : 'productName',
	sortable : true,
	width : 200,
	renderer : function(value, metadata, record, rowIndex, colIndex, store) {
			metadata.tdAttr = 'data-qtip="' + value + '"';
			return value;
			
		

		}
},{
	header : 'Frequency',
	dataIndex : 'frequency',
	sortable : true,
	width : 80
}, {
	header : 'No Of Days',
	dataIndex : 'noOfDays',
	sortable : true,
	width : 80
}, {
	header : 'Min Distance',
	dataIndex : 'minDistance',
	sortable : true,
	width : 80
}, {
	header : 'Cluster No',
	dataIndex : 'clusterNo',
	sortable : true,
	width : 80

}, {
	header : 'Distance C1',
	dataIndex : 'distanceC1',
	sortable : true,
	width : 80

}, {
	header : 'Distance C2',
	dataIndex : 'distanceC2',
	sortable : true,
	width : 80

}, {
	header : 'Distance C3',
	dataIndex : 'distanceC3',
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
			name : 'productId',
			mapping : 'productId',
			type : 'string'
		},{
			name : 'productName',
			mapping : 'productName',
			type : 'string'
		}, {
			name : 'frequency',
			mapping : 'frequency',
			type : 'double'
		}, {
			name : 'noOfDays',
			mapping : 'noOfDays',
			type : 'double'
		}, {
			name : 'distanceC1',
			mapping : 'distanceC1',
			type : 'double'
		}, {
			name : 'distanceC2',
			mapping : 'distanceC2',
			type : 'double'
		}, {
			name : 'distanceC3',
			mapping : 'distanceC3',
			type : 'double'
		}, {
			name : 'minDistance',
			mapping : 'minDistance',
			type : 'double'
		}, {
			name : 'clusterNo',
			mapping : 'clusterNo',
			type : 'int'
		}

		]
	});

	keyStore = Ext.create('Ext.data.Store', {
		id : 'keyStoreId',
		name : 'keyStoreName',
		model : 'keywordModel',
		proxy : {
			type : 'ajax',
			url : contextPath + '/book/viewKmeans.do',
			autoLoad: {start: 0, limit: 25},
			pageSize:25,
			extraParams : {},
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
		title : 'View K Means Clustering Output',
		forceFit : true,
		id : 'keyGrid',
		store : keyStore,
		columns : keycolumns,
		width : 800,
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
	            displayMsg: 'Displaying KMeans {0} - {1} of {2}',
	            emptyMsg: "No topics to K Means",
	            inputItemWidth: 35
	     })
	});

});
