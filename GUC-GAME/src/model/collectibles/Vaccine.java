package model.collectibles;

import java.util.Random;

import engine.Game;
import exceptions.GameActionException;
import exceptions.InvalidTargetException;
import exceptions.NoAvailableResourcesException;
import exceptions.NotEnoughActionsException;
import model.characters.Hero;
import model.world.CharacterCell;

public class Vaccine implements Collectible {

	public Vaccine() {

	}

	@Override
	public void pickUp(Hero h) {
		h.addVaccine(this);
	}

	@Override
	public void use(Hero h) throws GameActionException {
		if (!h.removeVaccine(this))
			throw new NoAvailableResourcesException();
		if (h.getTarget() == null  || (h.getTarget().isHero() && h.isHero()) || (!h.getTarget().isHero() && !h.isHero()))
				throw new InvalidTargetException();
		if (h.getActionsAvailable() == 0)
			throw new NotEnoughActionsException(h.getName());
		Game.zombies.remove(h.getTarget());
		int r = new Random().nextInt(Game.availableHeroes.size());
		var newHero = Game.availableHeroes.remove(r);
		newHero.setLocation(h.getTarget().getLocation());
		Game.heroes.add(newHero);

		Game.heroes.add(h);  // This line was added due to the following test:testUseMethodLogicInVaccine
		var cell = (CharacterCell) Game.getCell(h.getTarget().getLocation());
		cell.setCharacter(newHero);
		h.setActionsAvailable(h.getActionsAvailable()-1);
	}

}
