package com.example.kursach;

import android.content.Intent;
import android.net.Uri;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ViewPagerAdapter extends RecyclerView.Adapter<ViewPagerAdapter.Holder> {
    private List<Post> posts = PostRepository.getInstance().getPosts();

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_pager_cardview, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewPagerAdapter.Holder holder, final int position) {
        holder.text.setText(posts.get(position).getText());
        holder.text.setMovementMethod(new ScrollingMovementMethod());
        holder.likes.setText(Integer.toString(posts.get(position).getLikes()));
        holder.source.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (posts.get(position).getDomain() == -92876084)
                    v.getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://vk.com/jumoreski")));
                else {
                    v.getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://vk.com/baneksbest")));
                }
            }
        });
        if (posts.get(position).getDomain() == -92876084)
            holder.source.setBackgroundResource(R.drawable.jumoreski_circle);
        else {
            holder.source.setBackgroundResource(R.drawable.baneks_circle);
        }
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public  class  Holder extends RecyclerView.ViewHolder {
        TextView text;
        TextView likes;
        Button source;
        public Holder(@NonNull View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.card_text);
            likes = itemView.findViewById(R.id.likes);
            source = itemView.findViewById(R.id.source_img);
        }
    }
}
