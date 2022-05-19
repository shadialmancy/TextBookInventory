package com.example.textbookinventory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AddAdmin extends AppCompatActivity {
    ArrayList<AdapterAdmin> listnewsData = new ArrayList<AdapterAdmin>();
    MyCustomAdapter myadapter;

    DBManagerUser dbManagerUser;
    Cursor cursor;
    EditText textbook1;
    EditText textbook2;
    EditText textbook3;
    EditText textbook4;
    EditText textbook5;
    EditText textbook6;
    EditText textbook7;
    EditText tvSearch;
    EditText tvDelete;
    ListView listview1;
    Button buDelete;
    Button buSelect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_admin);
        dbManagerUser = new DBManagerUser(this);
        LoadAllElement();
        textbook1 = findViewById(R.id.textbook1);
        textbook2 = findViewById(R.id.textbook2);
        textbook3 = findViewById(R.id.textbook3);
        textbook4 = findViewById(R.id.textbook4);
        textbook5 = findViewById(R.id.textbook5);
        textbook6 = findViewById(R.id.textbook6);
        textbook7 = findViewById(R.id.textbook7);
    }

    void LoadAllElement(){
        cursor=dbManagerUser.queryAdmin(null,null,null,null);
        //add data and view it
        listnewsData.clear();

        if(cursor.moveToFirst())
            do{

                listnewsData.add(new AdapterAdmin(cursor.getString(0),cursor.getString(1),
                        cursor.getString(2),cursor.getString(3),cursor.getString(4),
                        cursor.getString(5),cursor.getString(6)));

            }while (cursor.moveToNext());

        myadapter=new MyCustomAdapter(listnewsData);
        listview1=(ListView)findViewById(R.id.listview1);
        listview1.setAdapter(myadapter);//intial with data
    }

    public void buAdd(View view) {
        ContentValues values = new ContentValues();
        values.put(DBManagerUser.ColID,textbook1.getText().toString());
        values.put(DBManagerUser.ColName,textbook2.getText().toString());
        values.put(DBManagerUser.ColUserName,textbook3.getText().toString());
        values.put(DBManagerUser.ColPassword,textbook4.getText().toString());
        values.put(DBManagerUser.ColPhone,textbook5.getText().toString());
        values.put(DBManagerUser.ColSalary,textbook6.getText().toString());
        values.put(DBManagerUser.ColType,textbook7.getText().toString());
        long id=dbManagerUser.insertAdmin(values);
        if(id>0)
            Toast.makeText(getApplicationContext(),"admin is added and book id is:"+id,Toast.LENGTH_LONG).show();
        else
            Toast.makeText(getApplicationContext(),"This ID or Username is already been added ",Toast.LENGTH_LONG).show();
        LoadAllElement();
    }

    public void buUpdate(View view) {
        ContentValues values = new ContentValues();
        values.put(DBManagerUser.ColID,textbook1.getText().toString());
        values.put(DBManagerUser.ColName,textbook2.getText().toString());
        values.put(DBManagerUser.ColUserName,textbook3.getText().toString());
        values.put(DBManagerUser.ColPassword,textbook4.getText().toString());
        values.put(DBManagerUser.ColPhone,textbook5.getText().toString());
        values.put(DBManagerUser.ColSalary,textbook6.getText().toString());
        values.put(DBManagerUser.ColType,textbook7.getText().toString());
        String[] SeletectionArgs={String.valueOf(textbook1.getText().toString())};
        dbManagerUser.UpdateAdmin(values,"ID=?",SeletectionArgs);
        LoadAllElement();
    }

    public void buSearch(View view) {
        tvSearch=findViewById(R.id.tvSearch);
        String[] SelectionArgs={"%"+tvSearch.getText().toString()+"%","%"+tvSearch.getText().toString()+"%","%"+tvSearch.getText().toString()+"%"};
        cursor=dbManagerUser.queryAdmin(null,"(ID like ?) OR (UserName like ?) OR (Name like ?)",SelectionArgs,null);
        listnewsData.clear();
        if(cursor.moveToFirst())
            do{

                listnewsData.add(new AdapterAdmin(cursor.getString(0),cursor.getString(1),
                        cursor.getString(2),cursor.getString(3),cursor.getString(4),
                        cursor.getString(5),cursor.getString(6)));

            }while (cursor.moveToNext());

        myadapter=new MyCustomAdapter(listnewsData);
        listview1=(ListView)findViewById(R.id.listview1);
        listview1.setAdapter(myadapter);//intial with data
    }

    public void buClear(View view) {
        LoadAllElement();
        textbook1.setText("");
        textbook2.setText("");
        textbook3.setText("");
        textbook4.setText("");
        textbook5.setText("");
        textbook6.setText("");
        textbook7.setText("");
    }

    public void buDelete(View view) {
        tvDelete=findViewById(R.id.tvDelete);
        buDelete=findViewById(R.id.deleteButton);
        String[] SeletectionArgs={tvDelete.getText().toString()};
        int count = dbManagerUser.DeleteAdmin("ID=?",SeletectionArgs);
        if(count > 0)
            LoadAllElement();

    }

    public void buClose(View view) {
        finish();
    }

    //display news list
    private class MyCustomAdapter extends BaseAdapter {
        public ArrayList<AdapterAdmin> listnewsDataAdpater;

        public MyCustomAdapter(ArrayList<AdapterAdmin> listnewsDataAdpater) {
            this.listnewsDataAdpater = listnewsDataAdpater;
        }

        @Override
        public int getCount() {
            return listnewsDataAdpater.size();
        }

        @Override
        public String getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater mInflater = getLayoutInflater();
            View myView = mInflater.inflate(R.layout.listviewforadmin, null);

            final AdapterAdmin s = listnewsDataAdpater.get(position);

            TextView ID = myView.findViewById(R.id.textView1);
            ID.setText(String.valueOf(s.ID));

            TextView Name = myView.findViewById(R.id.textView2);
            Name.setText(s.Name);

            TextView Username = myView.findViewById(R.id.textView3);
            Username.setText(s.Username);

            TextView Password = myView.findViewById(R.id.textView4);
            Password.setText(s.Password);

            TextView Phone = myView.findViewById(R.id.textView5);
            Phone.setText(String.valueOf(s.Phone));

            TextView Salary = myView.findViewById(R.id.textView6);
            Salary.setText(String.valueOf(s.Salary));

            TextView Type = myView.findViewById(R.id.textView7);
            Type.setText(s.Type);

            buSelect = myView.findViewById(R.id.tvSelect);
            buSelect.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    textbook1.setText(String.valueOf(s.ID));
                    textbook2.setText(String.valueOf(s.Name));
                    textbook3.setText(String.valueOf(s.Username));
                    textbook4.setText(String.valueOf(s.Password));
                    textbook5.setText(String.valueOf(s.Phone));
                    textbook6.setText(String.valueOf(s.Salary));
                    textbook7.setText(String.valueOf(s.Type));

                }
            });
            return myView;

        }
    }
}