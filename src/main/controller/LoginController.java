package main.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import main.model.Model;

import java.io.IOException;

public class LoginController {

    private MainController mainController;

    void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    @FXML
    private TextField loginField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private CheckBox employeeCheckBox;
    @FXML
    private Text wrongDataText;

    @FXML
    public void signIn() {
        String login = loginField.getText();
        String password = passwordField.getText();
        if (employeeCheckBox.isSelected()) {
            if (Model.get().login(login, password, true)) {
                loadEmployeePanelScreen();
                wrongDataText.setVisible(false);
            } else wrongDataText.setVisible(true);
        } else if (Model.get().login(login, password, false)) {
            loadCustomerPanelScreen();
            wrongDataText.setVisible(false);
        } else wrongDataText.setVisible(true);
    }

    private void loadEmployeePanelScreen() {
        FXMLLoader loader = new FXMLLoader(EmployeePanelController.class.getResource("/main/fxml/employeePanelScreen.fxml"));
        try {
            Pane employeePanelScreen = loader.load();
            EmployeePanelController employeePanelController = loader.getController();
            employeePanelController.setMainController(mainController);
            mainController.setScene(employeePanelScreen);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadCustomerPanelScreen() {
        FXMLLoader loader = new FXMLLoader(CustomerPanelController.class.getResource("/main/fxml/customerPanelScreen.fxml"));
        try {
            Pane customerPanelPane = loader.load();
            CustomerPanelController customerPanelController = loader.getController();
            customerPanelController.setMainController(mainController);
            mainController.setScene(customerPanelPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

