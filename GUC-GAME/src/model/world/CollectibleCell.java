package model.world;

import model.collectibles.Collectible;
import model.collectibles.Supply;

public class CollectibleCell extends Cell {

	private Collectible collectible;

	public CollectibleCell(Collectible collectible) {
		this.collectible = collectible;
		this.setCellImage(collectible instanceof Supply ?"supply.png":"vaccine.png");
	}

	public Collectible getCollectible() {
		return collectible;
	}


}
