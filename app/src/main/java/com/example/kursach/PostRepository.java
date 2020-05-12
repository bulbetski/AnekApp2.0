package com.example.kursach;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;

public class PostRepository {
    private static PostRepository instance;
    private ArrayList<Post> posts = new ArrayList<>();

    public static PostRepository createInstance(){
        if (instance == null)
            instance = new PostRepository();
        return instance;
    }

    public static PostRepository getInstance() {
        return instance;
    }

    public ArrayList<Post> getPosts() {
        return posts;
    }

    public void load(JSONArray jsonArray){

        for (int i = 0; i < jsonArray.length(); i++){
            try {
                JSONObject obj = jsonArray.getJSONObject(i);
                posts.add(new Post(
                   obj.getInt("id"),
                   obj.getInt("date"),
                   obj.getInt("domain"),
                   obj.getString("text"),
                   obj.getInt("likes")
                ));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        Collections.shuffle(posts);
    }
}
