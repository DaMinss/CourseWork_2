package com.example.coursework2;

import androidx.annotation.NonNull;

public class ImageModel {
    int Image_Id;
    @NonNull
    String Imageurl;

    public ImageModel( int image_Id, String imageurl) {
        Imageurl = imageurl;
        Image_Id = image_Id;
    }

    public ImageModel() {

    }

    public int getImage_Id() {
        return Image_Id;
    }

    public void setImage_Id(int image_Id) {
        Image_Id = image_Id;
    }

    public String getImageurl() {
        return Imageurl;
    }

    public void setImageurl(String imageurl) {
        Imageurl = imageurl;
    }
}
