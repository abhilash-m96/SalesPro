Ext.require(['Ext.grid.*', 'Ext.data.*', 'Ext.form.*', 'Ext.layout.container.Column', 'Ext.tab.Panel']);
Ext.Loader.setConfig({
    enabled: true
});

Ext.define('keywordModel',{
		extend : 'Ext.data.Model',
		fields : [ 
		           {name:'noOfUsers',mapping:'noOfUsers',type:'int'},
		           {name:'catName',mapping:'catName',type:'string'}
		          ]
		
	});

Ext.onReady(function () {


var keyStore = Ext.create('Ext.data.Store', {
		id : 'keyStoreId',
		name : 'keyStoreName',
		model : 'keywordModel',
		proxy : {
			type : 'ajax',
			url :contextPath+'/book/viewCountsForClassifier.do',
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

Ext.create('Ext.chart.Chart', {
    renderTo: Ext.getBody(),
    width: 500,
    height: 300,
    animate: true,
    store: keyStore,
    axes: [{
        type: 'Numeric',
        position: 'bottom',
        fields: ['noOfUsers'],
        label: {
            renderer: Ext.util.Format.numberRenderer('0,0')
        },
        title: 'Number of Users',
        grid: true,
        minimum: 0
    }, {
        type: 'Category',
        position: 'left',
        fields: ['catName'],
        title: 'Category'
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
            this.setTitle(storeItem.get('catName') + ': ' + storeItem.get('noOfUsers') + 'Users');
          }
        },
        label: {
          display: 'insideEnd',
            field: 'data',
            renderer: Ext.util.Format.numberRenderer('0'),
            orientation: 'horizontal',
            color: '#333',
            'text-anchor': 'middle'
        },
	 xField: 'catName',
        yField: 'noOfUsers'
	}]
});

});