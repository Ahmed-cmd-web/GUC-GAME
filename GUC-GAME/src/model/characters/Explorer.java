package model.characters;

import engine.Game;
import exceptions.GameActionException;
import model.world.Cell;

public class Explorer extends Hero {


	public Explorer(String name,int maxHp, int attackDmg, int maxActions) {
		super( name, maxHp,  attackDmg,  maxActions) ;

	}

	private void setAllCellsVisible() {
		for (int i = 0; i < 15; i++)
			for (int j = 0; j < 15; j++)
				Game.map[i][j].setVisible(true);
	}

	@Override
	public void useSpecial() throws GameActionException {
		super.useSpecial();
		this.setActionsAvailable(this.getActionsAvailable()-1);
		this.setAllCellsVisible();
	}



}
