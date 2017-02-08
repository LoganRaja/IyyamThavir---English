package com.compunetconnections.iyyamthavir;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by connectors on 1/7/2016.
 */
public class SqlliteDBIyyam extends SQLiteOpenHelper {
    public SqlliteDBIyyam(Context context)
    {
        super(context, "termsDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL("create table terms " + "(id integer primary key AUTOINCREMENT,agree text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS termsDB");
        onCreate(db);
    }
    public void insert(){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("agree", "true");
        db.insert("terms", null, contentValues);
        select();
    }
    public Cursor select(){
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM terms",null);
        return cursor;
    }
    public void updateLanguage(String languageOLD,String languageNEW){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("language",languageNEW);
        db.update("location", contentValues, "language = ?", new String[]{languageOLD});
        select();
    }
}
