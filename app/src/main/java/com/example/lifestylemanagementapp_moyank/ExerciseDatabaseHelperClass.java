package com.example.lifestylemanagementapp_moyank;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ExerciseDatabaseHelperClass extends SQLiteOpenHelper {
    public ExerciseDatabaseHelperClass(@Nullable Context context) {
        super(context, "ExerciseCaloriesDatabase.sqlite", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public List<ExerciseCustomerModel> getCompleteDatabase(){
        List<ExerciseCustomerModel> returnList = new ArrayList<>();
        SQLiteDatabase db = SQLiteDatabase.openDatabase("/data/data/com.example.lifestylemanagementapp_moyank/databases/ExerciseCaloriesDatabase.sqlite",null,0);
        String QueryString = "SELECT * FROM ExerciseCaloriesDatabase_Sheet1";

        //Cursor class is special class to get data from the database

        Cursor cursorInstance = db.rawQuery(QueryString,null); //Selection args is used to find for records with a particular condition
        if(cursorInstance.moveToFirst()) {
            do {
                String ExerciseActivity = cursorInstance.getString(0);
                String weight130lb = cursorInstance.getString(1);
                String weight155lb = cursorInstance.getString(2);
                String weight180lb = cursorInstance.getString(3);
                String weight205lb = cursorInstance.getString(4);

                ExerciseCustomerModel exerciseCustomerModel = new ExerciseCustomerModel(ExerciseActivity,weight130lb,weight155lb,weight180lb,weight205lb);
                returnList.add(exerciseCustomerModel);

            } while (cursorInstance.moveToNext());
        }
        cursorInstance.close();
        db.close();
        return returnList;
    }

    public List<String> getExerciseActivityColumnOfDatabase(){
        List<String> returnList = new ArrayList<>();
        SQLiteDatabase db = SQLiteDatabase.openDatabase("/data/data/com.example.lifestylemanagementapp_moyank/databases/ExerciseCaloriesDatabase.sqlite",null,0);
        String QueryString = "SELECT * FROM ExerciseCaloriesDatabase_Sheet1";

        //Cursor class is special class to get data from the database

        Cursor cursorInstance = db.rawQuery(QueryString,null); //Selection args is used to find for records with a particular condition
        if(cursorInstance.moveToFirst()) {
            do {

                String ActivityExercise = cursorInstance.getString(cursorInstance.getColumnIndex("Activity"));
                returnList.add(ActivityExercise);
            } while (cursorInstance.moveToNext());
        }
        cursorInstance.close();
        db.close();
        return returnList;
    }

    public String getSpecificData(String Exercise,String weight){
        String Data = "";
        SQLiteDatabase db = SQLiteDatabase.openDatabase("/data/data/com.example.lifestylemanagementapp_moyank/databases/ExerciseCaloriesDatabase.sqlite",null,0);
        String QueryString = "SELECT * FROM ExerciseCaloriesDatabase_Sheet1 where Activity='"+Exercise + "'";

        Cursor cursor = db.rawQuery(QueryString,null);
        if(cursor.moveToFirst()){
            Integer wt = Integer.parseInt(weight);
            Integer[] array = new Integer[]{130,155,180,205};
            Integer curr = array[0];
            for(int i = 0;i<4;i++){
                if(Math.abs(array[i] - wt) < Math.abs(curr - wt)){
                    curr = array[i];
                }
            }
            Data = cursor.getString(cursor.getColumnIndex(curr + " lb"));
        }
        //System.out.println(Data);
        cursor.close();
        db.close();
        return Data;
    }
}
