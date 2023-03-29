package org.acme.services;

import org.acme.Util.PrimitiveUtil.StringUtil;
import org.acme.response.ResponseBuilder;
import org.acme.exceptions.ValidacaoException;
import org.acme.models.DTO.MotoristaDTO;
import org.acme.models.Motorista;
import org.acme.models.Nota_fiscal_eletronica.EnderecoNFE;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.util.List;

@ApplicationScoped
public class MotoristaService extends Service implements ServiceInterface {
    public Response listAll() {
            List<Motorista> motoristas = em.createQuery("SELECT m FROM Motorista m", Motorista.class)
                    .getResultList();
            if(motoristas.isEmpty()){
                return ResponseBuilder.responseNoContent();
            }else{
                return ResponseBuilder.responseOk(motoristas);
            }
    }

    @Transactional
    @Override
    public Response create(String json) {

            MotoristaDTO motoristaDTO = gson.fromJson(json, MotoristaDTO.class);
            validaDTO(motoristaDTO);
            Motorista motorista = new Motorista();
            convertModel(motorista,motoristaDTO);
            em.persist(motorista);
            return ResponseBuilder.responseOk(motorista);
    }

    @Override
    @Transactional
    public Response update(String uuid, String json) {
            MotoristaDTO motoristaDTO = gson.fromJson(json, MotoristaDTO.class);
            validaDTO(motoristaDTO);
            Motorista motorista = Motorista.findById(uuid);
            if(motorista != null){
                convertModel(motorista,motoristaDTO);
                em.persist(motorista);
                return ResponseBuilder.responseOk(motorista);
            }else{
                return ResponseBuilder.responseEntityNotFound();
            }
    }
    public void validaDTO(MotoristaDTO motoristaDTO) {
        validaDTO(null,motoristaDTO);
    }
    public void validaDTO(ValidacaoException validacao,MotoristaDTO motoristaDTO){
        Boolean validacaoNaoExiste = validacao == null;
        if(validacaoNaoExiste){
            validacao = new ValidacaoException();
        }

        if(!StringUtil.stringValida(motoristaDTO.getNome())){
            validacao.add("Campo nome esta invalido");
        }
        if(!StringUtil.stringValida(motoristaDTO.getSobrenome())){
            validacao.add("Campo sobrenome esta invalido");
        }
        if(!StringUtil.stringValida(motoristaDTO.getCPF())){
            validacao.add("Campo CPF esta invalido");
        }
        if(!StringUtil.stringValida(motoristaDTO.getCNH())){
            validacao.add("Campo CNH esta invalido");
        }
        if(!StringUtil.stringValida(motoristaDTO.getCategoriaCNH())){
            validacao.add("Campo categoria CNH esta invalido");
        }

        enderecoService.validaEndereco(validacao,motoristaDTO.getEndereco(),true);

        if(validacaoNaoExiste){
            validacao.lancaErro();
        }
    }

    public void convertModel(Motorista motorista, MotoristaDTO motoristaDTO) {
        fieldUtil.updateFieldsDtoToModel(motorista,motoristaDTO);
        EnderecoNFE endereco = new EnderecoNFE();
        fieldUtil.updateFieldsDtoToModel(endereco,motoristaDTO.getEndereco());
        em.persist(endereco);
        motorista.setEndereco(endereco);

    }

    public Response delete(String uuid) {
            Motorista motorista = Motorista.findById(uuid);
            motorista.delete();
            return ResponseBuilder.responseOk(motorista);
    }
}
