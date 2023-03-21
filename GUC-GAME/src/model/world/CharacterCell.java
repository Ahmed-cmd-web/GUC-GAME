package model.world;

public class CharacterCell extends Cell{
	
	private Character character;
	//READ & WRITE.
	
	private boolean isSafe;
	//READ & WRITE.

	public Character getCharacter() {
		return character;
	}

	public void setCharacter(Character character) {
		this.character = character;
	}

	public boolean isSafe() {
		return isSafe;
	}

	public void setSafe(boolean isSafe) {
		this.isSafe = isSafe;
	}
	
	public CharacterCell(Character character, boolean isSafe) {
		super();
		this.character=character;
		this.isSafe=isSafe;
	}
	
}
