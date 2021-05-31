package com.example.lifestylemanagementapp_moyank;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelperClass extends SQLiteOpenHelper {

    public DatabaseHelperClass(@Nullable Context context) {
        super(context, "FoodDatabase.sqlite", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        return ;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        return;

    }

    public List<CustomerModel> getCompleteDatabase(){
        List<CustomerModel> returnList = new ArrayList<>();
        SQLiteDatabase db = SQLiteDatabase.openDatabase("/data/data/com.example.lifestylemanagementapp_moyank/databases/FoodCaloriesDatabase.sqlite",null,0);
        String QueryString = "SELECT * FROM FoodCaloriesDatabase_Sheet1";

        //Cursor class is special class to get data from the database

        Cursor cursorInstance = db.rawQuery(QueryString,null); //Selection args is used to find for records with a particular condition
        if(cursorInstance.moveToFirst()) {
            do {
                String FoodName = cursorInstance.getString(0);
                String Quantity = cursorInstance.getString(1);
                String Calories = cursorInstance.getString(2);

                CustomerModel customerModel = new CustomerModel(FoodName,Quantity,Calories);
                returnList.add(customerModel);


            } while (cursorInstance.moveToNext());
        }
        cursorInstance.close();
        db.close();
        return returnList;
    }

    public List<String> getFoodNameColumnOfDatabase(){
        List<String> returnList = new ArrayList<>();
        SQLiteDatabase db = SQLiteDatabase.openDatabase("/data/data/com.example.lifestylemanagementapp_moyank/databases/FoodCaloriesDatabase.sqlite",null,0);
        String QueryString = "SELECT * FROM FoodCaloriesDatabase_Sheet1";

        //Cursor class is special class to get data from the database

        Cursor cursorInstance = db.rawQuery(QueryString,null); //Selection args is used to find for records with a particular condition
        if(cursorInstance.moveToFirst()) {
            do {

                String FoodItem = cursorInstance.getString(cursorInstance.getColumnIndex("Food"));
                returnList.add(FoodItem);
            } while (cursorInstance.moveToNext());
        }
        cursorInstance.close();
        db.close();
        return returnList;
    }

    public List<CustomerModel> getSpecificData(String foodItem){
        List<CustomerModel> Data = new ArrayList<>();
        SQLiteDatabase db = SQLiteDatabase.openDatabase("/data/data/com.example.lifestylemanagementapp_moyank/databases/FoodCaloriesDatabase.sqlite",null,0);
        String QueryString = "SELECT * FROM FoodCaloriesDatabase_Sheet1 where Food='"+foodItem + "'";

        Cursor cursor = db.rawQuery(QueryString,null);
        if(cursor.moveToFirst()){
            String FoodName = cursor.getString(0);
            String Quantity = cursor.getString(1);
            String Calories = cursor.getString(2);

            CustomerModel customerModel = new CustomerModel(FoodName,Quantity,Calories);
            Data.add(customerModel);
        }
        //System.out.println(Data);
        cursor.close();
        db.close();
        return Data;
    }
}


