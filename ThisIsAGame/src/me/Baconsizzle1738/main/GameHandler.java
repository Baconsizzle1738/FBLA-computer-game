package me.Baconsizzle1738.main;

import java.awt.Graphics;
import java.util.Iterator;
import java.util.LinkedList;

public class GameHandler {
	
	//list of all objects stored here
	LinkedList<GameObject> objects = new LinkedList<>();
	
	
	//update all objects
	public void tick() {
		Iterator<GameObject> it = objects.iterator();
		while (it.hasNext()) {
			GameObject temp = it.next();
			temp.tick();
		}
	}
	
	public void render(Graphics g) {
		Iterator<GameObject> it = objects.iterator();
		while (it.hasNext()) {
			GameObject temp = it.next();
			temp.render(g);
		}
	}
	
	public void addObject(GameObject o) {
		objects.add(o);
	}
	
	public void removeObject(GameObject o) {
		objects.remove(o);
	}
}