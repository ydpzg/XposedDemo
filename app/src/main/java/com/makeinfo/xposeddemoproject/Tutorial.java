package com.makeinfo.xposeddemoproject;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * Created by Basil on 8/12/2015.
 */
public class Tutorial implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
        if (!loadPackageParam.packageName.equals("com.amap.map3d.demo")) {
            return;
        }
        XposedBridge.log("hello->handleLoadPackage");
//        final Class<?> classes = XposedHelpers.findClass("com.amap.map3d.demo.location.LocationNetworkActivity", loadPackageParam.classLoader);
        XposedHelpers.findAndHookMethod("android.location.Location",
                loadPackageParam.classLoader, "getLatitude",
                new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                        super.beforeHookedMethod(param);
//                        Context context = (Context)param.thisObject;
//                        Toast.makeText(context, "11111", Toast.LENGTH_SHORT).show();
                        XposedBridge.log("hello->weekreport:before getLatitude");
//                        XposedBridge.log("hello->weekreport:before " + param.getResult());

                    }

                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        super.afterHookedMethod(param);
//                        param.setResult(true);
                        XposedBridge.log("hello->weekreport:after getLatitude");
//                        XposedBridge.log("hello->weekreport:after " + param.getResult());
//                        Field field = XposedHelpers.findField(classes, "aMapLocation");
//                        AMapLocation aMapLocation = (AMapLocation) field.get(param.thisObject);
//                        XposedBridge.log("hello->weekreport:aMapLocation " + aMapLocation);
                    }
                });

//        XposedHelpers.findAndHookConstructor("com.amap.map3d.demo.location.LocationNetworkActivity$Apple",
//                loadPackageParam.classLoader, new XC_MethodHook() {
//                    @Override
//                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                        super.beforeHookedMethod(param);
////                        Context context = (Context)param.thisObject;
////                        Toast.makeText(context, "11111", Toast.LENGTH_SHORT).show();
//                        XposedBridge.log("hello->weekreport:before apple");
//                    }
//
//                    @Override
//                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                        super.afterHookedMethod(param);
////                        param.setResult(true);
//                        XposedBridge.log("hello->weekreport:after apple");
//                    }
//                });
//        XposedHelpers.findAndHookMethod("org.json.JSONObject", loadPackageParam.classLoader, "put", String.class, boolean.class, new XC_MethodHook() {
//                    @Override
//                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                        super.beforeHookedMethod(param);
//                        XposedBridge.log("hello->weekreport:before put=" + param.args[0] + "," + param.args[1]);
//                        if (param.args[0].equals("inScope")) {
//                            param.args[1] = false;
////                            param.setResult(false);
//                        }
//                    }
//
//                    @Override
//                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                        super.afterHookedMethod(param);
//                        XposedBridge.log("hello->weekreport:after put=" + param.args[0] + "," + param.args[1]);
//                    }
//                });
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

//        try {
//            XposedHelpers.findAndHookConstructor("com.baidu.location.BDLocation", loadPackageParam.classLoader,
//                    String.class, new XC_MethodHook() {
//                        @Override
//                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                            super.beforeHookedMethod(param);
//                            String str = (String) param.args[0];
//                            XposedBridge.log("hello->weekreport:before str=" + str);
//                        }
//
//                        @Override
//                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                            super.afterHookedMethod(param);
//                        }
//                    });
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        try {
//            XposedHelpers.findAndHookConstructor("com.baidu.location.BDLocation", loadPackageParam.classLoader,
//                    BDLocation.class, new XC_MethodHook() {
//                        @Override
//                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                            super.beforeHookedMethod(param);
//                            BDLocation str = (BDLocation) param.args[0];
//                            XposedBridge.log("hello->weekreport:before str=" + str.getLongitude());
//                        }
//
//                        @Override
//                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                            super.afterHookedMethod(param);
//                        }
//                    });
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}
