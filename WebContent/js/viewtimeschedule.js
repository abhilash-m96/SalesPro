Ext.require(['Ext.grid.*', 'Ext.data.*', 'Ext.form.*', 'Ext.layout.container.Column', 'Ext.tab.Panel']);
Ext.Loader.setConfig({
    enabled: true
});
Ext.tip.QuickTipManager.init();



var keycolumns=[
         			{
         				header : 'Time Schedule ID',
         				dataIndex : 'timeScheduleId',
         				sortable:true,
         				width:120
         			},
         			{
         				header : 'City Name',
         				dataIndex : 'city',
         				sortable:true,
         				width:80
         			},
         			{
         				header : 'Hotel Name',
         				dataIndex : 'hotel',
         				sortable:true,
         				width    :100
         			},
         			{
         				header : 'Place 8am-10am',
         				dataIndex : 'place810',
         				sortable:true,
         				width    :160
         			},
         			{
         				header : 'Place 10am-12:30pm',
         				dataIndex : 'place101230',
         				sortable:true,
         				width    :160
         			},
         			{
         				header : 'Place 12:30pm-1:30pm',
         				dataIndex : 'place1230130',
         				sortable:true,
         				width    :160
         			},
         			{
         				header : 'Place 1:30pm-5:30pm',
         				dataIndex : 'place130530',
         				sortable:true,
         				width    :160
         			},
         			{
         				header : 'Place 5:30pm-8:00pm',
         				dataIndex : 'place5308',
         				sortable:true,
         				width    :160
         			},
         			{
         				header : 'Date',
         				dataIndex : 'date',
         				sortable:true,
         				width    :80
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
		          {name:'timeScheduleId', mapping:'timeScheduleId',type:'int'},
		          {name:'date',mapping:'date',type:'string'},
		          {name:'hotel',mapping:'hotel',type:'string'},
		          {name:'city',mapping:'city',type:'string'},
		          {name:'place810',mapping:'place810',type:'string'},
		          {name:'place101230',mapping:'place101230',type:'string'},
		          {name:'place1230130',mapping:'place1230130',type:'string'},
		          {name:'place130530',mapping:'place130530',type:'string'},
		          {name:'place5308',mapping:'place5308',type:'string'}
		          ]
		
	});

	keyStore = Ext.create('Ext.data.Store', {
		id : 'keyStoreId',
		name : 'keyStoreName',
		model : 'keywordModel',
		proxy : {
			type : 'ajax',
			url :contextPath+'/etourism/viewSchedule.do',
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
	keyStore.load();
	
	
	
	
	var keyGrid = Ext.create('Ext.grid.Panel', {
		title:'Time Schedule Output',
		forceFit : true,
		id : 'keyGrid',
		store : keyStore,
		columns : keycolumns,
		width:1200,
		height:300,
		autoFit : true,
		autoscroll:true,
		stripRows:true,
		renderTo : 'keyContainer',
		collapsible:true,
		overflowY:'auto'
	});

});
	
	
	
