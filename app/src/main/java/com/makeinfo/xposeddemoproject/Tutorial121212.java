package com.makeinfo.xposeddemoproject;

import com.baidu.location.BDLocation;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * hook地图
 * Created by Basil on 8/12/2015.
 */
public class Tutorial121212 implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
        if (!loadPackageParam.packageName.equals("com.hgsoft.wk.main")) {
            return;
        }
        XposedBridge.log("hello->handleLoadPackage");
        XposedHelpers.findAndHookMethod("com.hgsoft.cordova.location.BdLocation", loadPackageParam.classLoader, "getLocationWithBD", new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                        super.beforeHookedMethod(param);
                        XposedBridge.log("hello->weekreport:before getLocationWithBD");
                    }

                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        super.afterHookedMethod(param);
                        XposedBridge.log("hello->weekreport:after getLocationWithBD");
                    }
                });
        XposedHelpers.findAndHookMethod("org.json.JSONObject", loadPackageParam.classLoader, "put",
                String.class, boolean.class, new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                        super.beforeHookedMethod(param);
                        XposedBridge.log("hello->weekreport:before put=" + param.args[0] + "," + param.args[1]);
                        if (param.args[0].equals("inScope")) {
                            param.args[1] = false;
                        }
                    }

                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        super.afterHookedMethod(param);
                        XposedBridge.log("hello->weekreport:after put=" + param.args[0] + "," + param.args[1]);
                    }
                });
        XposedHelpers.findAndHookMethod("android.app.Application", loadPackageParam.classLoader, "onCreate", new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                super.beforeHookedMethod(param);
                XposedBridge.log("hello->weekreport:before onCreate");

            }

            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                XposedBridge.log("hello->weekreport:after onCreate");
            }
        });
//        XposedHelpers.findAndHookMethod("com.baidu.location.BDLocation", loadPackageParam.classLoader,
//                "setLongitude", double.class, new XC_MethodHook() {
//                    @Override
//                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                        super.beforeHookedMethod(param);
//                        double lon = (double)param.args[0];
//                        XposedBridge.log("hello->weekreport:before lon=" + lon);
//                    }
//
//                    @Override
//                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                        super.afterHookedMethod(param);
//                    }
//                });

        try {
            XposedHelpers.findAndHookConstructor("com.baidu.location.BDLocation", loadPackageParam.classLoader,
                    String.class, new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                            super.beforeHookedMethod(param);
                            String str = (String) param.args[0];
                            XposedBridge.log("hello->weekreport:before str=" + str);
                        }

                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            super.afterHookedMethod(param);
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            XposedHelpers.findAndHookConstructor("com.baidu.location.BDLocation", loadPackageParam.classLoader,
                    BDLocation.class, new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                            super.beforeHookedMethod(param);
                            BDLocation str = (BDLocation) param.args[0];
                            XposedBridge.log("hello->weekreport:before str=" + str.getLongitude());
                        }

                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            super.afterHookedMethod(param);
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
