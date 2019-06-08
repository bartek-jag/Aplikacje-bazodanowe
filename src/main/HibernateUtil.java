package main;

import main.entity.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();
            configuration.addAnnotatedClass(BeverageEntity.class);
            configuration.addAnnotatedClass(BeverageNameEntity.class);
            configuration.addAnnotatedClass(BeverageTypeEntity.class);
            configuration.addAnnotatedClass(CustomerEntity.class);
            configuration.addAnnotatedClass(EmployeeEntity.class);
            configuration.addAnnotatedClass(FlavorEntity.class);
            configuration.addAnnotatedClass(GasTypeEntity.class);
            configuration.addAnnotatedClass(ManufacturerEntity.class);
            configuration.addAnnotatedClass(OorderEntity.class);
            configuration.addAnnotatedClass(OrderDetailsEntity.class);
            configuration.addAnnotatedClass(PackageEntity.class);
            configuration.addAnnotatedClass(ProviderEntity.class);
            configuration.addAnnotatedClass(UsersEntity.class);
            configuration.addAnnotatedClass(OrdersViewEntity.class);
            configuration.addAnnotatedClass(OrderDetailsViewEntity.class);
            configuration.addAnnotatedClass(LogonRegistryEntity.class);

            StandardServiceRegistryBuilder standardServiceRegistryBuilder = new StandardServiceRegistryBuilder();
            standardServiceRegistryBuilder.applySettings(configuration.getProperties());
            ServiceRegistry serviceRegistry = standardServiceRegistryBuilder.build();

            return configuration.buildSessionFactory(serviceRegistry);
        } catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

}
