package org.acme.exceptions;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.Response;

@ApplicationScoped
public class ResponseBuilder {

    public static Response returnResponse(ValidacaoException e){
        return Response.status(Response.Status.BAD_REQUEST)
                .header("Content-Type","application/json")
                .entity(e.getMessage()).build();
    }
}
