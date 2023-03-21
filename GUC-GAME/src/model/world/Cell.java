package model.world;

//A class representing Cells in the game.

public class Cell {

	private boolean isVisible;
	//Boolean representing if the cell is visible or not, READ & WRITE.
	
	public boolean isVisible() {
		return isVisible;
	}

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}
	
	
	public Cell() {
		
	}

}
