package main.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class MainController {

    @FXML
    private Pane mainScreenPane;

    @FXML
    void initialize() {
        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/main/fxml/loginScreen.fxml"));
            setScene(loader.load());
            LoginController loginController = loader.getController();
            loginController.setMainController(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void setScene(Pane pane){
        mainScreenPane.getChildren().clear();
        mainScreenPane.getChildren().add(pane);
    }
}
