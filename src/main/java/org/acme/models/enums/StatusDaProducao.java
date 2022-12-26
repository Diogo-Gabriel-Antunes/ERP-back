package org.acme.models.enums;

import java.util.ArrayList;
import java.util.List;

public enum StatusDaProducao {
    FORNECEDOR,PRODUCAO,EMBALAGEM,FINALIZADO;

    public static List<StatusDaProducao> getAll() {
        List<StatusDaProducao> statusDaProducaos = new ArrayList<>() {{
            add(StatusDaProducao.FORNECEDOR);
            add(StatusDaProducao.PRODUCAO);
            add(StatusDaProducao.EMBALAGEM);
            add(StatusDaProducao.FINALIZADO);
        }};
        return statusDaProducaos;
    }
}
