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
	//����ʡ�ݵ�list����
	public List<Address> listPro;
	//����������map����
	public Map<String, List<Address>> mapCity;
	//�����ص�map����
	public Map<String, List<Address>> mapRegion;
	//������Ҫ��ʾ����������
	public List<Address> showListCity;
	//������Ҫ��ʾ�����ļ���
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
		//������ť�¼�
		btn1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				//���ﴦ��һ�·�ֹnull
				if(listPro ==null){
					return;
				}
				show_select_address("��ѡ��ʡ��",listPro, MainActivity.this, new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int arg1) {
						Address address=listPro.get(arg1);
						String province=address.getName();
						String pk=address.getPk();
						System.out.println("ѡ���ʡ����="+province+"��pk="+pk);
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
                show_select_address("��ѡ������",showListCity, MainActivity.this, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int arg1) {
						Address address=showListCity.get(arg1);
						String province=address.getName();
						String pk=address.getPk();
						System.out.println("ѡ���������="+province+"��pk="+pk);
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
				show_select_address("��ѡ�����",showListRegion, MainActivity.this, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int arg1) {
						Address address=showListRegion.get(arg1);
						String province=address.getName();
						String pk=address.getPk();
						System.out.println("ѡ���������="+province+"��pk="+pk);
						dialog.cancel();
					}
				} );
			}
		});
	}
	//��ʾaddress�б�
	public void show_select_address(String title,List<Address> list,Activity activity,DialogInterface.OnClickListener post){
		final String[] items=new String[list.size()];
		for (int i = 0; i <list.size(); i++) {
			Address address=list.get(i);
			items[i]=address.getName();
		}
		AlertDialog.Builder builder=new AlertDialog.Builder(activity);
		    builder.setTitle(title).setIcon(android.R.drawable.ic_lock_lock).setSingleChoiceItems(items,-1,  post).create().show();
	}
	//��ȡʡtxt�ļ�
	public void readTxt(){
		BufferedReader bufferRead=null;
		try {
			String str=null;
			//�����Ŀassets�ļ����µ��ļ�
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
	//��ȡ��txt�ļ�
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
	//��ȡ��txt�ļ�
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
	//adress��������
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