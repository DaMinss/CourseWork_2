package com.example.coursework2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class MyImageDatabaseHelper extends SQLiteOpenHelper {

    public static final String Database_Name = "Image_database";
    private static final int Database_Version = 1;

    //trip
    private static final String Table_Image = "My_image";
    private static final String Image_ID = "image_id";
    private static final String Image_URL = "image_url";

    public MyImageDatabaseHelper(@Nullable Context context) {
        super(context, Database_Name, null, Database_Version);
    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + Table_Image +
                " (" + Image_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    Image_URL + " TEXT); " ;
        sqLiteDatabase.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Table_Image);
        onCreate(sqLiteDatabase);
    }
    public long InsertImagetoDb( @NotNull ImageModel imageModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("image_url", imageModel.getImageurl());

        long result = db.insert(Table_Image, null, contentValues);
        return result;
    }

    public ArrayList<ImageModel> getAllImage() {
        ArrayList<ImageModel> Image_list = new ArrayList();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + Table_Image;
        Cursor cursor = db.rawQuery(query, null);
        Integer imageid;
        String imagename;


        while (cursor.moveToNext()) {
            imageid= cursor.getInt(cursor.getColumnIndexOrThrow(Image_ID));
            imagename = cursor.getString(cursor.getColumnIndexOrThrow(Image_URL));

            ImageModel info = new ImageModel(imageid, imagename);

            Image_list.add(info);
        }

        return Image_list;
    }
    public ArrayList<String> getImage() {
        ArrayList<String> _list = new ArrayList();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT image_url FROM " + Table_Image;
        Cursor cursor = db.rawQuery(query, null);
        String imagename;


        while (cursor.moveToNext()) {

            imagename = cursor.getString(cursor.getColumnIndexOrThrow(Image_URL));

            _list.add(imagename);
        }
        return _list;
    }


}
