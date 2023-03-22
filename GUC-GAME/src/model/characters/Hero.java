package model.characters;
import model.collectibles.*;
import java.util.*;

//class representing Heros in the game. This class is a subclass of the Character class.

public abstract class Hero extends Character {

	private int actionsAvailable;
	//int representing the number of the actions available for each hero in a turn, READ & WRITE.

	private int maxActions;
	//int representing the maximum number of actions a hero can make in a turn, READ ONLY.

	private boolean specialAction;
	//boolean represents if the hero has used his special action, READ & WRITE.

	ArrayList<Vaccine> vaccineInventory;
	//A list representing the vaccines collected by each hero, READ ONLY.

	ArrayList<Supply> supplyInventory;
	//A list representing the supplies collected by each hero, READ ONLY.

	public int getActionsAvailable() {
		return actionsAvailable;
	}

	public void setActionsAvailable(int actionsAvailable) {
		this.actionsAvailable = actionsAvailable;
	}

	public int getMaxActions() {
		return maxActions;
	}

	public void setMaxActions(int maxActions) {
		this.maxActions = maxActions;
	}

	public boolean isSpecialAction() {
		return specialAction;
	}

	public void setSpecialAction(boolean specialAction) {
		this.specialAction = specialAction;
	}

	public ArrayList<Vaccine> getVaccineInventory() {
		return vaccineInventory;
	}

	public void setVaccineInventory(ArrayList<Vaccine> vaccineInventory) {
		this.vaccineInventory = vaccineInventory;
	}

	public ArrayList<Supply> getSupplyInventory() {
		return supplyInventory;
	}

	public void setSupplyInventory(ArrayList<Supply> supplyInventory) {
		this.supplyInventory = supplyInventory;
	}

	public Hero(String name, int maxHp, int attackDmg, int maxActions) {
		super(name, maxHp, attackDmg);
		this.maxActions=maxActions;
		this.actionsAvailable = maxActions;
		this.vaccineInventory = new ArrayList<>();
		this.supplyInventory = new ArrayList<>();
		setCurrentHp(maxHp);
	}

}
