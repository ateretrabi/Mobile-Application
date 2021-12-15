package com.example.app2.UI.ui.RegisteredTravels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.app2.Data.ITravelDataSource;
import com.example.app2.Data.ITravelRepository;
import com.example.app2.Data.TravelDataSource;
import com.example.app2.Data.TravelRepository;
import com.example.app2.Entities.StatusOrder;
import com.example.app2.Entities.Travel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class RegisteredTravelsViewModel extends ViewModel {

    private TravelRepository travelRepository = new TravelRepository();
    private ITravelDataSource dataSource=travelRepository.getTravelDataSource();
    protected MutableLiveData<List<Travel>> openTravels;


    public ITravelRepository getRepository() {
        return travelRepository;
    }

    public RegisteredTravelsViewModel() {
        openTravels=new MutableLiveData<List<Travel>>();
        openTravels.postValue(travelRepository.openCustomerTravels());
}
    public MutableLiveData<List<Travel>>  openCustomerTravels(){
        return openTravels;
    }

    public void updateTravel(Travel travel){
        travelRepository.updateTravel(travel);
    }







}