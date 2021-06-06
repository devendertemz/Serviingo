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

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.serviingo.serviingo.Database.AppSettings;
import com.serviingo.serviingo.R;
import com.serviingo.serviingo.utils.AppUtils;
import com.serviingo.serviingo.view.BaseActivity;

public class Addones extends BaseActivity implements View.OnClickListener{
    RecyclerView RecyclerView;
    TextView tv_home, tvSend;
    LinearLayoutManager linearLayoutManager;
    ImageView   ivNav,ivBack;
    public static ScrollView scrollView;
    RelativeLayout rlDashBoard, rr_home, rr_category, rr_recharge, rrInsurance,
            rr_About, rr_Contact, rr_register, rrsignin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addones);
        setui();
    }
    private void setui(){
        tv_home = findViewById(R.id.tv_home);
        scrollView = findViewById(R.id.scroll_side_menu);
        ivBack = findViewById(R.id.ivBack);
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



        RecyclerView=findViewById(R.id.recyclerView);
        tvSend=findViewById(R.id.tvSend);


        tvSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Addones.this,covidTest.class));
            }
        });
        setOnClickListener();
        setRecyclerAdapter( RecyclerView);
    }
    public void setRecyclerAdapter(RecyclerView recyclerView) {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        recyclerView.setNestedScrollingEnabled(false);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(mActivity, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        GridAdapter gridAdapter = new GridAdapter( );
        RecyclerView.setAdapter(gridAdapter);

/*        RecommendedAdapter recommendedAdapter = new RecommendedAdapter(arrayRecommendedForYou);
        recyclerrecommedned.setAdapter(recommendedAdapter);*/
    }
    private void setOnClickListener() {

        ivBack    .setOnClickListener(this);
        ivNav    .setOnClickListener(this);
        tv_home.setOnClickListener(this);
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

    @Override
    public void onClick(View v) {
        switch(v.getId()){

            case R.id.ivNav:
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
//                finish();
//                startActivity(new Intent(this, MainActivity.class));
                break;

        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }
    private class GridAdapter extends RecyclerView.Adapter< GridAdapter.WalletHolder> {


        // ArrayList<HashMap<String, String>> data = new ArrayList();
        /*  public CategoryAdapter(ArrayList<HashMap<String, String>> programlist ) {
              this.data = programlist;
          }
  */
        @Override
        public GridAdapter.WalletHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View view = layoutInflater.inflate(R.layout.gridui, parent, false);
            return new GridAdapter.WalletHolder(view);
        }

        @Override
        public int getItemCount() {
            return 2;
        }

        @Override
        public void onBindViewHolder(final GridAdapter.WalletHolder holder, final int position) {
            holder.unCheck.setVisibility(View.GONE);
            holder.checkbox.setVisibility(View.VISIBLE);
            holder.checkbox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.checkbox.setVisibility(View.GONE);
                    holder.unCheck.setVisibility(View.VISIBLE);
                }
            });

            holder.unCheck.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    holder.checkbox.setVisibility(View.VISIBLE);
                    holder.unCheck.setVisibility(View.GONE);
                }
            });
        }
        public class WalletHolder extends RecyclerView.ViewHolder {
            TextView tvCategory;
            ImageView checkbox,unCheck;
            public WalletHolder(View itemView) {
                super(itemView);
                checkbox=itemView.findViewById(R.id.checkbox);
                unCheck=itemView.findViewById(R.id.unCheck);
            }
        }
    }


}
