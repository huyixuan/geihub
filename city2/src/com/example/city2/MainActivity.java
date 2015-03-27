package com.example.city2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity{
	public Button btn1;
	public Button btn2;
	public Button btn3;
	//保存省份的list集合
	public List<Address> listPro;
	//保存市区的map集合
	public Map<String, List<Address>> mapCity;
	//保存县的map集合
	public Map<String, List<Address>> mapRegion;
	//处理后的要显示的市区集合
	public List<Address> showListCity;
	//处理后的要显示县区的集合
	public List<Address> showListRegion;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		listPro=new ArrayList<Address>();
		mapCity=new HashMap<String, List<Address>>();
		mapRegion=new HashMap<String, List<Address>>();
		setContentView(R.layout.activity_main);
		btn1=(Button)findViewById(R.id.province);
		btn2=(Button)findViewById(R.id.city);
		btn3=(Button)findViewById(R.id.region);
		readTxt();
		readCityTxt();
		readRegionTxt();
		//三个按钮事件
		btn1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				//这里处理一下防止null
				if(listPro ==null){
					return;
				}
				show_select_address("请选择省份",listPro, MainActivity.this, new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int arg1) {
						Address address=listPro.get(arg1);
						String province=address.getName();
						String pk=address.getPk();
						System.out.println("选择的省份是="+province+"，pk="+pk);
						List<Address> cityList=mapCity.get(pk);
						showListCity=cityList;
						dialog.cancel();
					}
				} );
			}
		});
		btn2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if(showListCity == null){
					return;
				}
                show_select_address("请选择市区",showListCity, MainActivity.this, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int arg1) {
						Address address=showListCity.get(arg1);
						String province=address.getName();
						String pk=address.getPk();
						System.out.println("选择的市区是="+province+"，pk="+pk);
						List<Address> regionList=mapRegion.get(pk);
						showListRegion=regionList;
						dialog.cancel();
					}
				} );
			}
		});
		btn3.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if(showListRegion == null){
					return;
				}
				show_select_address("请选择地区",showListRegion, MainActivity.this, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int arg1) {
						Address address=showListRegion.get(arg1);
						String province=address.getName();
						String pk=address.getPk();
						System.out.println("选择的区号是="+province+"，pk="+pk);
						dialog.cancel();
					}
				} );
			}
		});
	}
	//显示address列表
	public void show_select_address(String title,List<Address> list,Activity activity,DialogInterface.OnClickListener post){
		final String[] items=new String[list.size()];
		for (int i = 0; i <list.size(); i++) {
			Address address=list.get(i);
			items[i]=address.getName();
		}
		AlertDialog.Builder builder=new AlertDialog.Builder(activity);
		    builder.setTitle(title).setIcon(android.R.drawable.ic_lock_lock).setSingleChoiceItems(items,-1,  post).create().show();
	}
	//读取省txt文件
	public void readTxt(){
		BufferedReader bufferRead=null;
		try {
			String str=null;
			//获得项目assets文件夹下的文件
			InputStream in = getResources().getAssets().open("province.txt");
			InputStreamReader reader = new InputStreamReader(in,"GBK");
			bufferRead=new BufferedReader(reader);
			str=bufferRead.readLine();
			while (str != null) {
				String[] pro=str.split(",");
				Address address=new Address();
				address.setPk(pro[0]);
				address.setName(pro[1]);
				listPro.add(address);
				str=bufferRead.readLine();
			}
			bufferRead.close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(bufferRead != null){
				try {
					bufferRead.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	//读取市txt文件
	public void readCityTxt(){
		BufferedReader bufferRead=null;
		try {
			String str=null;
			InputStream in = getResources().getAssets().open("city.txt");
			InputStreamReader reader = new InputStreamReader(in,"GBK");
			bufferRead=new BufferedReader(reader);
			str=bufferRead.readLine();
			while (str != null) {
				List<Address> list=new ArrayList<MainActivity.Address>();
				String[] pro=str.split(",");
				if(mapCity.containsKey(pro[0])){
					List<Address> listOld=mapCity.get(pro[0]);
					Address address=new Address();
					address.setPk(pro[1]);
					address.setName(pro[2]);
					listOld.add(address);
					mapCity.put(pro[0], listOld);
				}else{
					List<Address> addsList=new ArrayList<Address>();
					Address adds=new Address();
					adds.setPk(pro[1]);
					adds.setName(pro[2]);
					addsList.add(adds);
					mapCity.put(pro[0], addsList);
				}
				str=bufferRead.readLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(bufferRead != null){
				try {
					bufferRead.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	//读取区txt文件
	public void readRegionTxt(){
		BufferedReader bufferRead=null;
		try {
			String str=null;
			InputStream in = getResources().getAssets().open("region.txt");
			InputStreamReader reader = new InputStreamReader(in,"GBK");
			bufferRead=new BufferedReader(reader);
			str=bufferRead.readLine();
			while (str != null) {
				List<Address> list=new ArrayList<MainActivity.Address>();
				String[] pro=str.split(",");
				if(mapRegion.containsKey(pro[0])){
					List<Address> listOld=mapRegion.get(pro[0]);
					Address address=new Address();
					address.setPk(pro[1]);
					address.setName(pro[2]);
					listOld.add(address);
					mapRegion.put(pro[0], listOld);
				}else{
					List<Address> addsList=new ArrayList<Address>();
					Address adds=new Address();
					adds.setPk(pro[1]);
					adds.setName(pro[2]);
					addsList.add(adds);
					mapRegion.put(pro[0], addsList);
				}
				str=bufferRead.readLine();
			}
			bufferRead.close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(bufferRead != null){
				try {
					bufferRead.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	//adress辅助对象
	private static class Address{
		private String pk;
		private String name;
		public String getPk() {
			return pk;
		}
		public void setPk(String pk) {
			this.pk = pk;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
	}
}