package me.Baconsizzle1738.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;


//based off the konami code
public class RoomOne extends Room {

	public RoomOne(int spawnX, int spawnY, GameHandler h, int lvl) {
		super(spawnX, spawnY, h, lvl);
		//test
		
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
		
	}

	@Override
	public void render(Graphics g) {
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
		
		handler.addObject(new Obstacle(200, 50, ID.Obstacle, lvl, 5, 500, new Color(200, 200, 200)));
		handler.addObject(new Obstacle(200, 50, ID.Obstacle, lvl, 500, 5, new Color(200, 200, 200)));
		handler.addObject(new Obstacle(695, 50, ID.Obstacle, lvl, 5, 500, new Color(200, 200, 200)));
		handler.addObject(new Obstacle(200, 545, ID.Obstacle, lvl, 500, 5, new Color(200, 200, 200)));
		
		
	}

	@Override
	public void reset() {
		this.startLevel();
	}

}
