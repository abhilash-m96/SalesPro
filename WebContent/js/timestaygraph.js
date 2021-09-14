Ext.require([ 'Ext.grid.*', 'Ext.data.*', 'Ext.form.*',
		'Ext.layout.container.Column', 'Ext.tab.Panel' ]);
Ext.Loader.setConfig({
	enabled : true
});
Ext.tip.QuickTipManager.init();


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
	
	
	Ext.create('Ext.chart.Chart', {
	    renderTo: Ext.getBody(),
	    width: 500,
	    height: 300,
	    animate: true,
	    store: keyStore,
	    axes: [{
	        type: 'Numeric',
	        position: 'bottom',
	        fields: ['timeOfStay'],
	        label: {
	            renderer: Ext.util.Format.numberRenderer('0,0')
	        },
	        title: 'Time of Stay Graph',
	        grid: true,
	        minimum: 0
	    }, {
	        type: 'Category',
	        position: 'left',
	        fields: ['loginId'],
	        title: 'User Ids'
	    }],
	    series: [{
	        type: 'bar',
	        axis: 'bottom',
	        highlight: true,
	        tips: {
	          trackMouse: true,
	          width: 140,
	          height: 28,
	          renderer: function(storeItem, item) {
	            this.setTitle('Page Id is = '+ storeItem.get('pageId'));
	          }
	        },
	        label: {
	          display: 'insideEnd',
	            field: 'data',
	            renderer: Ext.util.Format.numberRenderer('0'),
	            orientation: 'horizontal',
	            color: '#333',
	            'text-anchor': 'middle'
	        },
		 xField: 'loginId',
	        yField: 'timeOfStay'
		}]
	});


});
