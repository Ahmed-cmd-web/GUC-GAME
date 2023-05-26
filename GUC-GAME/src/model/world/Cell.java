package model.world;


public abstract class Cell {

	private boolean isVisible;
	private String cellImage;

	public String getCellImage() {
		return cellImage;
	}

	public void setCellImage(String cellImage) {
		this.cellImage = cellImage;
	}

	public Cell() {
		this.setCellImage(null);
	}

	public boolean isVisible() {
		return isVisible;
	}

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

}
