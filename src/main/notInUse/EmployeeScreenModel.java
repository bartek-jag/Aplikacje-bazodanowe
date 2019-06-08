package main.model;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import main.entity.OrderDetailsViewEntity;
import main.entity.OrdersViewEntity;
import org.hibernate.Session;

import java.util.List;

public class EmployeeScreenModel {

    /*private Session session;

    void setSession(Session session) {
        this.session = session;
    }

    public ObservableList<OrdersViewEntity> ordersTableData = FXCollections.observableArrayList();
    public ObservableList<OrderDetailsViewEntity> orderDetailsTableData = FXCollections.observableArrayList();

    void loadTable() {
        List orders = session.createQuery("from OrdersViewEntity").list();
        ordersTableData.addAll(orders);
    }

    public void loadOrderDetails(int id) {
        List orderDetails = session.createQuery("from OrderDetailsViewEntity where id = " + id).list();
        orderDetailsTableData.clear();
        orderDetailsTableData.addAll(orderDetails);
    }*/
}
