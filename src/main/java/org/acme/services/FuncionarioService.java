package org.acme.services;

import org.acme.Util.JsonUtil;
import org.acme.Util.PrimitiveUtil.StringUtil;
import org.acme.response.ResponseBuilder;
import org.acme.exceptions.ValidacaoException;
import org.acme.models.DTO.FuncionarioDTO;
import org.acme.models.Funcionario;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.Response;

@ApplicationScoped
public class FuncionarioService extends Service {


    public Response create(String json) {
        try {

            FuncionarioDTO funcionarioDTO = gson.fromJson(json, FuncionarioDTO.class);

            validaFuncionario(funcionarioDTO);
            Funcionario funcionario = new Funcionario();
            fieldUtil.updateFieldsDtoToModel(funcionario.getEndereco(),funcionarioDTO.getEndereco());
            fieldUtil.updateFieldsDtoToModel(funcionario,funcionarioDTO);
            em.persist(funcionario);

            em.flush();
            return ResponseBuilder.responseOk(funcionario);
        }  catch (ValidacaoException e) {
            return ResponseBuilder.returnResponse(e);
        }catch (Throwable t) {
            t.printStackTrace();
            return ResponseBuilder.returnResponse();
        }
    }

    public Response update(String uuid, String json) {
        try {
            FuncionarioDTO funcionarioDTO = gson.fromJson(json, FuncionarioDTO.class);
            validaFuncionario(funcionarioDTO);

            Funcionario funcionario = Funcionario.findById(uuid);

            funcionarioDTO.getBeneficios().addAll(funcionario.getBeneficios());
            fieldUtil.updateFieldsDtoToModel(funcionario.getEndereco(),funcionarioDTO.getEndereco());
            fieldUtil.updateFieldsDtoToModel(funcionario, funcionarioDTO);

            Funcionario.persist(funcionario);
            return ResponseBuilder.responseOk(funcionario);
        } catch (ValidacaoException e) {
            return ResponseBuilder.returnResponse(e);
        } catch (Throwable t) {
            t.printStackTrace();
            return ResponseBuilder.returnResponse();
        }
    }
    public void validaFuncionario(FuncionarioDTO funcionarioDTO) {
        validaFuncionario(null,funcionarioDTO);
    }
    public void validaFuncionario(ValidacaoException validacao,FuncionarioDTO funcionarioDTO) {
        boolean precisaLancarErro = validacao == null;
        if(validacao == null){
            validacao = new ValidacaoException();
        }
        enderecoService.validaEndereco(validacao, funcionarioDTO.getEndereco(), true);

        if (!StringUtil.stringValida(funcionarioDTO.getNome())) {
            validacao.add("Campo nome invalido");
        }
        if (!StringUtil.stringValida(funcionarioDTO.getPis())) {
            validacao.add("Campo pis invalido");
        }
        if (!StringUtil.stringValida(funcionarioDTO.getCpf())) {
            validacao.add("Campo cpf invalido");
        }
        if (!StringUtil.stringValida(funcionarioDTO.getRg())) {
            validacao.add("Campo rg invalido");
        }
        if (!StringUtil.stringValida(funcionarioDTO.getTituloDeEleitor())) {
            validacao.add("Campo titulo de eleito invalido");
        }
        if (!StringUtil.stringValida(funcionarioDTO.getCargo())) {
            validacao.add("Campo Cargo invalido");
        }
        if (funcionarioDTO.getDataAdmissao() == null) {
            validacao.add("Campo data de admissão invalido");
        }
        if (funcionarioDTO.getDataNascimento() == null) {
            validacao.add("Campo data de nascimento invalido");
        }
        if (funcionarioDTO.getNacionalidade() == null) {
            validacao.add("Campo nacionalidade invalido");
        }
        if (funcionarioDTO.getSalario() == 0.0 || funcionarioDTO.getSalario() == null) {
            validacao.add("Campo salario invalido");
        }
        if(precisaLancarErro){
            validacao.lancaErro();
        }
    }

    public Response delete(String uuid) {
        try {
            Funcionario.delete("uuid", uuid);
            return Response.ok().build();
        } catch (Throwable t) {
            t.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
