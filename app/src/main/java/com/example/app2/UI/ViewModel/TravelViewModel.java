package com.example.app2.UI.ViewModel;

import com.example.app2.Entities.Travel;

import java.util.ArrayList;
import java.util.Date;

public class TravelViewModel {
    public ArrayList<Travel> GetTravelOfUser(String emailstring) {
        ArrayList<Travel> arr=new ArrayList<Travel>();
        arr.add(new Travel("hd","098765432","fg@gmail.com","bf","hbd","jfb",2,new Date(),new Date()));
        return arr;


    }
}
