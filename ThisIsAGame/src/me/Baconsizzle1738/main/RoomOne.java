package me.Baconsizzle1738.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Random;


//based off the konami code
public class RoomOne extends Room {
Random r;
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
		if (!canReset) {
			resetCool++;
			System.out.println(resetCool);
			if (resetCool>100) {
				canReset = true;
				resetCool = 0;
			}
		}
	}

	@Override
	public void render(Graphics g) {
		//Room title
		g.setColor(Color.CYAN);
		g.setFont(new Font(Font.SERIF, 50, 50));
		g.drawString("Konami's maze", 580, 40);
	}

	@Override
	public void startLevel() {
		
		this.SetPlayerSpawn();
		//test case
//		handler.addObject(new StaticEnemy(200, 200, ID.StaticEnemy, lvl, 500, 3, 1, 40));
//		handler.addObject(new StaticEnemy(200, 200, ID.StaticEnemy, lvl, 500, 4, 20, 100, 0));
//		handler.addObject(new Obstacle(300, 100, ID.Obstacle, lvl, 20, 100, new Color(50, 200, 80)));
		
		//maze border
		handler.addObject(new Obstacle(200, 50, ID.Obstacle, lvl, 5, 500, new Color(200, 200, 200)));
		handler.addObject(new Obstacle(200, 50, ID.Obstacle, lvl, 500, 5, new Color(200, 200, 200)));
		handler.addObject(new Obstacle(695, 50, ID.Obstacle, lvl, 5, 500, new Color(200, 200, 200)));
		handler.addObject(new Obstacle(200, 545, ID.Obstacle, lvl, 500, 5, new Color(200, 200, 200)));
		
		//the maze itself
		
		
		//these will spawn randomly in the maze
		handler.addObject(new KonamiArrow(r.nextInt(470)+205, r.nextInt(470)+55, ID.Interactable, lvl, "up"));
		handler.addObject(new KonamiArrow(r.nextInt(470)+205, r.nextInt(470)+55, ID.Interactable, lvl, "down"));
		handler.addObject(new KonamiArrow(r.nextInt(470)+205, r.nextInt(470)+55, ID.Interactable, lvl, "left"));
		handler.addObject(new KonamiArrow(r.nextInt(470)+205, r.nextInt(470)+55, ID.Interactable, lvl, "right"));
		
		
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
			handler.addObject(new KonamiArrow(r.nextInt(470)+205, r.nextInt(470)+55, ID.Interactable, lvl, "up"));
			handler.addObject(new KonamiArrow(r.nextInt(470)+205, r.nextInt(470)+55, ID.Interactable, lvl, "down"));
			handler.addObject(new KonamiArrow(r.nextInt(470)+205, r.nextInt(470)+55, ID.Interactable, lvl, "left"));
			handler.addObject(new KonamiArrow(r.nextInt(470)+205, r.nextInt(470)+55, ID.Interactable, lvl, "right"));
			
			//reset player position to spawn location
			this.SetPlayerSpawn();
			
			canReset = false;
		}
		
		
	}

}
