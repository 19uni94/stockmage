package com.bottle.stockmanage;


import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;



import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;


import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;



public class MainActivity extends Activity {
	
	private Button btn1;
	private Button btn2;
	private Button btn3;
	private Button btn4;
	private Button btn5;	
	private Button btn7;
	private Button btn8;
	private Button btn;
	private Button btn10;
	private EditText edt1;
	private EditText edt2;
	private TextView tv;
	
	private ListView listView;		
	private SimpleAdapter adapter;
	private DBUtil dbUtil;
	private Class Zxing;
             
	@Override

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		btn1=(Button) findViewById(R.id.btn_all);
		btn2=(Button) findViewById(R.id.btn_add);
		btn3=(Button) findViewById(R.id.btn_delete);
		btn4=(Button) findViewById(R.id.btn_select);
		btn5=(Button) findViewById(R.id.btn_qr);		
		btn7=(Button) findViewById(R.id.btn_upd);
		btn10=(Button)findViewById(R.id.btn_stop);
		
		
		listView = (ListView) findViewById(R.id.listView);
	
		
		dbUtil = new DBUtil();
		
		 
		
		btn1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				hideButton(true);
				setListView();
			}
		});

		btn2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {                                                
				hideButton(true);
				setAddDialog();
				
			}
		});

		btn3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				hideButton(true);
				setDeleteDialog();
			}
		});
	
	btn4.setOnClickListener(new OnClickListener (){
		
		@Override
		public void onClick(View v) {
			hideButton(true);
			//setgetnumDialog();
		  //  getnum();
			setgetnum();
		    
		    
		}

	});
	btn5.setOnClickListener(new OnClickListener (){
		
		
		@Override		
		public void onClick(View v) {
			
			hideButton(true);
			
			
			Intent inten = new Intent();
			inten.setClass(MainActivity.this, Zxing.class);
			MainActivity.this.startActivity(inten);
			//MainActivity.this.finish();			
		}	
	 });
	
	
	btn7.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				 setupdDialog();
				 hideButton(true);
			}
		});
	btn10.setOnClickListener(new OnClickListener(){
		
		@Override
		public void onClick(View v){
			
			Intent inten = new Intent();
			inten.setClass(MainActivity.this, aa.class);
			MainActivity.this.startActivity(inten);
		}
	});	

}	
		
	
	private void setDeleteDialog() {
		
		final Dialog dialog = new Dialog(MainActivity.this);
		dialog.setContentView(R.layout.dialog_delete);
		dialog.setTitle("輸入想要刪除的商品編號");
		Window dialogWindow = dialog.getWindow();
		WindowManager.LayoutParams lp = dialogWindow.getAttributes();
		dialogWindow.setGravity(Gravity.CENTER);
		dialogWindow.setAttributes(lp);

		final EditText cNoEditText = (EditText) dialog.findViewById(R.id.editText1);
		Button btnConfirm = (Button) dialog.findViewById(R.id.btn_select);
		Button btnCancel = (Button) dialog.findViewById(R.id.button2);

		btnConfirm.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
			dbUtil.delGood(cNoEditText.getText().toString());
				dialog.dismiss();
				hideButton(false);
				Toast.makeText(MainActivity.this, "成功刪除數據", Toast.LENGTH_SHORT).show();
			
			}
		});

		btnCancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				dialog.dismiss();
				hideButton(false);
				Toast.makeText(MainActivity.this, "取消", Toast.LENGTH_SHORT).show();
				
			}
		});
		
		dialog.show();
	}
	
	private void setAddDialog() {

		final Dialog dialog = new Dialog(MainActivity.this);
		dialog.setContentView(R.layout.dialog_add);
		dialog.setTitle("輸入新增的商品名稱及數量");
		Window dialogWindow = dialog.getWindow();
		WindowManager.LayoutParams lp = dialogWindow.getAttributes();
		dialogWindow.setGravity(Gravity.CENTER);
		dialogWindow.setAttributes(lp);

		final EditText cNameEditText = (EditText) dialog.findViewById(R.id.editText1);
		final EditText cNumEditText = (EditText) dialog.findViewById(R.id.editText2);
		Button btnConfirm = (Button) dialog.findViewById(R.id.btn_select);
		Button btnCancel = (Button) dialog.findViewById(R.id.button2);

		btnConfirm.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				dbUtil.addedGood(cNameEditText.getText().toString(), cNumEditText.getText().toString());
				dialog.dismiss();
				hideButton(false);
			if(cNameEditText.equals("Cname")) {
				 Log.d("debug", "button click");
				Toast.makeText(MainActivity.this, "已有此商品", Toast.LENGTH_SHORT).show();
			
			}else{
				Toast.makeText(MainActivity.this, "成功添加商品數據", Toast.LENGTH_SHORT).show();
			}			
			}
		});

		btnCancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				dialog.dismiss();
				hideButton(false);
				Toast.makeText(MainActivity.this, "取消", Toast.LENGTH_SHORT).show();
			}
		});
		dialog.show();
	}
	

	
	private void setupdDialog() {

		final Dialog dialog = new Dialog(MainActivity.this);
		dialog.setContentView(R.layout.dialog_upd);
		dialog.setTitle("輸入更改的商品名稱及編號");
		Window dialogWindow = dialog.getWindow();
		WindowManager.LayoutParams lp = dialogWindow.getAttributes();
		dialogWindow.setGravity(Gravity.CENTER);
		dialogWindow.setAttributes(lp);

	
		final EditText cNameEditText = (EditText) dialog.findViewById(R.id.editText1);
		final EditText cNumEditText = (EditText) dialog.findViewById(R.id.editText2);
		Button btnConfirm = (Button) dialog.findViewById(R.id.btn_select);
		Button btnCancel = (Button) dialog.findViewById(R.id.button2);

		btnConfirm.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				dbUtil.updGood(cNameEditText.getText().toString(), cNumEditText.getText().toString());
				dialog.dismiss();
				hideButton(false);
				Toast.makeText(MainActivity.this, "成功修改數據", Toast.LENGTH_SHORT).show();
			}			
		});

		btnCancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				dialog.dismiss();
				hideButton(false);
				Toast.makeText(MainActivity.this, "取消", Toast.LENGTH_SHORT).show();
			}
		});
		dialog.show();
	}
	
	
