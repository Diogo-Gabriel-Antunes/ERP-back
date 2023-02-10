package org.acme.exceptions;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.Setter;
import org.acme.Util.GsonUtil;

import javax.ws.rs.WebApplicationException;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ValidacaoException extends RuntimeException {

    Gson gson = new GsonUtil().parser;
    private List<Validacao> validacoes = new ArrayList<Validacao>();

    public ValidacaoException() {

    }

    public ValidacaoException(String message) {
        super(message);
    }

    public void add(String validacao){
        Validacao novaValidacao = new Validacao();
        novaValidacao.setErro(validacao);
        validacoes.add(novaValidacao);
    }

    public void lancaErro(){
        if(!validacoes.isEmpty()){
            throw new ValidacaoException(gson.toJson(validacoes));
        }
    }
}
