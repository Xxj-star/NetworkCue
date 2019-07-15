package com.skl.mvpdome;

import android.widget.EditText;
import android.widget.TextView;

import com.skl.mvpdome.base.BaseActivity;
import com.skl.mvpdome.presenter.IPresenter;
import com.skl.mvpdome.view.IView;

import butterknife.BindView;
import butterknife.OnClick;

public class UserActivity extends BaseActivity implements IView {

    @BindView(R.id.edit_query)
    EditText mInputText;
    @BindView(R.id.text_result)
    TextView mResultText;

    private IPresenter mPresenter;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter = new Presenter(this);
    }

    @OnClick(R.id.btn_submit)
    void onSubmit(){
        mPresenter.search();
    }

    @Override
    public String getInputString() {
        return mInputText.getText().toString();
    }

    @Override
    public void setResultString(String string) {
        mResultText.setText(string);
    }
}
