Ext.require( [ 'Ext.grid.*', 'Ext.data.*', 'Ext.form.*',
		'Ext.layout.container.Column', 'Ext.tab.Panel' ]);
Ext.Loader.setConfig( {
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
var packStore;
Ext.onReady(function() {
	
	hideConfirmationMsg();
	
	var loadMask = new Ext.LoadMask(Ext.getBody(), {msg:"Loading"});
	

	Ext.define('packModel', {
		extend : 'Ext.data.Model',
		fields : [ {
			name : 'bookId',
			mapping : 'bookId',
			type : 'int'
		}, {
			name : 'bookName',
			mapping : 'bookName',
			type : 'string'
		}]

	});


	packStore = Ext.create('Ext.data.Store', {
		id : 'packStoreId',
		name : 'packStoreName',
		model : 'packModel',
		proxy : {
			type : 'ajax',
			url : contextPath + '/book/books.do',
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

			}}//Write Logic to display error message
	});
	
	packStore.load();

	var contentPanel = Ext.create('Ext.form.Panel', {
		collapsible:true,
		title: 'Apply the Rating for Books',
		width: 1000,
		height: 300, 
		autoScroll: true,	
		defaults: {
			padding: '15 100 15 100',
			labelAlign: 'top'
		},
        layout: {
			type: 'table',
			columns: 1
		},
		items:[
				{
						xtype : 'combo',
						fieldLabel : 'Book Name',
						allowBlank : false,
						id : 'bookId',
						name : 'bookId',
						displayField:'bookName',
						valueField:'bookId',
						store:packStore,
						triggerAction:'all'
				},
				{
					xtype : 'numberfield',
					fieldLabel : 'Rating',
					allowBlank : false,
					id : 'rating',
					name : 'rating',
					value : 1,
					maxValue : 5,
					minValue : 1
				},
				{
					xtype:'button',
				    text: 'Apply Rating',
					id: 'Save',
					disabled: false,
			        handler: function (store,btn, args) {
						
							var ratingFormat=generateJSONRequestForDirectRating();
							urlLink=contextPath+'/book/storeRating.do';
							hideConfirmationMsg();
							doJSONRequestForDirectRating(ratingFormat, urlLink);
			            }
				}
				],
				renderTo:'contentPanel'
    });
	
	function generateJSONRequestForDirectRating()
	{
		var directRatingFormat={};
		var bookId=Ext.getCmp('bookId').getValue();
		if(bookId!=null)
		{
			directRatingFormat.bookId=bookId;
		}
		var rating=Ext.getCmp('rating').getValue();
		if(rating!=null)
		{
			directRatingFormat.rating=rating;
		}
		
		return directRatingFormat;
	}
	
	function doJSONRequestForDirectRating(roleFormat, urlLink)
	{
			loadMask.show();
			Ext.Ajax.request({	
			method: 'POST',
			processData: false,
			contentType:'application/json',
			jsonData: Ext.encode(roleFormat),
			url:urlLink, 
			success: function(response) {
			var data;
			if (response){
						 
						var JsonData = Ext.decode(response.responseText);
							if(JsonData.ebErrors != null){
								var errorObj=JsonData.ebErrors;
								var value=errorObj[0].msg;
								alert(value);
										showConfirmationMsg(value);
										contentPanel.hide();
								
								loadMask.hide();
							}
							else
							{
								var value=JsonData.message;
								showConfirmationMsg(value);
								contentPanel.hide();
								loadMask.hide();
							}
						}
			},
		failure : function(data) {
			contentPanel.hide();
			loadMask.hide();
		}
		});
	}

	
});
