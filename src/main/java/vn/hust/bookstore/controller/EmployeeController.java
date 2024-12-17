package vn.hust.bookstore.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import vn.hust.bookstore.entity.*;
import vn.hust.bookstore.service.AccountService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EmployeeController implements Initializable {

    private AccountService accountService = new AccountService();

    private Employee employee;
    private Parent root;
    private double x;
    private double y;

    @FXML
    private Button btnAddProduct;

    @FXML
    private Button btnClose;

    @FXML
    private Button btnMinimize;

    @FXML
    private Button btnUpdateInfo;

    @FXML
    private Button btnViewOrders;

    @FXML
    private Button btnImportStock;

    @FXML
    private CheckBox cbFemale;

    @FXML
    private CheckBox cbMale;

    @FXML
    private AnchorPane contentPane;

    @FXML
    private DatePicker dpBirthday;

    @FXML
    private Label lblEmployeeName;

    @FXML
    private Label lblHourlyWage;

    @FXML
    private Label lblLeaveHours;

    @FXML
    private Label lblRole;

    @FXML
    private Label lblWorkHours;

    @FXML
    private AnchorPane mainPane;

    @FXML
    private TextField tfEmail;

    @FXML
    private TextField tfPhone;

    @FXML
    private AnchorPane updateInfoPane;

    public void minimize() {
        Stage stage = (Stage) mainPane.getScene().getWindow();
        stage.setIconified(true);
    }

    public void close() {
        System.exit(0);
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

    public void updateEmployeeInfo(ActionEvent actionEvent) {
        employee.setMale(cbMale.isSelected());
        employee.setBirthday(java.sql.Date.valueOf(dpBirthday.getValue()));
        employee.setEmail(tfEmail.getText());
        employee.setPhone(tfPhone.getText());
        accountService.update(employee);
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
        if (employee instanceof Cashier)
        {
            lblRole.setText("Cashier");
            btnViewOrders.setVisible(true);
            btnAddProduct.setDisable(true);
            btnAddProduct.setVisible(false);
            btnImportStock.setDisable(true);
            btnImportStock.setVisible(false);
        }
        else
        {
            lblRole.setText("Stock Manager");
            btnAddProduct.setVisible(true);
            btnImportStock.setVisible(true);
            btnViewOrders.setDisable(true);
            btnViewOrders.setVisible(false);
        }
        lblEmployeeName.setText(employee.getFirstName() + " " + employee.getLastName());
        lblHourlyWage.setText(String.valueOf(employee.getHourlyWage()));
        lblLeaveHours.setText(String.valueOf(employee.getLeaveHours()));
        lblWorkHours.setText(String.valueOf(employee.getWorkingHours()));
        tfEmail.setText(employee.getEmail() != null ? employee.getEmail() : "");
        tfPhone.setText(employee.getPhone() != null ? employee.getPhone() : "");
        dpBirthday.setValue(employee.getBirthday() != null ? employee.getBirthday().toLocalDate() : null);
        if (employee.isMale()) {
            cbMale.setSelected(true);
        } else {
            cbFemale.setSelected(true);
        }
    }

    @FXML
    void importStock() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vn/hust/bookstore/view/ImportStock.fxml"));
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

        ImportStockController importStockController = fxmlLoader.getController();
        importStockController.setStockManager((StockManager) this.employee);

        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void addProduct() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vn/hust/bookstore/view/updateproduct.fxml"));
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

        UpdateproductController updateproductController = fxmlLoader.getController();
        updateproductController.setStockManager((StockManager) this.employee);

        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void viewOrders() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vn/hust/bookstore/view/OrderList.fxml"));
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

        OrderListController orderListController = fxmlLoader.getController();
        orderListController.setCashier((Cashier) this.employee);

        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
