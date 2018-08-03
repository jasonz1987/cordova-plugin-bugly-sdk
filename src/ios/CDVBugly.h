#import <Cordova/CDVPlugin.h>

@interface CDVBugly : CDVPlugin {

}
- (void)initSDK:(CDVInvokedUrlCommand*)command;
- (void)setTagID:(CDVInvokedUrlCommand*)command;
- (void)setUserID:(CDVInvokedUrlCommand*)command;
- (void)putUserData:(CDVInvokedUrlCommand*)command;
- (void)testOCCrash:(CDVInvokedUrlCommand*)command;

@end
