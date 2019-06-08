package main.controller.table;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.entity.BeverageEntity;
import main.entity.OorderEntity;
import main.entity.OrderDetailsEntity;
import main.model.Model;

public class OrderDetailsTableController {

    private ObservableList<OrderDetailsEntity> tableData = FXCollections.observableArrayList();
    private ObservableList<OrderDetailsEntity> selectedItems;
    public ObservableList<OrderDetailsEntity> getTableData() {
        return tableData;
    }
    public ObservableList<OrderDetailsEntity> getSelectedItems() {
        return selectedItems;
    }

    @FXML private TableView<OrderDetailsEntity> table;
    @FXML private TableColumn<OrderDetailsEntity, OorderEntity> oorderIdColumn;
    @FXML private TableColumn<OrderDetailsEntity, BeverageEntity> beverageIdColumn;
    @FXML private TableColumn<OrderDetailsEntity, Integer> quantityColumn;

    @FXML
    void initialize() {
        oorderIdColumn.setCellValueFactory(new PropertyValueFactory<>("oorderByOorderId"));
        beverageIdColumn.setCellValueFactory(new PropertyValueFactory<>("beverageByBeverageId"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        table.setItems(tableData);
        tableData.clear();
        tableData.addAll(Model.get().loadTable("OrderDetailsEntity"));

        selectedItems = table.getSelectionModel().getSelectedItems();
    }
}
