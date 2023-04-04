package org.acme.response;

import com.google.gson.Gson;
import org.acme.Util.InterfacesUtil.Model;
import org.acme.Util.PrimitiveUtil.StringUtil;
import org.acme.exceptions.ValidacaoException;
import org.acme.models.DTO.Response.ResponseFactory;
import org.acme.models.DTO.ResponseError;
import org.acme.services.Service;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.Response;
import java.util.List;

@ApplicationScoped
public class ResponseBuilder extends Service {


    public static Response returnResponse(ValidacaoException e) {
        if (e.getValidacoes().isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .header("Content-Type", "application/json")
                    .entity(e.getMessage()).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .header("Content-Type", "application/json")
                    .entity(new ResponseError(e.getValidacoes())).build();
        }
    }

    public static Response returnResponseErro(String erro) {

        return Response.status(Response.Status.BAD_REQUEST)
                .header("Content-Type", "application/json")
                .entity(erro).build();

    }

    public static Response returnResponse() {
        ValidacaoException validacaoException = new ValidacaoException();
        validacaoException.add("Ocorreu o erro no sistema, contate o suporte",500,"Verifique com o suporte");
        return Response.status(Response.Status.BAD_REQUEST)
                .header("Content-Type", "application/json")
                .entity(validacaoException.getValidacoes()).build();

    }

    public static Response responseOk(Object model) {
        return Response.ok(model)
                .header("Content-Type", "application/json")
                .build();
    }

    public static Response respondeOkWithAlert(Model oneModel, ValidacaoException validacao) {
        return ResponseBuilder.respondeOkWithAlert(null, oneModel, validacao);
    }

    public static Response respondeOkWithAlert(List<Model> modelList, ValidacaoException validacao) {
        return ResponseBuilder.respondeOkWithAlert(modelList, null, validacao);
    }

    public static Response respondeOkWithAlert(List<Model> modelList, Model oneModel, ValidacaoException validacao) {
        ResponseFactory responseFactory = new ResponseFactory();
        Gson gsonStatic = new Gson();

        if (validacao != null) {
            if (validacao.getValidacoes() != null) {
                responseFactory.setValidacao(validacao.getValidacoes());
            }
            if (StringUtil.stringValida(validacao.getMessage())) {
                responseFactory.setValidacao(gsonStatic.fromJson(validacao.getMessage(), List.class));
            }
        }

        if (oneModel != null) {
            responseFactory.setModel(oneModel);
        }
        if (modelList != null) {
            if (!modelList.isEmpty()) {
                responseFactory.setModelList(modelList);
            }
        }

        return Response.ok(responseFactory)
                .header("Content-Type", "application/json")
                .build();
    }

    public static Response responseEntityNotFound() {
        ValidacaoException validacaoException = new ValidacaoException();
        validacaoException.add("Conteudo não encontrado",400,"Verifique se o conteudo existe no sistema, se não contate o suporte");
        ResponseError response = new ResponseError(validacaoException.getValidacoes());
        return Response.status(Response.Status.NOT_FOUND)
                .header("Content-Type", "application/json")
                .entity(validacaoException.getValidacoes()).build();

    }

    public static Response returnJsonSyntax() {
        ResponseError response = new ResponseError();
        response.add("Erro na requisição",400,"Verifique se as informações foram enviadas corretamente");


        return Response.status(Response.Status.BAD_REQUEST)
                .header("Content-Type", "application/json")
                .entity(response)
                .build();
    }

    public static Response responseNoContent() {
        ValidacaoException validacao = new ValidacaoException();
        validacao.add("Não foi encontrado nem uma informação",204,"Sem informações no sistema");
        ResponseError response = new ResponseError(validacao.getValidacoes());

        return Response.status(Response.Status.NO_CONTENT)
                .header("Content-Type", "application/json")
                .entity(response)
                .build();
    }


    public static Response returnDateTimeException() {
        ValidacaoException validacao = new ValidacaoException();
        validacao.add("Alguma data não foi informada",400,"Se o erro continuar contate o suporte");
        ResponseError response = new ResponseError(validacao.getValidacoes());
        return Response.status(Response.Status.BAD_REQUEST)
                .header("Content-Type", "application/json")
                .entity(response)
                .build();
    }
}
