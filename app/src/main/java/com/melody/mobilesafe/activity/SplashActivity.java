package com.melody.mobilesafe.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.melody.mobilesafe.R;
import com.melody.mobilesafe.presenter.SplashPresenter;
import com.melody.mobilesafe.presenter.impl.SplashPresenterImpl;
import com.melody.mobilesafe.view.SplashView;

import java.security.Permission;
import java.util.jar.Manifest;

public class SplashActivity extends Activity implements SplashView{

    TextView mVersionName;
    SplashPresenter mSplashPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        checkMyPermission();
        init();
    }

    private void checkMyPermission() {
        int permission = getPackageManager().checkPermission("android.permission.WRITE_EXTERNAL_STORAGE", getString(R.string.packageName));
        if(permission != PackageManager.PERMISSION_GRANTED)
        {
            Toast.makeText(this, "未获取读取权限",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "已获取读取权限",Toast.LENGTH_SHORT).show();
        }
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
        mVersionName.setText(getString(R.string.version_name) + String.valueOf(versionName));
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
