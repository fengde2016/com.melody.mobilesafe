package com.melody.mobilesafe.presenter.impl;

import android.content.Context;
import android.widget.Toast;

import com.melody.mobilesafe.Listener.OnUpdateCheckListener;
import com.melody.mobilesafe.Listener.OnUpdateDownloadListener;
import com.melody.mobilesafe.application.MyApplication;
import com.melody.mobilesafe.model.SplashModel;
import com.melody.mobilesafe.model.impl.SplashModelImpl;
import com.melody.mobilesafe.presenter.SplashPresenter;
import com.melody.mobilesafe.view.SplashView;

/**
 * Created by fengd on 2016/8/28.
 */
public class SplashPresenterImpl implements SplashPresenter, OnUpdateCheckListener,OnUpdateDownloadListener {

    private SplashModel splashModel;
    private SplashView splashView;
    private Context context;

    public SplashPresenterImpl(SplashView splashView, Context context) {
        this.splashView = splashView;
        this.context = context;
        splashModel = new SplashModelImpl(context);
    }

    @Override
    public void checkUpdate() {
//        检查更新
        splashModel.checkUpdate(this);
    }

    @Override
    public void steupUpdate() {
        splashModel.downladUpdate(this);
    }

    @Override
    public void setVersionName() {
//        设置版本名称
        splashView.setVersionName(splashModel.loadVersionName());
    }

    @Override
    public void splashDelay() {

    }

    @Override
    public void onUpdate() {
        splashView.showUpdateDialog();
    }

    @Override
    public void onDont() {

    }

    @Override
    public void onSuccess() {
        splashView.steupApk();
        Toast.makeText(context, "下载成功！", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailed() {
        splashView.showError();
    }
}
