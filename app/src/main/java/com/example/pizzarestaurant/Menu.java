package com.example.pizzarestaurant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Menu extends AppCompatActivity {
    private TextView errorText;

    ListView listView;
    ArrayList<String> names = new ArrayList<>();
    ArrayList<String> details = new ArrayList<>();
    int images[] = {R.drawable.pizza, R.drawable.burger, R.drawable.steak, R.drawable.spaghetti};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        String url = "https://retoolapi.dev/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PlaceHolderAPI jsonPlaceHolderApi = retrofit.create(PlaceHolderAPI.class);
        Call<List<Post>> call = jsonPlaceHolderApi.getPosts();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                List<Post> posts = response.body();
                int j =0;
                for (Post post : posts){
                    if(j >= 4){
                        break;
                    }
                    names.add(post.getFoodName());
                    details.add(post.getDetails());
                    j++;
                }
                String a = "";
                for (int i = 0; i < details.size(); i++) {
                    a = a + details.get(i) + ", \n";
                }
                listView = (ListView)findViewById(R.id.listmenu);
                CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(), names, details, images);
                listView.setAdapter(customAdapter);

                FloatingActionButton myFab = (FloatingActionButton) findViewById(R.id.add);
                myFab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(Menu.this, Details.class));
                    }
                });
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                errorText =  (TextView)findViewById(R.id.error);
                errorText.setText("Error : "+t.getMessage());
            }
        });
    }
}