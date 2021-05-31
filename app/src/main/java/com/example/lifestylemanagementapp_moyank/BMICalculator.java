package com.example.lifestylemanagementapp_moyank;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BMICalculator extends AppCompatActivity {

    Button bmiResultButton;//foodCalorieButton,exerciseButton;
    TextView heightTextView,weightTextView,resultTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmicalculator);

        bmiResultButton = findViewById(R.id.calculateBMIButton);
        heightTextView = findViewById(R.id.heightTextView);
        weightTextView = findViewById(R.id.weightTextView);
        resultTextView = findViewById(R.id.resultTextView);
        /*foodCalorieButton = findViewById(R.id.foodCalorieButton);
        exerciseButton = findViewById(R.id.ExerciseButton);

        foodCalorieButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(BMICalculator.this,EnterDetailsActivity.class);
                startActivity(i);
            }
        });

        exerciseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(BMICalculator.this,ExerciseDataEnter.class);
                startActivity(i);
            }
        });*/

        bmiResultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String height = heightTextView.getText().toString();
                Double EnteredHeight = Double.parseDouble(height);
                String weight = weightTextView.getText().toString();
                Double EnteredWeight = Double.parseDouble(weight);
                Double res = (EnteredWeight * 10000.0)/(EnteredHeight * EnteredHeight);

                if(res <= 18){
                    resultTextView.setText("UnderWeight : " + (double) Math.round(res * 100) / 100);
                }
                else if(res > 18 && res < 25){
                    resultTextView.setText("Healthy : " + (double) Math.round(res * 100) / 100);
                }
                else{
                    if(res > 40){
                        resultTextView.setText("OverWeight : >40");
                    }
                    else{
                        resultTextView.setText("OverWeight : " + (double) Math.round(res * 100) / 100);
                    }
                }
            }
        });
    }
}
