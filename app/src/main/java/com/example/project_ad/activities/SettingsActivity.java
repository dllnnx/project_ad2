package com.example.project_ad.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.project_ad.R;
import com.example.project_ad.database.DBTaskLetter;
import com.example.project_ad.database.DBTaskTof;
import com.example.project_ad.internet.Answer;
import com.example.project_ad.internet.DBLoader;
import com.example.project_ad.models.TaskLetter;
import com.example.project_ad.models.TaskTof;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SettingsActivity extends AppCompatActivity {
    public static final String APP_PREFERENCES = "settings";
    public static final String APP_PREFERENCES_UPDATE = "update";

    TextView updateDateTextView;
    SharedPreferences sharedPreferences;
    Button cancel_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        updateDateTextView = findViewById(R.id.update_date_textview);
        updateDateTextView.setText("Пусто");

        sharedPreferences = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        if (sharedPreferences.contains(APP_PREFERENCES_UPDATE)){
            updateDateTextView.setText(sharedPreferences.getString(APP_PREFERENCES_UPDATE, "Пусто"));
        }

        Button button = findViewById(R.id.update_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBLoader dbLoader = new DBLoader();

                dbLoader.setOnPostExecutable(new DBLoader.OnPostExecutable() {
                    @Override
                    public void onPostExecute(Answer answer) {
                        @SuppressLint("SimpleDateFormat")
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
                        Date date = new Date(System.currentTimeMillis());
                        updateDateTextView.setText(simpleDateFormat.format(date));

                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(APP_PREFERENCES_UPDATE, simpleDateFormat.format(date));
                        editor.apply();

                        DBTaskTof dbTaskTof = new DBTaskTof(getApplicationContext());
                        for(TaskTof taskTof: answer.getTaskTofs()){
                            TaskTof t = dbTaskTof.select(taskTof.getId());
                            if (t == null){
                                dbTaskTof.insert(taskTof.getRuWord(), taskTof.getEnWord(), taskTof.getBool());
                            }
                        }

                        DBTaskLetter dbTaskLetter = new DBTaskLetter(getApplicationContext());
                        for(TaskLetter taskLetter: answer.getTaskLetters()){
                            TaskLetter t = dbTaskLetter.select(taskLetter.getId());
                            if (t == null){
                                dbTaskLetter.insert(taskLetter.getWord(), taskLetter.getDistractors(),
                                        taskLetter.getMissingLetter());
                            }
                        }
                    }
                });

                dbLoader.execute();
            }
        });


        cancel_btn = (Button) findViewById(R.id.cancel_sett_btn);
        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
