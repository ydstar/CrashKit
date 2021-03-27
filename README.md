# CrashKit

<img src="https://github.com/ydstar/CrashKit/blob/main/preview/show.gif" alt="动图演示效果" width="250px">


因为动图是用模拟器制作的,如果是真机的话,也是支持便签打开,微信一键分享给小伙伴的
## 导入方式

仅支持`AndroidX`
```
dependencies {
     implementation 'com.android.ydkit:crash-kit:1.0.1'
}
```

## 使用方法
在application中初始化
```java
CrashKitManager.init()
```

看crash日志列表
```java
startActivity(Intent(this, CrashLogActivity::class.java))
```

## License
```text
Copyright [2021] [ydStar]

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```