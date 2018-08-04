# cordova-plugin-bugly-sdk



cordova plugin for [Tencent Bugly SDK](https://bugly.qq.com/)

you can catch app crash reprot (like android anr, oc expection,js erro etc.) by this plugin.

more info, visit https://bugly.qq.com/docs/



# Example

[Ionic3 Demo](https://github.com/jasonz1987/ionic-bugly-sdk-demo)



# Docs

## install

```shell
cordova plugin add cordova-plugin-bugly-sdk  --variable ANDROID_APPID=your value --variable IOS_APPID=your value
```



##  methods


**init sdk**

*please notice params difference with each platform*

```javascript
var args = {
    // common
    debug:true,
    channel:"test",
    develop:true,
    version:"1.0",
    // android
    // delay:20000,
    // package:"com.jasonz.bugly.demo",
    // ios
    // device_id: "xxx-xxx",
    // block_monitor_enable: true,
    // block_monitor_timeout: 10000
};

Bugly.initSDK(function(success){
	console.log("初始化成功");
},function(err){
   console.log("初始化失败");
   console.log(err);
},args);

```



**enable js error handler**

```javascript
  Bugly.enableJSMonitor();
```



**set user indentifier**

```javascript
  var id = "jason.z";
  Bugly.setUserID(id);
```



**set tag id(id must be numeric)**

```javascript
  var id = 10086;
  Bugly.setTagID(id);
```



**set user define data**


```javascript
 var data = {
    key:'id',
    value:1
  };

 Bugly.putUserData(id);
```



There also have some methods to test app crash, you can see them in demo project code.




# Donate

If this project help you reduce time to develop, you can give me a cup of coffee :)



[![paypal](https://img.shields.io/badge/Donate-PayPal-green.svg)](https://www.paypal.me/jasonz1987/6.66)







