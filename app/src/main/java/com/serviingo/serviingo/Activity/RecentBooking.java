package com.serviingo.serviingo.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.serviingo.serviingo.Database.AppSettings;
import com.serviingo.serviingo.R;
import com.serviingo.serviingo.utils.AppUtils;
import com.serviingo.serviingo.view.BaseActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class RecentBooking extends BaseActivity implements View.OnClickListener{
    LinearLayoutManager linearLayoutManager;
    RecyclerView rvsupport;
    TextView tv_new, tv_ongoing, tv_completed, tv_cancelled;
    ImageView ivNav, ivBack;
    public static ScrollView scrollView;
    RelativeLayout rlDashBoard, rr_home, rr_category, rr_recharge, rrInsurance,
            rr_About, rr_Contact, rr_register, rrsignin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recent_booking);
        setUi();
        setOnClickListener();
    }
        private void setUi() {
            tv_new  = findViewById(R.id.tv_new);
            tv_ongoing  = findViewById(R.id.tv_ongoing);
            tv_completed = findViewById(R.id.tv_completed);
            tv_cancelled = findViewById(R.id.tv_cancelled);

            ivBack = findViewById(R.id.ivBack);
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

            rvsupport=findViewById(R.id.recyclerbooking);
            RBAdapter supportAdapter = new RBAdapter( );
            rvsupport.setAdapter(supportAdapter);
            linearLayoutManager = (new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, true));
            rvsupport.setLayoutManager(linearLayoutManager);
            rvsupport.setHasFixedSize(true);
        }
        private class RBAdapter extends RecyclerView.Adapter< RBAdapter.WalletHolder> {
            ArrayList<HashMap<String, String>> data = new ArrayList();
            /*  public CategoryAdapter(ArrayList<HashMap<String, String>> programlist ) {
                  this.data = programlist;
              }
      */
            @Override
            public RBAdapter.WalletHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
                View view = layoutInflater.inflate(R.layout.dashboard_items, parent, false);
                return new RBAdapter.WalletHolder(view);
            }

            @Override
            public int getItemCount() {
                return 3;
            }

            @Override
            public void onBindViewHolder(final RBAdapter.WalletHolder holder, final int position) {
            }


            public class WalletHolder extends RecyclerView.ViewHolder {
                TextView tvCategory;
                ImageView ivImages;
                public WalletHolder(View itemView) {
                    super(itemView);
//                    ivImages = itemView.findViewById(R.id.ivImages);
                }

            }
        }

    private void setOnClickListener() {

        ivBack.setOnClickListener(this);
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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

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
                startActivity(new Intent(this, VenderReg.class));
                break;
            case R.id.rrsignin:
                startActivity(new Intent(this, LoginActivity.class));
                break;


        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }

}
