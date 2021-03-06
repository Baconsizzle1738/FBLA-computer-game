package me.Baconsizzle1738.Objects;

import java.awt.Graphics;
//import java.util.Iterator;
import java.util.LinkedList;

/**
 * Where all <code>GameObject</code> objects are stored and updated.
 * @author Baconsizzle1738
 *
 */

public class GameHandler {
	
	//list of all objects stored here
	public LinkedList<GameObject> objects = new LinkedList<>();
	
	/**
	 * Updates logic for all objects.
	 */
	//update all objects
	public void tick() {
		for (int i = 0; i<objects.size(); i++) {
			objects.get(i).tick();
		}
	}
	
	/**
	 * Updates render for all objects.
	 * @param g	Graphics board to render on
	 */
	public void render(Graphics g) {
		for (int i = 0; i<objects.size(); i++) {
			objects.get(i).render(g);
		}
	}
	
	/**
	 * Adds a <code>GameObject</code> to the <code>Handler</code>.
	 * @param o	<code>GameObject</code> to be added.
	 */
	public void addObject(GameObject o) {
		objects.add(o);
	}
	
	/**
	 * Removes a <code>GameObject</code> from the <code>Handler</code>.
	 * @param o	<code>GameObject</code> to be removed.
	 */
	public void removeObject(GameObject o) {
		System.out.println("removed");
		objects.remove(o);
	}
}