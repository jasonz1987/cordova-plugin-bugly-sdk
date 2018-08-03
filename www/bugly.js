var exec = require('cordova/exec');

exports.initSDK = function(success, error, arg0) {
    exec(success, error, "Bugly", "initSDK", [arg0]);
};

exports.enableJSMonitor = function() {
    exec(null, null, "Bugly", "enableJSMonitor", []);
};

exports.setTagID = function(arg0) {
    exec(null, null, "Bugly", "setTagID", [arg0]);
};

exports.setUserID = function(arg0) {
    exec(null, null, "Bugly", "setUserID", [arg0]);
};

exports.putUserData = function(arg0) {
    exec(null, null, "Bugly", "putUserData", [arg0]);
};

exports.testJavaCrash = function() {
    exec(null, null, "Bugly", "testJavaCrash", []);
};

exports.testNativeCrash = function() {
    exec(null, null, "Bugly", "testNativeCrash", []);
};

exports.testANRCrash = function() {
    exec(null, null, "Bugly", "testANRCrash", []);
};

exports.testOCCrash = function() {
    exec(null, null, "Bugly", "testOCCrash", []);
};

