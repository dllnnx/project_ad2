package com.example.project_ad.models;

import java.io.Serializable;

public class TaskTof implements Serializable {
	private long id;
	private String ruWord, enWord;
	private boolean bool;

	public TaskTof(long id, String ruWord, String enWord, boolean bool) {
		this.id = id;
		this.ruWord = ruWord;
		this.enWord = enWord;
		this.bool = bool;
	}

	public TaskTof(long id, String ruWord, String enWord, int bool){
		this(id, ruWord, enWord, 0 != bool);
		System.out.println(ruWord + " " + bool);
	}

    public TaskTof(String ruWord, String enWord, boolean b) {
		this(-1, ruWord, enWord, b);
    }

    public long getId() {
		return id;
	}

	public String getRuWord() {
		return ruWord;
	}

	public String getEnWord() {
		return enWord;
	}

	public boolean getBool() {
		return bool;
	}
}
