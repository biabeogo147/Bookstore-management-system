package vn.hust.bookstore.controller;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import vn.hust.bookstore.entity.Admin;
import vn.hust.bookstore.entity.Employee;
import vn.hust.bookstore.entity.Order;
import vn.hust.bookstore.entity.SalaryHistory;
import vn.hust.bookstore.service.SalaryHistoryService;

import java.net.URL;
import java.util.ResourceBundle;

public class SalaryHistoryController implements Initializable {

    private SalaryHistoryService salaryHistoryService = new SalaryHistoryService();

    private Admin admin;

    @FXML
    private Button btnClose;

    @FXML
    private TableColumn<SalaryHistory, String> colFullName;

    @FXML
    private TableColumn<?, ?> colHourlyWage;

    @FXML
    private TableColumn<?, ?> colLeaveHours;

    @FXML
    private TableColumn<SalaryHistory, CheckBox> colPaid;

    @FXML
    private TableColumn<?, ?> colPayDate;

    @FXML
    private TableColumn<?, ?> colTotalSalary;

    @FXML
    private TableColumn<?, ?> colWorkHours;

    @FXML
    private TableColumn<?, ?> colSalaryMonth;

    @FXML
    private AnchorPane mainPane;

    @FXML
    private TableView<SalaryHistory> tableSalaryHistory;

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    private void loadSalaryHistory() {
        ObservableList<SalaryHistory> salaryHistories = FXCollections.observableArrayList(salaryHistoryService.getAllSalaryHistories());
        tableSalaryHistory.setItems(salaryHistories);
    }

    @FXML
    public void closeWindow() {
        Stage stage = (Stage) mainPane.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        colHourlyWage.setCellValueFactory(new PropertyValueFactory<>("hourlyWage"));
        colWorkHours.setCellValueFactory(new PropertyValueFactory<>("workingHours"));
        colLeaveHours.setCellValueFactory(new PropertyValueFactory<>("leaveHours"));
        colSalaryMonth.setCellValueFactory(new PropertyValueFactory<>("month"));
        colTotalSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colPayDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colFullName.setCellValueFactory(param -> {
            SalaryHistory salaryHistory = param.getValue();
            Employee employee = salaryHistory.getEmployee();
            return new SimpleStringProperty(employee.getFirstName() + " " + employee.getLastName());
        });
        colPaid.setCellFactory(column -> new TableCell<SalaryHistory, CheckBox>() {
            @Override
            protected void updateItem(CheckBox item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    SalaryHistory salaryHistory = getTableView().getItems().get(getIndex());
                    CheckBox checkBox = new CheckBox();
                    checkBox.setSelected(salaryHistory.isPaid());
                    checkBox.setDisable(salaryHistory.isPaid());
                    checkBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
                        if (newValue) {
                            checkBox.setDisable(true);
                            salaryHistory.setPaid(true);
                            salaryHistory.setDate(new java.sql.Date(System.currentTimeMillis()));
                            salaryHistoryService.updateSalaryHistory(salaryHistory);
                            getTableView().refresh();
                        }
                    });
                    setGraphic(checkBox);
                }
            }
        });
        loadSalaryHistory();
    }
}
