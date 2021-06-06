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
import com.serviingo.serviingo.Common.SimpleHTTPConnection;
import com.serviingo.serviingo.Database.AppSettings;
import com.serviingo.serviingo.R;
import com.serviingo.serviingo.utils.AppUrls;
import com.serviingo.serviingo.utils.AppUtils;
import com.serviingo.serviingo.view.BaseActivity;

import org.json.JSONObject;

public class ContactUsActivity extends BaseActivity implements View.OnClickListener {
    TextView tvLoaction,tvCall,tvEmail,tvSubmit;
    ImageView logo_Image;
    TextView tv_home,tvreferdesc;
    ImageView ivback, ivNav,referImageview;
    String pinterest_linklink,facebook_linklink,linkedin_linklink,youtube_linklink,insta_linklink;
    public static ScrollView scrollView;
    RelativeLayout rlDashBoard, rr_home, rr_category, rr_recharge, rrInsurance,
            rr_About, rr_Contact, rr_register, rrsignin;
    EditText etPostName,etPostEmail,etPostPhone,etPostSubject,ACTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitycontactus);

        findViewById();
    }

    private void findViewById() {
        tvLoaction=findViewById(R.id.tvLoaction);
        tvCall=findViewById(R.id.tvCall);
        tvEmail=findViewById(R.id.tvEmail);
        logo_Image=findViewById(R.id.logo_Image);
        tvreferdesc=findViewById(R.id.tvreferdesc);

//        referImageview=findViewById(R.id.referImageview);
        etPostName=findViewById(R.id.etPostName);
        etPostEmail=findViewById(R.id.etPostEmail);
        etPostPhone=findViewById(R.id.etPostPhone);
        etPostSubject=findViewById(R.id.etPostSubject);
        tvSubmit=findViewById(R.id.tvSubmit);
        ACTV=findViewById(R.id.ACTV);


        tv_home = findViewById(R.id.tv_home);
        ivback = findViewById(R.id.ivBack);
        ivNav = findViewById(R.id.ivNav);
        scrollView = findViewById(R.id.scroll_side_menu);
        rlDashBoard = findViewById(R.id.rlDashBoard);
        rr_home = findViewById(R.id.rr_home);
        rr_category = findViewById(R.id.rr_category);
        rr_recharge = findViewById(R.id.rr_recharge);
        rrInsurance = findViewById(R.id.rrInsurance);
        rr_About    = findViewById(R.id.rr_About);
        rr_Contact  = findViewById(R.id.rr_Contact);
        rr_register = findViewById(R.id.rr_register);
        rrsignin    = findViewById(R.id.rrsignin);

        setOnclickistener();
        if (SimpleHTTPConnection.isNetworkAvailable( mActivity )) {
            getContactApi();

        } else {
            AppUtils.showErrorMessage( tvreferdesc, "Please Check Your Internet Connection", mActivity );
        }
    }

    private void setOnclickistener() {

        tvSubmit.setOnClickListener(this);

        tv_home.setOnClickListener(this);
        ivNav.setOnClickListener(this);
        ivback.setOnClickListener(this);
        rlDashBoard.setOnClickListener(this);
        rr_home.setOnClickListener(this);
        rr_category.setOnClickListener(this);
        rr_recharge.setOnClickListener(this);
        rrInsurance.setOnClickListener(this);
        rr_About   . setOnClickListener(this);
        rr_Contact  .setOnClickListener(this);
        rr_register .setOnClickListener(this);
        rrsignin    .setOnClickListener(this);





    }




    private void PostContactApi() {
        String url = AppUrls.PostContact;
        AndroidNetworking.post(url)
                .addBodyParameter("name",    etPostName.getText().toString().trim())
                .addBodyParameter("email",   etPostEmail.getText().toString().trim())
                .addBodyParameter("mobile",  etPostPhone.getText().toString().trim())
                .addBodyParameter("subject", etPostSubject.getText().toString().trim())
                .addBodyParameter("message",   ACTV.getText().toString().trim())
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
                            Toast.makeText(mActivity,String.valueOf(error.getErrorDetail()), Toast.LENGTH_SHORT).show();
                            //  AppUtils.showErrorMessage(etNewpassword, String.valueOf(error.getErrorDetail()), mActivity);
                        }
                    }
                });
    }

    private void parseJsonResponse(JSONObject response) {
        try {
            String message=response.getString("message");
            Toast.makeText(this, ""+message, Toast.LENGTH_SHORT).show();
          if (response.getString("status").equals("true")) {
                JSONObject jsonObject = response.getJSONObject("data");
                JSONObject contact_infoobj=jsonObject.getJSONObject("query");
                String name         =contact_infoobj.getString("name");
                String email        =contact_infoobj.getString("email");
                String mobile_number=contact_infoobj.getString("mobile_number");
                String subject      =contact_infoobj.getString("subject");
                String query        =contact_infoobj.getString("query");
                String updated_at   =contact_infoobj.getString("updated_at");
                String created_at   =contact_infoobj.getString("created_at");
                String id           =contact_infoobj.getString("id");

              Toast.makeText(this, name+email+mobile_number+subject+query+updated_at+created_at+id, Toast.LENGTH_SHORT).show();
           startActivity(new Intent(mActivity,RefundPolicy.class));
            } else {
            }
        } catch (Exception e) {
            Log.v("error", String.valueOf(e));
        }
    }


     private void getContactApi() {
        String url = AppUrls.getcontactcontent;
        Log.v("faq_url", url);
        AndroidNetworking.get(url)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        parsePostJsonResponse(response);
                    }
                    @Override
                    public void onError(ANError error) {
                        Log.v("data", error.getErrorBody());
                        Log.v("data", error.getErrorDetail());
                    }
                });
    }

    private void parsePostJsonResponse(JSONObject response) {
        try {
            String message=response.getString("message");
            Toast.makeText(this, ""+message, Toast.LENGTH_SHORT).show();
            if (response.getString("status").equals("true")) {
                JSONObject jsonObject = response.getJSONObject("data");
                JSONObject contact_infoobj=jsonObject.getJSONObject("contact_info");
                String contact_infoid=contact_infoobj.getString("id");

                String email=contact_infoobj.getString("email");
                tvEmail.setText(email);
                String contact_infoaddress=contact_infoobj.getString("address");
                tvLoaction.setText(contact_infoaddress);
                String numbers=contact_infoobj.getString("numbers");
                tvCall.setText(numbers);

                String contact_infodescription=contact_infoobj.getString("created_at");
                String section1_heading=contact_infoobj.getString("updated_at");

                JSONObject refer_and_earnobj=jsonObject.getJSONObject("refer_and_earn");
                String id=refer_and_earnobj.getString("id");
                String slug=refer_and_earnobj.getString("slug");
                String heading=refer_and_earnobj.getString("heading");
                String title=refer_and_earnobj.getString("title");
                tvreferdesc.setText(title);
                String description=refer_and_earnobj.getString("description");
                String images=refer_and_earnobj.getString("images");

                String categories_ids=refer_and_earnobj.getString("categories_ids");
                String created_at=refer_and_earnobj.getString("created_at");
                String updated_at=refer_and_earnobj.getString("updated_at");



                JSONObject facebook_linkobj=jsonObject.getJSONObject("facebook_link");
                String facebook_linkid=facebook_linkobj.getString("id");
                String facebook_linkslug=facebook_linkobj.getString("slug");
                facebook_linklink=facebook_linkobj.getString("link");
                String facebook_linkcreated_at=facebook_linkobj.getString("created_at");
                String facebook_linkupdated_at=facebook_linkobj.getString("updated_at");

                JSONObject twitter_linkobj=jsonObject.getJSONObject("twitter_link");
                String twitter_linkid=twitter_linkobj.getString("id");
                String twitter_linkslug=twitter_linkobj.getString("slug");
                String twitter_linklink=twitter_linkobj.getString("link");
                String twitter_linkcreated_at=twitter_linkobj.getString("created_at");
                String twitter_linkupdated_at=twitter_linkobj.getString("updated_at");

                JSONObject insta_linkobj=jsonObject.getJSONObject("insta_link");
                String insta_linkid=twitter_linkobj.getString("id");
                String insta_linkslug=twitter_linkobj.getString("slug");
                insta_linklink=twitter_linkobj.getString("link");
                String insta_linkcreated_at=twitter_linkobj.getString("created_at");
                String insta_linkupdated_at=twitter_linkobj.getString("updated_at");

                JSONObject youtube_linkobj=jsonObject.getJSONObject("youtube_link");
                String youtube_linkid=youtube_linkobj.getString("id");
                String youtube_linkslug=youtube_linkobj.getString("slug");
                youtube_linklink=youtube_linkobj.getString("link");
                String youtube_linkcreated_at=youtube_linkobj.getString("created_at");
                String youtube_linkupdated_at=youtube_linkobj.getString("updated_at");

                JSONObject linkedin_linkobj=jsonObject.getJSONObject("linkedin_link");
                String linkedin_linkid=        linkedin_linkobj.getString("id");
                String linkedin_linkslug=      linkedin_linkobj.getString("slug");
                linkedin_linklink=      linkedin_linkobj.getString("link");
                String linkedin_linkcreated_at=linkedin_linkobj.getString("created_at");
                String linkedin_linkupdated_at=linkedin_linkobj.getString("updated_at");

                JSONObject pinterest_linkobj=jsonObject.getJSONObject("pinterest_link");
                String pinterest_linkid=        pinterest_linkobj.getString("id");
                String pinterest_linkslug=      pinterest_linkobj.getString("slug");
                pinterest_linklink=      pinterest_linkobj.getString("link");
                String pinterest_linkcreated_at=pinterest_linkobj.getString("created_at");
                String pinterest_linkupdated_at=pinterest_linkobj.getString("updated_at");

            } else {
            }
        } catch (Exception e) {
            Log.v("error", String.valueOf(e));
        }
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
           /* case R.id.ivPinterest:
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse(pinterest_linklink));
                startActivity(intent);
                break;
            case R.id.ivYoutube:
                Intent intent2 = new Intent();
                intent2.setAction(Intent.ACTION_VIEW);
                intent2.addCategory(Intent.CATEGORY_BROWSABLE);
                intent2.setData(Uri.parse(youtube_linklink));
                startActivity(intent2);
                break;
            case R.id.ivinsta:
                Intent intent3 = new Intent();
                intent3.setAction(Intent.ACTION_VIEW);
                intent3.addCategory(Intent.CATEGORY_BROWSABLE);
                intent3.setData(Uri.parse(insta_linklink));
                startActivity(intent3);
                break;
            case R.id.ivtwitter:
                Intent intent4 = new Intent();
                intent4.setAction(Intent.ACTION_VIEW);
                intent4.addCategory(Intent.CATEGORY_BROWSABLE);
                intent4.setData(Uri.parse(linkedin_linklink));
                startActivity(intent4);
                break;
            case R.id.ivfacebook:
                Intent intent5 = new Intent();
                intent5.setAction(Intent.ACTION_VIEW);
                intent5.addCategory(Intent.CATEGORY_BROWSABLE);
                intent5.setData(Uri.parse(pinterest_linklink));
                startActivity(intent5);
                break;*/
                case R.id.tvSubmit:
                    if(etPostName.getText().toString().trim().isEmpty()){
                        Toast.makeText(this, "Enter Email", Toast.LENGTH_SHORT).show();
                    }else if(etPostEmail.getText().toString().trim().isEmpty()){
                        Toast.makeText(this, "Enter  password", Toast.LENGTH_SHORT).show();
                    }else if(etPostPhone.getText().toString().trim().isEmpty()){
                        Toast.makeText(this, "Enter  password", Toast.LENGTH_SHORT).show();
                    }else if(etPostSubject.getText().toString().trim().isEmpty()){
                        Toast.makeText(this, "Enter  password", Toast.LENGTH_SHORT).show();
                    }else if(ACTV.getText().toString().trim().isEmpty()){
                        Toast.makeText(this, "Enter  password", Toast.LENGTH_SHORT).show();
                    }else {
                        if (SimpleHTTPConnection.isNetworkAvailable( mActivity )) {
                            PostContactApi();

                        } else {
                            AppUtils.showErrorMessage( tvreferdesc, "Please Check Your Internet Connection", mActivity );
                        }
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
        /* ivDrop.setVisibility( View.GONE );//scrollView.setVisibility(View.GONE);
         view.setVisibility( View.GONE );*/
                        AppUtils.collapse(scrollView);
                        break;
                    }
     /*ivDrop.setVisibility( View.VISIBLE );
     view.setVisibility( View.VISIBLE );*/
                    AppUtils.expand(scrollView);
                }

                break;

            case R.id.ivBack:
                onBackPressed();
                break;

