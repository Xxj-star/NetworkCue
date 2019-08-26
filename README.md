# NetworkCue
主要提供了简单易用强大的Android网络监听库，绑定activity生命周期监听，跟QQ微信一样，代码简洁、解耦性高
## 要添加说明
  使用该工具条件需要项目SDK27版本以上
  27以下需要添加依赖  
  implementation "android.arch.lifecycle:livedata:$LIFECYCLE_VERSION"


# 功能

动态加载监听  
支持监听2G、3G、4G、WiFi、未知网络  
activity启动时候自动注册监听  activity停用时候自动注销监听

# 使用方法：
```
NetworkLiveData.get(getActivity()).observe(this, new Observer() {
            @Override
            public void onChanged(@Nullable NetType netType) {
                switch (netType) {
                    case NET_UNKNOW:
                        Toast.makeText(getActivity(), "未知网络", Toast.LENGTH_LONG).show();
                        Log.e("uuu", "---MainActivity---未知网络");
                        break;
                    case NET_4G:
                        Toast.makeText(getActivity(), "4G", Toast.LENGTH_LONG).show();
                        Log.e("uuu", "---MainActivity---4G");
                        break;
                    case NET_3G:
                        Toast.makeText(getActivity(), "3G", Toast.LENGTH_LONG).show();
                        Log.e("uuu", "---MainActivity---3G");
                        break;
                    case NET_2G:
                        //有网络
                        Toast.makeText(getActivity(), "2G", Toast.LENGTH_LONG).show();
                        Log.e("uuu", "---MainActivity---2G");
                        break;
                    case WIFI:
                        Toast.makeText(getActivity(), "WIFI", Toast.LENGTH_LONG).show();
                        Log.e("uuu", "---MainActivity---WIFI");
                        break;
                    case NOME:
                        Toast.makeText(getActivity(), "没网络", Toast.LENGTH_LONG).show();
                        Log.e("uuu", "---MainActivity---没网络");
                        //没有网络，提示用户跳转到设置
                        break;
                    default:
                        break;
                }
            }
        });
```

版本说明

# 当前版本

[![release](https://img.shields.io/badge/release-v1.0.2-orange.svg)](https://github.com/xuxinjiang/NetworkCue-/blob/master/update.md)

## build.gradle设置
```
dependencies {
implementation 'com.github.xuxinjiang:NetworkCue:1.0.2'
}
```
# 演示（请star支持）

![image text](https://github.com/xuxinjiang/NetworkCue-/blob/master/gif/network.gif)

# 联系方式

本群旨在为使用我github项目的人提供方便，如果遇到问题欢迎在群里提问。

### 欢迎加入QQ交流群（Q群574181465）

![image text](https://github.com/xuxinjiang/NetworkCue-/blob/master/gif/xxjqq.png)



