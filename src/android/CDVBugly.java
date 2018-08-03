package com.jasonz.cordova.bugly;

import org.apache.cordova.BuildConfig;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaArgs;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;

import android.util.Log;
import android.webkit.WebView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Application;

import com.tencent.bugly.crashreport.CrashReport;
import com.tencent.bugly.crashreport.CrashReport.UserStrategy;

public class CDVBugly extends CordovaPlugin {
    public static final String TAG = "Cordova.Plugin.Bugly";
    private String APP_ID;
    private static final String BUGLY_APP_ID = "ANDROID_APPID";
    public static final String ERROR_INVALID_PARAMETERS = "参数格式错误";

    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
        APP_ID = webView.getPreferences().getString(BUGLY_APP_ID,"");
    }

    @Override
    public boolean execute(String action, CordovaArgs args, CallbackContext callbackContext) throws JSONException {

        if(action.equals("initSDK")) {
            return this.initSDK(args, callbackContext);
        } else if (action.equals("enableJSMonitor")){
            return this.enableJSMonitor(args, callbackContext);
        } else if (action.equals("setTagID")){
            return this.setTagID(args, callbackContext);
        } else if (action.equals("setUserID")){
            return this.setUserID(args, callbackContext);
        } else if (action.equals("putUserData")){
            return this.putUserData(args, callbackContext);
        } else if (action.equals("testJavaCrash")){
            return this.testJavaCrash(args, callbackContext);
        } else if (action.equals("testNativeCrash")){
            return this.testNativeCrash(args, callbackContext);
        } else if (action.equals("testANRCrash")){
            return this.testANRCrash(args, callbackContext);
        }

        return false;
    }

    private boolean initSDK(CordovaArgs args, CallbackContext callbackContext) {
        final JSONObject params;

        try {
            params = args.getJSONObject(0);
            //TODO check param format
            UserStrategy strategy = new UserStrategy(this.cordova.getActivity().getApplicationContext());

            if(params.has("channel")) {
                strategy.setAppChannel(params.getString("channel"));
            }
            if(params.has("version")) {
                strategy.setAppVersion(params.getString("version"));
            }
            if(params.has("package")) {
                strategy.setAppPackageName(params.getString("package"));
            }
            if(params.has("delay")) {
                strategy.setAppReportDelay(params.getInt("delay"));
            }
            if(params.has("develop")) {
                CrashReport.setIsDevelopmentDevice(this.cordova.getActivity().getApplicationContext(),params.getBoolean("develop"));
            } else {
                CrashReport.setIsDevelopmentDevice(this.cordova.getActivity().getApplicationContext(), BuildConfig.DEBUG);
            }

            boolean debugModel;

            if(params.has("debug")) {
                debugModel = params.getBoolean("debug");
            } else {
                debugModel = BuildConfig.DEBUG;
            }

            CrashReport.initCrashReport(this.cordova.getActivity().getApplicationContext(),APP_ID,debugModel,strategy);
        } catch (JSONException e) {
            callbackContext.error(ERROR_INVALID_PARAMETERS);
            return true;
        }

        Log.d(TAG, "bugly sdk init success!");
        callbackContext.success();
        return true;
    }

    private boolean enableJSMonitor(CordovaArgs args, CallbackContext callbackContext) {
        this.cordova.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                CrashReport.setJavascriptMonitor((WebView)webView.getView(), true);
            }
        });
        callbackContext.success();
        return true;
    }

    private boolean setTagID(CordovaArgs args, CallbackContext callbackContext) {
        try {
            int id = args.getInt(0);
            CrashReport.setUserSceneTag(this.cordova.getActivity().getApplicationContext(), id);
        } catch (JSONException e) {
            callbackContext.error(ERROR_INVALID_PARAMETERS);
            return true;
        }
        callbackContext.success();
        return true;
    }

     private boolean setUserID(CordovaArgs args, CallbackContext callbackContext) {
        try {
            int id = args.getInt(0);
            CrashReport.setUserID(this.cordova.getActivity().getApplicationContext(), id);
        } catch (JSONException e) {
            callbackContext.error(ERROR_INVALID_PARAMETERS);
            return true;
        }
        callbackContext.success();
        return true;
    }

    private boolean putUserData(CordovaArgs args, CallbackContext callbackContext) {
        try {
            String key = args.getString(0);
            String value = args.getString(1);
            CrashReport.putUserData(this.cordova.getActivity().getApplicationContext(), key, value);
        } catch (JSONException e) {
            callbackContext.error(ERROR_INVALID_PARAMETERS);
            return true;
        }
        callbackContext.success();
        return true;
    }

    private boolean testJavaCrash(CordovaArgs args, CallbackContext callbackContext) {
        this.cordova.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                CrashReport.testJavaCrash();
            }
        });
        callbackContext.success();
        return true;
    }

    private boolean testNativeCrash(CordovaArgs args, CallbackContext callbackContext) {
        this.cordova.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                CrashReport.testNativeCrash();
            }
        });
        callbackContext.success();
        return true;
    }

    private boolean testANRCrash(CordovaArgs args, CallbackContext callbackContext) {

        this.cordova.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                CrashReport.testANRCrash();
            }
        });
        callbackContext.success();
        return true;
    }

}