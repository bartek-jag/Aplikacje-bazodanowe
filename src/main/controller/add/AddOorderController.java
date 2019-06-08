package main.controller.add;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import main.controller.table.CustomerTableController;
import main.controller.table.EmployeeTableController;
import main.controller.table.OorderTableController;
import main.entity.CustomerEntity;
import main.entity.EmployeeEntity;
import main.entity.OorderEntity;
import main.model.Model;

import java.sql.Timestamp;
import java.util.Date;

public class AddOorderController {

    private ObservableList<OorderEntity> oorderTableData;
    private ObservableList<OorderEntity> oorderSelectedItems;
    private ObservableList<CustomerEntity> customerSelectedItems;
    private ObservableList<EmployeeEntity> employeeSelectedItems;

    private OorderEntity activeOorder;
    private CustomerEntity activeCustomer;
    private EmployeeEntity activeEmployee;

    @FXML private TableView oorderTable;
    @FXML private TableView customerTable;
    @FXML private TableView employeeTable;
    @FXML private OorderTableController oorderTableController;
    @FXML private CustomerTableController customerTableController;
    @FXML private EmployeeTableController employeeTableController;
    @FXML private HBox formHBox;
    @FXML private Button addButton;
    @FXML private Button deleteButton;

    public HBox getFormHBox() {
        return formHBox;
    }

    @FXML private void clear(){
        idField.clear();
        customerIdField.clear();
        employeeIdField.clear();
        orderDateField.clear();
        activeOorder = new OorderEntity();
        addButton.setText("Dodaj");
    }

    @FXML private void delete(){
        OorderEntity oorderEntity = activeOorder;
        Model.get().getSession().delete(oorderEntity);
        clear();
        deleteButton.setDisable(true);
        oorderTableData.setAll(Model.get().loadTable("OorderEntity"));
    }

    @FXML private void add(){
        OorderEntity oorderEntity;
        if (idField.getText().equals("")) oorderEntity = new OorderEntity();
        else oorderEntity = activeOorder;
        oorderEntity.setCustomerByCustomerId(activeCustomer);
        oorderEntity.setEmployeeByEmployeeId(activeEmployee);
        oorderEntity.setOrderDate(new Timestamp((new Date()).getTime()));
        Model.get().getSession().save(oorderEntity);
        oorderTableData.setAll(Model.get().loadTable("OorderEntity"));
    }

    @FXML private void loadOorderTable(){
        formHBox.getChildren().remove(1);
        formHBox.getChildren().add(oorderTable);
    }
    @FXML private void loadCustomerTable(){
        formHBox.getChildren().remove(1);
        formHBox.getChildren().add(customerTable);
    }
    @FXML private void loadEmployeeTable(){
        formHBox.getChildren().remove(1);
        formHBox.getChildren().add(employeeTable);
    }

    @FXML private TextField idField;
    @FXML private TextField customerIdField;
    @FXML private TextField employeeIdField;
    @FXML private TextField orderDateField;

    @FXML
    void initialize() {
        formHBox.getChildren().remove(customerTable);
        formHBox.getChildren().remove(employeeTable);
        activeOorder = new OorderEntity();

        oorderTableData = oorderTableController.getTableData();

        oorderSelectedItems = oorderTableController.getSelectedItems();
        customerSelectedItems = customerTableController.getSelectedItems();
        employeeSelectedItems = employeeTableController.getSelectedItems();

        oorderSelectedItems.addListener((ListChangeListener<OorderEntity>) c -> {
            if (oorderSelectedItems.size() != 0) {
                activeOorder = oorderSelectedItems.get(0);
                activeCustomer = oorderSelectedItems.get(0).getCustomerByCustomerId();
                activeEmployee = oorderSelectedItems.get(0).getEmployeeByEmployeeId();
                idField.setText(Integer.toString(activeOorder.getId()));
                customerIdField.setText(Integer.toString(activeCustomer.getId()));
                employeeIdField.setText(Integer.toString(activeEmployee.getId()));

                addButton.setText("Edytuj");
                deleteButton.setDisable(false);
            }
        });

        customerSelectedItems.addListener((ListChangeListener<CustomerEntity>) c -> {
            if (customerSelectedItems.size() != 0) {
                activeCustomer = customerSelectedItems.get(0);
                customerIdField.setText(Integer.toString(activeCustomer.getId()));
                addButton.setText("Edytuj");
                deleteButton.setDisable(false);
            }
        });

        employeeSelectedItems.addListener((ListChangeListener<EmployeeEntity>) c -> {
            if (employeeSelectedItems.size() != 0) {
                activeEmployee = employeeSelectedItems.get(0);
                employeeIdField.setText(Integer.toString(activeEmployee.getId()));
                addButton.setText("Edytuj");
                deleteButton.setDisable(false);
            }
        });
    }
}
