package model;

import java.util.Date;

public class Film {
	private String name;
	private String date;
	private String score;
	
	public Film()
	{
		super();	
	}

	public Film(String name, String date, String score) {
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}
	
	
}
