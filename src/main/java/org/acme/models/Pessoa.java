package org.acme.models;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.time.LocalDate;

@Getter
@Setter
@Entity
public class Pessoa extends PanacheEntityBase {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String uuid;
    private LocalDate alteradoEm;

    private LocalDate criadoEm;

    private String usuarioCriacao;

    private String usuarioAlteracao;

    private Integer versao;

    private String empresa;

    private String cnpjCpf;

    private Integer codigo;

    private LocalDate datanascimento;

    private String estadocivil;

    private String grauinstrucao;

    private String nomemae;

    private String nomepai;

    private String sexo;


    private String ddd;

    private String email;

    private String bairro;

    private String cep;

    private String complemento;

    private Double latitude;

    private String logradouro;

    private Double longitude;

    private String numero;

    private String oid_cidade;

    private Boolean inativo;

    private String inscr_est;

    private String inscr_mun;

    private String nome;

    private String nome_abreviado;

    private String oid_ramo_atividade;

    private Boolean optante_simples;

    private String telefone;

    private String url;
}
