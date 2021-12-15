package com.example.app2.UI.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.app2.R;
import com.example.app2.Entities.Travel;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class HistoryAdapter extends  RecyclerView.Adapter<HistoryAdapter.TravelViewHolder>{

    private List<Travel> travels;
    public List<Travel> getTravels(){
        return travels;
    }
    public void setTravels(List<Travel> l){
        this.travels=l;
    }
    public HistoryAdapter(List<Travel> l){
        this.travels=l;
    }
    @NonNull
    @Override
    public TravelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.history_travel, parent, false);
        TravelViewHolder holder = new TravelViewHolder((view));
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull TravelViewHolder holder, int position) {
        if (travels == null)
            return;
        Travel travel = travels.get(position);
        SimpleDateFormat dt1 = new SimpleDateFormat("dd/MM/yyyy");

        long num=travel.getReturnDate().getTime()-travel.getStartDate().getTime();
        num=TimeUnit.DAYS.convert(num, TimeUnit.MILLISECONDS);
        holder.customerName.setText(travel.getClientName());
        holder.travelDays.setText("נוסעים: "+ Long.toString(travel.getNumTravelers()));
        holder.address.setText(travel.getDestinationAddress());
        holder.date.setText(travel.getClientEmail());
        //holder.emailCompany.setOnClickListener(new View.OnClickListener() {
         //   @Override
          //  public void onClick(View v) {

           // }
       // });


    }

    @Override
    public int getItemCount() {
        if (travels == null)
            return 0;
        else
            return travels.size();
    }

    public class TravelViewHolder extends RecyclerView.ViewHolder{
        TextView customerName, travelDays, date, address;
        Button emailCompany;
        View view;
        public TravelViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            customerName=itemView.findViewById(R.id.name);
            travelDays=itemView.findViewById(R.id.numDaysOfTravel);
            date=itemView.findViewById(R.id.date);
            address=itemView.findViewById(R.id.address);
            //emailCompany=itemView.findViewById(R.id.contactCompany);
        }
    }
}
