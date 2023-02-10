package org.acme.models.NFE.config;

import br.com.swconsultoria.certificado.Certificado;
import br.com.swconsultoria.certificado.CertificadoService;
import br.com.swconsultoria.nfe.Nfe;
import br.com.swconsultoria.nfe.dom.ConfiguracoesNfe;
import br.com.swconsultoria.nfe.dom.enuns.AmbienteEnum;
import br.com.swconsultoria.nfe.dom.enuns.EstadosEnum;
import br.com.swconsultoria.nfe.schema_4.enviNFe.*;
import br.com.swconsultoria.nfe.util.ChaveUtil;
import br.com.swconsultoria.nfe.util.ConstantesUtil;
import br.com.swconsultoria.nfe.util.XmlNfeUtil;
import lombok.Getter;
import lombok.Setter;
import org.acme.exceptions.ValidacaoException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Getter
@Setter
public class ConfigNFE {

    private Certificado certificado;
    private TEnviNFe NFEMontada;
    private TEnviNFe tEnviNFe;
    private ConfiguracoesNfe configuracoesNfe;
    private ChaveUtil chaveUtil ;
    private String cnpj;
    private String modelo;
    private int serie ;
    private int numeroNf;
    private String tipoEmissao;
    private String cNF = String.format("#08d",new Random().nextInt(99999999));
    private LocalDateTime dataEmissao;

    public ConfigNFE() {
        this.cnpj = "11111111000111";
        this.modelo = "55";
        this.serie = 1;
        this.numeroNf = 92723;
        this.tipoEmissao = "1";
        this.dataEmissao = LocalDateTime.now();
        this.chaveUtil = montaChaveNFe(configuracoesNfe);
        this.configuracoesNfe = configNFE();
        this.tEnviNFe = montaNFE();
        this.NFEMontada = criaTEnviNFE();
    }

    private ChaveUtil montaChaveNFe(ConfiguracoesNfe configuracoesNfe) {
        return new ChaveUtil(configuracoesNfe.getEstado(),
                getCnpj(),
                getModelo(),
                getSerie(),
                getNumeroNf(),
                getTipoEmissao(),
                getCNF(),
                getDataEmissao()
                );
    }

    private TEnviNFe criaTEnviNFE() {
        TNFe nfe = new TNFe();
        TNFe.InfNFe infNFe = getinfNFe();
        nfe.setInfNFe(infNFe);
        tEnviNFe.getNFe().add(nfe);
        return tEnviNFe;
    }

    private TNFe.InfNFe getinfNFe() {
        TNFe.InfNFe infNFe = new TNFe.InfNFe();
        infNFe.setId(getChaveUtil().getChaveNF());
        infNFe.setVersao(ConstantesUtil.VERSAO.NFE);
        infNFe.setIde(montaIde());
        infNFe.setEmit(montaEmitente());
        infNFe.setDest(montaDestinatario());
        infNFe.getDet().addAll(montaDet());
        infNFe.setTransp(montaTransp());
        infNFe.setPag(montaPag());
        infNFe.setInfRespTec(montaRespTecnico());
        infNFe.setTotal(montaTotal());
        return infNFe;
    }

    private TNFe.InfNFe.Total montaTotal() {
        TNFe.InfNFe.Total total = new TNFe.InfNFe.Total();
        TNFe.InfNFe.Total.ICMSTot icmsTot = new TNFe.InfNFe.Total.ICMSTot();
        icmsTot.setVBC("0.00");
        icmsTot.setVICMS("0.00");
        icmsTot.setVFCP("0.00");
        icmsTot.setVICMSDeson("0.00");
        icmsTot.setVBCST("0.00");
        icmsTot.setVST("0.00");
        icmsTot.setVFCPSTRet("0.00");
        icmsTot.setVProd("10.00");
        icmsTot.setVSeg("0.00");
        icmsTot.setVDesc("0.00");
        icmsTot.setVII("0.00");
        icmsTot.setVIPI("0.00");
        icmsTot.setVIPIDevol("0.00");
        icmsTot.setVPIS("0.17");
        icmsTot.setVCOFINS("0.76");
        icmsTot.setVNF("0.00");
        icmsTot.setVOutro("0.00");
        total.setICMSTot(icmsTot);
        return total;
    }

