package com.ubisys.uav.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.ubisys.uav.R;
import com.ubisys.uav.frament.HomeFragment;
import com.ubisys.uav.frament.MessageFragment;
import com.ubisys.uav.frament.MineFragment;
import com.ubisys.uav.frament.ShopcartFragment;
import com.ubisys.uav.utils.EventMessage;
import com.ubisys.uav.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Main1Activity extends AppCompatActivity {

    private final static int SCANNIN_GREQUEST_CODE = 1;
    private static final String TAG = "vivi";
    @Bind(R.id.content)
    FrameLayout mContent;
    @Bind(R.id.rbHome)
    RadioButton mRbHome;
    @Bind(R.id.rbShop)
    RadioButton mRbShop;
    @Bind(R.id.rbMessage)
    RadioButton mRbMessage;
    @Bind(R.id.rbMine)
    RadioButton mRbMine;
    @Bind(R.id.rgTools)
    RadioGroup mRgTools;
    @Bind(R.id.add_icon)
    RadioButton addIcon;
    private Fragment[] mFragments;


    private int mIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);


        ButterKnife.bind(this);
        EventBus.getDefault().register(this);

        initFragment();
    }

    private void initFragment() {
        //首页
        HomeFragment homeFragment = new HomeFragment();
        //购物车
        ShopcartFragment shopcartFragment = new ShopcartFragment();

        //消息
        MessageFragment messageFragment = new MessageFragment();
        //个人中心

        MineFragment mineFragment = new MineFragment();

        //添加到数组
        mFragments = new Fragment[]{homeFragment, shopcartFragment, messageFragment, mineFragment};

        //开启事务

        FragmentTransaction ft =
                getSupportFragmentManager().beginTransaction();

        //添加首页
        ft.add(R.id.content, homeFragment).commit();

        //默认设置为第0个
        setIndexSelected(0);

    }

    private void setIndexSelected(int index) {

        if (mIndex == index) {
            return;
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();


        //隐藏
        ft.hide(mFragments[mIndex]);
        //判断是否添加
        if (!mFragments[index].isAdded()) {
            ft.add(R.id.content, mFragments[index]).show(mFragments[index]);
        } else {
            ft.show(mFragments[index]);
        }

        ft.commit();
        //再次赋值
        mIndex = index;

    }

    private final int CAMERA_REQUEST_CODE = 1;

    @OnClick({R.id.rbHome, R.id.rbShop, R.id.rbMessage, R.id.rbMine, R.id.add_icon})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rbHome:
                setIndexSelected(0);

                mRbHome.setTextColor(mRbHome.getResources().getColor(R.color.select));
                mRbShop.setTextColor(mRbShop.getResources().getColor(R.color.select_f));
                mRbMessage.setTextColor(mRbMessage.getResources().getColor(R.color.select_f));
                mRbMine.setTextColor(mRbMine.getResources().getColor(R.color.select_f));
                break;
            case R.id.rbShop:
                setIndexSelected(1);

                mRbShop.setTextColor(mRbShop.getResources().getColor(R.color.select));
                mRbHome.setTextColor(mRbHome.getResources().getColor(R.color.select_f));
                mRbMessage.setTextColor(mRbMessage.getResources().getColor(R.color.select_f));
                mRbMine.setTextColor(mRbMine.getResources().getColor(R.color.select_f));


                break;
            case R.id.rbMessage:
                setIndexSelected(2);
                mRbMessage.setTextColor(mRbMessage.getResources().getColor(R.color.select));

                mRbHome.setTextColor(mRbHome.getResources().getColor(R.color.select_f));
                mRbShop.setTextColor(mRbShop.getResources().getColor(R.color.select_f));
                mRbMine.setTextColor(mRbMine.getResources().getColor(R.color.select_f));

                break;
            case R.id.rbMine:
                setIndexSelected(3);

                mRbMine.setTextColor(mRbMine.getResources().getColor(R.color.select));

                mRbHome.setTextColor(mRbHome.getResources().getColor(R.color.select_f));
                mRbShop.setTextColor(mRbShop.getResources().getColor(R.color.select_f));
                mRbMessage.setTextColor(mRbMessage.getResources().getColor(R.color.select_f));
                break;
            case R.id.add_icon:
                ToastUtil.showToast(this, "111");


                mRbShop.setTextColor(mRbShop.getResources().getColor(R.color.select_f));
                mRbHome.setTextColor(mRbHome.getResources().getColor(R.color.select_f));
                mRbMessage.setTextColor(mRbMessage.getResources().getColor(R.color.select_f));
                mRbMine.setTextColor(mRbMine.getResources().getColor(R.color.select_f));

                break;


        }

    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setGoIndex(EventMessage eventMessage) {
        Log.d(TAG, "setGoIndex: " + eventMessage.getTag());
        if (eventMessage != null) {
            int tag = eventMessage.getTag();


            if (tag == EventMessage.EventMessageAction.TAG_GO_MAIN) {
                mRbHome.performClick();
                setIndexSelected(0);
            } else if (tag == EventMessage.EventMessageAction.TAG_GO_SHOPCART) {
                mRbShop.performClick();

                setIndexSelected(1);
            } else if (tag == EventMessage.EventMessageAction.TAG_GO_MESSAGE) {
                mRbMessage.performClick();
                setIndexSelected(2);
            }


        }
    }


}
