package vn.hust.bookstore.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import vn.hust.bookstore.entity.Admin;
import vn.hust.bookstore.entity.Cashier;
import vn.hust.bookstore.entity.Employee;
import vn.hust.bookstore.entity.StockManager;
import vn.hust.bookstore.service.AccountService;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

public class AddEmployeeController implements Initializable {

    private AccountService accountService = new AccountService();

    private Admin admin;

    @FXML
    private Button btnAddEmployee;

    @FXML
    private Button btnClose;

    @FXML
    private Button btnMinimize;

    @FXML
    private ComboBox<?> cbEmployeeRole;

    @FXML
    private StackPane mainPane;

    @FXML
    private TextField tfEmail;

    @FXML
    private TextField tfEmployeeName;

    @FXML
    private TextField tfHourlyWage;

    @FXML
    private TextField tfPassword;

    @FXML
    private TextField tfPhone;

    private void setCommonProperties(Employee employee) {
        employee.setFirstName(tfEmployeeName.getText());
        employee.setWorkingHours(0L);
        employee.setLeaveHours(0L);
        employee.setSalary(0.0);
        employee.setHourlyWage(Double.parseDouble(tfHourlyWage.getText()));
        employee.setEmail(tfEmail.getText());
        employee.setPhone(tfPhone.getText());
        employee.setPassword(tfPassword.getText());
        employee.setStatus(1L);
        employee.setTimeCreated(new Date(System.currentTimeMillis()));
    }

    public void addEmployee() {
        if (accountService.getAccount(tfEmail.getText()) != null || accountService.getAccount(tfPhone.getText()) != null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Email or phone number already exists");
            alert.showAndWait();
            return;
        }
        String role = cbEmployeeRole.getValue().toString();
        if (role.equals("Cashier")) {
            Cashier cashier = new Cashier();
            setCommonProperties(cashier);
            accountService.addAccount(cashier);
        } else if (role.equals("Stock Manager")) {
            StockManager stockManager = new StockManager();
            setCommonProperties(stockManager);
            accountService.addAccount(stockManager);
        }
    }

    public void minimize() {
        Stage stage = (Stage) mainPane.getScene().getWindow();
        stage.setIconified(true);
    }

    public void close(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
