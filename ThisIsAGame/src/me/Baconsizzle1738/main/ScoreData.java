package me.Baconsizzle1738.main;

public class ScoreData implements Comparable<ScoreData>{
	
	private String name;
	
	private int score;
	
	public ScoreData(String name, int score) {
		this.name = name;
		this.score = score;
	}
	
	public ScoreData (String data) {
		String[] dat = data.split(" ");
		
		this.name = dat[0];
		this.score = Integer.parseInt(dat[1]);
	}
	
	public String getName() {
		return name;
	}

	public int getScore() {
		return score;
	}
	
	@Override
	public int compareTo(ScoreData o) {
		if (this.getScore() >= o.getScore()) {
			return -1;
		}
		return 1;
	}
}
