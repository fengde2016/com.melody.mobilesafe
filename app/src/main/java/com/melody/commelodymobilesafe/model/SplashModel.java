package com.melody.commelodymobilesafe.model;

import java.io.File;

/**
 * Created by fengd on 2016/8/28.
 */
public interface SplashModel {
    //    检查更新
    boolean checkUpdate();

    //    获取版本名称
    String loadVersionName();

    //    下载更新
    File downladUpdate();
}
