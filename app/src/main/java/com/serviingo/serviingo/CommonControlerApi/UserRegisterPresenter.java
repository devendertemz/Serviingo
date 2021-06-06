package com.serviingo.serviingo.CommonControlerApi;


import com.serviingo.serviingo.Rtrofit.ApiClientt;
import com.serviingo.serviingo.modelrepo.request.UserReg_Request;

import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRegisterPresenter {

    private UserRegisterView  view;

    public UserRegisterPresenter(UserRegisterView view) {
        this.view = view;
    }

    public void UserRegisterPresenter(UserReg_Request userReg_request) {
        Call<ResponseBody> loginCall =  ApiClientt.getUserService().DoUserReg(userReg_request);

        loginCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                if (response.isSuccessful() && response.body() != null && response.code() == 200) {
                    try {

                        view.onUserRegisterSuccess(response.body(), response.message());
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                } else {
                    try {
                        String errorStr = response.errorBody().string();
                        JSONObject jsonObject = new JSONObject(errorStr);
                        String errorMsg = jsonObject.getString("status");
                        view.onUserRegisterError(errorMsg);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        view.onUserRegisterError(ex.getLocalizedMessage());
                    }

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                view.onUserRegisterFailure(t);
            }
        });

    }

    public interface UserRegisterView {

        void onUserRegisterError(String message);

        void onUserRegisterSuccess( ResponseBody response, String message);

        void onUserRegisterFailure(Throwable t);
    }
}
