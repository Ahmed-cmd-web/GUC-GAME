package model.world;

public class TrapCell extends Cell {

	private int trapDamage;

	public TrapCell() {
		trapDamage = ((int) (Math.random() * 3 + 1)) * 10;
		this.setCellImage("trap.jpeg");

	}

	public int getTrapDamage() {
		return trapDamage;
	}

}
