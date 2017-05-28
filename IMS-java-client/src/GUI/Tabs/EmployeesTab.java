package GUI.Tabs;

import com.sun.prism.impl.Disposer.Record;

import GUI.ADialog;
import GUI.Fields.ATextField;
import application.connection.DeleteConnection;
import application.connection.GETConnection;
import application.entity.Customer;
import application.entity.Employee;
import application.entity.Entity;
import application.entity.Owner;
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

public class EmployeesTab extends Tab {
	private ObservableList<Employee> data;
	private TableView<Employee> table;

	public EmployeesTab(ObservableList<Employee> data) {
		super("Employees");
		this.data = data;

		table = new TableView<>();
		table.getColumns().add(createColumn("Name", "name"));
		table.getColumns().add(createColumn("Company Name", "companyname"));
		table.getColumns().add(createColumn("Address", "address"));
		table.getColumns().add(createColumn("Email", "email"));
		table.getColumns().add(createColumn("Phone", "phone"));
		table.getColumns().add(createColumn("Notes", "notes"));
		table.getColumns().add(createColumn("WebSite", "website"));
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
			TableRow<Employee> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
					new EmployeeDialog("View Employee", row.getItem());
				}
			});
			return row;
		});
		table.setItems(this.data);

		Button addEmployee = new Button("Add Employee");
		addEmployee.setId("glass-grey");
		addEmployee.setOnAction(e -> {
			new EmployeeDialog("Add Employee");
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
		BorderPane root = new BorderPane(table, refresh, null, addEmployee, null);
		BorderPane.setAlignment(addEmployee, Pos.CENTER);
		BorderPane.setAlignment(refresh, Pos.CENTER);
		BorderPane.setMargin(addEmployee, new Insets(10));
		setContent(root);
	}

	private void refresh() {
		data.removeAll(data);
		data.addAll(new GETConnection().getAllEmployees());
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
					Employee employee = table.getItems().get(getIndex());
					new DeleteConnection(employee);
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

	class EmployeeDialog extends ADialog {
		ATextField fldCompanyname, fldNotes, fldType, fldId, fldPhone, fldAddress, fldEmail, fldWebsite, FldName;

		EmployeeDialog(String title) {
			this(title, null);
		}

		EmployeeDialog(String title, Employee employee) {
			super(title, employee == null ? false : true);
			System.out.println(employee);
			fldCompanyname = new ATextField("Company Name");
			fldNotes = new ATextField("Notes");
			fldType = new ATextField("Type");
			fldId = new ATextField("ID");
			fldPhone = new ATextField("Phone");
			fldAddress = new ATextField("Address");
			fldEmail = new ATextField("Email");
			fldWebsite = new ATextField("Website");
			FldName = new ATextField("Name");
			System.out.println(employee);
			if (employee != null) {
				fillField(employee);
			}
			addAll(fldId, FldName, fldCompanyname, fldType, fldAddress, fldPhone, fldEmail, fldWebsite, fldNotes);
			launch();

		}

		@Override
		public Entity getEntity() {
			return new Employee(fldCompanyname.getText(), fldNotes.getText(), fldType.getText(), fldId.getText(),
					fldPhone.getText(), fldAddress.getText(), fldEmail.getText(), fldWebsite.getText(),
					FldName.getText());
		}

		@Override
		public void fillField(Object obj) {
			Employee emp = (Employee) obj;
			fldCompanyname.setText(emp.getCompanyname());
			fldNotes.setText(emp.getNotes());
			fldType.setText(emp.getType());
			fldId.setText(emp.getId());
			fldPhone.setText(emp.getPhone());
			fldAddress.setText(emp.getAddress());
			fldEmail.setText(emp.getEmail());
			fldWebsite.setText(emp.getWebsite());
			FldName.setText(emp.getName());
		}
	}
}
