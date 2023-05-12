package engine;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.Point;
import java.awt.TextArea;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;

import exceptions.GameActionException;

import java.util.ArrayList;
import java.util.Random;

import model.characters.Explorer;
import model.characters.Fighter;
import model.characters.Hero;
import model.characters.Medic;
import model.characters.Zombie;
import model.collectibles.Supply;
import model.collectibles.Vaccine;
import model.world.Cell;
import model.world.CharacterCell;
import model.world.CollectibleCell;
import model.world.TrapCell;

public class Game {
	public static Cell[][] map=new Cell[15][15];
	public static ArrayList<Hero> availableHeroes = new ArrayList<Hero>();
	public static ArrayList<Hero> heroes = new ArrayList<Hero>();
	public static ArrayList<Zombie> zombies = new ArrayList<Zombie>();

	public static Cell getCell(Point location) {
		return map[(int) location.getX()][(int) location.getY()];
	}

	public static void loadHeroes(String filePath) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		String line = br.readLine();
		while (line != null) {
			String[] content = line.split(",");
			Hero hero = null;
			switch (content[1]) {
				case "FIGH":
					hero = new Fighter(content[0], Integer.parseInt(content[2]), Integer.parseInt(content[4]),
							Integer.parseInt(content[3]));
					break;
				case "MED":
					hero = new Medic(content[0], Integer.parseInt(content[2]), Integer.parseInt(content[4]),
							Integer.parseInt(content[3]));
					break;
				case "EXP":
					hero = new Explorer(content[0], Integer.parseInt(content[2]), Integer.parseInt(content[4]),
							Integer.parseInt(content[3]));
					break;
			}
			availableHeroes.add(hero);
			line = br.readLine();

		}
		br.close();
	}


	public static void spawnRandomly(Cell c) {
		Random r = new Random();
		int x = r.nextInt(15);
		int y = r.nextInt(15);
		while (map[x][y] instanceof CharacterCell && ((CharacterCell)map[x][y]).getCharacter()!=null) {
			x = r.nextInt(15);
			y = r.nextInt(15);
		}
		map[x][y] = c;
	}

	public static void startGame(Hero h) {
		for (int i = 0; i < 15; i++)
			for (int j = 0; j < 15; j++)
				map[i][j] = new CharacterCell(null);
		heroes.add(h);
		availableHeroes.remove(h);
		map[0][0] = new CharacterCell(h);
		h.setLocation(new Point(0, 0));
		h.updateVisibilty();
		for (int i = 0; i < 5; i++) {
			spawnRandomly(new CollectibleCell(new Supply()));
			spawnRandomly(new CollectibleCell(new Vaccine()));
			spawnRandomly(new TrapCell());
			spawnRandomly(new CharacterCell(new Zombie()));
			spawnRandomly(new CharacterCell(new Zombie()));
		}
	}


	public static void endTurn() throws GameActionException {
		for (Zombie zombie : zombies){
			zombie.setAdjacentTarget();
			zombie.attack();
		}
		for (Hero h:heroes){
			h.setActionsAvailable(h.getMaxActions());
			h.setSpecialAction(false);
			h.setTarget(null);
			h.updateVisibilty();
		}
		spawnRandomly(new CharacterCell(new Zombie()));

	}

	public static void addZombie() {
		Random rx= new Random();
		Random ry= new Random();
		while(true){
			int x=rx.nextInt(15);
			int y=ry.nextInt(15);
			if(map[x][y] instanceof CharacterCell && ((CharacterCell)map[x][y]).getCharacter()==null) {
				Zombie z = new Zombie();
				((CharacterCell)map[x][y]).setCharacter(z);
				zombies.add(z);
				z.setLocation(new Point(x, y));
				break;
			}
		}
	}

	// public static void main(String[] args) {
	// 	startGame(new Fighter(null, 0, 0, 0));
	// 	endTurn();
	// }

	// private static int sumVaccines() {
	// 	int sum = 0;
	// 	for (Hero hero : heroes)
	// 		sum += hero.getVaccineInventory().size();
	// 	return sum;
	// }

	// public static boolean checkWin() {
	// 	if (heroes.size()>=5 && sumVaccines()==0 && zombies.size()==0)
	// 		return true;
	// 	return false;
	// }

	// public static boolean checkGameOver() {

	// 	if ((sumVaccines() == 0 && zombies.size() == 0) || (heroes.size() == 0))
	// 		return true;
	// 	return false;
	// }

}










// public static void main(String[] args) {
// 	var frame = new JFrame();
// 	var panel = new JLayeredPane();
// 	panel.setSize(675, 675);
// 	panel.setBorder(BorderFactory.createLineBorder(Color.black));
// 	panel.setMaximumSize(new Dimension(400, 400));
// 	panel.setBackground(Color.cyan);
// 	frame.setSize(680, 705);
// 	frame.getContentPane().add(panel);
// 	frame.setLayout(null);

// 	// var ncell = new JPanel();
// 	var ncell = new JTextPane();
// 	// ncell.setText();
// 	ncell.setEditable(false);
// 	ncell.setBorder(BorderFactory.createLineBorder(Color.black, 1));
// 	ncell.setBounds(400, 45, 45, 45);
// 	ncell.setBackground(Color.yellow);
// 	try {
// 		var myPicture = ImageIO.read(new File("GUC-GAME/src/model/pic.jpeg"));

// 		JLabel picLabel = new JLabel(new ImageIcon(myPicture));
// 		picLabel.setBounds(0, 0, 45, 45);
// 		ncell.add(picLabel);

// 	} catch (Exception e) {
// 		// TODO: handle exception
// 		System.out.println(e.getMessage());
// 	}
// 	frame.getContentPane().add(ncell);

// 	var f = false;
// 	for (int y = 0; y < 675; y += 45) {
// 		for (int x = 0; x < 675; x += 45) {
// 			var cell = new JPanel();
// 			cell.setBorder(BorderFactory.createLineBorder(Color.black, 0));
// 			cell.setBounds(x, y, 45, 45);
// 			if (f)
// 				cell.setBackground(Color.white);
// 			else
// 				cell.setBackground(Color.gray);
// 			f = !f;
// 			frame.getContentPane().add(cell);
// 		}
// 	}





// 	// var p1 = new JPanel();
// 	// p1.setBounds(360, 400, 40, 40);
// 	// p1.setBackground(Color.blue);
// 	// var p2 = new JPanel();
// 	// p2.setBounds(360, 40, 40, 40);
// 	// p2.setBackground(Color.GREEN);
// 	// frame.getContentPane().add( p1);
// 	// frame.getContentPane().add(p2);
// 	frame.setVisible(true);

// }