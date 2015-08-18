var ioc={
		
	config : {
            type : "org.nutz.ioc.impl.PropertiesProxy",
            fields : {
                    paths : ["myapp.properties"]
            }
    },

    dataSource : {

        type:"org.apache.commons.dbcp.BasicDataSource",
        singleton:true,
        events:
        {
            depose:'close'
        },

        fields : 
        {
           driverClassName:'com.mysql.jdbc.Driver',
           url:'jdbc:mysql://123.57.91.98/vmall?useUnicode=true&amp;characterEncoding=utf-8',
           username:'root',
           password:'!tiangou2015'
        }
    },
    
	dao : {
		type : "com.skynet.framework.dao.SkynetDao",
		args : [ {
			refer : "dataSource"
		} ]
	},
	
	log : {
		type : 'org.nutz.aop.interceptor.LoggingMethodInterceptor'
	},
	
	txNONE : {
        type : 'org.nutz.aop.interceptor.TransactionInterceptor',
        args : [0]
    },
    txREAD_UNCOMMITTED : {
        type : 'org.nutz.aop.interceptor.TransactionInterceptor',
        args : [1]
    },
    txREAD_COMMITTED : {
        type : 'org.nutz.aop.interceptor.TransactionInterceptor',
        args : [2]
    },
    txREPEATABLE_READ : {
        type : 'org.nutz.aop.interceptor.TransactionInterceptor',
        args : [4]
    },
    txSERIALIZABLE : {
        type : 'org.nutz.aop.interceptor.TransactionInterceptor',
        args : [8]
    },
    
    $aop : 
	{
	    type : 'org.nutz.ioc.aop.config.impl.ComboAopConfigration',
	    fields : 
	    {
	        aopConfigrations  : 
	        [
	            {	
	            	type : 'org.nutz.ioc.aop.config.impl.JsonAopConfigration',
	                fields : 
	                {
	                    itemList : 
	                    [
	                     	['com\\.skynet\\..+\\.service','.+','ioc:txREAD_COMMITTED']
	                   	]
	                }
	            },
	            {	type : 'org.nutz.ioc.aop.config.impl.AnnotationAopConfigration' }
	        ]
	    }
	}
}
