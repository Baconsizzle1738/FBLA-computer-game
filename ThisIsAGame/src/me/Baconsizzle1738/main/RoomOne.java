package me.Baconsizzle1738.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Random;


//based off the konami code
public class RoomOne extends Room {
	Random r;
	private int collected = 0;
	
	public RoomOne(int spawnX, int spawnY, GameHandler h, int lvl) {
		super(spawnX, spawnY, h, lvl);
		//test
		r = new Random();
	}
	
	@Override
	public boolean isComplete() {
		//test to see if work
//		for (int i = 0; i<handler.objects.size(); i++) {
//			GameObject temp = handler.objects.get(i);
//			if (temp.gettypeID() == ID.Player) {
//				if (temp.getX()>500) {
//					return true;
//				}
//			}
//		}
		return false;
	}

	@Override
	public void tick() {
		for (int i = 0; i<handler.objects.size(); i++) {
			Player tempPlayer;
			if (handler.objects.get(i).gettypeID() == ID.Player) {
				tempPlayer = (Player) handler.objects.get(i);
				//check if collecting an arrow
				CollectArrow(tempPlayer);
				break;
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
	 * Removes the KonamiArrow that the player touches and makes a new one
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
		int[] newX = {5, 0, 0, 0, 0, 0, 0, 0};
		int[] newY = {9, 0, 0, 0, 0, 0, 0, 0};
		
		handler.addObject(new KonamiArrow(mazeIncrementX(newX[collected])+17, mazeIncrementY(newY[collected])+17, ID.Interactable, lvl, direction));
		collected++;
		
	}

	@Override
	public void render(Graphics g) {
		//Room title
		g.setColor(Color.CYAN);
		g.setFont(new Font(Font.SERIF, 50, 50));
		g.drawString("Konami's maze", 580, 40);
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
		handler.addObject(new StaticEnemy(mazeIncrementX(0)+10, mazeIncrementY(0)+10, ID.StaticEnemy, lvl, mazeIncrementX(9)+10, 3, 5, 20));
		handler.addObject(new StaticEnemy(mazeIncrementX(3)+10, mazeIncrementY(2)+10, ID.StaticEnemy, lvl, mazeIncrementX(5)+10, 1, 3, 60));
		handler.addObject(new StaticEnemy(mazeIncrementX(9)+10, mazeIncrementY(4)+10, ID.StaticEnemy, lvl, mazeIncrementY(6)+10, 1, 3, 60, -1));
		handler.addObject(new StaticEnemy(mazeIncrementX(0)+10, mazeIncrementY(7)+10, ID.StaticEnemy, lvl, mazeIncrementX(6)+10, 1, 3, 60));
		handler.addObject(new StaticEnemy(mazeIncrementX(0)+10, mazeIncrementY(8)+10, ID.StaticEnemy, lvl, mazeIncrementX(4)+10, 2, 3, 40));
		
		
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
			handler.addObject(new KonamiArrow(mazeIncrementX(r.nextInt(10))+16, mazeIncrementY(r.nextInt(10))+16, ID.Interactable, lvl, "up"));
			handler.addObject(new KonamiArrow(mazeIncrementX(r.nextInt(10))+16, mazeIncrementY(r.nextInt(10))+16, ID.Interactable, lvl, "down"));
			handler.addObject(new KonamiArrow(mazeIncrementX(r.nextInt(10))+16, mazeIncrementY(r.nextInt(10))+16, ID.Interactable, lvl, "left"));
			handler.addObject(new KonamiArrow(mazeIncrementX(r.nextInt(10))+16, mazeIncrementY(r.nextInt(10))+16, ID.Interactable, lvl, "right"));
			
			//reset player position to spawn location
			this.SetPlayerSpawn();
			
			canReset = false;
		}
		
		
	}

}
