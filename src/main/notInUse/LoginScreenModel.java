package main.model;

import main.HibernateUtil;
import main.entity.UsersEntity;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class LoginScreenModel {

    /*public void setMainScreenModel(MainScreenModel mainScreenModel) {
        this.mainScreenModel = mainScreenModel;
    }

    private MainScreenModel mainScreenModel;

    public LoginScreenModel(){}

    private void initSession(){
        mainScreenModel.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
        mainScreenModel.getSession().beginTransaction();
    }

    public boolean login(String login, String password){
        List users = mainScreenModel.getSession().createQuery("from UsersEntity where login=\'" + login + "\'").list();
        if (users.size() == 1)
            if (((UsersEntity) users.get(0)).getPassword().equals(get_SHA_512_SecurePassword(password))) {
                System.out.println("Password does match!");
                //loadEmployeePanelScreen();
                return true;
            }
        System.out.println("Password does not match!");
            return false;
    }


    private static String get_SHA_512_SecurePassword(String passwordToHash) {
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
    }*/
}
