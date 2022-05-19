package com.example.textbookinventory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
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
import android.widget.Toast;

import java.util.ArrayList;

public class AddBook extends AppCompatActivity {
    //adapter class
    ArrayList<AdapterBook> listnewsData = new ArrayList<AdapterBook>();
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
    EditText textbook8;
    EditText textbook9;
    EditText tvSearch;
    EditText tvDelete;
    ListView listview1;
    Button buDelete;
    Button buUpdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        dbManagerUser = new DBManagerUser(this);
        LoadAllElement();
        textbook1 = findViewById(R.id.textbook1);
        textbook2 = findViewById(R.id.textbook2);
        textbook3 = findViewById(R.id.textbook3);
        textbook4 = findViewById(R.id.textbook4);
        textbook5 = findViewById(R.id.textbook5);
        textbook6 = findViewById(R.id.textbook6);
        textbook7 = findViewById(R.id.textbook7);
        textbook8 = findViewById(R.id.textbook8);
        textbook9 = findViewById(R.id.textbook9);

    }

    void LoadAllElement(){
        cursor=dbManagerUser.queryBook(null,null,null,null);
        //add data and view it
        listnewsData.clear();

        if(cursor.moveToFirst())
            do{

                listnewsData.add(new AdapterBook(cursor.getString(0),cursor.getString(1),
                        cursor.getString(2),cursor.getString(3),cursor.getString(4),
                        cursor.getString(5),cursor.getString(6),cursor.getString(7),cursor.getString(8)));

            }while (cursor.moveToNext());

        myadapter=new MyCustomAdapter(listnewsData);
        listview1=(ListView)findViewById(R.id.listview1);
        listview1.setAdapter(myadapter);//intial with data
    }

    public void buAdd(View view) {


        ContentValues values = new ContentValues();
        values.put(DBManagerUser.ColBookCode,textbook1.getText().toString());
        values.put(DBManagerUser.ColBookName,textbook2.getText().toString());
        values.put(DBManagerUser.ColCategory,textbook3.getText().toString());
        values.put(DBManagerUser.ColPrice,textbook4.getText().toString());
        values.put(DBManagerUser.ColPublishDate,textbook5.getText().toString());
        values.put(DBManagerUser.ColAuthorName,textbook6.getText().toString());
        values.put(DBManagerUser.ColPublisher,textbook7.getText().toString());
        values.put(DBManagerUser.ColInStore,textbook8.getText().toString());
        values.put(DBManagerUser.ColInStock,textbook9.getText().toString());
        long id=dbManagerUser.insertBook(values);
        if(id>0)
            Toast.makeText(getApplicationContext(),"Data is added and book id is:"+id,Toast.LENGTH_LONG).show();
        else
            Toast.makeText(getApplicationContext(),"This BookCode has already been used",Toast.LENGTH_LONG).show();
        LoadAllElement();

    }

    public void buSearch(View view) {
        tvSearch=findViewById(R.id.tvSearch);
        String[] SelectionArgs={"%"+tvSearch.getText().toString()+"%","%"+tvSearch.getText().toString()+"%"};
        cursor=dbManagerUser.queryBook(null,"(BookName like ?) OR (BookCode like ?)",SelectionArgs,null);
        listnewsData.clear();
        if(cursor.moveToFirst())
            do{

                listnewsData.add(new AdapterBook(cursor.getString(0),cursor.getString(1),
                        cursor.getString(2),cursor.getString(3),cursor.getString(4),
                        cursor.getString(5),cursor.getString(6),cursor.getString(7),cursor.getString(8)));

            }while (cursor.moveToNext());

        myadapter=new MyCustomAdapter(listnewsData);
        listview1=(ListView)findViewById(R.id.listview1);
        listview1.setAdapter(myadapter);//intial with data
    }

    public void buDelete(View view) {
        tvDelete=findViewById(R.id.tvDelete);
        buDelete=findViewById(R.id.deleteButton);
        String[] SeletectionArgs={tvDelete.getText().toString()};
        int count = dbManagerUser.DeleteBook("BookCode=?",SeletectionArgs);
        if(count > 0)
            LoadAllElement();

    }

    public void buUpdate(View view) {
        ContentValues values = new ContentValues();
        values.put(DBManagerUser.ColBookCode,textbook1.getText().toString());
        values.put(DBManagerUser.ColBookName,textbook2.getText().toString());
        values.put(DBManagerUser.ColCategory,textbook3.getText().toString());
        values.put(DBManagerUser.ColPrice,textbook4.getText().toString());
        values.put(DBManagerUser.ColPublishDate,textbook5.getText().toString());
        values.put(DBManagerUser.ColAuthorName,textbook6.getText().toString());
        values.put(DBManagerUser.ColPublisher,textbook7.getText().toString());
        values.put(DBManagerUser.ColInStore,textbook8.getText().toString());
        values.put(DBManagerUser.ColInStock,textbook9.getText().toString());
        String[] SeletectionArgs={String.valueOf(textbook1.getText().toString())};
        dbManagerUser.UpdateBook(values,"BookCode=?",SeletectionArgs);
        LoadAllElement();
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
        textbook8.setText("");
        textbook9.setText("");

    }

    //display news list
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

            buUpdate=myView.findViewById(R.id.tvSelect);
            buUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    textbook1.setText(String.valueOf(s.BookCode));
                    textbook2.setText(String.valueOf(s.BookName));
                    textbook3.setText(String.valueOf(s.Category));
                    textbook4.setText(String.valueOf(s.Price));
                    textbook5.setText(String.valueOf(s.PublishDate));
                    textbook6.setText(String.valueOf(s.AuthorName));
                    textbook7.setText(String.valueOf(s.Publisher));
                    textbook8.setText(String.valueOf(s.inStore));
                    textbook9.setText(String.valueOf(s.inStock));
                }
            });
            return myView;

        }

    }
}
