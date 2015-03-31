package com.yuetu.main;

import com.example.leshan99.R;
import com.yuetu.type.CollectionFragment;
import com.yuetu.type.HomePageFragment;
import com.yuetu.type.IndividualCenterFragment;
import com.yuetu.type.ShoppingCartFragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends FragmentActivity implements OnClickListener {

	/**
	 * 用于展示主页的Fragment
	 */
	private HomePageFragment homeFragment;

	/**
	 * 用于展示收藏的Fragment
	 */
	private CollectionFragment collectionFragment;

	/**
	 * 用于展示购物车的Fragment
	 */
	private ShoppingCartFragment shopFragment;
	
	/**
	 * 用于展示个人设置的Fragment
	 */
	private IndividualCenterFragment individualFragment;

	/**
	 * 主页界面布局
	 */
	private View homeLayout;
	/**
	 * 收藏界面布局
	 */
	private View collectionLayout;
	/**
	 * 购物车界面布局
	 */
	private View shopLayout;
	/**
	 * 个人设置界面布局
	 */
	private View individualLayout;

	/**
	 * 在Tab布局上显示主页图标的控件
	 */
	private ImageView homeimage;
	/**
	 * 在Tab布局上显示收藏图标的控件
	 */
	private ImageView collectionimage;
	/**
	 * 在Tab布局上显示购物车图标的控件
	 */
	private ImageView shopimage;
	/**
	 * 在Tab布局上显示个人图标的控件
	 */
	private ImageView individualimage;
	/**
	 * 在Tab布局上显示主页标题的控件
	 */
	private TextView homeText;
	/**
	 * 在Tab布局上显示收藏标题的控件
	 */
	private TextView collectionText;
	/**
	 * 在Tab布局上显示购物车标题的控件
	 */
	private TextView shopText;
	/**
	 * 在Tab布局上显示个人标题的控件
	 */
	private TextView individualText;
	
	/**
	 * 用于对Fragment进行管理
	 */
	private FragmentManager fragmentManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main);
		// 初始化布局元素
		initViews();
		fragmentManager =getSupportFragmentManager();
		// 第一次启动时选中第0个tab
		setTabSelection(0);
	}
	



	private void initViews() {
		// TODO Auto-generated method stub
		homeLayout=findViewById(R.id.homepage_layout);
		collectionLayout=findViewById(R.id.collection_layout);
		shopLayout=findViewById(R.id.shop_layout);
		individualLayout=findViewById(R.id.individual_layout);
		
		homeimage=(ImageView) findViewById(R.id.homepage_image);
		collectionimage=(ImageView) findViewById(R.id.collection_image);
		shopimage=(ImageView) findViewById(R.id.shop_image);
		individualimage=(ImageView) findViewById(R.id.individual_image);
		
		homeLayout.setOnClickListener(this);
		collectionLayout.setOnClickListener(this);
		shopLayout.setOnClickListener(this);
		individualLayout.setOnClickListener(this);
	}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.homepage_layout:
			// 当点击了主页tab时，选中第1个tab
			setTabSelection(0);
			break;		
		case R.id.collection_layout:
			// 当点击了收藏tab时，选中第2个tab
			setTabSelection(1);
			break;
		case R.id.shop_layout:
			// 当点击了购物车tab时，选中第3个tab
			setTabSelection(2);
			break;
		case R.id.individual_layout:
			// 当点击了个人tab时，选中第4个tab
			setTabSelection(3);
			break;
		}
	}
	

	/**
	 * 根据传入的index参数来设置选中的tab页。
	 * 
	 * @param index
	 *            每个tab页对应的下标。0表示主页，1表示收藏，2表示购物车，3表示个人设置。
	 */
	private void setTabSelection(int index) {
		// 每次选中之前先清除掉上次的选中状态
		clearSelection();
		// 开启一个Fragment事务
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		// 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
		hideFragments(transaction);
		switch (index) {
		case 0:
			// 当点击了主页tab时，改变控件的图片和文字颜色
			homeimage.setImageResource(R.drawable.shou1_29);
//			homeText.setTextColor(getResources().getColor(R.color.selected));
			if (homeFragment == null) {
				// 如果HomePageFragment为空，则创建一个并添加到界面上
				homeFragment = new HomePageFragment();
				transaction.add(R.id.content, homeFragment);
			} else {
				// 如果ActivitiesFragment不为空，则直接将它显示出来
				transaction.show(homeFragment);
			}
			break;
		case 1:
			// 当点击了收藏tab时，改变控件的图片和文字颜色
			collectionimage.setImageResource(R.drawable.shou1_31);
//			collectionText.setTextColor(getResources().getColor(R.color.selected));
			if (collectionFragment == null) {
				// 如果collectionFragment为空，则创建一个并添加到界面上
				collectionFragment = new CollectionFragment();
				transaction.add(R.id.content, collectionFragment);
			} else {
				// 如果collectionFragment不为空，则直接将它显示出来
				transaction.show(collectionFragment);
			}
			break;
		case 2:
			// 当点击了购物车tab时，改变控件的图片和文字颜色
			shopimage.setImageResource(R.drawable.shou1_37);
//			shopText.setTextColor(getResources().getColor(R.color.selected));
			if (shopFragment == null) {
				// 如果shopFragment为空，则创建一个并添加到界面上
				shopFragment = new ShoppingCartFragment();
				transaction.add(R.id.content, shopFragment);
			} else {
				// 如果shopFragment不为空，则直接将它显示出来
				transaction.show(shopFragment);
			}
			break;

		case 3:		
			// 当点击了个人设置tab时，改变控件的图片和文字颜色
			individualimage.setImageResource(R.drawable.shou1_34);
//			individualText.setTextColor(getResources().getColor(R.color.selected));
			if (individualFragment == null) {
				// 如果individualFragment为空，则创建一个并添加到界面上
				individualFragment = new IndividualCenterFragment();
				transaction.add(R.id.content, individualFragment);
			} else {
				// 如果individualFragment不为空，则直接将它显示出来
				transaction.show(individualFragment);
			}
			break;
		}
		transaction.commit();
	}




	private void clearSelection() {
		// TODO Auto-generated method stub
		homeimage.setImageResource(R.drawable.shou_29);
//		homeText.setTextColor(Color.parseColor("#ffffff"));
		collectionimage.setImageResource(R.drawable.shou_31);
//		collectionText.setTextColor(Color.parseColor("#ffffff"));
		shopimage.setImageResource(R.drawable.shou_37);
//		shopText.setTextColor(Color.parseColor("#ffffff"));
		individualimage.setImageResource(R.drawable.shou_34);
//		individualText.setTextColor(Color.parseColor("#ffffff"));
	}
	/**
	 * 将所有的Fragment都置为隐藏状态。
	 * 
	 * @param transaction
	 *            用于对Fragment执行操作的事务
	 */
	private void hideFragments(FragmentTransaction transaction) {
		if (homeFragment != null) {
			transaction.hide(homeFragment);
		}
		if (collectionFragment != null) {
			transaction.hide(collectionFragment);
		}
		if (shopFragment != null) {
			transaction.hide(shopFragment);
		}
		if (individualFragment != null) {
			transaction.hide(individualFragment);
		}
	}
}
