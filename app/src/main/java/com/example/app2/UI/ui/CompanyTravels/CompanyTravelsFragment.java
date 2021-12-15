package com.example.app2.UI.ui.CompanyTravels;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app2.Entities.Travel;
import com.example.app2.R;
import com.example.app2.UI.ui.adapters.CompanyAdapter;
import com.example.app2.UI.ui.adapters.HistoryAdapter;

import java.util.ArrayList;
import java.util.List;

import static androidx.core.content.ContextCompat.startActivity;

public class CompanyTravelsFragment extends Fragment {

    private CompanyTravelsViewModel galleryViewModel;
    private List<Travel> travels = new ArrayList<Travel>();
    protected CompanyAdapter travelsAdapter;
    protected RecyclerView listTravels;
    LocationListener locationListener;
    LocationManager locationManager;
    Location location;
    //= (LocationManager)getContext().getSystemService(Context.LOCATION_SERVICE);


    //= locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);


    ArrayList<Travel> suitabelTravels = new ArrayList<>();


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                new ViewModelProvider(this).get(CompanyTravelsViewModel.class);
        locationManager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
           // return TODO;
        }
        location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
       MutableLiveData<List<Travel>> sTravels = galleryViewModel.suggestionsOfTravelsToCompany(location.getLatitude(), location.getLongitude(), getContext());
        View root = inflater.inflate(R.layout.fragment_company_travels, container, false);
        listTravels = root.findViewById(R.id.companyListTravels);
        listTravels.setLayoutManager(new LinearLayoutManager(getContext()));
        travelsAdapter = new CompanyAdapter(null);
        listTravels.setAdapter(travelsAdapter);
        galleryViewModel.suggestionsOfTravelsToCompany(location.getLatitude(), location.getLongitude(), getContext()).observe(getViewLifecycleOwner(), new Observer<List<Travel>>() {
            @Override
            public void onChanged(@Nullable List<Travel> l) {
                travelsAdapter.setTravels(l);
                travelsAdapter.notifyDataSetChanged();
            }
        });
        return root;
    }



}