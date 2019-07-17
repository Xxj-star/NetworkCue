package com.skl.mvpdome.fragment;

import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.skl.mvpdome.PictureActivity;
import com.skl.mvpdome.R;
import com.skl.mvpdome.base.BaseFragment;

import butterknife.OnClick;

public class TwoFragment extends BaseFragment {
    @Override
    protected int getContentLayoutId() {
        return R.layout.two_fragment;
    }

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        Log.e("ttt","222222222222222222222");
    }

    @OnClick(R.id.skipBut)
    public void IntentBut() {
        getActivity().startActivity(new Intent(getActivity(), PictureActivity.class));
    }
}
