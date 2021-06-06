package com.serviingo.serviingo.modelrepo.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Changepassword_request {

    @SerializedName("old_password")
    @Expose
    public String old_password;
    @SerializedName("password")
    @Expose
    public String password;
    @SerializedName("password_confirmation")
    @Expose
    public String password_confirmation;

    public Changepassword_request(String old_password, String password, String password_confirmation) {
        this.old_password = old_password;
        this.password = password;
        this.password_confirmation = password_confirmation;
    }
}
