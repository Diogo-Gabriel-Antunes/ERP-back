package org.acme.services;

import com.google.gson.Gson;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.acme.Util.FieldUtil;
import org.acme.Util.GsonUtil;
import org.acme.models.Compra;
import org.acme.models.DTO.CompraDTO;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.Response;
import java.net.URI;

@ApplicationScoped
public class CompraService {
    private Gson gson = new GsonUtil().parser;

    private FieldUtil fieldUtil = new FieldUtil();
    public Response create(String json) {
        try {
            CompraDTO compraDTO = gson.fromJson(json, CompraDTO.class);
            Compra compra = new Compra(compraDTO);
            Compra compraDB = Compra.getEntityManager().merge(compra);
            return Response.created(new URI("http://10.0.0.108:8080/compra/"+compraDB.getUuid())).build();
        }catch (Throwable t){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

    }

    public Response update(String uuid, String json) {
        Compra compra = Compra.findById(uuid);
        CompraDTO compraDTO = gson.fromJson(json, CompraDTO.class);

        fieldUtil.updateFieldsDtoToModel(compra,compraDTO);
        Compra.persist(compra);
        return Response.ok(compra).build();
    }
}
