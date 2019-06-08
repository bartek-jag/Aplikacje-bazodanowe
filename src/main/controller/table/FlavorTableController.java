package main.controller.table;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.entity.FlavorEntity;
import main.model.Model;

public class FlavorTableController {

    private ObservableList<FlavorEntity> tableData = FXCollections.observableArrayList();
    private ObservableList<FlavorEntity> selectedItems;
    public ObservableList<FlavorEntity> getTableData() {
        return tableData;
    }
    public ObservableList<FlavorEntity> getSelectedItems() {
        return selectedItems;
    }

    @FXML private TableView<FlavorEntity> table;
    @FXML private TableColumn<FlavorEntity, Integer> idColumn;
    @FXML private TableColumn<FlavorEntity, String> flavorColumn;

    @FXML
    void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        flavorColumn.setCellValueFactory(new PropertyValueFactory<>("flavor"));

        table.setItems(tableData);
        tableData.clear();
        tableData.addAll(Model.get().loadTable("FlavorEntity"));

        selectedItems = table.getSelectionModel().getSelectedItems();
    }
}
