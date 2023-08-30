package br.com.advocacia;

import br.com.advocacia.domain.entity.Advogado;
import br.com.advocacia.domain.entity.Estado;
import br.com.advocacia.domain.entity.Processo;
import br.com.advocacia.domain.entity.TipoDeAcao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import javax.swing.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("maria-db");
        EntityManager manager = factory.createEntityManager();

        //salvarProcesso(manager);

        //findById(manager);

        List<Processo> list = manager.createQuery("SELECT p FROM Processo p").getResultList();
        list.forEach(System.out::println);

        manager.close();
        factory.close();
    }

    private static void findById(EntityManager manager) {
        Long id = Long.valueOf(JOptionPane.showInputDialog("Informe o Id do Processo: "));
        Processo processo = manager.find(Processo.class, id);
        System.out.println(processo);
    }

    private static void salvarProcesso(EntityManager manager) {
        TipoDeAcao tpAcao = new TipoDeAcao(null, "Guarda Infantil");

        Estado uf = new Estado(null, "Minas Gerais", "MG");

        Advogado advogado1 = new Advogado(null, "AniFrancio Ferreira", "123ahs", uf);

        Processo proc1 = new Processo()
                .setAdvogado(advogado1)
                .setNumero("1234.4567.4567.45-45")
                .setProBono(true)
                .setTipoDeAcao(tpAcao);

        manager.getTransaction().begin();
        manager.persist(proc1);
        manager.getTransaction().commit();

        System.out.println(proc1);
    }
}