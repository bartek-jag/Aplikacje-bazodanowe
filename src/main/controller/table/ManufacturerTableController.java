package main.controller.table;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.entity.ManufacturerEntity;
import main.model.Model;

public class ManufacturerTableController {

    private ObservableList<ManufacturerEntity> tableData = FXCollections.observableArrayList();
    private ObservableList<ManufacturerEntity> selectedItems;
    public ObservableList<ManufacturerEntity> getTableData() {
        return tableData;
    }
    public ObservableList<ManufacturerEntity> getSelectedItems() {
        return selectedItems;
    }

    @FXML private TableView<ManufacturerEntity> table;
    @FXML private TableColumn<ManufacturerEntity, Integer> idColumn;
    @FXML private TableColumn<ManufacturerEntity, String> nameColumn;

    @FXML
    void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        table.setItems(tableData);
        tableData.clear();
        tableData.addAll(Model.get().loadTable("ManufacturerEntity"));

        selectedItems = table.getSelectionModel().getSelectedItems();
    }
}
