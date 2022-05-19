package com.example.textbookinventory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AdminForm extends AppCompatActivity {

    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_form);
    }

    public void buClose(View view) {
        finish();
    }

    public void buAdmin(View view) {
        intent=new Intent(this,AddAdmin.class);
        startActivity(intent);
    }

    public void buEmployee(View view) {
        intent = new Intent(this,AddEmployee.class);
        startActivity(intent);
    }

    public void buBook(View view) {
        intent = new Intent(this,AddBook.class);
        startActivity(intent);
    }

    public void buViewProfile(View view) {
        intent = new Intent(this,ViewProfile.class);
        startActivity(intent);
    }
}