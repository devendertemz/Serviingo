package com.serviingo.serviingo.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.irozon.sneaker.Sneaker;
import com.rjesture.startupkit.AppTools;
import com.serviingo.serviingo.Common.SimpleHTTPConnection;
import com.serviingo.serviingo.CommonControlerApi.ChangePasswordPresenter;
import com.serviingo.serviingo.Database.AppSettings;
import com.serviingo.serviingo.R;
import com.serviingo.serviingo.modelrepo.request.Changepassword_request;
import com.serviingo.serviingo.utils.AppUrls;
import com.serviingo.serviingo.utils.AppUtils;
import com.serviingo.serviingo.view.BaseActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;

import static com.serviingo.serviingo.R.drawable.globalloader;

public class ChangePasswordActivity extends BaseActivity implements View.OnClickListener, ChangePasswordPresenter .ChangePasswordView{
    EditText etoldPassword,etnewPassword,etconfirmMnewPassword;
    TextView tvUpdate;
    ImageView imageView, ivBack, ivNav;
    RelativeLayout rlDashBoard, rr_home, rr_category, rr_recharge, rrInsurance,
            rr_About, rr_Contact, rr_register, rrsignin;
    public static ScrollView scrollView;

    ChangePasswordPresenter passwordPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.changepassword);
        setui();
    }

    private void setui() {
        etoldPassword=findViewById(R.id.etoldPassword);
        etnewPassword=findViewById(R.id.etnewPassword);
        tvUpdate=findViewById(R.id.tvUpdate);
        etconfirmMnewPassword=findViewById(R.id.etconfirmMnewPassword);

        ivNav = findViewById(R.id.ivNav);
        ivBack = findViewById(R.id.ivBack);
        scrollView = findViewById(R.id.scroll_side_menu);
        rlDashBoard = findViewById(R.id.rlDashBoard);
        rr_home = findViewById(R.id.rr_home);
        rr_category = findViewById(R.id.rr_category);
        rr_recharge = findViewById(R.id.rr_recharge);
        rrInsurance = findViewById(R.id.rrInsurance);
        rr_About = findViewById(R.id.rr_About);
        rr_Contact = findViewById(R.id.rr_Contact);
        rr_register = findViewById(R.id.rr_register);
        rrsignin = findViewById(R.id.rrsignin);
        passwordPresenter=new ChangePasswordPresenter(this);

        setOnClickListener();
    }

    private void setOnClickListener() {
        tvUpdate.setOnClickListener(this);

        rlDashBoard.setOnClickListener(this);
        rr_home.setOnClickListener(this);
        rr_category.setOnClickListener(this);
        rr_recharge.setOnClickListener(this);
        rrInsurance.setOnClickListener(this);
        rr_About.setOnClickListener(this);
        rr_Contact.setOnClickListener(this);
        rr_register.setOnClickListener(this);
        rrsignin.setOnClickListener(this);
        ivBack.setOnClickListener(this);
        ivNav.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tvUpdate:
                if (etoldPassword.getText().toString().trim().isEmpty()) {
                    Sneaker.with(this)
                            .setTitle("Enter old Password")
                            .setMessage("")
                            .sneakError();
                } else if (etnewPassword.getText().toString().trim().length()<4) {
                    Sneaker.with(this)
                            .setTitle("The password must be at least 4 characters.")
                            .setMessage("")
                            .sneakError();
                } else if (!etconfirmMnewPassword.getText().toString().trim().equals(etnewPassword.getText().toString().trim())) {
                    Sneaker.with(this)
                            .setTitle("Confirm password not match")
                            .setMessage("")
                            .sneakError();
                } else {

                    AppTools.showGifDialog(mActivity, globalloader);


                    Changepassword_request changepassword_request=new Changepassword_request(etoldPassword.getText().toString().trim(),
                            etnewPassword.getText().toString().trim(),
                            etconfirmMnewPassword.getText().toString().trim());
                    passwordPresenter.ChangePassword(this,changepassword_request);

                 /*   if (SimpleHTTPConnection.isNetworkAvailable(mActivity)) {
                        PostUpdatePassword();
                    } else {
                        AppUtils.showErrorMessage(tvUpdate, "Please check your internet connection.", mActivity);
                    }*/
                }
                break;

            case R.id.ivNav:
                if (AppSettings.getString(AppSettings.id).isEmpty()) {
                    if (scrollView.getVisibility() == View.VISIBLE) {
                        AppUtils.collapse(scrollView);
                        break;
                    }
                    AppUtils.expand(scrollView);
                } else {
                    if (scrollView.getVisibility() == View.VISIBLE) {

                        AppUtils.collapse(scrollView);
                        break;
                    }
                    AppUtils.expand(scrollView);
                }

                break;
            case R.id.ivBack:
                onBackPressed();
                break;
            case R.id.rlDashBoard:
                //samepage
                break;
            case R.id.rr_home:
                //My Booking
                break;
            case R.id.rr_category:
                //My Profile
                break;
            case R.id.rr_recharge:
                //My Wallet
                break;
            case R.id.rrInsurance:
                //Logout
                break;
            case R.id.rr_About:
                startActivity(new Intent(this, AboutUs.class));
                break;
            case R.id.rr_Contact:
                startActivity(new Intent(this, ContactUsActivity.class));
                break;
            case R.id.rr_register:
                startActivity(new Intent(this, SignUpActivity.class));
                break;
            case R.id.rrsignin:
                startActivity(new Intent(this, LoginActivity.class));
                break;



        }
    }

    @Override
    public void onChangePasswordError(String message) {
        AppTools.hideGifDialog();

        Sneaker.with(this)
                .setTitle(message)
                .setMessage("")
                .sneakError();

    }

    @Override
    public void onChangePasswordSuccess(ResponseBody response, String message) {


        if (message.equalsIgnoreCase("ok"))
        {

            try {
                String s=response.string();
                JSONObject jsonObject=new JSONObject(s);
                Toast.makeText(this, jsonObject.getString("message")+"", Toast.LENGTH_SHORT).show();
                AppTools.hideGifDialog();

                startActivity( new Intent(ChangePasswordActivity.this,MainActivity.class));

            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }


        }

    }

    @Override
    public void onChangePasswordFailure(Throwable t) {
        AppTools.hideGifDialog();

        Sneaker.with(this)
                .setTitle(t.getLocalizedMessage())
                .setMessage("")
                .sneakError();
    }
}
