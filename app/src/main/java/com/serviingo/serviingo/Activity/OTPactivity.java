package com.serviingo.serviingo.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.rjesture.startupkit.ApiStrings;
import com.rjesture.startupkit.AppTools;
import com.serviingo.serviingo.R;
import com.serviingo.serviingo.utils.AppUrls;
import com.serviingo.serviingo.utils.AppUtils;
import com.serviingo.serviingo.view.BaseActivity;

import org.json.JSONObject;

import static com.rjesture.startupkit.ApiStrings.apiErrorException;
import static com.rjesture.startupkit.ApiStrings.defResponse;
import static com.rjesture.startupkit.AppTools.handleCatch;
import static com.rjesture.startupkit.AppTools.showToast;
import static com.serviingo.serviingo.R.drawable.globalloader;

public class OTPactivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.otpactivity);
    }
/*    private void getSubCategoryDetailsApi() {

        AppTools.showGifDialog(mActivity, globalloader);
        ApiStrings.setApiString("getSubCategoryDetails");
        String url = AppUrls.getSubCategoryDetails;
        Log.v(ApiStrings.apiUrl, url);
        JSONObject json = new JSONObject();
        JSONObject json_data = new JSONObject();

        try {
*//*
            json_data.put("mobile", getTextInputEditTextData(tif_reg_mob_no));
            json.put("shopmet", json_data);
*//*
            Log.v(ApiStrings.apiJson, json.toString());
        } catch (Exception e) {
            showToast(mActivity, defResponse);
            Log.v(apiErrorException, e.getMessage());
            handleCatch(e);
        }


        AndroidNetworking.post(url)
                .addJSONObjectBody(json)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        parsegetSubCategoryDetailsdApi(response);
                    }

                    @Override
                    public void onError(ANError error) {

                        AppUtils.hideDialog();
                        // handle error
                        if (error.getErrorCode() != 0) {
                            showToast(mActivity, defResponse);
                            Log.v(ApiStrings.apiErrorCode, "onError errorCode : " + error.getErrorCode());
                            Log.v(ApiStrings.apiErrorBody, "onError errorBody : " + error.getErrorBody());
                            Log.v(ApiStrings.apiErrorDetail, "onError errorDetail : " + error.getErrorDetail());
                        } else {
                            showToast(mActivity, defResponse);
                            Log.v(apiErrorException, error.getMessage());

                        }
                    }
                });
    }

    private void parsegetSubCategoryDetailsdApi(JSONObject response) {

        Log.d(ApiStrings.apiResponse, response.toString());
        try {
            JSONObject jsonObject = response.getJSONObject("ecommerce");
            AppUtils.hideDialog();
            showToast(mActivity, jsonObject.getString("res_msg"));
            if (jsonObject.getString("res_code").equalsIgnoreCase("1"))
                startActivity(new Intent(mActivity, OtpVerification.class).putExtra(AppConstants.userMobile, getTextInputEditTextData(tif_reg_mob_no)));

        } catch (Exception e) {
            Log.v(apiErrorException, e.getMessage());
            showToast(mActivity, defResponse);
            handleCatch(e);
        }
    }*/
}
