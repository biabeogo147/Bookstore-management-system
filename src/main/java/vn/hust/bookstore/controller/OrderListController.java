package vn.hust.bookstore.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import vn.hust.bookstore.entity.Cashier;
import vn.hust.bookstore.entity.Order;
import vn.hust.bookstore.service.OrderService;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

public class OrderListController implements Initializable {

    private OrderService orderService = new OrderService();

    private Cashier cashier;

    @FXML
    private Button btnRefresh;

    @FXML
    private TableColumn<?, ?> colCustomerName;

    @FXML
    private TableColumn<?, ?> colOrderDate;

    @FXML
    private TableColumn<?, ?> colOrderId;

    @FXML
    private TableColumn<Order, CheckBox> colPaid;

    @FXML
    private TableColumn<?, ?> colProductList;

    @FXML
    private TableColumn<?, ?> colShippingAddress;

    @FXML
    private TableColumn<?, ?> colStatus;

    @FXML
    private TableColumn<?, ?> colTotalAmount;

    @FXML
    private TableView<Order> orderTable;

    public void setCashier(Cashier cashier) {
        this.cashier = cashier;
    }

    public void close(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    void refreshOrders() {
        ObservableList<Order> orders = FXCollections.observableArrayList(orderService.getAllOrders());
        orderTable.setItems(orders);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colCustomerName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        colOrderId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colOrderDate.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
        colProductList.setCellValueFactory(new PropertyValueFactory<>("items"));
        colTotalAmount.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
        colShippingAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colPaid.setCellFactory(column -> new TableCell<Order, CheckBox>() {
            private final CheckBox checkBox = new CheckBox();
            @Override
            protected void updateItem(CheckBox item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    Order order = getTableView().getItems().get(getIndex());
                    checkBox.setSelected(order.isPaid());
                    checkBox.setDisable(order.isPaid());
                    checkBox.selectedProperty().addListener((obs, wasSelected, isNowSelected) -> {
                        if (isNowSelected) {
                            order.setPaid(true);
                            order.setStatus("Done");
                            checkBox.setDisable(true);
                            orderService.updateOrder(order);
                            getTableView().refresh();
                        }
                    });
                    setGraphic(checkBox);
                }
            }
        });
        refreshOrders();
    }
}
