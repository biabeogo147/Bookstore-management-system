package vn.hust.bookstore.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class UpdateproductController {

    @FXML
    private ComboBox<String> cbProductType;

    @FXML
    private AnchorPane bookPane;

    @FXML
    private AnchorPane stationeryPane;

    @FXML
    private AnchorPane toyPane;

    @FXML
    private TextField tfProductName, tfProductPrice, tfProductQuantity, tfProductDescription;

    @FXML
    private TextField tfBookAuthor, tfBookPublisher, tfBookGenre, tfBookPublicationDate;

    @FXML
    private TextField tfStationeryBrand, tfStationeryType;

    @FXML
    private TextField tfToyBrand, tfToyAgeGroup;

    @FXML
    private ImageView ivProductImage;

    private File selectedFile;  // File để lưu trữ ảnh sản phẩm được chọn

    @FXML
    public void initialize() {
        // Đặt giao diện mặc định là của Book
        handleProductTypeChange();
    }

    // Phương thức xử lý khi loại sản phẩm được thay đổi
    public void handleProductTypeChange() {
        String selectedType = cbProductType.getValue();

        // Hiển thị giao diện tương ứng với loại sản phẩm
        if ("Book".equals(selectedType)) {
            bookPane.setVisible(true);
            stationeryPane.setVisible(false);
            toyPane.setVisible(false);
        } else if ("Stationery".equals(selectedType)) {
            bookPane.setVisible(false);
            stationeryPane.setVisible(true);
            toyPane.setVisible(false);
        } else if ("Toy".equals(selectedType)) {
            bookPane.setVisible(false);
            stationeryPane.setVisible(false);
            toyPane.setVisible(true);
        }
    }

    // Phương thức để xử lý chọn ảnh
    public void chooseImage() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
        );
        selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            try {
                FileInputStream inputStream = new FileInputStream(selectedFile);
                Image image = new Image(inputStream);
                ivProductImage.setImage(image);  // Hiển thị ảnh trong ImageView
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    // Phương thức để xử lý cập nhật sản phẩm
    public void updateProduct() {
        String name = tfProductName.getText();
        String price = tfProductPrice.getText();
        String quantity = tfProductQuantity.getText();
        String description = tfProductDescription.getText();
        String productType = cbProductType.getValue();

        if ("Book".equals(productType)) {
            String author = tfBookAuthor.getText();
            String publisher = tfBookPublisher.getText();
            String genre = tfBookGenre.getText();
            String publicationDate = tfBookPublicationDate.getText();
            System.out.println("Sản phẩm sách: " + name + ", Giá: " + price + ", Số lượng: " + quantity + ", Tác giả: " + author + ", Nhà xuất bản: " + publisher + ", Thể loại: " + genre + ", Ngày xuất bản: " + publicationDate);
        } else if ("Stationery".equals(productType)) {
            String brand = tfStationeryBrand.getText();
            String type = tfStationeryType.getText();
            System.out.println("Sản phẩm văn phòng phẩm: " + name + ", Giá: " + price + ", Số lượng: " + quantity + ", Thương hiệu: " + brand + ", Loại: " + type);
        } else if ("Toy".equals(productType)) {
            String brand = tfToyBrand.getText();
            String ageGroup = tfToyAgeGroup.getText();
            System.out.println("Sản phẩm đồ chơi: " + name + ", Giá: " + price + ", Số lượng: " + quantity + ", Thương hiệu: " + brand + ", Nhóm tuổi: " + ageGroup);
        }

        // In ra thông tin ảnh nếu có
        if (selectedFile != null) {
            System.out.println("Đã chọn ảnh: " + selectedFile.getAbsolutePath());
        }

        // TODO: Thêm logic để cập nhật sản phẩm vào cơ sở dữ liệu
    }

    // Phương thức để đóng cửa sổ
    public void closeWindow(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    // Phương thức để thu nhỏ cửa sổ
    public void minimizeWindow(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.setIconified(true);
    }

}
