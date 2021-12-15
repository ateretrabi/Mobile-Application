package com.example.app2.Data;

import androidx.lifecycle.MutableLiveData;

import com.example.app2.Entities.Travel;

import java.util.List;

public interface ITravelDataSource {


    List<Travel> openCustomerTravels();

    void updateTravel(Travel travel);
    MutableLiveData<Boolean> getIsSuccess();
    void offerTravelToCustomer(Travel travel);
    MutableLiveData<List<Travel>> getListTravels();
    MutableLiveData<List<Travel>> getOpenTravels();
    List<Travel> getTravels();


}
