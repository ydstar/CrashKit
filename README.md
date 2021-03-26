# CrashKit

<img src="https://github.com/ydstar/CrashKit/blob/main/preview/show.gif" alt="动图演示效果" width="250px">

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

