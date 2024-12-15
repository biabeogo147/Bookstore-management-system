package vn.hust.bookstore.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ImportStockController {

    @FXML
    private ComboBox<String> cbProductName;

    @FXML
    private Label lblCurrentQuantity;

    @FXML
    private TextField tfAddQuantity;

    public void closeWindow(ActionEvent actionEvent) {
    }

    public void minimizeWindow(ActionEvent actionEvent) {
    }

    public void handleSubmit(ActionEvent actionEvent) {

    }

    // Code ChatGPT

//    private ObservableList<Product> products;

//    @FXML
//    public void initialize() {
//        // Load product list
//        products = FXCollections.observableArrayList(
//                new Product("Book1", 10),
//                new Product("Stationery1", 5),
//                new Product("Toy1", 20)
//        );
//
//        // Populate ComboBox
//        for (Product product : products) {
//            cbProductName.getItems().add(product.getName());
//        }
//
//        // Handle product selection
//        cbProductName.setOnAction(event -> {
//            String selectedProduct = cbProductName.getValue();
//            if (selectedProduct != null) {
//                Product product = findProductByName(selectedProduct);
//                if (product != null) {
//                    lblCurrentQuantity.setText(String.valueOf(product.getQuantity()));
//                }
//            }
//        });
//    }
//
//    @FXML
//    public void handleSubmit() {
//        String selectedProduct = cbProductName.getValue();
//        String addQuantityText = tfAddQuantity.getText();
//
//        if (selectedProduct == null || addQuantityText.isEmpty()) {
//            showAlert(AlertType.ERROR, "Lỗi", "Vui lòng chọn sản phẩm và nhập số lượng!");
//            return;
//        }
//
//        try {
//            int addQuantity = Integer.parseInt(addQuantityText);
//            Product product = findProductByName(selectedProduct);
//
//            if (product != null) {
//                product.setQuantity(product.getQuantity() + addQuantity);
//                lblCurrentQuantity.setText(String.valueOf(product.getQuantity()));
//                showAlert(AlertType.INFORMATION, "Thành công", "Số lượng đã được cập nhật!");
//            }
//        } catch (NumberFormatException e) {
//            showAlert(AlertType.ERROR, "Lỗi", "Vui lòng nhập số lượng hợp lệ!");
//        }
//    }
//
//    private Product findProductByName(String name) {
//        return products.stream().filter(p -> p.getName().equals(name)).findFirst().orElse(null);
//    }
//
//    private void showAlert(AlertType type, String title, String content) {
//        Alert alert = new Alert(type);
//        alert.setTitle(title);
//        alert.setContentText(content);
//        alert.showAndWait();
//    }
}
