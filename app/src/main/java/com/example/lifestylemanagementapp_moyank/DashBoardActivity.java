package com.example.lifestylemanagementapp_moyank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.google.firebase.auth.FirebaseAuth;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class DashBoardActivity extends AppCompatActivity {

    PieChart pieChart;
    Button CaloriesBurntButton,CaloriesIntakeButton,BMIButton;
    final UserDataBaseHelperClass userDataBaseHelperClass = new UserDataBaseHelperClass(DashBoardActivity.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        pieChart = findViewById(R.id.pieChart_view);
        showPieChart();
        initPieChart();

        CaloriesIntakeButton = findViewById(R.id.CalorieIntakeButton);
        CaloriesBurntButton = findViewById(R.id.CalorieBurntButton);
        BMIButton = findViewById(R.id.BMICalculatorButton);

        CaloriesIntakeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DashBoardActivity.this,EnterDetailsActivity.class);
                startActivity(i);
            }
        });

        CaloriesBurntButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DashBoardActivity.this,ExerciseDataEnter.class);
                startActivity(i);
            }
        });

        BMIButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DashBoardActivity.this,BMICalculator.class);
                startActivity(i);
            }
        });
    }

    private void showPieChart(){

        Double calorieIntakeValue = 0.0;
        Double calorieBurntValue = 0.0;
        ArrayList<PieEntry> pieEntries = new ArrayList<>();
        String label  = "type";
        String Todaysdate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        if(userDataBaseHelperClass.CheckIsDataAlreadyInDBorNot("ENTRY_DATE",Todaysdate)) {
            List<UserDataBaseCustomerModel> TodaysValues = userDataBaseHelperClass.getSpecificData(Todaysdate);
            calorieIntakeValue = TodaysValues.get(0).getCalorieIntake();
            calorieBurntValue = TodaysValues.get(0).getCalorieBurnt();
        }
        Map<String,Integer> typeAmountMap = new HashMap<>();
        typeAmountMap.put("Calories Intake : " + calorieIntakeValue,(int) Math.round(calorieIntakeValue));
        typeAmountMap.put("Calories Burnt : " + calorieBurntValue,(int) Math.round(calorieBurntValue));

        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.parseColor("#de1310"));
        colors.add(Color.parseColor("#14d914"));

        for(String type: typeAmountMap.keySet()){
            pieEntries.add(new PieEntry(typeAmountMap.get(type).floatValue(), type));
        }

        PieDataSet pieDataSet = new PieDataSet(pieEntries,label);
        //setting text size of the value
        pieDataSet.setValueTextSize(12f);
        //providing color list for coloring different entries
        pieDataSet.setColors(colors);
        //grouping the data set from entry to chart
        PieData pieData = new PieData(pieDataSet);
        //showing the value of the entries, default true if not set
        pieData.setDrawValues(true);

        pieChart.setData(pieData);
        pieChart.invalidate();
    }

    private void initPieChart(){
        //using percentage as values instead of amount
        pieChart.setUsePercentValues(true);

        //remove the description label on the lower left corner, default true if not set
        pieChart.getDescription().setEnabled(false);

        //enabling the user to rotate the chart, default true
        pieChart.setRotationEnabled(true);
        //adding friction when rotating the pie chart
        pieChart.setDragDecelerationFrictionCoef(0.9f);
        //setting the first entry start from right hand side, default starting from top
        pieChart.setRotationAngle(0);

        //highlight the entry when it is tapped, default true if not set
        pieChart.setHighlightPerTapEnabled(true);
        //adding animation so the entries pop up from 0 degree
        pieChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.options_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId()){
            case R.id.UserDataFromDatabase:
                Intent i = new Intent(DashBoardActivity.this,UserDataDisplayActivity.class);
                startActivity(i);
                return true;
            case R.id.signOutOption:
                FirebaseAuth firebaseAuth;
                FirebaseAuth.AuthStateListener authStateListener = new FirebaseAuth.AuthStateListener() {
                    @Override
                    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                        if (firebaseAuth.getCurrentUser() == null){
                            Intent i =  new Intent(DashBoardActivity.this,MainActivity.class);
                            startActivity(i);
                        }
                        else {
                        }
                    }
                };
                firebaseAuth = FirebaseAuth.getInstance();
                firebaseAuth.addAuthStateListener(authStateListener);

                firebaseAuth.signOut();
                return true;
        }
        return false;
    }
}
