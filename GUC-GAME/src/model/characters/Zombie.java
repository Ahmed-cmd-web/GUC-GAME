package model.characters;

import java.util.ArrayList;

import engine.Game;
import exceptions.GameActionException;
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
		var cells=this.getAdjacentCells();
		for (Cell cell : cells) {
			if (cell instanceof CharacterCell && ((CharacterCell) cell).getCharacter()!= null &&((CharacterCell) cell).getCharacter().isHero() ) {
					this.setTarget(((CharacterCell) cell).getCharacter());
					return;
			}
		}
	}



	@Override
	public void onCharacterDeath() {
		// TODO Auto-generated method stub
		super.onCharacterDeath();
		Game.spawnRandomly(new CharacterCell(new Zombie()));
		Game.zombies.remove(this);

	}


	@Override
	public void attack() throws GameActionException {
		this.setAdjacentTarget();
		if (this.getTarget() == null)
			return;
		super.attack();
	}


	// public static void main(String[] args) {
	// 	for (int i = -1; i < 2; i++) {
	// 		for (int j = -1; j < 2; j++) {
	// 			System.out.println(i+","+j);
	// 		}
	// 	}
	// }

}
