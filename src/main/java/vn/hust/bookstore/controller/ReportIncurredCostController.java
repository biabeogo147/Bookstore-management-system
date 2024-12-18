package vn.hust.bookstore.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import vn.hust.bookstore.entity.Employee;
import vn.hust.bookstore.entity.IncurredCost;
import vn.hust.bookstore.service.EmployeeService;
import vn.hust.bookstore.service.IncurredCostService;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

public class ReportIncurredCostController implements Initializable {

    private IncurredCostService incurredCostService = new IncurredCostService();
    private Employee employee;

    @FXML
    private Button btnSubmit;

    @FXML
    private DatePicker dpDate;

    @FXML
    private AnchorPane mainPane;

    @FXML
    private TextArea taDescription;

    @FXML
    private TextField tfCost;

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @FXML
    public void submitReport() {
        Date date = java.sql.Date.valueOf(dpDate.getValue());
        String description = taDescription.getText();
        Double cost = Double.parseDouble(tfCost.getText());

        if (description.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Please fill in all fields");
            alert.showAndWait();
        } else {
            IncurredCost incurredCost = new IncurredCost();
            incurredCost.setDate(date);
            incurredCost.setDescription(description);
            incurredCost.setCost(cost);
            incurredCost.setEmployee(employee);
            incurredCostService.addIncurredCost(incurredCost);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Report incurred cost successfully");
            alert.showAndWait();
        }
    }

    @FXML
    void closeWindow(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dpDate.setValue(java.time.LocalDate.now());
    }
}
