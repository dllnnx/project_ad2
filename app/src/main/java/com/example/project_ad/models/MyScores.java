package com.example.project_ad.models;

public class MyScores{
	private long id;
	private int score;
	private String date;
	private String taskType;

	public MyScores(long id, int score, String date, String taskType) {
		this.id = id;
		this.score = score;
		this.date = date;
		this.taskType = taskType;
	}

	public MyScores(int score, String date, String taskType) {
		this(-1, score, date, taskType);
	}

	public long getId() {
		return id;
	}

	public int getScore() {
		return score;
	}

	public String getDate() {
		return date;
	}

	public String getTaskType() {
		return taskType;
	}

}
