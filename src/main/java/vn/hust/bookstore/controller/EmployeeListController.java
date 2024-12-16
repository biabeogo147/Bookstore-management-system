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
        // Load data into the TableView
        employeeData.add(new Employee("Nguyen", "Anh", "1995-01-01", true, "anh@gmail.com", "123456789", "Manager", 160, 5, 20.0, true));
        employeeData.add(new Employee("Tran", "Binh", "1998-05-20", false, "binh@gmail.com", "987654321", "Cashier", 150, 3, 15.0, false));

        tableEmployee.setItems(employeeData);
    }

    @FXML
    public void closeWindow() {
        Stage stage = (Stage) tableEmployee.getScene().getWindow();
        stage.close();
    }
}
