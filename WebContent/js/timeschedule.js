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

Ext.define('cityModel', {
	extend : 'Ext.data.Model',
	fields : [ {
		name : 'cityId',
		mapping : 'cityId',
		type : 'int'
	}, {
		name : 'cityName',
		mapping : 'cityName',
		type : 'string'
	} ],
	idProperty : 'cityId'
});

var cityStore = Ext.create('Ext.data.Store', {
	id : 'cityIdStoreId',
	name : 'cityIdStoreName',
	model : 'cityModel',
	proxy : {
		type : 'ajax',
		url : contextPath + '/etourism/viewPlaces.do',
		actionMethods : {
			read : 'POST'
		},
		reader : {
			type : 'json',
			root : 'model',
			totalProperty : 'totalSize'
		}
	},
	listeners : {
		'load' : function(store, records) {
		}
	},
	autoLoad : true
});
cityStore.load();

/* */
Ext.define('hotelModel', {
	extend : 'Ext.data.Model',
	fields : [ {
		name : 'hotelId',
		mapping : 'hotelId',
		type : 'int'
	}, {
		name : 'hotelName',
		mapping : 'hotelName',
		type : 'string'
	} ],
	idProperty : 'hotelId'
});

var hotelStore = Ext.create('Ext.data.Store', {
	id : 'hotelModelStoreId',
	name : 'hotelModelStoreName',
	model : 'hotelModel',
	proxy : {
		type : 'ajax',
		url : contextPath + '/etourism/viewHotels.do',
		actionMethods : {
			read : 'POST'
		},
		reader : {
			type : 'json',
			root : 'model',
			totalProperty : 'totalSize'
		},
		params : {
			packId : ''
		}
	},
	listeners : {
		'load' : function(store, records) {
		}
	},
	autoLoad : true
});
hotelStore.load();

Ext.define('spotModel', {
	extend : 'Ext.data.Model',
	fields : [ {
		name : 'spotId',
		mapping : 'spotId',
		type : 'int'
	}, {
		name : 'spotName',
		mapping : 'spotName',
		type : 'string'
	} ],
	idProperty : 'spotId'
});

var spotStore = Ext.create('Ext.data.Store', {
	id : 'spotModelStoreId',
	name : 'spotModelStoreName',
	model : 'spotModel',
	proxy : {
		type : 'ajax',
		url : contextPath + '/etourism/viewSpots.do',
		actionMethods : {
			read : 'POST'
		},
		reader : {
			type : 'json',
			root : 'model',
			totalProperty : 'totalSize'
		},
		params : {
			packId : ''
		}
	},
	listeners : {
		'load' : function(store, records) {
		}
	},
	autoLoad : true
});
spotStore.load();

