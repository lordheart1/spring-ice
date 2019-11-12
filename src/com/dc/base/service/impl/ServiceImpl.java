package com.dc.base.service.impl;


import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dc.util.jndi.LocalContext;
import com.zeroc.Ice.Communicator;
import com.zeroc.Ice.ObjectAdapter;
import com.zeroc.IceBox.Service;
import com.zeroc.Ice.Util;

public class ServiceImpl implements Service {
	
	  private static final Logger logger = Logger.getLogger(ServiceImpl.class);
	  private ObjectAdapter _adapter;
	  
	  public ServiceImpl() {
		  
		  new LocalContext().init();
	  }

	  public void start(String name, Communicator communicator, String[] args)
	  {
	    logger.info("name=" + name);

	    for (int i = 0; i < args.length; i++) {
	      logger.info("args[" + i + "]=" + args[i]);
	    }
	    
	    ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	    logger.debug("context:" + context);
	    com.zeroc.Ice.Object object = (com.zeroc.Ice.Object)context.getBean(args[0]);

	    this._adapter = communicator.createObjectAdapter(name);
	
	    this._adapter.add(object, Util.stringToIdentity(name));
	    this._adapter.activate();
	      logger.info("===================== 启动完毕！！ ==============================");
	  }

	  public void stop() {
	    this._adapter.deactivate();
	  }
}
