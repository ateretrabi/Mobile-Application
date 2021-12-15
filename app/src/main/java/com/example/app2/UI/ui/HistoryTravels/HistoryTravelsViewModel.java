package com.example.app2.UI.ui.HistoryTravels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.app2.Data.ITravelRepository;
import com.example.app2.Data.TravelRepository;
import com.example.app2.Entities.StatusOrder;
import com.example.app2.Entities.Travel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class HistoryTravelsViewModel extends ViewModel {
    private ITravelRepository travelRepository=new TravelRepository();
    protected MutableLiveData<List<Travel>>  closedTravels;

    public HistoryTravelsViewModel(){
        List<Travel> l=new ArrayList<Travel>();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userEmail;
        if (user != null) {
            userEmail = user.getEmail();
        } else {
            userEmail = "";
        }
        for(Travel travel:travelRepository.getTravels()){
            if(travel.getStatusOrder()==StatusOrder.CLOSED)
                l.add(travel);
        }

        closedTravels=new MutableLiveData<>();
        closedTravels.postValue(l);
    }
    public MutableLiveData<List<Travel>> closedTravels(){
        List<Travel> t= new ArrayList<Travel>();

        t.add(new Travel("bhb","05463677","hdbhfb", "","","hfhf",2,new Date(2020,1,1),new Date(2020,1,1)));
        t.add(new Travel("bjhbb","0589677","hdbhfb", "","","knk",2,new Date(2020,1,1),new Date(2020,1,1)));
        //return travelRepository.closedTravels();
        MutableLiveData<List<Travel>> TR=new MutableLiveData<List<Travel>>();
        TR.postValue(travelRepository.closedTravels());
        return closedTravels;
    }





}