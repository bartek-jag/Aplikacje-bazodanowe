package main.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import main.controller.add.*;
import main.model.Model;

import java.util.HashMap;

public class EmployeePanelController {

    void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    private MainController mainController;

    private HashMap<String, HBox> screens = new HashMap<>();

    @FXML
    private HBox employeePanelHBox;

    @FXML private Button logonRegistryButton;

    @FXML
    private void logout(){
        Model.get().logout();
        Model.get().destroy();
        mainController.initialize();
    }

    @FXML
    private FXMLLoader loadScreen(String fxml){
        return Utils.load(screens, employeePanelHBox, fxml);
    }

    @FXML
    private void loadOrderDetailsViewScreen(){
        Utils.loadODScreen(employeePanelHBox);
    }

    @FXML
    private void loadCustomersScreen(){
        FXMLLoader loader = loadScreen("addCustomerScreen");
        if (loader != null) {
            AddCustomerController addCustomerController = loader.getController();
            employeePanelHBox.getChildren().add(1, screens.get("addCustomerScreen"));
            if (Model.get().getUserType() != Model.User.ADMIN)
                addCustomerController.getFormHBox().getChildren().remove(0);
        } else
            employeePanelHBox.getChildren().add(screens.get("addCustomerScreen"));
    }

    @FXML
    private void loadEmployeesScreen(){
        FXMLLoader loader = loadScreen("addEmployeeScreen");
        if (loader != null) {
            AddEmployeeController addEmployeeController = loader.getController();
            employeePanelHBox.getChildren().add(1, screens.get("addEmployeeScreen"));
            if (Model.get().getUserType() != Model.User.ADMIN)
                addEmployeeController.getFormHBox().getChildren().remove(0);
        } else
            employeePanelHBox.getChildren().add(screens.get("addEmployeeScreen"));
    }

    @FXML
    private void loadBeverageNameScreen(){
        FXMLLoader loader = loadScreen("addBeverageNameScreen");
        if (loader != null){
            AddBeverageNameController addBeverageNameController = loader.getController();
            employeePanelHBox.getChildren().add(1, screens.get("addBeverageNameScreen"));
            if (Model.get().getUserType() != Model.User.ADMIN)
                addBeverageNameController.getFormHBox().getChildren().remove(0);
    } else
            employeePanelHBox.getChildren().add(screens.get("addBeverageNameScreen"));
    }

    @FXML
    private void loadBeverageTypeScreen(){
        FXMLLoader loader = loadScreen("addBeverageTypeScreen");
        if (loader != null){
            AddBeverageTypeController addBeverageTypeController = loader.getController();
            employeePanelHBox.getChildren().add(1, screens.get("addBeverageTypeScreen"));
            if (Model.get().getUserType() != Model.User.ADMIN)
                addBeverageTypeController.getFormHBox().getChildren().remove(0);
        } else
            employeePanelHBox.getChildren().add(screens.get("addBeverageTypeScreen"));
    }

    @FXML
    private void loadFlavorScreen(){
        FXMLLoader loader = loadScreen("addFlavorScreen");
        if (loader != null){
            AddFlavorController addFlavorController = loader.getController();
            employeePanelHBox.getChildren().add(1, screens.get("addFlavorScreen"));
            if (Model.get().getUserType() != Model.User.ADMIN)
                addFlavorController.getFormHBox().getChildren().remove(0);
        } else
            employeePanelHBox.getChildren().add(screens.get("addFlavorScreen"));
    }

    @FXML
    private void loadManufacturerScreen(){
        FXMLLoader loader = loadScreen("addManufacturerScreen");
        if (loader != null){
            AddManufacturerController addManufacturerController = loader.getController();
            employeePanelHBox.getChildren().add(1, screens.get("addManufacturerScreen"));
            if (Model.get().getUserType() != Model.User.ADMIN)
                addManufacturerController.getFormHBox().getChildren().remove(0);
        } else
            employeePanelHBox.getChildren().add(screens.get("addManufacturerScreen"));
    }

    @FXML
    private void loadPackageScreen(){
        FXMLLoader loader = loadScreen("addPackageScreen");
        if (loader != null){
            AddPackageController addPackageController = loader.getController();
            employeePanelHBox.getChildren().add(1, screens.get("addPackageScreen"));
            if (Model.get().getUserType() != Model.User.ADMIN)
                addPackageController.getFormHBox().getChildren().remove(0);
        } else
            employeePanelHBox.getChildren().add(screens.get("addPackageScreen"));
    }

    @FXML
    private void loadProviderScreen(){
        FXMLLoader loader = loadScreen("addProviderScreen");
        if (loader != null){
            AddProviderController addProviderController = loader.getController();
            employeePanelHBox.getChildren().add(1, screens.get("addProviderScreen"));
            if (Model.get().getUserType() != Model.User.ADMIN)
                addProviderController.getFormHBox().getChildren().remove(0);
        } else
            employeePanelHBox.getChildren().add(screens.get("addProviderScreen"));
    }

    @FXML
    private void loadOorderScreen(){
        FXMLLoader loader = loadScreen("addOorderScreen");
        if (loader != null){
            AddOorderController addOorderController = loader.getController();
            employeePanelHBox.getChildren().add(1, screens.get("addOorderScreen"));
            if (Model.get().getUserType() != Model.User.ADMIN)
                addOorderController.getFormHBox().getChildren().remove(0);
        } else
            employeePanelHBox.getChildren().add(screens.get("addOorderScreen"));
    }

    @FXML
    private void loadBeverageScreen(){
        FXMLLoader loader = loadScreen("addBeverageScreen");
        if (loader != null){
            AddBeverageController beverageController = loader.getController();
            employeePanelHBox.getChildren().add(1, screens.get("addBeverageScreen"));
            if (Model.get().getUserType() != Model.User.ADMIN)
                beverageController.getFormHBox().getChildren().remove(0);
        } else
            employeePanelHBox.getChildren().add(screens.get("addBeverageScreen"));
    }

    @FXML
    private void loadOrderDetailsScreen(){
        FXMLLoader loader = loadScreen("addOrderDetailsScreen");
        if (loader != null){
            AddOrderDetailsController orderDetailsController = loader.getController();
            employeePanelHBox.getChildren().add(1, screens.get("addOrderDetailsScreen"));
            if (Model.get().getUserType() != Model.User.ADMIN)
                orderDetailsController.getFormHBox().getChildren().remove(0);
        } else
            employeePanelHBox.getChildren().add(screens.get("addOrderDetailsScreen"));
    }

    @FXML
    private void loadLogonRegistryScreen(){
        loadScreen("logonRegistryScreen");
        employeePanelHBox.getChildren().add(1, screens.get("logonRegistryScreen"));
    }

    @FXML
    void initialize(){
        if (Model.get().getUserType() != Model.User.ADMIN)
            logonRegistryButton.setDisable(true);
        loadOrderDetailsViewScreen();
    }
}