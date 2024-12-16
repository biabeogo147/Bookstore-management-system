package vn.hust.bookstore.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import vn.hust.bookstore.entity.BatchRecord;
import vn.hust.bookstore.entity.Product;
import vn.hust.bookstore.entity.StockManager;
import vn.hust.bookstore.service.BatchRecordService;
import vn.hust.bookstore.service.ProductService;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class ImportStockController implements Initializable {

    private ProductService productService = new ProductService();
    private BatchRecordService batchRecordService = new BatchRecordService();

    private StockManager stockManager;

    @FXML
    private Button btnClose;

    @FXML
    private Button btnSubmit;

    @FXML
    private ComboBox<String> cbProductName;

    @FXML
    private Label lblCurrentQuantity;

    @FXML
    private TextField tfAddQuantity;

    @FXML
    private TextField tfInPrice;

    private void loadProductIntoComboBox() {
        List<Product> products = productService.getAllProducts();
        List<String> productNames = products.stream()
                .map(Product::getName)
                .collect(Collectors.toList());
        cbProductName.getItems().setAll(productNames);
    }

    private void loadExistingProductData(Product product) {
        lblCurrentQuantity.setText(String.valueOf(product.getQuantity()));
    }

    public void closeWindow(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public void handleSubmit() {
        String selectedProduct = cbProductName.getValue();
        String addQuantityText = tfAddQuantity.getText();

        if (selectedProduct == null || addQuantityText.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Please fill in all fields!");
            alert.showAndWait();
            return;
        }

        try {
            Long addQuantity = Long.parseLong(addQuantityText);
            Optional<Product> product = productService.getProduct(selectedProduct);

            if (product.isPresent()) {
                Product p = product.get();
                p.setQuantity(p.getQuantity() + addQuantity);
                productService.updateProduct(p);

                lblCurrentQuantity.setText(String.valueOf(p.getQuantity()));

                BatchRecord batchRecord = new BatchRecord();
                batchRecord.setProduct(p);
                batchRecord.setInQuantity(addQuantity);
                batchRecord.setInPrice(Double.parseDouble(tfInPrice.getText()));
                batchRecord.setInDate(new java.sql.Date(System.currentTimeMillis()));
                batchRecordService.addBatchRecord(batchRecord);
            }
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid quantity!");
            alert.showAndWait();
        }
    }

    public void setStockManager(StockManager stockManager) {
        this.stockManager = stockManager;
    }

    @FXML
    public void handleProductSelection() {
        String selectedProduct = cbProductName.getValue();;
        Optional<Product> productOptional = productService.getProduct(selectedProduct);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            loadExistingProductData(product);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadProductIntoComboBox();
    }
}
