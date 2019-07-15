package com.skl.mvpdome;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnticipateOvershootInterpolator;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.skl.mvpdome.base.BaseActivity;
import com.skl.mvpdome.fragment.ActiveFragment;
import com.skl.mvpdome.fragment.ThreeFragment;
import com.skl.mvpdome.fragment.TwoFragment;
import com.skl.mvpdome.utils.NavHelper;

import java.util.Objects;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener, NavHelper.OnTabChangedListener<Integer> {

    @BindView(R.id.navigation)
    BottomNavigationView mNavigation;
    private NavHelper<Integer> mNavHelper;
    @BindView(R.id.btn_action)
    FloatingActionButton mAction;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        // 初始化底部辅助工具类
        mNavHelper = new NavHelper<>(this, R.id.lay_container, getSupportFragmentManager(), this);
        mNavHelper.add(R.id.action_home, new NavHelper.Tab<>(ActiveFragment.class, R.string.title_home))
                .add(R.id.action_group, new NavHelper.Tab<>(TwoFragment.class, R.string.title_group))
                .add(R.id.action_msg, new NavHelper.Tab<>(TwoFragment.class, R.string.title_group))
                .add(R.id.action_contact, new NavHelper.Tab<>(ThreeFragment.class, R.string.title_contact));
        // 添加对底部按钮点击的监听
        mNavigation.setOnNavigationItemSelectedListener(this);
        //获取整个的NavigationView
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) mNavigation.getChildAt(0);
        //这里就是获取所添加的每一个Tab(或者叫menu)，
        View tab = menuView.getChildAt(3);
        BottomNavigationItemView itemView = (BottomNavigationItemView) tab;
        //加载我们的角标View，新创建的一个布局
        View badge = LayoutInflater.from(this).inflate(R.layout.im_badge, menuView, false);
        //添加到Tab上
        itemView.addView(badge);
        TextView textView = badge.findViewById(R.id.texT);
        String max = textView.getText().toString();
        int result = Integer.parseInt(max);
        textView.setVisibility(result > 0 ? View.VISIBLE : View.GONE);
    }

    @Override
    protected void initData() {
        super.initData();
        // 从底部导中接管我们的Menu，然后进行手动的触发第一次点击
        Menu menu = mNavigation.getMenu();
        // 触发首次选中Home
        menu.performIdentifierAction(R.id.action_home, 0);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        // 转接事件流到工具类中
        return mNavHelper.performClickMenu(menuItem.getItemId());
    }

    @Override
    public void onTabChanged(NavHelper.Tab<Integer> newTab, NavHelper.Tab<Integer> oldTab) {
        // 对浮动按钮进行隐藏与显示的动画
        float transY = 76;
        float rotation = 0;
        if (Objects.equals(newTab.extra, R.string.title_home)) {
            // 主界面时隐藏
        } else {
            // transY 默认为0 则显示
            if (Objects.equals(newTab.extra, R.string.title_group)) {
                // 群
                mAction.setImageResource(R.drawable.ic_contact);
                rotation = -360;
            } else {
                // 联系人
                mAction.setImageResource(R.drawable.ic_home);
                rotation = 360;
            }
        }

        // 开始动画
        // 旋转，Y轴位移，弹性差值器，时间
        mAction.animate()
                .rotation(rotation)
                .translationY(transY)
                .setInterpolator(new AnticipateOvershootInterpolator(1))
                .setDuration(480)
                .start();
    }
}
