package com.serviingo.serviingo.view;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.serviingo.serviingo.Activity.AboutUs;
import com.serviingo.serviingo.Activity.ContactUsActivity;
import com.serviingo.serviingo.Database.AppSettings;
import com.serviingo.serviingo.Activity.LoginActivity;
import com.serviingo.serviingo.R;
import com.serviingo.serviingo.Activity.VenderReg;
import com.serviingo.serviingo.utils.AppUtils;

import java.util.ArrayList;
import java.util.HashMap;

public class SubsubCategory extends BaseActivity implements View.OnClickListener {
    RecyclerView recyclerView;

    TextView tv_home, tvSend;
    ImageView ivNav, ivBack;
    public static ScrollView scrollView;
    RelativeLayout rlDashBoard, rr_home, rr_category, rr_recharge, rrInsurance,
            rr_About, rr_Contact, rr_register, rrsignin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subsub_category);

        setui();
    }

    private void setui() {
//        tv_home = findViewById(R.id.tv_home);
        scrollView = findViewById(R.id.scroll_side_menu);
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

        setOnClickListener();


        recyclerView=findViewById(R.id.recyclerView);

//        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
        recyclerView.setHasFixedSize(true);
        PackAdapter PackAdapter = new PackAdapter();
        recyclerView.setAdapter(PackAdapter);
    }


    private class PackAdapter extends RecyclerView.Adapter<SubsubCategory.PackAdapter.WalletHolder> {
        ArrayList<HashMap<String, String>> data = new ArrayList();

//        public PackAdapter(ArrayList<HashMap<String, String>> programlist) {
//            this.data = programlist;
//        }

        @Override
        public SubsubCategory.PackAdapter.WalletHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View view = layoutInflater.inflate(R.layout.subsubcategory_item, parent, false);
            return new SubsubCategory.PackAdapter.WalletHolder(view);
        }

        @Override
        public int getItemCount() {
//            return data.size();
            return 2;
        }

        @Override
        public void onBindViewHolder(final SubsubCategory.PackAdapter.WalletHolder holder, final int position) {

            if(position%2 !=0){
                holder.ll_back.setBackground(getResources().getDrawable(R.drawable.rect_light_blue));
                holder.tv_pack.setTextColor(getResources().getColor(R.color.white));

            }
            else
            {
                holder.ll_back.setBackground(getResources().getDrawable(R.drawable.grey_border));
                holder.tv_pack.setTextColor(getResources().getColor(R.color.grey_));

            }


            holder.ll_back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

//                    Intent i = new Intent(SubsubCategory.this, SubcategoryActivity.class);
//                    i.putExtra("id", data.get(position).get("id"));
//                    startActivity(i);
                }
            });
          /*  if (((String) ((HashMap) this.data.get(position)).get("icon")).equalsIgnoreCase("")) {
                Picasso.get().load((int) R.mipmap.logo).resize(815,315).into(holder.iv_Imageview);
              //  progressBar.setVisibility(View.VISIBLE);
            } else {*/
//            Picasso.get().load(AppUrls.BASECategoryimagepath + data.get(position).get("icon")).into(holder.iv_Imageview);
//            holder.CateName.setText(data.get(position).get("name"));

            /* }*/

        }

        public class WalletHolder extends RecyclerView.ViewHolder {
            TextView tv_pack;
            LinearLayout ll_back;


            public WalletHolder(View itemView) {
                super(itemView);

                ll_back = itemView.findViewById(R.id.ll_back);
                tv_pack = itemView.findViewById(R.id.tv_pack);

            }
        }
    }


    private void setOnClickListener() {

        ivBack.setOnClickListener(this);
        ivNav.setOnClickListener(this);
//        tv_home.setOnClickListener(this);
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
            case R.id.tv_home:
//                finish();
//                startActivity(new Intent(this, MainActivity.class));
                break;

        }
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        finish();
    }
}