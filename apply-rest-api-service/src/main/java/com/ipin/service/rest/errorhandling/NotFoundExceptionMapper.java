package com.ipin.service.rest.errorhandling;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.ipin.service.rest.constants.ErrorCode;


/*
Not found exception
 */
@Provider
public class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException> {

    public Response toResponse(NotFoundException ex) {
        return Response.status(ex.getResponse().getStatus())
                .entity(new ErrorMessage(ErrorCode.API_NOT_FOUND, ex.getMessage()))
                .type(MediaType.APPLICATION_JSON) //this has to be set to get the generated JSON
                .build();
    }

}
