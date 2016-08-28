package com.melody.commelodymobilesafe.model.impl;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Environment;

import com.melody.commelodymobilesafe.model.SplashModel;

import java.io.File;

/**
 * Created by fengd on 2016/8/28.
 */
public class SplashModelImpl implements SplashModel {
    Context context;

    public SplashModelImpl(Context context) {
        this.context = context;
    }

    @Override
    public boolean checkUpdate() {

        return false;
    }

    @Override
    public String loadVersionName() {
        try {
            String versionName = context.getPackageManager().getPackageInfo("com.melody.commelodymobilesafe", 0).versionName;
            return versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public File downladUpdate() {

        return null;
    }
}
