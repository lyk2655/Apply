package com.ipin.service.rest.errorhandling;

import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.ipin.service.rest.constants.ErrorCode;

/**
 * InternalServerErrorException.
 * 服务内部错误.即内部非自定义的应用级错误
 * 
 * @author zhongyongsheng
 *
 */
@Provider
public class InternalServerErrorExceptionMapper implements ExceptionMapper<InternalServerErrorException>{

	@Override
	public Response toResponse(InternalServerErrorException ex) {
		return Response.status(ex.getResponse().getStatus())
                .entity(new ErrorMessage(ErrorCode.SYSTEM_ERROR, ex.getMessage()))
                .type(MediaType.APPLICATION_JSON) //this has to be set to get the generated JSON
                .build();
	}

}
