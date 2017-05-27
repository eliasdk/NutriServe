package GUI.Fields;

import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class ABooleanField extends Field {
	private CheckBox bool;

	public ABooleanField(String label) {
		super(label);
		bool = new CheckBox();
	}

	public void setFieldLocation(GridPane pane, int row) {
		pane.add(new Label(getLabel()), 0, row);
		pane.add(bool, 1, row);
	}

	public boolean getBoolean() {
		return bool.isSelected();
	}

	public void setBoolean(boolean bool) {
		this.bool.setSelected(bool);
	}
	@Override
	public void setEnable(boolean enable) {
		bool.setDisable(!enable);
	}
}
