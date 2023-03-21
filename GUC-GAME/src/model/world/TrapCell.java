package model.world;
import java.util.Random;

public class TrapCell extends Cell{
	
	Random random = new Random();
	private int trapDamage;
	
	public int getTrapDamage () {
		return trapDamage;
	}

	public TrapCell() {
		super();
		this.trapDamage=10 * (random.nextInt(3)+1);
	}
}
