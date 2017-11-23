package com.ipin.service.rest.errorhandling;

import com.ipin.service.rest.constants.ErrorCode;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {

    public Response toResponse(Throwable ex) {

        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setCode(ErrorCode.UNKNOWN_ERROR.getCode());
        errorMessage.setMessage(ex.getMessage());
        StringWriter errorStackTrace = new StringWriter();
        ex.printStackTrace(new PrintWriter(errorStackTrace));
        errorMessage.setDeveloperMessage(errorStackTrace.toString());

        return Response.status(getHttpStatus(ex))
                .entity(errorMessage)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

    private int getHttpStatus(Throwable ex) {
        if (ex instanceof WebApplicationException) { //NICE way to combine both of methods, say it in the blog
            return ((WebApplicationException) ex).getResponse().getStatus();
        } else {
            return Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(); //defaults to internal server error 500
        }
    }
}

