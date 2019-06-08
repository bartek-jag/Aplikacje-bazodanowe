package main.controller.add;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import main.controller.table.BeverageNameTableController;
import main.entity.BeverageNameEntity;
import main.model.Model;

public class AddBeverageNameController {

    private ObservableList<BeverageNameEntity> tableData;
    private ObservableList<BeverageNameEntity> selectedItems;

    @FXML private BeverageNameTableController beverageNameTableController;
    @FXML private HBox formHBox;

    public HBox getFormHBox() {
        return formHBox;
    }

    @FXML private void add(){
        BeverageNameEntity beverageNameEntity;
        if (idField.getText().equals("")) beverageNameEntity = new BeverageNameEntity();
        else beverageNameEntity = selectedItems.get(0);
        beverageNameEntity.setName(nameField.getText());
        Model.get().getSession().save(beverageNameEntity);
        tableData.setAll(Model.get().loadTable("beverageNameEntity"));
    }

    @FXML private void clear(){
        idField.clear();
        nameField.clear();
        addButton.setText("Dodaj");
    }

    @FXML private void delete(){
        BeverageNameEntity beverageNameEntity = selectedItems.get(0);
        Model.get().getSession().delete(beverageNameEntity);
        clear();
        deleteButton.setDisable(true);
        tableData.setAll(Model.get().loadTable("beverageNameEntity"));
    }

    @FXML private Button addButton;
    @FXML private Button deleteButton;

    @FXML private TextField idField;
    @FXML private TextField nameField;

    @FXML
    void initialize(){
        tableData = beverageNameTableController.getTableData();
        selectedItems = beverageNameTableController.getSelectedItems();
        selectedItems.addListener((ListChangeListener<BeverageNameEntity>) c -> {
            if (beverageNameTableController.getSelectedItems().size() != 0) {
                BeverageNameEntity beverageNameEntity = selectedItems.get(0);
                idField.setText(Integer.toString(beverageNameEntity.getId()));
                nameField.setText(beverageNameEntity.getName());
                addButton.setText("Edytuj");
                deleteButton.setDisable(false);
            }
        });
    }
}