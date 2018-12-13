package model;

import java.util.Date;

public class Film {
	private String name;
	private Date date;
	private int score;
	
	public Film()
	{
		super();
	}

	public Film(String name, Date date, int score) {
		super();
		this.name = name;
		this.date = date;
		this.score = score;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	
}
