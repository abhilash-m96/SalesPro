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
         				width:50
         			},
         			{
         				header : 'Token Name',
         				dataIndex : 'tokenName',
         				sortable:true,
         				width:50
         			},
         			{
         				header : 'Frequency ID',
         				dataIndex : 'freqId',
         				sortable:true,
         				width:50
         			},
         			{
         				header : 'Frequency',
         				dataIndex : 'freq',
         				sortable:true,
         				width:50
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
		          {name:'freqId',mapping:'freqId',type:'int'},
		          {name:'reviewId', mapping:'reviewId',type:'int'},
		          {name:'tourPackId',mapping:'tourPackId',type:'int'},
		          {name:'tokenName',mapping:'tokenName',type:'string'},
		          {name:'freq',mapping:'freq',type:'int'}]
	});

	keyStore = Ext.create('Ext.data.Store', {
		id : 'keyStoreId',
		name : 'keyStoreName',
		model : 'keywordModel',
		proxy : {
			type : 'ajax',
			url :contextPath+'/etourism/viewFrequency.do',
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
	
	
	
	
	
	var keyGrid = Ext.create('Ext.grid.Panel', {
		title:'Reviews Output',
		forceFit : true,
		id : 'keyGrid',
		store : keyStore,
		columns : keycolumns,
		width:800,
		height:300,
		autoFit : true,
		autoscroll:true,
		stripRows:true,
		renderTo : 'keyContainer',
		collapsible:true,
		overflowY:'auto'
	});

});
	
	
	
