package main.model;

import main.HibernateUtil;
import main.entity.CustomerEntity;
import main.entity.EmployeeEntity;
import main.entity.LogonRegistryEntity;
import main.entity.OorderEntity;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class Model {

    private static Model instance;

    public static synchronized Model get() {
        if (instance == null)
            instance = new Model();
        return instance;
    }

    public void destroy(){
        instance = null;
    }

    public enum User {CUSTOMER, EMPLOYEE, ADMIN}

    private Session session;

    private CustomerEntity user;

    public User getUserType() {
        return userType;
    }

    private User userType;

    public Session getSession() {
        return session;
    }

    public Model() {
        instance = this;
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
    }

    public List loadTable(String entity) {
        if (userType != User.CUSTOMER)
            return session.createQuery("from " + entity).list();
        List<Integer> ordersIds = new ArrayList<>();
        Collection<OorderEntity> orders = user.getOordersById();
        for (OorderEntity ord : orders){
            ordersIds.add(ord.getId());
        }
        Query query = session.createQuery("from OrdersViewEntity where id in :ordersIds");
        query.setParameterList("ordersIds", ordersIds);

        return query.list();
    }

    public List loadOrderDetails(int id) {
        return session.createQuery("from OrderDetailsViewEntity where id = " + id).list();
    }

    public boolean login(String login, String password, boolean isEmployee) {
        if (isEmployee) {
            List employees = session.createQuery("from EmployeeEntity where login=\'" + login + "\'").list();
            if (employees.size() == 1)
                if (((EmployeeEntity) employees.get(0)).getPassword().equals(get_SHA_512_SecurePassword(password))) {
                    //System.out.println("Password does match!");
                    newLogon(login);
                    if (login.equals("admin"))
                        userType = User.ADMIN;
                    else
                        userType = User.EMPLOYEE;
                    return true;
                }
        } else {
            List customers = session.createQuery("from CustomerEntity where login=\'" + login + "\'").list();
            if (customers.size() == 1)
                if (((CustomerEntity) customers.get(0)).getPassword().equals(get_SHA_512_SecurePassword(password))) {
                    userType = User.CUSTOMER;
                    user = (CustomerEntity) customers.get(0);
                    //System.out.println("Password does match!");
                    newLogon(login);
                    return true;
                }
        }
        //System.out.println("Password does not match!");
        return false;
    }

    public void logout(){
        session.getTransaction().commit();
        session.close();
    }

    private void newLogon(String login){
        LogonRegistryEntity logonRegistryEntity = new LogonRegistryEntity();
        Date date = new Date();
        logonRegistryEntity.setDate(new Timestamp(date.getTime()));
        logonRegistryEntity.setLogin(login);
        session.save(logonRegistryEntity);
    }

    public static String get_SHA_512_SecurePassword(String passwordToHash) {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] bytes = md.digest(passwordToHash.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte aByte : bytes) {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }
}
