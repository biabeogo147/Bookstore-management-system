package vn.hust.bookstore.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import vn.hust.bookstore.entity.Admin;
import vn.hust.bookstore.entity.Cashier;
import vn.hust.bookstore.entity.StockManager;
import vn.hust.bookstore.service.AccountService;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

public class AdminController implements Initializable {

    public AccountService accountService = new AccountService();

    private Admin admin;
    private Parent root;
    private double x, y;

    @FXML
    private BarChart<?, ?> barChartProfit;

    @FXML
    private Button btnViewRevenue;

    @FXML
    private AnchorPane contentPane;

    @FXML
    private CategoryAxis costXAxis;

    @FXML
    private NumberAxis costYAxis;

    @FXML
    private Label lblTitle;

    @FXML
    private LineChart<?, ?> lineChartCosts;

    @FXML
    private AnchorPane mainPane;

    @FXML
    private PieChart pieChartRevenue;

    @FXML
    private CategoryAxis profitXAxis;

    @FXML
    private NumberAxis profitYAxis;

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    private void addCashier() {
        Cashier cashier = new Cashier();
        cashier.setFirstName("Cashier");
        cashier.setLastName("1");
        cashier.setEmail("cashier@gmail.com");
        cashier.setPhone("111");
        cashier.setPassword("c");
        cashier.setMale(true);
        cashier.setStatus(1L);
        cashier.setTimeCreated(new Date(System.currentTimeMillis()));
        accountService.addAccount(cashier);
    }

    private void addStockManager() {
        StockManager stockManager = new StockManager();
        stockManager.setFirstName("Stock Manager");
        stockManager.setLastName("1");
        stockManager.setEmail("stockmanager@gmail.com");
        stockManager.setPhone("222");
        stockManager.setPassword("s");
        stockManager.setMale(true);
        stockManager.setStatus(1L);
        stockManager.setTimeCreated(new Date(System.currentTimeMillis()));
        accountService.addAccount(stockManager);
    }

    public void minimize() {
        Stage stage = (Stage) mainPane.getScene().getWindow();
        stage.setIconified(true);
    }

    public void close() {
        System.exit(0);
    }

    @FXML
    void addEmployee() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vn/hust/bookstore/AddEmployee.fxml"));
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

        AddEmployeeController addEmployeeController = fxmlLoader.getController();
        addEmployeeController.setAdmin(this.admin);

        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //addCashier();
        //addStockManager();
    }


}
