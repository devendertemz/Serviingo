package com.serviingo.serviingo.Adapterr;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.serviingo.serviingo.R;
import com.serviingo.serviingo.model.CategoryModel;
import com.serviingo.serviingo.model.CityModel;

import java.util.ArrayList;

public class CategorySpinnerAdapter extends BaseAdapter {
    Context context;
    ArrayList<CategoryModel>arrayListt;
    LayoutInflater inflter;

    public CategorySpinnerAdapter(Context context, ArrayList<CategoryModel> arrayList) {
        this.context = context;
        this.arrayListt = arrayList;
        inflter = (LayoutInflater.from(context));

    }




    @Override
    public int getCount() {
        return arrayListt.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        view = inflter.inflate(R.layout.state_layout, null);
        TextView coinNames = (TextView) view.findViewById(R.id.coinName);
        //TextView coinSymbol = (TextView) view.findViewById(R.id.coinsymbols);

       // coinImages.setImageResource(coinImage[i]);

        if (arrayListt.get(position).id.equalsIgnoreCase("00"))
        {
            coinNames.setText(arrayListt.get(position).name);
            coinNames.setTextColor(Color.parseColor("#757575"));
            coinNames.setTextSize(18);

        }
        else {
            coinNames.setText(arrayListt.get(position).name);
            Log.e("check",arrayListt.get(position).name);
            // coinSymbol.setText(coinSymbols[position]);
        }



        return view;
    }
    }

