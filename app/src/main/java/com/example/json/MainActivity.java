package com.example.json;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private CommentAdapter commentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Инициализация ListView
        listView = findViewById(R.id.list_view);

        // Создание Retrofit и вызов API
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Интерфейс для API
        Json Json = retrofit.create(Json.class);

        // Запрос к API
        Call<List<Comment>> call = Json.getComments();
        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Code: " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                // Получаем список комментариев и отображаем
                List<Comment> comments = response.body();
                if (comments != null) {
                    // Логирование данных для проверки
                    for (Comment comment : comments) {
                        Log.d("API_Response", "Name: " + comment.getName() + ", Email: " + comment.getEmail() + ", Body: " + comment.getBody());
                    }

                    // Устанавливаем адаптер
                    commentAdapter = new CommentAdapter(MainActivity.this, comments);
                    listView.setAdapter(commentAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
