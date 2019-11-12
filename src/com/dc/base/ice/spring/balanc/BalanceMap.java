package com.dc.base.ice.spring.balanc;

import java.util.List;
import java.util.Map;

public class BalanceMap {

	public Map<String,List<String>> map;
	
	public List<String> getProxyStrings(String instanceName) {
		
		return map.get(instanceName);
	}
}
