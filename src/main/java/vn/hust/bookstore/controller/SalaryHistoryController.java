package vn.hust.bookstore.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import vn.hust.bookstore.entity.SalaryHistory;

public class SalaryHistoryController {
    @FXML
    private TableView<SalaryHistory> tableSalaryHistory;

    private ObservableList<SalaryHistory> salaryData = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
    }

    @FXML
    public void closeWindow() {
        Stage stage = (Stage) tableSalaryHistory.getScene().getWindow();
        stage.close();
    }
}
