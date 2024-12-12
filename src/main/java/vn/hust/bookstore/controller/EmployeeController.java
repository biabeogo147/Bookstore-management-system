package vn.hust.bookstore.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import vn.hust.bookstore.entity.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EmployeeController implements Initializable {

    private Employee employee;
    private Parent root;
    private double x;
    private double y;

    @FXML
    private Button btnAddProduct;

    @FXML
    private Button btnViewOrders;

    @FXML
    private AnchorPane contentPane;

    @FXML
    private Label lblEmployeeName;

    @FXML
    private Label lblHourlyWage;

    @FXML
    private Label lblLeaveHours;

    @FXML
    private Label lblRole;

    @FXML
    private Label lblTitle;

    @FXML
    private Label lblWorkHours;

    @FXML
    private AnchorPane mainPane;

    @FXML
    private CheckBox cbFemale;

    @FXML
    private CheckBox cbMale;

    public void setEmployee(Employee employee) {
        this.employee = employee;
        if (employee instanceof Cashier)
        {
            lblTitle.setText("Cashier");
            lblRole.setText("Cashier");
            btnViewOrders.setVisible(true);
            btnAddProduct.setDisable(true);
            btnAddProduct.setVisible(false);
        }
        else
        {
            lblTitle.setText("Stock Manager");
            lblRole.setText("Stock Manager");
            btnAddProduct.setVisible(true);
            btnViewOrders.setDisable(true);
            btnViewOrders.setVisible(false);
        }
    }

    @FXML
    void addProduct() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vn/hust/bookstore/updateproduct.fxml"));
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
    void viewOrders(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

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

    }

    public void updateEmployeeInfo(ActionEvent actionEvent) {
    }
}
