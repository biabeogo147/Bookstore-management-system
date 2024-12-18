package vn.hust.bookstore.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import vn.hust.bookstore.entity.Admin;
import vn.hust.bookstore.entity.IncurredCost;
import vn.hust.bookstore.service.IncurredCostService;

import java.net.URL;
import java.util.ResourceBundle;

public class IncurredCostListController implements Initializable {

    private IncurredCostService incurredCostService = new IncurredCostService();
    private Admin admin;

    @FXML
    private Button btnClose;

    @FXML
    private TableColumn<?, ?> colCost;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colReportingEmployee;

    @FXML
    private TableView<IncurredCost> tableCostReports;

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    @FXML
    public void closeWindow() {
        Stage stage = (Stage) tableCostReports.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colCost.setCellValueFactory(new PropertyValueFactory<>("cost"));
        colReportingEmployee.setCellValueFactory(new PropertyValueFactory<>("name"));

        ObservableList<IncurredCost> incurredCosts = FXCollections.observableArrayList(incurredCostService.getAllIncurredCosts());
        tableCostReports.setItems(incurredCosts);
    }
}
