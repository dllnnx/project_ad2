package com.example.project_ad.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project_ad.R;

public class ChoiceActivity extends AppCompatActivity {

	TextView choice_text;
	Button goToSpelling_btn;
	Button goToTof_btn;
	Button cancel_btn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_choice);

		choice_text = (TextView) findViewById(R.id.choice_txt);
		goToSpelling_btn = (Button) findViewById(R.id.spelling_btn);
		goToTof_btn = (Button) findViewById(R.id.tof_btn);
		cancel_btn = (Button) findViewById(R.id.cancel_choice_btn);

		goToSpelling_btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(ChoiceActivity.this, SpellingChoiceActivity.class);
				startActivity(i);
			}
		});

		goToTof_btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(ChoiceActivity.this, ToFChoiceActivity.class);
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
