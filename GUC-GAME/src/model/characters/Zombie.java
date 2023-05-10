package model.characters;

import java.util.ArrayList;

import engine.Game;
import exceptions.GameActionException;
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


	private void setAdjacentTarget() {
		var x = this.getLocation().getX();
		var y = this.getLocation().getY();
		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				if ((i + x) <= 14 && (j + y) <= 14 && (i + x) >= 0 && (j + y) >= 0) {
					var cell = Game.map[(int) (i + x)][(int) (j + y)];
					if (cell instanceof CharacterCell && ((CharacterCell) cell).getCharacter() instanceof Hero) {
						this.setTarget(((CharacterCell) cell).getCharacter());
						return;
					}
				}
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
