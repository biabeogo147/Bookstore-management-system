package vn.hust.bookstore.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import vn.hust.bookstore.entity.Employee;

public class EmployeeListController {
    @FXML
    private TableView<Employee> tableEmployee;

    private ObservableList<Employee> employeeData = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        tableEmployee.setItems(employeeData);
    }

    @FXML
    public void closeWindow() {
        Stage stage = (Stage) tableEmployee.getScene().getWindow();
        stage.close();
    }
}