    private TInfRespTec montaRespTecnico() {
        TInfRespTec tInfRespTec = new TInfRespTec();
        tInfRespTec.setCNPJ("11111111000111");
        tInfRespTec.setFone("47999999999");
        tInfRespTec.setXContato("Teste");
        tInfRespTec.setEmail("teste@email.com");
        return tInfRespTec;
    }

    private TNFe.InfNFe.Pag montaPag() {
        TNFe.InfNFe.Pag pag = new TNFe.InfNFe.Pag();
        TNFe.InfNFe.Pag.DetPag detPag = new TNFe.InfNFe.Pag.DetPag();
        detPag.setVPag("01");
        detPag.setVPag("10.00");
        pag.getDetPag().add(detPag);
        return pag;
    }

    private TNFe.InfNFe.Transp montaTransp() {
        TNFe.InfNFe.Transp transp = new TNFe.InfNFe.Transp();
        transp.setModFrete("9");
        return transp;
    }

    private List<TNFe.InfNFe.Det> montaDet() {
        List<TNFe.InfNFe.Det> listProdutos = new ArrayList<>();
        TNFe.InfNFe.Det det1 = new TNFe.InfNFe.Det();
        TNFe.InfNFe.Det.Prod produto = montaProduto();
        det1.setProd(produto);
        det1.setImposto(montaImposto());
        listProdutos.add(det1);
        return listProdutos;
    }

    private TNFe.InfNFe.Det.Imposto montaImposto() {
        TNFe.InfNFe.Det.Imposto imposto = new TNFe.InfNFe.Det.Imposto();
        montaICMS60(imposto);
        criaImpostoPis(imposto);
        criaImpostoCofins(imposto);
        return imposto;
    }

    private void criaImpostoPis(TNFe.InfNFe.Det.Imposto imposto) {
        TNFe.InfNFe.Det.Imposto.PIS pis = new TNFe.InfNFe.Det.Imposto.PIS();
        TNFe.InfNFe.Det.Imposto.PIS.PISAliq pisaliq = new TNFe.InfNFe.Det.Imposto.PIS.PISAliq();
        pisaliq.setCST("01");
        pisaliq.setVBC("10.00");
        pisaliq.setPPIS("1.65");
        pisaliq.setVPIS("0.17");
        pis.setPISAliq(pisaliq);
        imposto.getContent().add(new ObjectFactory().createTNFeInfNFeDetImpostoPIS(pis));
    }
    private void criaImpostoCofins(TNFe.InfNFe.Det.Imposto imposto) {
        TNFe.InfNFe.Det.Imposto.COFINS cofins = new TNFe.InfNFe.Det.Imposto.COFINS();
        TNFe.InfNFe.Det.Imposto.COFINS.COFINSAliq cofinsAliq = new TNFe.InfNFe.Det.Imposto.COFINS.COFINSAliq();
        cofinsAliq.setCST("01");
        cofinsAliq.setVBC("10.00");
        cofinsAliq.setPCOFINS("7.60");
        cofinsAliq.setVCOFINS("0.76");
        cofins.setCOFINSAliq(cofinsAliq);
        imposto.getContent().add(new ObjectFactory().createTNFeInfNFeDetImpostoCOFINS(cofins));
    }

    private void montaICMS60(TNFe.InfNFe.Det.Imposto imposto) {
        TNFe.InfNFe.Det.Imposto.ICMS icms = new TNFe.InfNFe.Det.Imposto.ICMS();
        TNFe.InfNFe.Det.Imposto.ICMS.ICMS60 icms60 = new TNFe.InfNFe.Det.Imposto.ICMS.ICMS60();
        icms60.setOrig("0");
        icms60.setCST("60");
        icms60.setVBCSTRet("0.00");
        icms60.setPST("0.00");
        icms.setICMS60(icms60);
        imposto.getContent().add(new ObjectFactory().createTNFeInfNFeDetImpostoICMS(icms));
    }

