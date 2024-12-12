package vn.hust.bookstore.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AddEmployeeController {
    @FXML
    private AnchorPane mainPane;

    public void addEmployee(ActionEvent actionEvent) {
    }

    public void minimize() {
        Stage stage = (Stage) mainPane.getScene().getWindow();
        stage.setIconified(true);
    }

    public void close() {
        System.exit(0);
    }
}
