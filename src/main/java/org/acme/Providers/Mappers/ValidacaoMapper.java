package org.acme.Providers.Mappers;

import com.google.gson.JsonSyntaxException;
import org.acme.exceptions.ValidacaoException;
import org.acme.response.ResponseBuilder;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ValidacaoMapper implements ExceptionMapper<ValidacaoException> {
    @Override
    public Response toResponse(ValidacaoException validacaoException) {
        return ResponseBuilder.returnResponse(validacaoException);
    }
}
