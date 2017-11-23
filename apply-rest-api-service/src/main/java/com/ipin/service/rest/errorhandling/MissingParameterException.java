package com.ipin.service.rest.errorhandling;

import com.ipin.service.rest.constants.ErrorCode;

/**
 * MissingParameterException.
 * 缺失参数
 * 
 * @author zhongyongsheng
 *
 */
public class MissingParameterException extends AppException {

	public MissingParameterException() {
		this(null);
	}

	public MissingParameterException(String detail) {
		super(ErrorCode.PARAM_REQUIRE, detail);
	}

}
