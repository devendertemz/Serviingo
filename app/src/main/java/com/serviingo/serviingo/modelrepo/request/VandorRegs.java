package com.serviingo.serviingo.modelrepo.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VandorRegs {

    public String firstName ;
    
    public String lastName ;
  
    public String state ;
    
    public String city;
  
    public String category ;
  
    public String email ;
    
    public String mobileNumber;
   
    public String password;
  
    public String confirmPassword;

    public VandorRegs(String firstName, String lastName, String state, String city, String category, String email, String mobileNumber, String password, String confirmPassword) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.state = state;
        this.city = city;
        this.category = category;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }
}
