package com.yuetu.type;

import com.example.leshan99.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ShoppingCartFragment extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View shoplayout=inflater.inflate(R.layout.shoppingcart, container,false);
		return shoplayout;
	}
}
