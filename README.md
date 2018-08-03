## 项目说明	

腾讯[Buyly SDK](https://bugly.qq.com/)的Cordova插件。



## 项目演示

[Demo](https://github.com/jasonz1987/ionic-bugly-sdk-demo)



## 项目使用

### 安装



```shell
cordova plugin add cordova-plugin-bugly-sdk  --variable ANDROID_APPID=your value --variable IOS_APPID=your value
```



### 调用



**初始化SDK**

*注意不同平台下的参数配置可能有差异*



```javascript
var args = {
    // 通用配置
    debug:true,
    channel:"test",
    develop:true,
    version:"1.0",
    // 安卓配置
    // delay:20000,
    // package:"com.jasonz.bugly.demo",
    // IOS配置
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



**开启Javascript异常捕获（仅安卓）**



```javascript

  Bugly.enableJSMonitor();

```



**设置用户ID**



```javascript
  var id = "jason.z";
  Bugly.setUserID(id);

```



**设置TagID(id必须是数字)**



```javascript
  var id = 10086;
  Bugly.setTagID(id);

```



**设置用户自定义数据**




```javascript

  var data = {
    key:'id',
    value:1
  };

 Bugly.putUserData(id);

```



还有一些测试闪退的方法，具体参考demo里的代码。




# 赞赏

如果我的项目对你有帮助，欢迎赞赏。

![donate.png](donate.png)





