package main.controller;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import main.model.*;
import org.hibernate.Session;

import java.sql.Timestamp;
import java.util.List;

public class EmployeePanelScreenController {

    private MainScreenController mainScreenController;
    private Session session;

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
    private Pane employeePanelScreen;

    void setMainScreenController(MainScreenController mainScreenController) {
        this.mainScreenController = mainScreenController;
    }

    void setSession(Session session) {
        this.session = session;
    }

    @FXML
    private void logout(){
        session.close();
        mainScreenController.loadLoginScreen();
    }

    @FXML
    void initialize(){
        id.setCellValueFactory(new PropertyValueFactory<>("id")); // here id is a variable name which is define in pojo.
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
    }


    void loadTable() {
        List orders = session.createQuery("from OrdersViewEntity").list();
        ordersTableData.addAll(orders);

        ordersTable.setItems(ordersTableData);

        ordersTable.getSelectionModel().setCellSelectionEnabled(true);
        ObservableList<OrdersViewEntity> selectedItems = ordersTable.getSelectionModel().getSelectedItems();

        selectedItems.addListener((ListChangeListener<OrdersViewEntity>) c -> {
            OrdersViewEntity order = selectedItems.get(0);
            loadOrderDetails(order.getId());
        });
    }

    private void loadOrderDetails(int id) {
        List orderDetails = session.createQuery("from OrderDetailsViewEntity where id = " + id).list();
        orderDetailsTableData.clear();
        orderDetailsTableData.addAll(orderDetails);
        orderDetailsTable.setItems(orderDetailsTableData);
    }
}