    private TNFe.InfNFe.Det.Prod montaProduto() {
        TNFe.InfNFe.Det.Prod produto = new TNFe.InfNFe.Det.Prod();
        produto.setCProd("123");
        produto.setCEAN("SEM GTIN");
        produto.setXProd("Produto xyz");
        produto.setNCM("27101932");
        produto.setCEST("0600500");
        produto.setIndEscala("S");
        produto.setCFOP("5405");
        produto.setUCom("UN");
        produto.setQCom("1");
        produto.setVUnCom("10.00");
        produto.setVProd("10");
        produto.setCEANTrib("SEM GTIN");
        produto.setUTrib("UN");
        produto.setQTrib("1");
        produto.setVUnTrib("10");
        produto.setIndTot("1");
        return  produto;
    }

    private TNFe.InfNFe.Dest montaDestinatario() {
        TNFe.InfNFe.Dest dest = new TNFe.InfNFe.Dest();
        TEndereco endereco = new TEndereco();
        endereco.setXLgr("Rua da Creche");
        endereco.setNro("123");
        endereco.setXCpl("Qd 1 Lote 1");
        endereco.setXBairro("Senador Camará");
        endereco.setCMun(TUf.RJ.toString());
        endereco.setXMun("Rio de Janeiro");
        endereco.setUF(TUf.RJ);
        endereco.setCEP("21843197");

        dest.setEnderDest(endereco);
        return dest;
    }

    private TNFe.InfNFe.Emit montaEmitente() {
        TNFe.InfNFe.Emit emit = new TNFe.InfNFe.Emit();
        emit.setXNome("Nome empresa");
        emit.setCNPJ(cnpj);
        emit.setIE("111111111");
        emit.setCRT("3");
        emit.setEnderEmit(getEnderecoEmitente());

        return emit;
    }

    private TEnderEmi getEnderecoEmitente(){
        TEnderEmi enderecoEmitente = new TEnderEmi();
        enderecoEmitente.setXLgr("Rua soure");
        enderecoEmitente.setNro("123");
        enderecoEmitente.setXCpl("Qd 1 Lote 1");
        enderecoEmitente.setXBairro("Centro");
        enderecoEmitente.setCMun(configuracoesNfe.getEstado().getCodigoUF());
        enderecoEmitente.setXMun("Joinville");
        enderecoEmitente.setUF(TUfEmi.SC);
        enderecoEmitente.setCEP("12123-123");
        return enderecoEmitente;
    }
    private TNFe.InfNFe.Ide montaIde() {
        TNFe.InfNFe.Ide ide = new TNFe.InfNFe.Ide();
        ide.setCUF(configuracoesNfe.getEstado().getCodigoUF());
        ide.setCNF(getCNF());
        ide.setNatOp("Venda NFE");
        ide.setMod(getModelo());
        ide.setSerie(String.valueOf(getSerie()));
        ide.setNNF(String.valueOf(getNumeroNf()));
        ide.setDhEmi(XmlNfeUtil.dataNfe(getDataEmissao()));
        ide.setTpNF("1");
        ide.setIdDest("2");
        ide.setCMunFG(configuracoesNfe.getEstado().getCodigoUF());
        ide.setTpImp("1");
        ide.setTpEmis(getTipoEmissao());
        ide.setCDV(chaveUtil.getDigitoVerificador());
        ide.setTpAmb(configuracoesNfe.getAmbiente().getCodigo());
        ide.setFinNFe("1");
        ide.setIndFinal("0");
        ide.setIndPres("1");
        ide.setProcEmi("0");
        ide.setVerProc("1.0.0");
        return ide;
    }

    private ConfiguracoesNfe configNFE(){
        try{
            certificado = CertificadoService.getCertificadoByCnpjCpf("49458807000161");
            ConfiguracoesNfe configuracoesNfe = ConfiguracoesNfe.criarConfiguracoes(EstadosEnum.SC, AmbienteEnum.HOMOLOGACAO, certificado, "./org/acme/schemas");
            return configuracoesNfe;
        }catch (Throwable t){
            t.printStackTrace();
            throw new ValidacaoException("Erro na configuração do certificado");
        }

    }

    private TEnviNFe montaNFE(){
        try{
            return Nfe.montaNfe(configNFE(), NFEMontada, false);
        }catch (Throwable t){
            t.printStackTrace();
            throw new ValidacaoException("Erro na montagem da NFE");
        }
    }
}
