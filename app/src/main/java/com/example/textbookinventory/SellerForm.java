package com.example.textbookinventory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SellerForm extends AppCompatActivity {
    ArrayList<AdapterBook> listnewsData = new ArrayList<AdapterBook>();
    MyCustomAdapter myadapter;

    DBManagerUser dbManagerUser;
    Cursor cursor;

    EditText tvsearch;
    EditText tvdelete;
    ListView listview2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_form);
        dbManagerUser = new DBManagerUser(this);
        myadapter=new MyCustomAdapter(listnewsData);
        listview2=(ListView)findViewById(R.id.listview2);
        listview2.setAdapter(myadapter);
        listview2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listnewsData.remove(id);
                myadapter.notifyDataSetChanged();
                listnewsData.clear();


            }
        });
    }

    public void buSearch(View view) {
        tvsearch=findViewById(R.id.tvsearch);
        String[] SelectionArgs={"%"+tvsearch.getText().toString()+"%","%"+tvsearch.getText().toString()+"%"};
        cursor=dbManagerUser.queryBook(null,"(BookName like ?) OR (BookCode like ?)",SelectionArgs,null);

        if(cursor.moveToFirst())
            do{
                if(cursor.getString(0).equals(tvsearch.getText().toString())||cursor.getString(1).equals(tvsearch.getText().toString())) {
                    Toast.makeText(this, "this book is available", Toast.LENGTH_SHORT).show();
                }else
                    Toast.makeText(this, "this book is not available", Toast.LENGTH_SHORT).show();
            }while (cursor.moveToNext());
    }

    public void buAdd(View view) {
        tvsearch=findViewById(R.id.tvsearch);
        String[] SelectionArgs={"%"+tvsearch.getText().toString()+"%","%"+tvsearch.getText().toString()+"%"};
        cursor=dbManagerUser.queryBook(null,"(BookName like ?) OR (BookCode like ?)",SelectionArgs,null);

        if(cursor.moveToFirst())
            do{
                if(cursor.getString(0).equals(tvsearch.getText().toString())||cursor.getString(1).equals(tvsearch.getText().toString()))
                listnewsData.add(new AdapterBook(cursor.getString(0),cursor.getString(1),cursor.getString(2)
                ,cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6)));
                else
                    Toast.makeText(this, "this book is not found", Toast.LENGTH_SHORT).show();
            }while (cursor.moveToNext());
        myadapter=new MyCustomAdapter(listnewsData);
        listview2=(ListView)findViewById(R.id.listview2);
        listview2.setAdapter(myadapter);
    }

    public void buLogout(View view) {
        finish();
    }

    public void buVProfile(View view) {
        Intent intent=new Intent(this,ViewProfile.class);
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
            View myView = mInflater.inflate(R.layout.listviewforseller, null);

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


            return myView;
        }
    }
}