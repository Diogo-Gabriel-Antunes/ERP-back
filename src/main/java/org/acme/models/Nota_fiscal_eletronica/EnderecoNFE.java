package org.acme.models.Nota_fiscal_eletronica;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class EnderecoNFE {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String uuid;
    private String bairro;
    private String cep;
    private String codigoCidade;
    @Enumerated(EnumType.STRING)
    private Estado estado;
    private  String logradouro;
    private String numero;
    @Enumerated(EnumType.STRING)
    private TipoLogradouro tipoLogradouro;
    private String codigoPais;
    private String complemento;
    private String descricaoCidade;
    private String descricaoPais;
    @Enumerated(EnumType.STRING)
    private TipoBairro tipoBairro;
}


enum Estado {
    AC("AC"), AL("AL"), AM("AM"), AP("AP"), BA("BA"), CE("CE"), DF("DF"),
    ES("ES"), GO("GO"), MA("MA"), MG("MG"), MS("MS"), MT("MT"), PA("PA"),
    PB("PB"), PE("PE"), PI("PI"), PR("PR"), RJ("RJ"), RN("RN"), RO("RO"),
    RR("RR"), RS("RS"), SC("SC"), SE("SE"), SP("SP"), TO("TO");
    private String estado;

    public String getEstado(){
        return this.estado;
    }
    Estado(String estado){
        this.estado = estado;
    }
}

enum TipoLogradouro{
    Alameda("Alameda"), Avenida("Avenida"), Chácara("Chácara"), Colônia("Colônia"),
    Condomínio("Condomínio"), Estância("Estância"), Estrada("Estrada"), Fazenda("Fazenda"),
    Praça("Praça"), Prolongamento("Prolongamento"), Rodovia("Rodovia"), Rua("Rua"),
    Sítio("Sítio"), Travessa("Travessa"), Vicinal("Vicinal"), Eqnp("Eqnp");

    private String tipo;

    public String getTipo(){
        return this.tipo;
    }

    TipoLogradouro(String tipo) {
        this.tipo = tipo;
    }
}

enum TipoBairro {
    Bairro("Bairro"), Bosque("Bosque"), Chácara("Chácara"), Conjunto("Conjunto"),
    Desmembramento("Desmembramento"), Distrito("Distrito"), Favela("Favela"), Fazenda("Fazenda"),
    Gleba("Gleba"), Horto("Horto"), Jardim("Jardim"), Loteamento("Loteamento"), Núcleo("Núcleo"),
    Parque("Parque"), Residencial("Residencial"), Sítio("Sítio"), Tropical("Tropical"), Vila("Vila"),
    Zona("Zona"), Centro("Centro"), Setor("Setor");

    private String tipo;

    public String getTipo(){
        return this.tipo;
    }
    TipoBairro(String tipo) {
        this.tipo = tipo;
    }
}