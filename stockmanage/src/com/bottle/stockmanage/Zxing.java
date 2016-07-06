package com.bottle.stockmanage;


import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;

import android.app.Activity; 

import android.content.Intent; 
import android.os.Bundle; 
import android.util.Log; 
import android.view.View; 
import android.widget.Button; 
import android.widget.TextView; 
 
public class Zxing extends Activity { 
    private TextView text1; 
    private Button button1; 
    private Button bk1;
 
    @Override 
    public void onCreate(Bundle savedInstanceState) { 
        super.onCreate(savedInstanceState); 
        setContentView(R.layout.qr_code); 
        
        bk1=(Button)findViewById(R.id.bk1);
       
        bk1.setOnClickListener(new View.OnClickListener() {
        	@Override
        	public void onClick(View v) {
        	
        		Intent inten = new Intent();
    			inten.setClass(Zxing.this, MainActivity.class);
    			Zxing.this.startActivity(inten);       	
        }
        });
 
        try { 
 
            text1 = (TextView) findViewById(R.id.textView1); 
            button1 = (Button) findViewById(R.id.btn_enter); 
            button1.setOnClickListener(startClickListener); 
 
        } catch (Exception e) { 
            e.printStackTrace(); 
            Log.e("Mo", e.toString()); 
        } 
    } 
 
    private Button.OnClickListener startClickListener = new Button.OnClickListener() { 
        public void onClick(View arg0) { 
            // 連結ZXING的API 
            Intent intent = new Intent("com.google.zxing.client.android.SCAN"); // 開啟條碼掃描器 
            intent.putExtra("SCAN_MODE", "QR_CODE_MODE"); // 設定QR Code參數 
            startActivityForResult(intent, 1); // 要求回傳1 
        } 
    }; 
 
    public void onActivityResult(int requestCode, int resultCode, Intent intent) { 
    	
    	 super.onActivityResult(requestCode, resultCode, intent);
    	
        String rs = "requestCode = " + requestCode + " \n"; 
        rs += "resultCode = " + resultCode + " \n"; 
        rs += "contents = " + intent.getStringExtra("SCAN_RESULT") + " \n"; 
        text1.setText(rs); 
 
        if (requestCode == 1) { 
            if (resultCode == RESULT_OK) { 
                // Handle ok 
 
            } else if (resultCode == RESULT_CANCELED) { 
                // Handle cancel 
            } 
        } 
    } 
 
	
} 
