package org.acme.services;

import com.google.gson.Gson;
import org.acme.Util.FieldUtil;
import org.acme.Util.GsonUtil;
import org.acme.exceptions.NFEException;
import org.acme.models.DTO.NFE.*;
import org.acme.models.Imposto;
import org.acme.models.Nota_fiscal_eletronica.*;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.util.Optional;

@ApplicationScoped
public class ImpostoService extends Service {

    @Transactional
    public Response create(String json) {
        try {
            ImpostoDTO impostoDTO = gson.fromJson(json, ImpostoDTO.class);
            Imposto imposto = new Imposto();
            convertAndUpdateFieldsDTO(impostoDTO, imposto);
            fieldUtil.updateFieldsDtoToModel(imposto, impostoDTO);
            Imposto.persist(imposto);
            return Response.ok(impostoDTO).build();
        } catch (Throwable t) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

    }
    @Transactional
    public Response create(CofinsDTO cofinsDTO) {
        try {
            Cofins cofins = montaCofins(cofinsDTO);
            Cofins.persist(cofins);
            return Response.ok(cofins).build();
        } catch (NFEException n) {
            n.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity(n.retorno()).build();
        }
    }

    @Transactional
    public Response create(PisDTO pisDTO) {
        try {
            Pis cofins = montaPis(pisDTO);
            Pis.persist(cofins);
            return Response.ok(cofins).build();
        } catch (NFEException n) {
            n.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity(n.retorno()).build();
        }
    }

    @Transactional
    public Response create(ICMSDTO icmsdto) {
        try {
            ICMS icms = montaICMS(icmsdto);
            ICMS.persist(icms);
            return Response.ok(icms).build();
        } catch (NFEException n) {
            n.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity(n.retorno()).build();
        }

    }

    @Transactional
    public Response create(IssqnDTO issqnDTO) {
        try {
            Issqn issqn = montaIssqn(issqnDTO);
            Issqn.persist(issqn);
            return Response.ok(issqn).build();
        } catch (NFEException n) {
            n.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity(n.retorno()).build();
        }

    }

    @Transactional
    public Response create(IPIDTO ipidto) {
        try {
            IPI ipi = montaIpi(ipidto);
            IPI.persist(ipi);
            return Response.ok(ipi).build();
        } catch (NFEException n) {
            n.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity(n.retorno()).build();
        }

    }

    private void convertAndUpdateFieldsDTO(ImpostoDTO impostoDTO, Imposto imposto) {
        BaseCalculo baseCalculo = imposto.getBaseCalculo() != null ? imposto.getBaseCalculo() : new BaseCalculo();
        SubstituicaoTributaria substituicaoTributaria = imposto.getSubstituicaoTributaria() != null ? imposto.getSubstituicaoTributaria() : new SubstituicaoTributaria();
        FundoCombatePobreza fundoCombatePobreza = imposto.getFundoCombatePobreza() != null ? imposto.getFundoCombatePobreza() : new FundoCombatePobreza();


        if (impostoDTO.getBaseCalculo() != null) {
            fieldUtil.updateFieldsDtoToModel(baseCalculo, impostoDTO.getBaseCalculo());
            imposto.setBaseCalculo(baseCalculo);
            BaseCalculo.persist(imposto.getBaseCalculo());
            impostoDTO.setBaseCalculo(null);
        }
        if (impostoDTO.getSubstituicaoTributaria() != null) {
            fieldUtil.updateFieldsDtoToModel(substituicaoTributaria, impostoDTO.getSubstituicaoTributaria());
            imposto.setSubstituicaoTributaria(substituicaoTributaria);
            if (impostoDTO.getSubstituicaoTributaria().getBaseCalculo() != null) {
                BaseCalculo.persist(imposto.getSubstituicaoTributaria().getBaseCalculo());
            }
            SubstituicaoTributaria.persist(imposto.getSubstituicaoTributaria());
            impostoDTO.setSubstituicaoTributaria(null);
        }
        if (impostoDTO.getFundoCombatePobreza() != null) {
            fieldUtil.updateFieldsDtoToModel(fundoCombatePobreza, impostoDTO.getFundoCombatePobreza());

            imposto.setFundoCombatePobreza(fundoCombatePobreza);
            if (impostoDTO.getFundoCombatePobreza().getBaseCalculo() != null) {
                BaseCalculo.persist(imposto.getFundoCombatePobreza().getBaseCalculo());
            }
            FundoCombatePobreza.persist(imposto.getFundoCombatePobreza());
            impostoDTO.setFundoCombatePobreza(null);
        }
    }


