package com.example.project_ad.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project_ad.MyScoreAdapter;
import com.example.project_ad.database.DBScores;
import com.example.project_ad.models.MyScores;
import com.example.project_ad.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ScoresActivity extends AppCompatActivity {

	Button cancel_btn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_scores);

		String taskType = getIntent().getStringExtra("taskType");

		Comparator<MyScores> comparator = new Comparator<MyScores>() {
			@Override
			public int compare(MyScores o1, MyScores o2) {
				Integer score1 = o1.getScore();
				Integer score2 = o2.getScore();
				return score2 - score1;
			}
		};
		ArrayList<MyScores> scores = new DBScores(getApplicationContext()).selectAll(taskType);
		Collections.sort(scores, comparator);

		MyScoreAdapter adapter = new MyScoreAdapter(this, scores);
		ListView lv = (ListView) findViewById(R.id.list1);
		lv.setAdapter(adapter);

		cancel_btn = (Button) findViewById(R.id.cancel_scores_btn);

		cancel_btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}

}
