package com.example.project_ad.internet;

import com.example.project_ad.models.TaskLetter;
import com.example.project_ad.models.TaskTof;

import java.util.ArrayList;

public class Answer {
    private ArrayList<TaskTof> taskTofs;
    private ArrayList<TaskLetter> taskLetters;

    public ArrayList<TaskTof> getTaskTofs() {
        return taskTofs;
    }

    public void setTaskTofs(ArrayList<TaskTof> taskTofs) {
        this.taskTofs = taskTofs;
    }

    public ArrayList<TaskLetter> getTaskLetters() {
        return taskLetters;
    }

    public void setTaskLetters(ArrayList<TaskLetter> taskLetters) {
        this.taskLetters = taskLetters;
    }
}
