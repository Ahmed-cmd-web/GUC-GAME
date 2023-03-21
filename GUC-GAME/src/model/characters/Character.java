package model.characters;
import java.awt.Point;

//A class representing the Characters available in the game.
//No objects of type Character can be instantiated.

public abstract class Character {

	private String name;
	//Name of the characters, READ ONLY.
	
	private Point location;
	//point representing the x, y coordinates of the character’s location.
	
	private int maxHp;
	//maximum hp of this character, READ ONLY.
	//This is the upper bound of character’s currentHP.
	
	private int currentHp;
	//integer representing the current hp of this character.
	
	private int attackDmg;
	//number represents the damage inflicted by the character on its target, READ ONLY.
	
	private Character target;
	//variable representing the target character that will be affected by any possible action done by the character.
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Point getLocation() {
		return location;
	}

	public void setLocation(Point location) {
		this.location = location;
	}

	public int getMaxHp() {
		return maxHp;
	}

	public void setMaxHp(int maxHp) {
		this.maxHp = maxHp;
	}

	public int getCurrentHp() {
		return currentHp;
	}

	public void setCurrentHp(int currentHp) {
		this.currentHp = currentHp;
	}

	public int getAttackDmg() {
		return attackDmg;
	}

	public void setAttackDmg(int attackDmg) {
		this.attackDmg = attackDmg;
	}

	public Character getTarget() {
		return target;
	}

	public void setTarget(Character target) {
		this.target = target;
	}

	
	public Character(String name, int maxHp, int attackDmg) {
		this.name=name;
		this.maxHp=maxHp;
		this.attackDmg=attackDmg;
	}
	
}
