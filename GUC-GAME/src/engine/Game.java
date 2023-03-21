package engine;
import java.util.*;
import model.characters.*;
import model.world.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
//A class representing the Game itself. This class will represent the main engine of the game,
//and will ensure all game rules are followed.

public class Game {

	public static ArrayList<Hero> availableHeros=new ArrayList<>();
	//arraylist representing the available Heros in the game.
	
	public static ArrayList<Hero> heros=new ArrayList<>();
	//arraylist representing the Heros participating in the game.
	
	public static ArrayList<Zombie> zombies=new ArrayList<>();
	//arraylist representing the 10 zombies generated in the game.
	
	public static Cell [][] map;
	//A 2D array representing the map in the game.
	
	
	public static void loadHeroes(String filePath)
	throws IOException{
		Scanner sc =new Scanner(new File(filePath));
		sc.useDelimiter(",|\\R");
		
		while (sc.hasNext()){
			var name=sc.next();
			sc.next();	
			var maxHp=sc.nextInt();
			var maxActions=sc.nextInt();
			var attackDmg=sc.nextInt();
			availableHeros.add(new Hero(name, maxHp, attackDmg, maxActions));
		}
	}
	
}
