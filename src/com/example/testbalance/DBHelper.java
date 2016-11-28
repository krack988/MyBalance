package com.example.testbalance;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper{
	
	public DBHelper(Context context){
	super(context,"myDB",null,1);
	}
	@Override
	public void onCreate(SQLiteDatabase db){
		db.execSQL("create table mytable ("
		          + "id integer primary key autoincrement,"
		          + "debet integer,"
		          + "kredit integer" + ");");
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
		
	}

}
