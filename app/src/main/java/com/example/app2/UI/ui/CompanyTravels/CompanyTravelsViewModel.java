package com.example.app2.UI.ui.CompanyTravels;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.app2.Data.ITravelRepository;
import com.example.app2.Data.TravelRepository;
import com.example.app2.Entities.Travel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CompanyTravelsViewModel extends ViewModel {
    private ITravelRepository travelRepository=new TravelRepository();
    MutableLiveData<List<Travel>> data=new MutableLiveData<>();
    public void suggestServiceToCustomer(Travel travel){
        travelRepository.suggestServiceToCustomer(travel);
    }


    public MutableLiveData<List<Travel>> suggestionsOfTravelsToCompany(double latitude, double longitude, Context context){
        ArrayList<Travel> l=travelRepository.GetSuitableTravels(latitude,longitude, context);

        data.postValue(l);
        return data;

    }


}