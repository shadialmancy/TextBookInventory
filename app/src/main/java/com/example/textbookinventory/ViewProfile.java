package com.example.textbookinventory;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ViewProfile extends AppCompatActivity {

    Cursor cursor;
    DBManagerUser dbManagerUser;
    TextView textView1;
    TextView textView2;
    TextView textView3;
    TextView textView4;
    TextView textView5;
    TextView textView6;
    TextView textView7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);


            textView1 = findViewById(R.id.txtID);
            textView1.setText(Login.getid());
            textView2 = findViewById(R.id.txtName);
            textView2.setText(Login.getName());
            textView3 = findViewById(R.id.txtUserName);
            textView3.setText(Login.getUsername());
            textView4 = findViewById(R.id.txtPassword);
            textView4.setText(Login.getPassword());
            textView5 = findViewById(R.id.txtPhone);
            textView5.setText(Login.getPhone());
            textView6 = findViewById(R.id.txtSalary);
            textView6.setText(Login.getSalary());
            textView7 = findViewById(R.id.txtType);
            textView7.setText(Login.getType());



    }

    public void buClose(View view) {
        finish();
    }
}