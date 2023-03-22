package engine;
import java.util.*;
import model.characters.*;
import model.world.*;
import java.io.File;
import java.io.IOException;
//A class representing the Game itself. This class will represent the main engine of the game,
//and will ensure all game rules are followed.


public class Game {

	public static ArrayList<Hero> availableHeroes;
	//arraylist representing the available Heros in the game.

	public static ArrayList<Hero> heroes;
	//arraylist representing the Heros participating in the game.

	//arraylist representing the 10 zombies generated in the game.
	public static ArrayList<Zombie> zombies;

	public static Cell [][] map;
	//A 2D array representing the map in the game.



	public static void loadHeroes(String filePath)
			throws IOException {
		Scanner sc = new Scanner(new File(filePath));
		sc.useDelimiter(",|\\R");

		while (sc.hasNext()) {
			var name = sc.next();
			var type = sc.next();
			var maxHp = sc.nextInt();
			var maxActions = sc.nextInt();
			var attackDmg = sc.nextInt();

			switch (type) {
				case "MED":
					availableHeroes.add(new Medic(name, maxHp, attackDmg, maxActions));
					break;
				case "FIGH":
					availableHeroes.add(new Fighter(name, maxHp, attackDmg, maxActions));
					break;
				case "EXP":
					availableHeroes.add(new Explorer(name, maxHp, attackDmg, maxActions));
					break;
				default:
					break;
			}

		}
	}


	public Game() {
		availableHeroes = new ArrayList<>();
		zombies = new ArrayList<>();
		heroes=new ArrayList<>();
	}
	

}
