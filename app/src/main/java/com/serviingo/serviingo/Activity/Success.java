package com.serviingo.serviingo.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.serviingo.serviingo.R;
import com.serviingo.serviingo.view.BaseActivity;

public class Success extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.success);
        findViewById(R.id.success).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Success.this,MainActivity.class));
            }
        });
    }
}
