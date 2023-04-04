package org.acme.exceptions;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.Setter;
import org.acme.Util.GsonUtil;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ValidacaoException extends RuntimeException {

    Gson gson = new GsonUtil().parser;
    private List<Validacao> validacoes = new ArrayList<Validacao>();

    public ValidacaoException() {}

    public ValidacaoException(String message) {
        super(message);
    }

    public void add(String mensagem,Integer statusCode,String solucao){
        Validacao novaValidacao = new Validacao();
        novaValidacao.setMensagem(mensagem);
        novaValidacao.setStatusCode(statusCode);
        novaValidacao.setSolucao(solucao);
        validacoes.add(novaValidacao);
    }
    public void add(String mensagem){
        Validacao novaValidacao = new Validacao();
        novaValidacao.setMensagem(mensagem);
        novaValidacao.setSolucao("Verifique se as informações foram inseridas corretamente");
        validacoes.add(novaValidacao);
    }
    public void add(String mensagem,String solucao){
        Validacao novaValidacao = new Validacao();
        novaValidacao.setMensagem(mensagem);
        novaValidacao.setSolucao(solucao);
        validacoes.add(novaValidacao);
    }

    public void lancaErro(){
        if(!validacoes.isEmpty()){
            throw new ValidacaoException(gson.toJson(validacoes));
        }
    }

}
