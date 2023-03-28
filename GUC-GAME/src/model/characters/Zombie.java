package model.characters;

//A class representing Zombies that are in the game.

public class  Zombie extends Character{

	static int ZOMBIES_COUNT;
	private int speed;

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public Zombie() {
		super("Zombie " + ++ZOMBIES_COUNT, 40, 10);
	}

	public Zombie(int speed) {
		super("Zombie " + ++ZOMBIES_COUNT, 40, 10);
		this.speed = speed;
	}

}
