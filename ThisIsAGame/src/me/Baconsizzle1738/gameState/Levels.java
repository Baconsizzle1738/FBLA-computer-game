package me.Baconsizzle1738.gameState;

import java.awt.Graphics;
import java.util.ArrayList;

import me.Baconsizzle1738.Objects.GameHandler;
import me.Baconsizzle1738.Objects.GameObject;
import me.Baconsizzle1738.main.Game;

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
	 * Takes handler as a parameter and creates a list of the levels.
	 * @param handler
	 */
	public Levels(/* HUD hud, */ GameHandler handler) {
		//this.hud = hud;
		this.handler = handler;
		//handler.addObject(new Player(500, 500, ID.Player, -1, this.handler));
		level = 0;
		//room.add(new RoomBegin(350, 350, handler, 0));
		
		//add levels to the list
		room.add(new RoomBegin(0, 0, handler, 0));
		room.add(new RoomOne(305, 300, handler, 1));
		room.add(new RoomTwo(255, 500, handler, 2));
		room.add(new RoomThree(400, 400, handler, 3));
		//for the first level to initialize
		init = false;
	}
	
	/**
	 * Checks status of level completion on every update.
	 */
	public void tick() {
		if (Game.gameStarted && !Game.isdead && !Game.win) {
			//initiates the first room when the game starts
			if (!init) {
				room.get(level).startLevel();
				init = true;
			}
			
			//check for completion
			if (room.get(level).isComplete()) {
				removeLevelObjects();
				level++;
				System.out.println(level);
				//System.out.println(room.get(level-1).isComplete());
				//check if player has completed all levels, win state is activated when true
				if (level<room.size()) {
					room.get(level).startLevel();
				}
				else {
					Game.win = true;
					Game.takingInput = true;
					resetDefault();
				}
			}
			room.get(level).tick();
		}
		//check if dead
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
	
	/**
	 * resets the level
	 */
	public void resetLevel() {
		room.get(level-1).reset();
	}
	
	/**
	 * Resets everything to default, lives are unaffected.
	 */
	public void resetDefault() {
		
		init = false;
		removeLevelObjects();
		level = 1;
		Game.health = 100;
		Game.keys.reset();
	}
	
	/**
	 * Renders the room/level.
	 * @param g	Graphics board to render <code>Room</code> on.
	 */
	public void render(Graphics g) {
		if (Game.gameStarted && !Game.isdead && !Game.win) {
			room.get(level).render(g);
		}
		
	}
}
