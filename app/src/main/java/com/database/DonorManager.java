package com.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.administrator.ie_project.Donor;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

/**
 * Created by Administrator on 3/25/2018.
 */

public class DonorManager {
    private  DBOpenHelper dbHelper;

    public DonorManager(Context context){
        dbHelper = new DBOpenHelper(context);
    }

    public  ArrayList<Donor> getAllDonors(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        ArrayList<Donor> donorList = new ArrayList<>();
        Cursor result  = db.rawQuery("select * from Donors", null);
        while (result.moveToNext()){
            System.out.println("executed once");
            String name = result.getString(result.getColumnIndex("name"));
            float latitude = result.getFloat(result.getColumnIndex("latitude"));
            float longitude = result.getFloat(result.getColumnIndex("longitude"));
            String description = result.getString(result.getColumnIndex("description"));
            Donor donor = new Donor(new LatLng(latitude,longitude),name);
            donor.setDescription(description);
            donorList.add(donor);
        }
        return donorList;
    }
}
