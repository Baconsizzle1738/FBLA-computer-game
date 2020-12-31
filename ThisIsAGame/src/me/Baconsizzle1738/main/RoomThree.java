package me.Baconsizzle1738.main;

import java.awt.Graphics;

public class RoomThree extends Room{
	
	Player p;

	public RoomThree(int spawnX, int spawnY, GameHandler h, int lvl) {
		super(spawnX, spawnY, h, lvl);
		
		p = new Player(spawnX, spawnY, ID.Player, lvl, handler);
		
	}

	@Override
	public void startLevel() {
		handler.addObject(p);
	}

	@Override
	public void reset() {
		
	}

	@Override
	public boolean isComplete() {
		
		return false;
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		
	}

}
