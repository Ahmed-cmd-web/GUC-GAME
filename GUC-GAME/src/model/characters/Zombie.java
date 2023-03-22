package model.characters;

//A class representing Zombies that are in the game.

public class Zombie extends Character{

	static int ZOMBIES_COUNT;
	//An int representing the number of zombies created.

	public Zombie() {
		super("Zombie " + ++ZOMBIES_COUNT, 40, 10);
	}

}
