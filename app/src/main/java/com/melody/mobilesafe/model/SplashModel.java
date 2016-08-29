package com.melody.mobilesafe.model;

import com.melody.mobilesafe.Listener.OnUpdateCheckListener;
import com.melody.mobilesafe.Listener.OnUpdateDownloadListener;

import java.io.File;

/**
 * Created by fengd on 2016/8/28.
 */
public interface SplashModel {
    //    检查更新
    void checkUpdate(OnUpdateCheckListener onUpdateCheckListener);

    //    获取版本名称
    String loadVersionName();

    //    下载更新
    void downladUpdate(OnUpdateDownloadListener onUpdateDownloadListener);
}
