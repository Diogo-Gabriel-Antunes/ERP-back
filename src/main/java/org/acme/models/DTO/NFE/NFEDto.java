package org.acme.models.DTO.NFE;

import lombok.Getter;
import lombok.Setter;
import org.acme.models.DTO.DTO;
import org.acme.models.Itens;
import org.acme.models.Nota_fiscal_eletronica.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
public class NFEDto implements DTO {
    private String idIntegracao;
    private String versaoManual;
    private String codigo;
    private String chave;
    private Integer serie;
    private Finalidade finalidade;
    private String natureza;
    private String dataEmissao;
    private String dataSaidaEntrada;
    private boolean saida;
    private boolean presencial;
    private TipoImpressao tipoImpressao;
    private TipoEmissao tipoEmissao;
    private CodigoIdentificacaoDestino codigoIdentificacaoDestino;
    private String codigoMunicipioFatoGerador;
    private boolean consumidorFinal;
    private Pessoa emitente;
    private Pessoa destinatario;
    private List<Itens> itens;
    private boolean compoeTotal;
    private String nve;
    private String indicadorEscalaRelevante;
    private String cnpjFabricante;
    private String codigoBeneficioFiscal;
    private String exTipi;
    private double valorFrete;
    private double valorSeguro;
    private double valorOutros;
    private String numeroFci;
    private String informacoesComplementaresContribuinte;
    private String informacoesComplementares;
    private boolean enviarEmail;
    private UnidadeNFE unidade;
    private ValorUnitario valorUnitario;
    private List<Medicamentos> medicamentos;
    private Veiculo veiculo;
    private List<Armamento> armamentos;
    private Combustivel combustivel;
    private Total total;
    private Transporte transporte;
    private List<Pagamento> pagamentos;
    private Cobranca cobranca;
    private Local localEntrega;
    private Local localRetirada;
    private Exportacao exportacao;
    private IntermediadorTransacao intermediadorTransacao;
    private int intermediador;
    private Local responsavelTenico;
}
