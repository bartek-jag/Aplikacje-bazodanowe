package main.controller.table;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.entity.CustomerEntity;
import main.entity.EmployeeEntity;
import main.model.Model;

public class CustomerTableController {
    private ObservableList<CustomerEntity> tableData = FXCollections.observableArrayList();
    private ObservableList<CustomerEntity> selectedItems;
    public ObservableList<CustomerEntity> getTableData() {
        return tableData;
    }
    public ObservableList<CustomerEntity> getSelectedItems() {
        return selectedItems;
    }

    @FXML private TableView<CustomerEntity> table;
    @FXML private TableColumn<CustomerEntity, Integer> idColumn;
    @FXML private TableColumn<CustomerEntity, String> firstNameColumn;
    @FXML private TableColumn<CustomerEntity, String> lastNameColumn;
    @FXML private TableColumn<CustomerEntity, String> emailColumn;
    @FXML private TableColumn<CustomerEntity, String> phoneNumberColumn;
    @FXML private TableColumn<CustomerEntity, String> cityColumn;
    @FXML private TableColumn<CustomerEntity, String> postalCodeColumn;
    @FXML private TableColumn<CustomerEntity, String> streetColumn;
    @FXML private TableColumn<CustomerEntity, String> numberColumn;
    @FXML private TableColumn<EmployeeEntity, String> loginColumn;

    @FXML
    void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id")); //nazwa pola powinna być taka jak w klasach generowanych przez hibernate
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        cityColumn.setCellValueFactory(new PropertyValueFactory<>("city"));
        postalCodeColumn.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        streetColumn.setCellValueFactory(new PropertyValueFactory<>("street"));
        numberColumn.setCellValueFactory(new PropertyValueFactory<>("number"));
        loginColumn.setCellValueFactory(new PropertyValueFactory<>("login"));

        table.setItems(tableData);
        tableData.clear();
        tableData.addAll(Model.get().loadTable("CustomerEntity"));

        selectedItems = table.getSelectionModel().getSelectedItems();
    }
}
