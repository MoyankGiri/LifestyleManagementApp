package com.example.lifestylemanagementapp_moyank;

public class UserDataBaseCustomerModel {

    private int id;
    private String date;
    private double calorieIntake,calorieBurnt;

    public UserDataBaseCustomerModel(){

    }

    public UserDataBaseCustomerModel(int id, String date, double calorieIntake, double calorieBurnt){
        this.id = id;

        this.date = date;
        this.calorieIntake = calorieIntake;
        this.calorieBurnt = calorieBurnt;
    }

    @Override
    public String toString() {
        return "UserDataBaseCustomerModel{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", calorieIntake=" + calorieIntake +
                ", calorieBurnt=" + calorieBurnt +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getCalorieIntake() {
        return calorieIntake;
    }

    public void setCalorieIntake(double calorieIntake) {
        this.calorieIntake = calorieIntake;
    }

    public double getCalorieBurnt() {
        return calorieBurnt;
    }

    public void setCalorieBurnt(double calorieBurnt) {
        this.calorieBurnt = calorieBurnt;
    }
}
