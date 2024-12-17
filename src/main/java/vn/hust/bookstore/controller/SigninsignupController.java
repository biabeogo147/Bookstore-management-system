package vn.hust.bookstore.controller;

import javafx.event.ActionEvent;
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
import vn.hust.bookstore.entity.*;
import vn.hust.bookstore.service.*;

import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;

public class SigninsignupController implements Initializable {

    private ProductService productService = new ProductService();
    private AccountService accountService = new AccountService();
    private CustomerService customerService = new CustomerService();
    private SalaryHistoryService salaryHistoryService = new SalaryHistoryService();

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
    private TextField tfPhoneSignup;

    @FXML
    private TextField tfEmailorPhoneSignin;

    @FXML
    private PasswordField tfPasswordSignin;

    @FXML
    private PasswordField tfPasswordSignup;

    @FXML
    private Button btnClose;

    @FXML
    private void minimize(ActionEvent event) {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }

    public void close() {
        System.exit(0);
    }

    public void switchToBookstore(String fxmlPath) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlPath));
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

        BookstoreController bookstoreController = fxmlLoader.getController();
        bookstoreController.setCustomer((Customer) accountService.getAccount(tfEmailorPhoneSignin.getText()));

        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.show();

        btnSignin.getScene().getWindow().hide();
    }

    public void switchToEmployee(String fxmlPath) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlPath));
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

        EmployeeController employeeController = fxmlLoader.getController();
        employeeController.setEmployee((Employee) accountService.getAccount(tfEmailorPhoneSignin.getText()));

        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.show();

        btnSignin.getScene().getWindow().hide();
    }

    public void switchToAdmin(String fxmlPath) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlPath));
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

        AdminController adminController = fxmlLoader.getController();
        adminController.setAdmin((Admin) accountService.getAccount(tfEmailorPhoneSignin.getText()));

        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.show();

        btnSignin.getScene().getWindow().hide();
    }

    public void login() throws IOException {
        long role = accountService.login(tfEmailorPhoneSignin.getText(), tfPasswordSignin.getText());
        String fxmlPath = switch ((int) role) {
            case 1 -> "/vn/hust/bookstore/view/bookstore.fxml";
            case 2 -> "/vn/hust/bookstore/view/employee.fxml";
            case 3 -> "/vn/hust/bookstore/view/admin.fxml";
            default -> "";
        };
        if (role == 1)
            switchToBookstore(fxmlPath);
        else if (role == 2)
            switchToEmployee(fxmlPath);
        else if (role == 3)
            switchToAdmin(fxmlPath);
        else
            System.out.println("Login failed");
    }

    public void signup() {
        if (tfPasswordSignup.getText().equals(tfCfPasswordSignup.getText())) {
            customerService.signup(tfEmailSignup.getText(), tfPhoneSignup.getText(), tfPasswordSignup.getText(), tfCfPasswordSignup.getText());
            System.out.println("Signup success");
        } else {
            System.out.println("Signup failed");
        }
    }

    private void addAdmin() {
        Admin admin = new Admin();
        admin.setEmail("admin");
        admin.setPhone("000");
        admin.setFirstName("Admin");
        admin.setLastName("1");
        admin.setPassword("a");
        admin.setStatus(1L);
        admin.setTimeCreated(new java.sql.Date(System.currentTimeMillis()));
        accountService.addAccount(admin);
    }

    private void addFakeEmployees() {
        Cashier cashier = new Cashier();
        cashier.setFirstName("Cashier");
        cashier.setLastName("1");
        cashier.setEmail("cashier@gmail.com");
        cashier.setPhone("09122332344");
        cashier.setPassword("c");
        cashier.setStatus(1L);
        cashier.setMale(true);
        cashier.setTimeCreated(new java.sql.Date(System.currentTimeMillis()));
        cashier.setBirthday(new java.sql.Date(System.currentTimeMillis()));
        cashier.setHourlyWage(10.0);
        cashier.setWorkingHours(10L);
        cashier.setLeaveHours(2L);
        accountService.addAccount(cashier);

        StockManager stockManager = new StockManager();
        stockManager.setFirstName("StockManager");
        stockManager.setLastName("1");
        stockManager.setEmail("stockmanager@gmail.com");
        stockManager.setPhone("09122332345");
        stockManager.setPassword("s");
        stockManager.setStatus(1L);
        stockManager.setMale(true);
        stockManager.setTimeCreated(new java.sql.Date(System.currentTimeMillis()));
        stockManager.setBirthday(new java.sql.Date(System.currentTimeMillis()));
        stockManager.setHourlyWage(10.0);
        stockManager.setWorkingHours(10L);
        stockManager.setLeaveHours(0L);
        accountService.addAccount(stockManager);

        Calendar calendar = Calendar.getInstance();
        calendar.set(2024, Calendar.NOVEMBER, 20);

        SalaryHistory salaryHistory = new SalaryHistory();
        salaryHistory.setDate(new java.sql.Date(calendar.getTimeInMillis()));
        salaryHistory.setSalary(1000.0);
        salaryHistory.setHourlyWage(11.0);
        salaryHistory.setWorkingHours(51L);
        salaryHistory.setLeaveHours(1L);
        salaryHistory.setPaid(true);
        salaryHistory.setMonth(11L);
        salaryHistory.setEmployee(cashier);
        salaryHistoryService.addSalaryHistory(salaryHistory);

        salaryHistory = new SalaryHistory();
        salaryHistory.setDate(new java.sql.Date(calendar.getTimeInMillis()));
        salaryHistory.setSalary(2000.0);
        salaryHistory.setHourlyWage(12.0);
        salaryHistory.setWorkingHours(49L);
        salaryHistory.setLeaveHours(0L);
        salaryHistory.setPaid(true);
        salaryHistory.setMonth(11L);
        salaryHistory.setEmployee(stockManager);
        salaryHistoryService.addSalaryHistory(salaryHistory);
    }

    private void addFakeProduct() {
        Book book = new Book();
        book.setName("Em là ai?");
        book.setPrice(10000.0);
        book.setDescription("Không có des");
        book.setQuantity(31L);
        book.setImage("abc.jpg");
        book.setAuthor("Tác giả là ai");
        book.setGenre("Không biêt");
        book.setPublisher("Nhà xuất bản không biết");
        book.setPublicationDate(new java.sql.Date(System.currentTimeMillis()));
        productService.addProduct(book);

        Stationery stationery = new Stationery();
        stationery.setName("Bút chì");
        stationery.setPrice(5000.0);
        stationery.setDescription("bút chì 2B");
        stationery.setQuantity(101L);
        stationery.setImage("def.jpg");
        stationery.setBrand("Thương hiệu bút chì");
        stationery.setType("Bút chì");
        productService.addProduct(stationery);

        Toy toy = new Toy();
        toy.setName("Búp bê");
        toy.setPrice(20000.0);
        toy.setDescription("Búp bê xinh xắn");
        toy.setQuantity(51L);
        toy.setImage("ghi.jpg");
        toy.setBrand("Thương hiệu búp bê");
        toy.setAgeGroup("Trẻ em trên 2 tuổi");
        productService.addProduct(toy);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       if (accountService.getAccount("admin") == null) {
           addAdmin();
           addFakeEmployees();
           addFakeProduct();
       }
    }
}