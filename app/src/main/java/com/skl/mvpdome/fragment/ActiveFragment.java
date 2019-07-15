package com.skl.mvpdome.fragment;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

import com.skl.mvpdome.R;
import com.skl.mvpdome.base.BaseFragment;
import com.skl.networkmonitor.NetType;
import com.skl.networkmonitor.NetworkLiveData;

public class ActiveFragment extends BaseFragment {


    @Override
    protected int getContentLayoutId() {
        return R.layout.active_fragment;
    }

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        NetworkLiveData.get(getActivity()).observe(this, new Observer<NetType>() {
            @Override
            public void onChanged(@Nullable NetType netType) {
                switch (netType) {
                    case NET_UNKNOW:
                        Toast.makeText(getActivity(), "未知网络", Toast.LENGTH_LONG).show();
                        Log.e("uuu", "---MainActivity---未知网络");
                        break;
                    case NET_4G:
                        Toast.makeText(getActivity(), "4G", Toast.LENGTH_LONG).show();
                        Log.e("uuu", "---MainActivity---4G");
                        break;
                    case NET_3G:
                        Toast.makeText(getActivity(), "3G", Toast.LENGTH_LONG).show();
                        Log.e("uuu", "---MainActivity---3G");
                        break;
                    case NET_2G:
                        //有网络
                        Toast.makeText(getActivity(), "2G", Toast.LENGTH_LONG).show();
                        Log.e("uuu", "---MainActivity---2G");
                        break;
                    case WIFI:
                        Toast.makeText(getActivity(), "WIFI", Toast.LENGTH_LONG).show();
                        Log.e("uuu", "---MainActivity---WIFI");
                        break;
                    case NOME:
                        Toast.makeText(getActivity(), "没网络", Toast.LENGTH_LONG).show();
                        Log.e("uuu", "---MainActivity---没网络");
                        //没有网络，提示用户跳转到设置
                        break;
                    default:
                        break;
                }
            }
        });

    }


}
