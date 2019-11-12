package com.trace.cloud.test.service.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.kdy.test.TestServcie;
import com.zeroc.Ice.Current;


@Service("iceTest")
public class TestAdapter implements TestServcie {
	
	private static final Logger logger = Logger.getLogger(TestAdapter.class);

	@Override
	public int print(String value, Current current) {
		long time = System.currentTimeMillis();
		
		logger.debug("value:" + value + " " + time);
		
		time = time % 1000;
		
		int result = (int)time;
		return result;
	}

}
