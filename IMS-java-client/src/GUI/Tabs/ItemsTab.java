package GUI.Tabs;

import com.sun.prism.impl.Disposer.Record;

import GUI.ADialog;
import GUI.Fields.AIntegerField;
import GUI.Fields.ALookupField;
import GUI.Fields.ATextField;
import application.connection.DeleteConnection;
import application.connection.GETConnection;
import application.entity.Entity;
import application.entity.Product;
import application.entity.PurchasedItemElement;
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

public class ItemsTab extends Tab {
	private ObservableList<PurchasedItemElement> data;
	private TableView<PurchasedItemElement> table;

	public ItemsTab(ObservableList<PurchasedItemElement> data) {
		super("Items");
		this.data = data;

		table = new TableView<>();
		table.getColumns().add(createColumn("Name", "name"));
		table.getColumns().add(createColumn("Description", "description"));
		table.getColumns().add(createColumn("Purchase Price", "purchaseprice"));
		table.getColumns().add(createColumn("Quantity", "quantity"));
		table.getColumns().add(createColumn("Rate", "rate"));
		table.getColumns().add(createColumn("Selling Price", "sellingprice"));
		table.getColumns().add(createColumn("Status", "status"));
		table.getColumns().add(createColumn("Unit", "unit"));
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
			TableRow<PurchasedItemElement> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
					new ItemDialog("View Item", row.getItem());
				}
			});
			return row;
		});
		table.setItems(this.data);

		Button addItems = new Button("Add Items");
		addItems.setId("glass-grey");
		addItems.setOnAction(e -> {
			new ItemDialog("Add Item");
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
		BorderPane root = new BorderPane(table, refresh, null, addItems, null);
		BorderPane.setAlignment(addItems, Pos.CENTER);
		BorderPane.setAlignment(refresh, Pos.CENTER);
		BorderPane.setMargin(addItems, new Insets(10));
		setContent(root);
	}

	private void refresh() {
		data.removeAll(data);
		data.addAll(new GETConnection().getAllItems());
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
					PurchasedItemElement item = table.getItems().get(getIndex());
					new DeleteConnection(item);
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

	class ItemDialog extends ADialog {
		ATextField fldType, fldUnit, fldId, fldRate, fldStatus, fldName, fldDescription;
		AIntegerField fldPurchaseprice, fldSellingprice, fldQuantity;
		ALookupField fldProduct;

		ItemDialog(String title) {
			this(title, null);
		}

		ItemDialog(String title, PurchasedItemElement item) {
			super(title, item == null ? false : true);

			fldQuantity = new AIntegerField("Quantity");
			fldProduct = new ALookupField("Product", new GETConnection().getAllProducts());
			fldType = new ATextField("Type");
			fldPurchaseprice = new AIntegerField("Purchase Price");
			fldSellingprice = new AIntegerField("Selling Price");
			fldUnit = new ATextField("Unit");
			fldId = new ATextField("ID");
			fldRate = new ATextField("Rate");
			fldStatus = new ATextField("Status");
			fldName = new ATextField("Name");
			fldDescription = new ATextField("Description");

			if (item != null) {
				fillField(item);
			}
			addAll(fldQuantity, fldProduct, fldType, fldPurchaseprice, fldSellingprice, fldUnit, fldId, fldRate,
					fldStatus, fldName, fldDescription);
			launch();

		}

		@Override
		public Entity getEntity() {
			return new PurchasedItemElement(fldQuantity.getInt(), (Product) fldProduct.getItem(), fldType.getText(),
					fldPurchaseprice.getInt(), fldSellingprice.getInt(), fldUnit.getText(), fldId.getText(),
					fldRate.getText(), fldStatus.getText(), fldName.getText(), fldDescription.getText());
		}

		@Override
		public void fillField(Object obj) {
			PurchasedItemElement item = (PurchasedItemElement) obj;
			fldQuantity.setInt(item.getQuantity());
			fldProduct.setItem(item.getProduct());
			fldType.setText(item.getType());
			fldPurchaseprice.setInt(item.getPurchaseprice());
			fldSellingprice.setInt(item.getSellingprice());
			fldUnit.setText(item.getUnit());
			fldId.setText(item.getId());
			fldRate.setText(item.getRate());
			fldStatus.setText(item.getStatus());
			fldName.setText(item.getName());
		}
	}
}
