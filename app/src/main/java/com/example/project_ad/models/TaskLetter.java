package com.example.project_ad.models;

import java.io.Serializable;

public class TaskLetter implements Serializable {
    private long id;
    private String word;
    private int missingLetter;
    private String distractors;

    public TaskLetter(String word, int missingLetter, String distractors) {
        this(-1, word, missingLetter, distractors);
    }

    public TaskLetter(long id, String word, int missingLetter, String distractors){
        this.id = id;
        this.word = word;
        this.missingLetter = missingLetter;
        this.distractors = distractors;
    }

    public boolean checkAnswer(String answerLetter){
        return answerLetter.equals(word.charAt(missingLetter) + "");
    }

    public String getWord() {
        return word;
    }

    public int getMissingLetter() {
        return missingLetter;
    }

    public String getDistractors() {
        return distractors;
    }

    public long getId() {
        return id;
    }
}
