package com.serviingo.serviingo.Activity;

import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
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
import com.serviingo.serviingo.view.BaseFragment;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class FragHowItWorks extends BaseFragment {
    View rootView;
    LinearLayoutManager linearLayoutManager;
    ArrayList<HashMap<String, String>> faqUi = new ArrayList();
    RecyclerView  headingRecy;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate( R.layout.fraghowitworks, container, false );

        setUi();
        return  rootView;
    }

    private void setUi() {

        headingRecy = rootView.findViewById(R.id.headingRecy);
        linearLayoutManager = (new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        headingRecy.setLayoutManager(linearLayoutManager);
        if (SimpleHTTPConnection.isNetworkAvailable(mActivity)) {
            getRecyclerData();

        } else {
            AppUtils.showErrorMessage(rootView, "Please Check Your Internet Connection", mActivity);
        }
    }
    private void getRecyclerData() {
       // String categoryId = getIntent().getStringExtra("id");
        Log.v("categoryId", "1");
        String url = AppUrls.getCategoryDetails + "1";
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
            String message = response.getString("message");

            if (response.getString("status").equals("true")) {
                JSONObject jsonObject = response.getJSONObject("data");
                String total_give_rates = jsonObject.getString("total_give_rates");

                JSONArray how_it_worksrray = jsonObject.getJSONArray("how_it_works");
                for (int i = 0; i < how_it_worksrray.length(); i++) {
                    JSONObject productobject = how_it_worksrray.getJSONObject(i);
                    HashMap<String, String> addList = new HashMap();
                    addList.put("id", productobject.getString("id"));
                    addList.put("category_id", productobject.getString("category_id"));
                    addList.put("position", productobject.getString("position"));
                    addList.put("title", productobject.getString("title"));
                    addList.put("description", productobject.getString("description"));
                    addList.put("created_at", productobject.getString("created_at"));
                    addList.put("updated_at", productobject.getString("updated_at"));

                    faqUi.add(addList);
                }
                Log.v("Sub_CategoryArray", faqUi.toString());



                HorizontalCategoryAdapter horizontalCategoryAdapter = new HorizontalCategoryAdapter(faqUi);
                headingRecy.setAdapter(horizontalCategoryAdapter);
                headingRecy.setHasFixedSize(true);

            } else {

            }
        } catch (Exception e) {
            Log.v("error", String.valueOf(e));
        }
    }
    private class HorizontalCategoryAdapter extends RecyclerView.Adapter<HorizontalCategoryAdapter.WalletHolder> {
        ArrayList<HashMap<String, String>> data = new ArrayList();

        public HorizontalCategoryAdapter(ArrayList<HashMap<String, String>> programlist) {
            this.data = programlist;
        }

        @Override
        public HorizontalCategoryAdapter.WalletHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View view = layoutInflater.inflate(R.layout.howitworking, parent, false);
            return new HorizontalCategoryAdapter.WalletHolder(view);
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        @Override
        public void onBindViewHolder
                (final HorizontalCategoryAdapter.WalletHolder holder, final int position) {
           /* holder.cv_View.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this,newActivity.class));
                }
            });*/
            // Picasso.get().load(AppUrls.BASEServicesimagepath + data.get(position).get("icon")).into(holder.ivImages);
            holder.choosefile.setText(data.get(position).get("title"));
            holder.newText.setText(data.get(position).get("description"));

        }

        public class WalletHolder extends RecyclerView.ViewHolder {
            TextView choosefile, newText;
            ImageView ivImages;
            CardView cv_View;

            public WalletHolder(View itemView) {
                super(itemView);
                ivImages = itemView.findViewById(R.id.ivImages);
                choosefile = itemView.findViewById(R.id.choosefile);
                newText = itemView.findViewById(R.id.newText);
            }
        }
    }

}
