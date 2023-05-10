package model.collectibles;

import exceptions.GameActionException;
import exceptions.NoAvailableResourcesException;
import model.characters.Hero;

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
	}

}
