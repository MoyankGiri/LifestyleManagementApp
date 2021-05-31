package com.example.lifestylemanagementapp_moyank;

public class CustomerModel {
    private String FoodItem,Quantity,Calories;

    public CustomerModel() {

    }

    // Parametrized constructor


    public CustomerModel(String foodItem, String quantity, String calories) {
        FoodItem = foodItem;
        Quantity = quantity;
        Calories = calories;
    }

    @Override
    public String toString() {
        return "CustomerModel{" +
                ", FoodItem='" + FoodItem + '\'' +
                ", Quantity='" + Quantity + '\'' +
                ", Calories='" + Calories + '\'' +
                '}';
    }

    public String getFoodItem() {
        return FoodItem;
    }

    public void setFoodItem(String foodItem) {
        FoodItem = foodItem;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }

    public String getCalories() {
        return Calories;
    }

    public void setCalories(String calories) {
        Calories = calories;
    }
}
