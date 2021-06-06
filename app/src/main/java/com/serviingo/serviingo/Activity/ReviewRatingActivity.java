package com.serviingo.serviingo.Activity;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.serviingo.serviingo.R;
import com.serviingo.serviingo.view.BaseActivity;

public class ReviewRatingActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.review_rating_activity);

        ViewDialog alert = new ViewDialog();
        alert.showDialog(ReviewRatingActivity.this);

    }
    public class ViewDialog {

        public void showDialog(Activity activity) {
            final Dialog dialog = new Dialog(activity);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.rating_alertbox);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            WindowManager.LayoutParams wmlp = dialog.getWindow().getAttributes();
//            wmlp.x = 30;   //x position
            wmlp.y = 300;   //y position

            wmlp.gravity = Gravity.TOP;

            ImageView close = dialog.findViewById(R.id.close);
            close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });


            dialog.show();
        }
    }


    public class CustomDialogClass extends Dialog implements android.view.View.OnClickListener {

        public Activity c;
        public Dialog d;
        public ImageView close;

        public CustomDialogClass(Activity a) {
            super(a);
            // TODO Auto-generated constructor stub
            this.c = a;
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            WindowManager.LayoutParams wmlp = d.getWindow().getAttributes();
            wmlp.x = 100;   //x position
            wmlp.y = 100;   //y position

            wmlp.gravity = Gravity.TOP;
            setContentView(R.layout.rating_alertbox);
            close = findViewById(R.id.close);
            close.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.close:
                    dismiss();
                    break;
                default:
                    break;
            }
            dismiss();
        }
    }
}
