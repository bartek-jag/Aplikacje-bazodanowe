package main.controller.add;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import main.controller.table.ProviderTableController;
import main.entity.ProviderEntity;
import main.model.Model;

public class AddProviderController {

    private ObservableList<ProviderEntity> tableData;
    private ObservableList<ProviderEntity> selectedItems;
    @FXML private ProviderTableController providerTableController;
    @FXML private HBox formHBox;
    @FXML private Button addButton;
    @FXML private Button deleteButton;

    public HBox getFormHBox() {
        return formHBox;
    }

    @FXML private void clear(){
        idField.clear();
        firstNameField.clear();
        lastNameField.clear();
        emailField.clear();
        phoneNumberField.clear();
        addButton.setText("Dodaj");
    }

    @FXML private void delete(){
        ProviderEntity customerEntity = selectedItems.get(0);
        Model.get().getSession().delete(customerEntity);
        clear();
        deleteButton.setDisable(true);
        tableData.setAll(Model.get().loadTable("ProviderEntity"));
    }

    @FXML private void add(){
        ProviderEntity providerEntity;
        if (idField.getText().equals("")) providerEntity = new ProviderEntity();
        else providerEntity = selectedItems.get(0);
        providerEntity.setFirstName(firstNameField.getText());
        providerEntity.setLastName(lastNameField.getText());
        providerEntity.setEmail(emailField.getText());
        providerEntity.setPhoneNumber(phoneNumberField.getText());
        Model.get().getSession().save(providerEntity);
        tableData.setAll(Model.get().loadTable("ProviderEntity"));
    }

    @FXML private TextField idField;
    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField emailField;
    @FXML private TextField phoneNumberField;

    @FXML
    void initialize() {
        tableData = providerTableController.getTableData();
        selectedItems = providerTableController.getSelectedItems();
        selectedItems.addListener((ListChangeListener<ProviderEntity>) c -> {
            if (selectedItems.size() != 0) {
                ProviderEntity providerEntity = selectedItems.get(0);
                idField.setText(Integer.toString(providerEntity.getId()));
                firstNameField.setText(providerEntity.getFirstName());
                lastNameField.setText(providerEntity.getLastName());
                emailField.setText(providerEntity.getEmail());
                phoneNumberField.setText(providerEntity.getPhoneNumber());
                addButton.setText("Edytuj");
                deleteButton.setDisable(false);
            }
        });
    }
}
