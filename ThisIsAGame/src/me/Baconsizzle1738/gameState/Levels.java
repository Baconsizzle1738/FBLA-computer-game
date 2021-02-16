package me.Baconsizzle1738.gameState;

//github reee
import java.awt.Graphics;
import java.util.ArrayList;

import me.Baconsizzle1738.Objects.GameHandler;
import me.Baconsizzle1738.Objects.GameObject;
import me.Baconsizzle1738.main.Game;
import me.Baconsizzle1738.main.HUD;

/**
 * Controls leveling system.
 * @author Baconsizzle1738
 *
 */
public class Levels {
	GameHandler handler;
	HUD hud;
	public int level;
	private int numLevels, controlLevel, leadLevel;
	private ArrayList<Room> room, transition;
	
	//for the first level to initiate level progression
	private boolean init;
	
	
	/**
	 * Takes handler as a parameter and creates a list of the levels.
	 * @param handler
	 */
	public Levels(HUD hud, GameHandler handler) {
		//this.hud = hud;
		this.handler = handler;
		//handler.addObject(new Player(500, 500, ID.Player, -1, this.handler));
		level = 0;
		numLevels = 3;
		controlLevel = 4;
		leadLevel = 5;
		
		//room.add(new RoomBegin(350, 350, handler, 0));
		room = new ArrayList<>();
		transition = new ArrayList<>();
		//add levels to the list
		room.add(new RoomBegin(0, 0, handler, 0));
		room.add(new RoomOne(305, 300, handler, 1));
		room.add(new RoomTwo(255, 500, handler, 2));
		room.add(new RoomThree(400, 400, handler, 3));
		
		//the main menu rooms
		room.add(new RoomControls(0, 0, handler, controlLevel));
		
		
		//for the first level to initialize
		init = false;
		
		//start with the main menu screen.
		room.get(0).startLevel();
	}
	
	/**
	 * Checks status of level completion on every update.
	 */
	public void tick() {
		//if (Game.gameStarted && !Game.isdead && !Game.win) {
		//initiates the first room when the game starts
//		if (!init) {
//			room.get(level).startLevel();
//			init = true;
//		}
		
		
		if (level == 0) {
			//go to controls page
			if (((RoomBegin) room.get(0)).goControls()) {
				((RoomBegin) room.get(0)).getButton("controls").setRelease(false);
				removeLevelObjects();
				level = controlLevel;
				room.get(level).startLevel();
				//System.out.println(level);
				//System.out.println("ree");
			}
			
			//go to leaderboards page
			if (((RoomBegin) room.get(0)).goLead()) {
				((RoomBegin) room.get(0)).getButton("lead").setRelease(false);
				removeLevelObjects();
				level = leadLevel;
				room.get(level).startLevel();
			}
		}
		
		//check for completion
		if (room.get(level).isComplete()) {
			removeLevelObjects();
			
			//if the room is at a menu screen then return to main menu, otherwise advance level.
			if (level == controlLevel || level == leadLevel) {
				System.out.println(level);
				((RoomControls)room.get(controlLevel)).getButton().setRelease(false);
				level = 0;
			}
			
			else {
				level++;
			}
			
			//System.out.println(level);
			//System.out.println(room.get(level-1).isComplete());
			//check if player has completed all levels, win state is activated when true
			if (level<=numLevels) {
				room.get(level).startLevel();
			}
			else {
				Game.win = true;
				Game.takingInput = true;
				resetDefault();
			}
		}
		room.get(level).tick();
		//System.out.println(((RoomBegin) room.get(0)).goControls());
		//System.out.println(level);
		//}
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
		room.get(level).reset();
	}
	
	/**
	 * Resets everything to default, lives are unaffected.
	 */
	public void resetDefault() {
		
		init = false;
		removeLevelObjects();
		level = 0;
		Game.health = 100;
		Game.keys.reset();
	}
	
	/**
	 * 
	 * @param hud
	 */
	public void setHUD(HUD hud) {
		this.hud = hud;
	}
	/**
	 * Renders the room/level.
	 * @param g	Graphics board to render <code>Room</code> on.
	 */
	public void render(Graphics g) {
		room.get(level).render(g);
	}
}
