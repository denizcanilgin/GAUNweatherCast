package com.example.gaunhavajson.Adapter;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.gaunhavajson.Model.Hava;
import com.example.gaunhavajson.R;

import java.util.ArrayList;

public class HavaAdapter extends BaseAdapter {

    Context context;
    ArrayList<Hava> channel;
    LayoutInflater layoutInflater;

    public HavaAdapter(Activity activity,ArrayList<Hava> channel){
        this.context=activity;
        this.channel= channel;
        this.layoutInflater = (LayoutInflater)context.getSystemService(Service.LAYOUT_INFLATER_SERVICE);

    }


    @Override
    public int getCount() {
        return channel.size();
    }


    @Override
    public Hava getItem(int position) {
        return channel.get(position);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.hava_gorunumu,parent,false);

        TextView tvEntryID = (TextView)convertView.findViewById(R.id.tvEntryID);
        TextView tvCreatedAt = (TextView)convertView.findViewById(R.id.tvCreatedAt);
        TextView tvF3 = (TextView)convertView.findViewById(R.id.tvField3);
        TextView tvF4 = (TextView)convertView.findViewById(R.id.tvField4);

        tvEntryID.setText(channel.get(position).getEntry_Id() + "");
        tvCreatedAt.setText(channel.get(position).getCreated_At() + "");
        tvF3.setText(channel.get(position).getField3() + "");
        tvF4.setText(channel.get(position).getField4() + "");

        if(channel.get(position).getEntry_Id() == 81818188)
            tvEntryID.setText("ID");

        if(channel.get(position).getField3() == 91919191) {
            tvF3.setText("Field3");
        }
        if(channel.get(position).getField4() == 71717171) {
            tvF4.setText("Field4");
        }

        return convertView;

    }
}
