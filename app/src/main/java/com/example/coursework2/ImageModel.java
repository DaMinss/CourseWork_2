package com.example.coursework2;

public class ImageModel {
    int Image_Id;
    String Imageurl;

    public ImageModel( int image_Id, String imageurl) {
        Imageurl = imageurl;
        Image_Id = image_Id;
    }

    public String getImageurl() {
        return Imageurl;
    }

    public void setImageurl(String imageurl) {
        Imageurl = imageurl;
    }
}
