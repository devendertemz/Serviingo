package com.serviingo.serviingo.CommonControlerApi;


import android.content.Context;

import com.serviingo.serviingo.Rtrofit.ApiManager;
import com.serviingo.serviingo.modelrepo.Responsee.Profile_Repo;
import com.serviingo.serviingo.modelrepo.request.UpdateProfile_request;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DoMyprofileUpdatePresenter {


    private  DoMyprofileUpdateView  view;

    public DoMyprofileUpdatePresenter(DoMyprofileUpdateView view) {
        this.view = view;
    }

    public void DoMyprofil(Context context, UpdateProfile_request updateProfile_request) {
        Call<Profile_Repo> loginCall =  ApiManager.getApi(context).DoMyprofileUpdate(updateProfile_request);

        loginCall.enqueue(new Callback<Profile_Repo>() {
            @Override
            public void onResponse(Call<Profile_Repo> call, Response<Profile_Repo> response) {

                if (response.isSuccessful() && response.body() != null && response.code() == 200) {
                    try {

                        view.onDoMyprofileUpdateSuccess(response.body(), response.message());
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                } else {
                    try {
                        String errorStr = response.errorBody().string();
                        JSONObject jsonObject = new JSONObject(errorStr);
                        String errorMsg = jsonObject.getString("status");
                        view.onDoMyprofileUpdateError(String.valueOf(response.code()));
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        view.onDoMyprofileUpdateError(String.valueOf(response.code()));
                    }

                }
            }

            @Override
            public void onFailure(Call<Profile_Repo> call, Throwable t) {
                view.onDoMyprofileUpdateFailure(t);
            }
        });

    }

    public interface DoMyprofileUpdateView {

        void onDoMyprofileUpdateError(String message);

        void onDoMyprofileUpdateSuccess( Profile_Repo response, String message);

        void onDoMyprofileUpdateFailure(Throwable t);
    }
}
