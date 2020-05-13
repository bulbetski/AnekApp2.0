package com.example.kursach;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ShareActionProvider;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;

public class MainActivity extends AppCompatActivity {
    ViewPager2 viewPager2;
    private ShareActionProvider shareActionProvider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        final PostRepository postRepository = PostRepository.createInstance();
        PostRepository.createInstance();

        viewPager2 = findViewById(R.id.view_pager);
        viewPager2.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        final ViewPagerAdapter adapter = new ViewPagerAdapter();
        viewPager2.setAdapter(adapter);
        viewPager2.setUserInputEnabled(false);

        final String url ="http://192.168.3.17:5000/";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        PostRepository.getInstance().load(response);
                        adapter.notifyDataSetChanged();
                            //-92876084
                            //-85443458
                        final Button previous = findViewById(R.id.previous);
                        previous.setVisibility(View.GONE);
                        previous.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (viewPager2.getCurrentItem() > 0){
                                    viewPager2.setCurrentItem(viewPager2.getCurrentItem()-1, true);
                                }
                                if (viewPager2.getCurrentItem() == 0)
                                    previous.setVisibility(View.GONE);

                            }
                        });

                        Button next = findViewById(R.id.next);
                        next.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (viewPager2.getCurrentItem() == 0)
                                    previous.setVisibility(View.VISIBLE);
                                if (viewPager2.getCurrentItem() <= PostRepository.getInstance().getPosts().size()){
                                    viewPager2.setCurrentItem(viewPager2.getCurrentItem()+1, true);
                                }
                            }
                        });
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });
        MySingleton.getInstance(this).addToRequestQueue(jsonArrayRequest);
    }

    public Post getItem(){
        return PostRepository
                .getInstance()
                .getPosts()
                .get(viewPager2
                        .getCurrentItem());
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (!PostRepository.getInstance().isEmpty()){
            switch (item.getItemId()){
                case R.id.share_button:
                    Intent i = new Intent(
                            android.content.Intent.ACTION_SEND);
                    i.setType("text/plain");
                    i.putExtra(
                            android.content.Intent.EXTRA_TEXT,
                            getItem().getText() + "\n\nSource: AnekApp 2.0");
                    startActivity(Intent.createChooser(
                            i,
                            "Share Via"));
                    break;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
