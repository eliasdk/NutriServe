package GUI.Fields;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public abstract class Field {
	private String label;

	public Field(String label) {
		this.label = label;
	}
	public String getLabel() {
		return label;
	}
	public abstract void setFieldLocation(GridPane pane, int row);
	public abstract void setEnable(boolean enable);
}
