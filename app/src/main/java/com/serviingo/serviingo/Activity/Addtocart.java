package com.serviingo.serviingo.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.serviingo.serviingo.Database.AppSettings;
import com.serviingo.serviingo.R;
import com.serviingo.serviingo.utils.AppUtils;
import com.serviingo.serviingo.view.BaseActivity;

public class Addtocart extends BaseActivity implements View.OnClickListener {
    TextView btn,tvPrice;
    ImageView   ivNav,ivBack;
    ScrollView scrollView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addtocartui);
        btn=findViewById(R.id.btn);
        tvPrice=findViewById(R.id.tvPrice);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Addtocart.this,Addones.class));
            }
        });
        tvPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Addtocart.this,Addones.class));
            }
        });
        ivNav = findViewById(R.id.ivNav);
        scrollView = findViewById(R.id.scroll_side_menu);
        ivBack = findViewById(R.id.ivBack);
        setOnClickListener();
    }
    private void setOnClickListener() {

        ivBack    .setOnClickListener(this);
        ivNav    .setOnClickListener(this);


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

        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
