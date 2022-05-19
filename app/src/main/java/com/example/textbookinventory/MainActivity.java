package com.example.textbookinventory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<AdapterBook> listnewsData = new ArrayList<AdapterBook>();
    MyCustomAdapter myadapter;

    Intent intent;
    DBManagerUser dbManagerUser;
    Cursor cursor;
    EditText textbook1;
    EditText textbook2;
    EditText textbook3;
    EditText textbook4;
    EditText textbook5;
    EditText textbook6;
    EditText textbook7;
    EditText textbook8;
    EditText textbook9;
    EditText tvSearch;
    EditText tvDelete;
    ListView listview1;
    Button buDelete;
    Button buUpdate;
    ListView listviewHomePage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        dbManagerUser = new DBManagerUser( this);
        LoadAllElement();
        tvSearch = findViewById(R.id.tvSearch);


    }

    void LoadAllElement() {
        cursor = dbManagerUser.queryBook(null, null, null, null);
        //add data and view it
        listnewsData.clear();

        if (cursor.moveToFirst())
            do {

                listnewsData.add(new AdapterBook(cursor.getString(0), cursor.getString(1),
                        cursor.getString(2), cursor.getString(3), cursor.getString(4),
                        cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8)));

            } while (cursor.moveToNext());

        myadapter = new MyCustomAdapter(listnewsData);
        listviewHomePage = (ListView) findViewById(R.id.listviewHomePage);
        listviewHomePage.setAdapter(myadapter);//intial with data
    }

    public void buSearchBook(View view) {
        tvSearch = findViewById(R.id.tvSearch);
        String[] SelectionArgs = {"%" + tvSearch.getText().toString() + "%", "%" + tvSearch.getText().toString() + "%"};
        cursor = dbManagerUser.queryBook(null, "(BookName like ?) OR (BookCode like ?)", SelectionArgs, null);
        listnewsData.clear();
        if (cursor.moveToFirst())
            do {

                listnewsData.add(new AdapterBook(cursor.getString(0), cursor.getString(1),
                        cursor.getString(2), cursor.getString(3), cursor.getString(4),
                        cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8)));

            } while (cursor.moveToNext());

        myadapter = new MyCustomAdapter(listnewsData);
        listviewHomePage = (ListView) findViewById(R.id.listviewHomePage);
        listviewHomePage.setAdapter(myadapter);//intial with data
    }

    public void buLogin(View view) {
        intent= new Intent(this,Login.class);
        startActivity(intent);
    }

    private class MyCustomAdapter extends BaseAdapter {
        public ArrayList<AdapterBook> listnewsDataAdpater;

        public MyCustomAdapter(ArrayList<AdapterBook> listnewsDataAdpater) {
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
            View myView = mInflater.inflate(R.layout.listview_book, null);

            final AdapterBook s = listnewsDataAdpater.get(position);

            TextView BookCode = myView.findViewById(R.id.textView1);
            BookCode.setText(String.valueOf(s.BookCode));

            TextView BookName = myView.findViewById(R.id.textView2);
            BookName.setText(s.BookName);

            TextView Category = myView.findViewById(R.id.textView3);
            Category.setText(s.Category);

            TextView Price = myView.findViewById(R.id.textView4);
            Price.setText(String.valueOf(s.Price));

            TextView PublishDate = myView.findViewById(R.id.textView5);
            PublishDate.setText(s.PublishDate);

            TextView AuthorName = myView.findViewById(R.id.textView6);
            AuthorName.setText(s.AuthorName);

            TextView Publisher = myView.findViewById(R.id.textView7);
            Publisher.setText(s.Publisher);

            TextView inStore = myView.findViewById(R.id.textView8);
            inStore.setText(String.valueOf(s.inStore));

            TextView inStock = myView.findViewById(R.id.textView9);
            inStock.setText(String.valueOf(s.inStock));

//            buUpdate = myView.findViewById(R.id.tvSelect);
//            buUpdate.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    textbook1.setText(String.valueOf(s.BookCode));
//                    textbook2.setText(String.valueOf(s.BookName));
//                    textbook3.setText(String.valueOf(s.Category));
//                    textbook4.setText(String.valueOf(s.Price));
//                    textbook5.setText(String.valueOf(s.PublishDate));
//                    textbook6.setText(String.valueOf(s.AuthorName));
//                    textbook7.setText(String.valueOf(s.Publisher));
//                    textbook8.setText(String.valueOf(s.inStore));
//                    textbook9.setText(String.valueOf(s.inStock));
//                }
//            });
            return myView;

        }
    }
}