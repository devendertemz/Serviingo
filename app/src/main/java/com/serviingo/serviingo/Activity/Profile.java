package com.serviingo.serviingo.Activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.serviingo.serviingo.R;
import com.serviingo.serviingo.view.BaseFragment;

public class Profile extends BaseFragment {
    View rootView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.profilefragment, container, false);

        setUi();
        return rootView;
    }

    private void setUi() {

    }
}
