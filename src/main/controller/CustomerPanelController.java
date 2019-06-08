package main.controller;

import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import main.model.Model;

public class CustomerPanelController {

    void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    private MainController mainController;

    @FXML
    private HBox employeePanelHBox;

    @FXML
    private void logout(){
        Model.get().logout();
        Model.get().destroy();
        mainController.initialize();
    }

    @FXML
    private void loadOrderDetailsScreen(){
        Utils.loadODScreen(employeePanelHBox);
    }

    @FXML
    void initialize(){
        loadOrderDetailsScreen();
    }
}