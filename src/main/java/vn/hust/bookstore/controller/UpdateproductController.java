package vn.hust.bookstore.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import vn.hust.bookstore.entity.*;
import vn.hust.bookstore.service.ProductService;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.util.Optional;

public class UpdateproductController {

    ProductService productService = new ProductService();

    private File selectedFile;

    @FXML
    private AnchorPane bookPane;

    @FXML
    private Button btnClose;

    @FXML
    private Button btnMinimize;

    @FXML
    private Button btnUpdateProduct;

    @FXML
    private Button btnUploadImage;

    @FXML
    private ComboBox<String> cbProductType;

    @FXML
    private AnchorPane imagePane;

    @FXML
    private ImageView ivChoosePicture;

    @FXML
    private AnchorPane stationeryPane;

    @FXML
    private TextField tfBookGenre;

    @FXML
    private DatePicker tfBookPublicationDate;

    @FXML
    private TextField tfBookPublisher;

    @FXML
    private TextField tfProductDescription;

    @FXML
    private TextField tfProductName;

    @FXML
    private TextField tfProductPrice;

    @FXML
    private TextField tfProductQuantity;

    @FXML
    private TextField tfStationeryBrand;

    @FXML
    private TextField tfStationeryType;

    @FXML
    private TextField tfToyAgeGroup;

    @FXML
    private TextField tfToyBrand;

    @FXML
    private AnchorPane toyPane;

    @FXML
    public void initialize() {
        handleProductTypeChange();
    }

    public void handleProductTypeChange() {
        String selectedType = cbProductType.getValue();

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
                ivChoosePicture.setImage(image);  // Hiển thị ảnh trong ImageView
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void updateProduct() {
        String name = tfProductName.getText();
        String price = tfProductPrice.getText();
        String quantity = tfProductQuantity.getText();
        String description = tfProductDescription.getText();
        String productType = cbProductType.getValue();

        Optional<Product> product = null;
        if ("Book".equals(productType)) {
            Book book = new Book();
            book.setName(name);
            book.setPrice(Double.parseDouble(price));
            book.setQuantity(Long.parseLong(quantity));
            book.setDescription(description);
            book.setPublisher(tfBookPublisher.getText());
            book.setGenre(tfBookGenre.getText());
            book.setPublicationDate(Date.valueOf(tfBookPublicationDate.getValue()));
            product = productService.addProduct(book);
        } else if ("Stationery".equals(productType)) {
            Stationery stationery = new Stationery();
            stationery.setName(name);
            stationery.setPrice(Double.parseDouble(price));
            stationery.setQuantity(Long.parseLong(quantity));
            stationery.setDescription(description);
            stationery.setBrand(tfStationeryBrand.getText());
            stationery.setType(tfStationeryType.getText());
            product = productService.addProduct(stationery);
        } else if ("Toy".equals(productType)) {
            Toy toy = new Toy();
            toy.setName(name);
            toy.setPrice(Double.parseDouble(price));
            toy.setQuantity(Long.parseLong(quantity));
            toy.setDescription(description);
            toy.setBrand(tfToyBrand.getText());
            toy.setAgeGroup(tfToyAgeGroup.getText());
            product = productService.addProduct(toy);
        }
        Long id = product.get().getId();

        if (selectedFile != null) {
            File destDir = new File("src/main/resources/images");
            if (!destDir.exists()) {
                destDir.mkdirs(); // Tạo thư mục nếu chưa tồn tại
            }

            File destFile = new File(destDir, id + ".png");
            try {
                Files.copy(selectedFile.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Image saved to resources/images");
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println(e);
            }
        } else {
            System.out.println("No images selected to save");
        }
    }

    public void closeWindow(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public void minimizeWindow(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.setIconified(true);
    }

}
