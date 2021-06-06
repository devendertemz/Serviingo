package com.serviingo.serviingo.CommonControlerApi;


import android.content.Context;

import com.serviingo.serviingo.Rtrofit.ApiClientt;
import com.serviingo.serviingo.Rtrofit.ApiManager;
import com.serviingo.serviingo.modelrepo.Responsee.Profile_Repo;

import org.json.JSONObject;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DoMyprofilePresenter {


    private  DoMyprofileView  view;

    public DoMyprofilePresenter(DoMyprofileView view) {
        this.view = view;
    }

    public void DoMyprofil( Context context) {
        Call<Profile_Repo> loginCall =  ApiManager.getApi(context).Domyprofile();

        loginCall.enqueue(new Callback<Profile_Repo>() {
            @Override
            public void onResponse(Call<Profile_Repo> call, Response<Profile_Repo> response) {

                if (response.isSuccessful() && response.body() != null && response.code() == 200) {
                    try {

                        view.onDoMyprofileSuccess(response.body(), response.message());
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                } else {
                    try {
                        String errorStr = response.errorBody().string();
                        JSONObject jsonObject = new JSONObject(errorStr);
                        String errorMsg = jsonObject.getString("status");
                        view.onDoMyprofileError(String.valueOf(response.code()));
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        view.onDoMyprofileError(String.valueOf(response.code()));
                    }

                }
            }

            @Override
            public void onFailure(Call<Profile_Repo> call, Throwable t) {
                view.onDoMyprofileFailure(t);
            }
        });

    }

    public interface DoMyprofileView {

        void onDoMyprofileError(String message);

        void onDoMyprofileSuccess( Profile_Repo response, String message);

        void onDoMyprofileFailure(Throwable t);
    }
}
