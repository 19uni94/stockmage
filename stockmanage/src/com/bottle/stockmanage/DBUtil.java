package com.bottle.stockmanage;

import java.sql.Connection;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import android.app.Dialog;
import android.database.Cursor;
import android.provider.Contacts;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class DBUtil{
	private ArrayList<String> arrayList = new ArrayList<String>();
	private ArrayList<String> brrayList = new ArrayList<String>();
	private ArrayList<String> crrayList = new ArrayList<String>();
	private HttpConnSoap Soap = new HttpConnSoap();
	private EditText editText;
	

	public static Connection getConnection() {
		Connection con = null;
		try {
			
		} catch (Exception e) {
			
		}
		return con;
	}

	public List<HashMap<String, String>> getAllInfo() {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		arrayList.clear();
		brrayList.clear();
		crrayList.clear();

		crrayList = Soap.GetWebServre("selectGood", arrayList, brrayList);

		HashMap<String, String> tempHash = new HashMap<String, String>();
		tempHash.put("Cno", "Cno");
		tempHash.put("Cname", "Cname");
		tempHash.put("Cnum", "Cnum");
		list.add(tempHash);
		
		for (int j = 0; j < crrayList.size(); j += 3) {
			HashMap<String, String> hashMap = new HashMap<String, String>();
			hashMap.put("Cno", crrayList.get(j));
			hashMap.put("Cname", crrayList.get(j + 1));
			hashMap.put("Cnum", crrayList.get(j + 2));
			list.add(hashMap);
		}

		return list;
	}

	
	public List<HashMap<String, String>> query() {
		List<HashMap<String, String>> list2 = new ArrayList<HashMap<String, String>>();
			
		arrayList.clear();
		brrayList.clear();
		crrayList.clear();

		arrayList.add("Cno");    // 參數名稱
		//arrayList.add("Cno");
		brrayList.add("2");      // 要查詢的值
		
		//brrayList.add(editText.getText().toString());			
		crrayList = Soap.GetWebServre("getnum", arrayList, brrayList);
		
		
		HashMap<String, String> tempHash = new HashMap<String, String>();
		//tempHash.put("Cno", "Cno");
		tempHash.put("Cname", "Cname");
		tempHash.put("Cnum", "Cnum");
		list2.add(tempHash);
		
		
		
		
		
		
		
		
		
		
		
		
		

		
		for (int j =1; j < crrayList.size(); j += 2) {
			HashMap<String, String> hashMap = new HashMap<String, String>();
			//hashMap.put("Cno", crrayList.get(j));
			hashMap.put("Cname", crrayList.get(j));
			hashMap.put("Cnum", crrayList.get(j +1));
			list2.add(hashMap);
		}
		return list2;
	}

	
			
	

	public void addedGood(String Cname, String Cnum) {

		arrayList.clear();
		brrayList.clear();
		
		arrayList.add("Cname");
		arrayList.add("Cnum");
		brrayList.add(Cname);
		brrayList.add(Cnum);
		
		Soap.GetWebServre("addedGood", arrayList, brrayList);
	}
	
	
	public void delGood(String Cno) {

		arrayList.clear();
		brrayList.clear();
		
		arrayList.add("Cno");
		brrayList.add(Cno);
		
		Soap.GetWebServre("delGood", arrayList, brrayList);		
	}
	
	public void updGood(String Cname, String Cnum) {

		arrayList.clear();
		brrayList.clear();
		
		arrayList.add("Cname");
		arrayList.add("Cnum");
		brrayList.add(Cname);
		brrayList.add(Cnum);
		
		crrayList = Soap.GetWebServre("updGood", arrayList, brrayList);
		
	}
	
	}


