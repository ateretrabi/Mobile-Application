package com.example.app2;



import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MyService extends Service {
    protected FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference travels = database.getReference("TravelRequests");

    public MyService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //Toast.makeText(this,"onStartCommand", Toast.LENGTH_LONG).show();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    Log.d("SERVICE", "Running");
                    try {
                        Thread.sleep(Constants.TIMEOUT);
                        if( Constants.IS_RUN) {
                            Log.d("SERVICE", "SEND BROAD CAST");
                            travels.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    Intent intent=new Intent(Constants.CUSTOM_INTENT);
                                    sendBroadcast(intent);
                                }
                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                            Constants.IS_RUN = false;
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        });

        thread.start();

        return Service.START_STICKY;



    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}

