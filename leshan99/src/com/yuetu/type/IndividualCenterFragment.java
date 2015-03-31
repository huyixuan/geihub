package com.yuetu.type;

import com.example.leshan99.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class IndividualCenterFragment extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View individuallayout=inflater.inflate(R.layout.individualcenter, container,false);
		return individuallayout;
	}
}
