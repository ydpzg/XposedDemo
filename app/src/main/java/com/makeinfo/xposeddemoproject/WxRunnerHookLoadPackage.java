package com.makeinfo.xposeddemoproject;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static de.robv.android.xposed.XposedHelpers.findClass;

public class WxRunnerHookLoadPackage implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
        if (loadPackageParam.packageName.equals("com.tencent.mm") || loadPackageParam.packageName.equals("com.eg.android.AlipayGphone")) {


            XposedBridge.hookAllMethods(
                    findClass("android.hardware.SystemSensorManager$SensorEventQueue", loadPackageParam.classLoader),
                    "dispatchSensorEvent", new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                            super.beforeHookedMethod(param);
                            XposedBridge.log("duke:dispatchSensorEvent");
                            for (Object arg : param.args) {
                                XposedBridge.log("duke:wx->dispatchSensorEvent->param:" + arg.toString());
                            }
                            ((float[]) param.args[1])[0] = ((float[]) param.args[1])[0] * 100;
                        }

                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            super.afterHookedMethod(param);
                        }
                    });
        }
    }
}