Ext
		.onReady(function() {
			
			hideConfirmationMsg();

			var contentPanel = Ext
					.create(
							'Ext.form.Panel',
							{
								width : 1000,
								height : 300,
								defaults : {
									padding : '15 50 15 50',
									labelAlign : 'top'
								},
								layout : {
									type : 'table',
									columns : 3
								},
								items : [
										{
											xtype : 'combo',
											fieldLabel : 'City',
											id : 'city',
											name : 'city',
											queryMode : 'local',
											editable : false,
											displayField : 'cityName',
											valueField : 'cityId',
											triggerAction : 'all',
											store : cityStore,
											listeners : {
												'select' : function(combo,
														records) {
													var cityIdSelected = Ext
															.getCmp('city')
															.getValue();
													sendSpotTable(cityIdSelected);
													sendHotelTable(cityIdSelected);
												}
											}
										},
										{
											xtype : 'combo',
											fieldLabel : 'Hotel',
											id : 'hotel',
											name : 'hotel',
											queryMode : 'local',
											editable : false,
											displayField : 'hotelName',
											valueField : 'hotelId',
											triggerAction : 'all',
											store : hotelStore,
											listeners : {
												'select' : function(combo,
														records) {
												}
											}
										},
										{
											xtype : 'combo',
											fieldLabel : '8am-10am',
											id : 'PLACE810',
											name : 'PLACE810',
											queryMode : 'local',
											editable : false,
											displayField : 'spotName',
											valueField : 'spotId',
											triggerAction : 'all',
											store : spotStore,
											listeners : {
												'select' : function(combo,
														records) {
												}
											}
										},
										{
											xtype : 'combo',
											fieldLabel : '10am-12:30pm',
											id : 'PLACE101230',
											name : 'PLACE101230',
											queryMode : 'local',
											editable : false,
											displayField : 'spotName',
											valueField : 'spotId',
											triggerAction : 'all',
											store : spotStore,
											listeners : {
												'select' : function(combo,
														records) {
												}
											}
										},
										{
											xtype : 'combo',
											fieldLabel : '12:30pm-1:30',
											id : 'PLACE1230130',
											name : 'PLACE1230130',
											queryMode : 'local',
											editable : false,
											displayField : 'spotName',
											valueField : 'spotId',
											triggerAction : 'all',
											store : spotStore,
											listeners : {
												'select' : function(combo,
														records) {
												}
											}
										},
										{
											xtype : 'combo',
											fieldLabel : '1:30pm-5:30pm',
											id : 'PLACE130530',
											name : 'PLACE130530',
											queryMode : 'local',
											editable : false,
											displayField : 'spotName',
											valueField : 'spotId',
											triggerAction : 'all',
											store : spotStore,
											listeners : {
												'select' : function(combo,
														records) {
												}
											}
										},
										{
											xtype : 'combo',
											fieldLabel : '5:30pm-8pm',
											id : 'PLACE5308',
											name : 'PLACE5308',
											queryMode : 'local',
											editable : false,
											displayField : 'spotName',
											valueField : 'spotId',
											triggerAction : 'all',
											store : spotStore,
											listeners : {
												'select' : function(combo,
														records) {
												}
											}
										},
										{
											xtype:'datefield',
											fieldLabel:'Enter Date Field',
											id:'DATE',
											name:'DATE'
											
										},
										{
											xtype : 'button',
											text : 'Time Schedule',
											width : 220,
											id : 'Save',
											disabled : false,
											handler : function(store, btn, args) {

												var scheduleGenFormat = generateJSONRequestForTimeSchedule();
												urlLink = contextPath + '/etourism/timeSchedule.do';
												hideConfirmationMsg();
												doTopologyGenerationRequest(
														scheduleGenFormat,
														urlLink);
											}
										}, {
											xtype : 'button',
											text : 'Reset',
											width : 150,
											id : 'Reset',
											disabled : false,
											handler : function() {
												contentPanel.getForm().reset();
											}
										} ],
								renderTo : 'contentDiv',
								collapsible : true,
								title : 'Time Schedule Panel'

							});

			function doTopologyGenerationRequest(topologyGenFormat, urlLink) {
				var loadMask = new Ext.LoadMask(Ext.getBody(), {
					msg : "Loading"
				});
				loadMask.show();
				Ext.Ajax.request( {
					method : 'POST',
					processData : false,
					contentType : 'application/json',
					jsonData : Ext.encode(topologyGenFormat),
					url : urlLink,
					success : function(response) {
						var data;
						if (response) {

							var JsonData = Ext.decode(response.responseText);
							if (JsonData.ebErrors != null) {
								var errorObj = JsonData.ebErrors;
								for (i = 0; i < errorObj.length; i++) {
									var value = errorObj[i].msg;
									showConfirmationMsg(value);
								}
								loadMask.hide();
							} else {
								var value = JsonData.message;
								showConfirmationMsg(value);
								contentPanel.hide();
								loadMask.hide();
							}
						}
					},
					failure : function(data) {
						loadMask.hide();
					}
				});
			}

			function generateJSONRequestForTimeSchedule() {
				var scheduleGenFormat = {};
				var city = Ext.getCmp('city').getValue();
				if (city != null) {
					scheduleGenFormat.city = city;
				}
				var hotel = Ext.getCmp('hotel').getValue();
				if (hotel != null) {
					scheduleGenFormat.hotel = hotel;
				}
				var place810 = Ext.getCmp('PLACE810').getValue();
				if (place810 != null) {
					scheduleGenFormat.place810 = place810;
				}
				var place101230 = Ext.getCmp('PLACE101230').getValue();
				if (place101230 != null) {
					scheduleGenFormat.place101230 = place101230;
				}
				var place1230130 = Ext.getCmp('PLACE1230130').getValue();
				if (place1230130 != null) {
					scheduleGenFormat.place1230130 = place1230130;
				}
				var place130530 = Ext.getCmp('PLACE130530').getValue();
				if (place130530 != null) {
					scheduleGenFormat.place130530 = place130530;
				}
				var place5308 = Ext.getCmp('PLACE5308').getValue();
				if (place5308 != null) {
					scheduleGenFormat.place5308 = place5308;
				}
				var date = Ext.getCmp('DATE').getRawValue();
				if (date != null) {
					scheduleGenFormat.date = date;
				}
				return scheduleGenFormat;
			}
			
			
			function sendSpotTable(cityIdSelected)
			{
				spotStore.load(
					{
						url :contextPath+'/etourism/viewSpots.do',
						params:{
									cityId:cityIdSelected
								}
					}
				);
			}
			
			function sendHotelTable(hotelIdSelected)
			{
				hotelStore.load(
					{
						url :contextPath+'/etourism/viewHotels.do',
						params:{
									cityId:hotelIdSelected
								}
					}
				);
			}
			

		});