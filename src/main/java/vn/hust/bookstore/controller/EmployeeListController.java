package vn.hust.bookstore.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import vn.hust.bookstore.entity.Admin;
import vn.hust.bookstore.entity.Cashier;
import vn.hust.bookstore.entity.Employee;
import vn.hust.bookstore.service.EmployeeService;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

public class EmployeeListController implements Initializable {

    private EmployeeService employeeService = new EmployeeService();

    private Admin admin;

    @FXML
    private Button btnClose;

    @FXML
    private TableColumn<Employee, Date> colBirthDate;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colFirstName;

    @FXML
    private TableColumn<Employee, String> colGender;

    @FXML
    private TableColumn<?, ?> colHourlyWage;

    @FXML
    private TableColumn<?, ?> colLastName;

    @FXML
    private TableColumn<?, ?> colPhone;

    @FXML
    private TableColumn<Employee, String> colRole;

    @FXML
    private TableColumn<Employee, String> colStatus;

    @FXML
    private AnchorPane mainPane;

    @FXML
    private TableView<Employee> tableEmployee;

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    private void loadEmployeeList() {
        ObservableList<Employee> employeeList = FXCollections.observableArrayList();
        employeeList.addAll(employeeService.getAllEmployees());
        tableEmployee.setItems(employeeList);
    }

    @FXML
    public void closeWindow() {
        Stage stage = (Stage) mainPane.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        colFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        colLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        colGender.setCellValueFactory(cellData -> {
            Employee employee = cellData.getValue();
            return new SimpleStringProperty(employee.isMale() ? "Nam" : "Nữ");
        });
        colBirthDate.setCellValueFactory(new PropertyValueFactory<>("birthday"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colRole.setCellValueFactory(cellData -> {
            Employee employee = cellData.getValue();
            return new SimpleStringProperty(employee instanceof Cashier ? "cashier" : "stock manager");
        });
        colHourlyWage.setCellValueFactory(new PropertyValueFactory<>("hourlyWage"));
        colStatus.setCellValueFactory(cellData -> {
            Employee employee = cellData.getValue();
            return new SimpleStringProperty(employee.getStatus() == 1 ? "active" : "inactive");
        });
        loadEmployeeList();
    }
}
