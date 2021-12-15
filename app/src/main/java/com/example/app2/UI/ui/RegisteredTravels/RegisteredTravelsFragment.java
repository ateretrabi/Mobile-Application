package com.example.app2.UI.ui.RegisteredTravels;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app2.Entities.StatusOrder;
import com.example.app2.Entities.Travel;
import com.example.app2.R;
import com.example.app2.UI.ui.adapters.CompanyAdapter;
import com.example.app2.UI.ui.adapters.RegisteredAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class RegisteredTravelsFragment extends Fragment {

    private RegisteredTravelsViewModel homeViewModel;
    protected RegisteredAdapter travelsAdapter;
    protected RecyclerView listTravels;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(RegisteredTravelsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_registered_travels, container, false);
        listTravels = root.findViewById(R.id.registerListTravels);
        listTravels.setLayoutManager(new LinearLayoutManager(getContext()));
        travelsAdapter=new RegisteredAdapter(null);
        listTravels.setAdapter(travelsAdapter);

        homeViewModel.openCustomerTravels().observe(getViewLifecycleOwner(), new Observer<List<Travel>>() {
            @Override
            public void onChanged(@Nullable List<Travel> l) {
                travelsAdapter.setTravels(l);
                travelsAdapter.notifyDataSetChanged();
            }
        });
        return root;
    }
}