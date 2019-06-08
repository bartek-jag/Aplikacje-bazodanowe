package main.controller.add;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import main.controller.table.*;
import main.entity.*;
import main.model.Model;

public class AddBeverageController {

    private ObservableList<BeverageEntity> beverageTableData;
    private ObservableList<BeverageEntity> beverageSelectedItems;
    private ObservableList<BeverageNameEntity> beverageNameSelectedItems;
    private ObservableList<BeverageTypeEntity> beverageTypeSelectedItems;
    private ObservableList<FlavorEntity> flavorSelectedItems;
    private ObservableList<GasTypeEntity> gasTypeSelectedItems;
    private ObservableList<ManufacturerEntity> manufacturerSelectedItems;
    private ObservableList<PackageEntity> packageSelectedItems;
    private ObservableList<ProviderEntity> providerSelectedItems;

    private BeverageEntity activeBeverage;
    private BeverageNameEntity activeBeverageName;
    private BeverageTypeEntity activeBeverageType;
    private FlavorEntity activeFlavor;
    private GasTypeEntity activeGasType;
    private ManufacturerEntity activeManufacturer;
    private PackageEntity activePackage;
    private ProviderEntity activeProvider;

    @FXML private TableView beverageTable;
    @FXML private TableView beverageNameTable;
    @FXML private TableView beverageTypeTable;
    @FXML private TableView flavorTable;
    @FXML private TableView gasTypeTable;
    @FXML private TableView manufacturerTable;
    @FXML private TableView packageTable;
    @FXML private TableView providerTable;
    @FXML private BeverageTableController beverageTableController;
    @FXML private BeverageNameTableController beverageNameTableController;
    @FXML private BeverageTypeTableController beverageTypeTableController;
    @FXML private FlavorTableController flavorTableController;
    @FXML private GasTypeTableController gasTypeTableController;
    @FXML private ManufacturerTableController manufacturerTableController;
    @FXML private PackageTableController packageTableController;
    @FXML private ProviderTableController providerTableController;
    @FXML private HBox formHBox;
    @FXML private Button addButton;
    @FXML private Button deleteButton;

    public HBox getFormHBox() {
        return formHBox;
    }

    @FXML private void clear(){
        idField.clear();
        beverageNameIdField.clear();
        beverageTypeIdField.clear();
        flavorIdField.clear();
        gasTypeIdField.clear();
        manufacturerIdField.clear();
        packageIdField.clear();
        providerIdField.clear();
        quantityField.clear();
        priceField.clear();
        activeBeverage = new BeverageEntity();
        addButton.setText("Dodaj");
    }

    @FXML private void delete(){
        BeverageEntity beverageEntity = activeBeverage;
        Model.get().getSession().delete(beverageEntity);
        clear();
        deleteButton.setDisable(true);
        beverageTableData.setAll(Model.get().loadTable("BeverageEntity"));
    }

    @FXML private void add(){
        BeverageEntity beverageEntity;

        if (idField.getText().equals("")) beverageEntity = new BeverageEntity();
        else beverageEntity = activeBeverage;
        if (quantityField.getText().equals("")) return;
        else beverageEntity.setQuantity(Integer.parseInt(quantityField.getText()));
        if (priceField.getText().equals("")) return;
        else beverageEntity.setPrice(Double.parseDouble(priceField.getText()));
        beverageEntity.setBeverageNameByBeverageNameId(activeBeverageName);
        beverageEntity.setBeverageTypeByBeverageTypeId(activeBeverageType);
        beverageEntity.setFlavorByFlavorId(activeFlavor);
        beverageEntity.setGasTypeByGasTypeId(activeGasType);
        beverageEntity.setManufacturerByManufacturerId(activeManufacturer);
        beverageEntity.setPackageByPackageId(activePackage);
        beverageEntity.setProviderByProviderId(activeProvider);
        Model.get().getSession().save(beverageEntity);
        beverageTableData.setAll(Model.get().loadTable("BeverageEntity"));
    }

    @FXML private void loadBeverageTable(){
        formHBox.getChildren().remove(1);
        formHBox.getChildren().add(beverageTable);
    }
    @FXML private void loadBeverageNameTable(){
        formHBox.getChildren().remove(1);
        formHBox.getChildren().add(beverageNameTable);
    }
    @FXML private void loadBeverageTypeTable(){
        formHBox.getChildren().remove(1);
        formHBox.getChildren().add(beverageTypeTable);
    }
    @FXML private void loadFlavorTable(){
        formHBox.getChildren().remove(1);
        formHBox.getChildren().add(flavorTable);
    }
    @FXML private void loadgasTypeTable(){
        formHBox.getChildren().remove(1);
        formHBox.getChildren().add(gasTypeTable);
    }
    @FXML private void loadManufacturerTable(){
        formHBox.getChildren().remove(1);
        formHBox.getChildren().add(manufacturerTable);
    }
    @FXML private void loadPackageTable(){
        formHBox.getChildren().remove(1);
        formHBox.getChildren().add(packageTable);
    }
    @FXML private void loadProviderTable(){
        formHBox.getChildren().remove(1);
        formHBox.getChildren().add(providerTable);
    }

    @FXML private TextField idField;
    @FXML private TextField beverageNameIdField;
    @FXML private TextField beverageTypeIdField;
    @FXML private TextField flavorIdField;
    @FXML private TextField gasTypeIdField;
    @FXML private TextField manufacturerIdField;
    @FXML private TextField packageIdField;
    @FXML private TextField providerIdField;
    @FXML private TextField quantityField;
    @FXML private TextField priceField;

