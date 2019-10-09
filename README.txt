1.Build Setup
通过命令安装依赖的jar
npm install

serve with hot reload at localhost:8080 运行项目
npm run dev

build for production with minification
npm run build

build for production and view the bundle analyzer report
npm run build --report

2.项目结构
    build: 目录下面放的都是webpack的配置文件
    config: 目录下面放的都是webpack运行所需要的环境参数
    dist: 目录下面放的都是Vue项目打包后的目录
    node_modules: Vue的相关jar都在这里

3.项目说明
    Vue项目一般要关注package.json这个文件,这里面定义了该项目所用到的jar,类似于pom.xml文件.
    1.这是Vue项目,是后台系统的前端页面.
    2.拿到项目后,需要下载项目的依赖jar,执行命令:npm install.
    3.然后通过:npm run dev 命令运行项目.

4.修改host文件里面的域名和端口映射关系,以后通过域名来进行访问项目.
    127.0.0.1	localhost
    127.0.0.1	eurekaSlave1
    127.0.0.1	eurekaSlave2
    127.0.0.1	eurekaSlave3
    127.0.0.1	leyou.com
    127.0.0.1	manage.leyou.com
    127.0.0.1	api.leyou.com
    127.0.0.1	image.leyou.com

5.什么是nginx?nginx属于反向代理
    正向代理:代理用户
    反向代理:代理服务器

6.zuul nginx区别?
    zuul和nginx有什么区别?
    zuul是在程序内部进行负载均衡
    nginx是在程序外部进行的负载均衡


7.修改jenkins端口
    nginx的默认端口是80,可以省略不写,但是会和已经安装好的jenkins冲突,所以需要修改Jenkins的port.
    修改jenkins端口的方法：
    先关闭jenkins;
    命令行下修改端口：sudo defaults write /Library/Preferences/org.jenkins-ci httpPort 7071
    启动jenkins： sudo launchctl load /Library/LaunchDaemons/org.jenkins-ci.plist
    停止jenkins：sudo launchctl unload /Library/LaunchDaemons/org.jenkins-ci.plist

8.Mac电脑上该项目使用的nginx.conf配置文件
cat /usr/local/etc/nginx/nginx.conf
这是Mac上的nginx配置文件:Mac_nginx.conf

9.图片上传服务需要绕过网关
    图片上传服务过慢,效率很低,需要绕过网关.
    https://www.bilibili.com/video/av54216146/?p=95

10.不同模块使用的端口
    leyou-gateway:10010
    item-microService:8081
    leyou-register:10086
    upload-service:8082

