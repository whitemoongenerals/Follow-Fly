package com.suixin.vy.ui;

import java.util.ArrayList;
import java.util.List;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.suixin.vy.adapter.GVBottomAdapter;
import com.suixin.vy.core.BaseActivity;
import com.suixin.vy.fragment.IndexFragment;
import com.suixin.vy.model.GVBottomModel;

public class HomeActivity extends BaseActivity {
	private GridView gv_bottom;
	private List<GVBottomModel> list_gv;
	private GVBottomAdapter gvbAdapter;
	private Button btn_home;
	private boolean isOpneBtn_home;
	// private ImageView iv_invite, iv_showtrip, iv_video;
	private RelativeLayout rl_invite, rl_showtrip, rl_video;
	private LinearLayout ll_addshow;
	private FragmentManager fm;
	private FragmentTransaction ft;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		// 初始化控件
		initView();
		// 生成数据源
		getData();
		// 设置适配器
		setAdapter();
		// 绑定监听
		addListener();
		// 初始化状态
		isOpneBtn_home = false;
		// 绑定碎片
		Fragment indexFrag = new IndexFragment();
		fm =this.getFragmentManager();
		ft=fm.beginTransaction();
		ft.add(R.id.ll_fragmentshow, indexFrag);
		ft.commit();
		
	}

	private void setAdapter() {
		gvbAdapter = new GVBottomAdapter(this, list_gv, gv_bottom);
		gv_bottom.setAdapter(gvbAdapter);
		gv_bottom.setItemChecked(0, true);
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.btn_home:
			// 判断加号按钮是否打开
			if (this.isOpneBtn_home) {
				closeAdd();
				this.isOpneBtn_home = false;
			} else {
				opneAdd();
				this.isOpneBtn_home = true;
			}
			break;
		case R.id.v_addshow:
			closeAdd();
			this.isOpneBtn_home = false;
			break;
		default:
			break;
		}
	}

	/** 打开加号按钮 */
	private void opneAdd() {
		// 设置透明度
		Animation anim_btn = AnimationUtils.loadAnimation(this,
				R.anim.btn_home_rotate);
		View v_addshow = findViewById(R.id.v_addshow);
		v_addshow.setVisibility(View.VISIBLE);
		v_addshow.setOnClickListener(this);
		btn_home.startAnimation(anim_btn);
		// 显示3个按钮动画效果
		ll_addshow.setVisibility(View.VISIBLE);
		PropertyValuesHolder holder_x = PropertyValuesHolder.ofFloat("scaleX",
				0.1f, 1f);
		PropertyValuesHolder holder_y = PropertyValuesHolder.ofFloat("scaleY",
				0.1f, 1f);
		PropertyValuesHolder holder_t = PropertyValuesHolder.ofFloat(
				"translationY", -260);

		ObjectAnimator anim_a1 = ObjectAnimator.ofPropertyValuesHolder(
				rl_invite, holder_x, holder_y);
		ObjectAnimator anim_a2 = ObjectAnimator.ofPropertyValuesHolder(
				rl_showtrip, holder_x, holder_y);
		ObjectAnimator anim_a3 = ObjectAnimator.ofPropertyValuesHolder(
				rl_video, holder_x, holder_y);
		ObjectAnimator anim_s = ObjectAnimator.ofPropertyValuesHolder(
				ll_addshow, holder_t);
		PropertyValuesHolder holder_xx = PropertyValuesHolder.ofFloat("scaleX",
				1f, 0.97f);
		PropertyValuesHolder holder_yy = PropertyValuesHolder.ofFloat("scaleY",
				1f, 0.97f);
		PropertyValuesHolder holder_tt = PropertyValuesHolder.ofFloat(
				"translationY", -253);
		ObjectAnimator anim_a11 = ObjectAnimator.ofPropertyValuesHolder(
				rl_invite, holder_xx, holder_yy);
		ObjectAnimator anim_a12 = ObjectAnimator.ofPropertyValuesHolder(
				rl_showtrip, holder_xx, holder_yy);
		ObjectAnimator anim_a13 = ObjectAnimator.ofPropertyValuesHolder(
				rl_video, holder_xx, holder_yy);
		ObjectAnimator anim_ss = ObjectAnimator.ofPropertyValuesHolder(
				ll_addshow, holder_tt);
		AnimatorSet set = new AnimatorSet();

		set.playTogether(anim_a1, anim_a2, anim_a3, anim_s);
		set.setDuration(250);
		set.play(anim_a1).before(anim_a11);
		set.playTogether(anim_a11, anim_a12, anim_a13, anim_ss);
		set.start();
	}

	/** 关闭加号按钮 */
	private void closeAdd() {
		// 设置透明度
		Animation anim_btn = AnimationUtils.loadAnimation(this,
				R.anim.btn_home_rotatere);
		findViewById(R.id.v_addshow).setVisibility(View.GONE);
		btn_home.startAnimation(anim_btn);
		// 还原3个按钮的位置和显示
		findViewById(R.id.ll_addshow).setVisibility(View.GONE);
		PropertyValuesHolder holder_re = PropertyValuesHolder.ofFloat(
				"translationY", 0);
		ObjectAnimator anim_re = ObjectAnimator.ofPropertyValuesHolder(
				ll_addshow, holder_re);
		AnimatorSet set = new AnimatorSet();
		set.play(anim_re);
		set.start();
	}

	@Override
	protected void initView() {
		gv_bottom = (GridView) findViewById(R.id.gv_bottom);
		gv_bottom.setChoiceMode(GridView.CHOICE_MODE_SINGLE);
		btn_home = (Button) findViewById(R.id.btn_home);

		// iv_invite = (ImageView) findViewById(R.id.iv_invite);
		// iv_showtrip = (ImageView) findViewById(R.id.iv_showtrip);
		// iv_video = (ImageView) findViewById(R.id.iv_video);

		rl_invite = (RelativeLayout) findViewById(R.id.rl_invite);
		rl_showtrip = (RelativeLayout) findViewById(R.id.rl_showtrip);
		rl_video = (RelativeLayout) findViewById(R.id.rl_video);
		ll_addshow = (LinearLayout) findViewById(R.id.ll_addshow);

	}

	@Override
	protected void addListener() {
		gv_bottom.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				gvbAdapter.notifyDataSetChanged();
			}
		});
		btn_home.setOnClickListener(this);
	}

	@Override
	protected void getData() {
		list_gv = new ArrayList<GVBottomModel>();
		list_gv.add(new GVBottomModel(R.drawable.tab_home_focused,
				R.drawable.tab_home_normal, "首页", false, false));
		list_gv.add(new GVBottomModel(R.drawable.tab_tourpic_focused,
				R.drawable.tab_tourpic_normal, "旅途", false, false));
		list_gv.add(new GVBottomModel(0, 0, "", true, false));
		list_gv.add(new GVBottomModel(R.drawable.tab_chat_focused,
				R.drawable.tab_chat_normal, "聊天", false, false));
		list_gv.add(new GVBottomModel(R.drawable.tab_me_focused,
				R.drawable.tab_me_normal, "我", false, false));
	}

}
