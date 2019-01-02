# springproject-AutoScripts
Final version v1.0, upload for checking

## 1. 因为前端使用了在线css，js文档，使用该项目请确保联网。

## 2. 数据库相关：

#### 1. 数据库类型为mysql，请先建立好新的schema，然后进入该schema执行建表语句脚本文件，autoscripts.sql 文件

#### 2. 执行程序前需要修改yml文件，在src\main\resources\application.yml作对应修改。

## 3. 文件相关：

#### 1. 默认存储脚本根目录路径为D盘，"D:\scriptStorage", 若确实需要修改，请进入 "src\main\java\com\autoscript\springproject\service\ScriptService.java"，修改pathGenerator方法。

#### 2. 默认存储脚本运行结果根目录路径也为D盘， "D:\result", 若确实需要修改，请进入 "src\main\java\com\autoscript\springproject\web\ScriptController.java"， 修改use方法。


