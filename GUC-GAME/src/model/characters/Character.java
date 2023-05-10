package model.characters;

import java.awt.Point;
import java.util.HashSet;

import engine.Game;
import exceptions.GameActionException;
import exceptions.InvalidTargetException;
import exceptions.MovementException;
import model.collectibles.Supply;
import model.collectibles.Vaccine;
import model.world.Cell;
import model.world.CharacterCell;
import model.world.CollectibleCell;
import model.world.TrapCell;



public abstract class Character {
	private String name;
	private Point location;
	private int maxHp;
	private int currentHp;
	private int attackDmg;
	private boolean isHero;
	private Character target;


	public Character() {
	}

	public Character(String name, int maxHp, int attackDmg) {
		this.name=name;
		this.maxHp = maxHp;
		this.currentHp = maxHp;
		this.attackDmg = attackDmg;
		this.isHero = true;
	}

	public Character getTarget() {
		return target;
	}

	public void setTarget(Character target) {
		this.target = target;
	}

	public String getName() {
		return name;
	}

	public Point getLocation() {
		return location;
	}

	public void changeResources(Cell newCell) {
		if (newCell instanceof TrapCell){
			this.setCurrentHp(this.getCurrentHp() - ((TrapCell) newCell).getTrapDamage());
			this.onCharacterDeath();
		}
		else if (newCell instanceof CollectibleCell  && this.isHero()) {
			var collectibleCell = (CollectibleCell) newCell;
			if (((CollectibleCell) newCell).getCollectible() instanceof Supply)
				((Hero)this).addSupply((Supply)collectibleCell.getCollectible());
			else
				((Hero)this).addVaccine((Vaccine)collectibleCell.getCollectible());
		}
	}

	public void setLocation(Point loc)  {
		this.location = loc;
	}

	public int getMaxHp() {
		return maxHp;
	}

	public int getCurrentHp() {
		return currentHp;
	}

	public void setCurrentHp(int currentHp) {
		if(currentHp < 0)
			this.currentHp = 0;
		else if(currentHp > maxHp)
			this.currentHp = maxHp;
		else
			this.currentHp = currentHp;
	}

	public int getAttackDmg() {
		return attackDmg;
	}

	public boolean isHero() {
		return isHero;
	}

	public void setHero(boolean isHero) {
		this.isHero = isHero;
	}

	public boolean isAdjacent() {
		return Math.abs(this.getLocation().getX() + this.getLocation().getY() - this.getTarget().getLocation().getX()
				- this.getTarget().getLocation().getY()) <= 2;
	}

	public void attack() throws GameActionException {
		if (!this.isAdjacent() || (this.target.isHero() && this.isHero()) || (!this.target.isHero() && !this.isHero()))
			throw new InvalidTargetException();
		this.target.setCurrentHp(this.target.getCurrentHp() - this.attackDmg);
		this.getTarget().defend(this);
		this.target.onCharacterDeath();

	}

	public void defend(Character c) {
		c.setCurrentHp(c.getCurrentHp() - (this.getAttackDmg()/2));
	}

	public void onCharacterDeath() {
		if (this.currentHp != 0)
			return;
		Game.heroes.remove(this);
		var cell = Game.map[(int) this.getLocation().getX()][(int) this.getLocation().getY()];
		((CharacterCell)cell).setCharacter(null);
	}
}
