package org.acme.models.enums;

public enum Estado {
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
