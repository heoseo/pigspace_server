package com.pigspace.common.support;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LogObject implements Serializable {

	private static final long serialVersionUID = -8886982931525359327L;

	private transient Logger logger;
	protected final String loggerName;

	protected LogObject(){
		this.loggerName = getClass().getName();
	}
	protected LogObject(String loggerName){
		this.loggerName=loggerName;
	}

	public void error(String message){
		initLogger();
		logger.error(message);
	}
	public void error(String message, Object... objects){
		initLogger();
		logger.error(message, objects);
	}
	public void error(String message, Throwable t) {
		initLogger();
		logger.error(message, t);
	}

	public void debug(String message){
		initLogger();
		logger.debug(message);
	}
	public void debug(String message, Object... objects){
		initLogger();
		logger.debug(message, objects);
	}

	public void info(String message){
		initLogger();
		logger.info(message);
	}
	public void info(String message, Object... objects){
		initLogger();
		logger.info(message, objects);
	}

	private void initLogger(){
		if(logger == null){
			logger=LoggerFactory.getLogger(loggerName);
		}
	}
}