package me.Baconsizzle1738.gameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
//import java.util.Random;

import me.Baconsizzle1738.Objects.GameHandler;
import me.Baconsizzle1738.Objects.GameObject;
import me.Baconsizzle1738.Objects.KonamiArrow;
import me.Baconsizzle1738.Objects.KonamiDoor;
import me.Baconsizzle1738.Objects.Obstacle;
import me.Baconsizzle1738.Objects.Player;
import me.Baconsizzle1738.Objects.StaticEnemy;
import me.Baconsizzle1738.main.Game;
import me.Baconsizzle1738.main.ID;


//based off the konami code

/**
 * The player must navigate a maze and collect <code>KonamiArrows</code>
 * in the order of the Konami code in order to pass.
 * @author Baconsizzle1738
 *
 */
public class RoomOne extends Room {
	//Random r;
	private int collected = 0;
	private ArrayList<KonamiArrow> collectedArrows;
	private ArrayList<String> correct;
	//check if all arrows are collected
	private boolean allArrows = false;
	//check if combination is correct;
	private boolean correctComb = false;
	private boolean done;
	
	Player p;
	
	/**
	 * Takes only basic parameters for <code>Room</code>.
	 * 
	 * @param spawnX	x position of spawn point.
	 * @param spawnY	y position of spawn point.
	 * @param h			<code>GameHandler</code> to add objects.
	 * @param lvl		The level of the room.
	 */
	public RoomOne(int spawnX, int spawnY, GameHandler h, int lvl) {
		super(spawnX, spawnY, h, lvl);
		//r = new Random();
		collectedArrows = new ArrayList<>();
		correct = new ArrayList<>();
		correct.add("up");
		correct.add("up");
		correct.add("down");
		correct.add("down");
		correct.add("left");
		correct.add("right");
		correct.add("left");
		correct.add("right");
		p = new Player(400, 400, ID.Player, this.lvl, this.handler);
		done = false;
		
	}
	
	@Override
	public boolean isComplete() {
		KonamiDoor d= null;
		
		//checks if the combination is correct
		if (correctComb) {
			for (int i = 0; i<handler.objects.size(); i++) {
				if (handler.objects.get(i).gettypeID() == ID.Door) {
					d = (KonamiDoor) handler.objects.get(i);
					break;
				}
			}
			//check if player is at the door
			try {
				if (p.getBounds().intersects(d.getBounds())) {
					done = true;
				}
			}
			catch(Exception e) {
				
			}
			
			
		}
		
		//change to true for quick advance for testing out other levels
		return done;
	}

	
	@Override
	public void tick() {
		
		//check for arrows being collected by player
		if (!allArrows) {
			CollectArrow();
		}
		
		//level reset cooldown timer
		if (!canReset) {
			resetCool++;
			//System.out.println(resetCool);
			if (resetCool>100) {
				canReset = true;
				resetCool = 0;
			}
		}
//		if (Game.isdead) {
//			for (int i = 0; i<handler.objects.size(); i++) {
//				if (handler.objects.get(i).getlevelID() == lvl) {
//					handler.removeObject(handler.objects.get(i));
//					i--;
//				}
//			}
//		}
	}
	/**
	 * Removes the KonamiArrow that the player touches and makes a new one, also adding it to collectedArrows
	 * 
	 * @param p	The player collecting the KonamiArrow
	 */
	private void CollectArrow() {
		for (int i = 0; i<handler.objects.size(); i++) {
			KonamiArrow temp;
			if (handler.objects.get(i).gettypeID() == ID.Interactable) {
				temp = (KonamiArrow) handler.objects.get(i);
				//makes a new arrow of temp direction and removes the collected one
				if (p.getBounds().intersects(temp.getBounds())) {
					newArrow(temp.getDirection());
					handler.removeObject(temp);
					break;
				}
			}
		}
	}
	
	/**
	 * replaces KonamiArrow player collects
	 * @param direction	Direction of arrow that player just collected
	 */
	private void newArrow(String direction) {
		int[] newX = {5, 0, 6, 4, 0, 9, 6, 0, 9};
		int[] newY = {9, 5, 1, 5, 8, 1, 8, 0, 9};
		
		collectedArrows.add(new KonamiArrow(730+collected*25, 200, null, lvl, direction));
		if (collectedArrows.size() == 8) {
			allArrows = true;
			int c = 0;
			for (int i = 0; i<correct.size(); i++) {
				if (collectedArrows.get(i).getDirection().equals(correct.get(i))) {
					c++;
				}
			}
			if (c == 8) {
				handler.addObject(new KonamiDoor(mazeIncrementX(10)+3, mazeIncrementY(4), ID.Door, lvl));
				correctComb = true;
			}
		}
		handler.addObject(new KonamiArrow(mazeIncrementX(newX[collected])+17, mazeIncrementY(newY[collected])+17, ID.Interactable, lvl, direction));
		collected++;
		
	}

