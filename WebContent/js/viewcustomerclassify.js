Ext.require([ 'Ext.grid.*', 'Ext.data.*', 'Ext.form.*',
		'Ext.layout.container.Column', 'Ext.tab.Panel' ]);
Ext.Loader.setConfig({
	enabled : true
});

var keycolumns = [ {
	header : 'RFM',
	dataIndex : 'rfm',
	sortable : true,
	width : 80
}, {
	header : 'Customer Name',
	dataIndex : 'customer',
	sortable : true,
	width : 200,
	renderer : function(value, metadata, record, rowIndex, colIndex, store) {
		metadata.tdAttr = 'data-qtip="' + value + '"';
		return value;

	}
}, {
	header : 'Cluster No',
	dataIndex : 'clusterNo',
	sortable : true,
	width : 80
}, {
	header : 'Cluster Name',
	dataIndex : 'clusterName',
	sortable : true,
	width : 120
}

];

Ext.define('keywordModel', {
	extend : 'Ext.data.Model',
	fields : [ {
		name : 'rfm',
		mapping : 'rfm',
		type : 'double'
	}, {
		name : 'customer',
		mapping : 'customer',
		type : 'string'
	}, {
		name : 'clusterNo',
		mapping : 'clusterNo',
		type : 'int'
	}, {
		name : 'clusterName',
		mapping : 'clusterName',
		type : 'string'
	} ]

});

Ext.onReady(function() {

	var keyStore = Ext.create('Ext.data.Store', {
		id : 'keyStoreId',
		name : 'keyStoreName',
		model : 'keywordModel',
		proxy : {
			type : 'ajax',
			url : contextPath + '/book/viewCustomerClusterOutput.do',
			autoLoad : {
				start : 0,
				limit : 25
			},
			pageSize : 25,
			actionMethods : {
				read : 'POST'
			},
			reader : {
				type : 'json',
				root : 'model',
				totalProperty : 'total'
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
		title : 'View RFM Clustering Output',
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
		bbar : Ext.create('Ext.PagingToolbar', {
			store : keyStore,
			displayInfo : true,
			displayMsg : 'Displaying RFM {0} - {1} of {2}',
			emptyMsg : "No topics to RFM",
			inputItemWidth : 35
		})
	});

});