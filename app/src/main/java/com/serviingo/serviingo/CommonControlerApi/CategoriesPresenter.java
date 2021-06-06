package com.serviingo.serviingo.CommonControlerApi;


import com.serviingo.serviingo.Rtrofit.ApiClientt;

import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoriesPresenter {

    private CategoriesView view;

    public CategoriesPresenter(CategoriesView view) {
        this.view = view;
    }

    public void CategorieUser() {
        Call<ResponseBody> loginCall =  ApiClientt.getUserService().Getcategory();

        loginCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                if (response.isSuccessful() && response.body() != null && response.code() == 200) {
                    try {

                        view.onLoginSuccess(response.body(), response.message());
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                } else {
                    try {
                        String errorStr = response.errorBody().string();
                        JSONObject jsonObject = new JSONObject(errorStr);
                        String errorMsg = jsonObject.getString("status");
                        view.onLoginError(errorMsg);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        view.onLoginError(ex.getLocalizedMessage());
                    }

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                view.onLoginFailure(t);
            }
        });

    }

    public interface CategoriesView {

        void onLoginError(String message);

        void onLoginSuccess( ResponseBody response, String message);

        void onLoginFailure(Throwable t);
    }
}
