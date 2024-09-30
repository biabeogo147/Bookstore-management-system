package vn.hust.bookstore.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import vn.hust.bookstore.service.AccountService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SigninsignupController implements Initializable {

    AccountService accountService = new AccountService();

    private Parent root;

    private double x;
    private double y;

    @FXML
    private Button btnSignin;

    @FXML
    private Button btnSignup;

    @FXML
    private PasswordField tfCfPasswordSignup;

    @FXML
    private TextField tfEmailSignup;

    @FXML
    private TextField tfEmailorPhoneSignin;

    @FXML
    private PasswordField tfPasswordSignin;

    @FXML
    private PasswordField tfPasswordSignup;

    @FXML
    private TextField tfPhoneSiginup;

    @FXML
    private Button btnClose;

    public void close() {
        System.exit(0);
    }

    public void login() throws IOException {
        if (accountService.login(tfEmailorPhoneSignin.getText(), tfPasswordSignin.getText())) {
            System.out.println("Login success");

            btnSignin.getScene().getWindow().hide();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vn/hust/bookstore/bookstore.fxml"));
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

            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(scene);
            stage.show();

        } else {
            System.out.println("Login failed");
        }
    }

    public void signup() {
        if (tfPasswordSignup.getText().equals(tfCfPasswordSignup.getText())) {
            accountService.signup(tfEmailSignup.getText(), tfPhoneSiginup.getText(), tfPasswordSignup.getText(), tfCfPasswordSignup.getText());
            System.out.println("Signup success");
        } else {
            System.out.println("Signup failed");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

}
