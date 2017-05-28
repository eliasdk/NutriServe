package GUI.Fields;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class ATextField extends Field {
	private TextField text;
	public ATextField(String label) {
		super(label);
		text = new TextField();
	}
	public void setFieldLocation(GridPane pane, int row){
		pane.add(new Label(getLabel()), 0, row);
		pane.add(text, 1, row);
	}
	public String getText() {
		return text.getText();
	}
	public void setText(String text) {
		this.text.setText(text);
	}
	@Override
	public void setEnable(boolean enable) {
		text.setDisable(!enable);
	}
}
