package com.example.fragmentsfilms.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fragmentsfilms.Adapter.AdapterFilme;
import com.example.fragmentsfilms.Adapter.touch.TouchDiretor;
import com.example.fragmentsfilms.Adapter.touch.TouchFilme;
import com.example.fragmentsfilms.R;
import com.example.fragmentsfilms.activity.MainActivity;
import com.example.fragmentsfilms.controller.ControllerFilme;

public class RecyclerFragmentFilme extends Fragment {
    private RecyclerView recyclerView;
    private AdapterFilme adapterFilme;
    private ControllerFilme controllerFilme;
    private MainActivity activity;

    public RecyclerFragmentFilme(MainActivity activity){
        this.activity = activity;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recycler, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        AdapterFilme adapterFilme = new AdapterFilme(activity);
        recyclerView.setAdapter(adapterFilme);


        ItemTouchHelper touchHelper = new ItemTouchHelper(new TouchFilme(adapterFilme));
        touchHelper.attachToRecyclerView(recyclerView);
        return view;
    }
}
