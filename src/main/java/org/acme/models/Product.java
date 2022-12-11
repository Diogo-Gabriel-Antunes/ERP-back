package org.acme.models;

import lombok.Getter;
import lombok.Setter;
import org.acme.models.enums.Status;
import org.hibernate.annotations.Columns;
import org.hibernate.annotations.GenericGenerator;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
public class Product implements Model {

    @Id @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String uuid;
    private String name;
    private String codeBar;
    @ManyToOne(targetEntity = Category.class)
    private Category category;
    private LocalDate dateCreate;

    private LocalDate dataAlteracao;

    private boolean status;

    @ManyToMany(mappedBy = "products")
    @JsonbTransient
    private Set<Request> requests;


}
