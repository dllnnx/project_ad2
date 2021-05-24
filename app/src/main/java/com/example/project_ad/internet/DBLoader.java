package com.example.project_ad.internet;

import android.os.AsyncTask;

import java.io.IOException;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.Call;
import retrofit2.converter.gson.GsonConverterFactory;

public class DBLoader extends AsyncTask<String, Integer, Answer>{
    private OnPostExecutable onPostExecutable = new OnPostExecutable() {
        @Override
        public void onPostExecute(Answer answer) {

        }
    };
    private OnPreExecutable onPreExecutable = new OnPreExecutable() {
        @Override
        public void onPreExecute() {

        }
    };
    public static String HOST_NAME = "https://h152771.s22.test-hf.su";

    public interface OnPostExecutable{
        public void onPostExecute(Answer answer);
    }

    public interface OnPreExecutable{
        public void onPreExecute();
    }

    public void setOnPostExecutable(OnPostExecutable onPostExecutable) {
        this.onPostExecutable = onPostExecutable;
    }

    public void setOnPreExecutable(OnPreExecutable onPreExecutable) {
        this.onPreExecutable = onPreExecutable;
    }

    @Override
    protected Answer doInBackground(String... strings) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HOST_NAME)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        TaskService taskService = retrofit.create(TaskService.class);
        Call<Answer> call = taskService.getTasks();

        try {
            Response<Answer> response = call.execute();
            return response.body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        onPreExecutable.onPreExecute();
    }

    @Override
    protected void onPostExecute(Answer answer) {
        super.onPostExecute(answer);
        onPostExecutable.onPostExecute(answer);
    }
}
