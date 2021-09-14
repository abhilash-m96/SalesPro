Ext.require(['Ext.grid.*', 'Ext.data.*', 'Ext.form.*', 'Ext.layout.container.Column', 'Ext.tab.Panel']);
Ext.Loader.setConfig({
    enabled: true
});
Ext.tip.QuickTipManager.init();



var keycolumns=[
         			{
         				header : 'Review ID',
         				dataIndex : 'reviewId',
         				sortable:true,
         				width:80
         			},
         			{
         				header : 'Tourist Package ID',
         				dataIndex : 'tourPackId',
         				sortable:true,
         				width:80
         			},
         			{
         				header : 'Positive Rating',
         				dataIndex : 'positiveRating',
         				sortable:true,
         				width    :80
         			},
         			{
         				header : 'Negative Rating',
         				dataIndex : 'negativeRating',
         				sortable:true,
         				width    :80
         			},
         			{
         				header : 'Neutral Rating',
         				dataIndex : 'neutralRating',
         				sortable:true,
         				width    :80
         			}];

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
		confMsgDiv.dom.innerHTML =  msg;
		confMsgDiv.dom.style.display = 'inline-block';		
	}
	var keyStore;
Ext.onReady(function () {
	
	hideConfirmationMsg();

	var loadMask = new Ext.LoadMask(Ext.getBody(), {msg:"Loading"});
	loadMask.show();
	
	Ext.define('keywordModel',{
		extend : 'Ext.data.Model',
		fields : [ 
		          {name:'reviewId', mapping:'reviewId',type:'int'},
		          {name:'tourPackId',mapping:'tourPackId',type:'int'},
		          {name:'positiveRating',mapping:'positiveRating',type:'int'},
		          {name:'negativeRating',mapping:'negativeRating',type:'int'},
		          {name:'neutralRating',mapping:'neutralRating',type:'int'}
		          ]
		
	});

	keyStore = Ext.create('Ext.data.Store', {
		id : 'keyStoreId',
		name : 'keyStoreName',
		model : 'keywordModel',
		proxy : {
			type : 'ajax',
			url :contextPath+'/etourism/viewPolarity.do',
			extraParams:{
			},
			actionMethods:{
				read:'POST'
			},
			reader : {
				type :'json',
				root:'model'
			}
		},
		listeners:
		{
			'load':function(store, records){
						
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
	        fields: ['positiveRating'],
	        label: {
	            renderer: Ext.util.Format.numberRenderer('0,0')
	        },
	        title: 'Positive Rating',
	        grid: true,
	        minimum: 0
	    }, {
	        type: 'Category',
	        position: 'left',
	        fields: ['tourPackId'],
	        title: 'Tour Pack Id'
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
	            this.setTitle(storeItem.get('tourPackId') + ': ' + storeItem.get('positiveRating') + ' views');
	          }
	        },
	        label: {
	          display: 'insideEnd',
	            field: 'positiveRating',
	            renderer: Ext.util.Format.numberRenderer('0'),
	            orientation: 'horizontal',
	            color: '#333',
	            'text-anchor': 'middle'
	        },
	        xField: 'tourPackId',
	        yField: 'positiveRating'
	    }]
	});
	

});
	
	
	
