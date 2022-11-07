package com.example.coursework2;



import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ImageAdapter extends BaseAdapter {

   private ArrayList<ImageModel> image_list;

    private Context mContext;

    public ImageAdapter(Context mContext, ArrayList<ImageModel> list) {

        this.mContext = mContext;
        this.image_list = list;
        notifyDataSetChanged();
    }
    public void updatelist(Context mContext, ArrayList<ImageModel> list) {

        this.mContext = mContext;
        this.image_list = list;
        notifyDataSetChanged();
    }




    @Override
    public int getCount() {
      return image_list.size();
    }

    @Override
    public Object getItem(int position) {
        return image_list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

            ImageModel imageModel = image_list.get(position);

             ImageView imageView = (ImageView) convertView;
            imageView = new ImageView(mContext);
            Picasso.with(mContext).load(imageModel.getImageurl()).into(imageView);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(345, 345));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            return imageView;


            
        }




    }
    



