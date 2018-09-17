# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

#指定代码的压缩级别
-optimizationpasses 5

#包明不混合大小写
-dontusemixedcaseclassnames

#不去忽略非公共的库类
-dontskipnonpubliclibraryclasses

 #优化  不优化输入的类文件
-dontoptimize

 #预校验
-dontpreverify

 #混淆时是否记录日志
-verbose

 # 混淆时所采用的算法
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*

#保护注解
-keepattributes *Annotation*

# 保持哪些类不被混淆
-keep public class * extends android.app.Fragment
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class com.android.vending.licensing.ILicensingService
# protobuf message 不混淆
-keep class com.banda.harvester.message.** {*;}
#如果有引用v4包可以添加下面这行
-keep public class * extends android.support.v4.app.Fragment



#忽略警告
-ignorewarning

##记录生成的日志数据,gradle build时在本项目根目录输出##
#apk 包内所有 class 的内部结构
-dump proguard/class_files.txt
#未混淆的类和成员
-printseeds proguard/seeds.txt
#列出从 apk 中删除的代码
-printusage proguard/unused.txt
#混淆前后的映射
-printmapping proguard/mapping.txt
########记录生成的日志数据，gradle build时 在本项目根目录输出-end######

#如果引用了v4或者v7包
-dontwarn android.support.**

####混淆保护自己项目的部分代码以及引用的第三方jar包library-end####
#保持 native 方法不被混淆
-keepclasseswithmembernames class * {
    native <methods>;
}

#保持自定义控件类不被混淆
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}

#保持自定义控件类不被混淆
-keepclassmembers class * extends android.app.Activity {
   public void *(android.view.View);
}

-keep public class * extends android.view.View {
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
    public void set*(...);
}

#保持 Parcelable 不被混淆
-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}

#保持 Serializable 不被混淆
-keepnames class * implements java.io.Serializable


#保持 Serializable 不被混淆并且enum 类也不被混淆
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    !static !transient <fields>;
    !private <fields>;
    !private <methods>;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

#保持枚举 enum 类不被混淆
-keepclassmembers enum * {
  public static **[] values();
  public static ** valueOf(java.lang.String);
}

-keepclassmembers class * {
    public void *ButtonClicked(android.view.View);
}

#不混淆资源类
-keepclassmembers class **.R$* {
    public static <fields>;
}

#避免混淆泛型 如果混淆报错建议关掉
#-keepattributes Signature

#移除Log类打印各个等级日志的代码，打正式包的时候可以做为禁log使用，这里可以作为禁止log打印的功能使用，另外的一种实现方案是通过BuildConfig.DEBUG的变量来控制
#-assumenosideeffects class android.util.Log {
#    public static *** v(...);
#    public static *** i(...);
#    public static *** d(...);
#    public static *** w(...);
#    public static *** e(...);
#}

#############################################################################################
########################                 以上通用           ##################################
#############################################################################################

#######################     常用第三方模块的混淆选项         ###################################
#gson
#如果用用到Gson解析包的，直接添加下面这几行就能成功混淆，不然会报错。
-keepattributes Signature
# Gson specific classes
-keep class sun.misc.Unsafe { *; }
# Application classes that will be serialized/deserialized over Gson
-keep class com.google.gson.** { *; }
-keep class com.google.gson.stream.** { *; }

#本地libery
-keepattributes Signature,InnerClasses
-keepclasseswithmembers class io.netty.** {
    *;
}
-dontwarn io.netty.**
-dontwarn sun.**

-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}

-keep class com.yinshan.happycash.view.bindcard.model.**{*;}
-dontwarn com.yinshan.happycash.view.bindcard.model.**

-keep class com.yinshan.happycash.view.information.model.**{*;}
-dontwarn com.yinshan.happycash.view.information.model.**

-keep class com.yinshan.happycash.view.liveness.model.**{*;}
-dontwarn com.yinshan.happycash.view.liveness.model.**

-keep class com.yinshan.happycash.view.loan.model.**{*;}
-dontwarn com.yinshan.happycash.view.loan.model.**

-keep class com.yinshan.happycash.view.login.model.**{*;}
-dontwarn com.yinshan.happycash.view.login.model.**

-keep class com.yinshan.happycash.view.main.model.**{*;}
-dontwarn com.yinshan.happycash.view.main.model.**

-keep class com.yinshan.happycash.view.me.model.**{*;}
-dontwarn com.yinshan.happycash.view.me.model.**

