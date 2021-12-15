package com.example.app2.Entities;



import java.util.Date;
import java.util.HashMap;

public class Travel {
    private String clientName;
    private String clientPhone;
    private String clientEmail;
    private String pickupAddress;
    private String returnAddress;
    private String destinationAddress;
    private int numTravelers;
    private Date startDate;
    private Date returnDate;
    private StatusOrder statusOrder;
    private  HashMap<String,Boolean> companyName;
    private String key;


    public HashMap<String, Boolean> getCompanyName() {
        return companyName;
    }

    public void setCompanyName(HashMap<String, Boolean> companyName) {
        this.companyName = companyName;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientPhone() {
        return clientPhone;
    }

    public void setClientPhone(String clientPhone) {
        this.clientPhone = clientPhone;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public String getPickupAddress() {
        return pickupAddress;
    }

    public void setPickupAddress(String pickupAddress) {
        this.pickupAddress = pickupAddress;
    }

    public String getReturnAddress() {
        return returnAddress;
    }

    public void setReturnAddress(String returnAddress) {
        this.returnAddress = returnAddress;
    }

    public String getDestinationAddress() {
        return destinationAddress;
    }

    public void setDestinationAddress(String destinationAddress) {
        this.destinationAddress = destinationAddress;
    }

    public int getNumTravelers() {
        return numTravelers;
    }

    public void setNumTravelers(int numTravelers) {
        this.numTravelers = numTravelers;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public StatusOrder getStatusOrder() {
        return statusOrder;
    }

    public void setStatusOrder(StatusOrder statusOrder) {
        this.statusOrder = statusOrder;
    }

    public String getKey(){return key;}

    public void setKey(String key) {
        this.key = key;
    }

    public Travel(String clientName, String clientPhone, String clientEmail, String pickupAddress, String returnAddress, String destinationAddress, int numTravelers, Date startDate, Date returnDate) {
        this.clientName = clientName;
        this.clientPhone = clientPhone;
        this.clientEmail = clientEmail;
        this.pickupAddress = pickupAddress;
        this.returnAddress = returnAddress;
        this.destinationAddress = destinationAddress;
        this.numTravelers = numTravelers;
        this.startDate = startDate;
        this.returnDate = returnDate;
        this.statusOrder = StatusOrder.SENT;
        this.companyName=new HashMap<String,Boolean>();
    }
    public Travel(){
        this.clientName = "clientName";
        this.clientPhone = "clientPhone";
        this.clientEmail = "clientEmail";
        this.pickupAddress = "pickupAddress";
        this.returnAddress = "returnAddress";
        this.destinationAddress = "destinationAddress";
        this.numTravelers = 0;
        this.startDate = new Date();
        this.returnDate = new Date();
        this.statusOrder = StatusOrder.CLOSED;
        this.companyName=new HashMap<String,Boolean>();
    }
}

