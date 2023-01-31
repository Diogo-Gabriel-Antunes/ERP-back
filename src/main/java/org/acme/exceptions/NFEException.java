package org.acme.exceptions;

import java.util.HashMap;

public class NFEException extends RuntimeException{
    public NFEException(String message) {
        super(message);
    }

    public HashMap<String,String> retorno (){
        HashMap<String,String> hashMap = new HashMap<String,String>();
        hashMap.put("Mensagem",getMessage());
        return hashMap;
    }
}

