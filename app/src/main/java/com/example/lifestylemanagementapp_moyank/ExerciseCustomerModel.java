package com.example.lifestylemanagementapp_moyank;

public class ExerciseCustomerModel {
    private String ExerciseActivity,weight130lb,weight155lb,weight180lb,weight205lb;

    public ExerciseCustomerModel(){

    }

    public ExerciseCustomerModel(String Exerciseactivity, String weight130lb, String weight155lb, String weight180lb, String weight205lb) {
        this.ExerciseActivity = Exerciseactivity;
        this.weight130lb = weight130lb;
        this.weight155lb = weight155lb;
        this.weight180lb = weight180lb;
        this.weight205lb = weight205lb;
    }

    @Override
    public String toString() {
        return "ExerciseCustomerModel{" +
                "ExerciseActivity='" + ExerciseActivity + '\'' +
                ", weight130lb='" + weight130lb + '\'' +
                ", weight155lb='" + weight155lb + '\'' +
                ", weight180lb='" + weight180lb + '\'' +
                ", weight205lb='" + weight205lb + '\'' +
                '}';
    }

    public String getExerciseActivity() {
        return ExerciseActivity;
    }

    public void setExerciseActivity(String exerciseActivity) {
        this.ExerciseActivity = exerciseActivity;
    }

    public String getWeight130lb() {
        return weight130lb;
    }

    public void setWeight130lb(String weight130lb) {
        this.weight130lb = weight130lb;
    }

    public String getWeight155lb() {
        return weight155lb;
    }

    public void setWeight155lb(String weight155lb) {
        this.weight155lb = weight155lb;
    }

    public String getWeight180lb() {
        return weight180lb;
    }

    public void setWeight180lb(String weight180lb) {
        this.weight180lb = weight180lb;
    }

    public String getWeight205lb() {
        return weight205lb;
    }

    public void setWeight205lb(String weight205lb) {
        this.weight205lb = weight205lb;
    }

    public String getCorrectWeight(String weight){
        Integer wt = Integer.parseInt(weight);
        String data = "";
        Integer[] array = new Integer[]{130,155,180,205};
        Integer curr = array[0];
        for(int i = 0;i<4;i++){
            if(Math.abs(array[i] - wt) < Math.abs(curr - wt)){
                curr = array[i];
            }
        }
        switch (curr){
            case 130: data = getWeight130lb();break;
            case 155: data = getWeight155lb();break;
            case 180: data = getWeight180lb();break;
            case 205: data = getWeight205lb();break;
        }
        return data;
    }
}
