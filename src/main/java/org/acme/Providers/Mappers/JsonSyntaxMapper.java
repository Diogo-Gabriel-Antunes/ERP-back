package org.acme.Providers.Mappers;

import com.google.gson.JsonSyntaxException;
import org.acme.response.ResponseBuilder;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class JsonSyntaxMapper implements ExceptionMapper<JsonSyntaxException> {
    @Override
    public Response toResponse(JsonSyntaxException jsonSyntaxException) {
        jsonSyntaxException.printStackTrace();
        return ResponseBuilder.returnJsonSyntax();
    }
}
