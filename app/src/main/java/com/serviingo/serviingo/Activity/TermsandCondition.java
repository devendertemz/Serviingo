package com.serviingo.serviingo.Activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.serviingo.serviingo.Common.SimpleHTTPConnection;
import com.serviingo.serviingo.R;
import com.serviingo.serviingo.utils.AppUrls;
import com.serviingo.serviingo.utils.AppUtils;
import com.serviingo.serviingo.view.BaseActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class TermsandCondition extends BaseActivity  implements View.OnClickListener{

   TextView tvtermsandcond;
    Context context;

    TextView tvreferdesc;
    ImageView referImageview;
    String pinterest_linklink,facebook_linklink,linkedin_linklink,youtube_linklink,insta_linklink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.termsui);
        findViewById();
    }

    private void findViewById() {
        tvtermsandcond=findViewById(R.id.tvtermsandcond);

        tvreferdesc=findViewById(R.id.tvreferdesc);
//        referImageview=findViewById(R.id.referImageview);
        /*ivPinterest =findViewById(R.id.ivPinterest);
        ivYoutube   =findViewById(R.id.ivYoutube);
        ivinsta     =findViewById(R.id.ivinsta);
        ivtwitter   =findViewById(R.id.ivtwitter);
        ivfacebook  =findViewById(R.id.ivfacebook);*/

        setOnClickLictener();

        if (SimpleHTTPConnection.isNetworkAvailable( mActivity )) {
            getTQ();

        } else {
            AppUtils.showErrorMessage( tvreferdesc, "Please Check Your Internet Connection", mActivity );
        }



    }

    private void setOnClickLictener() {
       /* ivPinterest.setOnClickListener(this);
        ivYoutube.setOnClickListener(this);
        ivinsta.setOnClickListener(this);
        ivtwitter.setOnClickListener(this);
        ivfacebook.setOnClickListener(this);
*/
    }
    private void getTQ() {

        String url = AppUrls.getTerms;
        Log.v("faq_url", url);
        AndroidNetworking.get(url)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        parseJsonResponse(response);
                    }
                    @Override
                    public void onError(ANError error) {
                        Log.v("data", error.getErrorBody());
                        Log.v("data", error.getErrorDetail());
                    }
                });
    }

    private void parseJsonResponse(JSONObject response) {
        try {
            String message=response.getString("message");
            Toast.makeText(this, ""+message, Toast.LENGTH_SHORT).show();
          if (response.getString("status").equals("true")) {
           JSONObject jsonObject = response.getJSONObject("data");
            JSONObject refer_and_earnobj=jsonObject.getJSONObject("refer_and_earn");
                String id=refer_and_earnobj.getString("id");
                String slug=refer_and_earnobj.getString("slug");
                String heading=refer_and_earnobj.getString("heading");
                String title=refer_and_earnobj.getString("title");
                tvreferdesc.setText(title);
                String description=refer_and_earnobj.getString("description");
                String images=refer_and_earnobj.getString("images");
                //Picasso.get().load(AppUrls.getImage ).into(referImageview);
                String categories_ids=refer_and_earnobj.getString("categories_ids");
                String created_at=refer_and_earnobj.getString("created_at");
                String updated_at=refer_and_earnobj.getString("updated_at");


            JSONArray latest_servicesArray = jsonObject.getJSONArray("latest_services");
                for (int i = 0; i < latest_servicesArray.length(); i++) {
                    JSONObject productobject = latest_servicesArray.getJSONObject(i);
                    HashMap<String, String> addList = new HashMap();
                    addList.put("id", productobject.getString("id"));
                    addList.put("category_id", productobject.getString("category_id"));
                    addList.put("sub_category_id", productobject.getString("sub_category_id"));
                    addList.put("sub_sub_category_id", productobject.getString("sub_sub_category_id"));
                    addList.put("name", productobject.getString("name"));
                    addList.put("amount", productobject.getString("amount"));
                    addList.put("discount", productobject.getString("discount"));
                    addList.put("after_discount", productobject.getString("after_discount"));
                    addList.put("status", productobject.getString("status"));
                    addList.put("icon", productobject.getString("icon"));
                    addList.put("detail_image", productobject.getString("detail_image"));
                    addList.put("description", productobject.getString("description"));
                    addList.put("detail_video", productobject.getString("detail_video"));
                    addList.put("created_at", productobject.getString("created_at"));
                    addList.put("updated_at", productobject.getString("updated_at"));
                }

              JSONObject term_contentobj=jsonObject.getJSONObject("term_content");

            JSONObject facebook_linkobj=jsonObject.getJSONObject("facebook_link");
                String facebook_linkid=facebook_linkobj.getString("id");
                String facebook_linkslug=facebook_linkobj.getString("slug");
                facebook_linklink=facebook_linkobj.getString("link");
                String facebook_linkcreated_at=facebook_linkobj.getString("created_at");
                String facebook_linkupdated_at=facebook_linkobj.getString("updated_at");

            JSONObject twitter_linkobj=jsonObject.getJSONObject("twitter_link");
                String twitter_linkid=twitter_linkobj.getString("id");
                String twitter_linkslug=twitter_linkobj.getString("slug");
                String twitter_linklink=twitter_linkobj.getString("link");
                String twitter_linkcreated_at=twitter_linkobj.getString("created_at");
                String twitter_linkupdated_at=twitter_linkobj.getString("updated_at");

           JSONObject insta_linkobj=jsonObject.getJSONObject("insta_link");
                String insta_linkid=twitter_linkobj.getString("id");
                String insta_linkslug=twitter_linkobj.getString("slug");
                insta_linklink=twitter_linkobj.getString("link");
                String insta_linkcreated_at=twitter_linkobj.getString("created_at");
                String insta_linkupdated_at=twitter_linkobj.getString("updated_at");

            JSONObject youtube_linkobj=jsonObject.getJSONObject("youtube_link");
                String youtube_linkid=youtube_linkobj.getString("id");
                String youtube_linkslug=youtube_linkobj.getString("slug");
                youtube_linklink=youtube_linkobj.getString("link");
                String youtube_linkcreated_at=youtube_linkobj.getString("created_at");
                String youtube_linkupdated_at=youtube_linkobj.getString("updated_at");

             JSONObject linkedin_linkobj=jsonObject.getJSONObject("linkedin_link");
                String linkedin_linkid=        linkedin_linkobj.getString("id");
                String linkedin_linkslug=      linkedin_linkobj.getString("slug");
                linkedin_linklink=      linkedin_linkobj.getString("link");
                String linkedin_linkcreated_at=linkedin_linkobj.getString("created_at");
                String linkedin_linkupdated_at=linkedin_linkobj.getString("updated_at");

             JSONObject pinterest_linkobj=jsonObject.getJSONObject("pinterest_link");
                String pinterest_linkid=        pinterest_linkobj.getString("id");
                String pinterest_linkslug=      pinterest_linkobj.getString("slug");
                pinterest_linklink=      pinterest_linkobj.getString("link");
                String pinterest_linkcreated_at=pinterest_linkobj.getString("created_at");
                String pinterest_linkupdated_at=pinterest_linkobj.getString("updated_at");





            } else {
            }
        } catch (Exception e) {
            Log.v("error", String.valueOf(e));
        }
    }



    @Override
    public void onClick(View v) {
        switch(v.getId()){


        }
    }
}
