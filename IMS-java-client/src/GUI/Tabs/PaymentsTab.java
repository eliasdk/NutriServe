package GUI.Tabs;

import com.sun.prism.impl.Disposer.Record;

import GUI.ADialog;
import GUI.Fields.AIntegerField;
import GUI.Fields.ATextField;
import application.connection.DeleteConnection;
import application.connection.GETConnection;
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

public class PaymentsTab extends Tab {
	private ObservableList<Payment> data;
	private TableView<Payment> table;

	public PaymentsTab(ObservableList<Payment> data) {
		super("Payments");
		this.data = data;
		TableView<Payment> table = new TableView<>();
		table.getColumns().add(createColumn("Card", "card"));
		table.getColumns().add(createColumn("Create Time", "createtime"));
		table.getColumns().add(createColumn("Method", "method"));
		table.getColumns().add(createColumn("State", "state"));

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
			TableRow<Payment> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
					new PaymentDialog("View Payment", row.getItem());
				}
			});
			return row;
		});
		table.setItems(this.data);

		Button addPayment = new Button("Add Payment");
		addPayment.setId("glass-grey");
		addPayment.setOnAction(e -> {
			new PaymentDialog("Add Dialog");
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
		BorderPane root = new BorderPane(table, refresh, null, addPayment, null);
		BorderPane.setAlignment(addPayment, Pos.CENTER);
		BorderPane.setAlignment(refresh, Pos.CENTER);
		BorderPane.setMargin(addPayment, new Insets(10));
		setContent(root);
	}

	private void refresh() {
		data.removeAll(data);
		data.addAll(new GETConnection().getAllPayments());
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
					Payment payment = table.getItems().get(getIndex());
					new DeleteConnection(payment);
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

	class PaymentDialog extends ADialog {
		ATextField fldCard, fldMethod, fldType, fldCreatetime, fldId, fldUpdatetime;
		AIntegerField fldState;

		PaymentDialog(String title) {
			this(title, null);
		}

		PaymentDialog(String title, Payment payment) {
			super(title, payment == null ? false : true);
			GETConnection con = new GETConnection();
			fldCard = new ATextField("Card");
			fldState = new AIntegerField("State");
			fldMethod = new ATextField("Method");
			fldType = new ATextField("Type");
			fldCreatetime = new ATextField("Create Time");
			fldId = new ATextField("ID");
			fldUpdatetime = new ATextField("Update Time");

			if (payment != null) {
				fillField(payment);
			}
			addAll(fldCard, fldState, fldMethod, fldType, fldCreatetime, fldId, fldUpdatetime);
			launch();
		}

		@Override
		public Entity getEntity() {
			return new Payment(fldCard.getText(), fldState.getInt(), fldMethod.getText(), fldType.getText(),
					fldCreatetime.getText(), fldId.getText(), fldUpdatetime.getText());
		}

		@Override
		public void fillField(Object obj) {
			Payment payment = (Payment) obj;
			fldCard.setText(payment.getCard());
			fldState.setInt(payment.getState());
			fldMethod.setText(payment.getMethod());
			fldType.setText(payment.getType());
			fldCreatetime.setText(payment.getCreatetime());
			fldId.setText(payment.getId());
			fldUpdatetime.setText(payment.getUpdatetime());
		}
	}
}
