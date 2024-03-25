package com.example.instagram.cloneby.Funoverflow.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.instagram.cloneby.Funoverflow.R;
import com.example.instagram.cloneby.Funoverflow.models.Stories;

import java.util.List;

public class StoriesAdapter extends RecyclerView.Adapter<StoriesAdapter.ViewHolder> {


    public StoriesAdapter(List<Stories> stories, Context context) {
        this.stories = stories;
        this.context = context;
    }

    List<Stories> stories;
    Context context;

    @NonNull
    @Override
    public StoriesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.storiesiteams, null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StoriesAdapter.ViewHolder holder, int position) {

        holder.storiesImage.setImageResource(stories.get(position).image);
    }

    @Override
    public int getItemCount() {
        return stories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView storiesImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            storiesImage = itemView.findViewById(R.id.storiesImage);
        }
    }
}
