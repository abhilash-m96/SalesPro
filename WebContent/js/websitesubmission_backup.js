Ext.require( [ 'Ext.grid.*', 'Ext.data.*', 'Ext.form.*',
		'Ext.layout.container.Column', 'Ext.tab.Panel' ]);

Ext.Loader.setConfig( {
	enabled : true
});

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

Ext
		.onReady(function() {

			Ext.define('webSiteSubmissionModel', {
				extend : 'Ext.data.Model',
				fields : [ {
					name : 'webSiteUrl',
					mapping : 'webSiteUrl',
					type : 'string'
				} ]
			});

			var cellEditing = Ext.create('Ext.grid.plugin.CellEditing', {
				clicksToEdit : 1
			});

			var webSiteColumns = [ {
				header : 'Web Site Url',
				dataIndex : 'webSiteUrl',
				sortable : true,
				editor : {
					xtype : 'textfield'
				},
				width : 250
			} ];

			var webSiteSubmissionStore = Ext.create('Ext.data.ArrayStore', {
				storeId : 'webSiteStoreId',
				model : 'webSiteSubmissionModel'
			});

			var webSiteSubmissionGridPanel = Ext.create('Ext.grid.Panel', {
				collapsible : true,
				title : 'Enter the Web Site Urls Information',
				forceFit : true,
				id : 'webSiteSubmissionGridId',
				store : webSiteSubmissionStore,
				columns : webSiteColumns,
				height : 400,
				width : 400,
				plugins : Ext.create('Ext.grid.plugin.CellEditing', {
					clicksToEdit : 1
				}),
				autoFit : true,
				stripRows : true
			});

			var webSitesSubmissionPanel = Ext
					.create(
							'Ext.form.Panel',
							{
								id : 'webSitesSubmissionPanel',
								height : 480,
								width : 400,
								margin:'5 5 5 5',
								style : {
									border : 0
								},
								items : [
										webSiteSubmissionGridPanel,
										{
											width : 200,
											height : 40,
											xtype : 'button',
											text : 'Add Web URL',
											id : 'webUrlBtn',
											handler : function(btn, args) {
												var store = Ext
														.getCmp(
																'webSiteSubmissionGridId')
														.getStore();
												var r = Ext
														.create(
																'webSiteSubmissionModel',
																{
																	'webSiteUrl' : ''
																});
												store.insert(0, r);
												store.commitChanges();
											}
										},
										{
											xtype : 'button',
											text : 'Submit Web Sites',
											id : 'Save',
											handler : function(store, btn, args) {

												var webSiteSubmissionGenFormat = generateJSONRequestForWebSiteSubmission();
												var urlLink = contextPath + '/rankweb/webSubmissionDynamic.do';
												hideConfirmationMsg();
												doWebSiteSubmissionRequest(
														webSiteSubmissionGenFormat,
														urlLink);
											},
											width:200,
											height:40
										}

								],
								renderTo : 'webSiteContainer'
							});

			function doWebSiteSubmissionRequest(webSiteSubmissionGenFormat, urlLink) {
				var loadMask = new Ext.LoadMask(Ext.getBody(), {
					msg : "Loading"
				});
				loadMask.show();
				Ext.Ajax.request( {
					method : 'POST',
					processData : false,
					contentType : 'application/json',
					jsonData : Ext.encode(webSiteSubmissionGenFormat),
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
								webSitesSubmissionPanel.hide();
								loadMask.hide();
							}
						}
					},
					failure : function(data) {
						loadMask.hide();
					}
				});
			}

			function generateJSONRequestForWebSiteSubmission() {
				var webSiteSubmissionGenFormat = {};

				var storeObj = Ext.getCmp('webSiteSubmissionGridId').getStore();

				var count = storeObj.getCount();

				if (count == 0) {
					alert("Please Enter the Web Site URLS");
				}
				var webSubArray =new Array();

				for (i = 0; i < count; i++) {
					webSubArray.push(storeObj.getAt(i).get('webSiteUrl'));
				}
				
				webSiteSubmissionGenFormat.webSitesUrl=webSubArray;

				return webSiteSubmissionGenFormat;
			}

		});