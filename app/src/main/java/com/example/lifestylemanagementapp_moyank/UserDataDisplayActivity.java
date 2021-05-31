package com.example.lifestylemanagementapp_moyank;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class UserDataDisplayActivity extends AppCompatActivity {

    ListView customerDatabaseListView;
    ArrayAdapter dataArrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data_display);

        customerDatabaseListView = findViewById(R.id.UserDatabaseListView);

        UserDataBaseHelperClass userDataBaseHelperClass = new UserDataBaseHelperClass(UserDataDisplayActivity.this);

        List<UserDataBaseCustomerModel>dataBaseList = userDataBaseHelperClass.getUserDatabaseDetails();

        dataArrayAdapter = new ArrayAdapter<UserDataBaseCustomerModel>(UserDataDisplayActivity.this,android.R.layout.simple_list_item_1,dataBaseList);
        customerDatabaseListView.setAdapter(dataArrayAdapter);
    }
}
