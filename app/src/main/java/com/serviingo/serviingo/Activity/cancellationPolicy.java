package com.serviingo.serviingo.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
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

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

public class cancellationPolicy extends BaseActivity implements View.OnClickListener {

    TextView tvreferdesc,cancellationDesc,cancellationheading;

    String pinterest_linklink,facebook_linklink,linkedin_linklink,youtube_linklink,insta_linklink;
    TextView tv_home;
    ImageView ivback, ivNav, referImageview;
    public static ScrollView scrollView;
    RelativeLayout rlDashBoard, rr_home, rr_category, rr_recharge, rrInsurance,
            rr_About, rr_Contact, rr_register, rrsignin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cancellationpollicy);

        findViewById();
    }

    private void findViewById() {

        cancellationDesc=findViewById(R.id.cancellationDesc);
        cancellationheading=findViewById(R.id.cancellationheading);
        tvreferdesc=findViewById(R.id.tvreferdesc);
//        referImageview=findViewById(R.id.referImageview);

        tv_home = findViewById(R.id.tv_home);
        ivback = findViewById(R.id.ivBack);
        ivNav = findViewById(R.id.ivNav);
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

        setOnClickListener();
        if (SimpleHTTPConnection.isNetworkAvailable( mActivity )) {
            getCancellation();

        } else {
            AppUtils.showErrorMessage( tvreferdesc, "Please Check Your Internet Connection", mActivity );
        }
    }

    private void setOnClickListener() {

        tv_home.setOnClickListener(this);
        ivNav.setOnClickListener(this);
        rlDashBoard.setOnClickListener(this);
        rr_home.setOnClickListener(this);
        rr_category.setOnClickListener(this);
        rr_recharge.setOnClickListener(this);
        rrInsurance.setOnClickListener(this);
        rr_About.setOnClickListener(this);
        rr_Contact.setOnClickListener(this);
        rr_register.setOnClickListener(this);
        rrsignin.setOnClickListener(this);
        ivback.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
          /*  case R.id.ivPinterest:
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



    private void getCancellation() {

        String url = AppUrls.getRefundCancellationPolicy;
        Log.v("faq_url", url);
        AndroidNetworking.get(url)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        parseJsonResponse(response);
                    }
                    @Override
                    public void onError(ANError error) {
                        Log.v("data", error.getErrorBody());
                        Log.v("data", error.getErrorDetail());
                    }
                });
    }

    private void parseJsonResponse(JSONObject response) {
        try {
            String message=response.getString("message");
            Toast.makeText(this, ""+message, Toast.LENGTH_SHORT).show();
            if (response.getString("status").equals("true")) {
                JSONObject jsonObject = response.getJSONObject("data");
                JSONObject refer_and_earnobj=jsonObject.getJSONObject("refer_and_earn");
                String id=refer_and_earnobj.getString("id");
                String slug=refer_and_earnobj.getString("slug");
                String heading=refer_and_earnobj.getString("heading");
                String title=refer_and_earnobj.getString("title");
                Log.v("titleCancellation",title);
                tvreferdesc.setText(title);

                String description=refer_and_earnobj.getString("description");
                String images=refer_and_earnobj.getString("images");
                Log.v("image",images);
             //  Picasso.get().load("http://7xhospitals.com/serviingo/public/home_images/Y21WbVpYSXRhV052Ymk1d2JtYz0yMDEyMDMwNzM3MDQucG5n210112064425.png").into(referImageview);
                String categories_ids=refer_and_earnobj.getString("categories_ids");
                String created_at=refer_and_earnobj.getString("created_at");
                String updated_at=refer_and_earnobj.getString("updated_at");


                JSONArray latest_servicesArray = jsonObject.getJSONArray("latest_services");
                for (int i = 0; i < latest_servicesArray.length(); i++) {
                    JSONObject productobject = latest_servicesArray.getJSONObject(i);
                    HashMap<String, String> addList = new HashMap();
                    addList.put("id", productobject.getString("id"));
                    addList.put("category_id", productobject.getString("category_id"));
                    addList.put("sub_category_id", productobject.getString("sub_category_id"));
                    addList.put("sub_sub_category_id", productobject.getString("sub_sub_category_id"));
                    addList.put("name", productobject.getString("name"));
                    addList.put("amount", productobject.getString("amount"));
                    addList.put("discount", productobject.getString("discount"));
                    addList.put("after_discount", productobject.getString("after_discount"));
                    addList.put("status", productobject.getString("status"));
                    addList.put("icon", productobject.getString("icon"));
                    addList.put("detail_image", productobject.getString("detail_image"));
                    addList.put("description", productobject.getString("description"));
                    addList.put("detail_video", productobject.getString("detail_video"));
                    addList.put("created_at", productobject.getString("created_at"));
                    addList.put("updated_at", productobject.getString("updated_at"));
                }

                JSONObject refund_content=jsonObject.getJSONObject("refund_content");
                String refund_contentid=refund_content.getString("id");
                String refund_contentimage=refund_content.getString("image");
                String refund_contentheading=refund_content.getString("heading");
                String refund_contentshort_description=refund_content.getString("short_description");
                String refund_contentdescription=refund_content.getString("description");
                String refund_contentcreated_at=refund_content.getString("created_at");
                String refund_contentupdated_at=refund_content.getString("updated_at");
                cancellationDesc.setText(Html.fromHtml(refund_contentdescription));
                cancellationheading.setText(refund_contentheading);

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

}
