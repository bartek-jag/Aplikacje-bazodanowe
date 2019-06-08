package main.controller.table;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.entity.BeverageTypeEntity;
import main.model.Model;

public class BeverageTypeTableController {

    private ObservableList<BeverageTypeEntity> tableData = FXCollections.observableArrayList();
    private ObservableList<BeverageTypeEntity> selectedItems;
    public ObservableList<BeverageTypeEntity> getTableData() {
        return tableData;
    }
    public ObservableList<BeverageTypeEntity> getSelectedItems() {
        return selectedItems;
    }

    @FXML private TableView<BeverageTypeEntity> table;
    @FXML private TableColumn<BeverageTypeEntity, Integer> idColumn;
    @FXML private TableColumn<BeverageTypeEntity, String> beverageTypeColumn;
    @FXML private TableColumn<BeverageTypeEntity, Double> taxColumn;

    @FXML
    void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        beverageTypeColumn.setCellValueFactory(new PropertyValueFactory<>("beverageType"));
        taxColumn.setCellValueFactory(new PropertyValueFactory<>("tax"));

        table.setItems(tableData);
        tableData.clear();
        tableData.addAll(Model.get().loadTable("BeverageTypeEntity"));

        selectedItems = table.getSelectionModel().getSelectedItems();
    }
}
