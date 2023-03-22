package model.world;
import model.collectibles.*;

public class CollectibleCell extends Cell{

	private Collectible collectible;
	//READ ONLY.

	public Collectible getCollectible() {
		return collectible;
	}

	public CollectibleCell(Collectible collectible){
		this.collectible=collectible;
	}
}
