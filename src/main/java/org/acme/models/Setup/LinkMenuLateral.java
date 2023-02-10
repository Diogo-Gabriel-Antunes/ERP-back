package org.acme.models.Setup;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LinkMenuLateral {
    private String linkPag;
    private String titulo;

    public LinkMenuLateral(String linkPag, String titulo) {
        this.linkPag = linkPag;
        this.titulo = titulo;
    }
}
