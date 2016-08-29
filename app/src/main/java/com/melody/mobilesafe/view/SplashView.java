package com.melody.mobilesafe.view;

/**
 * Created by fengd on 2016/8/28.
 */
public interface SplashView {
    //    设置app版本名称
    void setVersionName(String versionName);

    //    跳转到主界面
    void goToHomeActivity();

    //    显示错误
    void showError();

    //    显示更新对话框
    void showUpdateDialog();

    //    splash界面延时
    void splashDelay();

    //    跳转到安装界面
    void steupApk();
}
