package com.example.omur.contacs;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by omur on 16.04.2016.
 */
public class MyListAdapter extends BaseAdapter {

    private LayoutInflater minflater ;
    private List<PersonalInfo> mpersonalınfo ;

    public MyListAdapter(Activity activity , List<PersonalInfo> personalInfo)
    {
        minflater=(LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mpersonalınfo=personalInfo ;

    }

    @Override
    public int getCount() {
        return mpersonalınfo.size();
    }

    @Override
    public Object getItem(int position) {
        return mpersonalınfo.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View satirview ;

        satirview=minflater.inflate(R.layout.listlayout,null) ;

        TextView isim =(TextView) satirview.findViewById(R.id.ad) ;
        Button phoneno = (Button) satirview.findViewById(R.id.numara);
        ImageView picture = (ImageView) satirview.findViewById(R.id.imageView) ;

        PersonalInfo personalInfo = mpersonalınfo.get(position);

        isim.setText(personalInfo.getName());
        phoneno.setText(personalInfo.getNo());
        picture.setImageResource(personalInfo.getResim());


        return satirview;
    }
}
