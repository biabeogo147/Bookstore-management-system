package vn.hust.bookstore.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import vn.hust.bookstore.entity.Account;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BookstoreController implements Initializable {

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
    }

}
