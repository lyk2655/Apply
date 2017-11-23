package com.ipin.service.rest.errorhandling;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.ipin.service.rest.constants.ErrorCode;


@XmlRootElement
public class ErrorMessage {

    /**
     * application specific error code
     */
    @XmlElement(name = "error_code")
    int code;

    /**
     * message describing the error
     */
    @XmlElement(name = "error")
    String message;


    /**
     * extra information that might useful for developers
     */
    @XmlElement(name = "detail")
    String developerMessage;
    
    public ErrorMessage() {
    }
    
    public ErrorMessage(ErrorCode errorCode, String developerMessage) {
    	this(errorCode.getCode(), errorCode.getDescription(), developerMessage);
    }

    public ErrorMessage(int code, String message, String developerMessage) {
		super();
		this.code = code;
		this.message = message;
		this.developerMessage = developerMessage;
	}
    
	public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDeveloperMessage() {
        return developerMessage;
    }

    public void setDeveloperMessage(String developerMessage) {
        this.developerMessage = developerMessage;
    }
    
}
