package com.dc.base.ice.spring.balanc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProxyStringFactory {

	private static Map<String,Long> TIME_CONFIG = new HashMap<String,Long>();
	private static BalanceMap BALANCE_MAP;
	
	protected long getTimes(String instanceName) {
		
		Long time = TIME_CONFIG.get(instanceName);
		
		if(time != null) {
			
			synchronized(time) {
				
				time ++;
				TIME_CONFIG.put(instanceName, time);
			}
		}
		
		synchronized (TIME_CONFIG) {
			
			time = new Long(0);
			TIME_CONFIG.put(instanceName, time);
		}
		
		return time;
	}
	
	public String getProxyString(String instanceName) {
		
		long time = this.getTimes(instanceName);
		
		List<String> proxyStrings = BALANCE_MAP.getProxyStrings(instanceName);
		
		if(proxyStrings == null) {
			
			return null;
		}
		
		long length = (long)proxyStrings.size();
		
		long index = time % length;
		
		return proxyStrings.get((int)index);
	}
}
