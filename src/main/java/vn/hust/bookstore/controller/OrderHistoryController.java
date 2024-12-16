package vn.hust.bookstore.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.fxml.Initializable;
import vn.hust.bookstore.entity.Customer;
import vn.hust.bookstore.entity.Order;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class OrderHistoryController implements Initializable {

    private Customer customer;
    private Parent root;
    private double x;
    private double y;

    @FXML
    private Button btnBackToCart;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colOrderDate;

    @FXML
    private TableColumn<?, ?> colOrderID;

    @FXML
    private TableColumn<?, ?> colOrderItems;

    @FXML
    private TableColumn<?, ?> colOrderStatus;

    @FXML
    private TableColumn<?, ?> colOrderTotal;

    @FXML
    private AnchorPane mainForm;

    @FXML
    private TableView<Order> orderTable;

    public void goBackToCart() throws IOException {
        mainForm.getScene().getWindow().hide();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vn/hust/bookstore/cart.fxml"));
        root = fxmlLoader.load();

        Stage stage = new Stage();
        Scene scene = new Scene(root);

        root.setOnMousePressed(event -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });

        root.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() - x);
            stage.setY(event.getScreenY() - y);
        });

        CartController cartController = fxmlLoader.getController();
        cartController.setCustomer(this.customer);

        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
        displayOrderHistory();
    }

    private void displayOrderHistory() {
        if (customer != null) {
            ObservableList<Order> purchaseHistory = FXCollections.observableArrayList(customer.getPurchaseHistory());
            orderTable.setItems(purchaseHistory);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colOrderID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colOrderItems.setCellValueFactory(new PropertyValueFactory<>("items"));
        colOrderTotal.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
        colOrderDate.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
        colOrderStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
    }
}
