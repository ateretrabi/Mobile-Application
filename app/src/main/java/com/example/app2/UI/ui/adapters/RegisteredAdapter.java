package com.example.app2.UI.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app2.Entities.StatusOrder;
import com.example.app2.Entities.Travel;
import com.example.app2.R;
import com.example.app2.UI.ui.RegisteredTravels.RegisteredTravelsViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class RegisteredAdapter extends RecyclerView.Adapter<RegisteredAdapter.RegisteredTravelViewHolder>{
    List<Travel> travels;
    RegisteredTravelsViewModel viewModel= new RegisteredTravelsViewModel();
    public RegisteredAdapter(List<Travel> l){
        this.travels=l;
    }
    public List<Travel> getTravels(){
        return travels;
    }
    public void setTravels(List<Travel> l){
        this.travels=l;
    }
    @NonNull
    @Override
    public RegisteredTravelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.registered_travel, parent, false);
        RegisteredTravelViewHolder holder = new RegisteredTravelViewHolder((view));
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RegisteredTravelViewHolder holder, int position) {
        if (travels == null)
            return;
        Travel travel = travels.get(position);

        List<String> l = new ArrayList<String>(travel.getCompanyName().keySet());

        ArrayAdapter<String> adapter= new ArrayAdapter<String>(holder.startDate.getContext(),android.R.layout.simple_spinner_dropdown_item,l);
        holder.startDate.setText( "תאריך התחלה: "+ travel.getStartDate().getDay()+"/"+travel.getStartDate().getMonth()+"/"+travel.getStartDate().getYear());
        holder.endDate.setText("תאריך סיום: "+  travel.getReturnDate().getDay()+"/"+travel.getReturnDate().getMonth()+"/"+travel.getReturnDate().getYear());
        holder.numberTravelers.setText("מספר נוסעים: "+ Integer.toString(travel.getNumTravelers()));
        holder.spinner.setAdapter(adapter);
        if(l==null||l.isEmpty())
            holder.chooseCompany.setEnabled(false);
        holder.chooseCompany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                travel.getCompanyName().put(holder.spinner.getSelectedItem().toString(),true);
                viewModel.updateTravel(travel);
            }
        });
        holder.closeTravel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                travel.setStatusOrder(StatusOrder.CLOSED);
                viewModel.updateTravel(travel);
            }
        });

    }

    @Override
    public int getItemCount() {
        if (travels == null)
            return 0;
        else
            return travels.size();
    }

    public class RegisteredTravelViewHolder extends RecyclerView.ViewHolder{
        TextView startDate, endDate, numberTravelers;
        Spinner spinner;
        Button chooseCompany, closeTravel;
        public RegisteredTravelViewHolder(@NonNull View itemView) {
            super(itemView);

            startDate=itemView.findViewById(R.id.startDate);
            endDate=itemView.findViewById(R.id.endDate);
            numberTravelers=itemView.findViewById(R.id.numberTravelers);
            spinner=itemView.findViewById(R.id.spinner);
            chooseCompany=itemView.findViewById(R.id.chooseCompany);
            closeTravel=itemView.findViewById(R.id.finishTravel);


        }
    }
}
