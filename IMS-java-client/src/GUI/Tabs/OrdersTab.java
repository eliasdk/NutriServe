package GUI.Tabs;

import com.sun.prism.impl.Disposer.Record;

import GUI.ADialog;
import GUI.Fields.ABooleanField;
import GUI.Fields.AIntegerField;
import GUI.Fields.ALookupField;
import GUI.Fields.ATextField;
import application.connection.DeleteConnection;
import application.connection.GETConnection;
import application.entity.Customer;
import application.entity.Entity;
import application.entity.Order;
import application.entity.Payment;
import application.entity.PurchasedItemElement;
import application.entity.Shipment;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;

public class OrdersTab extends Tab {
	private ObservableList<Order> data;
	private TableView<Order> table;

	public OrdersTab(ObservableList<Order> data) {
		super("Orders");
		this.data = data;
		table = new TableView<>();
		TableColumn<Order, Boolean> addToStockCol = createColumn("Add To Stock", "addedtostock");
		addToStockCol.setCellFactory(col -> {
			CheckBoxTableCell<Order, Boolean> cell = new CheckBoxTableCell<>(index -> {
				BooleanProperty active = new SimpleBooleanProperty(table.getItems().get(index).getAddedtostock());
				return active;
			});
			cell.setEditable(false);
			return cell;
		});
		table.getColumns().add(addToStockCol);
		table.getColumns().add(createColumn("Date Recieved", "datereceived"));
		table.getColumns().add(createColumn("Date Placed", "dateplaced"));
		table.getColumns().add(createColumn("Number", "number"));
		TableColumn<Order, Boolean> outstandingCol = createColumn("Outstanding", "outstanding");
		outstandingCol.setCellFactory(col -> {
			CheckBoxTableCell<Order, Boolean> cll = new CheckBoxTableCell<>(index -> {
				BooleanProperty active = new SimpleBooleanProperty(table.getItems().get(index).getOutstanding());
				return active;
			});
			cll.setEditable(false);
			return cll;
		});
		table.getColumns().add(outstandingCol);
		table.getColumns().add(createColumn("Quantity", "quantity"));
		TableColumn col_action = new TableColumn<>("Delete");
		col_action.setSortable(false);
		col_action.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Record, Boolean>, ObservableValue<Boolean>>() {
					@Override
					public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Record, Boolean> p) {
						return new SimpleBooleanProperty(p.getValue() != null);
					}
				});
		col_action.setCellFactory(new Callback<TableColumn<Record, Boolean>, TableCell<Record, Boolean>>() {
			@Override
			public TableCell<Record, Boolean> call(TableColumn<Record, Boolean> p) {
				return new ButtonCell();
			}

		});
		table.getColumns().add(col_action);
		table.setRowFactory(tv -> {
			TableRow<Order> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
					new OrderDialog("View Order", row.getItem());
				}
			});
			return row;
		});
		table.setItems(this.data);

		Button addOreder = new Button("Add Order");
		addOreder.setId("glass-grey");
		addOreder.setOnAction(e -> {
			new OrderDialog("Add Order");
			refresh();
		});
		ImageView image = new ImageView(new Image(getClass().getResourceAsStream("/image/refresh.png")));
		image.setFitHeight(20);
		image.setFitWidth(20);
		Button refresh = new Button("Refresh", image);
		refresh.setId("glass-grey");
		refresh.setAlignment(Pos.CENTER);
		refresh.setOnAction(e -> {
			refresh();
		});
		BorderPane root = new BorderPane(table, refresh, null, addOreder, null);
		BorderPane.setAlignment(addOreder, Pos.CENTER);
		BorderPane.setAlignment(refresh, Pos.CENTER);
		BorderPane.setMargin(addOreder, new Insets(10));
		setContent(root);
	}

	private void refresh() {
		data.removeAll(data);
		data.addAll(new GETConnection().getAllOrders());
	}

	private <S, T> TableColumn<S, T> createColumn(String title, String propertyName) {
		TableColumn<S, T> col = new TableColumn<>(title);
		col.setCellValueFactory(new PropertyValueFactory<>(propertyName));
		return col;
	}

	private class ButtonCell extends TableCell<Record, Boolean> {
		final Button cellButton = new Button("Delete");

		ButtonCell() {
			cellButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent t) {
					Order order = table.getItems().get(getIndex());
					new DeleteConnection(order);
					refresh();
				}
			});
		}

		@Override
		protected void updateItem(Boolean t, boolean empty) {
			super.updateItem(t, empty);
			if (!empty) {
				setGraphic(cellButton);
			} else {
				setGraphic(null);
			}
		}
	}

	class OrderDialog extends ADialog {
		ATextField fldId, fldDatereceived, fldNumber, fldType, fldDateplaced;
		ALookupField fldPayment, fldCustomer, fldShipment, fldPurchaseditems;
		AIntegerField fldTotal, fldQuantity;
		ABooleanField fldAddedtostock, fldOutstanding;

		OrderDialog(String title) {
			this(title, null);
		}

		OrderDialog(String title, Order order) {
			super(title, order == null ? false : true);
			GETConnection con = new GETConnection();
			fldId = new ATextField("ID");
			fldDatereceived = new ATextField("Dat Recieved");
			fldNumber = new ATextField("Number");
			fldType = new ATextField("Type");
			fldTotal = new AIntegerField("Total");
			fldShipment = new ALookupField("Shipmment", con.getAllShipments());
			fldPayment = new ALookupField("Payment", con.getAllPayments());
			fldCustomer = new ALookupField("Customer", con.getAllCustomers());
			fldAddedtostock = new ABooleanField("Added to Stock");
			fldDateplaced = new ATextField("Date Placed");
			fldPurchaseditems = new ALookupField("Items", con.getAllItems());
			fldQuantity = new AIntegerField("Quantity");
			fldOutstanding = new ABooleanField("Out Standing");

			if (order != null) {
				fillField(order);
			}
			addAll(fldId, fldDatereceived, fldNumber, fldType, fldTotal, fldShipment, fldPayment, fldCustomer,
					fldAddedtostock, fldDateplaced, fldPurchaseditems, fldQuantity, fldOutstanding);
			launch();
		}

		@Override
		public Entity getEntity() {
			return new Order(fldId.getText(), fldDatereceived.getText(), fldNumber.getText(), fldType.getText(),
					fldTotal.getInt(), (Shipment) fldShipment.getItem(), (Payment) fldPayment.getItem(),
					(Customer) fldCustomer.getItem(), fldAddedtostock.getBoolean(), fldDateplaced.getText(),
					(PurchasedItemElement) fldPurchaseditems.getItem(), fldQuantity.getInt(),
					fldOutstanding.getBoolean());
		}

		@Override
		public void fillField(Object obj) {
			Order order = (Order) obj;
			fldId.setText(order.getId());
			fldDatereceived.setText(order.getId());
			fldNumber.setText(order.getId());
			fldType.setText(order.getId());
			fldTotal.setInt(order.getTotal());
			fldShipment.setItem(order.getShipment());
			fldPayment.setItem(order.getPurchaseditems());
			fldCustomer.setItem(order.getCustomer());
			fldAddedtostock.setBoolean(order.getAddedtostock());
			fldDateplaced.setText(order.getId());
			fldPurchaseditems.setItem(order.getPurchaseditems());
			fldQuantity.setInt(order.getQuantity());
			fldOutstanding.setBoolean(order.getOutstanding());
		}
	}
}
