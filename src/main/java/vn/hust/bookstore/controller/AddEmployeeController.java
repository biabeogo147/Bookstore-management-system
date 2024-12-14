package vn.hust.bookstore.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import vn.hust.bookstore.entity.Admin;
import vn.hust.bookstore.entity.Cashier;
import vn.hust.bookstore.entity.StockManager;
import vn.hust.bookstore.service.AccountService;

import java.net.URL;
import java.util.ResourceBundle;

public class AddEmployeeController implements Initializable {

    public AccountService accountService = new AccountService();

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
    private TextField tfEmployeeName;

    @FXML
    private TextField tfHourlyWage;

    @FXML
    private AnchorPane mainPane;

    public void addEmployee() {
        if (cbEmployeeRole.getValue().equals("Cashier")) {
            Cashier cashier = new Cashier();
            cashier.setFirstName(tfEmployeeName.getText());
            cashier.setHourlyWage(Double.parseDouble(tfHourlyWage.getText()));
            accountService.addAccount(cashier);
        } else if (cbEmployeeRole.getValue().equals("Stock Manager")) {
            StockManager stockManager = new StockManager();
            stockManager.setFirstName(tfEmployeeName.getText());
            stockManager.setHourlyWage(Double.parseDouble(tfHourlyWage.getText()));
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
