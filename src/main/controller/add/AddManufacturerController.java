package main.controller.add;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import main.controller.table.ManufacturerTableController;
import main.entity.ManufacturerEntity;
import main.model.Model;

public class AddManufacturerController {

    private ObservableList<ManufacturerEntity> tableData;
    private ObservableList<ManufacturerEntity> selectedItems;

    @FXML private ManufacturerTableController manufacturerTableController;
    @FXML private HBox formHBox;
    @FXML private Button addButton;
    @FXML private Button deleteButton;

    public HBox getFormHBox() {
        return formHBox;
    }

    @FXML private void clear(){
        idField.clear();
        nameField.clear();
        addButton.setText("Dodaj");
    }

    @FXML private void delete(){
        ManufacturerEntity manufacturerEntity = selectedItems.get(0);
        Model.get().getSession().delete(manufacturerEntity);
        clear();
        deleteButton.setDisable(true);
        tableData.setAll(Model.get().loadTable("ManufacturerEntity"));
    }

    @FXML private void add(){
        ManufacturerEntity manufacturerEntity;
        if (idField.getText().equals("")) manufacturerEntity = new ManufacturerEntity();
        else manufacturerEntity = selectedItems.get(0);
        manufacturerEntity.setName(nameField.getText());
        Model.get().getSession().save(manufacturerEntity);
        tableData.setAll(Model.get().loadTable("ManufacturerEntity"));
    }

    @FXML private TextField idField;
    @FXML private TextField nameField;

    @FXML
    void initialize() {
        tableData = manufacturerTableController.getTableData();
        selectedItems = manufacturerTableController.getSelectedItems();
        selectedItems.addListener((ListChangeListener<ManufacturerEntity>) c -> {
            if (selectedItems.size() != 0) {
                ManufacturerEntity manufacturerEntity = selectedItems.get(0);
                idField.setText(Integer.toString(manufacturerEntity.getId()));
                nameField.setText(manufacturerEntity.getName());
                addButton.setText("Edytuj");
                deleteButton.setDisable(false);
            }
        });
    }
}
