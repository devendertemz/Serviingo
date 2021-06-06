package com.serviingo.serviingo.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.irozon.sneaker.Sneaker;
import com.rjesture.startupkit.AppTools;
import com.serviingo.serviingo.Common.SimpleHTTPConnection;
import com.serviingo.serviingo.CommonControlerApi.UserRegisterPresenter;
import com.serviingo.serviingo.CommonControlerApi.VandorRegPresenter;
import com.serviingo.serviingo.R;
import com.serviingo.serviingo.modelrepo.request.UserReg_Request;
import com.serviingo.serviingo.utils.AppUrls;
import com.serviingo.serviingo.utils.AppUtils;
import com.serviingo.serviingo.utils.CheckInternetConnection;
import com.serviingo.serviingo.view.BaseActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import okhttp3.ResponseBody;

import static com.serviingo.serviingo.R.drawable.globalloader;

public class SignUpActivity extends BaseActivity implements View.OnClickListener, UserRegisterPresenter.UserRegisterView {
    TextView tvSignup;
    EditText etName, etEmail, etPhoneNumber, etPassword, etConfirmPassword;
    UserRegisterPresenter userRegisterPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signupactivity);
        setui();

    }

    private void setui() {
        tvSignup = findViewById(R.id.tvSignup);
        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPhoneNumber = findViewById(R.id.etPhoneNumber);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        userRegisterPresenter=new UserRegisterPresenter(this);

        onClickListener();
    }

    private void onClickListener() {

        tvSignup.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvSignup:
                if (etName.getText().toString().trim().isEmpty()) {
                    Sneaker.with(this)
                            .setTitle("Enter your  name!")
                            .setMessage("")
                            .sneakError();
                } else if (etEmail.getText().toString().trim().isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(etEmail.getText().toString().trim()).matches()) {
                    Sneaker.with(this)
                            .setTitle("Enter your email!")
                            .setMessage("")
                            .sneakError();
                } else if (etPhoneNumber.getText().toString().length() != 10) {
                    Sneaker.with(this)
                            .setTitle("Please enter your 10 digit of mobile!")
                            .setMessage("")
                            .sneakError();
                } else if (etPassword.getText().toString().trim().isEmpty()) {
                    Sneaker.with(this)
                            .setTitle("Enter your password!")
                            .setMessage("")
                            .sneakError();
                } else if (!etConfirmPassword.getText().toString().trim().equals(etPassword.getText().toString().trim())) {
                    Sneaker.with(this)
                            .setTitle("confirm password not match!")
                            .setMessage("")
                            .sneakError();
                } else {
                    AppTools.showGifDialog(mActivity, globalloader);

                    UserReg_Request userReg_request = new UserReg_Request(etName.getText().toString().trim(), etEmail.getText().toString().trim(),
                            etPhoneNumber.getText().toString().trim(), etPassword.getText().toString().trim(),
                            etConfirmPassword.getText().toString().trim());
                    userRegisterPresenter.UserRegisterPresenter(userReg_request);

                    ///
                }
                break;
        }
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View view = getCurrentFocus();
        if (view != null && (ev.getAction() == MotionEvent.ACTION_UP || ev.getAction() == MotionEvent.ACTION_MOVE) && view instanceof EditText && !view.getClass().getName().startsWith("android.webkit.")) {
            int scrcoords[] = new int[2];
            view.getLocationOnScreen(scrcoords);
            float x = ev.getRawX() + view.getLeft() - scrcoords[0];
            float y = ev.getRawY() + view.getTop() - scrcoords[1];
            if (x < view.getLeft() || x > view.getRight() || y < view.getTop() || y > view.getBottom())
                ((InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow((this.getWindow().getDecorView().getApplicationWindowToken()), 0);
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //check Internet Connection

        new CheckInternetConnection(this).checkConnection();
    }

    @Override
    public void onUserRegisterError(String message) {
        AppTools.hideGifDialog();

        Sneaker.with(this)
                .setTitle(" Email OR  Mobile number already exist in our record, please try another.!")
                .setMessage("")
                .sneakError();

    }

    @Override
    public void onUserRegisterSuccess(ResponseBody response, String message) {
        AppTools.hideGifDialog();

        Log.e("onUserRegisterSuccess",message);
        Intent in=new Intent(SignUpActivity.this,LoginActivity.class);
        startActivity(in);
    }

    @Override
    public void onUserRegisterFailure(Throwable t) {
        Log.e("onUserRegisterFailure",t.getLocalizedMessage());
        AppTools.hideGifDialog();

        Sneaker.with(this)
                .setTitle(t.getLocalizedMessage())
                .setMessage("")
                .sneakError();
    }
}
