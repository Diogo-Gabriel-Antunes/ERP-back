package org.acme.models;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Getter;
import lombok.Setter;
import org.acme.Util.FieldUtil;
import org.acme.models.DTO.ClienteDTO;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.time.LocalDate;

@Getter
@Setter
@Entity
public class Cliente extends PanacheEntityBase implements Model {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String uuid;
    private String cnpj;
    private String cpf;
    private String idEstrangeiro;
    private String xNome;
    private String indIEDest;
    private String ie;
    private String isuf;
    private String im;
    private String email;
    @OneToOne
    @Cascade(CascadeType.SAVE_UPDATE)
    private Endereco endereco;
    private LocalDate dataCriacao;
    private LocalDate ultimaAtualização;

    public static Cliente ClienteCreator(ClienteDTO responsavelPelaVenda) {
        Cliente cliente = new Cliente();
        FieldUtil fieldUtil = new FieldUtil();
        fieldUtil.updateFieldsDtoToModel(cliente,responsavelPelaVenda);
        return cliente;
    }
}