    public Response update(String uuid, String json) {
        ImpostoDTO impostoDTO = gson.fromJson(json, ImpostoDTO.class);
        Optional<Imposto> imposto = Imposto.findByIdOptional(uuid);
        if (imposto.isPresent()) {
            fieldUtil.createHashMap(imposto.get(), impostoDTO);
            Imposto.persist(imposto);
            return Response.ok(imposto).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }


    private Issqn montaIssqn(IssqnDTO issqnDTO) {
        Issqn issqn;
        if (issqnDTO != null) {
            issqn = new Issqn();
            issqn.setCodigoExigibilidade(issqnDTO.getCodigoExigibilidade() != null ? issqnDTO.getCodigoExigibilidade() : null);
            issqn.setCodigoMunicipalServico(issqnDTO.getCodigoMunicipalServico() != null ? issqnDTO.getCodigoMunicipalServico() : null);
            issqn.setCodigoMunicipioFatoGerador(issqnDTO.getCodigoMunicipioFatoGerador() != null ? issqnDTO.getCodigoMunicipioFatoGerador() : null);
            issqn.setCodigoMunicipioIncidencia(issqnDTO.getCodigoMunicipioIncidencia() != null ? issqnDTO.getCodigoMunicipioIncidencia() : null);
            issqn.setCodigoServico(issqnDTO.getCodigoServico() != null ? issqnDTO.getCodigoServico() : null);
            issqn.setDecontoCondicionado(issqnDTO.getDecontoCondicionado() != 0 ? issqnDTO.getDecontoCondicionado() : 0);
            issqn.setDescontoIncondicionado(issqnDTO.getDescontoIncondicionado() != 0 ? issqnDTO.getDescontoIncondicionado() : 0);
            issqn.setValorDeducao(issqnDTO.getValorDeducao() != null ? issqnDTO.getValorDeducao() : null);
            issqn.setValorOutros(issqnDTO.getValorOutros() != 0 ? issqnDTO.getValorOutros() : 0);
            issqn.setValorRetencaoIss(issqnDTO.getValorRetencaoIss() != 0 ? issqnDTO.getValorRetencaoIss() : 0);
            issqn.setNumeroProcessoSuspensao(issqnDTO.getNumeroProcessoSuspensao() != null ? issqnDTO.getNumeroProcessoSuspensao() : null);
            issqn.setFundoCombatePobreza(convertFundoCombatePobreza(issqnDTO.getFundoCombatePobreza() != null ? issqnDTO.getFundoCombatePobreza() : null));
            issqn.setBaseCalculo(convertBaseCalculo(issqnDTO.getBaseCalculo() != null ? issqnDTO.getBaseCalculo() : null));
            issqn.setEstado(issqnDTO.getEstado() != null ? issqnDTO.getEstado() : null);
        } else {
            issqn = null;
        }
        if(issqn != null && issqn.getBaseCalculo() != null){
            em.persist(issqn.getBaseCalculo());
            em.persist(issqn);
        }

        return issqn;
    }


    private IPI montaIpi(IPIDTO ipidto) {
        IPI ipi;
        if (ipidto != null) {
            ipi = new IPI();
            ipi.setCnpjProdutor(ipidto.getCnpjProdutor());
            ipi.setUnidade(convertUnidade(ipidto.getUnidade()));
            ipi.setSeloControle(convertSeloControle(ipidto.getSeloControle()));
            ipi.setCodigoEnquadramentoLegal(ipidto.getCodigoEnquadramentoLegal());
        } else {
            ipi = null;
        }
        em.persist(ipi);
        return ipi;
    }

    private SeloControle convertSeloControle(SeloControleDTO seloControleDTO) {
        SeloControle seloControle;
        if (seloControleDTO != null) {
            seloControle = new SeloControle();
            seloControle.setQuantidade(seloControleDTO.getQuantidade() != 0 ? seloControle.getQuantidade() : 0);
            seloControle.setCodigo(seloControle.getCodigo() != null ? seloControle.getCodigo() : null);
        } else {
            seloControle = null;
        }
        return seloControle;
    }

    private UnidadeNFE convertUnidade(UnidadeNFEDto unidadeDTO) {
        UnidadeNFE unidadeNFE;
        if (unidadeDTO != null) {
            unidadeNFE = new UnidadeNFE();
            unidadeNFE.setComercial(unidadeDTO.getComercial() != null ? unidadeDTO.getComercial() : null);
            unidadeNFE.setQuantidade(unidadeDTO.getQuantidade() != 0 ? unidadeDTO.getQuantidade() : 0);
            unidadeNFE.setTributavel(unidadeDTO.getTributavel() != null ? unidadeDTO.getTributavel() : null);
        } else {
            unidadeNFE = null;
        }
        return unidadeNFE;
    }


    private Pis montaPis(PisDTO pisDTO) {
        Pis pis;
        if (pisDTO != null) {
            pis = new Pis();
            pis.setAliquotaReais(pisDTO.getAliquotaReais());
            pis.setQuantidadeVendida(pisDTO.getQuantidadeVendida());
            pis.setFundoCombatePobreza(convertFundoCombatePobreza(pisDTO.getFundoCombatePobreza() != null ? pisDTO.getFundoCombatePobreza() : null));
            pis.setBaseCalculo(convertBaseCalculo(pisDTO.getBaseCalculo() != null ? pisDTO.getBaseCalculo() : null));
            pis.setEstado(pisDTO.getEstado() != null ? pisDTO.getEstado() : null);
            pis.setTipoImposto(pisDTO.getTipoImposto() != null ? pisDTO.getTipoImposto() : null);
            em.persist(pis.getBaseCalculo());
            em.persist(pis.getFundoCombatePobreza());

        } else {
            pis = null;
        }
        if(pis != null && pis.getBaseCalculo() != null){
            em.persist(pis.getBaseCalculo());
        }
        return pis;
    }

    private Cofins montaCofins(CofinsDTO cofinsDTO) {
        Cofins cofins;
        if (cofinsDTO != null) {
            cofins = new Cofins();
            cofins.setSubstituicaoTributaria(convertSubstituicaoTributaria(cofinsDTO.getSubstituicaoTributaria() != null ? cofinsDTO.getSubstituicaoTributaria() : null));
            cofins.setCst(cofinsDTO.getCst() != null ? cofinsDTO.getCst() : null);
            cofins.setAliquota(cofinsDTO.getAliquota() != 0 ? cofinsDTO.getAliquota() : 0);
            cofins.setBaseCalculo(convertBaseCalculo(cofinsDTO.getBaseCalculo() != null ? cofinsDTO.getBaseCalculo() : null));
            cofins.setAliquotaReais(cofinsDTO.getAliquotaReais() != 0 ? cofinsDTO.getAliquotaReais() : 0);
            cofins.setTipoImposto(cofinsDTO.getTipoImposto() != null ? cofinsDTO.getTipoImposto() : null);
            cofins.setQuantidadeVendida(cofinsDTO.getQuantidadeVendida() != 0 ? cofinsDTO.getQuantidadeVendida() : 0);
            cofins.setFundoCombatePobreza(convertFundoCombatePobreza(cofinsDTO.getFundoCombatePobreza() != null ? cofinsDTO.getFundoCombatePobreza() : null));
        } else {
            cofins = null;
        }

        return cofins;
    }

    private ICMS montaICMS(ICMSDTO icmsdto) {
        ICMS icms;
        if (icmsdto != null) {
            icms = new ICMS();
            icms.setOrigem(icmsdto.getOrigem() != null ? icmsdto.getOrigem() : null);
            icms.setCst(icmsdto.getCst() != null ? icmsdto.getCst() : null);
            icms.setAliquota(icmsdto.getAliquota() != 0 ? icmsdto.getAliquota() : 0);
            icms.setValor(icmsdto.getValor() != 0 ? icmsdto.getValor() : 0);
            icms.setFundoCombatePobreza(convertFundoCombatePobreza(icmsdto.getFundoCombatePobreza() != null ? icmsdto.getFundoCombatePobreza() : null));
            icms.setBaseCalculo(convertBaseCalculo(icmsdto.getBaseCalculo() != null ? icmsdto.getBaseCalculo() : null));
            icms.setEstado(icmsdto.getEstado() != null ? icmsdto.getEstado() : null);

            icms.setTipoImposto(icmsdto.getTipoImposto() != null ? icmsdto.getTipoImposto() : null);
        } else {
            throw new NFEException("Informar um json valido");
        }

        em.persist(icms.getBaseCalculo());
        em.persist(icms.getFundoCombatePobreza());
        em.persist(icms);
        return icms;
    }

    private FundoCombatePobreza convertFundoCombatePobreza(FundoCombatePobrezaDTO fundoCombatePobrezaDTO) {
        FundoCombatePobreza fundoCombatePobreza;

        if (fundoCombatePobrezaDTO != null) {
            fundoCombatePobreza = new FundoCombatePobreza();
            fundoCombatePobreza.setValor(fundoCombatePobrezaDTO.getValor() != 0 ? fundoCombatePobrezaDTO.getValor() : 0);
            fundoCombatePobreza.setAliquota(fundoCombatePobrezaDTO.getAliquota() != 0 ? fundoCombatePobrezaDTO.getValor() : 0);
            fundoCombatePobreza.setSubstituicaoTributaria(convertSubstituicaoTributaria(fundoCombatePobrezaDTO.getSubstituicaoTributaria()));
        } else {
            fundoCombatePobreza = null;
        }
        if(fundoCombatePobreza != null){
            em.persist(fundoCombatePobreza.getSubstituicaoTributaria());
            em.persist(fundoCombatePobreza);

        }

        return fundoCombatePobreza;
    }

    private SubstituicaoTributaria convertSubstituicaoTributaria(SubstituicaoTributariaDTO substituicaoTributariaDTO) {
        SubstituicaoTributaria substituicaoTributaria;
        if (substituicaoTributariaDTO != null) {
            substituicaoTributaria = new SubstituicaoTributaria();
            substituicaoTributaria.setAliquota(substituicaoTributariaDTO.getAliquota() != 0 ? substituicaoTributariaDTO.getAliquota() : 0);
            substituicaoTributaria.setMargemValorAdicionado(substituicaoTributariaDTO.getMargemValorAdicionado() != 0 ? substituicaoTributariaDTO.getMargemValorAdicionado() : 0);
        } else {
            substituicaoTributaria = null;
        }
        em.persist(substituicaoTributaria);
        return substituicaoTributaria;
    }

    private BaseCalculo convertBaseCalculo(BaseCalculoDTO baseCalculoDTO) {
        BaseCalculo baseCalculo;
        if (baseCalculoDTO != null) {
            baseCalculo = new BaseCalculo();
            baseCalculo.setValor(baseCalculoDTO.getValor() != 0 ? baseCalculoDTO.getValor() : 0);
            baseCalculo.setTipoDaBaseDeCalculo(baseCalculoDTO.getTipoDaBaseDeCalculo() != null ? baseCalculo.getTipoDaBaseDeCalculo() : null);
            baseCalculo.setModalidadeDeterminacao(baseCalculoDTO.getModalidadeDeterminacao() != null ? baseCalculoDTO.getModalidadeDeterminacao() : null);
            baseCalculo.setPercentualReducao(baseCalculoDTO.getPercentualReducao() != 0 ? baseCalculoDTO.getPercentualReducao() : 0);
        } else {
            baseCalculo = null;
        }
        em.persist(baseCalculo);
        return baseCalculo;
    }


}
