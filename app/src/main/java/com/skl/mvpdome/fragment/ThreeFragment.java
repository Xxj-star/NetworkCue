package com.skl.mvpdome.fragment;

import android.util.Log;
import android.view.View;

import com.skl.mvpdome.R;
import com.skl.mvpdome.base.BaseFragment;

public class ThreeFragment extends BaseFragment {
    @Override
    protected int getContentLayoutId() {
        return R.layout.three_fragment;
    }

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        Log.e("ttt","333333333333333333333333333");
    }
}
