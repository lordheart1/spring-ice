package com.dc.base.ice;

import java.lang.reflect.Method;

import javax.annotation.Resource;

import org.springframework.beans.factory.FactoryBean;

import com.zeroc.Ice.Communicator;

public class IceFactory implements FactoryBean<Object> {
	
	private static final String CAST_METHOD = "checkedCast";
	
	@Resource(name = "ice")
	private  Communicator communicator = null;
	
	private String proxyString;
	
	private String proxyClass;

	public Communicator getCommunicator() {
		return communicator;
	}

	public void setCommunicator(Communicator communicator) {
		this.communicator = communicator;
	}

	public String getProxyString() {
		return proxyString;
	}

	public void setProxyString(String proxyString) {
		this.proxyString = proxyString;
	}

	public String getProxyClass() {
		return proxyClass;
	}

	public void setProxyClass(String proxyClass) {
		this.proxyClass = proxyClass;
	}

	@Override
	public Object getObject() throws Exception {
		
		  com.zeroc.Ice.ObjectPrx base = this.communicator.stringToProxy(this.proxyString);
		  
	      Class pClass = Class.forName(this.proxyClass);
	      
	      Method method = pClass.getMethod(CAST_METHOD, new Class[]{com.zeroc.Ice.ObjectPrx.class});
	      
	      Object obj = method.invoke(null, base);
	      
	      return obj;
	}

	@Override
	public Class<?> getObjectType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isSingleton() {
		// TODO Auto-generated method stub
		return false;
	}

	

}
