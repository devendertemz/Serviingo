package com.serviingo.serviingo.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.serviingo.serviingo.Database.AppSettings;
import com.serviingo.serviingo.R;
import com.serviingo.serviingo.utils.AppUtils;

public class AddressActivity extends AppCompatActivity implements View.OnClickListener {
RecyclerView icrecyclerView;
    AdressAdapter addressadapter;
    TextView addadress;
    TextView tv_home, tvreferdesc;
    ImageView ivback, ivNav,referImageview;
    String pinterest_linklink,facebook_linklink,linkedin_linklink,youtube_linklink,insta_linklink;
    public static ScrollView scrollView;
    RelativeLayout rlDashBoard, rr_home, rr_category, rr_recharge, rrInsurance,
            rr_About, rr_Contact, rr_register, rrsignin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        
        setui();
    }

    private void setui() {
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

        icrecyclerView=findViewById(R.id.icrecyclerView);
        addadress=findViewById(R.id.addadress);
        addressadapter = new AdressAdapter( );
        icrecyclerView.setAdapter(addressadapter);
       LinearLayoutManager  linearLayoutManager = (new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true));
        icrecyclerView.setLayoutManager(linearLayoutManager);
        icrecyclerView.setHasFixedSize(true);

        addadress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPictureialog();
            }
        });
    }
    private class AdressAdapter extends RecyclerView.Adapter< AdressAdapter.WalletHolder> {
        // ArrayList<HashMap<String, String>> data = new ArrayList();
        /*  public CategoryAdapter(ArrayList<HashMap<String, String>> programlist ) {
              this.data = programlist;
          }
  */
        @Override
        public AdressAdapter.WalletHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View view = layoutInflater.inflate(R.layout.addressadapter, parent, false);
            return new AdressAdapter.WalletHolder(view);
        }

        @Override
        public int getItemCount() {
            return 2;
        }

        @Override
        public void onBindViewHolder(final AdressAdapter.WalletHolder holder, final int position) {

        }


        public class WalletHolder extends RecyclerView.ViewHolder {
            TextView tvCategory;
            ImageView ivImages;
            public WalletHolder(View itemView) {
                super(itemView);

              //  ivImages = itemView.findViewById(R.id.ivImages);
            }

        }
    }
    private void showPictureialog() {
        final Dialog dialog = new Dialog(this,
                android.R.style.Theme_DeviceDefault_Light_NoActionBar);
        // Setting dialogview
        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER);
        window.setLayout(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
        dialog.setTitle(null);
        dialog.setContentView(R.layout.alertbox);
        dialog.setCancelable(true);
        dialog.dismiss();
        dialog.show();
    }

    private void setOnclickistener() {

        tv_home.setOnClickListener(this);
        ivNav.setOnClickListener(this);
        rlDashBoard.setOnClickListener(this);
        rr_home.setOnClickListener(this);
        rr_category.setOnClickListener(this);
        rr_recharge.setOnClickListener(this);
        rrInsurance.setOnClickListener(this);
        rr_About   . setOnClickListener(this);
        rr_Contact  .setOnClickListener(this);
        rr_register .setOnClickListener(this);
        rrsignin    .setOnClickListener(this);
        ivback    .setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            /*case R.id.ivPinterest:
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
}