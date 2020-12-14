package me.Baconsizzle1738.main;

import java.awt.Graphics;
import java.util.ArrayList;

/**
 * Controls leveling system.
 * @author Baconsizzle1738
 *
 */
public class Levels {
	GameHandler handler;
	//HUD hud;
	public static int level;
	private ArrayList<Room> room = new ArrayList<>();
	
	
	/**
	 * Takes handler into class and also creates a list of the levels, including the title screen.
	 * @param handler
	 */
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
	
	/**
	 * Checks status of level completion on every update.
	 */
	public void tick() {
		if (Game.gameStarted) {
			if (room.get(level).isComplete()) {
				//this is for test
				removeLevelObjects();
				level++;
				room.get(level).startLevel();
			}
		}
	}
	
	/**
	 * Removes all level specific objects
	 */
	public void removeLevelObjects() {
		for (int i = 0; i<handler.objects.size(); i++) {
			GameObject temp = handler.objects.get(i);
			if (temp.getlevelID() == level) {
				handler.removeObject(temp);
				i--;
			}
		}
	}
	
	/**
	 * Renders the room/level.
	 * @param g	Graphics board to render <code>Room</code> on.
	 */
	public void render(Graphics g) {
		room.get(level).render(g);
	}
}
