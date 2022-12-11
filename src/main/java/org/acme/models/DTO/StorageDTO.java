package org.acme.models.DTO;

import lombok.Getter;
import lombok.Setter;
import org.acme.models.Product;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.time.LocalDate;

@Getter
@Setter
public class StorageDTO implements DTO{
    private String uuid;
    private Product product;
    private Long amount;
    private LocalDate lastUpdate;
}
