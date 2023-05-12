package model.characters;

import exceptions.GameActionException;

public class Fighter extends Hero{

	public Fighter(String name, int maxHp, int attackDmg, int maxActions) {
		super(name, maxHp, attackDmg, maxActions);
	}

	@Override
	public void attack() throws GameActionException {
		var isSpecial = this.isSpecialAction();
		if (isSpecial)
			this.setActionsAvailable(this.getActionsAvailable()+1);
		super.attack();
		this.setSpecialAction(isSpecial);
	}

}
