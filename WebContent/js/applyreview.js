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
			name : 'packId',
			mapping : 'packId',
			type : 'int'
		}, {
			name : 'packName',
			mapping : 'packName',
			type : 'string'
		}]

	});


	packStore = Ext.create('Ext.data.Store', {
		id : 'packStoreId',
		name : 'packStoreName',
		model : 'packModel',
		proxy : {
			type : 'ajax',
			url : contextPath + '/etourism/allPackInfo.do',
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
		title: 'Apply the Review',
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
						fieldLabel : 'Package Names',
						allowBlank : false,
						id : 'packId',
						name : 'packId',
						displayField:'packName',
						valueField:'packId',
						store:packStore,
						triggerAction:'all'
				},
				{
					xtype : 'textarea',
					fieldLabel : 'Review Details',
					allowBlank : false,
					id : 'reviewDetails',
					name : 'reviewDetails',
					blankMsg:'Please enter Review Details',
					maxLength:140,
					minLength:10,
					maxLengthText:'Maximum Number of Allowed Characters are 40',
					minLengthText:'Minimum Number of Allowed Characters are 10',
					enforceMaxLength:true
				},
				{
					xtype:'button',
				    text: 'Save Review',
					id: 'Save',
					disabled: false,
			        handler: function (store,btn, args) {
						
							var reviewFormat=generateJSONRequestForReview();
							urlLink=contextPath+'/etourism/storeReview.do';
							hideConfirmationMsg();
							doJSONRequestForReview(reviewFormat, urlLink);
			            }
				}
				],
				renderTo:'contentPanel'
    });
	
	function generateJSONRequestForReview()
	{
		var reviewFormat={};
		var packId=Ext.getCmp('packId').getValue();
		if(packId!=null)
		{
			reviewFormat.packId=packId;
		}
		
		var reviewDetails=Ext.getCmp('reviewDetails').getValue();
		if(reviewDetails!=null)
		{
			reviewFormat.reviewDetails=reviewDetails;
		}
		return reviewFormat;
	}
	
	function doJSONRequestForReview(roleFormat, urlLink)
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
