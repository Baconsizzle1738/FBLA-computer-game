package me.Baconsizzle1738.main;

import java.awt.Graphics;
import java.util.ArrayList;

public class Levels {
	GameHandler handler;
	//HUD hud;
	public static int level;
	private ArrayList<Room> room = new ArrayList<>();
	
	public Levels(/* HUD hud, */ GameHandler handler) {
		//this.hud = hud;
		this.handler = handler;
		handler.addObject(new Player(500, 500, ID.Player, -1, this.handler));
		level = 0;
		//test
		room.add(new RoomBegin(350, 350, handler, 0));
		room.add(new RoomOne(300, 300, handler, 1));
		room.add(new RoomTwo(200, 200, handler, 2));
		room.get(0).SetPlayerSpawn();
	}
	
	public void tick() {
		if (Game.gameStarted) {
			if (room.get(level).isComplete()) {
				//this is for test
				removeLevelObjects();
				level++;
				room.get(level).startLevel();
				room.get(level).SetPlayerSpawn();
			}
		}
	}
	
	//removes all level specific objects
	public void removeLevelObjects() {
		for (int i = 0; i<handler.objects.size(); i++) {
			GameObject temp = handler.objects.get(i);
			if (temp.getlevelID() == level) {
				handler.removeObject(temp);
				i--;
			}
		}
	}
	
	
	public void render(Graphics g) {
		
	}
}
