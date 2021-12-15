package com.example.app2.UI.ui.HistoryTravels;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.app2.Entities.Travel;
import com.example.app2.R;
import com.example.app2.UI.ui.adapters.HistoryAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class HistoryTravelsFragment extends Fragment {

    private HistoryTravelsViewModel historyViewModel;
    private List<Travel> closedTravels=new ArrayList<Travel>();
    protected HistoryAdapter travelsAdapter;
    protected RecyclerView listTravels;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        historyViewModel =
                new ViewModelProvider(this).get(HistoryTravelsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_history_travels, container, false);
        listTravels = root.findViewById(R.id.listTravels);
        listTravels.setLayoutManager(new LinearLayoutManager(getContext()));
        travelsAdapter = new HistoryAdapter(null);
        listTravels.setAdapter(travelsAdapter);
        historyViewModel.closedTravels().observe(getViewLifecycleOwner(), new Observer<List<Travel>>() {
            @Override
            public void onChanged(@Nullable List<Travel> travelList) {
                travelsAdapter.setTravels(travelList);
                travelsAdapter.notifyDataSetChanged();
            }
        });
        return root;
    }
}