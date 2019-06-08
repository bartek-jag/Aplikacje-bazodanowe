package main.controller.table;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.entity.PackageEntity;
import main.model.Model;

public class PackageTableController {

    private ObservableList<PackageEntity> tableData = FXCollections.observableArrayList();
    private ObservableList<PackageEntity> selectedItems;
    public ObservableList<PackageEntity> getTableData() {
        return tableData;
    }
    public ObservableList<PackageEntity> getSelectedItems() {
        return selectedItems;
    }

    @FXML private TableView<PackageEntity> table;
    @FXML private TableColumn<PackageEntity, Integer> idColumn;
    @FXML private TableColumn<PackageEntity, String> packageTypeColumn;
    @FXML private TableColumn<PackageEntity, Double> capacityColumn;

    @FXML
    void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        packageTypeColumn.setCellValueFactory(new PropertyValueFactory<>("packageType"));
        capacityColumn.setCellValueFactory(new PropertyValueFactory<>("capacity"));

        table.setItems(tableData);
        tableData.clear();
        tableData.addAll(Model.get().loadTable("PackageEntity"));

        selectedItems = table.getSelectionModel().getSelectedItems();
    }
}
