package main.controller;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import main.entity.GasTypeEntity;
import main.model.Model;

public class AddGasTypeController {

    private Model model;

    private ObservableList<GasTypeEntity> tableData = FXCollections.observableArrayList();
    private ObservableList<GasTypeEntity> selectedItems;

    @FXML private HBox formHBox;
    @FXML private Button addButton;
    @FXML private Button deleteButton;

    HBox getFormHBox() {
        return formHBox;
    }

    @FXML private void clear(){
        idField.clear();
        gasTypeField.clear();
        addButton.setText("Dodaj");
    }

    @FXML private void delete(){
        GasTypeEntity gasTypeEntity = selectedItems.get(0);
        model.getSession().delete(gasTypeEntity);
        clear();
        deleteButton.setDisable(true);
        tableData.setAll(model.loadTable("GasTypeEntity"));
    }

    @FXML private void add(){
        GasTypeEntity gasTypeEntity;
        if (idField.getText().equals(""))
            gasTypeEntity = new GasTypeEntity();
        else
            gasTypeEntity = selectedItems.get(0);
        gasTypeEntity.setGasType(gasTypeField.getText());
        model.getSession().save(gasTypeEntity);
        tableData.setAll(model.loadTable("GasTypeEntity"));
    }

    @FXML private TextField idField;
    @FXML private TextField gasTypeField;

    @FXML private TableView<GasTypeEntity> table;
    @FXML private TableColumn<GasTypeEntity, Integer> idColumn;
    @FXML private TableColumn<GasTypeEntity, String> gasTypeColumn;

    void init(Model model){
        this.model = model;
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        gasTypeColumn.setCellValueFactory(new PropertyValueFactory<>("gasType"));

        table.setItems(tableData);
        tableData.clear();
        tableData.addAll(model.loadTable("GasTypeEntity"));

        table.getSelectionModel().setCellSelectionEnabled(true);
        selectedItems = table.getSelectionModel().getSelectedItems();

        selectedItems.addListener((ListChangeListener<GasTypeEntity>) c -> {
            if (selectedItems.size() != 0) {
                GasTypeEntity GasTypeEntity = selectedItems.get(0);
                idField.setText(Integer.toString(GasTypeEntity.getId()));
                gasTypeField.setText(GasTypeEntity.getGasType());
                addButton.setText("Edytuj");
                deleteButton.setDisable(false);
            }
        });
    }
}
