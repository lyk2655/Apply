package com.ipin.service.rest.errorhandling;

import com.ipin.service.rest.constants.ErrorCode;

/**
 * IllegalParameterException.
 * 非法参数.
 * 
 * @author zhongyongsheng
 *
 */
public class IllegalParameterException extends AppException{
	
	public IllegalParameterException() {
		this(null);
	}
	
	public IllegalParameterException(String detail) {
		super(ErrorCode.PARAM_ILLEGAL, detail);
	}

}
