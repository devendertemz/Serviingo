package com.serviingo.serviingo.CommonControlerApi;


import android.content.Context;

import com.serviingo.serviingo.Rtrofit.ApiClientt;
import com.serviingo.serviingo.Rtrofit.ApiManager;
import com.serviingo.serviingo.modelrepo.request.Changepassword_request;

import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePasswordPresenter {

    private ChangePasswordView view;

    public ChangePasswordPresenter(ChangePasswordView view) {
        this.view = view;
    }

    public void ChangePassword(Context context, Changepassword_request changepassword_request) {
        Call<ResponseBody> loginCall = ApiManager.getApi(context).Doupdatepassword(changepassword_request);

        loginCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                if (response.isSuccessful() && response.body() != null && response.code() == 200) {
                    try {

                        view.onChangePasswordSuccess(response.body(), response.message());
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                } else if (response.code()==500){
                    try {
                        String errorStr = response.errorBody().string();
                        JSONObject jsonObject = new JSONObject(errorStr);
                      JSONObject jsonObject1= jsonObject.getJSONObject("message");

                        view.onChangePasswordError(jsonObject1.getString("error"));
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        view.onChangePasswordError(String.valueOf(response.code()));
                    }

                }else if (response.code()==401)
                {
                    try {
                        String errorStr = response.errorBody().string();
                        JSONObject jsonObject = new JSONObject(errorStr);
                        JSONObject jsonObject1= jsonObject.getJSONObject("message");

                        view.onChangePasswordError(jsonObject1.getString("error"));
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        view.onChangePasswordError(String.valueOf(response.code()));
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                view.onChangePasswordFailure(t);
            }
        });

    }

    public interface ChangePasswordView {

        void onChangePasswordError(String message);

        void onChangePasswordSuccess( ResponseBody response, String message);

        void onChangePasswordFailure(Throwable t);
    }
}
