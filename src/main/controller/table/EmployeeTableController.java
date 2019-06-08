package main.controller.table;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.entity.EmployeeEntity;
import main.model.Model;

public class EmployeeTableController {

    private ObservableList<EmployeeEntity> tableData = FXCollections.observableArrayList();
    private ObservableList<EmployeeEntity> selectedItems;
    public ObservableList<EmployeeEntity> getTableData() {
        return tableData;
    }
    public ObservableList<EmployeeEntity> getSelectedItems() {
        return selectedItems;
    }

    @FXML private TableView<EmployeeEntity> table;
    @FXML private TableColumn<EmployeeEntity, Integer> idColumn;
    @FXML private TableColumn<EmployeeEntity, String> firstNameColumn;
    @FXML private TableColumn<EmployeeEntity, String> lastNameColumn;
    @FXML private TableColumn<EmployeeEntity, String> emailColumn;
    @FXML private TableColumn<EmployeeEntity, String> phoneNumberColumn;
    @FXML private TableColumn<EmployeeEntity, String> loginColumn;

    @FXML
    void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        loginColumn.setCellValueFactory(new PropertyValueFactory<>("login"));

        table.setItems(tableData);
        tableData.clear();
        tableData.addAll(Model.get().loadTable("EmployeeEntity"));

        selectedItems = table.getSelectionModel().getSelectedItems();
    }
}
