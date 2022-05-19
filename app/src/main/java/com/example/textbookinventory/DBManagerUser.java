package com.example.textbookinventory;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.widget.Toast;

public class DBManagerUser {
    private SQLiteDatabase sqlDB;

    static final String DBName ="Text_Book_Inventory";
    static final String TableNameAdmin ="Admin";
    static final String TableNameSeller ="Seller";
    static final String ColID ="ID";
    static final String ColName ="Name";
    static final String ColUserName ="UserName";
    static final String ColPassword ="Password";
    static final String ColPhone ="Phone";
    static final String ColSalary ="Salary";
    static final String ColType ="Type";
    // if i wanted to change something in the database i made versions so i know what version im using right now

    static final int DBVersion =1;

    static final String TableNameBook ="Book";
    static final String ColBookCode ="BookCode";
    static final String ColBookName ="BookName";
    static final String ColCategory ="Category";
    static final String ColPrice ="Price";
    static final String ColPublishDate ="PublishDate";
    static final String ColAuthorName ="AuthorName";
    static final String ColPublisher ="Publisher";
    static final String ColInStore ="InStore";
    static final String ColInStock ="InStock";


    static final String CreateTableAdmin="Create table IF NOT EXISTS "+TableNameAdmin+
            "("+ColID+" integer PRIMARY KEY,"+
            ColName+" text,"+
            ColUserName+" text NOT NULL UNIQUE,"+
            ColPassword+" text,"+
            ColPhone+" integer,"+
            ColSalary+" integer,"+
            ColType+" text);";

    static final String CreateTableSeller="Create table IF NOT EXISTS "+TableNameSeller+
            "("+ColID+" integer PRIMARY KEY,"+
            ColName+" text,"+
            ColUserName+" text NOT NULL UNIQUE,"+
            ColPassword+" text,"+
            ColPhone+" integer,"+
            ColSalary+" integer,"+
            ColType+" text);";

    static final String CreateTableBook="Create table IF NOT EXISTS "+TableNameBook+
            "("+ColBookCode+" integer PRIMARY KEY,"+
            ColBookName+" text,"+
            ColCategory+" text,"+
            ColPrice+" integer,"+
            ColPublishDate+" text,"+
            ColAuthorName+" text,"+
            ColPublisher+" text,"+
            ColInStore+" integer,"+
            ColInStock+" integer);";



    static class DataBaseHelperUser extends SQLiteOpenHelper {
        //this checks if the database does exists or not if there was no table created
        // it will use onCreate method but if the table was created before but someone updated the version
        //it will use the onUpgrade method to drop the table and create a new one
        Context context;

        DataBaseHelperUser(Context context){
            super(context,DBName,null,DBVersion);
            this.context=context;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CreateTableBook);
            Toast.makeText(context,"Book Table is created",Toast.LENGTH_LONG).show();
            db.execSQL(CreateTableAdmin);
            Toast.makeText(context,"Admin is created",Toast.LENGTH_LONG).show();
            db.execSQL(CreateTableSeller);
            Toast.makeText(context,"User is created",Toast.LENGTH_LONG).show();
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("drop table IF EXISTS "+ TableNameBook);
            db.execSQL("drop table IF EXISTS "+ TableNameAdmin);
            db.execSQL("drop table IF EXISTS "+ TableNameSeller);
            onCreate(db);
        }
    }

    public DBManagerUser(Context context){
        DataBaseHelperUser dbadmin= new DataBaseHelperUser(context);
        sqlDB=dbadmin.getWritableDatabase();

    }

    public long insertBook(ContentValues values){
        long id = sqlDB.insert(TableNameBook,"",values);
        return id;
    }

    public long insertAdmin(ContentValues values){
        long id = sqlDB.insert(TableNameAdmin,"",values);
        return id;
    }

    public long insertSeller(ContentValues values){
        long id = sqlDB.insert(TableNameSeller,"",values);
        return id;
    }

    public Cursor queryBook(String[] Projection, String Selection, String[] SelectionArgs, String Order){

        SQLiteQueryBuilder qb=new SQLiteQueryBuilder();
        qb.setTables(TableNameBook);
        Cursor cursor=qb.query(sqlDB,Projection,Selection,SelectionArgs,null,null,Order);
        return cursor;
    }

    public Cursor queryAdmin(String[] Projection, String Selection, String[] SelectionArgs, String Order){

        SQLiteQueryBuilder qb=new SQLiteQueryBuilder();
        qb.setTables(TableNameAdmin);
        Cursor cursor=qb.query(sqlDB,Projection,Selection,SelectionArgs,null,null,Order);
        return cursor;
    }

    public Cursor querySeller(String[] Projection, String Selection, String[] SelectionArgs, String Order){

        SQLiteQueryBuilder qb=new SQLiteQueryBuilder();
        qb.setTables(TableNameSeller);
        Cursor cursor=qb.query(sqlDB,Projection,Selection,SelectionArgs,null,null,Order);
        return cursor;
    }

    public int DeleteBook(String Selection,String[] SelectionArgs){
        int count=sqlDB.delete(TableNameBook,Selection,SelectionArgs);
        return count;
    }

    public int DeleteAdmin(String Selection,String[] SelectionArgs){
        int count=sqlDB.delete(TableNameAdmin,Selection,SelectionArgs);
        return count;
    }

    public int DeleteSeller(String Selection,String[] SelectionArgs){
        int count=sqlDB.delete(TableNameSeller,Selection,SelectionArgs);
        return count;
    }

    public int UpdateBook(ContentValues values,String Selection,String[] SelectionArgs){
        int count=sqlDB.update(TableNameBook,values,Selection,SelectionArgs);
        return count;
    }

    public int UpdateAdmin(ContentValues values,String Selection,String[] SelectionArgs){
        int count=sqlDB.update(TableNameAdmin,values,Selection,SelectionArgs);
        return count;
    }

    public int UpdateSeller(ContentValues values,String Selection,String[] SelectionArgs){
        int count=sqlDB.update(TableNameSeller,values,Selection,SelectionArgs);
        return count;
    }

}
