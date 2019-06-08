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
import main.entity.OorderEntity;
import main.model.Model;

import java.sql.Timestamp;

public class AddOorderEntity {

    private Model model;

    private ObservableList<OorderEntity> tableData = FXCollections.observableArrayList();
    private ObservableList<OorderEntity> selectedItems;

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
        OorderEntity oorderEntity = selectedItems.get(0);
        model.getSession().delete(oorderEntity);
        clear();
        deleteButton.setDisable(true);
        tableData.setAll(model.loadTable("OorderEntity"));
    }

    @FXML private void add(){
        OorderEntity oorderEntity;
        if (idField.getText().equals(""))
            oorderEntity = new OorderEntity();
        else
            oorderEntity = selectedItems.get(0);
        oorderEntity.setCustomerByCustomerId(customerIdField.getText());
        oorderEntity.setGasType(gasTypeField.getText());
        oorderEntity.setGasType(gasTypeField.getText());
        model.getSession().save(oorderEntity);
        tableData.setAll(model.loadTable("OorderEntity"));
    }

    @FXML private TextField idField;
    @FXML private TextField customerIdField;
    @FXML private TextField employeeIdField;
    @FXML private TextField orderDateField;

    @FXML private TableView<OorderEntity> table;
    @FXML private TableColumn<OorderEntity, Integer> idColumn;
    @FXML private TableColumn<OorderEntity, Integer> customerIdColumn;
    @FXML private TableColumn<OorderEntity, Integer> employeeIdColumn;
    @FXML private TableColumn<OorderEntity, Timestamp> orderDateColumn;

    void init(Model model){
        this.model = model;
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        customerIdColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        employeeIdColumn.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        orderDateColumn.setCellValueFactory(new PropertyValueFactory<>("orderDate"));

        table.setItems(tableData);
        tableData.clear();
        tableData.addAll(model.loadTable("OorderEntity"));

        table.getSelectionModel().setCellSelectionEnabled(true);
        selectedItems = table.getSelectionModel().getSelectedItems();

        selectedItems.addListener((ListChangeListener<OorderEntity>) c -> {
            if (selectedItems.size() != 0) {
                OorderEntity oorderEntity = selectedItems.get(0);
                idField.setText(Integer.toString(oorderEntity.getId()));
                gasTypeField.setText(oorderEntity.getGasType());
                addButton.setText("Edytuj");
                deleteButton.setDisable(false);
            }
        });
    }
}
