package org.acme.services;

import com.google.gson.Gson;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.acme.Util.FieldUtil;
import org.acme.Util.GsonUtil;
import org.acme.models.*;
import org.acme.models.DTO.CompraDTO;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.Arrays;

@ApplicationScoped
public class CompraService extends Service {

    public Response create(String json) {
        try {
            CompraDTO compraDTO = gson.fromJson(json, CompraDTO.class);
            Compra compra = new Compra(compraDTO);
            Compra.getEntityManager().merge(compra);

            compra.setItens(compraDTO.getItens());
            compra.setCondicoesArmazenamentoETransporte(compraDTO.getCondicoesArmazenamentoETransporte());
            compra.setResponsavelPelaVenda(compraDTO.getResponsavelPelaVenda());
            CondicoesArmazenamentoETransporte condicoesArmazenamentoETransporte = compra.getCondicoesArmazenamentoETransporte();
            CondicoesArmazenamentoETransporte.persist(condicoesArmazenamentoETransporte);
            condicoesArmazenamentoETransporte.setCompra(compra);
            Compra.persist(compra);
            Compra.flush();
            return Response.created(new URI("/compra")).build();
        }catch (Throwable t){
            t.printStackTrace();
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
