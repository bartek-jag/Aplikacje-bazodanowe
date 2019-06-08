package main.controller.table;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.entity.CustomerEntity;
import main.entity.EmployeeEntity;
import main.entity.OorderEntity;
import main.model.Model;

import java.sql.Timestamp;

public class OorderTableController {

    private ObservableList<OorderEntity> tableData = FXCollections.observableArrayList();
    private ObservableList<OorderEntity> selectedItems;
    public ObservableList<OorderEntity> getTableData() {
        return tableData;
    }
    public ObservableList<OorderEntity> getSelectedItems() {
        return selectedItems;
    }

    @FXML private TableView<OorderEntity> table;
    @FXML private TableColumn<OorderEntity, Integer> idColumn;
    @FXML private TableColumn<OorderEntity, CustomerEntity> customerIdColumn;
    @FXML private TableColumn<OorderEntity, EmployeeEntity> employeeIdColumn;
    @FXML private TableColumn<OorderEntity, Timestamp> orderDateColumn;

    @FXML
    void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        customerIdColumn.setCellValueFactory(new PropertyValueFactory<OorderEntity, CustomerEntity>("customerByCustomerId"));
        employeeIdColumn.setCellValueFactory(new PropertyValueFactory<OorderEntity, EmployeeEntity>("employeeByEmployeeId"));
        orderDateColumn.setCellValueFactory(new PropertyValueFactory<>("orderDate"));

        table.setItems(tableData);
        tableData.clear();
        tableData.addAll(Model.get().loadTable("OorderEntity"));

        selectedItems = table.getSelectionModel().getSelectedItems();
    }
}
