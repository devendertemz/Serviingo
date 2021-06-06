package com.serviingo.serviingo.CommonControlerApi;


import android.content.Context;

import com.serviingo.serviingo.Rtrofit.ApiClientt;
import com.serviingo.serviingo.Rtrofit.ApiManager;
import com.serviingo.serviingo.modelrepo.Responsee.Profile_Repo;
import com.serviingo.serviingo.modelrepo.request.UserReg_Request;

import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DoLogoutPresenter {

    private DoLogoutView  view;

    public DoLogoutPresenter(DoLogoutView view) {
        this.view = view;
    }

    public void DoLogout(Context context) {
        Call<ResponseBody> loginCall =  ApiManager.getApi(context).DoLogout();

        loginCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                if (response.isSuccessful() && response.body() != null && response.code() == 200) {
                    try {

                        view.onDoLogoutSuccess(response.body(), response.message());
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                } else {
                    try {
                        String errorStr = response.errorBody().string();
                        JSONObject jsonObject = new JSONObject(errorStr);
                        String errorMsg = jsonObject.getString("status");
                        view.onDoLogoutError(errorMsg);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        view.onDoLogoutError(ex.getLocalizedMessage());
                    }

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                view.onDoLogoutFailure(t);
            }
        });

    }

    public interface DoLogoutView {

        void onDoLogoutError(String message);

        void onDoLogoutSuccess( ResponseBody response, String message);

        void onDoLogoutFailure(Throwable t);
    }
}
