package org.acme.services;


import org.acme.Util.PrimitiveUtil.StringUtil;
import org.acme.response.ResponseBuilder;
import org.acme.exceptions.ValidacaoException;
import org.acme.models.Contato;
import org.acme.models.DTO.LojaDTO;
import org.acme.models.Loja;
import org.acme.models.Nota_fiscal_eletronica.EnderecoNFE;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.Response;
import java.util.Optional;

@ApplicationScoped
public class LojaService extends Service {


    public Response create(String json) {
        try{
            Loja loja = new Loja();
            transfereDadosDtoToModel(json, loja);
            em.persist(loja);
            return ResponseBuilder.responseOk(loja);
        }catch (ValidacaoException e){
            return ResponseBuilder.returnResponse(e);
        }catch (Throwable t){
            t.printStackTrace();
            return ResponseBuilder.returnResponse();
        }
    }

    private void transfereDadosDtoToModel(String json, Loja loja) {

        LojaDTO lojaDTO = gson.fromJson(json, LojaDTO.class);
        validaLoja(lojaDTO);
        EnderecoNFE enderecoNFE = em.merge(enderecoService.convertDtoToModel(loja.getEndereco(), lojaDTO.getEndereco()));
        Contato contato = em.merge(contatoService.convertDtoToModel(loja.getContato(), lojaDTO.getContato()));

        fieldUtil.updateFieldsDtoToModel(loja, lojaDTO);
        loja.setEndereco(enderecoNFE);
        loja.setContato(contato);
        em.persist(loja);
        em.flush();


    }

    private void validaLoja(LojaDTO lojaDTO) {
        ValidacaoException validacao = new ValidacaoException();

        enderecoService.validaEndereco(validacao, lojaDTO.getEndereco(), true);
        if (!StringUtil.stringValida(lojaDTO.getNomeLoja())) {
            validacao.add("Campo nome loja invalido");
        }
        if (!StringUtil.stringValida(lojaDTO.getCnpj())) {
            validacao.add("Campo CNPJ invalido");
        }
        if (!StringUtil.stringValida(lojaDTO.getRazaoSocial())) {
            validacao.add("Campo Razão social invalido");
        }
        contatoService.validaContato(validacao, lojaDTO.getContato(), true);

        validacao.lancaErro();
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

