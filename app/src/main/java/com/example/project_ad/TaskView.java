package com.example.project_ad;


import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;


import com.example.project_ad.models.TaskLetter;

import java.util.ArrayList;


public class TaskView {
	public static final int MARGIN = 5;
	public static final int ANSWER_COUNT = 4;

	private int screenWidth, screenHeight;
	private Paint letterPaint;
	private Paint backgroundPaint;
	private Paint letterBackgroundPaint;
	private Paint answerBackgroundPaint;
	private Paint answerLetterPaint;
	private ArrayList<LetterView> letterViews, answers;

	private TaskLetter curTaskLetter;

	public int checkAnswer() {
		for (LetterView letterView : answers){
			if (letterView.isTaken){
				for(LetterView let: letterViews){
					if (let.isIn(letterView.posX, letterView.posY)){
						if (curTaskLetter.checkAnswer(letterView.letter)){
							return 1;
						}else {
							return 0;
						}
					}
				}
				down();
			}
		}

		return -1;
	}

	class LetterView {
		boolean isTaken = false;
		int dX = 0, dY = 0;
		int oldPosX = 0, oldPosY = 0;
		String letter;
		Paint backgroundPaint, letterPaint;
		int posX, posY, size;


		public LetterView(String letter, int posX, int posY, int size) {
			this.letter = letter;
			this.posX = posX;
			this.posY = posY;
			this.size = size;
			oldPosX = posX;
			oldPosY = posY;
			this.backgroundPaint = new Paint();
			this.letterPaint = new Paint();
		}


		void draw(Canvas canvas){
			canvas.drawRect(posX + TaskView.MARGIN +  dX, posY + dY,
					posX + dX + size - 2 * TaskView.MARGIN, posY + dY + size,
					this.backgroundPaint);
			this.letterPaint.setTextSize((int) (0.9 * size));
			canvas.drawText(
					letter, posX + dX + size / 4,
					posY + dY + size - TaskView.MARGIN, this.letterPaint);
		}


		public void setBackgroundPaint(Paint backgroundPaint) {
			this.backgroundPaint = backgroundPaint;
		}


		public void setLetterPaint(Paint letterPaint) {
			this.letterPaint = letterPaint;
		}


		public boolean isIn(int posX, int posY){
			return this.posX <= posX && posX <= this.posX + size &&
					this.posY <= posY && posY <= this.posY + size;
		}


		public void take(int posX, int posY){
			if (isIn(posX, posY)){
				isTaken = true;
				dX = - posX + this.posX;
				dY = - posY + this.posY;
				this.posX -= dX;
				this.posY -= dY;
			}
		}


		public void release(){
			isTaken = false;
			posX = oldPosX;
			posY = oldPosY;
			dX = 0;
			dY = 0;
		}


		public void setXY(int posX, int posY){
			this.posY = posY;
			this.posX = posX;
		}
	}


	public TaskView(TaskLetter taskLetter, int screenWidth, int screenHeight){
		this(taskLetter.getWord(), taskLetter.getMissingLetter(),
				taskLetter.getDistractors(), screenWidth, screenHeight);
	}


	public TaskView(String word, int missingLatter, String distractors, int screenWidth, int screenHeight) {
		this.curTaskLetter = new TaskLetter(word, missingLatter, distractors);
		this.screenHeight = screenHeight;
		this.screenWidth = screenWidth;

		this.backgroundPaint = new Paint();
		this.backgroundPaint.setColor(Color.WHITE);

		this.answerBackgroundPaint = new Paint();
		this.answerBackgroundPaint.setColor(Color.RED);

		this.letterBackgroundPaint = new Paint();
		this.letterBackgroundPaint.setColor(Color.GREEN);

		this.letterPaint = new Paint();
		this.letterPaint.setColor(Color.BLACK);
		this.letterPaint.setStyle(Paint.Style.FILL);

		this.answerLetterPaint = new Paint();
		this.answerLetterPaint.setColor(Color.GRAY);
		this.answerLetterPaint.setStyle(Paint.Style.FILL);

		letterViews = new ArrayList<>();
		int size = Math.min(this.screenHeight, this.screenWidth) /
				Math.max(this.curTaskLetter.getWord().length(), 4);

		for (int i = 0; i < this.curTaskLetter.getWord().length(); i++){
			String let = this.curTaskLetter.getWord().charAt(i) + "";
			if (i == this.curTaskLetter.getMissingLetter()){
				let = "";
			}
			LetterView letterView = new LetterView(let, i * size, 0, size);
			letterView.setBackgroundPaint(this.letterBackgroundPaint);
			letterView.setLetterPaint(this.letterPaint);
			letterViews.add(letterView);
		}

		answers = new ArrayList<>();
		int startPos = (screenWidth - TaskView.ANSWER_COUNT * size) / 2;
		for(int i = 0; i < TaskView.ANSWER_COUNT; i++){
			LetterView letterView = new LetterView(curTaskLetter.getDistractors().charAt(i) + "", startPos + i * size, size * 2, size);
			letterView.setBackgroundPaint(this.answerBackgroundPaint);
			letterView.setLetterPaint(this.answerLetterPaint);
			answers.add(letterView);
		}
	}


	public void draw(Canvas canvas){
		for(int i = 0; i < this.letterViews.size(); i++){
			this.letterViews.get(i).draw(canvas);
		}
		for(int i = 0; i < this.answers.size(); i++){
			this.answers.get(i).draw(canvas);
		}
	}


	public void up(int mousePosX, int mousePosY){
		for (LetterView letterView : answers) {
			if (letterView.isIn(mousePosX, mousePosY)) {
				letterView.take(mousePosX, mousePosY);
			}
		}
	}


	public void down(){
		for (LetterView letterView : answers){
			letterView.release();
		}
	}


	public void move(int posX, int posY) {
		for (LetterView letterView : answers){
			if (letterView.isTaken){
				letterView.setXY(posX, posY);
			}
		}
	}


	public void setLetterColor(int color) {
		this.letterPaint.setColor(color);
	}


	public void setLetterBackgroundColor(int color) {
		this.letterBackgroundPaint.setColor(color);
	}


	public void setAnswerBackgroundColor(int color) {
		this.answerBackgroundPaint.setColor(color);
	}


	public void setAnswerLetterColor(int color) {
		this.answerLetterPaint.setColor(color);
	}
}