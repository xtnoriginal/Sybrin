package com.example.sybrin.ui.display;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.sybrin.R;
import com.example.sybrin.components.RecyclerviewAdapter;
import com.example.sybrin.databinding.FragmentDisplayBinding;

public class DisplayFragment extends Fragment {

    private FragmentDisplayBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DisplayViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DisplayViewModel.class);

        binding = FragmentDisplayBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //Set up recyclerview
        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.displayRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
        recyclerView.setAdapter(new RecyclerviewAdapter(new String[]{"hello", "ndeipi", "hello world"}));

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}