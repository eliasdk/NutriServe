package GUI.Fields;

import javax.swing.event.ChangeListener;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class AIntegerField extends Field {
	private TextField text;

	public AIntegerField(String label) {
		super(label);
		text = new TextField();
		text.textProperty().addListener((observable, oldValue, newValue) -> {
	        if (!newValue.matches("\\d*")) {
	        	text.setText(newValue.replaceAll("[^\\d]", ""));
	        }
	    });
	}

	public void setFieldLocation(GridPane pane, int row) {
		pane.add(new Label(getLabel()), 0, row);
		pane.add(text, 1, row);
	}

	public Integer getInt() {
		return Integer.parseInt(text.getText());
	}

	public void setInt(int integer) {
		this.text.setText(integer + "");
	}

	@Override
	public void setEnable(boolean enable) {
		text.setDisable(!enable);
	}

}
