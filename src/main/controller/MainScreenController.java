package main.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import main.HibernateUtil;
import org.hibernate.Session;

import java.io.IOException;

public class MainScreenController {

    private Session session;

    @FXML
    private Pane mainScreen;

    @FXML
    private LoginScreenController loginScreenController;

    void setScene(Pane pane){
        mainScreen.getChildren().clear();
        mainScreen.getChildren().add(pane);
    }

    @FXML
    private void initialize() {
        loadLoginScreen();
    }

    void loadLoginScreen(){
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/main/fxml/loginScreen.fxml"));
        try {
            Pane loginScreen = loader.load();
            loginScreenController = loader.getController();
            loginScreenController.setMainScreenController(this);
            setScene(loginScreen);

        } catch (IOException e) {
            e.printStackTrace();
        }
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        loginScreenController.setSession(session);
    }
}
