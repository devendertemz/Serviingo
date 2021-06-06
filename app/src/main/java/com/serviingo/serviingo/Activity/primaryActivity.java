package com.serviingo.serviingo.Activity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.serviingo.serviingo.Common.CustomLoader;
import com.serviingo.serviingo.Common.Preferences;
import com.serviingo.serviingo.Database.AppSettings;
import com.serviingo.serviingo.R;
import com.serviingo.serviingo.utils.AppUtils;
import com.serviingo.serviingo.view.BaseActivity;

public class primaryActivity extends BaseActivity implements View.OnClickListener {
    FrameLayout flPrimary;
    CustomLoader loader;
    FragmentManager fragmentManager;
    LinearLayout llHome, llmyBooking, llProfile, llhelpCenter;
    TextView tvHomeBottom, tvCategoryBottom, tvNotificationBottom, tvProfileBottom;
    ImageView ivHomeBottom, ivCategoryBottom, ic_profile, ic_notification, iv_menu;
    int back = 0;
    RelativeLayout rlDashBoard, rr_home, rr_category, rr_recharge, rrInsurance,
            rr_About, rr_Contact, rr_register, rrsignin;
    Preferences pref;
    private Fragment fragment;
    public static ScrollView scrollView;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.primary_activity);
        init();
        displayView(0);
    }

    private void init() {
        pref=new Preferences(mActivity);
        flPrimary = findViewById(R.id.flPrimary);
        llHome = findViewById(R.id.llHome);
        llmyBooking = findViewById(R.id.llmyBooking);
        llProfile = findViewById(R.id.llProfile);
        llhelpCenter = findViewById(R.id.llhelpCenter);
        tvHomeBottom = findViewById(R.id.tvHomeBottom);
        tvCategoryBottom = findViewById(R.id.tvCategoryBottom);
        tvNotificationBottom = findViewById(R.id.tvNotificationBottom);
        tvProfileBottom = findViewById(R.id.tvProfileBottom);
        ivHomeBottom = findViewById(R.id.ivHomeBottom);
        ivCategoryBottom = findViewById(R.id.ivCategoryBottom);
        ic_profile = findViewById(R.id.ic_profile);
        ic_notification = findViewById(R.id.ic_notification);
        scrollView = findViewById(R.id.scroll_side_menu);
        iv_menu = findViewById(R.id.ivNav);
        rlDashBoard = findViewById(R.id.rlDashBoard);
        rr_home     = findViewById(R.id.rr_home);
        rr_category = findViewById(R.id.rr_category);
        rr_recharge = findViewById(R.id.rr_recharge);
        rrInsurance = findViewById(R.id.rrInsurance);
        rr_About    = findViewById(R.id.rr_About);
        rr_Contact  = findViewById(R.id.rr_Contact);
        rr_register = findViewById(R.id.rr_register);
        rrsignin    = findViewById(R.id.rrsignin);
        onClickListener();
    }

    private void onClickListener() {
        llHome.setOnClickListener(this);
        llmyBooking.setOnClickListener(this);
        llProfile.setOnClickListener(this);
        llhelpCenter.setOnClickListener(this);
        iv_menu.setOnClickListener(this);

        rlDashBoard.setOnClickListener(this);
        rr_home.setOnClickListener(this);
        rr_category.setOnClickListener(this);
        rr_recharge.setOnClickListener(this);
        rrInsurance.setOnClickListener(this);
        rr_About   . setOnClickListener(this);
        rr_Contact  .setOnClickListener(this);
        rr_register .setOnClickListener(this);
        rrsignin    .setOnClickListener(this);
        hitsubCategoryApi();
    }

    private void hitsubCategoryApi() {

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void displayView(int position) {
        fragmentManager = getSupportFragmentManager();
        switch (position) {

            case 0:
                back = 0;
                setDefault(tvHomeBottom,ivHomeBottom);
                fragment = new homeFragment();
                break;
            case 1:
                back = 1;
                setDefault(tvCategoryBottom,ivCategoryBottom);
                fragment = new myBooking();
                break;
            case 2:
                back = 1;
                setDefault(tvNotificationBottom,ic_notification);
                fragment = new Profile();
                break;
            case 3:
                back = 1;
                setDefault(tvProfileBottom,ic_profile);
               // fragment = new helpCenter();
                fragment = new SupportTicketsActivity();
                break;
            case R.id.ivNav:
                /*if(pref.get(AppSettings.loginCheck).equals("true")){
                    if (scrollView.getVisibility() == View.VISIBLE) {
                        AppUtils.collapse(scrollView);
                        break;
                    }

                    AppUtils.expand(scrollView);
                    rr_home.setVisibility(View.VISIBLE);
                    rr_category.setVisibility(View.VISIBLE);
                    rr_recharge.setVisibility(View.VISIBLE);
                    rrInsurance.setVisibility(View.VISIBLE);
                    rr_About.setVisibility(View.GONE);
                    rr_Contact.setVisibility(View.GONE);
                    rr_register.setVisibility(View.GONE);
                    rrsignin.setVisibility(View.GONE);
                }else{
                    if (scrollView.getVisibility() == View.VISIBLE) {
                        AppUtils.collapse(scrollView);
                        break;
                    }

                    AppUtils.expand(scrollView);
                    rr_home.setVisibility(View.GONE);
                    rr_category.setVisibility(View.GONE);
                    rr_recharge.setVisibility(View.GONE);
                    rrInsurance.setVisibility(View.GONE);
                    rr_About.setVisibility(View.VISIBLE);
                    rr_Contact.setVisibility(View.VISIBLE);
                    rr_register.setVisibility(View.VISIBLE);
                    rrsignin.setVisibility(View.VISIBLE);
                }*/
                if(AppSettings.getString(AppSettings.id).isEmpty()) {
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
                AppSettings.putString(AppSettings.mobile,"");
                AppSettings.putString(AppSettings.loginCheck,"");
                //userprofile data
                AppSettings.putString(AppSettings.id,"");
                AppSettings.putString(AppSettings.name,"");
                AppSettings.putString(AppSettings.email,"");
                AppSettings.putString(AppSettings.gender,"");
                AppSettings.putString(AppSettings.mobile,"");
                AppSettings.putString(AppSettings.wallet,"");
                AppSettings.putString(AppSettings.dob,"");
                AppSettings.putString(AppSettings.user_pic,"");
                AppSettings.putString(AppSettings.referralId,"");
                AppSettings.putString(AppSettings.profilePic,"");

                AppSettings.putString(AppSettings.googleName,"");
                AppSettings.putString(AppSettings.googleEmail,"");
                AppSettings.putString(AppSettings.googledob,"");
                AppSettings.putString(AppSettings.googleId,"");

                AppSettings.putString(AppSettings.fbname,"");
                AppSettings.putString(AppSettings.fbId,"");

                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id. rr_About:
                startActivity(new Intent(primaryActivity.this, AboutUs.class));
                break;
            case R.id. rr_Contact:
                startActivity(new Intent(primaryActivity.this, ContactUsActivity.class));
                break;
            case R.id.rr_register:
                startActivity(new Intent(primaryActivity.this, SignUpActivity.class));
                break;
            case R.id.rrsignin:
                startActivity(new Intent(primaryActivity.this, LoginActivity.class));
                break;


        }
        fragmentManager.beginTransaction()
                .replace(R.id.flPrimary, fragment)
                .addToBackStack("")
                .commit();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.llHome:
               /* llHome.setTextColor( Color.parseColor( "#000000" ) );
                tvTopFeatures.setTextColor( Color.parseColor( "#bfc2c2" ) );
                tvAll.setTextColor( Color.parseColor( "#bfc2c2" ) );*/
                displayView(0);
                break;
            case R.id.llmyBooking:
               /* llHome.setTextColor( Color.parseColor( "#000000" ) );
                tvTopFeatures.setTextColor( Color.parseColor( "#bfc2c2" ) );
                tvAll.setTextColor( Color.parseColor( "#bfc2c2" ) );*/
                displayView(1);
                break;
            case R.id.llProfile:
               /* llHome.setTextColor( Color.parseColor( "#000000" ) );
                tvTopFeatures.setTextColor( Color.parseColor( "#bfc2c2" ) );
                tvAll.setTextColor( Color.parseColor( "#bfc2c2" ) );*/
                displayView(2);
                break;
            case R.id.llhelpCenter:
               /* llHome.setTextColor( Color.parseColor( "#000000" ) );
                tvTopFeatures.setTextColor( Color.parseColor( "#bfc2c2" ) );
                tvAll.setTextColor( Color.parseColor( "#bfc2c2" ) );*/
                displayView(3);
                break;

        }

    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void setDefault(TextView tvV, ImageView ivv) {
        tvHomeBottom.setTextColor(ColorStateList.valueOf(getResources().getColor(R.color.black)));
        tvCategoryBottom.setTextColor(ColorStateList.valueOf(getResources().getColor(R.color.black)));
        tvNotificationBottom.setTextColor(ColorStateList.valueOf(getResources().getColor(R.color.black)));
        tvProfileBottom.setTextColor(ColorStateList.valueOf(getResources().getColor(R.color.black)));
        ivHomeBottom.setImageTintList(ColorStateList.valueOf(getResources().getColor(R.color.black)));
        ivCategoryBottom.setImageTintList(ColorStateList.valueOf(getResources().getColor(R.color.black)));
        ic_profile.setImageTintList(ColorStateList.valueOf(getResources().getColor(R.color.black)));
        ic_notification.setImageTintList(ColorStateList.valueOf(getResources().getColor(R.color.black)));

        tvV.setTextColor(ColorStateList.valueOf(getResources().getColor(R.color.purple_700)));
        ivv.setImageTintList(ColorStateList.valueOf(getResources().getColor(R.color.purple_700)));

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBackPressed() {
        finish();

        /*switch (back) {
            case 0:
                startActivity(new Intent(mActivity, Lowcontact.class));
                break;
            case 1:
                displayView(0);
                break;
            default:
                displayView(0);*/


    }
}