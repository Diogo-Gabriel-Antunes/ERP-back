package org.acme.controller;

import com.google.gson.Gson;
import org.acme.Util.GsonUtil;
import org.acme.models.Nota_fiscal_eletronica.Transporte;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Path("transporte")
public class TransporteController {

    private Gson gson = new GsonUtil().parser;

    @GET
    @Path("{uuid}")
    public Response listOne(@PathParam("uuid") String uuid) {
        Transporte transporte = Transporte.findById(uuid);
        List<String> campos = new ArrayList<>() {{
            add("transportador");
            add("endereco");
            add("cep");
        }};
        String jsonInicial = gson.toJson(transporte);
        HashMap hashMapInicial = gson.fromJson(jsonInicial,HashMap.class);
        String proximoJson= "";
        for (int i = 0;campos.size() > i ; i++){
            HashMap<Object,Object> proximoHashMap = new HashMap<>();

            if(i == 0){
                proximoJson = String.valueOf(hashMapInicial.get(campos.get(i)));
            }else if(i  ==campos.size()-1){
                proximoHashMap = gson.fromJson(proximoJson, HashMap.class);

                proximoJson = String.valueOf(gson.toJson(proximoHashMap.get(campos.get(i))));

                return Response.ok(proximoJson).build();
            }else{
                proximoHashMap = gson.fromJson(proximoJson, HashMap.class);

                proximoJson = String.valueOf(gson.toJson(proximoHashMap.get(campos.get(i))));


            }
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
}
