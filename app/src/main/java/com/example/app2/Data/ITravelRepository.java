package com.example.app2.Data;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.example.app2.Entities.Travel;

import java.util.ArrayList;
import java.util.List;

public interface ITravelRepository {


    ITravelDataSource getTravelDataSource();
    List<Travel> getTravels();
    List<Travel>  openCustomerTravels();
    void updateTravel(Travel travel);
    void suggestServiceToCustomer(Travel travel);
    List<Travel> closedTravels();
    MutableLiveData<List<Travel>> getOpenTravels();

    ArrayList<Travel> GetSuitableTravels(double curLatitude, double curLongitude, Context context);
}
