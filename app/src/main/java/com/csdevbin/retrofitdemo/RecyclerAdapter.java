package com.csdevbin.retrofitdemo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyHolder> {
    List<Post> postList;

    public RecyclerAdapter(List<Post> postList) {
        this.postList = postList;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        Post post = postList.get(position);

        holder.title.setText(post.getTitle());
        holder.body.setText(post.getText());
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView body;

        public MyHolder(@NonNull View view) {
            super(view);

            title = view.findViewById(R.id.title);
            body = view.findViewById(R.id.body);
        }
    }
}
