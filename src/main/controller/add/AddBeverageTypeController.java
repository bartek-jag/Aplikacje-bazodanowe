package main.controller.add;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import main.controller.table.BeverageTypeTableController;
import main.entity.BeverageTypeEntity;
import main.model.Model;

import java.math.BigDecimal;

public class AddBeverageTypeController {

    private ObservableList<BeverageTypeEntity> tableData;
    private ObservableList<BeverageTypeEntity> selectedItems;

    @FXML private BeverageTypeTableController beverageTypeTableController;
    @FXML private HBox formHBox;

    public HBox getFormHBox() {
        return formHBox;
    }

    @FXML
    private void add() {
        BeverageTypeEntity beverageTypeEntity;
        if (idField.getText().equals("")) beverageTypeEntity = new BeverageTypeEntity();
        else beverageTypeEntity = selectedItems.get(0);
        beverageTypeEntity.setBeverageType(beverageTypeField.getText());
        beverageTypeEntity.setTax(BigDecimal.valueOf(Double.valueOf(taxField.getText())));
        Model.get().getSession().save(beverageTypeEntity);
        tableData.setAll(Model.get().loadTable("CustomerEntity"));
    }

    @FXML
    private void clear() {
        idField.clear();
        beverageTypeField.clear();
        taxField.clear();
        addButton.setText("Dodaj");
    }

    @FXML
    private void delete() {
        BeverageTypeEntity beverageTypeEntity = selectedItems.get(0);
        Model.get().getSession().delete(beverageTypeEntity);
        clear();
        deleteButton.setDisable(true);
        tableData.setAll(Model.get().loadTable("BeverageTypeEntity"));
    }

    @FXML private Button addButton;
    @FXML private Button deleteButton;

    @FXML private TextField idField;
    @FXML private TextField beverageTypeField;
    @FXML private TextField taxField;

    @FXML
    void initialize() {
        tableData = beverageTypeTableController.getTableData();
        selectedItems = beverageTypeTableController.getSelectedItems();
        selectedItems.addListener((ListChangeListener<BeverageTypeEntity>) c -> {
            if (beverageTypeTableController.getSelectedItems().size() != 0) {
                BeverageTypeEntity beverageTypeEntity = selectedItems.get(0);
                idField.setText(Integer.toString(beverageTypeEntity.getId()));
                beverageTypeField.setText(beverageTypeEntity.getBeverageType());
                taxField.setText(beverageTypeEntity.getTax().toString());
                addButton.setText("Edytuj");
                deleteButton.setDisable(false);
            }
        });
    }
}