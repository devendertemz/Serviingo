package com.serviingo.serviingo.Activity;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.serviingo.serviingo.Database.AppSettings;
import com.serviingo.serviingo.R;
import com.serviingo.serviingo.utils.AppUtils;
import com.serviingo.serviingo.view.BaseActivity;

import java.util.ArrayList;
import java.util.HashMap;

//  import java.util.ArrayList;
//   import java.util.HashMap;

public class subCategoryActivities extends BaseActivity implements View.OnClickListener {
    LinearLayoutManager linearLayoutManager;
    RecyclerView box1_recyclerview, box1_recyclerview1, recyclerView;


    TextView tv_home, tvSend;
    ImageView ivNav, ivBack;
    public static ScrollView scrollView;
    RelativeLayout rlDashBoard, rr_home, rr_category, rr_recharge, rrInsurance,
            rr_About, rr_Contact, rr_register, rrsignin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.subcategory_activity);
        setContentView(R.layout.activity_subcategory);
        setui();
    }

    private void setui() {
        tv_home = findViewById(R.id.tv_home);
        scrollView = findViewById(R.id.scroll_side_menu);
        ivBack = findViewById(R.id.ivBack);
        ivNav = findViewById(R.id.ivNav);
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

        // textView.setPaintFlags(textView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        recyclerView=findViewById(R.id.recyclerView);
        ReviewsAdapter reviewsAdapter = new ReviewsAdapter( );
        recyclerView.setAdapter(reviewsAdapter);
        linearLayoutManager = (new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true));
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        box1_recyclerview=findViewById(R.id.box1_recyclerview);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        box1_recyclerview.setLayoutManager(gridLayoutManager);
        box1_recyclerview.setHasFixedSize(true);
        PackAdapter PackAdapter = new PackAdapter();
        box1_recyclerview.setAdapter(PackAdapter);

        box1_recyclerview1=findViewById(R.id.box1_recyclerview1);
        GridLayoutManager gridLayoutManager1 = new GridLayoutManager(this, 3);
        box1_recyclerview1.setLayoutManager(gridLayoutManager1);
        box1_recyclerview1.setHasFixedSize(true);
        PackAdapter1 PackAdapter1 = new PackAdapter1();
        box1_recyclerview1.setAdapter(PackAdapter1);

    }
    private class ReviewsAdapter extends RecyclerView.Adapter< ReviewsAdapter.WalletHolder> {
       // ArrayList<HashMap<String, String>> data = new ArrayList();
        /*  public PackAdapter(ArrayList<HashMap<String, String>> programlist ) {
              this.data = programlist;
          }
  */
        @Override
        public ReviewsAdapter.WalletHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View view = layoutInflater.inflate(R.layout.reviews_ui, parent, false);
            return new ReviewsAdapter.WalletHolder(view);
        }

        @Override
        public int getItemCount() {
            return 3;
        }

        @Override
        public void onBindViewHolder(final ReviewsAdapter.WalletHolder holder, final int position) {

        }

        public class WalletHolder extends RecyclerView.ViewHolder {
            TextView tvCategory;
            ImageView ivImages;
            public WalletHolder(View itemView) {
                super(itemView);
                ivImages = itemView.findViewById(R.id.ivImages);
            }

        }
    }



    private class PackAdapter extends RecyclerView.Adapter<subCategoryActivities.PackAdapter.WalletHolder> {
        ArrayList<HashMap<String, String>> data = new ArrayList();

//        public PackAdapter(ArrayList<HashMap<String, String>> programlist) {
//            this.data = programlist;
//        }

        @Override
        public subCategoryActivities.PackAdapter.WalletHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View view = layoutInflater.inflate(R.layout.grid_pack, parent, false);
            return new subCategoryActivities.PackAdapter.WalletHolder(view);
        }

        @Override
        public int getItemCount() {
//            return data.size();
            return 9;
        }

        @Override
        public void onBindViewHolder(final subCategoryActivities.PackAdapter.WalletHolder holder, final int position) {
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

//                    Intent i = new Intent(subCategoryActivities.this, SubcategoryActivity.class);
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
            TextView CateName;
            CardView cardView;
            ImageView iv_Imageview;

            public WalletHolder(View itemView) {
                super(itemView);
                // tvCategory = itemView.findViewById(R.id.tv_Category);
                cardView = itemView.findViewById(R.id.cv_View);
                iv_Imageview = itemView.findViewById(R.id.iv_Imageview);
                CateName = itemView.findViewById(R.id.CateName);
                // newregistrationi = itemView.findViewById(R.id.newregistrationi);
            }
        }
    }


    private class PackAdapter1 extends RecyclerView.Adapter<subCategoryActivities.PackAdapter1.WalletHolder> {
        ArrayList<HashMap<String, String>> data = new ArrayList();

//        public PackAdapter(ArrayList<HashMap<String, String>> programlist) {
//            this.data = programlist;
//        }

        @Override
        public subCategoryActivities.PackAdapter1.WalletHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View view = layoutInflater.inflate(R.layout.grid_pack1, parent, false);
            return new subCategoryActivities.PackAdapter1.WalletHolder(view);
        }

        @Override
        public int getItemCount() {
//            return data.size();
            return 3;
        }

        @Override
        public void onBindViewHolder(final subCategoryActivities.PackAdapter1.WalletHolder holder, final int position) {
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

//                    Intent i = new Intent(subCategoryActivities.this, SubcategoryActivity.class);
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
            TextView CateName;
            CardView cardView;
            ImageView iv_Imageview;

            public WalletHolder(View itemView) {
                super(itemView);
                // tvCategory = itemView.findViewById(R.id.tv_Category);
                cardView = itemView.findViewById(R.id.cv_View);
                iv_Imageview = itemView.findViewById(R.id.iv_Imageview);
                CateName = itemView.findViewById(R.id.CateName);
                // newregistrationi = itemView.findViewById(R.id.newregistrationi);
            }
        }
    }


    private void setOnClickListener() {

        ivBack.setOnClickListener(this);
        ivNav.setOnClickListener(this);
        tv_home.setOnClickListener(this);
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
        //super.onBackPressed();
        finish();
    }
}