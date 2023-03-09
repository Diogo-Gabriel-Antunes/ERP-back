package org.acme.services;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.acme.Util.FieldUtil;
import org.acme.models.DTO.VendasDTO;
import org.acme.models.Venda;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.Response;

@ApplicationScoped
public class VendasService extends Service{



    public Response update(String uuid, VendasDTO vendasDTO){
            Venda venda = Venda.findById(uuid);
            fieldUtil.updateFieldsDtoToModel(venda,vendasDTO);
            Venda.persist(venda);
            return Response.ok(venda).build();

    }
}