# OkHttp3
-dontwarn okhttp3.logging.**
-keep class okhttp3.internal.**{*;}
-dontwarn okio.**
# Retrofit
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
#-keepattributes Signature-keepattributes Exceptions
# RxJava RxAndroid
-dontwarn sun.misc.**
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
    long producerIndex;
    long consumerIndex;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode producerNode;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode consumerNode;
}

#butterknife
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }

-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}

-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}

######引用的其他Module可以直接在app的这个混淆文件里配置

# 如果使用了Gson之类的工具要使被它解析的JavaBean类即实体类不被混淆。
-keep class com.matrix.app.entity.json.** { *; }
-keep class com.matrix.appsdk.network.model.** { *; }

#-libraryjars ../android-gif-drawable-1.2.5/src/main/jniLibs/x86/libpl_droidsonroids_gif.so
#-libraryjars ../android-gif-drawable-1.2.5/src/main/jniLibs/x86_64/libpl_droidsonroids_gif.so
#-libraryjars ../camerakit
-keep class com.flurgle.** { *;}
-keep interface  com.flurgle.**{*;}
-keep class com.googlecode.**{*;}
-dontwarn com.googlecode.**
-keep class java.io.**{*;}
-keep class java.nio.**{*;}
-keep class com.coremedia.**{*;}
-dontwarn com.flurgle.**
-dontwarn com.appsflyer.**
#####混淆保护自己项目的部分代码以及引用的第三方jar包library#######
#如果在当前的application module或者依赖的library module中使用了第三方的库，并不需要显式添加规则
#-libraryjars xxx
#添加了反而有可能在打包的时候遭遇同一个jar多次被指定的错误，一般只需要添加忽略警告和保持某些class不被混淆的声明。
#以libaray的形式引用了开源项目,如果不想混淆 keep 掉，在引入的module的build.gradle中设置minifyEnabled=false
-keep class com.nineoldandroids.** { *; }
-keep interface com.nineoldandroids.** { *; }
-dontwarn com.nineoldandroids.**

#********************************************************
# v7 support lib
#-dontwarn android.support.v7.**
#-keep class android.support.v7.** { *; }
#-keep interface android.support.v7.** { *; }
##netty
#-keep class io.netty.**{*;}
#-keep interface io.netty.**{*;}
#-dontwarn io.netty.**
#
##appsflayer
#-keep class com.appsflyer.**{*;}
#-keep interface com.appsflyer.**{*;}
#-dontwarn com.appsflyer.**
#
## okhttp
#-dontwarn rx.**
#-dontwarn okio.**
#-dontwarn com.squareup.okhttp.**
#-keep class com.squareup.okhttp.** { *; }
#-keep interface com.squareup.okhttp.** { *; }
#
## for RxJava:
#-dontwarn sun.misc.Unsafe
#-dontnote sun.misc.Unsafe
#
## okhttp3
#-keep class okhttp3.** { *; }
#-keep interface okhttp3.** { *; }
#-dontwarn okhttp3.**
#-dontnote okhttp3.**
#
#-dontwarn retrofit.**
#-dontwarn retrofit.appengine.UrlFetchClient
#-keep class retrofit.** { *; }
#-keepclasseswithmembers class * {
#    @retrofit.http.* <methods>;
#}
#
##rxbus
#-keepattributes *Annotation*
#-keepclassmembers class ** {
#    @com.hwangjr.rxbus.annotation.Subscribe public *;
#    @com.hwangjr.rxbus.annotation.Produce public *;
#}
#
## Retrofit 2.X
### https://square.github.io/retrofit/ ##
#-dontwarn retrofit2.**
#-keep class retrofit2.** { *; }
#-keepattributes Signature
#-keepattributes Exceptions
#
#-keepclasseswithmembers class * {
#    @retrofit2.http.* <methods>;
#}
#
## for image loader: glide
#-keep public class * implements com.bumptech.glide.module.GlideModule


-keep class com.flurgle.** {*;}
-keep interface com.flurgle.** {*;}
#
-keep class com.googlecode.** {*;}
-keep interface com.googlecode.** {*;}
#
-keep class com.google.** {*;}
-keep interface com.google.** {*;}
##
-keepclasseswithmembers class com.banda.program.banda_android.harvester.** {*;}
-keep interface com.banda.program.banda_android.harvester.** {*;}
#
-keep class com.appsflyer.** {*;}
-keep interface com.appsflyer.** {*;}

-keep class com.banda.program.banda_android.view.camera.** {*;}
-keep interface com.banda.program.banda_android.view.camera.** {*;}
#
-keep class java.** {*;}
-keep class com.coremedia.** {*;}

