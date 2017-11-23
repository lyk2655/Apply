package com.ipin.service.rest.utils;

import org.apache.thrift.TException;
import org.slf4j.Logger;

import com.ipin.service.rest.constants.ErrorCode;
import com.ipin.service.rest.errorhandling.AppException;
import com.ipin.thrift.edu.exception.EduException;

/**
 * LoggerUtils.
 * 日志帮助工具
 * 
 * @author zhongyongsheng
 *
 */
public class LoggerUtils {
	
	/**
	 * 打印EduException异常信息和抛出AppException
	 * @param logger
	 * @param e
	 */
	public static void logEduTExceptionAndThrowAppException(Logger logger, TException e) {
		if(e instanceof EduException) {
			EduException eduException = (EduException)e;
			logger.info("EduException msgId : " +  eduException.getMsgId() + ", customMsgId : " + eduException.getCustomMsgId() + " content : " + eduException.getMsgContent(), e);
			throw new AppException(ErrorCode.SYSTEM_ERROR, eduException.getMsgContent());
		} else {
			logTExceptionAndThrowAppException(logger, e);
		}
		
	}
	
	/**
	 * 打印TException异常信息和抛出AppException
	 * @param logger
	 * @param e
	 */
	public static void logTExceptionAndThrowAppException(Logger logger, TException e) {
		logger.info("TException msgId : " + e.getMessage() , e);
		throw new AppException(ErrorCode.SYSTEM_ERROR, e.getMessage());
	}

}
