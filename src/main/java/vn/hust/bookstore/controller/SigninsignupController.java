package vn.hust.bookstore.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import vn.hust.bookstore.entity.*;
import vn.hust.bookstore.service.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SigninsignupController implements Initializable {

    public AccountService accountService = new AccountService();
    public CustomerService customerService = new CustomerService();

    private Parent root;
    private double x;
    private double y;

    @FXML
    private Button btnSignin;

    @FXML
    private Button btnSignup;

    @FXML
    private PasswordField tfCfPasswordSignup;

    @FXML
    private TextField tfEmailSignup;

    @FXML
    private TextField tfPhoneSignup;

    @FXML
    private TextField tfEmailorPhoneSignin;

    @FXML
    private PasswordField tfPasswordSignin;

    @FXML
    private PasswordField tfPasswordSignup;

    @FXML
    private Button btnClose;

    @FXML
    private void minimize(ActionEvent event) {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }

    public void close() {
        System.exit(0);
    }

    public void switchToBookstore(String fxmlPath) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlPath));
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
        bookstoreController.setCustomer((Customer) accountService.getAccount(tfEmailorPhoneSignin.getText()));

        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.show();

        btnSignin.getScene().getWindow().hide();
    }

    public void switchToEmployee(String fxmlPath) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlPath));
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

        EmployeeController employeeController = fxmlLoader.getController();
        employeeController.setEmployee((Employee) accountService.getAccount(tfEmailorPhoneSignin.getText()));

        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.show();

        btnSignin.getScene().getWindow().hide();
    }

    public void switchToAdmin(String fxmlPath) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlPath));
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

        AdminController adminController = fxmlLoader.getController();
        adminController.setAdmin((Admin) accountService.getAccount(tfEmailorPhoneSignin.getText()));

        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.show();

        btnSignin.getScene().getWindow().hide();
    }

    public void login() throws IOException {
        long role = accountService.login(tfEmailorPhoneSignin.getText(), tfPasswordSignin.getText());
        String fxmlPath = switch ((int) role) {
            case 1 -> "/vn/hust/bookstore/bookstore.fxml";
            case 2 -> "/vn/hust/bookstore/employee.fxml";
            case 3 -> "/vn/hust/bookstore/admin.fxml";
            default -> "";
        };
        if (role == 1)
            switchToBookstore(fxmlPath);
        else if (role == 2)
            switchToEmployee(fxmlPath);
        else if (role == 3)
            switchToAdmin(fxmlPath);
        else
            System.out.println("Login failed");
    }

    public void signup() {
        if (tfPasswordSignup.getText().equals(tfCfPasswordSignup.getText())) {
            customerService.signup(tfEmailSignup.getText(), tfPhoneSignup.getText(), tfPasswordSignup.getText(), tfCfPasswordSignup.getText());
            System.out.println("Signup success");
        } else {
            System.out.println("Signup failed");
        }
    }

    private void addAdmin() {
        Admin admin = new Admin();
        admin.setEmail("admin@gmail.com");
        admin.setPhone("000");
        admin.setFirstName("Admin");
        admin.setLastName("1");
        admin.setPassword("a");
        admin.setStatus(1L);
        admin.setTimeCreated(new java.sql.Date(System.currentTimeMillis()));
        accountService.addAccount(admin);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       //addAdmin();
    }

}