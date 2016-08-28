package com.melody.commelodymobilesafe.activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.melody.commelodymobilesafe.R;
import com.melody.commelodymobilesafe.presenter.SplashPresenter;
import com.melody.commelodymobilesafe.presenter.impl.SplashPresenterImpl;
import com.melody.commelodymobilesafe.view.SplashView;

public class SplashActivity extends AppCompatActivity implements SplashView{

    TextView mVersionName;
    SplashPresenter mSplashPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        init();
    }
//    初始化
    private void init() {
        mVersionName = (TextView)findViewById(R.id.tv_version_name);
        mSplashPresenter = new SplashPresenterImpl(this, this);
        mSplashPresenter.setVersionName();
        mSplashPresenter.checkUpdate();
    }

    @Override
    public void setVersionName(String versionName) {
        mVersionName.setText("版本号：" + versionName);
    }

    @Override
    public void goToHomeActivity() {
        splashDelay();
        startActivity(new Intent(this, HomeActivity.class));
    }

    @Override
    public void showError() {

    }

    @Override
    public void showUpdateDialog() {
        new AlertDialog.Builder(this).setTitle("版本更新")
                .setCancelable(false)
                .setPositiveButton("立即更新", new Dialog.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mSplashPresenter.steupUpdate();

                    }
                })
                .setNegativeButton("暂不更新", new Dialog.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        goToHomeActivity();
                    }
                }).show();
    }

    @Override
    public void splashDelay() {

    }

    @Override
    public void steupApk() {

    }
}
