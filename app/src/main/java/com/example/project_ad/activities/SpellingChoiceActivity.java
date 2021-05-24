package com.example.project_ad.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project_ad.R;

public class SpellingChoiceActivity extends AppCompatActivity {
	Button easySp_btn;
	Button cancel_btn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_spelling);

		easySp_btn = (Button)findViewById(R.id.easysp_btn);
		cancel_btn = (Button)findViewById(R.id.cancelsp_btn);

		easySp_btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(SpellingChoiceActivity.this, EasySpellingActivity.class);
				startActivity(i);
			}
		});

		cancel_btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});

		Button button = findViewById(R.id.letter_btn_scores);
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(SpellingChoiceActivity.this, ScoresActivity.class);
				intent.putExtra("taskType", getString(R.string.task_type_letter));
				startActivity(intent);
				finish();
			}
		});

	}
}
