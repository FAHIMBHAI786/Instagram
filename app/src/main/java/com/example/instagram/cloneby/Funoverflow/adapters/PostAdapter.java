package com.example.instagram.cloneby.Funoverflow.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.instagram.cloneby.Funoverflow.R;
import com.example.instagram.cloneby.Funoverflow.models.Post;
import com.example.instagram.cloneby.Funoverflow.models.Stories;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {


    public PostAdapter(List<Post> post, Context context) {
        this.post  = post;
        this.context = context;
    }

    List<Post> post;
    Context context;

    @NonNull
    @Override
    public PostAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.all_post_rv, null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostAdapter.ViewHolder holder, int position) {
        StoriesAdapter storiesAdapter;
        storiesAdapter = new StoriesAdapter(post.get(position).stories,context);
        holder.allPostRv.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));

        holder.allPostRv.setAdapter(storiesAdapter);
        storiesAdapter.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return post.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        RecyclerView allPostRv;
        TextView profileName;

        ImageView postImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            allPostRv = itemView.findViewById(R.id.rvStories);

        }
    }
}
