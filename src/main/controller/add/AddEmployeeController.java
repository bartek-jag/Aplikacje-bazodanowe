package main.controller.add;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import main.controller.table.EmployeeTableController;
import main.entity.EmployeeEntity;
import main.model.Model;

public class AddEmployeeController {

    private ObservableList<EmployeeEntity> tableData;
    private ObservableList<EmployeeEntity> selectedItems;

    @FXML private EmployeeTableController employeeTableController;
    @FXML private HBox formHBox;

    public HBox getFormHBox() {
        return formHBox;
    }

    @FXML private void add(){
        EmployeeEntity employeeEntity;
        if (idField.getText().equals("")) employeeEntity = new EmployeeEntity();
        else employeeEntity = selectedItems.get(0);
        employeeEntity.setFirstName(firstNameField.getText());
        employeeEntity.setLastName(lastNameField.getText());
        employeeEntity.setEmail(emailField.getText());
        employeeEntity.setPhoneNumber(phoneNumberField.getText());
        employeeEntity.setLogin(loginField.getText());
        employeeEntity.setPassword(Model.get_SHA_512_SecurePassword(passwordField.getText()));
        Model.get().getSession().save(employeeEntity);
        tableData.setAll(Model.get().loadTable("EmployeeEntity"));
    }

    @FXML private void clear(){
        idField.clear();
        firstNameField.clear();
        lastNameField.clear();
        emailField.clear();
        phoneNumberField.clear();
        loginField.clear();
        passwordField.clear();
        addButton.setText("Dodaj");
    }

    @FXML private void delete(){
        EmployeeEntity employeeEntity = selectedItems.get(0);
        Model.get().getSession().delete(employeeEntity);
        clear();
        deleteButton.setDisable(true);
        tableData.setAll(Model.get().loadTable("EmployeeEntity"));
    }

    @FXML private Button addButton;
    @FXML private Button deleteButton;

    @FXML private TextField idField;
    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField emailField;
    @FXML private TextField phoneNumberField;
    @FXML private TextField loginField;
    @FXML private TextField passwordField;

    @FXML
    void initialize() {
        tableData = employeeTableController.getTableData();
        selectedItems = employeeTableController.getSelectedItems();
        selectedItems.addListener((ListChangeListener<EmployeeEntity>) c -> {
            if (selectedItems.size() != 0) {
                EmployeeEntity employee = selectedItems.get(0);
                idField.setText(Integer.toString(employee.getId()));
                firstNameField.setText(employee.getFirstName());
                lastNameField.setText(employee.getLastName());
                emailField.setText(employee.getEmail());
                phoneNumberField.setText(employee.getPhoneNumber());
                loginField.setText(employee.getLogin());
                addButton.setText("Edytuj");
                deleteButton.setDisable(false);
            }
        });
    }
}