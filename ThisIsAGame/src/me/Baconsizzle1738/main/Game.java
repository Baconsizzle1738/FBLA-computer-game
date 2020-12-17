package me.Baconsizzle1738.main;


//yay git works
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
//import java.util.Random;

/**
 * This game is a puzzle/riddle game where the player is trapped in a lot of
 * rooms and the only way to escape is to solve the puzzle/riddle in the room,
 * some of which will be a reference to other games. Every room has a different
 * puzzle that the player has to solve, and upon completion
 * will allow the player to move to the next room where there is another puzzle to solve.
 * While solving the puzzle, the player will encounter enemies that will hurt them,
 * some more than others.
 * 
 * 
 * <code>Game</code> is the main class.
 * 
 * @author Baconsizzle1738
 *
 */

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
	
	
	public static int health = 100;
	
	//for testing vvv
	//private Random r;
	
	
	//the Handler
	GameHandler handler;
	//the HUD
	HUD hud;
	//the rooms
	Levels levels;
	//Room room;
	
	/**
	 * Constructor of <code>Game</code> class instantiates the game handler class <code>handler</code>,
	 * the leveling system class <code>levels</code>, and the HUD class <code>hud</code>, and also adds 
	 * keyboard and mouse listeners for the game.
	 */
	public Game() {
		//make the handler first to prevent some possible errors
		handler = new GameHandler();
		
		//handles leveling system
		levels = new Levels(/*hud,*/ handler);
		
		//heads up display
		hud = new HUD(levels);
		
		//handler.addObject(new Player(300, 300, ID.Player, -1));
		
		
		new Window(WIDTH,HEIGHT,"Game",this);
		this.requestFocusInWindow();
		
		//this for test
		//handler.addObject(new Player(300, 300, ID.Player, ID.AllLevels));
		//handler.addObject(new StaticEnemy(500, 500, ID.Enemy, ID.Level1 ,50 , 50, 3, 4));
		//handler.addObject(new StaticEnemy(70, 70, ID.Enemy, ID.Level1 ,300 , 2));
		//handler.addObject(new StaticEnemy(70, 70, ID.Enemy, ID.Level1 ,300 , 2, 0));
		
		//takes keyboard inputs
		this.addKeyListener(new GameKeyListener(handler, levels));
		//takes mouse inputs
		this.addMouseListener(new GameMouseListener(hud));
		
		
		
		//this fixes annoying window issue where you have to click on it for it to work
		
	}
	
	/**
	 * The <code>start</code> method starts the game.
	 */
	public synchronized void start() {
		//starts game
		//single thread
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	/**
	 * The <code>stop</code> method kills the game.
	 */
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
	
	/**
	 * The <code>run</code> method continuously runs the game by calling
	 * <code>tick</code> and <code>render</code>.
	 */
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
	
	/**
	 * The <code>tick</code> method updates all of the game logic when called.
	 */
	public void tick() {
		//calls update tick method in every object
		handler.tick();
		hud.tick();
		levels.tick();
	}
	
	//actually renders the stuff.
	/**
	 * The <code>render</code> method draws the game objects on the screen so long as the
	 * game is running.
	 */
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
		
		//render game objects, levels, and HUD here
		handler.render(g);
		hud.render(g);
		levels.render(g);
		
		g.dispose();
		bs.show();
	}
	/**
	 * The <code>clamp</code> method restrains a changing variable to a certain <code>min</code>
	 * and <code>max</code> limit.
	 * 
	 * @param var	This is the input variable.
	 * @param min	This is the minimum value the variable can be.
	 * @param max	This is the maximum value the variable can be.
	 * @return		Returns <code>var</code> if the input is within the constrains, otherwise it will return <code>min</code> or <code>max</code> of it is to low or high respectively.
	 */
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
	
	/**
	 * This is the main method.
	 * @param args	nothing here
	 */
	//make the game start in main
	public static void main(String[] args) {
		new Game();
	}
	
}
