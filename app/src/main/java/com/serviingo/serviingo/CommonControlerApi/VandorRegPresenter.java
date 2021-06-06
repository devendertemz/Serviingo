package com.serviingo.serviingo.CommonControlerApi;


import android.util.Log;

import com.serviingo.serviingo.Rtrofit.ApiClientt;
import com.serviingo.serviingo.modelrepo.Responsee.VandorRepo;
import com.serviingo.serviingo.modelrepo.request.VandorRegs;

import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VandorRegPresenter {

    private VandorRegView view;
    VandorRegs vandorRegs;

    public VandorRegPresenter(VandorRegView view) {
        this.view = view;
    }

    public void VandorReg(VandorRegs vandorRegs) {

        Call<VandorRepo> loginCall =  ApiClientt.getUserService().doVandorReg(vandorRegs.firstName,vandorRegs.lastName,vandorRegs.state,vandorRegs.city,vandorRegs.category,vandorRegs.email,vandorRegs.mobileNumber,vandorRegs.password,vandorRegs.confirmPassword);

        loginCall.enqueue(new Callback<VandorRepo>() {
            @Override
            public void onResponse(Call<VandorRepo> call, Response<VandorRepo> response) {
                Log.d("checkkkkkk", String.valueOf(response.code()));

                if (response.isSuccessful() && response.body() != null && response.code() == 200) {
                    try {

                        view.onVandorRegSuccess(response.body(), response.message());
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                } else {
                    try {
                        String errorStr = response.errorBody().string();
                        JSONObject jsonObject = new JSONObject(errorStr);
                        JSONObject jsonObject1=jsonObject.getJSONObject("message");
                        JSONObject jsonObject2=jsonObject1.getJSONObject("error");
                        String eroor=jsonObject2.getString("first_name");


                        String errorMsg = jsonObject.getString("status");
                        view.onVandorRegError("Email and Mobile number already exist in our record, please try another.");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        view.onVandorRegError("Email and Mobile number already exist in our record, please try another.");
                    }

                }
            }

            @Override
            public void onFailure(Call<VandorRepo> call, Throwable t) {
                view.onVandorRegFailure(t);
            }
        });

    }

    public interface VandorRegView {

        void onVandorRegError(String message);

        void onVandorRegSuccess( VandorRepo response, String message);

        void onVandorRegFailure(Throwable t);
    }
}