#ali push
-keepclasseswithmembers class ** {
    native <methods>;
}
-keepattributes Signature
-keep class sun.misc.Unsafe { *; }
-keep class com.taobao.** {*;}
-keep class com.alibaba.** {*;}
-keep class com.alipay.** {*;}
-keep class com.ut.** {*;}
-keep class com.ta.** {*;}
-keep class anet.**{*;}
-keep class anetwork.**{*;}
-keep class org.android.spdy.**{*;}
-keep class org.android.agoo.**{*;}
-keep class android.os.**{*;}
-dontwarn com.taobao.**
-dontwarn com.alibaba.**
-dontwarn com.alipay.**
-dontwarn anet.**
-dontwarn org.android.spdy.**
-dontwarn org.android.agoo.**
-dontwarn anetwork.**
-dontwarn com.ut.**
-dontwarn com.ta.**


#baidu sdk
-dontwarn com.baidu.**
-keep class com.baidu.** { *; }

#GreenDao
-keep class com.banda.program.banda_android.dao.** {*;}
-keep class org.greenrobot.greendao.** {*;}

#活体检测
-keep class com.oliveapp.camerasdk.** {*;}


# Keep GSON stuff
-keep class com.google.gson.** { *; }

#gson
-keep class sun.misc.Unsafe { *; }
-keep class com.google.gson.stream.* { *; }
-keep class com.google.gson.examples.android.model.* { *; }
-keep class com.google.gson.* { *;}


#不混淆org.apache.http.legacy.jar
-dontwarn android.net.compatibility.**
-dontwarn android.net.http.**
-dontwarn com.android.internal.http.multipart.**
-dontwarn org.apache.commons.**
-dontwarn org.apache.http.**
-keep class org.apache.http.**{*;}
-keep class android.net.compatibility.**{*;}
-keep class android.net.http.**{*;}
-keep class com.android.internal.http.multipart.**{*;}
-keep class org.apache.commons.**{*;}


# Keep Retrofit
-keep class retrofit.** { *; }
-keepclasseswithmembers class * {
    @retrofit.** *;
}
-keepclassmembers class * {
    @retrofit.** *;
}

-keepclassmembers class **.R$* {
  public static <fields>;
}


-keepclassmembers class * extends android.webkit.WebChromeClient{
		public void openFileChooser(...);
}


# easemob 3.x
-keep class com.hyphenate.** {*;}
-dontwarn  com.hyphenate.**


#greendao3.2.0,此是针对3.2.0，如果是之前的，可能需要更换下包名
-keep class org.greenrobot.greendao.**{*;}
-keepclassmembers class * extends org.greenrobot.greendao.AbstractDao {
public static java.lang.String TABLENAME;
}
-keep class **$Properties






#社保SDK
-dontoptimize
-dontusemixedcaseclassnames
-verbose

-dontskipnonpubliclibraryclasses
-dontskipnonpubliclibraryclassmembers
-dontwarn dalvik.**
-dontwarn com.tencent.smtt.** #-overloadaggressively
#Addidional for x5.sdk classes for apps
-keep class com.tencent.smtt.export.external.**{
*;
}
-keep class com.tencent.tbs.video.interfaces.IUserStateChangedListener {
*;
}
-keep class com.tencent.smtt.sdk.CacheManager { public *;
}
-keep class com.tencent.smtt.sdk.CookieManager { public *;
}
-keep class com.tencent.smtt.sdk.WebHistoryItem { public *;
}
-keep class com.tencent.smtt.sdk.WebViewDatabase { public *;
}
-keep class com.tencent.smtt.sdk.WebBackForwardList { public *;
}
-keep public class com.tencent.smtt.sdk.WebView { public <fields>;
public <methods>;
}
-keep public class com.tencent.smtt.sdk.WebView$HitTestResult { public static final <fields>;
public java.lang.String getExtra(); public int getType();
}
-keep public class com.tencent.smtt.sdk.WebView$WebViewTransport { public <methods>;
}
-keep public class com.tencent.smtt.sdk.WebView$PictureListener { public <fields>;
public <methods>;
}
-keepattributes InnerClasses
-keep public enum com.tencent.smtt.sdk.WebSettings$** {
*;
}
-keep public enum com.tencent.smtt.sdk.QbSdk$** {
*;
}
-keep public class com.tencent.smtt.sdk.WebSettings { public *;
}
-keepattributes Signature

