package org.acme.services;


import org.acme.models.Contato;
import org.acme.models.DTO.LojaDTO;
import org.acme.models.Loja;
import org.acme.models.Nota_fiscal_eletronica.EnderecoNFE;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.Optional;

@ApplicationScoped
public class LojaService extends Service {
    @Inject
    private EnderecoService enderecoService;
    @Inject
    private ContatoService contatoService;
    @Inject
    private ItensService itensService;

    public Loja create(String json) {
        Loja loja = new Loja();
        transfereDadosDtoToModel(json, loja);
        em.persist(loja);
        return loja;
    }

    private void transfereDadosDtoToModel(String json, Loja loja) {
        LojaDTO lojaDTO = gson.fromJson(json, LojaDTO.class);
        EnderecoNFE enderecoNFE = em.merge(enderecoService.convertDtoToModel(loja.getEndereco(), lojaDTO.getEndereco()));
        Contato contato = em.merge(contatoService.convertDtoToModel(loja.getContato(), lojaDTO.getContato()));

        fieldUtil.updateFieldsDtoToModel(loja, lojaDTO);
        loja.setEndereco(enderecoNFE);
        loja.setContato(contato);
        em.persist(loja);
        em.flush();
    }

    public Loja update(String uuid, String json) {
        try {
            Optional<Loja> loja = Loja.findByIdOptional(uuid);
            if (loja.isPresent()) {
                Loja lojaModel = em.merge(loja.get());
                transfereDadosDtoToModel(json, lojaModel);
                em.persist(lojaModel);
                em.flush();
                return lojaModel;
            }
            throw new RuntimeException();
        } catch (Throwable t) {
            t.printStackTrace();
            return null;
        }
    }

    public Response delete(String uuid) {
        Optional<Loja> loja = Loja.findByIdOptional(uuid);
        if (loja.isPresent()) {
            em.remove(loja);
            return Response.ok().build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

}

