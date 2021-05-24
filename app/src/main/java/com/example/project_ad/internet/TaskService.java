package com.example.project_ad.internet;

import retrofit2.http.GET;
import retrofit2.Call;

public interface TaskService {
    @GET("/denisova/tasks.php")
    Call<Answer> getTasks();
}
