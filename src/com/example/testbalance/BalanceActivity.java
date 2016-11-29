package com.example.testbalance;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class BalanceActivity extends Activity {
	
	ListView list;
	TextView result;
	ArrayList<Integer> listStat=new ArrayList<Integer>();;
	ArrayAdapter<Integer> adapter;
	Intent intent;
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_balance);
		int balance=0;
		result=(TextView) findViewById(R.id.textBalance);
		list=(ListView) findViewById(R.id.list);
		intent=getIntent();
		
		listStat=intent.getIntegerArrayListExtra("aList");
		
		adapter=new ArrayAdapter<Integer>(this,R.layout.my_item,listStat);
		list.setAdapter(adapter);
		
		for (int i : listStat) {
			balance+=i;
		}
		result.setText("Ваш баланс: "+String.valueOf(balance));
		Toast.makeText(this,"Ваш баланс: "+ String.valueOf(balance) , Toast.LENGTH_SHORT).show();
	}
	
	@Override
	protected void onStop(){
		super.onStop();
		listStat.clear();
		
	}
}
