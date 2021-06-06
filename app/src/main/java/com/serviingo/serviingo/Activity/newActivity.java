package com.serviingo.serviingo.Activity;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.serviingo.serviingo.R;
import com.serviingo.serviingo.view.BaseActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class newActivity extends BaseActivity {
    View rootView;
    LinearLayoutManager linearLayoutManager;
    RecyclerView recyclerView;
    CardView ourprogram_cv;
    ReviewsAdapter reviewsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_activity);
        setUi();
    }
      private void setUi() {
        recyclerView=rootView.findViewById(R.id.recyclerView);
        ourprogram_cv=rootView.findViewById(R.id.ourprogram_cv);
        ourprogram_cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mActivity,newActivity.class));
            }
        });
        reviewsAdapter = new ReviewsAdapter( );
        recyclerView.setAdapter(reviewsAdapter);
        linearLayoutManager = (new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, true));
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
    }
    private class ReviewsAdapter extends RecyclerView.Adapter< ReviewsAdapter.WalletHolder> {
        ArrayList<HashMap<String, String>> data = new ArrayList();
/*
          public CategoryAdapter(ArrayList<HashMap<String, String>> programlist ) {
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

}