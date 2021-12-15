package com.example.app2.Data;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import androidx.annotation.Nullable;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.app2.Entities.StatusOrder;
import com.example.app2.Entities.Travel;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TravelRepository implements ITravelRepository {
    protected TravelDataSource travelDataSource =TravelDataSource.getInstance();
    protected IHistoryDataSource historyDataSource= new HistoryDataSource();
    protected MutableLiveData<List<Travel>> openTravels;
    protected List<Travel> travels=travelDataSource.getTravels();




    @Override
    public  List<Travel> getTravels(){return travels;}

    @Override
    public ITravelDataSource getTravelDataSource() {
        return travelDataSource;
    }

    @Override
    public MutableLiveData<List<Travel>> getOpenTravels(){return openTravels;}


    @Override
    /**
     * this func returns mutable live data list of all open travels of connected customer
     * @return
     */
    public List<Travel>  openCustomerTravels(){
        ArrayList<Travel> travels=new ArrayList<Travel>() {};
        List<Travel> all= travelDataSource.getTravels();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userEmail;
        if (user != null) {
            userEmail = user.getEmail();
        } else {
            userEmail = "";
        }
        for (Travel tr:all){
            String e=tr.getClientEmail();
            if(tr.getStatusOrder()!= StatusOrder.CLOSED)
                if(e.equals(userEmail))
                    travels.add(tr);
        }

        return travels;
    }
    @Override
    /**
     * use this to save changes of travel
     * @param travel
     */
    public void updateTravel(Travel travel){
        travelDataSource.updateTravel(travel);
    }

    @Override
    /**
    *
     * *this func saves offer of travel company to customer
     *@param travel
     *
    */
    public void suggestServiceToCustomer(Travel travel){
        travelDataSource.offerTravelToCustomer(travel);
    }


    @Override
    /**
     * muteable live data of close travels to history
     * @return
     */
    public List<Travel> closedTravels(){

        return historyDataSource.closedTravels();
    }

    @Override
    public ArrayList<Travel>  GetSuitableTravels(double curLatitude, double curLongitude, Context context)
    {

        ArrayList<Travel> suitabletravels=new ArrayList<Travel>() {};
        List<Travel> all= new ArrayList<Travel>();
        for(Travel t:travelDataSource.getTravels()){
            if(t.getStatusOrder()!= StatusOrder.CLOSED)
                all.add(t);
        }
        for (int i=0;i<all.size();i++) {
            String newAddress=all.get(i).getPickupAddress()+","+"ישראל";
            LatLng Loc= getLocationFromAddress(context,newAddress);

            //UserLocation d=all.get(i).getUs();
            float distance=  calculateDistance(curLatitude,  curLongitude,Loc.latitude,Loc.longitude);
            if(distance<10)
                suitabletravels.add(all.get(i));

        }

        return suitabletravels;

    }
    public final static double AVERAGE_RADIUS_OF_EARTH = 6371;
    public float calculateDistance(double userLat, double userLng, double venueLat, double venueLng) {

        double latDistance = Math.toRadians(userLat - venueLat);
        double lngDistance = Math.toRadians(userLng - venueLng);

        double a = (Math.sin(latDistance / 2) * Math.sin(latDistance / 2)) +
                (Math.cos(Math.toRadians(userLat))) *
                        (Math.cos(Math.toRadians(venueLat))) *
                        (Math.sin(lngDistance / 2)) *
                        (Math.sin(lngDistance / 2));

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return (float) (Math.round(AVERAGE_RADIUS_OF_EARTH * c));

    }
    public LatLng getLocationFromAddress(Context context, String strAddress) {

        Geocoder coder = new Geocoder(context);
        List<Address> address;
        LatLng LatLan= null;

        try {
            // May throw an IOException
            address = coder.getFromLocationName(strAddress, 5);
            if (address == null) {
                return null;
            }

            Address location = address.get(0);
            LatLan= new LatLng(location.getLatitude(), location.getLongitude() );

        } catch (IOException ex) {

            ex.printStackTrace();
        }

        return LatLan;
    }



}
