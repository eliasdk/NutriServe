package GUI.Tabs;

import com.sun.prism.impl.Disposer.Record;

import GUI.ADialog;
import GUI.Fields.ATextField;
import application.connection.DeleteConnection;
import application.connection.GETConnection;
import application.entity.Entity;
import application.entity.Product;
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

public class ProductsTab extends Tab {
	private ObservableList<Product> data;
	private TableView<Product> table;

	public ProductsTab(ObservableList<Product> data) {
		super("Products");
		this.data = data;

		table = new TableView<>();
		table.getColumns().add(createColumn("Category", "category"));
		table.getColumns().add(createColumn("Guaratnee Info", "guaranteeinfo"));
		table.getColumns().add(createColumn("Supplier", "supplier"));

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
			TableRow<Product> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
					new ProductDialog("Add Product", row.getItem());
				}
			});
			return row;
		});
		table.setItems(this.data);

		Button addProduct = new Button("Add Product");
		addProduct.setId("glass-grey");
		addProduct.setOnAction(e -> {
			new ProductDialog("Add Product");
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
		BorderPane root = new BorderPane(table, refresh, null, addProduct, null);
		BorderPane.setAlignment(addProduct, Pos.CENTER);
		BorderPane.setAlignment(refresh, Pos.CENTER);
		BorderPane.setMargin(addProduct, new Insets(10));
		setContent(root);
	}

	private void refresh() {
		data.removeAll(data);
		data.addAll(new GETConnection().getAllProducts());
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
					Product product = table.getItems().get(getIndex());
					new DeleteConnection(product);
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

	class ProductDialog extends ADialog {
		ATextField fldType, fldSupplier, fldId, fldCategory, fldGuaranteeinfo;

		ProductDialog(String title) {
			this(title, null);
		}

		ProductDialog(String title, Product product) {
			super(title, product == null ? false : true);
			GETConnection con = new GETConnection();

			fldType = new ATextField("Card");
			fldSupplier = new ATextField("Supplier");
			fldId = new ATextField("ID");
			fldCategory = new ATextField("Category");
			fldGuaranteeinfo = new ATextField("Guarantee Info.");

			if (product != null) {
				fillField(product);
			}
			addAll(fldType, fldSupplier, fldId, fldCategory, fldGuaranteeinfo);
			launch();
		}

		@Override
		public Entity getEntity() {
			return new Product(fldType.getText(), fldSupplier.getText(), fldId.getText(), fldCategory.getText(),
					fldGuaranteeinfo.getText());
		}

		@Override
		public void fillField(Object obj) {
			Product product = (Product) obj;
			fldType.setText(product.getType());
			fldSupplier.setText(product.getSupplier());
			fldId.setText(product.getId());
			fldCategory.setText(product.getCategory());
			fldGuaranteeinfo.setText(product.getGuaranteeinfo());
		}
	}
}
