package com.skl.mvpdome.adapter;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.skl.mvpdome.R;
import com.zhouyou.recyclerview.adapter.HelperRecyclerViewAdapter;
import com.zhouyou.recyclerview.adapter.HelperRecyclerViewHolder;

import java.io.File;


/**
 * Created by Administrator on 2018\7\22 0022.
 */


public class PictureAdapter extends HelperRecyclerViewAdapter<String> {

    public PictureAdapter(Context context) {
        super(context, R.layout.picture_adapter);
    }

    @Override
    protected void HelperBindData(HelperRecyclerViewHolder viewHolder, int position, String item) {
        final String testBean = getData(position);
        if (testBean != null) {
            ImageView image =viewHolder.getView(R.id.image);
            Uri imagePath = Uri.fromFile(new File(testBean));
            Glide.with(mContext).load(imagePath).into(image);
        }
    }
}