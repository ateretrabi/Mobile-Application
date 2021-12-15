package com.example.app2.UI.ui.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app2.Entities.Travel;
import com.example.app2.R;
import com.example.app2.UI.ui.CompanyTravels.CompanyTravelsFragment;
import com.example.app2.UI.ui.CompanyTravels.CompanyTravelsViewModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

import static androidx.core.content.ContextCompat.startActivity;

public class CompanyAdapter extends RecyclerView.Adapter<CompanyAdapter.TravelCompanyViewHolder> {
    private CompanyTravelsViewModel viewModel=new CompanyTravelsViewModel();
    private List<Travel> travels;
    public List<Travel> getTravels(){
        return travels;
    }
    public void setTravels(List<Travel> l){
        this.travels=l;
    }
    public CompanyAdapter(List<Travel> l){this.travels=l;}



    @NonNull
    @Override
    public TravelCompanyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.company_travel, parent, false);
        TravelCompanyViewHolder holder = new TravelCompanyViewHolder((view));
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull TravelCompanyViewHolder holder, int position) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userEmail;
        if (user != null) {
            userEmail = user.getEmail();
        } else {
            userEmail = "";
        }
        userEmail=userEmail.substring(0,userEmail.indexOf("@"));
        if (travels == null)
            return;
        Travel travel = travels.get(position);
        holder.customerName.setText("שם הלקוח: "+    travel.getClientName());
        holder.pickup_return_addresses.setText( "כתובת איסוף: "+  travel.getPickupAddress());
        holder.destinationAddress.setText("יעד: "+  travel.getDestinationAddress());
        holder.numTravelers.setText("נוסעים: "+Integer.toString(travel.getNumTravelers()));
        holder.emailAddress.setText( "אימייל: "+  travel.getClientEmail());
        if(travel.getCompanyName().keySet().contains(userEmail))
            holder.offerService.setEnabled(false);
        holder.emailCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] to=new String[]{travel.getClientEmail()};
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/html");
                intent.putExtra(Intent.EXTRA_EMAIL, to);
                intent.putExtra(Intent.EXTRA_SUBJECT, "הצעת הסעה");

                startActivity(holder.customerName.getContext(),intent,null);


            }
        });

        holder.offerService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                viewModel.suggestServiceToCustomer(travel);
                holder.offerService.setEnabled(false);
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

    public class TravelCompanyViewHolder extends RecyclerView.ViewHolder{
        TextView customerName, pickup_return_addresses, destinationAddress, numTravelers, emailAddress;
        Button emailCustomer, offerService;
        View view;
        public TravelCompanyViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            customerName=itemView.findViewById(R.id.nameCustomer);
            pickup_return_addresses=itemView.findViewById(R.id.pickupReturnAddress);
            destinationAddress=itemView.findViewById(R.id.destination);
            numTravelers=itemView.findViewById(R.id.numTravelers);
            emailAddress=itemView.findViewById(R.id.emailCustomer);
            emailCustomer=itemView.findViewById(R.id.contactCustomer);
            offerService=itemView.findViewById(R.id.offerService);
        }
    }
}
