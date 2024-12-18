package vn.hust.bookstore.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import vn.hust.bookstore.entity.IncurredCost;

public class IncurredCostListController {

    @FXML
    private TableView<IncurredCost> tableCostReports;

    private ObservableList<IncurredCost> costData = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
//        costData.add(new IncurredCost("2024-01-01", "Chi phí sửa chữa máy tính", "200.0"));
//        costData.add(new IncurredCost("2024-01-05", "Mua văn phòng phẩm", "150.0"));
//        tableCostReports.setItems(costData);
    }

    @FXML
    public void closeWindow() {
        Stage stage = (Stage) tableCostReports.getScene().getWindow();
        stage.close();
    }
}
