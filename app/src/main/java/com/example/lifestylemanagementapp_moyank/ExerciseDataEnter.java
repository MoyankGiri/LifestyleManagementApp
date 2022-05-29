package com.example.lifestylemanagementapp_moyank;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.shrikanthravi.collapsiblecalendarview.data.Day;
import com.shrikanthravi.collapsiblecalendarview.widget.CollapsibleCalendar;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ExerciseDataEnter extends AppCompatActivity {

    AutoCompleteTextView ExerciseAutoCompleteTextView;
    ArrayAdapter ExerciseList;
    Button AddCaloriesButton;
    String SelectedItem = null,finalDate;
    EditText TimeEditText,WeightEditText;
    CollapsibleCalendar collapsibleCalendar;
    Double CalorieIntake = 0.0,CalorieBurnt = 0.0;
    TextView calorieIntakeTextView,calorieBurnttextview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_data_enter);

        ExerciseAutoCompleteTextView = findViewById(R.id.ExerciseAutoCompleteTextView);
        AddCaloriesButton = findViewById(R.id.AddCaloriesButton1);
        TimeEditText = findViewById(R.id.TimeEditText);
        collapsibleCalendar = findViewById(R.id.CollapsableCalendarView1);
        calorieIntakeTextView = findViewById(R.id.CalorieIntakeTextView1);
        calorieBurnttextview = findViewById(R.id.CalorieBurntTextView1);
        WeightEditText = findViewById(R.id.weightEditText);

        final ExerciseDatabaseHelperClass exerciseDatabaseHelperClass = new ExerciseDatabaseHelperClass(ExerciseDataEnter.this);
        final UserDataBaseHelperClass userDataBaseHelperClass = new UserDataBaseHelperClass(ExerciseDataEnter.this);
        final List<String> ExerciseFromDatabase = exerciseDatabaseHelperClass.getExerciseActivityColumnOfDatabase();
        ExerciseList = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item, ExerciseFromDatabase);
        ExerciseAutoCompleteTextView.setAdapter(ExerciseList);
        ExerciseAutoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(EnterDetailsActivity.this, "You have Selected " + (String)parent.getItemAtPosition(position), Toast.LENGTH_LONG).show();
                SelectedItem = parent.getSelectedItem().toString();
            }
        });
        AddCaloriesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((SelectedItem != null) && (finalDate != null) && (WeightEditText.getText().toString() != null)) {
                    Double Time = 0.0;
                    String SelectedExerciseCalories = exerciseDatabaseHelperClass.getSpecificData(SelectedItem, WeightEditText.getText().toString());
                    //Toast.makeText(EnterDetailsActivity.this, "Selected Data is : " + SelectedFoodItemFromDatabase, Toast.LENGTH_SHORT).show();
                    //System.out.println((SelectedFoodItemFromDatabase.get(0).getCalories()).replace(" cal",""));
                    String cal = SelectedExerciseCalories;
                    if (cal.contains("cal")) {
                        cal = cal.replaceAll("cal", "");
                        cal = cal.replaceAll("\\s", "");
                    }
                    Double CaloriesInItem = Double.parseDouble(cal);
                    Log.i("Calories in item : ", "" + CaloriesInItem);
                    try {
                        Time = Double.parseDouble(TimeEditText.getText().toString());
                    } catch (Exception e) {
                        Toast.makeText(ExerciseDataEnter.this, "Some Error has occurred...", Toast.LENGTH_SHORT).show();
                        Time = 0.0;
                    }
                    DecimalFormat f = new DecimalFormat("##.00");
                    Double TotalCaloriesBurnt = CaloriesInItem * Double.parseDouble(f.format(Time / 1.0));
                    CalorieBurnt += TotalCaloriesBurnt;
                    //Toast.makeText(EnterDetailsActivity.this, "" + TotalCalories, Toast.LENGTH_SHORT).show();

                    if (userDataBaseHelperClass.CheckIsDataAlreadyInDBorNot("ENTRY_DATE", finalDate)) {
                        userDataBaseHelperClass.UpdateCalorieBurntInDatabase(finalDate, CalorieBurnt);
                        Toast.makeText(ExerciseDataEnter.this, "Data updated ", Toast.LENGTH_SHORT).show();
                        //System.out.println(date.getClass().getName());
                    } else {
                        UserDataBaseCustomerModel userDataBaseCustomerModel = new UserDataBaseCustomerModel(1, finalDate, CalorieIntake, CalorieBurnt);
                        userDataBaseHelperClass.addRecordtoUserDatabase(userDataBaseCustomerModel);
                        Toast.makeText(ExerciseDataEnter.this, "Data added", Toast.LENGTH_SHORT).show();
                    }
                    String Todaysdate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                    Log.i("todays date ", Todaysdate + userDataBaseHelperClass.CheckIsDataAlreadyInDBorNot("ENTRY_DATE", Todaysdate));
                    if (userDataBaseHelperClass.CheckIsDataAlreadyInDBorNot("ENTRY_DATE", Todaysdate)) {
                        List<UserDataBaseCustomerModel> TodaysValues = userDataBaseHelperClass.getSpecificData(Todaysdate);
                        System.out.println(TodaysValues);
                        calorieIntakeTextView.setText(TodaysValues.get(0).getCalorieIntake() + "");
                        calorieBurnttextview.setText(TodaysValues.get(0).getCalorieBurnt() + "");
                    }

                }
                else
                    Toast.makeText(ExerciseDataEnter.this, "Please input all values and retry...", Toast.LENGTH_LONG).show();
            }
        });
        collapsibleCalendar.setCalendarListener(new CollapsibleCalendar.CalendarListener() {
            @Override
            public void onDaySelect() {
                Day day = collapsibleCalendar.getSelectedDay();
                if(day.getMonth() + 1 >= 10){
                    if(day.getDay() >= 10){
                        finalDate = day.getYear() + "-" + (day.getMonth() + 1) + "-" + day.getDay();
                    }
                    else {
                        finalDate = day.getYear() + "-" + (day.getMonth() + 1) + "-0" + day.getDay();
                    }
                }
                else {
                    if(day.getDay() >= 10){
                        finalDate = day.getYear() + "-0" + (day.getMonth() + 1) + "-" + day.getDay();
                    }
                    else {
                        finalDate = day.getYear() + "-0" + (day.getMonth() + 1) + "-0" + day.getDay();
                    }
                }
            }

            @Override
            public void onItemClick(View view) {

            }

            @Override
            public void onDataUpdate() {

            }

            @Override
            public void onMonthChange() {

            }

            @Override
            public void onWeekChange(int i) {

            }

            @Override
            public void onClickListener() {

            }

            @Override
            public void onDayChanged() {

            }
        });
    }
}
