package model.collectibles;

import exceptions.GameActionException;
import exceptions.NoAvailableResourcesException;
import model.characters.Hero;

public class Supply implements Collectible{


	public Supply() {

	}

	@Override
	public void pickUp(Hero h) {
		h.addSupply(this);
	}

	@Override
	public void use(Hero h) throws GameActionException {
		if (!h.removeSupply(this))
			throw new NoAvailableResourcesException();
	}


}