	@Override
	public void render(Graphics g) {
		//Room title
		g.setColor(Color.CYAN);
		g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 50));
		g.drawString("Konami's maze", 580, 40);
		
		//space for collected arrows
		g.setColor(new Color(200, 0, 0));
		g.drawRect(730, 200, 20, 20);
		g.drawRect(755, 200, 20, 20);
		g.drawRect(780, 200, 20, 20);
		g.drawRect(805, 200, 20, 20);
		g.drawRect(830, 200, 20, 20);
		g.drawRect(855, 200, 20, 20);
		g.drawRect(880, 200, 20, 20);
		g.drawRect(905, 200, 20, 20);
		
		//render collected arrows inside the boxes
		for (int i = 0; i<collectedArrows.size(); i++) {
			collectedArrows.get(i).render(g);
		}
		
		//Dialogue
		g.setFont(new Font(Font.MONOSPACED, 10, 12));
		if (!allArrows) {
			g.setColor(new Color(200,200,0));
			g.drawString("The labyrinth seems to have no exit...", 710, 240);
			g.drawString("There seems to be random keys lying", 710, 255);
			g.drawString("around though.", 710, 270);
		}
		if (allArrows && !correctComb) {
			g.setColor(new Color(200,200,0));
			g.drawString("Hmm...", 720, 240);
			g.drawString("Nothing happened.", 720, 255);
		}
		if (allArrows && correctComb) {
			g.setColor(new Color(200,200,0));
			g.drawString("Oh look a door.", 720, 240);
		}
		
		//g.dispose();
	}
	
	//so the maze width stays consistent
	/**
	 * x increments based off the maze width, made so that everything can be evenly spaced easily
	 * @param n	increment number
	 * @return	the x coordinate corresponding to the increment
	 */
	private int mazeIncrementX(int n) {
		return 200+(49*n);
	}
	
	/**
	 * y increments based off the maze width, made so that everything can be evenly spaced easily
	 * @param n	increment number
	 * @return	the y coordinate corresponding to the increment
	 */
	private int mazeIncrementY(int n) {
		return 50+(49*n);
	}
	
	/**
	 * The space between the increments so that lengths are spaced evenly for the maze
	 * @param n	increment number
	 * @return	length corresponding to the increment
	 */
	private int mazeUnitLength(int n) {
		return 49*n+5;
	}
	
	@Override
	public void startLevel() {
		reset();
		Color mazeCol = new Color(170, 170, 170);
		handler.addObject(p);
		
		this.SetPlayerSpawn();
		//test case
//		handler.addObject(new StaticEnemy(200, 200, ID.StaticEnemy, lvl, 500, 3, 1, 40));
//		handler.addObject(new StaticEnemy(200, 200, ID.StaticEnemy, lvl, 500, 4, 20, 100, 0));
//		handler.addObject(new Obstacle(300, 100, ID.Obstacle, lvl, 20, 100, new Color(50, 200, 80)));
		
		//maze border
		handler.addObject(new Obstacle(200, 50, ID.Obstacle, lvl, 5, 500, mazeCol));
		handler.addObject(new Obstacle(200, 50, ID.Obstacle, lvl, 500, 5, mazeCol));
		handler.addObject(new Obstacle(695, 50, ID.Obstacle, lvl, 5, 500, mazeCol));
		handler.addObject(new Obstacle(200, 545, ID.Obstacle, lvl, 500, 5, mazeCol));
		
		//the maze itself
		handler.addObject(new Obstacle(mazeIncrementX(1), mazeIncrementY(1), ID.Obstacle, lvl, 5, mazeUnitLength(3), mazeCol));
		handler.addObject(new Obstacle(mazeIncrementX(1), mazeIncrementY(1), ID.Obstacle, lvl, mazeUnitLength(2), 5, mazeCol));
		handler.addObject(new Obstacle(mazeIncrementX(4), mazeIncrementY(1), ID.Obstacle, lvl, mazeUnitLength(1), 5, mazeCol));
		handler.addObject(new Obstacle(mazeIncrementX(6), mazeIncrementY(1), ID.Obstacle, lvl, mazeUnitLength(1), 5, mazeCol));
		handler.addObject(new Obstacle(mazeIncrementX(8), mazeIncrementY(1), ID.Obstacle, lvl, mazeUnitLength(2), 5, mazeCol));
		handler.addObject(new Obstacle(mazeIncrementX(2), mazeIncrementY(2), ID.Obstacle, lvl, 5, mazeUnitLength(4), mazeCol));
		handler.addObject(new Obstacle(mazeIncrementX(3), mazeIncrementY(2), ID.Obstacle, lvl, mazeUnitLength(3), 5, mazeCol));
		handler.addObject(new Obstacle(mazeIncrementX(3), mazeIncrementY(1), ID.Obstacle, lvl, 5, mazeUnitLength(2), mazeCol));
		handler.addObject(new Obstacle(mazeIncrementX(6), mazeIncrementY(1), ID.Obstacle, lvl, 5, mazeUnitLength(4), mazeCol));
		handler.addObject(new Obstacle(mazeIncrementX(9), mazeIncrementY(1), ID.Obstacle, lvl, 5, mazeUnitLength(1), mazeCol));
		handler.addObject(new Obstacle(mazeIncrementX(7), mazeIncrementY(3), ID.Obstacle, lvl, mazeUnitLength(2), 5, mazeCol));
		handler.addObject(new Obstacle(mazeIncrementX(0), mazeIncrementY(5), ID.Obstacle, lvl, mazeUnitLength(1), 5, mazeCol));
		handler.addObject(new Obstacle(mazeIncrementX(7), mazeIncrementY(2), ID.Obstacle, lvl, mazeUnitLength(1), 5, mazeCol));
		handler.addObject(new Obstacle(mazeIncrementX(7), mazeIncrementY(1), ID.Obstacle, lvl, 5, mazeUnitLength(1), mazeCol));
		handler.addObject(new Obstacle(mazeIncrementX(3), mazeIncrementY(4), ID.Obstacle, lvl, mazeUnitLength(1), 5, mazeCol));
		handler.addObject(new Obstacle(mazeIncrementX(4), mazeIncrementY(3), ID.Obstacle, lvl, 5, mazeUnitLength(1), mazeCol));
		handler.addObject(new Obstacle(mazeIncrementX(5), mazeIncrementY(3), ID.Obstacle, lvl, 5, mazeUnitLength(3), mazeCol));
		handler.addObject(new Obstacle(mazeIncrementX(3), mazeIncrementY(4), ID.Obstacle, lvl, 5, mazeUnitLength(3), mazeCol));
		handler.addObject(new Obstacle(mazeIncrementX(4), mazeIncrementY(5), ID.Obstacle, lvl, mazeUnitLength(1), 5, mazeCol));
		handler.addObject(new Obstacle(mazeIncrementX(4), mazeIncrementY(5), ID.Obstacle, lvl, 5, mazeUnitLength(1), mazeCol));
		handler.addObject(new Obstacle(mazeIncrementX(2), mazeIncrementY(7), ID.Obstacle, lvl, mazeUnitLength(5), 5, mazeCol));
		handler.addObject(new Obstacle(mazeIncrementX(5), mazeIncrementY(6), ID.Obstacle, lvl, mazeUnitLength(4), 5, mazeCol));
		handler.addObject(new Obstacle(mazeIncrementX(8), mazeIncrementY(4), ID.Obstacle, lvl, mazeUnitLength(2), 5, mazeCol));
		handler.addObject(new Obstacle(mazeIncrementX(7), mazeIncrementY(4), ID.Obstacle, lvl, 5, mazeUnitLength(1), mazeCol));
		handler.addObject(new Obstacle(mazeIncrementX(7), mazeIncrementY(5), ID.Obstacle, lvl, mazeUnitLength(2), 5, mazeCol));
		handler.addObject(new Obstacle(mazeIncrementX(1), mazeIncrementY(6), ID.Obstacle, lvl, 5, mazeUnitLength(1), mazeCol));
		handler.addObject(new Obstacle(mazeIncrementX(0), mazeIncrementY(8), ID.Obstacle, lvl, mazeUnitLength(4), 5, mazeCol));
		handler.addObject(new Obstacle(mazeIncrementX(1), mazeIncrementY(9), ID.Obstacle, lvl, mazeUnitLength(3), 5, mazeCol));
		handler.addObject(new Obstacle(mazeIncrementX(5), mazeIncrementY(8), ID.Obstacle, lvl, 5, mazeUnitLength(2), mazeCol));
		handler.addObject(new Obstacle(mazeIncrementX(7), mazeIncrementY(7), ID.Obstacle, lvl, 5, mazeUnitLength(1), mazeCol));
		handler.addObject(new Obstacle(mazeIncrementX(6), mazeIncrementY(8), ID.Obstacle, lvl, 5, mazeUnitLength(1), mazeCol));
		handler.addObject(new Obstacle(mazeIncrementX(8), mazeIncrementY(7), ID.Obstacle, lvl, mazeUnitLength(2), 5, mazeCol));
		handler.addObject(new Obstacle(mazeIncrementX(6), mazeIncrementY(9), ID.Obstacle, lvl, mazeUnitLength(3), 5, mazeCol));
		handler.addObject(new Obstacle(mazeIncrementX(8), mazeIncrementY(7), ID.Obstacle, lvl, 5, mazeUnitLength(1), mazeCol));
		handler.addObject(new Obstacle(mazeIncrementX(9), mazeIncrementY(8), ID.Obstacle, lvl, 5, mazeUnitLength(1), mazeCol));
		
		
		//these will be what the player collects
		handler.addObject(new KonamiArrow(mazeIncrementX(9)+17, mazeIncrementY(0)+17, ID.Interactable, lvl, "up"));
		handler.addObject(new KonamiArrow(mazeIncrementX(2)+17, mazeIncrementY(2)+17, ID.Interactable, lvl, "down"));
		handler.addObject(new KonamiArrow(mazeIncrementX(5)+17, mazeIncrementY(5)+17, ID.Interactable, lvl, "left"));
		handler.addObject(new KonamiArrow(mazeIncrementX(9)+17, mazeIncrementY(7)+17, ID.Interactable, lvl, "right"));
		
		//add enemies
		handler.addObject(new StaticEnemy(mazeIncrementX(0)+10, mazeIncrementY(0)+10, ID.Enemy, lvl, mazeIncrementX(9)+10, 3, 12, 20, "x"));
		handler.addObject(new StaticEnemy(mazeIncrementX(3)+10, mazeIncrementY(2)+10, ID.Enemy, lvl, mazeIncrementX(5)+10, 1, 8, 30, "x"));
		handler.addObject(new StaticEnemy(mazeIncrementX(9)+10, mazeIncrementY(4)+10, ID.Enemy, lvl, mazeIncrementY(6)+10, 1, 8, 30, "y"));
		handler.addObject(new StaticEnemy(mazeIncrementX(0)+10, mazeIncrementY(7)+10, ID.Enemy, lvl, mazeIncrementX(6)+10, 1, 8, 30, "x"));
		handler.addObject(new StaticEnemy(mazeIncrementX(0)+10, mazeIncrementY(8)+10, ID.Enemy, lvl, mazeIncrementX(4)+10, 2, 11, 40, "x"));
		
		System.out.println("ONE START");
	}

	@Override
	public void reset() {
		//reset cooldown needed to prevent spamming
		if (canReset) {
			//System.out.println("ree");
			//removes the konami arrows
			for (int i = 0; i<handler.objects.size(); i++) {
				GameObject temp = handler.objects.get(i);
				if (temp.gettypeID() == ID.Interactable && temp.getlevelID()== this.lvl) {
					handler.removeObject(temp);
					i--;
				}
			}
			//puts in new konami arows
			handler.addObject(new KonamiArrow(mazeIncrementX(9)+17, mazeIncrementY(0)+17, ID.Interactable, lvl, "up"));
			handler.addObject(new KonamiArrow(mazeIncrementX(2)+17, mazeIncrementY(2)+17, ID.Interactable, lvl, "down"));
			handler.addObject(new KonamiArrow(mazeIncrementX(5)+17, mazeIncrementY(5)+17, ID.Interactable, lvl, "left"));
			handler.addObject(new KonamiArrow(mazeIncrementX(9)+17, mazeIncrementY(7)+17, ID.Interactable, lvl, "right"));
			
			//reset player position to spawn location
			this.SetPlayerSpawn();
			
			//clear arrayList
			collectedArrows.clear();
			
			canReset = false;
			allArrows = false;
			correctComb = false;
			done = false;
			collected = 0;
		}
		
		
	}

	@Override
	public void hardReset() {
		
		//removes any possible remaining arrows or other ID.Interactable objects
		for (int i = 0; i<handler.objects.size(); i++) {
			GameObject temp = handler.objects.get(i);
			if (temp.gettypeID() == ID.Interactable && temp.getlevelID()== this.lvl) {
				handler.removeObject(temp);
				i--;
			}
		}
		collectedArrows.clear();
		
		allArrows = false;
		correctComb = false;
		done = false;
		collected = 0;
	}

}
