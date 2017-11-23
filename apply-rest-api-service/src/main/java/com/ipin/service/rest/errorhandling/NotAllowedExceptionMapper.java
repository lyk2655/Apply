package com.ipin.service.rest.errorhandling;

import javax.ws.rs.NotAllowedException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.ipin.service.rest.constants.ErrorCode;

/**
 * NotAllowedExceptionMapper.
 * 
 * @author zhongyongsheng
 *
 */
@Provider
public class NotAllowedExceptionMapper implements ExceptionMapper<NotAllowedException>{

	@Override
	public Response toResponse(NotAllowedException ex) {
        return Response.status(ex.getResponse().getStatus())
                .entity(new ErrorMessage(ErrorCode.HTTP_METHOD_ERROR, ex.getMessage()))
                .type(MediaType.APPLICATION_JSON) //this has to be set to get the generated JSON
                .build();
	}

}
