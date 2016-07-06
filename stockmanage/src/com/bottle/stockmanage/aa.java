package com.bottle.stockmanage;






import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class aa extends Activity{
	private Button btn_enterr;
	private EditText edt1;
	private EditText edt2;
	private TextView tv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.jj);
	
	btn_enterr = (Button) this.findViewById(R.id.b1);
	edt1 = (EditText) this.findViewById(R.id.editText1);
	edt2 = (EditText) this.findViewById(R.id.editText2);
	tv = (TextView) this.findViewById(R.id.tv2);
	
	btn_enterr.setOnClickListener(new View.OnClickListener() {
	@Override
	public void onClick(View v) {
	String[] account = { "Tom", "Aney", "Bob" };
	String[] password = { "111", "222", "333" };
	// 設定三組帳號密碼
	for (int i = 0; i < account.length; i++) { // 用一個迴圈把帳號跟密碼從陣列取出
	if (edt1.getText().toString().equals(account[i])
	&& edt2.getText().toString().equals(password[i])) {
	Toast.makeText(aa.this, "登入成功", Toast.LENGTH_SHORT).show();
			
			
			Intent inten = new Intent();
			inten.setClass(aa.this, MainActivity.class);
			aa.this.startActivity(inten);
			break;//一定要break,否則會無法判斷第二組
	} else {
	tv.setText("輸入錯誤，請重新輸入");
	}
	}
	}
	});
    }


   
}
	
