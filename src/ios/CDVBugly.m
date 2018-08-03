
#import <Cordova/CDVViewController.h>
#import <Bugly/Bugly.h>
#import "CDVBugly.h"

@implementation CDVBugly

- (void)pluginInitialize
{
    
}

- (void)initSDK:(CDVInvokedUrlCommand*)command
{
    CDVPluginResult* pluginResult = nil;

    NSDictionary *params = [command.arguments objectAtIndex:0];
    
    if (!params)
    {
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:@"参数格式错误"];
        [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
        return ;
    }
    
    BuglyConfig * config = [[BuglyConfig alloc] init];
    
    if([params objectForKey:@"debug"]) {
        config.debugMode = [[params objectForKey:@"debug"] boolValue];
    }
    
    if([params objectForKey:@"channel"]) {
        config.channel = [params objectForKey:@"channel"];
    }
    
    if([params objectForKey:@"version"]) {
        config.version = [params objectForKey:@"version"];
    }
    
    if([params objectForKey:@"device_id"]) {
        config.deviceIdentifier = [params objectForKey:@"device_id"];
    }
    
    if([params objectForKey:@"block_monitor_enable"]) {
        config.blockMonitorEnable = [[params objectForKey:@"block_monitor_enable"] boolValue];
    }
    
    if([params objectForKey:@"block_monitor_timeout"]) {
        config.blockMonitorTimeout = [[params objectForKey:@"block_monitor_timeout"] intValue];
    }
    
    config.reportLogLevel = BuglyLogLevelInfo;
    NSDictionary * dict = [[NSBundle mainBundle] infoDictionary];
    NSString * appid = [dict objectForKey:@"BuglyAppIDString"];
    
    if([params objectForKey:@"develop"]) {
        [Bugly startWithAppId:appid developmentDevice:[[params objectForKey:@"develop"] boolValue] config:config];
    } else {
        [Bugly startWithAppId:appid config:config];
    }
    
    pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:@""];
    
    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}

- (void)setTagID:(CDVInvokedUrlCommand *)command
{
    CDVPluginResult* pluginResult = nil;

    int tagID = [[command.arguments objectAtIndex:0] intValue];
    [Bugly setTag:tagID];
    
    pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:@""];
    
    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}

- (void)setUserID:(CDVInvokedUrlCommand *)command
{
    CDVPluginResult* pluginResult = nil;

    NSString *userID = [command.arguments objectAtIndex:0];
    [Bugly setUserIdentifier:userID];
    
    pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:@""];
    
    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}

- (void)putUserData:(CDVInvokedUrlCommand*)command
{
    CDVPluginResult* pluginResult = nil;
    NSDictionary *params = [command.arguments objectAtIndex:0];
    
    if (!params)
    {
         pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:@"参数格式错误"];
        [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
        return ;
    }
    
    NSString *key = [params objectForKey:@"key"];
    NSString *value = [params objectForKey:@"value"];
    
    if(key == nil || value == nil) {
        CDVPluginResult *commandResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:@"参数格式错误"];
        [self.commandDelegate sendPluginResult:commandResult callbackId:command.callbackId];
        return ;
    }
    
    [Bugly setUserValue:value forKey:key];
    
    pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:@""];
    
    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
    
}

- (void)testOCCrash:(CDVInvokedUrlCommand*)command
{
    CDVPluginResult* pluginResult = nil;
    NSString* echo = [command.arguments objectAtIndex:0];
    
    [self testNSException];
    [self testSignalException];
    pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:echo];
    
    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}

- (void)testNSException {
    NSLog(@"it will throw an NSException ");
    NSArray * array = @[];
    NSLog(@"the element is %@", array[1]);
}

- (void)testSignalException {
    NSLog(@"test signal exception");
    NSString * null = nil;
    NSLog(@"print the nil string %s", [null UTF8String]);
}

@end
