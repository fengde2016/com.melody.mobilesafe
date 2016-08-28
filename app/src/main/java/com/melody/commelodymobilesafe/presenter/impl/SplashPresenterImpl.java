package com.melody.commelodymobilesafe.presenter.impl;

import android.content.Context;

import com.melody.commelodymobilesafe.model.SplashModel;
import com.melody.commelodymobilesafe.model.impl.SplashModelImpl;
import com.melody.commelodymobilesafe.presenter.SplashPresenter;
import com.melody.commelodymobilesafe.view.SplashView;

/**
 * Created by fengd on 2016/8/28.
 */
public class SplashPresenterImpl implements SplashPresenter {

    private SplashModel splashModel;
    private SplashView splashView;

    public SplashPresenterImpl(SplashView splashView, Context context) {
        this.splashView = splashView;
        splashModel = new SplashModelImpl(context);
    }

    @Override
    public void checkUpdate() {
//        如果检查更新返回true，显示更新对话框
        if (splashModel.checkUpdate()) {
            splashView.showUpdateDialog();
        }
    }

    @Override
    public void steupUpdate() {
//        如果下载更新成功，自动跳转到安装界面
        if (splashModel.downladUpdate() != null) {
            splashView.steupApk();
        } else {
            splashView.showError();
        }
    }

    @Override
    public void setVersionName() {
//        设置版本名称
        splashView.setVersionName(splashModel.loadVersionName());
    }
}
