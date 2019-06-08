package main.controller.add;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import main.controller.table.GasTypeTableController;
import main.entity.GasTypeEntity;
import main.model.Model;

public class AddGasTypeController {

    private ObservableList<GasTypeEntity> tableData;
    private ObservableList<GasTypeEntity> selectedItems;

    @FXML private GasTypeTableController gasTypeTableController;
    @FXML private HBox formHBox;
    @FXML private Button addButton;
    @FXML private Button deleteButton;

    public HBox getFormHBox() {
        return formHBox;
    }

    @FXML private void clear(){
        idField.clear();
        gasTypeField.clear();
        addButton.setText("Dodaj");
    }

    @FXML private void delete(){
        GasTypeEntity gasTypeEntity = selectedItems.get(0);
        Model.get().getSession().delete(gasTypeEntity);
        clear();
        deleteButton.setDisable(true);
        tableData.setAll(Model.get().loadTable("GasTypeEntity"));
    }

    @FXML private void add(){
        GasTypeEntity gasTypeEntity;
        if (idField.getText().equals("")) gasTypeEntity = new GasTypeEntity();
        else gasTypeEntity = selectedItems.get(0);
        gasTypeEntity.setGasType(gasTypeField.getText());
        Model.get().getSession().save(gasTypeEntity);
        tableData.setAll(Model.get().loadTable("GasTypeEntity"));
    }

    @FXML private TextField idField;
    @FXML private TextField gasTypeField;

    @FXML
    void initialize() {
        tableData = gasTypeTableController.getTableData();
        selectedItems = gasTypeTableController.getSelectedItems();
        selectedItems.addListener((ListChangeListener<GasTypeEntity>) c -> {
            if (selectedItems.size() != 0) {
                GasTypeEntity gasTypeEntity = selectedItems.get(0);
                idField.setText(Integer.toString(gasTypeEntity.getId()));
                gasTypeField.setText(gasTypeEntity.getGasType());
                addButton.setText("Edytuj");
                deleteButton.setDisable(false);
            }
        });
    }
}