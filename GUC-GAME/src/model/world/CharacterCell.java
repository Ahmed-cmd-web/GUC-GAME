package model.world;

import model.characters.Character;
import model.characters.Hero;

public class CharacterCell extends Cell {

	private Character character;
	private boolean isSafe;

	public CharacterCell(Character character, boolean isSafe) {
		this.character = character;
		this.isSafe = isSafe;
		this.setCellImage(this.character instanceof Hero ? character.getName() : "zombie.png");

	}

	public CharacterCell(Character character) {
		this.character = character;
		this.setCellImage(character instanceof Hero ?character.getName():"zombie.png");
	}

	public Character getCharacter() {
		return character;
	}

	public void setCharacter(Character character) {
		this.character = character;
		this.setCellImage(character instanceof Hero ?character.getName():"zombie.png");
	}

	public boolean isSafe() {
		return isSafe;
	}

	public void setSafe(boolean isSafe) {
		this.isSafe = isSafe;
	}

}
