package com.serviingo.serviingo.Activity;

import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.serviingo.serviingo.Common.Preferences;
import com.serviingo.serviingo.Database.AppSettings;
import com.serviingo.serviingo.R;
import com.serviingo.serviingo.view.BaseFragment;

public class fragAboutSaloonForhim extends BaseFragment {
    View rootView;
    TextView tvSaloonReview,heading,desc;
    Preferences pref;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate( R.layout.fragaboutsallonui, container, false );

        setUi();
        return  rootView;
    }

    private void setUi() {
        pref    =new Preferences(mActivity);
        tvSaloonReview=rootView.findViewById(R.id.tvSaloonReview);
        heading=rootView.findViewById(R.id.heading);
        desc=rootView.findViewById(R.id.desc);
        String categoryabout= pref.get(AppSettings.categoryobjabout_category);
        String categoryobjname= pref.get(AppSettings.categoryobjname);
        String categoryobjavg_rating= pref.get(AppSettings.categoryobjavg_rating);
        String categoryobjmeta_description= pref.get(AppSettings.categoryobjmeta_description);
        String categoryobjmeta_keywords= pref.get(AppSettings.categoryobjmeta_keywords);
        String categoryobjshort_description= pref.get(AppSettings.categoryobjshort_description);
        String categoryobjabout_category= pref.get(AppSettings.categoryobjabout_category);
        tvSaloonReview.setText(categoryobjname+" in Lucknow");
        desc.setText(Html.fromHtml(categoryabout));
        heading.setText(categoryobjname);
    }
}
