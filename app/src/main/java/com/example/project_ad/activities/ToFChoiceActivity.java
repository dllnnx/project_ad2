package com.example.project_ad.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project_ad.R;

public class ToFChoiceActivity extends AppCompatActivity {

	Button easyTof_btn, cancel_btn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tofchoice);

		easyTof_btn = (Button) findViewById(R.id.easytof_btn);
		cancel_btn = (Button) findViewById(R.id.canceltof_btn);

		Button button = findViewById(R.id.scores_tof_btn);
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(ToFChoiceActivity.this, ScoresActivity.class);
				intent.putExtra("taskType", getString(R.string.task_type_tof));
				startActivity(intent);
				finish();
			}
		});

		easyTof_btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(ToFChoiceActivity.this, EasyTofActivity.class);
				startActivity(i);
			}
		});

		cancel_btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});

	}

}
