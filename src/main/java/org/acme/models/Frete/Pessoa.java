package org.acme.models.Frete;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Getter;
import lombok.Setter;
import org.acme.Util.InterfacesUtil.Model;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class Pessoa extends PanacheEntityBase implements Model {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String uuid;
    private Integer codigo;

    private String nome;

    private String nomeAbreviado;

    private String cnpjCpf;

    private String codigoErp;

    private String inscricaoEstadual;

    private String inscricaoMunicipal;


    @Column(name = "url", nullable = false, length = 106)
    private String url;



    private Boolean inativo;

    private String email;

    private String ddd;

    private String telefone;

    private String dddIntegracao;

    private String telefoneIntegracao;

    private Date dataNascimento;

    private Boolean optanteSimples;

    private Boolean provisorio;
}