-keep public class com.tencent.smtt.sdk.ValueCallback { public <fields>;
public <methods>;
}
-keep public class com.tencent.smtt.sdk.WebViewClient { public <fields>;
public <methods>;
}
-keep public class com.tencent.smtt.sdk.DownloadListener { public <fields>;
public <methods>;
}
-keep public class com.tencent.smtt.sdk.WebChromeClient { public <fields>;
public <methods>;
}
-keep public class com.tencent.smtt.sdk.WebChromeClient$FileChooserParams { public <fields>;
public <methods>;
}
-keep class com.tencent.smtt.sdk.SystemWebChromeClient{ public *;
}
#1、extension interfaces should be apparent
-keep public class com.tencent.smtt.export.external.extension.interfaces.* { public protected *;
}
#2、interfaces should be apparent
-keep public class com.tencent.smtt.export.external.interfaces.* { public protected *;
}
-keep public class com.tencent.smtt.sdk.WebViewCallbackClient { public protected *;
}
-keep public class com.tencent.smtt.sdk.WebStorage$QuotaUpdater { public <fields>;
public <methods>;
}
-keep public class com.tencent.smtt.sdk.WebIconDatabase { public <fields>;
public <methods>;
}
-keep public class com.tencent.smtt.sdk.WebStorage { public <fields>;
public <methods>;
}
-keep public class com.tencent.smtt.sdk.DownloadListener { public <fields>;
public <methods>;
}
-keep public class com.tencent.smtt.sdk.QbSdk { public <fields>;
public <methods>;
}

-keep public class com.tencent.smtt.sdk.QbSdk$PreInitCallback { public <fields>;
public <methods>;
}
-keep public class com.tencent.smtt.sdk.CookieSyncManager { public <fields>;
public <methods>;
}
-keep public class com.tencent.smtt.sdk.Tbs* { public <fields>;
public <methods>;
}
-keep public class com.tencent.smtt.utils.LogFileUtils { public <fields>;
public <methods>;
}
-keep public class com.tencent.smtt.utils.TbsLog { public <fields>;
public <methods>;
}
-keep public class com.tencent.smtt.utils.TbsLogClient { public <fields>;
public <methods>;
}
-keep public class com.tencent.smtt.sdk.CookieSyncManager { public <fields>;
public <methods>;
}
#Added for game demos
-keep public class com.tencent.smtt.sdk.TBSGamePlayer { public <fields>;
public <methods>;
}
-keep public class com.tencent.smtt.sdk.TBSGamePlayerClient* { public <fields>;
public <methods>;
}
-keep public class com.tencent.smtt.sdk.TBSGamePlayerClientExtension { public <fields>;
public <methods>;
}
-keep public class com.tencent.smtt.sdk.TBSGamePlayerService* { public <fields>;
public <methods>;
}
-keep public class com.tencent.smtt.utils.Apn { public <fields>;
public <methods>;
}
-keep class com.tencent.smtt.** {
*;
}
#end
-keep public class com.tencent.smtt.export.external.extension.proxy.ProxyWebViewClientExtension {
public <fields>; public <methods>;
}
-keep class MTT.ThirdAppInfoNew {
*;
}
-keep class com.tencent.mtt.MttTraceEvent {
*;
}
#Game related
-keep public class com.tencent.smtt.gamesdk.* { public protected *;
}
-keep public class com.tencent.smtt.sdk.TBSGameBooter { public <fields>;
public <methods>;
}
-keep public class com.tencent.smtt.sdk.TBSGameBaseActivity { public protected *;
}
-keep public class com.tencent.smtt.sdk.TBSGameBaseActivityProxy { public protected *;
}
-keep public class com.tencent.smtt.gamesdk.internal.TBSGameServiceClient { public *;
}
#下⽅方是android平台⾃自带的排除项，这⾥里里不不要动
-keep public class * extends android.app.Activity{ public <fields>;
public <methods>;
}
-keep public class * extends android.app.Application{ public <fields>;
public <methods>;
}
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keepclassmembers enum * { public static **[] values();
public static ** valueOf(java.lang.String);
}
-keepclasseswithmembers class * {
public <init>(android.content.Context, android.util.AttributeSet);
}
-keepclasseswithmembers class * {
public <init>(android.content.Context, android.util.AttributeSet, int);
}
-keepattributes *Annotation*
-keepclasseswithmembernames class *{ native <methods>;
}
-keep class * implements android.os.Parcelable { public static final android.os.Parcelable$Creator *;
}
#下⽅方是共性的排除项⽬目
#⽅方法名中含有“JNI”字符的，认定是Java Native Interface⽅方法，⾃自动排除
#⽅方法名中含有“JRI”字符的，认定是Java Reflection Interface⽅方法，⾃自动排除
-keepclasseswithmembers class * {
... *JNI*(...);
}
-keepclasseswithmembernames class * {
... *JRI*(...);
}
-keep class **JNI* {*;}
-keep class com.alibaba.fastjson.** { *; }
-dontwarn com.alibaba.fastjson.**

