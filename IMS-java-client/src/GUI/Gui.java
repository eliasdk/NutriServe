package GUI;

import GUI.Tabs.CustomersTab;
import GUI.Tabs.EmployeesTab;
import GUI.Tabs.ItemsTab;
import GUI.Tabs.OrdersTab;
import GUI.Tabs.PaymentsTab;
import GUI.Tabs.ProductsTab;
import GUI.Tabs.ShipmentsTab;
import application.connection.GETConnection;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Gui extends Application {
	GETConnection t = new GETConnection();

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("SOA");
        Group root = new Group();
        Scene scene = new Scene(root, 800, 600);
        TabPane tabPane = new TabPane();
        BorderPane borderPane = new BorderPane();
        OrdersTab orderTab = new OrdersTab(t.getAllOrders());
        tabPane.getTabs().add(orderTab);
        ShipmentsTab shipmentTab = new ShipmentsTab(t.getAllShipments());
        tabPane.getTabs().add(shipmentTab);
        CustomersTab customerTab = new CustomersTab(t.getAllCustomers());
        tabPane.getTabs().add(customerTab);
        EmployeesTab employeeTab = new EmployeesTab(t.getAllEmployees());
        tabPane.getTabs().add(employeeTab);
        PaymentsTab paymentsTab = new PaymentsTab(t.getAllPayments());
        tabPane.getTabs().add(paymentsTab);
        ProductsTab productsTab = new ProductsTab(t.getAllProducts());
        tabPane.getTabs().add(productsTab);
        ItemsTab itemsTab = new ItemsTab(t.getAllItems());
        tabPane.getTabs().add(itemsTab);

        borderPane.prefHeightProperty().bind(scene.heightProperty());
        borderPane.prefWidthProperty().bind(scene.widthProperty());

        borderPane.setCenter(tabPane);
        root.getChildren().add(borderPane);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}