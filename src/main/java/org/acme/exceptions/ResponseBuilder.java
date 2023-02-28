package org.acme.exceptions;

import com.google.gson.Gson;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.acme.Util.GsonUtil;
import org.acme.Util.StringUtil;
import org.acme.models.DTO.Response.ResponseFactory;
import org.acme.models.EntradaDeProduto;
import org.acme.models.Model;
import org.acme.models.asaas.CobrancaParcelada;
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

    public static Response responseOk(Object model) {
        return Response.ok(model)
                .header("Content-Type", "application/json")
                .build();
    }
    public static Response respondeOkWithAlert(Model oneModel, ValidacaoException validacao) {
       return ResponseBuilder.respondeOkWithAlert(null,oneModel,validacao);
    }
    public static Response respondeOkWithAlert(List<Model> modelList, ValidacaoException validacao) {
       return ResponseBuilder.respondeOkWithAlert(modelList,null,validacao);
    }
    public static Response respondeOkWithAlert(List<Model> modelList,Model oneModel, ValidacaoException validacao) {
        ResponseFactory responseFactory = new ResponseFactory();
        Gson gsonStatic = new Gson();

        if(validacao != null){
            if(validacao.getValidacoes() != null){
                responseFactory.setValidacao(validacao.getValidacoes());
            }
            if(StringUtil.stringValida(validacao.getMessage())){
                responseFactory.setValidacao(gsonStatic.fromJson(validacao.getMessage(),List.class));
            }
        }

        if(oneModel != null){
            responseFactory.setModel(oneModel);
        }
        if(modelList != null){
            if(!modelList.isEmpty()){
                responseFactory.setModelList(modelList);
            }
        }

        return Response.ok(responseFactory)
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

    public static Response responseNoContent() {
        ValidacaoException validacao = new ValidacaoException();
        validacao.add("Não foi encontrado nem uma informação");
        return Response.status(Response.Status.NO_CONTENT)
                .header("Content-Type", "application/json")
                .entity(validacao.getValidacoes())
                .build();
    }


}
