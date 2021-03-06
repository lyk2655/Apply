package com.ipin.service.rest;

import org.glassfish.jersey.message.GZipEncoder;
import org.glassfish.jersey.message.filtering.EntityFilteringFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.EncodingFilter;

/**
 * Registers the components to be used by the JAX-RS application
 * 
 * @author longman
 * 
 */
public class RestJaxRsApplication extends ResourceConfig {

	/**
	 * Register JAX-RS application components.
	 */
	public RestJaxRsApplication() {
		
        packages("com.ipin.service.rest");
        
//		// register application resources
//		register(PodcastsResource.class);
//		register(PodcastLegacyResource.class);
//
//		// register filters
//		register(RequestContextFilter.class);
//		register(LoggingResponseFilter.class);
//		register(CORSResponseFilter.class);
//
//		// register exception mappers
//		register(GenericExceptionMapper.class);
//		register(AppExceptionMapper.class);
//      register(CustomReasonPhraseExceptionMapper.class);
//		register(NotFoundExceptionMapper.class);
//
//		// register features
//		register(JacksonFeature.class);
		register(EntityFilteringFeature.class);
		//if uncomment this, all data will compress.
		// EncodingFilter.enableFor(this, GZipEncoder.class);
		
//		property(EntityFilteringFeature.ENTITY_FILTERING_SCOPE, new Annotation[] {PodcastDetailedView.Factory.get()});
	}
}
