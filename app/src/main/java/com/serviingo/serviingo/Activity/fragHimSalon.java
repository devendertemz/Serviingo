package com.serviingo.serviingo.Activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.serviingo.serviingo.Common.Preferences;
import com.serviingo.serviingo.Database.AppSettings;
import com.serviingo.serviingo.R;
import com.serviingo.serviingo.view.BaseFragment;

public class fragHimSalon extends BaseFragment {
    RatingBar rating;
    TextView tvRatings,tvRatingsValue,tvSaloonReview;
    View rootView;
    Preferences preferences;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate( R.layout.himsaloonui, container, false );

        setUi();
        return  rootView;
    }

    private void setUi() {
        tvRatings=rootView.findViewById(R.id.tvRatings);
        tvRatingsValue=rootView.findViewById(R.id.tvRatingsValue);
        tvSaloonReview=rootView.findViewById(R.id.tvSaloonReview);
        rating=rootView.findViewById(R.id.rating);
        preferences=new Preferences(mActivity);
        tvRatingsValue.setText(preferences.get(AppSettings.categoryobjavg_rating));
        tvSaloonReview.setText(preferences.get(AppSettings.categoryobjreviews_heading));


    }
}
