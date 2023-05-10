package model.characters;

import exceptions.GameActionException;
import exceptions.InvalidTargetException;

public class Medic extends Hero {
	//Heal amount  attribute - quiz idea


	public Medic(String name, int maxHp, int attackDmg, int maxActions) {
		super(name, maxHp, attackDmg, maxActions);

	}


	@Override
	public void useSpecial() throws GameActionException {
		if (!this.isHero())
			throw new InvalidTargetException("Damn it!!! This is a Zombie!😈");
		super.useSpecial();
		var target = this.getTarget();
		target.setCurrentHp(target.getMaxHp());
	}


}
