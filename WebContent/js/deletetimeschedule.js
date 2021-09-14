Ext.require([
    'Ext.grid.*',
    'Ext.data.*',
    'Ext.dd.*'
]);

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

var keyStore = Ext.create('Ext.data.Store', {
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
					
		}
	},
	autoLoad : true
});
keyStore.load();
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
Ext.onReady(function(){
	
	hideConfirmationMsg();
	
	

    

    // Column Model shortcut array
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
         			}];

    // declare the source Grid
    var firstGrid = Ext.create('Ext.grid.Panel', {
        multiSelect: true,
        viewConfig: {
            plugins: {
                ptype: 'gridviewdragdrop',
                dragGroup: 'firstGridDDGroup',
                dropGroup: 'secondGridDDGroup'
            },
            listeners: {
                drop: function(node, data, dropRec, dropPosition) {
                    var dropOn = dropRec ? ' ' + dropPosition + ' ' + dropRec.get('TABLENAME') : ' on empty view';
                }
            }
        },
        store            : keyStore,
        columns          : keycolumns,
        stripeRows       : true,
        title            : 'List of Schedules',
        margins          : '0 2 0 0'
    });

    var secondGridStore = Ext.create('Ext.data.Store', {
        model: 'keywordModel'
    });

    // create the destination Grid
    var secondGrid = Ext.create('Ext.grid.Panel', {
    	id:'secondgrid',
        viewConfig: {
            plugins: {
                ptype: 'gridviewdragdrop',
                dragGroup: 'secondGridDDGroup',
                dropGroup: 'firstGridDDGroup'
            },
            listeners: {
                drop: function(node, data, dropRec, dropPosition) {
                    var dropOn = dropRec ? ' ' + dropPosition + ' ' + dropRec.get('TABLENAME') : ' on empty view';
                }
            }
        },
        store            : secondGridStore,
        columns          : keycolumns,
        stripeRows       : true,
        title            : 'Schedule to Be Removed',
        margins          : '0 0 0 3'
    });

    //Simple 'border layout' panel to house both grids
    var displayPanel = Ext.create('Ext.Panel', {
        width        : 650,
        height       : 300,
        layout       : {
            type: 'hbox',
            align: 'stretch',
            padding: 5
        },
        renderTo     : 'webSiteContainer',
        defaults     : { flex : 1 }, //auto stretch
        items        : [
            firstGrid,
            secondGrid
        ],
        dockedItems: {
            xtype: 'toolbar',
            dock: 'bottom',
            items: ['->', // Fill
            {
                text: 'Reset both grids',
                handler: function(){
                    //refresh source grid
                    keyStore.load();

                    //purge destination grid
                    secondGridStore.removeAll();
                }
            },
            {
            	
            	text:'Delete Schedule',
            	handler:function()
            	{
            		var dataToSubmit=generateJSONRequestForDeleteData();
        			var urlLink = contextPath + '/etourism/deleteSchedule.do';
            		doDeleteTableSubmissionRequest(dataToSubmit, urlLink);
            	}
            	
            	
            }
            
            ]
        }
    });
    
    function doDeleteTableSubmissionRequest(nameOfTables, urlLink) {
		var loadMask = new Ext.LoadMask(Ext.getBody(), {
			msg : "Loading"
		});
		loadMask.show();
		Ext.Ajax.request( {
			method : 'POST',
			processData : false,
			contentType : 'application/json',
			jsonData : Ext.encode(nameOfTables),
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
							displayPanel.hide();
						}
						
					} else {
						var value = JsonData.message;
						showConfirmationMsg(value);
						displayPanel.hide();
						}
					
					loadMask.hide();
				}
			},
			failure : function(data) {
				loadMask.hide();
			}
		});
	}
    
    
    function generateJSONRequestForDeleteData() {
		var deleteData = {};
		var storeObj = Ext.getCmp('secondgrid').getStore();
		var count = storeObj.getCount();

		if (count == 0) {
			alert("Please Select the Schedules to be Deleted");
		}
		var webSubArray =new Array();

		for (i = 0; i < count; i++) {
			webSubArray.push(storeObj.getAt(i).get('timeScheduleId'));
		}
		deleteData.timeScheduleId=webSubArray;
		return deleteData;
	}
    
});