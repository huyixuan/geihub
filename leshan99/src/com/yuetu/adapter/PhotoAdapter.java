package com.yuetu.adapter;

import java.util.List;

import com.example.leshan99.R;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class PhotoAdapter extends BaseAdapter{
	
	Context context;
	List<Integer> photo;
	ViewHolder vh = null;

	public PhotoAdapter(Context context, List<Integer> photo) {
		super();
		this.context = context;
		this.photo = photo;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return photo.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return photo.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		if(convertView == null){
			vh = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(R.layout.photo_list, null);
			vh.mIcon = (ImageView) convertView.findViewById(R.id.photo);
			convertView.setTag(vh);
		}else{
			vh = (ViewHolder) convertView.getTag();
		}
		vh.mIcon.setBackgroundResource(photo.get(position));
		
		Log.i("leshan", "图片名字"+photo.get(position)+".......");
		
		return convertView;
	}
	class ViewHolder{
		ImageView mIcon;
		TextView tv;
	}

}
