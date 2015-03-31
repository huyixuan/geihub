package com.yuetu.type;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.example.leshan99.R;
import com.yuetu.adapter.PhotoAdapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class HomePageFragment extends Fragment {


	private ArrayList<ImageView> images;
	private List<View> dots;// ����Բ��
	private List<Integer> listBeans; //���������ͼƬ����    �õ�ʱ��ĳɰ���ʵ����ļ���
	
	private int oldPosition = 0;
	private int currentPosition = 0;
	private ViewPager vp_main;
	private LinearLayout layoyt_dots;
	private TextView tv_title;
	private ListView listView;
	private List<Integer> ugfhphoto;//储存图片
	private ScheduledExecutorService scheduledExecutorService;
	private static final int PIC = 12;
	@SuppressLint("HandlerLeak")
	private Handler mHandler = new Handler() {

		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case PIC:
				vp_main.setCurrentItem(currentPosition);
				break;
			case 13:
				if (listBeans != null && listBeans.size() > 0) {
					scheduledExecutorService.scheduleWithFixedDelay(new PagerTask(), 4, 6, TimeUnit.SECONDS);
				}
				break;
			default:
				break;
			}
		};
	};	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View homelayout=inflater.inflate(R.layout.homepage, container,false);
		// ��ʼ���ؼ�
		vp_main=(ViewPager) homelayout.findViewById(R.id.vp_main);
		listView = (ListView) homelayout.findViewById(R.id.listView1);
		layoyt_dots=(LinearLayout) homelayout.findViewById(R.id.layoyt_dots);
//		tv_title=(TextView) homelayout.findViewById(R.id.tv_title);
		
		listBeans=new ArrayList<Integer>();
		listBeans.add(R.drawable.banner);
		listBeans.add(R.drawable.tu1);
		listBeans.add(R.drawable.tu2);
		//存放图片
		ugfhphoto = new ArrayList<Integer>();
		ugfhphoto.add(R.drawable.tu1);
		ugfhphoto.add(R.drawable.tu2);
		listView.setDividerHeight(0);
		PhotoAdapter pa = new PhotoAdapter(getActivity(),ugfhphoto);
		listView.setAdapter(pa);
		initFourmPic();
		return homelayout;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
	/**
	 * �ֲ�����
	 * 
	 * @param
	 */
	private void initFourmPic() {
		images = new ArrayList<ImageView>();
		dots = new ArrayList<View>();
		currentPosition = 0;
		oldPosition = 0;
		//����ͼƬ����   �޸������Ǹı�ѭ���Ĵ���
		for (int i = 0; i < listBeans.size(); i++) {
			Log.i("TAG", listBeans.size() + "");
			ImageView iv = new ImageView(getActivity());
			iv.setScaleType(ScaleType.FIT_XY);
			iv.setImageResource((int)listBeans.get(i));

			images.add(iv);
		}
		//���õײ�ԭ������   ������һ��
		for (int i = 0; i < listBeans.size(); i++) {
			ImageView iv = new ImageView(getActivity());
			//���������ȫ��px  �����д����dpתpx�ķ���
			LayoutParams params = new LayoutParams(15, 15);
			params.setMargins(8, 0, 0, 0);
			iv.setLayoutParams(params);
			iv.setBackgroundResource(R.drawable.main_pager_forcused);
			layoyt_dots.addView(iv);
			layoyt_dots.setGravity(Gravity.CENTER);
			dots.add(iv);
			
		}

		// dots.add(findViewById(R.id.dot_0));
		// dots.add(findViewById(R.id.dot_1));
		// dots.add(findViewById(R.id.dot_2));
		// dots.add(findViewById(R.id.dot_3));
		// dots.add(findViewById(R.id.dot_4));
		dots.get(0).setBackgroundResource(R.drawable.main_pager_normal);

	//	tv_title.setText(listBeans.get(0).getFoodName());

		MyAdapter adapter = new MyAdapter();

		vp_main.setAdapter(adapter);
		// viewpager�Ļ��������¼�
		vp_main.setOnPageChangeListener(new OnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {
				final int p = position;
				// ����Բ��
				dots.get(oldPosition).setBackgroundResource(R.drawable.main_pager_forcused);
				dots.get(position).setBackgroundResource(R.drawable.main_pager_normal);
				// ���ñ�������   �ȸ���ע�͵���
				//tv_title.setText(listBeans.get(position).getFoodName());
				// ͼƬ����¼�

				oldPosition = p;
				currentPosition = p;
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {

			}
		});
	}

	private class MyAdapter extends PagerAdapter {
		@Override
		public int getCount() {
			return images.size();
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {

			return view == (object);
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView(images.get(position));
		}

		@Override
		public Object instantiateItem(ViewGroup container, final int position) {
			container.addView(images.get(position));
			images.get(position).setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// ������ͼƬ�ĵ���¼�

					
					
				}
			});
			return images.get(position);
		}

	}
	
	
	
	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		scheduledExecutorService.shutdown();
	}
	

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		
		// ������ʱ��ʵ��
		scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
		if (listBeans != null && listBeans.size() > 0) {
			scheduledExecutorService.scheduleWithFixedDelay(new PagerTask(), 1, 6, TimeUnit.SECONDS);
		}
	}
	
	
	/*
	 * �첽
	 */
	private class PagerTask implements Runnable {

		@Override
		public void run() {

			currentPosition = (currentPosition + 1) % images.size();
			Message msg = mHandler.obtainMessage(PIC);
			mHandler.sendMessage(msg);

		}

	}	
	
	
	public static class DensityUtil {  

	    /** 
	     * �����ֻ��ķֱ��ʴ� dp �ĵ�λ ת��Ϊ px(����) 
	     */  
	    public static int dip2px(Context context, float dpValue) {  
	        final float scale = context.getResources().getDisplayMetrics().density;  
	        return (int) (dpValue * scale + 0.5f);  
	    }  

	    /** 
	     * �����ֻ��ķֱ��ʴ� px(����) �ĵ�λ ת��Ϊ dp 
	     */  
	    public static int px2dip(Context context, float pxValue) {  
	        final float scale = context.getResources().getDisplayMetrics().density;  
	        return (int) (pxValue / scale + 0.5f);  
	    }  
	}  

	
	
}
