package com.skl.mvpdome;


import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.lcw.library.imagepicker.ImagePicker;
import com.skl.mvpdome.adapter.PictureAdapter;
import com.skl.mvpdome.base.BaseActivity;
import com.skl.mvpdome.utils.GlideLoader;
import com.zhouyou.recyclerview.adapter.BaseRecyclerViewAdapter;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;
import cc.shinichi.library.ImagePreview;
import cc.shinichi.library.bean.ImageInfo;
import cc.shinichi.library.view.listener.OnBigImageClickListener;
import cc.shinichi.library.view.listener.OnBigImageLongClickListener;
import cc.shinichi.library.view.listener.OnBigImagePageChangeListener;
import cc.shinichi.library.view.listener.OnOriginProgressListener;


public class PictureActivity extends BaseActivity {

    private static final int REQUEST_SELECT_IMAGES_CODE = 0x01;
    private ArrayList<String> mImagePaths;
    @BindView(R.id.imageRecyclerView)
    RecyclerView mRecyclerView;
    PictureAdapter mAdapter;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_picture;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        List<String> imageList = new ArrayList<>();
        imageList.add("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3758689078,3167760751&fm=27&gp=0.jpg");
        imageList.add("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=2378340389,1058905659&fm=27&gp=0.jpg");
        imageList.add("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3993856800,2757669228&fm=27&gp=0.jpg");
        imageList.add("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1919987481,1458816333&fm=27&gp=0.jpg");
        imageList.add("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2147845381,555035544&fm=27&gp=0.jpg");
        imageList.add("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=386863998,3749134059&fm=27&gp=0.jpg");
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setAutoMeasureEnabled(true);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new PictureAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseRecyclerViewAdapter.OnItemClickListener<String>() {
            @Override
            public void onItemClick(View view, String item, int position) {
                ImagePreview.getInstance()
                        // 上下文，必须是activity，不需要担心内存泄漏，本框架已经处理好
                        .setContext(PictureActivity.this)
                        // 从第几张图片开始，索引从0开始哦~
                        .setIndex(0)
                        //=================================================================================================
                        // 有三种设置数据集合的方式，根据自己的需求进行三选一：
                        // 1：第一步生成的imageInfo List
                        //.setImageInfoList(images)
                        // 2：直接传url List
                        .setImageList(imageList)
                        // 3：只有一张图片的情况，可以直接传入这张图片的url
                        //.setImage("https://ws1.sinaimg.cn/large/610dc034ly1fgepc1lpvfj20u011i0wv.jpg")
                        //=================================================================================================
                        // 缩放动画时长，单位ms
                        .setZoomTransitionDuration(1000)
                        // 是否显示加载失败的Toast
                        .setShowErrorToast(true)
                        // 是否启用点击图片关闭。默认启用
                        .setEnableClickClose(true)
                        // 是否启用下拉关闭。默认不启用
                        .setEnableDragClose(true)
                        // 是否启用上拉关闭。默认不启用
                        .setEnableUpDragClose(true)
                        // 是否显示关闭页面按钮，在页面左下角。默认不显示
                        .setShowCloseButton(true)
                        // 设置关闭按钮图片资源，可不填，默认为库中自带：R.drawable.ic_action_close
                        .setCloseIconResId(R.drawable.ic_action_close)
                        // 是否显示下载按钮，在页面右下角。默认显示
                        .setShowDownButton(true)
                        // 设置下载按钮图片资源，可不填，默认为库中自带：R.drawable.icon_download_new
                        .setDownIconResId(R.drawable.icon_download_new)
                        // 设置是否显示顶部的指示器（1/9）默认显示
                        .setShowIndicator(true)
                        // 设置失败时的占位图，默认为库中自带R.drawable.load_failed，设置为 0 时不显示
                        .setErrorPlaceHolder(R.drawable.load_failed)
                        // 开启预览
                        .start();
            }
        });
    }

    @OnClick(R.id.selectBut)
    public void selectImage() {
        ImagePicker.getInstance()
                .setTitle("标题")//设置标题
                .showCamera(true)//设置是否显示拍照按钮
                .showImage(true)//设置是否展示图片
                .showVideo(false)//设置是否展示视频
                .setMaxCount(3)//设置最大选择图片数目(默认为1，单选)
                .setSingleType(true)//设置图片视频不能同时选择
                .setImagePaths(mImagePaths)//设置历史选择记录
                .setImageLoader(new GlideLoader())//设置自定义图片加载器
                .start(PictureActivity.this, REQUEST_SELECT_IMAGES_CODE);//REQEST_SELECT_IMAGES_CODE为Intent调用的requestCode
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SELECT_IMAGES_CODE && resultCode == RESULT_OK) {
            mImagePaths = data.getStringArrayListExtra(ImagePicker.EXTRA_SELECT_IMAGES);
            Log.e("ttt", "----------imagePaths----------->" + mImagePaths);
            mAdapter.setListAll(mImagePaths);
        }
    }


}
