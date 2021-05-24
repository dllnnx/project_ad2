package com.example.project_ad;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.project_ad.database.DBController;
import com.example.project_ad.database.DBTaskLetter;
import com.example.project_ad.models.TaskLetter;

import java.util.ArrayList;


public class SpellingView extends View {
	public interface OnPostAction{
		void doOnPostAction();
	}

	private float mousePosX = 0, mousePosY = 0;
	private TaskView taskView;
	boolean isInit = false;
	private int count = 0;

	int taskCount = 0;

	ArrayList<TaskLetter> taskLetters = new ArrayList<>();

	OnPostAction onPostAction = new OnPostAction() {
		@Override
		public void doOnPostAction() {

		}
	};

	int taskTextColor, answerTextColor, taskBackgroundColor, answerBackgroundColor;


	public SpellingView(Context context, AttributeSet attrs){
		super(context, attrs);

		TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.SpellingView);

		taskTextColor = typedArray.getColor(R.styleable.SpellingView_task_text_color, Color.BLACK);
		answerTextColor = typedArray.getColor(R.styleable.SpellingView_answer_text_color, Color.BLACK);
		taskBackgroundColor = typedArray.getColor(R.styleable.SpellingView_task_bgcolor, Color.argb(100, 149, 124, 243));
		answerBackgroundColor = typedArray.getColor(R.styleable.SpellingView_answer_bgcolor, Color.argb(100, 72, 57, 129));

		DBTaskLetter dbTaskLetter = new DBTaskLetter(getContext());
		taskLetters = dbTaskLetter.selectAll();
		taskCount = taskLetters.size();
	}

	public void setOnPostAction(OnPostAction onPostAction){
		this.onPostAction = onPostAction;
	}

	public int getCount(){
		return count;
	}

	public int getTaskCount(){
		return taskCount;
	}

	public void init(){
		if (taskLetters.size() > 0){
			taskView = new TaskView(taskLetters.remove(0), getWidth(), getHeight());
			taskView.setAnswerLetterColor(answerTextColor);
			taskView.setAnswerBackgroundColor(answerBackgroundColor);
			taskView.setLetterColor(taskTextColor);
			taskView.setLetterBackgroundColor(taskBackgroundColor);
			isInit = true;
		}else{
			onPostAction.doOnPostAction();
		}
	}


	@Override
	protected void onDraw(Canvas canvas) {
		if (!isInit){
			init();
		}
		taskView.draw(canvas);
	}


	@Override
	public boolean onTouchEvent(MotionEvent event) {
		mousePosX = event.getX();
		mousePosY = event.getY();

		if (MotionEvent.ACTION_DOWN == event.getAction()){
			taskView.up((int) mousePosX, (int) mousePosY);
		}else if (MotionEvent.ACTION_UP == event.getAction()){
			int ans = taskView.checkAnswer();
			if (ans >= 0){
				count += ans;
				init();
			}
			//taskView.down();
		}else if (MotionEvent.ACTION_MOVE == event.getAction()){
			taskView.move((int) mousePosX, (int) mousePosY);
		}
		invalidate();
		return true;
	}
}