    @FXML
    void initialize() {
        formHBox.getChildren().remove(1, formHBox.getChildren().size());
        formHBox.getChildren().add(1, beverageTable);

        activeBeverage = new BeverageEntity();

        beverageTableData = beverageTableController.getTableData();

        beverageSelectedItems = beverageTableController.getSelectedItems();
        beverageNameSelectedItems = beverageNameTableController.getSelectedItems();
        beverageTypeSelectedItems = beverageTypeTableController.getSelectedItems();
        flavorSelectedItems = flavorTableController.getSelectedItems();
        gasTypeSelectedItems = gasTypeTableController.getSelectedItems();
        manufacturerSelectedItems = manufacturerTableController.getSelectedItems();
        packageSelectedItems = packageTableController.getSelectedItems();
        providerSelectedItems = providerTableController.getSelectedItems();

        beverageSelectedItems.addListener((ListChangeListener<BeverageEntity>) c -> {
            if (beverageSelectedItems.size() != 0) {
                activeBeverage = beverageSelectedItems.get(0);
                activeBeverageName = beverageSelectedItems.get(0).getBeverageNameByBeverageNameId();
                activeBeverageType = beverageSelectedItems.get(0).getBeverageTypeByBeverageTypeId();
                activeFlavor = beverageSelectedItems.get(0).getFlavorByFlavorId();
                activeGasType = beverageSelectedItems.get(0).getGasTypeByGasTypeId();
                activeManufacturer = beverageSelectedItems.get(0).getManufacturerByManufacturerId();
                activePackage = beverageSelectedItems.get(0).getPackageByPackageId();
                activeProvider = beverageSelectedItems.get(0).getProviderByProviderId();

                idField.setText(Integer.toString(activeBeverage.getId()));
                beverageNameIdField.setText(Integer.toString(activeBeverageName.getId()));
                beverageTypeIdField.setText(Integer.toString(activeBeverageType.getId()));
                flavorIdField.setText(activeFlavor == null ? "" : Integer.toString(activeFlavor.getId()));
                gasTypeIdField.setText(Integer.toString(activeGasType.getId()));
                manufacturerIdField.setText(Integer.toString(activeManufacturer.getId()));
                packageIdField.setText(Integer.toString(activePackage.getId()));
                providerIdField.setText(Integer.toString(activeProvider.getId()));
                quantityField.setText(Integer.toString(activeBeverage.getQuantity()));
                priceField.setText(Double.toString(activeBeverage.getPrice()));

                addButton.setText("Edytuj");
                deleteButton.setDisable(false);
            }
        });

        beverageNameSelectedItems.addListener((ListChangeListener<BeverageNameEntity>) c -> {
            if (beverageNameSelectedItems.size() != 0) {
                activeBeverageName = beverageNameSelectedItems.get(0);
                beverageNameIdField.setText(Integer.toString(activeBeverageName.getId()));
                addButton.setText("Edytuj");
                deleteButton.setDisable(false);
            }
        });

        beverageTypeSelectedItems.addListener((ListChangeListener<BeverageTypeEntity>) c -> {
            if (beverageTypeSelectedItems.size() != 0) {
                activeBeverageType = beverageTypeSelectedItems.get(0);
                beverageTypeIdField.setText(Integer.toString(activeBeverageType.getId()));
                addButton.setText("Edytuj");
                deleteButton.setDisable(false);
            }
        });

        flavorSelectedItems.addListener((ListChangeListener<FlavorEntity>) c -> {
            if (flavorSelectedItems.size() != 0) {
                activeFlavor = flavorSelectedItems.get(0);
                flavorIdField.setText(Integer.toString(activeFlavor.getId()));
                addButton.setText("Edytuj");
                deleteButton.setDisable(false);
            }
        });

        gasTypeSelectedItems.addListener((ListChangeListener<GasTypeEntity>) c -> {
            if (gasTypeSelectedItems.size() != 0) {
                activeGasType = gasTypeSelectedItems.get(0);
                gasTypeIdField.setText(Integer.toString(activeGasType.getId()));
                addButton.setText("Edytuj");
                deleteButton.setDisable(false);
            }
        });

        manufacturerSelectedItems.addListener((ListChangeListener<ManufacturerEntity>) c -> {
            if (manufacturerSelectedItems.size() != 0) {
                activeManufacturer = manufacturerSelectedItems.get(0);
                manufacturerIdField.setText(Integer.toString(activeManufacturer.getId()));
                addButton.setText("Edytuj");
                deleteButton.setDisable(false);
            }
        });

        packageSelectedItems.addListener((ListChangeListener<PackageEntity>) c -> {
            if (packageSelectedItems.size() != 0) {
                activePackage = packageSelectedItems.get(0);
                packageIdField.setText(Integer.toString(activePackage.getId()));
                addButton.setText("Edytuj");
                deleteButton.setDisable(false);
            }
        });

        providerSelectedItems.addListener((ListChangeListener<ProviderEntity>) c -> {
            if (providerSelectedItems.size() != 0) {
                activeProvider = providerSelectedItems.get(0);
                providerIdField.setText(Integer.toString(activeProvider.getId()));
                addButton.setText("Edytuj");
                deleteButton.setDisable(false);
            }
        });
    }
}
