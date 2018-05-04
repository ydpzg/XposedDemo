package com.dukelight.xposeddemoproject;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static de.robv.android.xposed.XposedHelpers.findClass;

/**
 * hook 微信webview onPageStarted
 */
public class WxH5UrlHookLoadPackage implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
        if (!loadPackageParam.packageName.equals("com.tencent.mm")) {
            return;
        }
        XposedBridge.log("duke:hello->handleLoadPackage");

        String className = "com.tencent.smtt.sdk.SystemWebViewClient";
        String methodName = "onPageStarted";
        XposedBridge.hookAllMethods(findClass(className, loadPackageParam.classLoader), methodName, new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                        super.beforeHookedMethod(param);
                        for (Object arg : param.args) {
                            if (arg != null) {
                                XposedBridge.log("duke:wx->onPageStarted->param:" + arg.toString());
                            }
                        }
                    }

                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        super.afterHookedMethod(param);
                    }
                });

    }
}
