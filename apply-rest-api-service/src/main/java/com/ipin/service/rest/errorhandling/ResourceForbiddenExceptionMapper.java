package com.ipin.service.rest.errorhandling;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.ipin.service.rest.beans.NullBeanResult;

/**
 * ResourceForbiddenExceptionMapper.
 * 
 * @author zhongyongsheng
 *
 */
@Provider
public class ResourceForbiddenExceptionMapper implements ExceptionMapper<ResourceForbiddenException>{

	@Override
	public Response toResponse(ResourceForbiddenException exception) {
		return Response.status(Response.Status.OK)
				.entity(new NullBeanResult())
				.type(MediaType.APPLICATION_JSON)
				.build();
	}

}
