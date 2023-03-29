package org.acme.services;

import org.acme.Util.PrimitiveUtil.StringUtil;
import org.acme.response.ResponseBuilder;
import org.acme.exceptions.ValidacaoException;
import org.acme.models.DTO.TransportadorDTO;
import org.acme.models.Nota_fiscal_eletronica.EnderecoNFE;
import org.acme.models.Nota_fiscal_eletronica.Transportador;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.Response;
import java.util.List;

@ApplicationScoped
public class TransportadoraService extends Service{


    public List<Transportador> findAll() {
        return em.createQuery("SELECT t FROM Transportador t", Transportador.class).getResultList();
    }
    public Transportador findOne(String uuid){
        return em.createQuery("SELECT t FROM Transportador t WHERE uuid = :uuid",Transportador.class)
                .setParameter("uuid",uuid)
                .getSingleResult();
    }

    public Response create(String json) {
            TransportadorDTO transportadorDTO = gson.fromJson(json, TransportadorDTO.class);
            validaDTO(transportadorDTO);
            Transportador transportador = new Transportador();
            parserToModel(transportador,transportadorDTO);
            em.persist(transportador);
            return ResponseBuilder.responseOk(transportador);
    }

    public Response update(String uuid, String json) {
            TransportadorDTO transportadorDTO = gson.fromJson(json, TransportadorDTO.class);
            validaDTO(transportadorDTO);
            Transportador transportador = Transportador.findById(uuid);
            parserToModel(transportador,transportadorDTO);
            em.persist(transportador);
            return ResponseBuilder.responseOk(transportador);
    }

    private void parserToModel(Transportador transportador, TransportadorDTO transportadorDTO) {
        fieldUtil.updateFieldsDtoToModel(transportador,transportadorDTO);
        EnderecoNFE enderecoNFE = new EnderecoNFE();
        fieldUtil.updateFieldsDtoToModel(enderecoNFE,transportadorDTO.getEndereco());
        em.persist(enderecoNFE);
        transportador.setEndereco(enderecoNFE);
    }

    private void validaDTO(TransportadorDTO transportadorDTO) {
        ValidacaoException validacao = new ValidacaoException();

        if(!StringUtil.stringValida(transportadorDTO.getCnpjCpf())){
            validacao.add("É Necessario passar um CNPJ/CPF valido");
        }
        if(!StringUtil.stringValida(transportadorDTO.getNome())){
            validacao.add("É necesarrio passar um nome valido");
        }
        if(!StringUtil.stringValida(transportadorDTO.getInscricaoEstadual())){
            validacao.add("É necessario passar uma inscrição estudal valida");
        }
        if(transportadorDTO.getEndereco() == null){
            validacao.add("É Necessario informar o endereço");
        }
        validacao.lancaErro();
    }

    public Response delete(String uuid) {
            Transportador transportador = findOne(uuid);
            em.remove(transportador);
            return Response.ok().build();
    }
}
