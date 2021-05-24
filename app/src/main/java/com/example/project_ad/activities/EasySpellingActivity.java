package com.example.project_ad.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project_ad.R;
import com.example.project_ad.SpellingView;
import com.example.project_ad.database.DBScores;

public class EasySpellingActivity extends AppCompatActivity {
	SpellingView spView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_easyspelling);

		spView = (SpellingView)findViewById(R.id.spelling_view);
		spView.setOnPostAction(new SpellingView.OnPostAction() {
			@Override
			public void doOnPostAction() {
				int result = spView.getCount();
				int count = spView.getTaskCount();

				DBScores scores = new DBScores(getApplicationContext());
				scores.insert(result, getString(R.string.task_type_letter));

				Intent intent = new Intent(EasySpellingActivity.this, TaskResultActivity.class);
				intent.putExtra("count", count);
				intent.putExtra("correct", result);
				startActivity(intent);

				finish();
			}
		});


	}

}
