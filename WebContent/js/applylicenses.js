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
		}		
		]
	});

	keyStore = Ext.create('Ext.data.Store', {
		id : 'keyStoreId',
		name : 'keyStoreName',
		model : 'keywordModel',
		proxy : {
			type : 'ajax',
			url : contextPath + '/book/viewUsers.do',
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
	
	var licensePanel = Ext
	.create(
			'Ext.form.Panel',
			{
				id : 'licensePanel',
				height : 420,
				width : 400,
				margin:'25 25 25 25',
				style : {
					border : 0
				},
				title:'Apply Licenses',
				layout: {
				type: 'table',
				columns: 1
				},
				items : [
					{
						xtype : 'combo',
						labelAlign : 'top',
						fieldLabel : 'User Name',
						id : 'loginId',
						name : 'loginId',
						queryMode : 'local',
						displayField : 'loginId',
						valueField : 'loginId',
						triggerAction : 'all',
						store : keyStore,
						listeners : {	
							'select' : function(combo,records) {
						
									
								}
							}
					},{
						xtype : 'checkboxfield',
						boxLabel:'SPORTS',
						boxLabelAlign:'before',
						name:'SPORTS',
						id:'SPORTS'
		 			},{
						xtype : 'checkboxfield',
						boxLabel:'POLITICS',
						boxLabelAlign:'before',
						name:'POLITICS',
						id:'POLITICS'
		 			},{
						xtype : 'checkboxfield',
						boxLabel:'FLIMFARE',
						boxLabelAlign:'before',
						name:'FLIMFARE',
						id:'FLIMFARE'
		 			},{
						xtype : 'checkboxfield',
						boxLabel:'ANALYTICS',
						boxLabelAlign:'before',
						name:'ANALYTICS',
						id:'ANALYTICS'
		 			},{
						xtype : 'checkboxfield',
						boxLabel:'PROGRAMMING',
						boxLabelAlign:'before',
						name:'PROGRAMMING',
						id:'PROGRAMMING'
		 			},{
						xtype : 'checkboxfield',
						boxLabel:'GREETINGS',
						boxLabelAlign:'before',
						name:'GREETINGS',
						id:'GREETINGS'
		 			},{
						xtype : 'checkboxfield',
						boxLabel:'BUDGETSET',
						boxLabelAlign:'before',
						name:'BUDGETSET',
						id:'BUDGETSET'
		 			},
					
						{
							xtype : 'button',
							text : 'Save License',
							id : 'Save',
							handler : function(store, btn, args) {

								var jsonReqForLicense = generateJSONRequestForLicense();
								var urlLink = contextPath + '/book/applyLicense.do';
								hideConfirmationMsg();
								doJSONRequestForLicense(
										jsonReqForLicense,
										urlLink);
							}
						}

				],
				renderTo : 'licenseContainer'
			});
	
	function generateJSONRequestForLicense() {
		
		var licenseFormat = {};
		
		var loginId=Ext.getCmp('loginId').getValue();
		licenseFormat.loginId=loginId;
		
		var SPORTS=Ext.getCmp('SPORTS').getValue();
		licenseFormat.sports=SPORTS;
		
		var POLITICS=Ext.getCmp('POLITICS').getValue();
		licenseFormat.politics=POLITICS;
		
		var FLIMFARE=Ext.getCmp('FLIMFARE').getValue();
		licenseFormat.flimfare=FLIMFARE;
		
		var ANALYTICS=Ext.getCmp('ANALYTICS').getValue();
		licenseFormat.analytics=ANALYTICS;
		
		var PROGRAMMING=Ext.getCmp('PROGRAMMING').getValue();
		licenseFormat.programming=PROGRAMMING;
		
		var GREETINGS=Ext.getCmp('GREETINGS').getValue();
		licenseFormat.greetings=GREETINGS;
		
		
		var BUDGETSET=Ext.getCmp('BUDGETSET').getValue();
		licenseFormat.budgetset=BUDGETSET;
		
	
		return licenseFormat;
	};
	

	function doJSONRequestForLicense(jsonReqForLicense, urlLink) {
		var loadMask = new Ext.LoadMask(Ext.getBody(), {
			msg : "Loading"
		});
		loadMask.show();
		Ext.Ajax.request( {
			method : 'POST',
			processData : false,
			contentType : 'application/json',
			jsonData : Ext.encode(jsonReqForLicense),
			url : urlLink,
			success : function(response) {
				var data;
				if (response) {

					var JsonData = Ext.decode(response.responseText);
					if (JsonData.ebErrors != null) {
						var errorObj = JsonData.ebErrors;
						for (i = 0; i < errorObj.length; i++) {
							var value = errorObj[i].errMessage;
							showConfirmationMsg(value);
						}
						loadMask.hide();
					} else {
						var value = JsonData.message;
						showConfirmationMsg(value);
						licensePanel.hide();
						loadMask.hide();
					}
				}
			},
			failure : function(data) {
				loadMask.hide();
			}
		});
	}


	

});
