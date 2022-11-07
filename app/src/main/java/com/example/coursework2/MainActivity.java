package com.example.coursework2;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;

import androidx.lifecycle.Observer;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    protected int _currentIndex;
    ArrayList<ImageModel> imageList = new ArrayList<>();
    EditText imageUrl_input;
    Button add;
    ImageView camera;
    MyImageDatabaseHelper myImageDatabaseHelper = new MyImageDatabaseHelper(this);
    ImageAdapter imageAdapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageList = getImagefromDb();
        _currentIndex = 0;
        GridView gridView = findViewById(R.id.myGrid);
        imageAdapter = new ImageAdapter(this, imageList);
        gridView.setAdapter(imageAdapter);
        ImageViewModel viewModel = new ViewModelProvider(this).get(ImageViewModel.class);
        viewModel.getDataStatus().observe(this, status -> {
            ArrayList<ImageModel> _list = getImagefromDb();
            imageAdapter.updatelist(this, _list);
        });
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ShowDialogBox(position);

            }
        });
        imageUrl_input = findViewById(R.id.addURL);
        add = findViewById(R.id.addURLButton);

            add.setOnClickListener(view -> {
                addImage();
                viewModel.setDataStatus(new Date().toString());
            });
        camera = findViewById(R.id.getCameraURLButton);
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentActivity fragmentActivity = (FragmentActivity) view.getContext();
                new AccessCameraDialog().show(fragmentActivity.getSupportFragmentManager(), null);
            }
        });



    }

    protected ArrayList<ImageModel>  getImagefromDb(){
        return myImageDatabaseHelper.getAllImage();

    }
    protected void addImage() {
        String imageURL = imageUrl_input.getText().toString();
        ImageModel imageModel = new ImageModel();
        imageModel.setImageurl(imageURL);
        if(imageURL.length() != 0){
        myImageDatabaseHelper.InsertImagetoDb(imageModel);
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(imageUrl_input.getWindowToken(), 0);
        imageUrl_input.setText("");
        Toast.makeText(this, "New image has been added.", Toast.LENGTH_SHORT).show();

        }else{
            Toast.makeText(this, "Please enter image url", Toast.LENGTH_SHORT).show();
        }
    }

    public void ShowDialogBox ( int item_pos){
        final Dialog dialog = new Dialog(this, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        _currentIndex = item_pos;
        dialog.setContentView(R.layout.fragment_preview_image);

        //Getting custom dialog views
        ImageView Image = dialog.findViewById(R.id.img);
        Button btn_dismiss = dialog.findViewById(R.id.btn_dismiss);
        ImageButton forward = dialog.findViewById(R.id.imagebuttonforward);
        ImageButton back = dialog.findViewById(R.id.imagebuttonback);
        ArrayList<String> _imagelist =  myImageDatabaseHelper.getImage();
        Picasso.with(this).load(_imagelist.get( item_pos)).into(Image);
        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{

                       int i = ++_currentIndex;
                    Picasso.with(getApplicationContext()).load(_imagelist.get(i)).into(Image);

                }catch(Exception error1) {
                    Log.e(TAG, "The exception caught while executing the process. (error1)");
                    Toast.makeText(getApplicationContext(), "File not found.", Toast.LENGTH_SHORT).show();
                    error1.printStackTrace();
                }

            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{

                        int i = --_currentIndex;
                        Picasso.with(getApplicationContext()).load(_imagelist.get(i)).into(Image);

                }catch(Exception error1) {
                    Log.e(TAG, "The exception caught while executing the process. (error1)");
                    Toast.makeText(getApplicationContext(), "File not found.", Toast.LENGTH_SHORT).show();
                    error1.printStackTrace();
                }

            }
        });
        btn_dismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }






}