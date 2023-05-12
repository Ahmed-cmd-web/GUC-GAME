package model.characters;


import engine.Game;
import exceptions.InvalidTargetException;
import exceptions.NotEnoughActionsException;
import model.world.Cell;
import model.world.CharacterCell;

public class Zombie extends Character {
	static int ZOMBIES_COUNT = 1;

	public static int getZOMBIES_COUNT() {
		return ZOMBIES_COUNT;
	}

	public static void setZOMBIES_COUNT(int zOMBIES_COUNT) {
		if (zOMBIES_COUNT < 0)
			return;
		ZOMBIES_COUNT = zOMBIES_COUNT;
	}

	public Zombie() {
		super("Zombie " + ZOMBIES_COUNT, 40, 10);
		ZOMBIES_COUNT++;
		this.setHero(false);
	}

	public void setAdjacentTarget() {
		var cells = this.getAdjacentCells();
		for (Cell cell : cells) {
			if (cell instanceof CharacterCell && ((CharacterCell) cell).getCharacter() != null
					&& ((CharacterCell) cell).getCharacter().isHero()) {
				this.setTarget(((CharacterCell) cell).getCharacter());
				return;
			}
		}
	}

	@Override
	public void onCharacterDeath() {
		super.onCharacterDeath();
		Game.spawnRandomly(new CharacterCell(new Zombie()));
		Game.zombies.remove(this);

	}

	@Override
	public void attack() throws InvalidTargetException, NotEnoughActionsException {
		if (this.getTarget()==null)
			this.setAdjacentTarget();
		if (!this.isAdjacent())
			return;
		// if (this.getTarget() == null)
		// 	return;
		super.attack();
	}

}
