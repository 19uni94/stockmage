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
	// �]�w�T�ձb���K�X
	for (int i = 0; i < account.length; i++) { // �Τ@�Ӱj���b����K�X�q�}�C���X
	if (edt1.getText().toString().equals(account[i])
	&& edt2.getText().toString().equals(password[i])) {
	Toast.makeText(aa.this, "�n�J���\", Toast.LENGTH_SHORT).show();
			
			
			Intent inten = new Intent();
			inten.setClass(aa.this, MainActivity.class);
			aa.this.startActivity(inten);
			break;//�@�w�nbreak,�_�h�|�L�k�P�_�ĤG��
	} else {
	tv.setText("��J���~�A�Э��s��J");
	}
	}
	}
	});
    }


   
}
	
