package com.ipin.service.rest.errorhandling;

import javax.ws.rs.ServiceUnavailableException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.ipin.service.rest.constants.ErrorCode;

/**
 * ServiceUnavailableExceptionMapper.
 * 服务不可用
 * 
 * @author zhongyongsheng
 *
 */
@Provider
public class ServiceUnavailableExceptionMapper implements ExceptionMapper<ServiceUnavailableException>{

	@Override
	public Response toResponse(ServiceUnavailableException ex) {
        return Response.status(ex.getResponse().getStatus())
                .entity(new ErrorMessage(ErrorCode.SERVICE_UNAVAILABLE, ex.getMessage()))
                .type(MediaType.APPLICATION_JSON) //this has to be set to get the generated JSON
                .build();
	}

}
