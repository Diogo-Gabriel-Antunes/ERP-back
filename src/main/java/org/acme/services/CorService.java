package org.acme.services;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.acme.models.Nota_fiscal_eletronica.Cor;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class CorService extends Service{


    public Response setupCores(){
        List<Cor> cores = Cor.listAll();

        Cor AMARELO = new Cor();
        AMARELO.setCodigoCor("01");
        AMARELO.setDescricaoCor("AMARELO");
        Cor AZUL = new Cor();
        AZUL.setCodigoCor("02");
        AZUL.setDescricaoCor("AZUL");
        Cor BEGE = new Cor();
        BEGE.setCodigoCor("03");
        BEGE.setDescricaoCor("BEGE");
        Cor BRANCA = new Cor();
        BRANCA.setCodigoCor("04");
        BRANCA.setDescricaoCor("BRANCA");
        Cor CINZA = new Cor();
        CINZA.setCodigoCor("05");
        CINZA.setDescricaoCor("CINZA");
        Cor DOURADA = new Cor();
        DOURADA.setCodigoCor("06");
        DOURADA.setDescricaoCor("DOURADA");
        Cor GRENA = new Cor();
        GRENA.setCodigoCor("07");
        GRENA.setDescricaoCor("GRENÁ");
        Cor LARANJA = new Cor();
        LARANJA.setCodigoCor("08");
        LARANJA.setDescricaoCor("LARANJA");
        Cor MARROM = new Cor();
        MARROM.setCodigoCor("09");
        MARROM.setDescricaoCor("MARROM");
        Cor PRATA = new Cor();
        PRATA.setCodigoCor("10");
        PRATA.setDescricaoCor("PRATA");
        Cor PRETA = new Cor();
        PRETA.setCodigoCor("11");
        PRETA.setDescricaoCor("PRETA");
        Cor ROSA = new Cor();
        ROSA.setCodigoCor("12");
        ROSA.setDescricaoCor("ROSA");
        Cor ROXA = new Cor();
        ROXA.setCodigoCor("13");
        ROXA.setDescricaoCor("ROXA");
        Cor VERDE = new Cor();
        VERDE.setCodigoCor("14");
        VERDE.setDescricaoCor("VERDE");
        Cor VERMELHA = new Cor();
        VERMELHA.setCodigoCor("15");
        VERMELHA.setDescricaoCor("VERMELHA");
        Cor FANTASIA = new Cor();
        FANTASIA.setCodigoCor("16");
        FANTASIA.setDescricaoCor("FANTASIA");

        List<Cor> coresParaAdicionar = new ArrayList<>();
        coresParaAdicionar.add(AMARELO);
        coresParaAdicionar.add(AZUL);
        coresParaAdicionar.add(BEGE);
        coresParaAdicionar.add(BRANCA);
        coresParaAdicionar.add(CINZA);
        coresParaAdicionar.add(DOURADA);
        coresParaAdicionar.add(GRENA);
        coresParaAdicionar.add(LARANJA);
        coresParaAdicionar.add(MARROM);
        coresParaAdicionar.add(PRATA);
        coresParaAdicionar.add(PRETA);
        coresParaAdicionar.add(ROSA);
        coresParaAdicionar.add(ROXA);
        coresParaAdicionar.add(VERDE);
        coresParaAdicionar.add(VERMELHA);
        coresParaAdicionar.add(FANTASIA);
        if(coresParaAdicionar.size() != cores.size()){
            Cor.deleteAll();
            Cor.flush();

            coresParaAdicionar.forEach(cor->{
                Cor.getEntityManager().merge(cor);
            });
            return Response.ok("Adicionado novas cores").build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    public Response findByDescricao(String descricao) {
        Cor corByDescricao = em.createQuery("select c from Cor c where LOWER( c.descricaoCor) = lower(:descricao)", Cor.class).setParameter("descricao", descricao).getSingleResult();
        if(corByDescricao != null){
            return Response.ok(corByDescricao).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
}
