package me.Baconsizzle1738.main;

import java.awt.Graphics;

public class Levels {
	GameHandler handler;
	HUD hud;
	public static int level;
	private Room room;
	
	public Levels(HUD hud, GameHandler handler) {
		this.hud = hud;
		this.handler = handler;
		handler.addObject(new Player(500, 500, ID.Player, -1));
		level = 0;
		//test
		room = new RoomOne(1,1,handler,1);
	}
	
	public void tick() {
		while (Game.gameStarted) {
			
		}
	}
	
	
	public void render(Graphics g) {
		
	}
}
