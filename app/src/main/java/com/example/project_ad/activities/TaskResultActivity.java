package com.example.project_ad.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project_ad.R;

public class TaskResultActivity extends AppCompatActivity {

    Button cancel_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_result);

        Intent intent = getIntent();
        int taskCount = intent.getIntExtra("count", 0);
        int correctCount = intent.getIntExtra("correct", 0);
        int incorrectCount = taskCount - correctCount;
        float percent = 0f;
        if (taskCount != 0) percent = 100f * correctCount / taskCount;

        int mark = 2;
        if (percent >= 50) mark = 3;
        if (percent >= 70) mark = 4;
        if (percent >= 90) mark = 5;

        TextView taskCountTextView = findViewById(R.id.all_count_tw);
        TextView correctCountTextView = findViewById(R.id.correct_count_tw);
        TextView incorrectCountTextView = findViewById(R.id.incorrect_count_tw);
        TextView percentTextView = findViewById(R.id.percent_tw);
        TextView markTextView = findViewById(R.id.mark_tw);

        taskCountTextView.setText(String.valueOf(taskCount));
        correctCountTextView.setText(String.valueOf(correctCount));
        incorrectCountTextView.setText(String.valueOf(incorrectCount));
        percentTextView.setText(String.format("%.0f", percent) + "%");
        markTextView.setText(String.valueOf(mark));


        cancel_btn = (Button) findViewById(R.id.cancel_res_btn);

        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
