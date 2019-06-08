package main.controller.add;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import main.controller.table.PackageTableController;
import main.entity.PackageEntity;
import main.model.Model;

import java.math.BigDecimal;

public class AddPackageController {

    private ObservableList<PackageEntity> tableData;
    private ObservableList<PackageEntity> selectedItems;

    @FXML private PackageTableController packageTableController;
    @FXML private HBox formHBox;
    @FXML private Button addButton;
    @FXML private Button deleteButton;

    public HBox getFormHBox() {
        return formHBox;
    }

    @FXML private void clear(){
        idField.clear();
        packageTypeField.clear();
        capacityField.clear();
        addButton.setText("Dodaj");
    }

    @FXML private void delete(){
        PackageEntity customerEntity = selectedItems.get(0);
        Model.get().getSession().delete(customerEntity);
        clear();
        deleteButton.setDisable(true);
        tableData.setAll(Model.get().loadTable("PackageEntity"));
    }

    @FXML private void add(){
        PackageEntity packageEntity;
        if (idField.getText().equals("")) packageEntity = new PackageEntity();
        else packageEntity = selectedItems.get(0);
        packageEntity.setPackageType(packageTypeField.getText());
        packageEntity.setCapacity(BigDecimal.valueOf(Double.valueOf(capacityField.getText())));
        Model.get().getSession().save(packageEntity);
        tableData.setAll(Model.get().loadTable("PackageEntity"));
    }

    @FXML private TextField idField;
    @FXML private TextField packageTypeField;
    @FXML private TextField capacityField;

    @FXML
    void initialize() {
        tableData = packageTableController.getTableData();
        selectedItems = packageTableController.getSelectedItems();
        selectedItems.addListener((ListChangeListener<PackageEntity>) c -> {
            if (selectedItems.size() != 0) {
                PackageEntity packageEntity = selectedItems.get(0);
                idField.setText(Integer.toString(packageEntity.getId()));
                packageTypeField.setText(packageEntity.getPackageType());
                capacityField.setText(packageEntity.getCapacity().toString());
                addButton.setText("Edytuj");
                deleteButton.setDisable(false);
            }
        });
    }
}
