package main;

import main.model.CustomerEntity;
import org.hibernate.Session;

import java.util.List;

import static main.HibernateUtil.getSessionFactory;

public class HibernateTest {

    private static void printCustomers() {
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();

        List<CustomerEntity> customers = session.createQuery("from CustomerEntity", CustomerEntity.class).list();
        for (CustomerEntity c : customers) System.out.println(c);


        session.getTransaction().commit();
    }


    public static void main(String[] args) {
/*        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Autor autor = new Autor();
        autor.setImie("Henryk");
        autor.setNazwisko("Sienkiewicz");
        session.save(autor);

        Ksiazka ksiazka = new Ksiazka();
        ksiazka.setTytul("W pustyni i w puszczy");
        ksiazka.setAutor(autor);
        session.save(ksiazka);

        session.getTransaction().commit();*/

/*        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();

        GasType gasType = new GasType();
        gasType.setGasType("Zagazowany");
        session.save(gasType);
        session.getTransaction().commit();
        getSessionFactory().close();*/

        System.out.println("Test Hibernate 01");

        System.out.println("\nCustomers:");
        printCustomers();


        //getSessionFactory().close();
    }
}