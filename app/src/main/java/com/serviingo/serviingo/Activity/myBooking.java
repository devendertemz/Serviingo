package com.serviingo.serviingo.Activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.serviingo.serviingo.R;
import com.serviingo.serviingo.view.BaseFragment;

import java.util.ArrayList;
import java.util.HashMap;

public class myBooking extends BaseFragment  {
    View rootView;
    LinearLayoutManager linearLayoutManager;
    RecyclerView recyclerView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate( R.layout.subcategory_activity, container, false );

        setUi();
        return  rootView;
    }

    private void setUi() {
        recyclerView=rootView.findViewById(R.id.recyclerView);
        ReviewsAdapter reviewsAdapter = new ReviewsAdapter( );
        recyclerView.setAdapter(reviewsAdapter);
        linearLayoutManager = (new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
    }
    private class ReviewsAdapter extends RecyclerView.Adapter< ReviewsAdapter.WalletHolder> {
        ArrayList<HashMap<String, String>> data = new ArrayList();
        /*  public CategoryAdapter(ArrayList<HashMap<String, String>> programlist ) {
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
