package main.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.entity.LogonRegistryEntity;
import main.model.Model;

import java.sql.Timestamp;

public class LogonRegistryController {

    private ObservableList<LogonRegistryEntity> tableData = FXCollections.observableArrayList();

    @FXML private TableView<LogonRegistryEntity> table;
    @FXML private TableColumn<LogonRegistryEntity, Integer> idColumn;
    @FXML private TableColumn<LogonRegistryEntity, Timestamp> dateColumn;
    @FXML private TableColumn<LogonRegistryEntity, String> loginColumn;
    @FXML
    void initialize(){
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        loginColumn.setCellValueFactory(new PropertyValueFactory<>("login"));

        table.setItems(tableData);
        tableData.clear();
        tableData.addAll(Model.get().loadTable("LogonRegistryEntity"));
    }
}
