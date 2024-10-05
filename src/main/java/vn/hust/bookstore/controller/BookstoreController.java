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
import vn.hust.bookstore.entity.Account;
import vn.hust.bookstore.entity.Book;
import vn.hust.bookstore.entity.Product;
import vn.hust.bookstore.entity.Toy;
import vn.hust.bookstore.service.ProductService;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

public class BookstoreController implements Initializable {

    public ProductService productService = new ProductService();

    private Account account;
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

    public void getAccount(Account account) {
        this.account = account;
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
        accountController.getAccount(account);

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
//        for (int i = 1; i <= 100; ++i) {
//            Toy toy = new Toy();
//            Book book = new Book();
//            if (i % 2 == 0) {
//                toy.setName("Toy " + i);
//                toy.setPrice(100.0 * i);
//                productService.addProduct(toy);
//            } else {
//                book.setName("Book " + i);
//                book.setPrice(100.0 * i);
//                productService.addProduct(book);
//            }
//        }

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
                        String imagePath = "/image/" + (productIndex) + ".png";
                        imageViews[j].setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath))));
                        nameLabels[j].setText(product.get().getName());
                        priceLabels[j].setText(product.get().getPrice().toString());
                        int index = j;
                        vBoxes[j].setOnMouseClicked(vbEvent -> {
                            Product selectedProduct = productService.getProduct((long) (start + index)).orElse(null);
                            if (selectedProduct != null) {
                                ivBookImage.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath))));
                                lblBookName.setText(selectedProduct.getName());
                                lblBookPrice.setText(selectedProduct.getPrice().toString());
                                lblBookInfo.setText(selectedProduct.getDescription());
                            }
                        });
                    }
                }
            });
        }
        btnPage1.fire();
    }

}
