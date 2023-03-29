package org.acme.models;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Getter;
import lombok.Setter;
import org.acme.Util.InterfacesUtil.Model;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Devolucao extends PanacheEntityBase implements Model {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String uuid;
    @ManyToOne
    private MotivoDaDevolucao motivoDevolucao;
    private LocalDateTime dataDaDevolucao;
    @ManyToOne
    private Pedido pedido;
    private String descricao;
    private LocalDateTime dataDeCriacao;
    private LocalDateTime ultimaAtualizacao;

    public Devolucao() {
        motivoDevolucao = new MotivoDaDevolucao();
    }
}

