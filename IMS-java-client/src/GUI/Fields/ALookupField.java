package GUI.Fields;

import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class ALookupField extends Field {
	private ComboBox combo;
	public ALookupField(String label, ObservableList list) {
		super(label);
		combo = new ComboBox<>(list);
	}
	public void setFieldLocation(GridPane pane, int row){
		pane.add(new Label(getLabel()), 0, row);
		pane.add(combo, 1, row);
	}
	public Object getItem() {
		return combo.getSelectionModel().getSelectedItem();
	}
	public void setItem(Object obj) {
		combo.getSelectionModel().select(obj);
	}
	@Override
	public void setEnable(boolean enable) {
		combo.setDisable(!enable);
	}
}
