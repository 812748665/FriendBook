package com.youshibi.app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.youshibi.app.pref.AppConfig;
import com.youshibi.app.presentation.book.BookDetailActivity;


/**
 * 作者: 赵成柱 on 2016/7/13.
 * app路由，界面跳转帮助类，所有的界面跳转通过此类进行跳转,包括组件交互
 */
public class AppNavigator {


    /**
     * 跳转到当前应用设置界面
     */
    public static void showAppDetailSetting(Context context) {
        Intent localIntent = new Intent();
        localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= 9) {
            localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            localIntent.setData(Uri.fromParts("package", context.getPackageName(), null));
        } else if (Build.VERSION.SDK_INT <= 8) {
            localIntent.setAction(Intent.ACTION_VIEW);
            localIntent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
            localIntent.putExtra("com.android.settings.ApplicationPkgName", context.getPackageName());
        }
        context.startActivity(localIntent);
    }


    /**
     * @param webView
     */
    @SuppressLint("SetJavaScriptEnabled")
    public static void initWebViewSettings(WebView webView) {
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.setDefaultFontSize(15);
        //设置缓存
        webSettings.setDomStorageEnabled(true);
        webSettings.setDatabaseEnabled(true);

        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webSettings.setAppCacheEnabled(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setAppCachePath(AppConfig.WEB_CACHE_PATH);
    }

    public static void showBookDetailActivity(Context context, String bookId) {
        context.startActivity(BookDetailActivity.newIntent(context, bookId));
    }


}
