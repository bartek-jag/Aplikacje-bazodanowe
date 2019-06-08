package main.controller.table;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.entity.ProviderEntity;
import main.model.Model;

public class ProviderTableController {

    private ObservableList<ProviderEntity> tableData = FXCollections.observableArrayList();
    private ObservableList<ProviderEntity> selectedItems;
    public ObservableList<ProviderEntity> getTableData() {
        return tableData;
    }
    public ObservableList<ProviderEntity> getSelectedItems() {
        return selectedItems;
    }

    @FXML private TableView<ProviderEntity> table;
    @FXML private TableColumn<ProviderEntity, Integer> idColumn;
    @FXML private TableColumn<ProviderEntity, String> firstNameColumn;
    @FXML private TableColumn<ProviderEntity, String> lastNameColumn;
    @FXML private TableColumn<ProviderEntity, String> emailColumn;
    @FXML private TableColumn<ProviderEntity, String> phoneNumberColumn;

    @FXML
    void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));

        table.setItems(tableData);
        tableData.clear();
        tableData.addAll(Model.get().loadTable("ProviderEntity"));

        selectedItems = table.getSelectionModel().getSelectedItems();
    }
}
