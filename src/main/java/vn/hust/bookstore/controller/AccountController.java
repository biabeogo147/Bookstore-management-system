package vn.hust.bookstore.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import vn.hust.bookstore.entity.Account;
import vn.hust.bookstore.entity.Customer;
import vn.hust.bookstore.service.AccountService;
import vn.hust.bookstore.service.CustomerService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AccountController implements Initializable {

    public AccountService accountService = new AccountService();
    private Customer customer;
    private Parent root;
    private double x;
    private double y;

    @FXML
    private AnchorPane apAccount;

    @FXML
    private AnchorPane apCart;

    @FXML
    private AnchorPane apShopping;

    @FXML
    private Button btnAccountInfo;

    @FXML
    private Button btnClose;

    @FXML
    private Button btnMinimize;

    @FXML
    private Button btnMyOrders;

    @FXML
    private Button btnSave;

    @FXML
    private CheckBox cbFemale;

    @FXML
    private CheckBox cbMale;

    @FXML
    private DatePicker dpBirthday;

    @FXML
    private AnchorPane mainForm;

    @FXML
    private TextField tfEmail;

    @FXML
    private TextField tfFirstName;

    @FXML
    private TextField tfLastName;

    @FXML
    private TextField tfPhone;

    public void save() {
        customer.setEmail(tfEmail.getText());
        customer.setFirstName(tfFirstName.getText());
        customer.setLastName(tfLastName.getText());
        customer.setPhone(tfPhone.getText());
        customer.setBirthday(java.sql.Date.valueOf(dpBirthday.getValue()));
        customer.setMale(cbMale.isSelected());
        accountService.update(customer);
    }

    public void isMaleChecked() {
        if (cbMale.isSelected()) {
            cbFemale.setSelected(false);
        }
    }

    public void isFemaleChecked() {
        if (cbFemale.isSelected()) {
            cbMale.setSelected(false);
        }
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
        tfEmail.setText(customer.getEmail() != null ? customer.getEmail() : "");
        tfFirstName.setText(customer.getFirstName() != null ? customer.getFirstName() : "");
        tfLastName.setText(customer.getLastName() != null ? customer.getLastName() : "");
        tfPhone.setText(customer.getPhone() != null ? customer.getPhone() : "");
        dpBirthday.setValue(customer.getBirthday() != null ? customer.getBirthday().toLocalDate() : null);
        if (customer.isMale()) {
            cbMale.setSelected(true);
        } else {
            cbFemale.setSelected(true);
        }
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

    public void switchToCart() throws IOException {
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

    public void minimize() {
        Stage stage = (Stage) mainForm.getScene().getWindow();
        stage.setIconified(true);
    }

    public void close() {
        System.exit(0);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
