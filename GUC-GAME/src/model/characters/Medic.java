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
		if (this.getTarget() == null || !this.getTarget().isHero() || this.getTarget().getCurrentHp()==this.getTarget().getMaxHp())
			throw new InvalidTargetException();
		super.useSpecial();
		this.setActionsAvailable(this.getActionsAvailable()-1);
		var target = this.getTarget();
		target.setCurrentHp(target.getMaxHp());
	}
}
