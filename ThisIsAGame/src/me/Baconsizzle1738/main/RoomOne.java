package me.Baconsizzle1738.main;

import java.awt.Graphics;

public class RoomOne extends Room {

	public RoomOne(int spawnX, int spawnY, GameHandler h, int lvl) {
		super(spawnX, spawnY, h, lvl);
		
	}

	@Override
	public boolean isComplete(ID objectiveID) {
		return false;
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		
	}

}
