package main.controller.add;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import main.controller.table.CustomerTableController;
import main.entity.CustomerEntity;
import main.model.Model;

public class AddCustomerController {

    private ObservableList<CustomerEntity> tableData = FXCollections.observableArrayList();
    private ObservableList<CustomerEntity> selectedItems;

    @FXML private CustomerTableController customerTableController;
    @FXML private HBox formHBox;

    public HBox getFormHBox() {
        return formHBox;
    }

    @FXML
    private void add() {
        CustomerEntity customerEntity;
        if (idField.getText().equals("")) customerEntity = new CustomerEntity();
        else customerEntity = selectedItems.get(0);
        customerEntity.setFirstName(firstNameField.getText());
        customerEntity.setLastName(lastNameField.getText());
        customerEntity.setEmail(emailField.getText());
        customerEntity.setPhoneNumber(phoneNumberField.getText());
        customerEntity.setCity(cityField.getText());
        customerEntity.setPostalCode(postalCodeField.getText());
        customerEntity.setStreet(streetField.getText());
        customerEntity.setNumber(numberField.getText());
        customerEntity.setLogin(loginField.getText());
        customerEntity.setPassword(Model.get_SHA_512_SecurePassword(passwordField.getText()));
        Model.get().getSession().save(customerEntity);
        tableData.setAll(Model.get().loadTable("CustomerEntity"));
    }

    @FXML
    private void clear() {
        idField.clear();
        firstNameField.clear();
        lastNameField.clear();
        emailField.clear();
        phoneNumberField.clear();
        cityField.clear();
        postalCodeField.clear();
        streetField.clear();
        numberField.clear();
        loginField.clear();
        passwordField.clear();
        addButton.setText("Dodaj");
    }

    @FXML
    private void delete() {
        CustomerEntity customerEntity = selectedItems.get(0);
        Model.get().getSession().delete(customerEntity);
        clear();
        deleteButton.setDisable(true);
        tableData.setAll(Model.get().loadTable("CustomerEntity"));
    }

    @FXML private Button addButton;
    @FXML private Button deleteButton;

    @FXML private TextField idField;
    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField emailField;
    @FXML private TextField phoneNumberField;
    @FXML private TextField cityField;
    @FXML private TextField postalCodeField;
    @FXML private TextField streetField;
    @FXML private TextField numberField;
    @FXML private TextField loginField;
    @FXML private TextField passwordField;

    @FXML
    void initialize() {
        tableData = customerTableController.getTableData();
        selectedItems = customerTableController.getSelectedItems();
        selectedItems.addListener((ListChangeListener<CustomerEntity>) c -> {
            if (selectedItems.size() != 0) {
                CustomerEntity customer = selectedItems.get(0);
                idField.setText(Integer.toString(customer.getId()));
                firstNameField.setText(customer.getFirstName());
                lastNameField.setText(customer.getLastName());
                emailField.setText(customer.getEmail());
                phoneNumberField.setText(customer.getPhoneNumber());
                cityField.setText(customer.getCity());
                postalCodeField.setText(customer.getPostalCode());
                streetField.setText(customer.getStreet());
                numberField.setText(customer.getNumber());
                loginField.setText(customer.getLogin());
                addButton.setText("Edytuj");
                deleteButton.setDisable(false);
            }
        });
    }
}