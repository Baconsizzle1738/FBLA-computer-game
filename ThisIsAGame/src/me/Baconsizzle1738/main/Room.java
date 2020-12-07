package me.Baconsizzle1738.main;

import java.awt.Graphics;

public class Room {  
	private int initialX, initialY, num;
	GameHandler handler;
	public boolean complete = false;
	
	public Room(int spawnX, int spawnY, GameHandler h, int num) {
		initialX = spawnX;
		initialY = spawnY;
		handler = h;
		this.num = num;
	}
	
	public void SetPlayerSpawn() {
		for (int i = 0; i<handler.objects.size(); i++) {
			if (handler.objects.get(i).gettypeID() == ID.Player) {
				GameObject temp = handler.objects.get(i);
				temp.setX(initialX);
				temp.setY(initialY);
			}
		}
	}
	
	public void tick() {
		
	}
	public void render(Graphics g) {
		
	}
}