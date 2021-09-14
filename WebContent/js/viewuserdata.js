Ext.require([ 'Ext.grid.*', 'Ext.data.*', 'Ext.form.*',
		'Ext.layout.container.Column', 'Ext.tab.Panel' ]);
Ext.Loader.setConfig({
	enabled : true
});
Ext.tip.QuickTipManager.init();

var keycolumns = [ {
	header : 'User ID',
	dataIndex : 'loginId',
	sortable : true,
	width : 80
}, {
	header : 'Page ID',
	dataIndex : 'pageId',
	sortable : true,
	width : 80
}, {
	header : 'Time of Stay',
	dataIndex : 'timeOfStay',
	sortable : true,
	width : 80
}, {
	header : 'Access Frequency',
	dataIndex : 'frequency',
	sortable : true,
	width : 80

},
{
	header : 'Number of Bytes',
	dataIndex : 'noOfBytes',
	sortable : true,
	width : 80

},
{
	header : 'Number of Advitisements',
	dataIndex : 'noOfAdv',
	sortable : true,
	width : 80

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
			name : 'loginId',
			mapping : 'loginId',
			type : 'string'
		}, {
			name : 'noOfAdv',
			mapping : 'noOfAdv',
			type : 'int'
		}, {
			name : 'noOfBytes',
			mapping : 'noOfBytes',
			type : 'double'
		},
		{
			name : 'timeOfStay',
			mapping : 'timeOfStay',
			type : 'double'
		},
		{
			name : 'pageId',
			mapping : 'pageId',
			type : 'int'
		},
		{
			name : 'frequency',
			mapping : 'frequency',
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
			url : contextPath + '/book/viewStatistics.do',
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

	var keyGrid = Ext.create('Ext.grid.Panel', {
		title : 'User Statistics',
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
		overflowY : 'auto'
	});

});
