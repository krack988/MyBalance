package com.example.testbalance;

import java.util.ArrayList;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	Button btnAdd,btnView;
	EditText textDeb,textKred;
	DBHelper dbHelper;
	ArrayList<Integer> aList=new ArrayList<Integer>();
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		btnAdd=(Button) findViewById(R.id.btn);
		btnView=(Button) findViewById(R.id.btnStat);
		textDeb=(EditText) findViewById(R.id.editDeb);
		textKred=(EditText) findViewById(R.id.editKred);
		
		dbHelper=new DBHelper(this);
		
	}
	
	public void onClick(View v){
		
		ContentValues content=new ContentValues();
		String debet=textDeb.getText().toString();
		int kr=Integer.parseInt(textKred.getText().toString())*-1;
		String kredit=String.valueOf(kr);
		
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		
		switch(v.getId()){
			case R.id.btn:
				content.put("debet", debet);
				content.put("kredit", kredit);
				long rowId=db.insert("mytable", null, content);
				Toast.makeText(this, String.valueOf(rowId), Toast.LENGTH_SHORT).show();
				//
				break;
			case R.id.btnStat:
				Cursor c=db.query("mytable", null, null, null, null, null, null);
				
				if(c.moveToFirst()){
					int indexId=c.getColumnIndex("id");
					int indexDeb=c.getColumnIndex("debet");
					int indexKred=c.getColumnIndex("kredit");
					do{
						aList.add(c.getInt(indexDeb));
						aList.add(c.getInt(indexKred));
					}while (c.moveToNext());
				}else{
					Toast.makeText(this, "0 rows", Toast.LENGTH_SHORT).show();
					c.close();
				}
				
				
				Intent intent=new Intent(this,BalanceActivity.class);
				intent.putIntegerArrayListExtra("aList", aList);
				startActivity(intent);
				
				break;
		}
	}
}
