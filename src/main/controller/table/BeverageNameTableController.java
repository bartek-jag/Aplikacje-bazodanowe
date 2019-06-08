package main.controller.table;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.entity.BeverageNameEntity;
import main.model.Model;

public class BeverageNameTableController {

    private ObservableList<BeverageNameEntity> selectedItems;
    private ObservableList<BeverageNameEntity> tableData = FXCollections.observableArrayList();
    public ObservableList<BeverageNameEntity> getTableData() {
        return tableData;
    }
    public ObservableList<BeverageNameEntity> getSelectedItems() {
        return selectedItems;
    }

    @FXML private TableView<BeverageNameEntity> table;
    @FXML private TableColumn<BeverageNameEntity, Integer> idColumn;
    @FXML private TableColumn<BeverageNameEntity, String> nameColumn;

    @FXML
    void initialize(){
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        table.setItems(tableData);
        tableData.clear();
        tableData.addAll(Model.get().loadTable("BeverageNameEntity"));

        selectedItems = table.getSelectionModel().getSelectedItems();
    }
}
