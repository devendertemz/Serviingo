package com.serviingo.serviingo.modelrepo.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateProfile_request {
    @SerializedName("name")
    @Expose
    public String name ;
    @SerializedName("gender")
    @Expose
    public String gender ;
    @SerializedName("email")
    @Expose
    public String email ;
    @SerializedName("mobile_number")
    @Expose
    public String mobileNumber;
    @SerializedName("state_id")
    @Expose
    public String state_id;
    @SerializedName("city_id")
    @Expose
    public String city_id;

    @SerializedName("address")
    @Expose
    public String address;

    @SerializedName("landmark")
    @Expose
    public String landmark;

    @SerializedName("pincode")
    @Expose
    public String pincode;


    public UpdateProfile_request(String name, String gender, String email, String mobileNumber, String state_id, String city_id, String address, String landmark, String pincode) {
        this.name = name;
        this.gender = gender;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.state_id = state_id;
        this.city_id = city_id;
        this.address = address;
        this.landmark = landmark;
        this.pincode = pincode;
    }
}

