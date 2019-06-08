package main.controller;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.entity.OrderDetailsViewEntity;
import main.entity.OrdersViewEntity;
import main.model.Model;

import java.sql.Timestamp;

public class OrderDetailsViewController {

    private ObservableList<OrdersViewEntity> ordersTableData = FXCollections.observableArrayList();
    private ObservableList<OrderDetailsViewEntity> orderDetailsTableData = FXCollections.observableArrayList();

    @FXML private TableView<OrdersViewEntity> ordersTable;
    @FXML private TableColumn<OrdersViewEntity, Integer> id;
    @FXML private TableColumn<OrdersViewEntity, String> firstName;
    @FXML private TableColumn<OrdersViewEntity, String> lastName;
    @FXML private TableColumn<OrdersViewEntity, Timestamp> orderDate;

    @FXML public TableView<OrderDetailsViewEntity> orderDetailsTable;
    @FXML public TableColumn<OrderDetailsViewEntity, String> beverageName;
    @FXML public TableColumn<OrderDetailsViewEntity, String> beverageType;
    @FXML public TableColumn<OrderDetailsViewEntity, String> flavor;
    @FXML public TableColumn<OrderDetailsViewEntity, String> gasType;
    @FXML public TableColumn<OrderDetailsViewEntity, String> packageType;
    @FXML public TableColumn<OrderDetailsViewEntity, Integer> quantity;
    @FXML public TableColumn<OrderDetailsViewEntity, Double> price;

    @FXML
    void initialize(){
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        orderDate.setCellValueFactory(new PropertyValueFactory<>("orderDate"));

        beverageName.setCellValueFactory(new PropertyValueFactory<>("name"));
        beverageType.setCellValueFactory(new PropertyValueFactory<>("beverageType"));
        flavor.setCellValueFactory(new PropertyValueFactory<>("flavor"));
        gasType.setCellValueFactory(new PropertyValueFactory<>("gasType"));
        packageType.setCellValueFactory(new PropertyValueFactory<>("packageType"));
        quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));

        ordersTable.setItems(ordersTableData);
        orderDetailsTable.setItems(orderDetailsTableData);

        ObservableList<OrdersViewEntity> selectedItems = ordersTable.getSelectionModel().getSelectedItems();

        selectedItems.addListener((ListChangeListener<OrdersViewEntity>) c -> {
            if (selectedItems.size() != 0) {
                OrdersViewEntity order = selectedItems.get(0);
                orderDetailsTableData.clear();
                orderDetailsTableData.addAll(Model.get().loadOrderDetails(order.getId()));
            }
        });
        ordersTableData.clear();
        ordersTableData.addAll(Model.get().loadTable("OrdersViewEntity"));
    }
}
