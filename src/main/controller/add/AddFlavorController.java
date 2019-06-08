package main.controller.add;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import main.controller.table.FlavorTableController;
import main.entity.FlavorEntity;
import main.model.Model;

public class AddFlavorController {

    private ObservableList<FlavorEntity> tableData;
    private ObservableList<FlavorEntity> selectedItems;

    @FXML private FlavorTableController flavorTableController;
    @FXML private HBox formHBox;
    @FXML private Button addButton;
    @FXML private Button deleteButton;

    public HBox getFormHBox() {
        return formHBox;
    }

    @FXML private void clear(){
        idField.clear();
        flavorField.clear();
        addButton.setText("Dodaj");
    }

    @FXML private void delete(){
        FlavorEntity flavorEntity = selectedItems.get(0);
        Model.get().getSession().delete(flavorEntity);
        clear();
        deleteButton.setDisable(true);
        tableData.setAll(Model.get().loadTable("FlavorEntity"));
    }

    @FXML private void add(){
        FlavorEntity flavorEntity;
        if (idField.getText().equals("")) flavorEntity = new FlavorEntity();
        else flavorEntity = selectedItems.get(0);
        flavorEntity.setFlavor(flavorField.getText());
        Model.get().getSession().save(flavorEntity);
        tableData.setAll(Model.get().loadTable("FlavorEntity"));
    }

    @FXML private TextField idField;
    @FXML private TextField flavorField;

    @FXML
    void initialize() {
        tableData = flavorTableController.getTableData();
        selectedItems = flavorTableController.getSelectedItems();
        selectedItems.addListener((ListChangeListener<FlavorEntity>) c -> {
            if (selectedItems.size() != 0) {
                FlavorEntity flavorEntity = selectedItems.get(0);
                idField.setText(Integer.toString(flavorEntity.getId()));
                flavorField.setText(flavorEntity.getFlavor());
                addButton.setText("Edytuj");
                deleteButton.setDisable(false);
            }
        });
    }
}