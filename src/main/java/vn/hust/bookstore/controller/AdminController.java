package vn.hust.bookstore.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import vn.hust.bookstore.entity.*;
import vn.hust.bookstore.service.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.Map;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;

public class AdminController implements Initializable {

    private OrderService orderService = new OrderService();
    private BatchRecordService batchRecordService = new BatchRecordService();
    private SalaryHistoryService salaryHistoryService = new SalaryHistoryService();

    private Admin admin;
    private Parent root;
    private double x, y;

    @FXML
    private BarChart<String, Number> barChartProfit;

    @FXML
    private Button btnAddEmployee;

    @FXML
    private Button btnClose;

    @FXML
    private Button btnEmployeeList;

    @FXML
    private Button btnMinimize;

    @FXML
    private Button btnSalaryHistory;

    @FXML
    private AnchorPane contentPane;

    @FXML
    private CategoryAxis costXAxis;

    @FXML
    private NumberAxis costYAxis;

    @FXML
    private Label lblTitle;

    @FXML
    private LineChart<String, Number> lineChartCosts;

    @FXML
    private AnchorPane mainPane;

    @FXML
    private PieChart pieChartRevenue;

    @FXML
    private CategoryAxis profitXAxis;

    @FXML
    private NumberAxis profitYAxis;

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public void minimize() {
        Stage stage = (Stage) mainPane.getScene().getWindow();
        stage.setIconified(true);
    }

    public void close() {
        System.exit(0);
    }

    private void addSalaryHistory(YearMonth currentMonth) {
        List<Employee> employees = new EmployeeService().getAllEmployees();
        for (Employee employee : employees) {
            SalaryHistory salaryHistory = new SalaryHistory();
            salaryHistory.setDate(null);
            salaryHistory.setPaid(false);
            salaryHistory.setEmployee(employee);
            salaryHistory.setHourlyWage(employee.getHourlyWage());
            salaryHistory.setLeaveHours(employee.getLeaveHours());
            salaryHistory.setWorkingHours(employee.getWorkingHours());
            salaryHistory.setMonth((long) currentMonth.getMonthValue());
            salaryHistory.setSalary(employee.getHourlyWage() * employee.getWorkingHours());
            salaryHistoryService.addSalaryHistory(salaryHistory);
        }
    }

    private void checkAndAddSalaryHistory() {
        LocalDate today = LocalDate.now();
        YearMonth currentMonth = YearMonth.from(today);
        LocalDate lastDayOfMonth = currentMonth.atEndOfMonth();
        if (today.isEqual(lastDayOfMonth)) {
            addSalaryHistory(currentMonth);
        }
    }

    private void updateCharts() {
        List<Order> orders = orderService.getAllOrders();
        List<BatchRecord> batchRecords = batchRecordService.getAllBatchRecords();
        List<SalaryHistory> salaryHistories = salaryHistoryService.getAllSalaryHistories();
        updatePieChart(orders);
        updateBarChart(orders, batchRecords);
        updateLineChart(batchRecords, salaryHistories);
    }

    private void updatePieChart(List<Order> orders) {
        Map<String, Double> revenueByType = new HashMap<>();
        revenueByType.put("Book", 0.0);
        revenueByType.put("Stationery", 0.0);
        revenueByType.put("Toy", 0.0);

        for (Order order : orders) {
            for (Product product : order.getItems()) {
                double productRevenue = product.getPrice() * product.getQuantity();
                switch (product) {
                    case Book book -> revenueByType.put("Book", revenueByType.get("Book") + productRevenue);
                    case Stationery stationery -> revenueByType.put("Stationery", revenueByType.get("Stationery") + productRevenue);
                    case Toy toy -> revenueByType.put("Toy", revenueByType.get("Toy") + productRevenue);
                    default -> {
                    }
                }
            }
        }

        pieChartRevenue.getData().clear();
        revenueByType.forEach((type, revenue) -> {
            PieChart.Data slice = new PieChart.Data(type, revenue);
            pieChartRevenue.getData().add(slice);
        });
    }

    private void updateBarChart(List<Order> orders, List<BatchRecord> batchRecords) {
        Map<Long, Double> costByProductId = new HashMap<>();
        for (BatchRecord batch : batchRecords) {
            costByProductId.put(batch.getProduct().getId(),
                    costByProductId.getOrDefault(batch.getProduct().getId(), 0.0) + batch.getInPrice());
        }

        Map<Month, Double> profitByMonth = new HashMap<>();
        for (Order order : orders) {
            double totalProfit = 0.0;
            Month orderMonth = order.getOrderDate().toLocalDate().getMonth();
            for (Product product : order.getItems()) {
                double productCost = costByProductId.getOrDefault(product.getId(), 0.0);
                double productProfit = product.getPrice() - productCost;
                totalProfit += productProfit;
            }
            profitByMonth.put(orderMonth, profitByMonth.getOrDefault(orderMonth, 0.0) + totalProfit);
        }

        XYChart.Series<String, Number> profitSeries = new XYChart.Series<>();
        profitSeries.setName("Profit");
        profitByMonth.forEach((month, profit) -> {
            profitSeries.getData().add(new XYChart.Data<>(month.name(), profit));
        });

        barChartProfit.getData().clear();
        barChartProfit.getData().add(profitSeries);
    }

    private void updateLineChart(List<BatchRecord> batchRecords, List<SalaryHistory> salaryHistories) {
        Map<Month, Double> costsByMonth = new HashMap<>();
        for (BatchRecord batch : batchRecords) {
            Month month = batch.getInDate().toLocalDate().getMonth();
            costsByMonth.put(month, costsByMonth.getOrDefault(month, 0.0) + batch.getInPrice());
        }
        for (SalaryHistory salaryHistory : salaryHistories) {
            Month month = Month.of(salaryHistory.getMonth().intValue());
            costsByMonth.put(month, costsByMonth.getOrDefault(month, 0.0) + salaryHistory.getSalary());
        }

        XYChart.Series<String, Number> costSeries = new XYChart.Series<>();
        costSeries.setName("Costs");
        costsByMonth.forEach((month, cost) -> {
            costSeries.getData().add(new XYChart.Data<>(month.name(), cost));
        });

        lineChartCosts.getData().clear();
        lineChartCosts.getData().add(costSeries);
    }

    @FXML
    void showSalaryHistory() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vn/hust/bookstore/view/SalaryHistory.fxml"));
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

        SalaryHistoryController salaryHistoryController = fxmlLoader.getController();
        salaryHistoryController.setAdmin(this.admin);

        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void showEmployees() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vn/hust/bookstore/view/EmployeeList.fxml"));
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

        EmployeeListController employeeListController = fxmlLoader.getController();
        employeeListController.setAdmin(this.admin);

        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void addEmployee() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vn/hust/bookstore/view/addemployee.fxml"));
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

        AddEmployeeController addEmployeeController = fxmlLoader.getController();
        addEmployeeController.setAdmin(this.admin);

        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        updateCharts();
        checkAndAddSalaryHistory();
    }
}