//            case R.id.ivNav:
//                if (AppSettings.getString(AppSettings.id).isEmpty()) {
//                    if (scrollView.getVisibility() == View.VISIBLE) {
//                        AppUtils.collapse(scrollView);
//                        break;
//                    }
//                    AppUtils.expand(scrollView);
//                } else {
//                    if (scrollView.getVisibility() == View.VISIBLE) {
//        /* ivDrop.setVisibility( View.GONE );//scrollView.setVisibility(View.GONE);
//         view.setVisibility( View.GONE );*/
//                        AppUtils.collapse(scrollView);
//                        break;
//                    }
//     /*ivDrop.setVisibility( View.VISIBLE );
//     view.setVisibility( View.VISIBLE );*/
//                    AppUtils.expand(scrollView);
//                }
//
//                break;
//            case R.id.ivBack:
//                onBackPressed();
//                break;
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
            case R.id. rr_About:
                startActivity(new Intent(this, AboutUs.class));
                break;
            case R.id. rr_Contact:
                startActivity(new Intent(this, ContactUsActivity.class));
                break;
            case R.id.rr_register:
                startActivity(new Intent(this, VenderReg.class));
                break;
            case R.id.rrsignin:
                startActivity(new Intent(this, LoginActivity.class));
                break;

            case R.id.tv_home:
                finish();
                startActivity(new Intent(this, MainActivity.class));
                break;

        }
    }


    @Override
    public void onBackPressed() {
        finish();
    }
}