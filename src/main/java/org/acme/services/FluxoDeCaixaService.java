package org.acme.services;

import org.acme.models.asaas.ContasApagar.ContasAPagar;
import org.acme.models.Pedido;

import javax.enterprise.context.ApplicationScoped;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
public class FluxoDeCaixaService extends Service {


//    public Double getFluxoDeCaixa() {
//        LocalDate hoje = LocalDate.now();
//        LocalDate umMesAtras = null;
//        try{
//            umMesAtras =LocalDate.of(hoje.getYear(),hoje.getMonth().getValue()-1,hoje.getDayOfMonth());
//
//        } catch (DateTimeException e){
//            umMesAtras = LocalDate.of(hoje.getYear()-1,12,hoje.getDayOfMonth());
//        }
//        List<ContasAPagar> contasAPagar = em.createQuery("SELECT c FROM ContasAPagar c WHERE c.dataQueFoiPago <= :hoje AND c.dataQueFoiPago >= :umMesAtras", ContasAPagar.class)
//                .setParameter("hoje",hoje)
//                .setParameter("umMesAtras",umMesAtras)
//                .getResultList();
//        List<Pedido> pedidos = em.createQuery("SELECT r FROM Pedido r WHERE r.status.uuid = :statusId AND r.finishDate <= :hoje AND r.finishDate >= :umMesAtras" , Pedido.class)
//                .setParameter("statusId","5")
//                .setParameter("hoje",hoje)
//                .setParameter("umMesAtras",umMesAtras)
//                .getResultList();
//
//        int count = Math.max(contasAPagar.size(), pedidos.size());
//        Double ValorFinal = 0D;
//
//        for (int i = 0;i< count; i++){
//            double valor = 0d;
//            Double value = 0D;
//            if(pedidos.size() > i){
//                 value= pedidos.get(i).getValue();
//            }
//            if(contasAPagar.size() > i){
//                valor = contasAPagar.get(i).getValue();
//
//            }
//
//            ValorFinal+=value - valor;
//        }
//
//        return ValorFinal;
//
//    }
}
