package com.dukelight.xposeddemoproject;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static de.robv.android.xposed.XposedHelpers.findClass;

/**
 * hook 微信webview loadurl
 */
public class WxLoadurlHookLoadPackage implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
        if (!loadPackageParam.packageName.equals("com.tencent.mm")) {
            return;
        }
        XposedBridge.log("duke:hello->handleLoadPackage");

        // 通过hook WebView下的loadUrl达到目的
        XposedHelpers.findAndHookMethod("com.tencent.smtt.sdk.WebView", loadPackageParam.classLoader, "loadUrl", String.class, new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                super.beforeHookedMethod(param);
                XposedBridge.log("duke:loadUrl3");
                param.args[0] = "http://www.baidu.com";
                for (Object arg : param.args) {
                    XposedBridge.log("duke:wx->loadUrl->param:" + arg.toString());
                }
            }

            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);
            }
        });
        // 通过hook SystemWebViewClient下的onPageStarted达到目的
        XposedBridge.hookAllMethods(
                findClass("com.tencent.smtt.sdk.SystemWebViewClient", loadPackageParam.classLoader),
                "onPageStarted", new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                        super.beforeHookedMethod(param);
                        XposedBridge.log("duke:onPageStarted=beforeHookedMethod");
                        for (Object arg : param.args) {
                            XposedBridge.log("duke:wx->onPageStarted->param:" + arg.toString());
                        }
                    }

                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        super.afterHookedMethod(param);
                        XposedBridge.log("duke:onPageStarted=afterHookedMethod");
                        for (Object arg : param.args) {
                            XposedBridge.log("duke:wx->onPageStarted->param:" + arg.toString());
                        }
                    }
                });

    }
}
