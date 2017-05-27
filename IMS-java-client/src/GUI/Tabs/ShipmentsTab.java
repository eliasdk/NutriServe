package GUI.Tabs;


import com.sun.prism.impl.Disposer.Record;

import GUI.ADialog;
import GUI.Fields.ALookupField;
import GUI.Fields.ATextField;
import application.connection.DeleteConnection;
import application.connection.GETConnection;
import application.entity.Entity;
import application.entity.Owner;
import application.entity.Shipment;
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

public class ShipmentsTab extends Tab {
	private ObservableList<Shipment> data;
	private TableView<Shipment> table;
	public ShipmentsTab(ObservableList<Shipment> data) {
		super("Shipments");
		this.data = data;
		table = new TableView<>();
		table.getColumns().add(createColumn("Address", "address"));
		table.getColumns().add(createColumn("Ship Date", "shipdate"));
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
		    TableRow<Shipment> row = new TableRow<>();
		    row.setOnMouseClicked(event -> {
		        if (! row.isEmpty() && event.getButton()==MouseButton.PRIMARY
		             && event.getClickCount() == 2) {
		        	new ShipmentDialog("Add Shipment",row.getItem());
		        }
		    });
		    return row ;
		});
		table.setItems(this.data);

		Button addShipment = new Button("Add Shipment");
		addShipment.setId("glass-grey");
		addShipment.setOnAction(e ->{
			new ShipmentDialog("Add Shipment");
			refresh();
		});

		ImageView image = new ImageView(new Image(getClass().getResourceAsStream("/image/refresh.png")));
		image.setFitHeight(20);
		image.setFitWidth(20);
		Button refresh = new Button("Refresh", image);
		refresh.setId("glass-grey");
		refresh.setAlignment(Pos.CENTER);
		refresh.setOnAction(e ->{
			refresh();
		});

		BorderPane root = new BorderPane(table, refresh, null, addShipment, null);
		BorderPane.setAlignment(addShipment, Pos.CENTER);
		BorderPane.setAlignment(refresh, Pos.CENTER);
		BorderPane.setMargin(addShipment, new Insets(10));
		setContent(root);
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
					Shipment shipment = table.getItems().get( getIndex() );
					new DeleteConnection(shipment);
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

	class ShipmentDialog extends ADialog {
		ATextField fldShipdate, fldType, fldId, fldAddress;
		ALookupField  fldOwner;

		ShipmentDialog(String title) {
			this(title, null);
		}

		ShipmentDialog(String title, Shipment shipment) {
			super(title, shipment == null ? false : true);
			GETConnection con = new GETConnection();

			fldShipdate = new ATextField("Shipment Date");
			fldType = new ATextField("Type");
			fldOwner = new ALookupField("Owner", new GETConnection().getAllOwners());
			fldId = new ATextField("ID");
			fldAddress = new ATextField("Address");
			if (shipment != null) {
				fillField(shipment);
			}
			addAll(fldShipdate, fldType, fldOwner, fldId, fldAddress);
			launch();
		}

		@Override
		public Entity getEntity() {
			return new Shipment(fldShipdate.getText(), fldType.getText(), (Owner)fldOwner.getItem(),
					fldId.getText(), fldAddress.getText());
		}

		@Override
		public void fillField(Object obj) {
			Shipment shipment = (Shipment) obj;
			fldShipdate.setText(shipment.getShipdate());
			fldType.setText(shipment.getType());
			fldOwner.setItem(shipment.getOwner());
			fldId.setText(shipment.getId());
			fldAddress.setText(shipment.getAddress());

		}
	}
	private void refresh(){
		 data.removeAll(data);
		 data.addAll(new GETConnection().getAllShipments());
	}
}
