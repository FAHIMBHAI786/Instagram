package com.example.instagram.cloneby.Funoverflow.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.instagram.cloneby.Funoverflow.R;
import com.example.instagram.cloneby.Funoverflow.adapters.PostAdapter;
import com.example.instagram.cloneby.Funoverflow.adapters.StoriesAdapter;
import com.example.instagram.cloneby.Funoverflow.databinding.FragmentHomeBinding;
import com.example.instagram.cloneby.Funoverflow.models.Post;
import com.example.instagram.cloneby.Funoverflow.models.Stories;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<Post> post;
    ArrayList<Stories> stories;
    private FragmentHomeBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        recyclerView = binding.rvPost;
        stories = new ArrayList<>();
        post = new ArrayList<>();


        stories.add(new Stories(R.drawable.avatar1));
        stories.add(new Stories(R.drawable.avarar2));
        stories.add(new Stories(R.drawable.avatar3));
        stories.add(new Stories(R.drawable.avatar4));
        stories.add(new Stories(R.drawable.avatar1));
        stories.add(new Stories(R.drawable.avarar2));
        stories.add(new Stories(R.drawable.avatar3));
        stories.add(new Stories(R.drawable.avatar4));
        stories.add(new Stories(R.drawable.avatar1));
        stories.add(new Stories(R.drawable.avarar2));
        stories.add(new Stories(R.drawable.avatar3));
        stories.add(new Stories(R.drawable.avatar4));


        post.add(new Post(stories));









        PostAdapter postAdapter = new PostAdapter(post,requireContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(postAdapter);
        postAdapter.notifyDataSetChanged();
        // You can add your logic here
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
