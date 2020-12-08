package me.Baconsizzle1738.main;

import java.awt.Graphics;

public abstract class Room {  
	private int initialX, initialY, lvl;
	GameHandler handler;
	public boolean complete = false;
	
	public Room(int spawnX, int spawnY, GameHandler h, int lvl) {
		initialX = spawnX;
		initialY = spawnY;
		handler = h;
		this.lvl = lvl;
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
	
	public int getLevel() {
		return lvl;
	}
	
	
	//to see if the conditions for completing the levels are met
	public abstract boolean isComplete (ID objectiveID);
	public abstract void tick();
	public abstract void render(Graphics g);
}