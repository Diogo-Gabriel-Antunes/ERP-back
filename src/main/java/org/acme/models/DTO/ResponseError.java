package org.acme.models.DTO;

import lombok.Getter;
import lombok.Setter;
import org.acme.exceptions.Validacao;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ResponseError {

    private List<Validacao> erro = new ArrayList<>();

    public ResponseError(List<Validacao> validacoes) {
        erro.addAll(validacoes);

    }

    public ResponseError() {
    }

    public void add(String mensagem,Integer statusCode,String solucao){
        Validacao validacao = new Validacao();
        validacao.setStatusCode(statusCode);
        validacao.setMensagem(mensagem);
        validacao.setSolucao(solucao);
        erro.add(validacao);
    }
}
