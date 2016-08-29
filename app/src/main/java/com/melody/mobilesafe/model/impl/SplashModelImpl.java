package com.melody.mobilesafe.model.impl;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.melody.mobilesafe.Listener.OnUpdateCheckListener;
import com.melody.mobilesafe.Listener.OnUpdateDownloadListener;
import com.melody.mobilesafe.R;
import com.melody.mobilesafe.model.SplashModel;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.HttpManagerImpl;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;

/**
 * Created by fengd on 2016/8/28.
 */
public class SplashModelImpl implements SplashModel {
    private Context context;
    private int netVersionCode;
    private int localVersionCode;
    public SplashModelImpl(Context context) {
        this.context = context;
    }

    @Override
    public void checkUpdate(final OnUpdateCheckListener onUpdateCheckListener) {

        x.http().get(new RequestParams(context.getString(R.string.updateURL)), new Callback.CommonCallback<JSONObject>() {
            @Override
            public void onSuccess(JSONObject result) {
                Log.i("tag", "success");
                try {
                    netVersionCode = result.getInt("versionCode");
                    localVersionCode = context.getPackageManager().getPackageInfo(context.getString(R.string.packageName),0).versionCode;
                    Log.i("tag", "net=" + netVersionCode);
                    Log.i("tag", "local=" + localVersionCode);
                    if(netVersionCode > localVersionCode){
                        onUpdateCheckListener.onUpdate();
                    }
                } catch (JSONException | PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    @Override
    public String loadVersionName() {
        try {
            return context.getPackageManager().getPackageInfo(context.getString(R.string.packageName), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void downladUpdate(final OnUpdateDownloadListener listener) {
        RequestParams params = new RequestParams("http://192.168.1.102/melody/mobilesafe.apk");
        params.setAutoRename(true);
        params.setConnectTimeout(4000);
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            String downloadPath = Environment.getDownloadCacheDirectory().getAbsolutePath() + "/Download/mobilesafe.apk";
            params.setSaveFilePath(downloadPath);
            x.http().get(params, new Callback.CacheCallback<File>() {
                @Override
                public void onSuccess(File result) {
                    listener.onSuccess();
                }

                @Override
                public void onError(Throwable ex, boolean isOnCallback) {
                    listener.onFailed();
                }

                @Override
                public void onCancelled(CancelledException cex) {

                }

                @Override
                public void onFinished() {

                }

                @Override
                public boolean onCache(File result) {
                    return false;
                }
            });
        }else{
            Toast.makeText(context, "没有sd卡", Toast.LENGTH_SHORT).show();
            listener.onFailed();
        }

    }
}
