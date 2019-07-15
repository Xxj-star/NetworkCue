package com.skl.mvpdome;

import android.text.TextUtils;

import com.skl.mvpdome.model.UserService;
import com.skl.mvpdome.presenter.IPresenter;
import com.skl.mvpdome.view.IUserService;
import com.skl.mvpdome.view.IView;

public class Presenter implements IPresenter {

    private IView mView;

    public Presenter(IView view) {
        this.mView = view;
    }

    @Override
    public void search() {
        //开启loading
        String inputString = mView.getInputString();
        if (TextUtils.isEmpty(inputString)) {
            return;
        }
        int hashCode = inputString.hashCode();
        IUserService service = new UserService();
        String serviceResult = service.search(hashCode);
        String result = "Result:" + inputString + "-" + serviceResult;
        //关闭loading
        mView.setResultString(result);
    }
}
