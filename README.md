# CrashKit

<img src="https://github.com/ydstar/CrashKit/blob/main/preview/show.gif" alt="动图演示效果" width="250px">


因为动图是用模拟器制作的,如果是真机的话,也是支持便签打开,微信一键分享给小伙伴的
## 导入方式

仅支持`AndroidX`
```
dependencies {
     implementation 'com.android.ydkit:crash-kit:1.0.0'
}
```

## 使用方法
在application中初始化
```java
CrashMgr.init()
```

看crash日志列表
```java
startActivity(Intent(this, CrashLogActivity::class.java))
```

