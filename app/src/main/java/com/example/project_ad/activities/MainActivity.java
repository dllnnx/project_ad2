package com.example.project_ad.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.project_ad.R;

public class MainActivity extends AppCompatActivity {

	TextView startText;
	Button startBtn, scoresBtn, exitBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		startText = (TextView)findViewById(R.id.start_text);
		startBtn = (Button)findViewById(R.id.btn_start);
		scoresBtn = (Button)findViewById(R.id.btn_scores);
		exitBtn = (Button)findViewById(R.id.btn_exit);


		startBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this, ChoiceActivity.class);
				startActivity(i);
			}
		});

		scoresBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this, SettingsActivity.class);
				startActivity(i);
			}
		});

		exitBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});

	}
}