package model.world;

import model.collectibles.Collectible;
import model.collectibles.Supply;

public class CollectibleCell extends Cell {

	private Collectible collectible;

	public CollectibleCell(Collectible collectible) {
		this.collectible = collectible;
		this.setCellImage(collectible instanceof Supply ?"supply.jpeg":"vaccine.jpeg");
	}

	public Collectible getCollectible() {
		return collectible;
	}


}
