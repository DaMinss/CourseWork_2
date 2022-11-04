package com.example.coursework2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
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

public class MainActivity extends AppCompatActivity {
    protected static final String file_path = "CourseWork2_image_list.txt";
    protected int _currentIndex;
    ArrayList<String> imageList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageList = getImagefromURL();
        GridView gridView = findViewById(R.id.myGrid);
        gridView.setAdapter(new ImageAdapter(this, imageList));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                _currentIndex = position;
                ShowDialogBox(_currentIndex);
            }
        });



    }

    protected ArrayList<String>  getImagefromURL(){
        ArrayList<String> _image = new ArrayList<>();
        _image.add("https://images.pexels.com/photos/1366919/pexels-photo-1366919.jpeg");
        _image.add("https://iphoneswallpapers.com/wp-content/uploads/2020/03/Yosemite-Nature-iPhone-Wallpaper.jpg");
        InputStream(_image);
        Toast.makeText(this, "Get list successfully.", Toast.LENGTH_SHORT).show();
        return _image;

    }
    protected void OutputStream(String url) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput(file_path, MODE_APPEND));
            outputStreamWriter.write(url);
            outputStreamWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(this, "File not found.", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void InputStream(ArrayList<String> _image) {
        try {
            InputStream inputStream = this.openFileInput(file_path);

            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                String url = "";
                while ((url = bufferedReader.readLine()) != null) {
                    _image.add(url);
                }

                inputStream.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
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
        Picasso.with(this).load(imageList.get( item_pos)).into(Image);
        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               int upindex = ++_currentIndex;
                Picasso.with(getApplicationContext()).load(imageList.get( upindex)).into(Image);

            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int downindex = --_currentIndex;
                Picasso.with(getApplicationContext()).load(imageList.get(downindex)).into(Image);
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
    protected void nextImage() {
        ++_currentIndex;

    }

    protected void previousImage() {
        --_currentIndex;

    }




}