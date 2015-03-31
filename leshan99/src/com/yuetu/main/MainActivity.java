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
	 * ����չʾ��ҳ��Fragment
	 */
	private HomePageFragment homeFragment;

	/**
	 * ����չʾ�ղص�Fragment
	 */
	private CollectionFragment collectionFragment;

	/**
	 * ����չʾ���ﳵ��Fragment
	 */
	private ShoppingCartFragment shopFragment;
	
	/**
	 * ����չʾ�������õ�Fragment
	 */
	private IndividualCenterFragment individualFragment;

	/**
	 * ��ҳ���沼��
	 */
	private View homeLayout;
	/**
	 * �ղؽ��沼��
	 */
	private View collectionLayout;
	/**
	 * ���ﳵ���沼��
	 */
	private View shopLayout;
	/**
	 * �������ý��沼��
	 */
	private View individualLayout;

	/**
	 * ��Tab��������ʾ��ҳͼ��Ŀؼ�
	 */
	private ImageView homeimage;
	/**
	 * ��Tab��������ʾ�ղ�ͼ��Ŀؼ�
	 */
	private ImageView collectionimage;
	/**
	 * ��Tab��������ʾ���ﳵͼ��Ŀؼ�
	 */
	private ImageView shopimage;
	/**
	 * ��Tab��������ʾ����ͼ��Ŀؼ�
	 */
	private ImageView individualimage;
	/**
	 * ��Tab��������ʾ��ҳ����Ŀؼ�
	 */
	private TextView homeText;
	/**
	 * ��Tab��������ʾ�ղر���Ŀؼ�
	 */
	private TextView collectionText;
	/**
	 * ��Tab��������ʾ���ﳵ����Ŀؼ�
	 */
	private TextView shopText;
	/**
	 * ��Tab��������ʾ���˱���Ŀؼ�
	 */
	private TextView individualText;
	
	/**
	 * ���ڶ�Fragment���й���
	 */
	private FragmentManager fragmentManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main);
		// ��ʼ������Ԫ��
		initViews();
		fragmentManager =getSupportFragmentManager();
		// ��һ������ʱѡ�е�0��tab
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
			// ���������ҳtabʱ��ѡ�е�1��tab
			setTabSelection(0);
			break;		
		case R.id.collection_layout:
			// ��������ղ�tabʱ��ѡ�е�2��tab
			setTabSelection(1);
			break;
		case R.id.shop_layout:
			// ������˹��ﳵtabʱ��ѡ�е�3��tab
			setTabSelection(2);
			break;
		case R.id.individual_layout:
			// ������˸���tabʱ��ѡ�е�4��tab
			setTabSelection(3);
			break;
		}
	}
	

	/**
	 * ���ݴ����index����������ѡ�е�tabҳ��
	 * 
	 * @param index
	 *            ÿ��tabҳ��Ӧ���±ꡣ0��ʾ��ҳ��1��ʾ�ղأ�2��ʾ���ﳵ��3��ʾ�������á�
	 */
	private void setTabSelection(int index) {
		// ÿ��ѡ��֮ǰ��������ϴε�ѡ��״̬
		clearSelection();
		// ����һ��Fragment����
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		// �����ص����е�Fragment���Է�ֹ�ж��Fragment��ʾ�ڽ����ϵ����
		hideFragments(transaction);
		switch (index) {
		case 0:
			// ���������ҳtabʱ���ı�ؼ���ͼƬ��������ɫ
			homeimage.setImageResource(R.drawable.shou1_29);
//			homeText.setTextColor(getResources().getColor(R.color.selected));
			if (homeFragment == null) {
				// ���HomePageFragmentΪ�գ��򴴽�һ������ӵ�������
				homeFragment = new HomePageFragment();
				transaction.add(R.id.content, homeFragment);
			} else {
				// ���ActivitiesFragment��Ϊ�գ���ֱ�ӽ�����ʾ����
				transaction.show(homeFragment);
			}
			break;
		case 1:
			// ��������ղ�tabʱ���ı�ؼ���ͼƬ��������ɫ
			collectionimage.setImageResource(R.drawable.shou1_31);
//			collectionText.setTextColor(getResources().getColor(R.color.selected));
			if (collectionFragment == null) {
				// ���collectionFragmentΪ�գ��򴴽�һ������ӵ�������
				collectionFragment = new CollectionFragment();
				transaction.add(R.id.content, collectionFragment);
			} else {
				// ���collectionFragment��Ϊ�գ���ֱ�ӽ�����ʾ����
				transaction.show(collectionFragment);
			}
			break;
		case 2:
			// ������˹��ﳵtabʱ���ı�ؼ���ͼƬ��������ɫ
			shopimage.setImageResource(R.drawable.shou1_37);
//			shopText.setTextColor(getResources().getColor(R.color.selected));
			if (shopFragment == null) {
				// ���shopFragmentΪ�գ��򴴽�һ������ӵ�������
				shopFragment = new ShoppingCartFragment();
				transaction.add(R.id.content, shopFragment);
			} else {
				// ���shopFragment��Ϊ�գ���ֱ�ӽ�����ʾ����
				transaction.show(shopFragment);
			}
			break;

		case 3:		
			// ������˸�������tabʱ���ı�ؼ���ͼƬ��������ɫ
			individualimage.setImageResource(R.drawable.shou1_34);
//			individualText.setTextColor(getResources().getColor(R.color.selected));
			if (individualFragment == null) {
				// ���individualFragmentΪ�գ��򴴽�һ������ӵ�������
				individualFragment = new IndividualCenterFragment();
				transaction.add(R.id.content, individualFragment);
			} else {
				// ���individualFragment��Ϊ�գ���ֱ�ӽ�����ʾ����
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
	 * �����е�Fragment����Ϊ����״̬��
	 * 
	 * @param transaction
	 *            ���ڶ�Fragmentִ�в���������
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
