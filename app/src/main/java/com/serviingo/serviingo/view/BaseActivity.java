package com.serviingo.serviingo.view;

import android.app.Activity;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.serviingo.serviingo.Common.SimpleHTTPConnection;
import com.serviingo.serviingo.Database.OSettings;


public class BaseActivity extends AppCompatActivity {
    protected static Activity mActivity;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
      //  AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        this.mActivity = this;
        OSettings oSettings = new OSettings(this.mActivity);
        SimpleHTTPConnection simpleHTTPConnection = new SimpleHTTPConnection(this.mActivity);
    }
}
