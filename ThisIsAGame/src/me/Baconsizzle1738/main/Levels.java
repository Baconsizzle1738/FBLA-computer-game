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
	
	//for the first level to initiate level progression
	private boolean init;
	
	
	/**
	 * Takes handler into class and also creates a list of the levels.
	 * @param handler
	 */
	public Levels(/* HUD hud, */ GameHandler handler) {
		//this.hud = hud;
		this.handler = handler;
		//handler.addObject(new Player(500, 500, ID.Player, -1, this.handler));
		level = 1;
		//test
		//room.add(new RoomBegin(350, 350, handler, 0));
		room.add(new RoomOne(305, 300, handler, 1));
		room.add(new RoomTwo(255, 500, handler, 2));
		room.add(new RoomThree(400, 400, handler, 3));
		init = false;
	}
	
	/**
	 * Checks status of level completion on every update.
	 */
	public void tick() {
		if (Game.gameStarted && !Game.isdead) {
			if (!init) {
				room.get(level-1).startLevel();
				init = true;
			}
			if (room.get(level-1).isComplete()) {
				//this is for test
				removeLevelObjects();
				level++;
				room.get(level-1).startLevel();
			}
			room.get(level-1).tick();
		}
		if (Game.isdead) {
			resetDefault();
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
	
	public void resetLevel() {
		room.get(level-1).reset();
	}
	
	public void resetDefault() {
		
		init = false;
		removeLevelObjects();
		level = 1;
		
	}
	
	/**
	 * Renders the room/level.
	 * @param g	Graphics board to render <code>Room</code> on.
	 */
	public void render(Graphics g) {
		if (Game.gameStarted && !Game.isdead) {
			room.get(level-1).render(g);
		}
		
	}
}
