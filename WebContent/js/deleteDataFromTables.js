Ext.require([
    'Ext.grid.*',
    'Ext.data.*',
    'Ext.dd.*'
]);

Ext.define('DataObject', {
    extend: 'Ext.data.Model',
    fields: ['TABLENAME']
});

Ext.onReady(function(){
	
	
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

    var myData = [
        { TABLENAME : "BESTWEB" },
        { TABLENAME : "CLEANWEBDATA" },
        { TABLENAME : "FEATUREVECTOR"},
        { TABLENAME : "FREQUENCY"},
        { TABLENAME : "TOKENS"},
        { TABLENAME : "WEBDATA"}
    ];

    // create the data store
    var firstGridStore = Ext.create('Ext.data.Store', {
        model: 'DataObject',
        data: myData
    });


    // Column Model shortcut array
    var columns = [
        {text: "TABLE NAME", flex: 1, sortable: true, dataIndex: 'TABLENAME'},
    ];

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
        store            : firstGridStore,
        columns          : columns,
        stripeRows       : true,
        title            : 'List of Tables',
        margins          : '0 2 0 0'
    });

    var secondGridStore = Ext.create('Ext.data.Store', {
        model: 'DataObject'
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
        columns          : columns,
        stripeRows       : true,
        title            : 'Tables to Be Deleted',
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
                    firstGridStore.loadData(myData);

                    //purge destination grid
                    secondGridStore.removeAll();
                }
            },
            {
            	
            	text:'Delete Tables',
            	handler:function()
            	{
            		var dataToSubmit=generateJSONRequestForDeleteData();
        			var urlLink = contextPath + '/rankweb/deleteTables.do';
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
						loadMask.hide();
					} else {
						var value = JsonData.message;
						showConfirmationMsg(value);
						displayPanel.hide();
						loadMask.hide();
					}
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
			alert("Please Select the Tables to be Deleted");
		}
		var webSubArray =new Array();

		for (i = 0; i < count; i++) {
			webSubArray.push(storeObj.getAt(i).get('TABLENAME'));
		}
		deleteData.tablesFromWhichDataToBeDeleted=webSubArray;
		return deleteData;
	}
    
});