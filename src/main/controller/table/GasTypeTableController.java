package main.controller.table;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.entity.GasTypeEntity;
import main.model.Model;

public class GasTypeTableController {

    private ObservableList<GasTypeEntity> tableData = FXCollections.observableArrayList();
    private ObservableList<GasTypeEntity> selectedItems;
    public ObservableList<GasTypeEntity> getTableData() {
        return tableData;
    }
    public ObservableList<GasTypeEntity> getSelectedItems() {
        return selectedItems;
    }

    @FXML private TableView<GasTypeEntity> table;
    @FXML private TableColumn<GasTypeEntity, Integer> idColumn;
    @FXML private TableColumn<GasTypeEntity, String> gasTypeColumn;

    @FXML
    void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        gasTypeColumn.setCellValueFactory(new PropertyValueFactory<>("gasType"));

        table.setItems(tableData);
        tableData.clear();
        tableData.addAll(Model.get().loadTable("GasTypeEntity"));

        selectedItems = table.getSelectionModel().getSelectedItems();
    }
}
