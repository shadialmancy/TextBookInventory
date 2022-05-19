package com.example.textbookinventory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    Cursor cursor;
    DBManagerUser dbManagerUser;
    EditText etUsername;
    EditText etPassword;
    Intent intent;
    CheckBox ck1;
    CheckBox ck2;

    private static String ID;
    private static String name;
    private static String username;
    private static String password;
    private static String phone;
    private static String salary;
    private static String type;

    public static String getid(){
        return ID;
    }

    public static String getName(){
        return name;
    }

    public static String getUsername(){
        return username;
    }

    public static String getPassword(){
        return password;
    }

    public static String getPhone(){
        return phone;
    }

    public static String getSalary(){
        return salary;
    }

    public static String getType(){
        return type;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        dbManagerUser=new DBManagerUser(this);

    }

    public void buClose(View view) {
        finish();
    }

    public void buLogin(View view) {
        etUsername=findViewById(R.id.etUsername);
        etPassword=findViewById(R.id.etPassword);
        ck1=findViewById(R.id.checkBox);
        ck2=findViewById(R.id.checkBox2);
        boolean isCorrect=false;
        String[] SelectionArgs={"%"+etUsername.getText().toString()+"%","%"+etPassword.getText().toString()+"%"};
       if(ck1.isChecked()&&ck2.isChecked()) {
            Toast.makeText(this, "choose only 1", Toast.LENGTH_LONG).show();
        }else if(ck1.isChecked()){
            cursor = dbManagerUser.queryAdmin(null, "UserName like ? AND Password like ?", SelectionArgs, null);
            if (cursor.moveToFirst()) {
                do {
                    if (cursor.getString(2).equals(etUsername.getText().toString()) &&cursor.getString(3).equals(etPassword.getText().toString()))
                    {
                        ID=cursor.getString(0);
                        name=cursor.getString(1);
                        username=cursor.getString(2);
                        password=cursor.getString(3);
                        phone=cursor.getString(4);
                        salary=cursor.getString(5);
                        type=cursor.getString(6);
                        intent = new Intent(this, AdminForm.class);

                        startActivity(intent);
                        isCorrect = true;
                    }
                } while (cursor.moveToNext());
            }
            if (isCorrect == false)
                Toast.makeText(this, "Incorrect username or password", Toast.LENGTH_LONG).show();
        }else if(ck2.isChecked()){
            cursor = dbManagerUser.querySeller(null, "UserName like ? AND Password like ?", SelectionArgs, null);
            if (cursor.moveToFirst()) {
                do {
                    if (cursor.getString(2).equals(etUsername.getText().toString()) &&cursor.getString(3).equals(etPassword.getText().toString()))
                    {
                        ID=cursor.getString(0);
                        name=cursor.getString(1);
                        username=cursor.getString(2);
                        password=cursor.getString(3);
                        phone=cursor.getString(4);
                        salary=cursor.getString(5);
                        type=cursor.getString(6);
                        intent = new Intent(this, SellerForm.class);
                        startActivity(intent);
                        isCorrect = true;
                    }
                } while (cursor.moveToNext());
            }
            if (isCorrect == false)
                Toast.makeText(this, "Incorrect username or password", Toast.LENGTH_LONG).show();

        }else
           Toast.makeText(this, "enter username and password and check one of the following", Toast.LENGTH_LONG).show();
    }
}