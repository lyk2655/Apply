package com.ipin.service.rest.errorhandling;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * AppExceptionMapper.
 * @author zhongyongsheng
 *
 */
@Provider
public class AppExceptionMapper implements ExceptionMapper<AppException> {

	public Response toResponse(AppException ex) {
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
				.entity(new ErrorMessage(ex.getCode(), ex.getMessage(), ex.getDetail()))
				.type(MediaType.APPLICATION_JSON)
				.build();
	}

}
