package com.serviingo.serviingo.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.serviingo.serviingo.Common.Preferences;
import com.serviingo.serviingo.Common.SimpleHTTPConnection;
import com.serviingo.serviingo.Database.AppSettings;
import com.serviingo.serviingo.R;
import com.serviingo.serviingo.storage.SharedPrefManager;
import com.serviingo.serviingo.storage.User_login;
import com.serviingo.serviingo.utils.AppUrls;
import com.serviingo.serviingo.utils.AppUtils;
import com.serviingo.serviingo.view.BaseActivity;

import org.json.JSONObject;

import static com.rjesture.startupkit.AppTools.getTextInputEditTextData;

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private static final int RC_SIGN_IN = 1001;
    public static TextView tvMessage;
    GoogleSignInClient googleSignInClient;
    EditText etPassword, etEmail;
    LinearLayout tvgoogleplus;
    TextView tvskip,tvSubmit, requestOtp;
    private FirebaseAuth firebaseAuth;
    Preferences pref;
    // private CallbackManager callbackManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginactivity);
        //callbackManager = CallbackManager.Factory.create();
        configureGoogleClient();
        setui();
    }

    private void setui() {
        etPassword = findViewById(R.id.etPassword);
        etEmail = findViewById(R.id.etEmail);
        tvskip = findViewById(R.id.tvskip);
        tvMessage = findViewById(R.id.tvMessage);
        requestOtp = findViewById(R.id.requestOtp);
//        tvgoogleplus = findViewById(R.id.tvgoogleplus);
        tvMessage = findViewById(R.id.tvMessage);
        tvSubmit = findViewById(R.id.tvSubmit);
        setOnClickListener();
        pref                  =new Preferences(mActivity);
        /*if(!pref.get(Constants.merchant_id).equals(""))
            {
                jsonObject.put("oxdisno",pref.get(Constants.merchant_oxdis));
            }
            else if(!pref.get(Constants.customer_id).equals(""))
            {
                jsonObject.put("oxdisno",pref.get(Constants.customer_oxdis_login));

            }


             pref.set(Constants.customer_id,id);
        pref.commit();
*/
    }

    private void setOnClickListener() {
        requestOtp.setOnClickListener(this);
//        tvgoogleplus.setOnClickListener(this);
        tvSubmit.setOnClickListener(this);
        tvskip.setOnClickListener(this);
        tvMessage.setOnClickListener(this);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // callbackManager.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);

                //  showToastMessage("Google Sign in Succeeded");
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.e("TAG", "Google sign in failed", e);
                // showToastMessage("Google Sign in Failed " + e);
            }
        }
    }

    private void signInToGoogle() {
        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void firebaseAuthWithGoogle(final GoogleSignInAccount account) {
        Log.d("", "firebaseAuthWithGoogle:" + account.getId());
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            String email = user.getEmail();
                            String name = user.getDisplayName();
                            String phoneNumber = user.getPhoneNumber();
                            // registerUser(name,email,phoneNumber,"Google");

                            // showToastMessage("Firebase Authentication Succeeded ");
                            //launchMainActivity(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("TAG", "signInWithCredential:failure", task.getException());
                            // showToastMessage("Firebase Authentication failed:" + task.getException());
                        }
                    }
                });
    }

    private void configureGoogleClient() {
        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                // for the requestIdToken, this is in the values.xml file that
                // is generated from your google-services.json
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        // Build a GoogleSignInClient with the options specified by gso.
        googleSignInClient = GoogleSignIn.getClient(this, gso);
        // Set the dimensions of the sign-in button.
//        SignInButton signInButton = findViewById(R.id.sign_in_button);
//        signInButton.setSize(SignInButton.SIZE_WIDE);
        // Initialize Firebase Auth
        firebaseAuth = FirebaseAuth.getInstance();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.requestOtp:
                startActivity(new Intent(LoginActivity.this, OTPactivity.class));
                break;

//            case R.id.tvgoogleplus:
//                signInToGoogle();
//                break;

            case R.id.tvskip:
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                break;

            case R.id.tvMessage:
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
                break;

            case R.id.tvSubmit:
                if (getTextInputEditTextData(etEmail).isEmpty()) {
                    Toast.makeText(this, "Enter Email/Phone Number", Toast.LENGTH_SHORT).show();
                } else if (getTextInputEditTextData(etPassword).isEmpty()) {
                    Toast.makeText(this, "Enter  password", Toast.LENGTH_SHORT).show();
                } else {

                    if (SimpleHTTPConnection.isNetworkAvailable(mActivity)) {
                        PostSignInApi();
                    } else {
                        AppUtils.showErrorMessage(tvMessage, "Please check your internet connection.", mActivity);
                    }
                }
                break;

        }
    }

    private void PostSignInApi() {
        AppUtils.showRequestDialog(mActivity);
        String url = AppUrls.PostSignIn;
        AndroidNetworking.post(url)
                .addBodyParameter("email", etEmail.getText().toString().trim())
                .addBodyParameter("password", etPassword.getText().toString().trim())
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        parseJsonResponse(response);
                    }

                    @Override
                    public void onError(ANError error) {
                        // handle error
                        if (error.getErrorCode() != 0) {

                            Log.d("onError errorCode ", "onError errorCode : " + error.getErrorCode());
                            Log.d("onError errorBody", "onError errorBody : " + error.getErrorBody());
                            Log.d("onError errorDetail", "onError errorDetail : " + error.getErrorDetail());
                        } else {
                            Toast.makeText(mActivity, String.valueOf(error.getErrorDetail()), Toast.LENGTH_SHORT).show();
                            //  AppUtils.showErrorMessage(etNewpassword, String.valueOf(error.getErrorDetail()), mActivity);
                        }
                    }
                });
    }


    private void parseJsonResponse(JSONObject response) {
        try {
            AppUtils.hideDialog();
            String message = response.getString("message");
            Toast.makeText(this, "" + message, Toast.LENGTH_SHORT).show();
            if (response.getString("status").equals("true")) {
                JSONObject jsonObject = response.getJSONObject("data");
                String access_token = jsonObject.getString("access_token");
                pref.set(AppSettings.ACCESSID,access_token);
                pref.commit();
                JSONObject logojsonObject = jsonObject.getJSONObject("user");
                String id = logojsonObject.getString("id");
                String name = logojsonObject.getString("name");
                String email = logojsonObject.getString("email");
                String mobile_number = logojsonObject.getString("mobile_number");
                String role = logojsonObject.getString("role");
                String state_id = logojsonObject.getString("state_id");
                String city_id = logojsonObject.getString("city_id");
                String category_id = logojsonObject.getString("category_id");
                String sub_category_id = logojsonObject.getString("sub_category_id");
                String service_id = logojsonObject.getString("service_id");
                String profile_pic = logojsonObject.getString("profile_pic");
                String gender = logojsonObject.getString("gender");
                String pincode = logojsonObject.getString("pincode");
                String address = logojsonObject.getString("address");
                String first_name = logojsonObject.getString("first_name");
                String last_name = logojsonObject.getString("last_name");
                String business_type = logojsonObject.getString("business_type");
                String website = logojsonObject.getString("website");
                String referral_id = logojsonObject.getString("referral_id");
                String full_address = logojsonObject.getString("full_address");
                String pan_card_number = logojsonObject.getString("pan_card_number");
                String aadhaar_card_number = logojsonObject.getString("aadhaar_card_number");
                String business_proof_number = logojsonObject.getString("business_proof_number");
                String business_address = logojsonObject.getString("business_address");
                String account_type = logojsonObject.getString("account_type");
                String account_name = logojsonObject.getString("account_name");
                String account_number = logojsonObject.getString("account_number");
                String ifsc_code = logojsonObject.getString("ifsc_code");
                String bank_name = logojsonObject.getString("bank_name");
                String bank_branch = logojsonObject.getString("bank_branch");
                String pan_card_document = logojsonObject.getString("pan_card_document");
                String aadhaar_card_front = logojsonObject.getString("aadhaar_card_front");
                String aadhaar_card_back = logojsonObject.getString("aadhaar_card_back");
                String business_proof_document = logojsonObject.getString("business_proof_document");
                String cancelled_cheque_img = logojsonObject.getString("cancelled_cheque_img");
                String photographs = logojsonObject.getString("photographs");
                String father_name = logojsonObject.getString("father_name");
                String dob = logojsonObject.getString("dob");
                String remaining_loan = logojsonObject.getString("remaining_loan");
                String total_loan = logojsonObject.getString("total_loan");
                String wallet_amount = logojsonObject.getString("wallet_amount");
                String profile_photo_path = logojsonObject.getString("profile_photo_path");
                String current_team_id = logojsonObject.getString("current_team_id");
                String email_verified_at = logojsonObject.getString("email_verified_at");
                String status = logojsonObject.getString("status");
                String landmark = logojsonObject.getString("landmark");
                String country_id = logojsonObject.getString("country_id");
                String other_documents = logojsonObject.getString("other_documents");
                String unique_id = logojsonObject.getString("unique_id");
                String updated_at = logojsonObject.getString("updated_at");
                String deleted_at = logojsonObject.getString("deleted_at");
                String created_at = logojsonObject.getString("created_at");

               // AppSettings.putString(AppSettings.id, logojsonObject.getString("id"));

               pref.set(AppSettings.id,id);
                pref.commit();
                pref.set(AppSettings.loginCheck,"true");
                pref.commit();
                String profile_photo_url = logojsonObject.getString("profile_photo_url");

                User_login user_login = new User_login(1,id,name,mobile_number,email,role,access_token);

                SharedPrefManager.getInstance(this)
                        .saveuser(user_login);
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            } else {
            }
        } catch (Exception e) {
            Log.v("error", String.valueOf(e));
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (SharedPrefManager.getInstance(this).isLoggedin()) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

            startActivity(intent);
        }
    }
}
