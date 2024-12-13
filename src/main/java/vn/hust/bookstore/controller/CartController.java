package vn.hust.bookstore.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import vn.hust.bookstore.entity.Customer;
import vn.hust.bookstore.entity.Product;
import vn.hust.bookstore.service.CustomerService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CartController implements Initializable {

    public CustomerService customerService = new CustomerService();

    private Customer customer;
    private Parent root;
    private double x;
    private double y;

    @FXML
    private AnchorPane apAccount;

    @FXML
    private AnchorPane apCart;

    @FXML
    private AnchorPane apOrderHistory;

    @FXML
    private AnchorPane apShopping;

    @FXML
    private Button btnCheckout;

    @FXML
    private Button btnClearCart;

    @FXML
    private Button btnClose;

    @FXML
    private Button btnMinimize;

    @FXML
    private TableView<Product> cartTable;

    @FXML
    private TableColumn<?, ?> colItemName;

    @FXML
    private TableColumn<?, ?> colPrice;

    @FXML
    private TableColumn<Product, String> colDescription;

    @FXML
    private Label lblTotal;

    @FXML
    private AnchorPane mainForm;

    @FXML
    private TextField txtShippingAddress;

    public void setCustomer(Customer customer) {
        this.customer = customer;
        populateCart();
    }

    public void populateCart() {
        if (customer != null) {
            ObservableList<Product> cartItems = FXCollections.observableArrayList(customer.getCart());
            cartTable.setItems(cartItems);
            double total = cartItems.stream().mapToDouble(Product::getPrice).sum();
            lblTotal.setText(String.valueOf(total));
        }
    }

    public void checkoutCart() {
        if (customer.getCart().isEmpty()) {
            return;
        }
        customerService.addOrder(customer, txtShippingAddress.getText());
        clearCart();
    }

    public void clearCart() {
        customerService.clearCart(customer);
        customer = customerService.getCustomer(customer.getId());
        populateCart();
    }

    public void switchToShopping() throws IOException {
        mainForm.getScene().getWindow().hide();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vn/hust/bookstore/bookstore.fxml"));
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

        BookstoreController bookstoreController = fxmlLoader.getController();
        bookstoreController.setCustomer(customer);

        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToAccount() throws IOException {
        mainForm.getScene().getWindow().hide();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vn/hust/bookstore/account.fxml"));
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

        AccountController accountController = fxmlLoader.getController();
        accountController.setCustomer(customer);

        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
    }

    public void viewOrderHistory() throws IOException {
        mainForm.getScene().getWindow().hide();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vn/hust/bookstore/OrderHistory.fxml"));
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

        OrderHistoryController orderHistoryController = fxmlLoader.getController();
        orderHistoryController.setCustomer(customer);

        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
    }

    public void minimize() {
        Stage stage = (Stage) mainForm.getScene().getWindow();
        stage.setIconified(true);
    }

    public void close() {
        System.exit(0);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colItemName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colDescription.setCellValueFactory(cellData -> {
            Product product = (Product) cellData.getValue();
            String descriptionWithToString = product.getDescription() + " " + product.toString();
            return new SimpleStringProperty(descriptionWithToString);
        });
    }
}
