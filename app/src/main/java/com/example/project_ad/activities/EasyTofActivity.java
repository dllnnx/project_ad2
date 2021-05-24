package com.example.project_ad.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.project_ad.R;
import com.example.project_ad.database.DBController;
import com.example.project_ad.database.DBScores;
import com.example.project_ad.database.DBTaskTof;
import com.example.project_ad.models.TaskTof;

import java.util.ArrayList;

public class EasyTofActivity extends AppCompatActivity {

	DBTaskTof dbTaskTof;
	Button trueBtn, falseBtn, cancelBtn;
	TextView enWordTv, ruWordTv, countTv;
	ArrayList<TaskTof> tasks = new ArrayList<>();
	int[] count = {0};
	int amount;
	TaskTof curTask;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tof);

		dbTaskTof = new DBTaskTof(getApplicationContext());

		for(TaskTof task : dbTaskTof.selectAll()){
			tasks.add(new TaskTof(task.getRuWord(), task.getEnWord(), task.getBool()));
		}

		trueBtn = (Button) findViewById(R.id.true_btn);
		falseBtn = (Button) findViewById(R.id.false_btn);
		cancelBtn = (Button) findViewById(R.id.tofCancel_btn);
		enWordTv = (TextView) findViewById(R.id.enWord_tv);
		ruWordTv = (TextView) findViewById(R.id.ruWord_tv);
		countTv = (TextView) findViewById(R.id.count_tv);
		
		countTv.setText("Счёт: " + count[0]);

		nextTask();

		trueBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (curTask.getBool()){
					count[0]++;
				}
				if(!tasks.isEmpty()){
					tasks.remove(0);
					nextTask();
				}
			}
		});

		falseBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(!curTask.getBool()){
					count[0]++;
				}
				if(!tasks.isEmpty()){
					tasks.remove(0);
					nextTask();
				}
			}
		});

		cancelBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});

		amount = tasks.size();
		nextTask();
	}

	public void nextTask(){
		if (!tasks.isEmpty()){
			curTask = tasks.get(0);
			enWordTv.setText(curTask.getEnWord());
			ruWordTv.setText(curTask.getRuWord());
			countTv.setText("Счёт: " + count[0]);
		}else{
//			float res = (float)count[0]/amount * 100;
//			countTv.setText("Задания закончились!\nРезультат: " + (int) res + "%");
			DBScores scores = new DBScores(getApplicationContext());
			scores.insert(count[0], getString(R.string.task_type_tof));
			Intent intent = new Intent(EasyTofActivity.this, TaskResultActivity.class);
			intent.putExtra("count", amount);
			intent.putExtra("correct", count[0]);
			startActivity(intent);
			finish();
		}
	}
}
