package main.controller.add;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import main.controller.table.BeverageTableController;
import main.controller.table.OorderTableController;
import main.controller.table.OrderDetailsTableController;
import main.entity.BeverageEntity;
import main.entity.OorderEntity;
import main.entity.OrderDetailsEntity;
import main.model.Model;

public class AddOrderDetailsController {

    private ObservableList<OrderDetailsEntity> orderDetailsTableData;
    private ObservableList<OrderDetailsEntity> orderDetailsSelectedItems;
    private ObservableList<OorderEntity> oorderSelectedItems;
    private ObservableList<BeverageEntity> beverageSelectedItems;

    private OrderDetailsEntity activeOrderDetails;
    private OorderEntity activeOorder;
    private BeverageEntity activeBeverage;

    @FXML private TableView orderDetailsTable;
    @FXML private TableView oorderTable;
    @FXML private TableView beverageTable;
    @FXML private OrderDetailsTableController orderDetailsTableController;
    @FXML private OorderTableController oorderTableController;
    @FXML private BeverageTableController beverageTableController;
    @FXML private HBox formHBox;
    @FXML private Button addButton;
    @FXML private Button deleteButton;

    public HBox getFormHBox() {
        return formHBox;
    }

    @FXML private void clear(){
        oorderIdField.clear();
        beverageIdField.clear();
        quantityField.clear();
        activeBeverage = null;
        activeOorder = null;
        loadOrderDetailsTable();
        activeOrderDetails = new OrderDetailsEntity();
    }

    @FXML private void delete() {
        if (activeBeverage == null || activeOorder == null) {
            return;
        }
        for (OrderDetailsEntity ord : orderDetailsTableData)
            if (ord.getBeverageByBeverageId().equals(activeBeverage))
                if (ord.getOorderByOorderId().equals(activeOorder)) {
                    Model.get().getSession().delete(ord);
                    clear();
                    deleteButton.setDisable(true);
                    orderDetailsTableData.setAll(Model.get().loadTable("OrderDetailsEntity"));
                    return;
                }
    }

    @FXML private void add(){
        if (activeBeverage == null || activeOorder == null){
            return;
        }
        for (OrderDetailsEntity ord : orderDetailsTableData)
            if (ord.getBeverageByBeverageId().equals(activeBeverage))
                if (ord.getOorderByOorderId().equals(activeOorder)){
                    if (!quantityField.getText().equals("")) {
                        ord.setQuantity(Integer.parseInt(quantityField.getText()));
                        Model.get().getSession().save(ord);
                    }
                    orderDetailsTableData.setAll(Model.get().loadTable("OrderDetailsEntity"));
                    return;
                }
        OrderDetailsEntity orderDetailsEntity = new OrderDetailsEntity();
        orderDetailsEntity.setOorderByOorderId(activeOorder);
        orderDetailsEntity.setOorderId(activeOorder.getId());
        orderDetailsEntity.setBeverageByBeverageId(activeBeverage);
        orderDetailsEntity.setBeverageId(activeBeverage.getId());
        if (!quantityField.getText().equals("")){
            orderDetailsEntity.setQuantity(Integer.parseInt(quantityField.getText()));
            Model.get().getSession().save(orderDetailsEntity);
            orderDetailsTableData.setAll(Model.get().loadTable("OrderDetailsEntity"));
        }

    }

    @FXML private void loadOrderDetailsTable(){
        formHBox.getChildren().remove(1);
        formHBox.getChildren().add(orderDetailsTable);
    }
    @FXML private void loadOorderTable(){
        formHBox.getChildren().remove(1);
        formHBox.getChildren().add(oorderTable);
    }
    @FXML private void loadBeverageTable(){
        formHBox.getChildren().remove(1);
        formHBox.getChildren().add(beverageTable);
    }

    @FXML private TextField oorderIdField;
    @FXML private TextField beverageIdField;
    @FXML private TextField quantityField;

    @FXML
    void initialize() {
        formHBox.getChildren().remove(oorderTable);
        formHBox.getChildren().remove(beverageTable);
        activeOrderDetails = new OrderDetailsEntity();

        orderDetailsTableData = orderDetailsTableController.getTableData();

        orderDetailsSelectedItems = orderDetailsTableController.getSelectedItems();
        oorderSelectedItems = oorderTableController.getSelectedItems();
        beverageSelectedItems = beverageTableController.getSelectedItems();

        orderDetailsSelectedItems.addListener((ListChangeListener<OrderDetailsEntity>) c -> {
            if (orderDetailsSelectedItems.size() != 0) {
                activeOrderDetails = orderDetailsSelectedItems.get(0);
                activeOorder = orderDetailsSelectedItems.get(0).getOorderByOorderId();
                activeBeverage = orderDetailsSelectedItems.get(0).getBeverageByBeverageId();
                oorderIdField.setText(Integer.toString(activeOorder.getId()));
                beverageIdField.setText(Integer.toString(activeBeverage.getId()));

                deleteButton.setDisable(false);
                System.out.println("wybrano" + activeOrderDetails);
            }
        });

        oorderSelectedItems.addListener((ListChangeListener<OorderEntity>) c -> {
            if (oorderSelectedItems.size() != 0) {
                activeOorder = oorderSelectedItems.get(0);
                oorderIdField.setText(Integer.toString(activeOorder.getId()));
                deleteButton.setDisable(false);
            }
        });

        beverageSelectedItems.addListener((ListChangeListener<BeverageEntity>) c -> {
            if (beverageSelectedItems.size() != 0) {
                activeBeverage = beverageSelectedItems.get(0);
                beverageIdField.setText(Integer.toString(activeBeverage.getId()));
                deleteButton.setDisable(false);
            }
        });
    }
}
