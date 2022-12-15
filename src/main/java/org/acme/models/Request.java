package org.acme.models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class Request implements Model{
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String uuid;
    private String numberRequest;
    @ManyToMany
    @JoinTable(name = "productsToResquest",
    joinColumns =
            {@JoinColumn(name = "product_id")},
            inverseJoinColumns = @JoinColumn(name = "request_id"))
    private Set<Product> products;
    private LocalDate createDate;
    private LocalDate finishDate;
    @ManyToOne
    private StatusRequest status;
    private Double value;
}
