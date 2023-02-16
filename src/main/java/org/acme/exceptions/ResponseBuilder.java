package org.acme.exceptions;

import org.acme.models.Model;
import org.acme.models.asaas.CobrancaParcelada;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.Response;

@ApplicationScoped
public class ResponseBuilder {

    public static Response returnResponse(ValidacaoException e) {
        if (e.getValidacoes().isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .header("Content-Type", "application/json")
                    .entity(e.getMessage()).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .header("Content-Type", "application/json")
                    .entity(e.getValidacoes()).build();
        }
    }

    public static Response returnResponse() {
        ValidacaoException validacaoException = new ValidacaoException();
        validacaoException.add("Ocorreu o erro no sistema, contate o suporte");
        return Response.status(Response.Status.BAD_REQUEST)
                .header("Content-Type", "application/json")
                .entity(validacaoException.getValidacoes()).build();

    }

    public static Response responseOk(Model model) {
        return Response.ok(model)
                .header("Content-Type", "application/json")
                .build();
    }

    public static Response responseEntityNotFound() {
        ValidacaoException validacaoException = new ValidacaoException();
        validacaoException.add("Conteudo não encontrado");
        return Response.status(Response.Status.NOT_FOUND)
                .header("Content-Type", "application/json")
                .entity(validacaoException.getValidacoes()).build();

    }

    public static Response returnNumberFormat() {
        ValidacaoException validacaoException = new ValidacaoException();
        validacaoException.add("Erro na formatação dos dados");
        validacaoException.add("Verifique se os dados foram informados corretamente");
        validacaoException.add("Em caso de continuar o erro contate o suporte");

        return Response.status(Response.Status.BAD_REQUEST)
                .header("Content-Type", "application/json")
                .entity(validacaoException.getValidacoes())
                .build();
    }
}
