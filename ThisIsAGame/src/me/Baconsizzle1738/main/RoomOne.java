package me.Baconsizzle1738.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;


//based off the konami code
public class RoomOne extends Room {
	Random r;
	private int collected = 0;
	private ArrayList<KonamiArrow> collectedArrows;
	private ArrayList<String> correct;
	//check if all arrows are collected
	private boolean allArrows = false;
	//check if combination is correct;
	private boolean correctComb = false;
	
	public RoomOne(int spawnX, int spawnY, GameHandler h, int lvl) {
		super(spawnX, spawnY, h, lvl);
		//test
		r = new Random();
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
		
	}
	
	@Override
	public boolean isComplete() {
		Player p = null;
		KonamiDoor d= null;
		
		if (correctComb) {
			for (int i = 0; i<handler.objects.size(); i++) {
				if (handler.objects.get(i).gettypeID() == ID.Player) {
					p = (Player) handler.objects.get(i);
				}
				if (handler.objects.get(i).gettypeID() == ID.Door) {
					d = (KonamiDoor) handler.objects.get(i);
				}
			}
			if (p.getBounds().intersects(d.getBounds())) {
				return true;
			}
		}
		
		//change to true for quick advance for testing out other levels
		return true;
	}

	@Override
	public void tick() {
		if (!allArrows) {
			for (int i = 0; i<handler.objects.size(); i++) {
				Player tempPlayer;
				if (handler.objects.get(i).gettypeID() == ID.Player) {
					tempPlayer = (Player) handler.objects.get(i);
					//check if collecting an arrow
					CollectArrow(tempPlayer);
					break;
				}
				
			}
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
		
	}
	/**
	 * Removes the KonamiArrow that the player touches and makes a new one, also adding it to collectedArrows
	 * 
	 * @param p	The player collecting the KonamiArrow
	 */
	private void CollectArrow(Player p) {
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
		g.setFont(new Font(Font.SERIF, 50, 50));
		g.drawString("Konami's maze", 580, 40);
		
		g.setColor(new Color(200, 0, 0));
		g.drawRect(730, 200, 20, 20);
		g.drawRect(755, 200, 20, 20);
		g.drawRect(780, 200, 20, 20);
		g.drawRect(805, 200, 20, 20);
		g.drawRect(830, 200, 20, 20);
		g.drawRect(855, 200, 20, 20);
		g.drawRect(880, 200, 20, 20);
		g.drawRect(905, 200, 20, 20);
		
		for (int i = 0; i<collectedArrows.size(); i++) {
			collectedArrows.get(i).render(g);
		}
		g.setFont(new Font(Font.SERIF, 10, 12));
		if (!allArrows) {
			g.setColor(new Color(200,200,0));
			g.drawString("The labyrinth seems to have no exit...", 720, 240);
			g.drawString("There seems to be random keys lying around though.", 720, 255);
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
	}
	
	//so the maze width stays consistent
	private int mazeIncrementX(int n) {
		return 200+(49*n);
	}
	private int mazeIncrementY(int n) {
		return 50+(49*n);
	}
	private int mazeUnitLength(int n) {
		return 49*n+5;
	}
	
	@Override
	public void startLevel() {
		
		Color mazeCol = new Color(200, 200, 200);
		
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
		handler.addObject(new StaticEnemy(mazeIncrementX(0)+10, mazeIncrementY(0)+10, ID.Enemy, lvl, mazeIncrementX(9)+10, 3, 10, 20));
		handler.addObject(new StaticEnemy(mazeIncrementX(3)+10, mazeIncrementY(2)+10, ID.Enemy, lvl, mazeIncrementX(5)+10, 1, 6, 60));
		handler.addObject(new StaticEnemy(mazeIncrementX(9)+10, mazeIncrementY(4)+10, ID.Enemy, lvl, mazeIncrementY(6)+10, 1, 6, 60, -1));
		handler.addObject(new StaticEnemy(mazeIncrementX(0)+10, mazeIncrementY(7)+10, ID.Enemy, lvl, mazeIncrementX(6)+10, 1, 6, 60));
		handler.addObject(new StaticEnemy(mazeIncrementX(0)+10, mazeIncrementY(8)+10, ID.Enemy, lvl, mazeIncrementX(4)+10, 2, 7, 40));
		
		
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
			collected = 0;
		}
		
		
	}

}