private void setgetnum() {
								
		final Dialog dialog = new Dialog(MainActivity.this);
		dialog.setContentView(R.layout.dialog_query);
		dialog.setTitle("查詢商品訊息");
		Window dialogWindow = dialog.getWindow();
		WindowManager.LayoutParams lp = dialogWindow.getAttributes();
		dialogWindow.setGravity(Gravity.CENTER);
		dialogWindow.setAttributes(lp);

		//final EditText cNumEditText = (EditText) dialog.findViewById(R.id.editText1);
		final EditText editText = (EditText)findViewById(R.id.editText1);
		Button btnConfirm = (Button) dialog.findViewById(R.id.btn_select);
		Button btnCancel = (Button) dialog.findViewById(R.id.button2);
		
	
		btnConfirm.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
																				
				dbUtil.query();				
				setContentView(R.layout.adapter_it);
				
				dialog.dismiss();
				hideButton(false);											
									  			
				setListView2();
				Toast.makeText(MainActivity.this, "查詢成功", Toast.LENGTH_SHORT).show();
				}
						
			
		});

		btnCancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				dialog.dismiss();
				hideButton(false);
				Toast.makeText(MainActivity.this, "取消", Toast.LENGTH_SHORT).show();
			}
		});
		dialog.show();
	}
	
		

			
	private void setListView() {
       
				
		listView.setVisibility(View.VISIBLE);

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		list = dbUtil.getAllInfo();

		adapter = new SimpleAdapter(
				MainActivity.this, 
				list, 
				R.layout.adapter_item, 
				new String[] { "Cno", "Cname", "Cnum" }, 
				new int[] { R.id.txt_Cno, R.id.txt_Cname, R.id.txt_Cnum });

		listView.setAdapter(adapter);		
		
		}
	
	private void setListView2() {
	       
		
		listView.setVisibility(View.VISIBLE);

		List<HashMap<String, String>> list2 = new ArrayList<HashMap<String, String>>();

		
		
		list2 = dbUtil.query();
		
		
		adapter = new SimpleAdapter(
				MainActivity.this, 
				list2, 
				R.layout.adapter_it,				
				new String[] { "Cname", "Cnum" }, 
				new int[] {  R.id.txt_Cname, R.id.txt_Cnum });

		listView.setAdapter(adapter);		
		
		}
		
				
	
	private void hideButton(boolean result) {
				if (result) {
					btn1.setVisibility(View.GONE);
					btn2.setVisibility(View.GONE);
					btn3.setVisibility(View.GONE);
					btn4.setVisibility(View.GONE);
					btn5.setVisibility(View.GONE);					
					btn7.setVisibility(View.GONE);
					btn10.setVisibility(View.GONE);
				
				} else {
					btn1.setVisibility(View.VISIBLE);
					btn2.setVisibility(View.VISIBLE);
					btn3.setVisibility(View.VISIBLE);
					btn4.setVisibility(View.VISIBLE);
					btn5.setVisibility(View.VISIBLE);
					btn7.setVisibility(View.VISIBLE);
					btn10.setVisibility(View.VISIBLE);
					
				}
		
			}
		
			
			@Override
			public void onBackPressed()
			{
				if (listView.getVisibility() == View.VISIBLE) {																								
					listView.setVisibility(View.GONE);
					hideButton(false);
					
				}else {
					MainActivity.this.finish();
				
				}
			
			}
			
			}

