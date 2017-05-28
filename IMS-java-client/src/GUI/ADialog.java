package GUI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import GUI.Fields.Field;
import application.connection.PostConnection;
import application.entity.Entity;
import application.entity.Owner;
import javafx.scene.Node;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.Callback;

public abstract class ADialog extends Dialog {
	private ArrayList<Field> fields;
	private GridPane grid;
	private boolean isEditable;

	public ADialog(String title, boolean isEditable) {
		setTitle(title);
		fields = new ArrayList<>();
		grid = new GridPane();
		grid.setVgap(5);
		grid.setHgap(5);
		this.isEditable = isEditable;

	}

	protected void launch() {
		this.setResizable(false);

		this.getDialogPane().setContent(grid);
		if (!isEditable) {
			ButtonType buttonTypeOk = new ButtonType("Add", ButtonData.OK_DONE);
			this.getDialogPane().getButtonTypes().add(buttonTypeOk);
			this.setResultConverter(new Callback<ButtonType, Object>() {
				@Override
				public Object call(ButtonType b) {
					if (b == buttonTypeOk) {
						PostConnection postConnection = new PostConnection(getEntity());
					}
					return null;
				}
			});
		}
		ButtonType buttonTypeCancel = new ButtonType(isEditable ? "Close" : "Cancel", ButtonData.CANCEL_CLOSE);
		this.getDialogPane().getButtonTypes().add(buttonTypeCancel);
		Optional<Object> result = showAndWait();
	}

	public void add(Field field) {
		fields.add(field);
		field.setFieldLocation(grid, fields.size() - 1);
	}

	public void addAll(Field... fields) {
		this.fields.addAll(new ArrayList<Field>(Arrays.asList(fields)));
		for (int i = 0; i < this.fields.size(); i++) {
			Field field = this.fields.get(i);
			field.setEnable(!isEditable);
			this.fields.get(i).setFieldLocation(grid, i);
		}
	}

	public abstract Entity getEntity();

	public abstract void fillField(Object obj);
}
