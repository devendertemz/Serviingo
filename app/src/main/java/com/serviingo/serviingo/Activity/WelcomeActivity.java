package com.serviingo.serviingo.Activity;

import android.os.Bundle;
import android.view.View;

import com.serviingo.serviingo.R;
import com.serviingo.serviingo.view.BaseActivity;

public class WelcomeActivity extends BaseActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcomeactivity);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
         /*   case R.id.about:

                break;*/
        }
    }
}
