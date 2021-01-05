package me.Baconsizzle1738.main;

/**
 * The data for each completion of the game is stored here.
 * @author Baconsizzle1738
 *
 */
public class ScoreData implements Comparable<ScoreData>{
	
	private String name;
	
	private int score;
	
	/**
	 * Stores the name of the player and the score they recieved
	 * @param name	player name
	 * @param score	player score
	 */
	public ScoreData(String name, int score) {
		this.name = name;
		this.score = score;
	}
	
	/**
	 * Takes the data in the format stored in the scores file
	 * @param data	String of raw data for the score
	 */
	public ScoreData (String data) {
		//splits the string to separate the player name and their score
		String[] dat = data.split(" ");
		
		this.name = dat[0];
		this.score = Integer.parseInt(dat[1]);
	}
	
	/**
	 * 
	 * @return name of the player
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @return	score of the player
	 */
	public int getScore() {
		return score;
	}
	
	//these will be put into a tree set to automatically sort the data.
	@Override
	public int compareTo(ScoreData o) {
		if (this.getScore() >= o.getScore()) {
			return -1;
		}
		return 1;
	}
}
