package com.example.taxi;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapter_01 extends BaseAdapter {
    Activity activity;
    ArrayList<Taxi_01> data;
    LayoutInflater inflater;

    public Adapter_01(Activity activity, ArrayList<Taxi_01> data){
        this.activity = activity;
        this.data = data;
        this.inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return data.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = view;
        if(v == null){
            v = inflater.inflate(R.layout.item, null);
            TextView txtCarId = v.findViewById(R.id.txtCar);
            txtCarId.setText(data.get(i).getCarId());
            TextView txtPrice = v.findViewById(R.id.txtPrice);
            txtPrice.setText(" "+data.get(i).TotalPrice());
            TextView txtDistance = v.findViewById(R.id.txtDistance);
            txtDistance.setText("Distance: "+ data.get(i).getDistance() + " km");
        }
        return v;
    }
}
