package me.Baconsizzle1738.main;


//yay git works
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
//import java.util.Random;

public class Game extends Canvas implements Runnable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//window dimensions, will remain static
	public static final int WIDTH = 1000, HEIGHT = 600;
	
	private Thread thread;
	private boolean running = false;
	public static boolean gameStarted = false;
	
	//for testing vvv
	//private Random r;
	
	
	//the Handler
	GameHandler handler;
	//the HUD
	HUD hud;
	//the rooms
	Levels levels;
	//Room room;
	
	
	public Game() {
		//make the handler first to prevent some possible errors
		handler = new GameHandler();
		
		levels = new Levels(hud,handler);
		hud = new HUD(levels);
		//handler.addObject(new Player(300, 300, ID.Player, -1));
		
		
		new Window(WIDTH,HEIGHT,"Game",this);
		
		//this for test
		//handler.addObject(new Player(300, 300, ID.Player, ID.AllLevels));
		//handler.addObject(new StaticEnemy(500, 500, ID.Enemy, ID.Level1 ,50 , 50, 3, 4));
		//handler.addObject(new StaticEnemy(70, 70, ID.Enemy, ID.Level1 ,300 , 2));
		//handler.addObject(new StaticEnemy(70, 70, ID.Enemy, ID.Level1 ,300 , 2, 0));
		
		//takes keyboard inputs
		this.addKeyListener(new GameKeyListener(handler));
		//takes mouse inputs
		this.addMouseListener(new GameMouseListener(hud));
		
		
		
		//this fixes annoying window issue where you have to click on it for it to work
		this.requestFocusInWindow();
	}
	
	public synchronized void start() {
		//starts game
		//single thread
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop() {
		//kills game
		try {
			//kills thread
			thread.join();
			running = false;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void run() {
		//game timer/runner
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000/amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while (running) {
			long now = System.nanoTime();
			delta +=(now-lastTime)/ns;
			lastTime = now;
			while (delta >=1) {
				tick();
				delta--;
			}
			if (running) {
				render();
			}
			frames++;
			
			if (System.currentTimeMillis()-timer > 1000) {
				timer+=1000;
				System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();
		
	}
	
	//tick updates the render
	public void tick() {
		//calls update tick method in every object
		handler.tick();
		hud.tick();
	}
	
	//actually renders the stuff.
	public void render() {
		//getBufferStrategy() is from canvas implementation
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		//draw a rect over entire screen so no flashing
		Color bkg = new Color(50,50,50);
		g.setColor(bkg);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		//render game objects and HUD here
		handler.render(g);
		hud.render(g);
		
		g.dispose();
		bs.show();
	}
	
	//restrains a variable within a certain limit
	public static int clamp(int var, int min, int max) {
		if (var>max) {
			return max;
		}
		else if (var<min) {
			return min;
		}
		else {
			return var;
		}
	}
	
	//make the game start in main
	public static void main(String[] args) {
		new Game();
	}
	
}
