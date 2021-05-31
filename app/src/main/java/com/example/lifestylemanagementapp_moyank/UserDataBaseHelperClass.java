package com.example.lifestylemanagementapp_moyank;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class UserDataBaseHelperClass extends SQLiteOpenHelper {


    public static final String USER_TABLE = "USER_TABLE";
    public static final String ID = "ID";
    public static final String ENTRY_DATE = "ENTRY_DATE";
    public static final String CALORIE_INTAKE = "CALORIE_INTAKE";
    public static final String CALORIE_BURNT = "CALORIE_BURNT";

    public UserDataBaseHelperClass(@Nullable Context context) {
        super(context, "UsersDataBase", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String CreateTable = "CREATE TABLE " + USER_TABLE + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " + ENTRY_DATE + " STRING ," + CALORIE_INTAKE + " DOUBLE , " + CALORIE_BURNT + " DOUBLE)";
        db.execSQL(CreateTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        return;
    }

    public boolean addRecordtoUserDatabase(UserDataBaseCustomerModel userDataBaseCustomerModel ){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ENTRY_DATE,userDataBaseCustomerModel.getDate());
        contentValues.put(CALORIE_INTAKE,userDataBaseCustomerModel.getCalorieIntake());
        contentValues.put(CALORIE_BURNT,userDataBaseCustomerModel.getCalorieBurnt());

        long InsertionInstance = db.insert(USER_TABLE, null, contentValues);

        if(InsertionInstance == -1){
            System.out.println("FAILED");
            return false;
        }
        System.out.println("OK");
        db.close();
        return true;
    }

    public List<UserDataBaseCustomerModel> getUserDatabaseDetails(){
        List<UserDataBaseCustomerModel> returnList = new ArrayList<>();
        String QueryString = "SELECT * FROM "+ USER_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();

        //Cursor class is special class to get data from the database

        Cursor cursorInstance = db.rawQuery(QueryString,null); //Selection args is used to find for records with a particular condition
        if(cursorInstance.moveToFirst()) {
            do {
                int ID = cursorInstance.getInt(0);
                String EntryDate = cursorInstance.getString(1);
                String CalorieIntake = cursorInstance.getString(2);
                String CalorieBurnt = cursorInstance.getString(3);

                UserDataBaseCustomerModel userDataBaseCustomerModel = new UserDataBaseCustomerModel(ID,EntryDate,Double.parseDouble(CalorieIntake),Double.parseDouble(CalorieBurnt));
                returnList.add(userDataBaseCustomerModel);


            } while (cursorInstance.moveToNext());
        }
        cursorInstance.close();
        db.close();
        return returnList;
    }

    public boolean CheckIsDataAlreadyInDBorNot(String dbfield, String fieldValue) {
        String Query = "Select * from " + USER_TABLE + " where " + dbfield + " =? ";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(Query,new String[]{fieldValue});
        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }

    public boolean UpdateCalorieIntakeInDatabase(String date,Double calorieIntake){

        SQLiteDatabase db = this.getWritableDatabase();
        /*ContentValues cv = new ContentValues();
        cv.put(ENTRY_DATE,date);
        cv.put(CALORIE_INTAKE,calorieIntake);*/
        List<UserDataBaseCustomerModel> currentValue = getSpecificData(date);
        Double CalorieIntakeCurrentValue = currentValue.get(0).getCalorieIntake();
        Double CalorieIntakeFinal = calorieIntake + CalorieIntakeCurrentValue;
        //db.execSQL("UPDATE " + USER_TABLE + " SET " + CALORIE_INTAKE +"=" + CalorieIntakeFinal + " WHERE " + ENTRY_DATE + "=" + date);
        ContentValues cv = new ContentValues();
        cv.put(CALORIE_INTAKE,CalorieIntakeFinal);
        db.update(USER_TABLE,cv,ENTRY_DATE + "=?",new String[]{date});
        return true;
    }

    public boolean UpdateCalorieBurntInDatabase(String date,Double calorieBurnt){
        SQLiteDatabase db = this.getWritableDatabase();
        List<UserDataBaseCustomerModel> currentValue = getSpecificData(date);
        Double CalorieBurntCurrentValue = currentValue.get(0).getCalorieBurnt();
        Double CalorieBurntFinal = calorieBurnt + CalorieBurntCurrentValue;
        //db.execSQL("UPDATE " + USER_TABLE + " SET " + CALORIE_BURNT +"=" + CalorieBurntFinal + " WHERE " + ENTRY_DATE + "=" + date);
        ContentValues cv = new ContentValues();
        cv.put(CALORIE_BURNT,CalorieBurntFinal);
        db.update(USER_TABLE,cv,ENTRY_DATE + "=?",new String[]{date});
        return true;
    }

    public List<UserDataBaseCustomerModel> getSpecificData(String date){
        List<UserDataBaseCustomerModel> Data = new ArrayList<>();
        String Query = "Select * from " + USER_TABLE + " where " + ENTRY_DATE + " =? ";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(Query,new String[]{date});
        if(cursor.moveToFirst()){
            Integer id = cursor.getInt(0);
            String dateFromDatabase = cursor.getString(1);
            Double caloriesIntake = cursor.getDouble(2);
            Double caloriesBurnt = cursor.getDouble(3);

            UserDataBaseCustomerModel userDataBaseCustomerModel = new UserDataBaseCustomerModel(id,dateFromDatabase,caloriesIntake,caloriesBurnt);
            Data.add(userDataBaseCustomerModel);
        }
        //System.out.println(Data);
        Log.i(null,"" + Data);
        cursor.close();
        return Data;
    }
}
