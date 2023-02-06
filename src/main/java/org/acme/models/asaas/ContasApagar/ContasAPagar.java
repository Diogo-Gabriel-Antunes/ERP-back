package org.acme.models.asaas.ContasApagar;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Getter;
import lombok.Setter;
import org.acme.models.Model;
import org.acme.models.asaas.Status;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class ContasAPagar extends PanacheEntityBase implements Model {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String uuid;
    private String identificationField;
    private Status status;
    private String scheduleDate;
    private String description;
    private double discount;
    private double interest;
    private double fine;
    private String dueDate;
    private String paymentDate;
    private String fee;
    private String companyName;
    private String transactionReceiptUrl;
    private String canBeCancelled;
    private String failReasons;
    private double value;
    private boolean retorno;
    @OneToOne
    private ContasAPagar contasAPagarRetorno;
}
