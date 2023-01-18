package org.acme.models.Nota_fiscal_eletronica;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Pagamento {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String uuid;
    private boolean aVista;
    @Enumerated(EnumType.STRING)
    private Meio meio;
    private String descricaoMeio;
    private double valor;
    @ManyToOne
    private Cartao cartao;
}
enum Meio{
    DINHEIRO("01"),
    CHEQUE("02"),
    CARTAO_CREDITO("03"),
    CARTAO_DEBITO("04"),
    CREDITO_LOJA("05"),
    VALE_ALIMENTACAO("10"),
    VALE_REFEICAO("11"),
    VALE_PRESENTE("12"),
    VALE_COMBUSTIVEL("13"),
    BOLETO_BANCARIO("15"),
    DEPOSITO_BANCARIO("16"),
    PAGAMENTO_INSTANTANEO("17"),
    TRANSFERENCIA_BANCARIA("18"),
    CARTEIRA_DIGITAL("18"),
    PROGRAMA_FIDELIDADE("19"),
    CASHBACK("19"),
    CREDITO_VIRTUAL("19"),
    SEM_PAGAMENTO("90"),
    OUTROS("99");

    private String meio;

    Meio(String meio) {
        this.meio = meio;
    }

    public String getMeio() {
        return meio;
    }
}