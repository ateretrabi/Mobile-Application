package com.example.app2.Data;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.app2.Entities.StatusOrder;
import com.example.app2.Entities.Travel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HistoryDataSource implements IHistoryDataSource{
    protected FirebaseDatabase database = FirebaseDatabase.getInstance();
    MutableLiveData<Boolean> isSuccess = new MutableLiveData<Boolean>();
    DatabaseReference travels = database.getReference("TravelRequests");
    private List<Travel> listTravels;

    /**
     * constructor
     */
    public HistoryDataSource (){
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
     * this func returns all closed travels
     * @return
     */
    @Override
    public List<Travel> closedTravels() {
        List<Travel> l = new ArrayList<>();
        for(Travel t :listTravels)
        {
            if(t.getStatusOrder()== StatusOrder.CLOSED)
                l.add(t);
        }
        return l;
    }
}
