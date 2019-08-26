package com.skl.networkmonitor;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.util.Log;

import androidx.lifecycle.LiveData;


public class NetworkLiveData extends LiveData<NetType> {

    private final Context mContext;
    @SuppressLint("StaticFieldLeak")
    private static NetworkLiveData mNetworkLiveData;
    private NetworkReceiver mNetworkReceiver;
    private final IntentFilter mIntentFilter;

    private static final String TAG = "NetworkLiveData";

    private NetworkLiveData(Context context) {
        mContext = context.getApplicationContext();
        mNetworkReceiver = new NetworkReceiver();
        mIntentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
    }

    public static NetworkLiveData get(Context context) {
        if (mNetworkLiveData == null) {
            mNetworkLiveData = new NetworkLiveData(context);
        }
        return mNetworkLiveData;
    }

    @Override
    protected void onActive() {
        super.onActive();
        Log.d(TAG, "onActive:");
        mContext.registerReceiver(mNetworkReceiver, mIntentFilter);
    }

    @Override
    protected void onInactive() {
        super.onInactive();
        Log.d(TAG, "onInactive: ");
        mContext.unregisterReceiver(mNetworkReceiver);
    }

    private static class NetworkReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            get(context).setValue(NetUtil.getNetType(context));
        }
    }


}
