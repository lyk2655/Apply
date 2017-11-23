package com.ipin.service.rest.filters;

import java.io.IOException;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ipin.service.rest.beans.NullBeanResult;

/**
 * NullResultResponseFilter.
 * 当返回结果为null的时候,转换结果
 * 
 * @author zhongyongsheng
 *
 */
@Provider
@Priority(Priorities.ENTITY_CODER)
public class NullResultResponseFilter implements ContainerResponseFilter{
	
	private static final Logger logger = LoggerFactory.getLogger(NullResultResponseFilter.class);

	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
			throws IOException {
		Object entity = responseContext.getEntity();
		if (entity == null) {
			responseContext.setStatus(Response.Status.OK.getStatusCode());
			responseContext.setEntity(new NullBeanResult());
		}
		
		logger.debug("null check.");
	}

}
