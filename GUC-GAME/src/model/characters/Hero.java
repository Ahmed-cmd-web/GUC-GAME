package model.characters;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import engine.Game;
import exceptions.GameActionException;
import exceptions.InvalidTargetException;
import exceptions.MovementException;
import exceptions.NoAvailableResourcesException;
import exceptions.NotEnoughActionsException;
import model.collectibles.Supply;
import model.collectibles.Vaccine;
import model.world.Cell;
import model.world.CharacterCell;
import model.world.CollectibleCell;
import model.world.TrapCell;


public abstract class Hero extends Character {


		private int actionsAvailable;
		private int maxActions;
		private ArrayList<Vaccine> vaccineInventory;
		private ArrayList<Supply> supplyInventory;
		private boolean specialAction;



		public Hero(String name,int maxHp, int attackDmg, int maxActions) {
			super(name,maxHp, attackDmg);
			this.maxActions = maxActions;
			this.actionsAvailable = maxActions;
			this.vaccineInventory = new ArrayList<Vaccine>();
			this.supplyInventory=new ArrayList<Supply>();
			this.specialAction=false;

		}

		public boolean isSpecialAction() {
			return specialAction;
		}



		public void setSpecialAction(boolean specialAction) {
			this.specialAction = specialAction;
		}



		public int getActionsAvailable() {
			return actionsAvailable;
		}



		public void setActionsAvailable(int actionsAvailable) {
			this.actionsAvailable = actionsAvailable;
		}



		public int getMaxActions() {
			return maxActions;
		}



		public ArrayList<Vaccine> getVaccineInventory() {
			return vaccineInventory;
		}

		public boolean addVaccine(Vaccine v) {
			return this.vaccineInventory.add(v);
		}

		public boolean removeVaccine(Vaccine v) {
			return this.vaccineInventory.remove(v);
		}

		public ArrayList<Supply> getSupplyInventory() {
			return supplyInventory;
		}

		public boolean addSupply(Supply s) {
			return this.supplyInventory.add(s);
		}

		public boolean removeSupply(Supply s) {
			return this.supplyInventory.remove(s);
		}

		private void checkSize(ArrayList arr) throws GameActionException {
			if (arr.size() == 0)
				throw new NoAvailableResourcesException();
		}

		private Point getNewLocation(Direction d, Point location) {
			var currentLocationX = location.getX();
			var currentLocationY = location.getY();
			switch (d) {
				case UP:
					currentLocationX++;
					break;
				case DOWN:
					currentLocationX--;
					break;
				case LEFT:
					currentLocationY--;
					break;
				case RIGHT:
					currentLocationY++;
					break;
				default:
					break;
			}
			return new Point((int) currentLocationX, (int) currentLocationY);
		}


		public boolean isValidMove(Point loc) {
			if (loc.getX() < 0 || loc.getY() < 0 || loc.getX() > 14 || loc.getY() > 14)
				return false;
			if (((Game.map[(int)loc.getX()][(int)loc.getY()]) instanceof CharacterCell) && ((CharacterCell)(Game.map[(int)loc.getX()][(int)loc.getY()])).getCharacter()!=null)
				return false;
			return true;
		}

		public void updateVisibilty() {
			var cells = this.getAdjacentCells();
			for (Cell cell : cells)
				cell.setVisible(this.getCurrentHp() != 0);
		}

		public void move(Direction d) throws GameActionException {
			if (this.getActionsAvailable() == 0)
				throw new NotEnoughActionsException();
			Point newLoc = getNewLocation(d, getLocation());
			if (!isValidMove(newLoc))
				throw new MovementException("This is outside the map's boundary!");

			Cell cell = Game.map[(int) newLoc.getX()][(int) newLoc.getY()];
			Cell oldCell= Game.map[(int) this.getLocation().getX()][(int) this.getLocation().getY()];
			if (cell instanceof CollectibleCell) {
				var collectibleCell = (CollectibleCell) cell;
				collectibleCell.getCollectible().pickUp(this);
			} else if (cell instanceof TrapCell)
				this.setCurrentHp(this.getCurrentHp() - ((TrapCell) cell).getTrapDamage());
			this.onCharacterDeath();
			Game.map[(int) newLoc.getX()][(int) newLoc.getY()] = new CharacterCell(
					((CharacterCell) oldCell).getCharacter());
			updateVisibilty();
			((CharacterCell)(oldCell)).setCharacter(null);
			this.setActionsAvailable(this.actionsAvailable - 1);
			this.setLocation(newLoc);
		}

		public void useSpecial() throws GameActionException {
			this.setSpecialAction(true);
			if (this.getActionsAvailable() == 0)
				throw new NotEnoughActionsException(getName());
			checkSize(this.getSupplyInventory());
			this.getSupplyInventory().get(0).use(this);
		}

		/**
		 * This method should handle using a vaccine to cure a zombie. Heroes can only
		 * cure zombies that are in adjacent cells.
		 * @throws GameActionException
		 */
		public void cure() throws GameActionException {
			if (this.getTarget() == null || !this.isAdjacent() || (this.getTarget().isHero() && this.isHero()) || (!this.getTarget().isHero() && !this.isHero()))
			throw new InvalidTargetException();
			if (this.getActionsAvailable() == 0)
				throw new NotEnoughActionsException(getName());
			checkSize(this.getVaccineInventory());
			if (this.getTarget().isHero())
				throw new InvalidTargetException("Damn it!,this is a hero!🦸");
			this.getVaccineInventory().get(0).use(this);
			this.setActionsAvailable(this.getActionsAvailable()-1);
			Game.zombies.remove(this.getTarget());
			int r = new Random().nextInt(Game.availableHeroes.size());
			var newHero = Game.availableHeroes.remove(r);
			Game.heroes.add(newHero);
			var cell = (CharacterCell) Game.getCell(this.getTarget().getLocation());
			cell.setCharacter(newHero);
		}


		@Override
		public void attack() throws GameActionException {
			if (this.getActionsAvailable() == 0 )
				throw new NotEnoughActionsException(getName());
			if (this.getTarget() == null)
				throw new InvalidTargetException();
			this.setActionsAvailable(this.getActionsAvailable()-1);
			super.attack();
			this.setSpecialAction(false);
		}


}
