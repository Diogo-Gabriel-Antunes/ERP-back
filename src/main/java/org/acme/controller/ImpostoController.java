package org.acme.controller;

import com.google.gson.Gson;
import org.acme.Util.GsonUtil;
import org.acme.models.DTO.NFE.*;
import org.acme.models.Imposto;
import org.acme.models.Nota_fiscal_eletronica.*;
import org.acme.models.enums.Estado;
import org.acme.services.ImpostoService;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("imposto")
public class ImpostoController {
    private Gson gson = new GsonUtil().parser;

    @Inject
    ImpostoService impostoService;

    @GET
    public Response listAll() {
        return Response.ok(Imposto.list("SELECT i,b from Imposto i left join fetch i.baseCalculo b")).build();
    }

    @GET
    @Path("{uuid}")
    public Response listOne(@PathParam("uuid") String uuid) {
        return Response.ok(Imposto.findById(uuid)).build();
    }

    @POST
    @Transactional
    public Response create(String json) {
        return impostoService.create(json);
    }

    @PUT
    @Path("{uuid}")
    public Response update(@PathParam("uuid") String uuid, String json) {
        return impostoService.update(uuid, json);
    }

    @POST
    @Path("icms")
    @Transactional
    public Response createICMS(String json) {
        ICMSDTO icmsdto = gson.fromJson(json, ICMSDTO.class);
        return impostoService.create(icmsdto);
    }

    @POST
    @Path("cofins")
    @Transactional
    public Response createCofins(String json) {
        CofinsDTO cofinsDTO = gson.fromJson(json, CofinsDTO.class);
        return impostoService.create(cofinsDTO);
    }

    @POST
    @Path("ipi")
    @Transactional
    public Response createIPI(String json) {
        IPIDTO ipidto = gson.fromJson(json, IPIDTO.class);
        return impostoService.create(ipidto);
    }
    @POST
    @Path("pis")
    @Transactional
    public Response createPIS(String json) {
        PisDTO pisDTO = gson.fromJson(json, PisDTO.class);
        return impostoService.create(pisDTO);
    }
    @POST
    @Path("issqn")
    @Transactional
    public Response createISSQN(String json) {
        IssqnDTO issqnDTO = gson.fromJson(json, IssqnDTO.class);
        return impostoService.create(issqnDTO);
    }

    @GET
    @Path("teste/teste")
    @Transactional
    public Response teste() {
        ICMS icms = new ICMS();
        icms.setCst("teste");
        icms.setAliquota(2.0);
        icms.setOrigem("teste");
        icms.setEstado(Estado.AM);
        SubstituicaoTributaria substituicaoTributaria = new SubstituicaoTributaria();
        substituicaoTributaria.setAliquota(2.0);
        substituicaoTributaria.setMargemValorAdicionado(2.0);
        icms.setSubstituicaoTributaria(substituicaoTributaria);
        BaseCalculo baseCalculo = new BaseCalculo();
        baseCalculo.setTipoDaBaseDeCalculo(TipoDaBaseDeCalculo.ICMS);
        baseCalculo.setValor(2.0);
        baseCalculo.setPercentualReducao(2.0);
        baseCalculo.setModalidadeDeterminacao(ModalidadeDeterminacao.PRECOTABELADOMAX);
        icms.setBaseCalculo(baseCalculo);
        substituicaoTributaria.setBaseCalculo(baseCalculo);
        EntityManager em = ICMS.getEntityManager();
        em.persist(baseCalculo);
        em.persist(substituicaoTributaria);
        em.persist(icms);
        return Response.ok(icms).build();

    }
}
