package model.world;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JTextPane;

public abstract class Cell  {


	private boolean isVisible;

	public Cell() {
		// setSize(45, 45);
		// setBorder(BorderFactory.createLineBorder(Color.black, 1));
		isVisible = false;
	}

	public boolean isVisible() {
		return isVisible;
	}

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}


}
