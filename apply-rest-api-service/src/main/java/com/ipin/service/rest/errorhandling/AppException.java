package com.ipin.service.rest.errorhandling;

import com.ipin.service.rest.constants.ErrorCode;

/**
 * Class to map application related exceptions
 * 应用自定义异常
 * @author amacoder
 */
public class AppException extends RuntimeException {

    private static final long serialVersionUID = -8999932578270387947L;

    /**
     * application specific error code
     */
    private int code;
    
    private String detail;
    
    public AppException(ErrorCode errorCode, String detail) {
    	this(errorCode.getCode(), errorCode.getDescription(), detail);
    }

    /**
     *
     * @param code
     * @param message
     */
    public AppException(int code, String message, String detail) {
        super(message);
        this.code = code;
        this.detail = detail;
    }

    public AppException() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

}
