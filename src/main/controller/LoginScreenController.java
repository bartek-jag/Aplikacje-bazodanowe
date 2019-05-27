package main.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import main.model.UsersEntity;
import org.hibernate.Session;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;


public class LoginScreenController {

    private MainScreenController mainScreenController;
    private Session session;

    void setSession(Session session) {
        this.session = session;
    }

    void setMainScreenController(MainScreenController mainScreenController) {
        this.mainScreenController = mainScreenController;
    }

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    public void signIn() {
        String login = loginField.getText();
        String password = passwordField.getText();

        List users = session.createQuery("from UsersEntity where login=\'" + login + "\'").list();
        if (users.size() == 1)
            if (((UsersEntity) users.get(0)).getPassword().equals(get_SHA_512_SecurePassword(password))) {
                System.out.println("Password does match!");
                loadEmployeePanelScreen();
                return;
            }
        System.out.println("Password does not match!");
    }

    private void loadEmployeePanelScreen() {
        FXMLLoader loader = new FXMLLoader(mainScreenController.getClass().getResource("/main/fxml/employeePanelScreen.fxml"));
        Pane employeePanelScreen;
        try {
            employeePanelScreen = loader.load();
            EmployeePanelScreenController employeePanelScreenController = loader.getController();
            employeePanelScreenController.setSession(session);
            employeePanelScreenController.setMainScreenController(mainScreenController);
            mainScreenController.setScene(employeePanelScreen);
            employeePanelScreenController.loadTable();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
    }
}

