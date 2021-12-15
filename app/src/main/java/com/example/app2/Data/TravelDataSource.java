package com.example.app2.Data;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.app2.Entities.StatusOrder;
import com.example.app2.Entities.Travel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TravelDataSource implements ITravelDataSource {
    private static TravelDataSource instance;
    protected FirebaseDatabase database = FirebaseDatabase.getInstance();
    MutableLiveData<Boolean> isSuccess = new MutableLiveData<Boolean>();
    DatabaseReference travels = database.getReference("TravelRequests");
    private List<Travel> listTravels;
    public MutableLiveData<List<Travel>> dataTravels;
    private MutableLiveData<List<Travel>> openTravels;
    public static TravelDataSource getInstance() {
        if (instance == null)
            instance = new TravelDataSource();
        return instance;
    }
    @Override
    public MutableLiveData<List<Travel>> getOpenTravels() {
        return openTravels;
    }

    @Override
    public MutableLiveData<List<Travel>> getListTravels() {
        return dataTravels;
    }
    @Override
    public List<Travel> getTravels(){return listTravels;}
    /**
     * constructor
     */
    public TravelDataSource() {
        listTravels = new ArrayList<>();
        travels.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listTravels.clear();
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Travel travel = snapshot.getValue(Travel.class);
                        listTravels.add(travel);
                    }
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    /**
     * this func returns all open travels of user without company approvals
     */
    @Override
    public List<Travel> openCustomerTravels() {

        List<Travel> l = new ArrayList<>();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userEmail;
        if (user != null) {
            userEmail = user.getEmail();
        } else {
            userEmail = "";
        }

        for (Travel travel : listTravels) {
            String s=travel.getClientEmail();
            if ((userEmail.equals(s)) && (travel.getStatusOrder() != StatusOrder.CLOSED)) {
                boolean bad = false;

                if (travel.getCompanyName() == null)
                    l.add(travel);
                else if(travel.getCompanyName().size()==0)
                    l.add(travel);

                else {
                    for (String i: travel.getCompanyName().keySet()) {
                        if (travel.getCompanyName().get(i))
                            bad = true;
                    }
                    if (!bad)
                        l.add(travel);
                }

            }
        }

       return listTravels;
    }


    /**
     * this func adds travel to firebase
     * @param travel
     */

    public void addTravel(Travel travel) {
        travels.child(travel.getKey())
               .setValue(travel).addOnSuccessListener(aVoid -> isSuccess.postValue(true));
    }

    /**
     * this func removes travel from firebase
     * @param travel
     */
    private void removeTravel(Travel travel) {
        travels.child(travel.getKey()).removeValue().addOnSuccessListener(aVoid -> isSuccess.postValue(true));
    }

    /**
     * this func updates the travel
     * @param travel
     */
    @Override
    public void updateTravel(Travel travel) {
        removeTravel(travel);
        addTravel(travel);
    }

    @Override
    /**
     * this func offers the customer to use service of the company
     * @param travel
     */
    public void offerTravelToCustomer(Travel travel) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userEmail;
        if (user != null) {
            userEmail = user.getEmail();
        }
        else {userEmail="";}
        String key = userEmail.substring(0,travel.getClientEmail().indexOf("@"));
        travel.getCompanyName().put(key,false);
        updateTravel(travel);

    }


    @Override
    public MutableLiveData<Boolean> getIsSuccess() {
        return isSuccess;
    }


}
