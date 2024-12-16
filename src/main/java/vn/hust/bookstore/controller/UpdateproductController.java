package vn.hust.bookstore.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
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
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class UpdateproductController {

    private ProductService productService = new ProductService();

    private boolean isAddingNewProduct;
    private File selectedFile;
    private StockManager stockManager;

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
    private ComboBox<String> cbExistingProducts;

    @FXML
    private ComboBox<String> cbProductType;

    @FXML
    private AnchorPane imagePane;

    @FXML
    private ImageView ivChoosePicture;

    @FXML
    private AnchorPane stationeryPane;

    @FXML
    private TextField tfBookAuthors;

    @FXML
    private TextField tfBookGenre;

    @FXML
    private DatePicker tfBookPublicationDate;

    @FXML
    private TextField tfBookPublisher;

    @FXML
    private TextField tfProductDescription;

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
                double aspectRatio = image.getHeight() / image.getWidth();
                Image resizedImage = new Image(new FileInputStream(selectedFile), 189, 189 * aspectRatio, true, true);
                ivChoosePicture.setImage(resizedImage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void loadExistingProductData(Product product) {
        cbProductType.setValue(product.getClass().getSimpleName());
        tfProductPrice.setText(String.valueOf(product.getPrice()));
        tfProductQuantity.setText(String.valueOf(product.getQuantity()));
        tfProductDescription.setText(product.getDescription());
        if (product instanceof Book book) {
            tfBookAuthors.setText(book.getAuthor());
            tfBookPublisher.setText(book.getPublisher());
            tfBookGenre.setText(book.getGenre());
            tfBookPublicationDate.setValue(book.getPublicationDate().toLocalDate());
        } else if (product instanceof Stationery stationery) {
            tfStationeryBrand.setText(stationery.getBrand());
            tfStationeryType.setText(stationery.getType());
        } else if (product instanceof Toy toy) {
            tfToyBrand.setText(toy.getBrand());
            tfToyAgeGroup.setText(toy.getAgeGroup());
        }
    }

    public void clearProductFields() {
        tfProductPrice.clear();
        tfProductQuantity.setText("0");
        tfProductDescription.clear();
        tfBookPublisher.clear();
        tfBookGenre.clear();
        tfBookPublicationDate.setValue(null);
        tfStationeryBrand.clear();
        tfStationeryType.clear();
        tfToyBrand.clear();
        tfToyAgeGroup.clear();
    }

    private Product createProduct(String productType) {
        return switch (productType) {
            case "Book" -> new Book();
            case "Stationery" -> new Stationery();
            case "Toy" -> new Toy();
            default -> null;
        };
    }

    private void loadProductsIntoComboBox() {
        List<Product> products = productService.getAllProducts();
        List<String> productNames = products.stream()
                .map(Product::getName)
                .collect(Collectors.toList());
        cbExistingProducts.getItems().setAll(productNames);
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

    public void setStockManager(StockManager stockManager) {
        this.stockManager =  stockManager;
    }

    @FXML
    void handleProductTypeChange() {
        String selectedType = (String) cbProductType.getValue();
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

    @FXML
    public void handleProductSelection() {
        String selectedProduct = cbExistingProducts.getEditor().getText();
        Optional<Product> productOptional = productService.getProduct(selectedProduct);
        if (productOptional.isPresent()) {
            isAddingNewProduct = false;
            Product product = productOptional.get();
            btnUpdateProduct.setText("Cập nhật sản phẩm");
            loadExistingProductData(product);
        } else {
            isAddingNewProduct = true;
            btnUpdateProduct.setText("Thêm sản phẩm mới");
            clearProductFields();
        }
    }

    @FXML
    public void handleUpdateOrAddProduct() {
        Product product = createProduct(cbProductType.getValue());
        if (product != null) {
            product.setName(cbExistingProducts.getEditor().getText());
            product.setPrice(Double.parseDouble(tfProductPrice.getText()));
            product.setQuantity(Long.parseLong(tfProductQuantity.getText()));
            product.setDescription(tfProductDescription.getText());
            switch (product) {
                case Book book -> {
                    book.setAuthor(tfBookAuthors.getText());
                    book.setPublisher(tfBookPublisher.getText());
                    book.setGenre(tfBookGenre.getText());
                    book.setPublicationDate(Date.valueOf(tfBookPublicationDate.getValue()));
                }
                case Stationery stationery -> {
                    stationery.setBrand(tfStationeryBrand.getText());
                    stationery.setType(tfStationeryType.getText());
                }
                case Toy toy -> {
                    toy.setBrand(tfToyBrand.getText());
                    toy.setAgeGroup(tfToyAgeGroup.getText());
                }
                default -> {
                }
            }
        }

        System.out.println("Product: " + product);

        if (selectedFile != null) {
            System.out.println("Copying image file to images folder");
            File destDir = new File("src/main/resources/images");
            if (!destDir.exists()) {
                destDir.mkdirs();
            }

            File destFile = new File(destDir, product.getName() + ".png");
            try {
                Files.copy(selectedFile.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Image saved to resources/images");
                product.setImage(product.getName() + ".png");
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println(e);
            }
        } else {
            System.out.println("No images selected to save");
        }

        if (isAddingNewProduct) {
            productService.addProduct(product);
        } else {
            productService.updateProduct(product);
        }
    }

    @FXML
    public void initialize() {
        handleProductTypeChange();
        loadProductsIntoComboBox();
    }
}
