package vn.hust.bookstore.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import vn.hust.bookstore.entity.*;
import vn.hust.bookstore.service.*;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

public class BookstoreController implements Initializable {

    private ProductService productService = new ProductService();
    private CustomerService customerService = new CustomerService();

    private Customer customer;
    private Parent root;
    private double x;
    private double y;

    @FXML
    private AnchorPane apAccount;

    @FXML
    private AnchorPane apCart;

    @FXML
    private AnchorPane apShopping;

    @FXML
    private Button btnClose;

    @FXML
    private Button btnMinimize;

    @FXML
    private Button btnAddToCart;

    @FXML
    private Button btnPage1;

    @FXML
    private Button btnPage2;

    @FXML
    private Button btnPage3;

    @FXML
    private Button btnPage4;

    @FXML
    private Button btnPage5;

    @FXML
    private ImageView ivBook1;

    @FXML
    private ImageView ivBook2;

    @FXML
    private ImageView ivBook3;

    @FXML
    private ImageView ivBook4;

    @FXML
    private ImageView ivBook5;

    @FXML
    private ImageView ivBook6;

    @FXML
    private ImageView ivBookImage;

    @FXML
    private Label lblBookInfo;

    @FXML
    private Label lblBookName;

    @FXML
    private Label lblBookPrice;

    @FXML
    private Label lblNameBook1;

    @FXML
    private Label lblNameBook2;

    @FXML
    private Label lblNameBook3;

    @FXML
    private Label lblNameBook4;

    @FXML
    private Label lblNameBook5;

    @FXML
    private Label lblNameBook6;

    @FXML
    private Label lblPriceBook1;

    @FXML
    private Label lblPriceBook2;

    @FXML
    private Label lblPriceBook3;

    @FXML
    private Label lblPriceBook4;

    @FXML
    private Label lblPriceBook5;

    @FXML
    private Label lblPriceBook6;

    @FXML
    private AnchorPane mainForm;

    @FXML
    private TextField tfSearch;

    @FXML
    private VBox vbBook1;

    @FXML
    private VBox vbBook2;

    @FXML
    private VBox vbBook3;

    @FXML
    private VBox vbBook4;

    @FXML
    private VBox vbBook5;

    @FXML
    private VBox vbBook6;

    @FXML
    private TextArea taProductDescription;

    private void handleProductClick(int index) {
        Product selectedProduct = productService.getProduct((long) index).orElse(null);
        if (selectedProduct != null) {
            String imagePath = "/images/" + selectedProduct.getImage();
            ivBookImage.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath))));
            lblBookName.setText(selectedProduct.getName());
            lblBookPrice.setText(selectedProduct.getPrice().toString());
            if (selectedProduct.getDescription() != null) {
                taProductDescription.setText(selectedProduct.getDescription() + '\n' + selectedProduct);
            } else {
                taProductDescription.setText(selectedProduct.toString());
            }
        } else {
            ivBookImage.setImage(null);
            lblBookName.setText("");
            lblBookPrice.setText("");
            taProductDescription.setText("");
        }
    }

    public void addToCart() {
        if (customer == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Please login to add to cart");
            alert.showAndWait();
        } else {
            String name = lblBookName.getText();
            System.out.println(name);

            Product product = productService.getProduct(name).orElse(null);
            if (product != null) {
                boolean canAdd = customerService.addToCart(customer, product);
                if (!canAdd) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Product already in cart");
                    alert.showAndWait();
                } else {
                    customer = customerService.getCustomer(customer.getId());
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setHeaderText("Add to cart successfully");
                    alert.showAndWait();
                }
            }
        }
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
        //customerService.addOrder(customer);
        //customerService.clearCart(customer);
        //this.customer = customerService.getCustomer(customer.getId());
    }

    public void switchToAccount() throws IOException {
        mainForm.getScene().getWindow().hide();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vn/hust/bookstore/account.fxml"));
        root = fxmlLoader.load();

        Stage stage = new Stage();
        Scene scene = new Scene(root);

        root.setOnMousePressed(event -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });

        root.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() - x);
            stage.setY(event.getScreenY() - y);
        });

        AccountController accountController = fxmlLoader.getController();
        accountController.setCustomer(customer);

        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToCart() throws IOException {
        mainForm.getScene().getWindow().hide();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vn/hust/bookstore/cart.fxml"));
        root = fxmlLoader.load();

        Stage stage = new Stage();
        Scene scene = new Scene(root);

        root.setOnMousePressed(event -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });

        root.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() - x);
            stage.setY(event.getScreenY() - y);
        });

        CartController cartController = fxmlLoader.getController();
        cartController.setCustomer(this.customer);

        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
    }

    public void minimize() {
        Stage stage = (Stage) mainForm.getScene().getWindow();
        stage.setIconified(true);
    }

    public void close() {
        System.exit(0);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ImageView[] imageViews = {
                ivBook1,
                ivBook2,
                ivBook3,
                ivBook4,
                ivBook5,
                ivBook6
        };

        Label[] nameLabels = {
                lblNameBook1,
                lblNameBook2,
                lblNameBook3,
                lblNameBook4,
                lblNameBook5,
                lblNameBook6
        };

        Label[] priceLabels = {
                lblPriceBook1,
                lblPriceBook2,
                lblPriceBook3,
                lblPriceBook4,
                lblPriceBook5,
                lblPriceBook6
        };

        VBox[] vBoxes = {
                vbBook1,
                vbBook2,
                vbBook3,
                vbBook4,
                vbBook5,
                vbBook6
        };

        Button[] buttons = {
                btnPage1,
                btnPage2,
                btnPage3,
                btnPage4,
                btnPage5
        };

        for (int i = 0; i < buttons.length; i++) {
            int pageIndex = i;
            buttons[i].setOnAction(event -> {
                int start = 6 * pageIndex + 1;
                for (int j = 0; j < imageViews.length; j++) {
                    int productIndex = start + j;
                    Optional<Product> product = productService.getProduct((long) productIndex);
                    if (product.isPresent()) {
                        Product currentProduct = product.get();
                        nameLabels[j].setText(currentProduct.getName());
                        String imagePath = "/images/" + currentProduct.getImage();
                        priceLabels[j].setText(currentProduct.getPrice().toString());
                        imageViews[j].setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath))));
                        vBoxes[j].setOnMouseClicked(vbEvent -> handleProductClick(productIndex));
                    } else {
                        nameLabels[j].setText("");
                        priceLabels[j].setText("");
                        imageViews[j].setImage(null);
                        vBoxes[j].setOnMouseClicked(null);
                    }
                }
            });
        }
        btnPage1.fire();
    }
}
