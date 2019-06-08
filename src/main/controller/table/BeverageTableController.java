package main.controller.table;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.entity.*;
import main.model.Model;

public class BeverageTableController {

    private ObservableList<BeverageEntity> tableData = FXCollections.observableArrayList();
    private ObservableList<BeverageEntity> selectedItems;
    public ObservableList<BeverageEntity> getTableData() {
        return tableData;
    }
    public ObservableList<BeverageEntity> getSelectedItems() {
        return selectedItems;
    }

    @FXML private TableView<BeverageEntity> table;
    @FXML private TableColumn<BeverageEntity, Integer> idColumn;
    @FXML private TableColumn<BeverageEntity, BeverageNameEntity> beverageNameIdColumn;
    @FXML private TableColumn<BeverageEntity, BeverageTypeEntity> beverageTypeIdColumn;
    @FXML private TableColumn<BeverageEntity, FlavorEntity> flavorIdColumn;
    @FXML private TableColumn<BeverageEntity, GasTypeEntity> gasTypeIdColumn;
    @FXML private TableColumn<BeverageEntity, ManufacturerEntity> manufacturerIdColumn;
    @FXML private TableColumn<BeverageEntity, PackageEntity> packageIdColumn;
    @FXML private TableColumn<BeverageEntity, ProviderEntity> providerIdColumn;
    @FXML private TableColumn<BeverageEntity, Integer> quantityColumn;
    @FXML private TableColumn<BeverageEntity, Double> priceColumn;

    @FXML
    void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        beverageNameIdColumn.setCellValueFactory(new PropertyValueFactory<>("beverageNameByBeverageNameId"));
        beverageTypeIdColumn.setCellValueFactory(new PropertyValueFactory<>("beverageTypeByBeverageTypeId"));
        flavorIdColumn.setCellValueFactory(new PropertyValueFactory<>("flavorByFlavorId"));
        gasTypeIdColumn.setCellValueFactory(new PropertyValueFactory<>("gasTypeByGasTypeId"));
        manufacturerIdColumn.setCellValueFactory(new PropertyValueFactory<>("manufacturerByManufacturerId"));
        packageIdColumn.setCellValueFactory(new PropertyValueFactory<>("packageByPackageId"));
        providerIdColumn.setCellValueFactory(new PropertyValueFactory<>("providerByProviderId"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        table.setItems(tableData);
        tableData.clear();
        tableData.addAll(Model.get().loadTable("BeverageEntity"));

        selectedItems = table.getSelectionModel().getSelectedItems();
    }
}
