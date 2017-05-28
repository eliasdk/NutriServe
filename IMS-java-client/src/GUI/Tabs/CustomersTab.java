package GUI.Tabs;

import com.sun.prism.impl.Disposer.Record;

import GUI.ADialog;
import GUI.Fields.AIntegerField;
import GUI.Fields.ALookupField;
import GUI.Fields.ATextField;
import application.connection.DeleteConnection;
import application.connection.GETConnection;
import application.entity.Customer;
import application.entity.Entity;
import application.entity.Payment;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;

public class CustomersTab extends Tab {
	private ObservableList<Customer> data;
	private TableView<Customer> table;

	public CustomersTab(ObservableList<Customer> data) {
		super("Customers");
		this.data = data;
		table = new TableView<>();
		table.getColumns().add(createColumn("Account Balance", "accountbalance"));
		table.getColumns().add(createColumn("Currency", "currency"));
		table.getColumns().add(createColumn("First Name", "customerfirstname"));
		table.getColumns().add(createColumn("Last Name", "customerlastname"));
		table.getColumns().add(createColumn("Email", "email"));
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
			TableRow<Customer> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
					new CustomerDialog("View Customer", row.getItem());
				}
			});
			return row;
		});
		table.setItems(this.data);

		Button addCustomer = new Button("Add Customer");
		addCustomer.setId("glass-grey");
		addCustomer.setOnAction(e -> {
			CustomerDialog dialog = new CustomerDialog("Add Customer");
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
		BorderPane root = new BorderPane(table, refresh, null, addCustomer, null);
		BorderPane.setAlignment(addCustomer, Pos.CENTER);
		BorderPane.setAlignment(refresh, Pos.CENTER);
		BorderPane.setMargin(addCustomer, new Insets(10));
		setContent(root);
	}

	private void refresh() {
		data.removeAll(data);
		data.addAll(new GETConnection().getAllCustomers());
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
					Customer customer = table.getItems().get(getIndex());
					new DeleteConnection(customer);
					//data.remove(customer);
					// ((Button)t.getSource()).setVisible(false);
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

	class CustomerDialog extends ADialog {
		ATextField fldType, fldCurrency, fldCustomerbirthdate, fldCustomerlastname, fldCustomerfirstname, fldId,
				fldEmail;;
		ALookupField fldPaymentinfo;
		AIntegerField fldAccountbalance;

		CustomerDialog(String title) {
			this(title, null);
		}

		CustomerDialog(String title, Customer customer) {
			super(title, customer == null ? false : true);

			fldType = new ATextField("Type");
			fldCurrency = new ATextField("Currency");
			fldCustomerbirthdate = new ATextField("Customer Birth Date");
			fldCustomerlastname = new ATextField("Last Name");
			fldCustomerfirstname = new ATextField("First Name");
			fldPaymentinfo = new ALookupField("Payment", new GETConnection().getAllPayments());
			fldId = new ATextField("ID");
			fldAccountbalance = new AIntegerField("Account Balance");
			fldEmail = new ATextField("Email");
			if (customer != null) {
				fillField(customer);
			}
			addAll(fldType, fldCurrency, fldCustomerbirthdate, fldCustomerlastname, fldCustomerfirstname, fldId,
					fldAccountbalance, fldEmail, fldPaymentinfo);
			launch();
		}

		@Override
		public Entity getEntity() {
			return new Customer(fldType.getText(), fldCurrency.getText(), fldCustomerbirthdate.getText(),
					fldCustomerlastname.getText(), fldCustomerfirstname.getText(), (Payment) fldPaymentinfo.getItem(),
					fldId.getText(), fldAccountbalance.getInt(), fldEmail.getText());
		}

		@Override
		public void fillField(Object obj) {
			Customer cust = (Customer) obj;
			fldType.setText(cust.getType());
			fldCurrency.setText(cust.getCurrency());
			fldCustomerbirthdate.setText(cust.getCustomerbirthdate());
			fldCustomerlastname.setText(cust.getCustomerlastname());
			fldCustomerfirstname.setText(cust.getCustomerfirstname());
			fldPaymentinfo.setItem(cust.getPaymentinfo());
			fldId.setText(cust.getId());
			fldAccountbalance.setInt(cust.getAccountbalance());
			fldEmail.setText(cust.getEmail());
		}
	}

}
