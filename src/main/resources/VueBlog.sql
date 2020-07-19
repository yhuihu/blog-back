/*
 Navicat Premium Data Transfer

 Source Server         : 阿里云ces
 Source Server Type    : MySQL
 Source Server Version : 80017
 Source Schema         : VueBlog

 Target Server Type    : MySQL
 Target Server Version : 80017
 File Encoding         : 65001

 Date: 29/08/2019 21:01:09
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for blog
-- ----------------------------
DROP TABLE IF EXISTS `blog`;
CREATE TABLE `blog`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '标题',
  `summary` varchar(400) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '概览',
  `create_date` datetime(0) NOT NULL COMMENT '创建日期',
  `update_date` datetime(0) NOT NULL COMMENT '更新日期',
  `click_count` int(11) NOT NULL COMMENT '点击数',
  `comment_count` int(11) NOT NULL COMMENT '评论数',
  `like_count` int(11) NOT NULL COMMENT '喜欢数',
  `tag` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '博客标签(如1,2,3表示具有三种标签)',
  `blog_image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '博客图片',
  `original_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '原文地址（如果非原创）',
  `type` int(1) UNSIGNED NOT NULL COMMENT '0原创/1转载/2翻译',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `type_id`(`tag`) USING BTREE,
  INDEX `type`(`type`) USING BTREE,
  CONSTRAINT `blog_ibfk_1` FOREIGN KEY (`type`) REFERENCES `type_name` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 39 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of blog
-- ----------------------------
INSERT INTO `blog` VALUES (1, '关于本博客', '简介本博客前后端分离的轻量级个人博客系统。本博客系统由后端服务器系统、后台管理系统、前台系统三个子系统组成。其中，后端服务系统基于SpringBoot，后台管理系统和前台系统均基于Vue。每个子系统的', '2019-07-23 20:13:13', '2019-08-29 10:31:31', 237, 0, 28, '11,13', 'http://yhhu.xyz/5lQGI7TSaeU97bVgYoTMJasr', '', 1);
INSERT INTO `blog` VALUES (10, '图解排序算法(四)之归并排序', '基本思想　　归并排序（MERGE-SORT）是利用归并的思想实现的排序方法，该算法采用经典的分治（divide-and-conquer）策略（分治法将问题分。分而治之!(http://localhos', '2019-07-16 19:51:27', '2019-08-14 11:09:53', 20, 0, 1, '1,10', 'http://yhhu.xyz/6FuV57igPa9A0Iwru922IK8l', 'http://www.cnblogs.com/chengxiao/p/6194356.html', 2);
INSERT INTO `blog` VALUES (20, 'vue不使用vuex时创建响应式全局变量的方法', '问题描述Vuex适合于中大型项目，在小型项目中使用往往是杀鸡用牛刀。官方说明如下:如果您不打算开发大型单页应用，使用Vuex可能是繁琐冗余的。确实是如此——如果您的应用够简单，您最好不要使用Vuex。', '2019-07-23 20:13:13', '2019-08-14 11:14:19', 79, 0, 1, '4,6,12', 'http://yhhu.xyz/nAUpvLNjzkxwks6rvAu9a14i', '', 1);
INSERT INTO `blog` VALUES (33, 'CentOS下docker安装', '前提条件1.Docker运行在CentOS7上，要求系统为64位、系统内核版本为3.10以上。2.Docker运行在CentOS-6.5或更高的版本的CentOS上，要求系统为64位、系统内核版本为2', '2019-08-14 11:02:55', '2019-08-14 11:06:09', 5, 0, 1, '15', NULL, '', 1);
INSERT INTO `blog` VALUES (34, 'SpringCloud学习笔记', 'SpringIPC,AOP。是一个轻量级的JAVAEE开源框架，主要是为了解决企业级开发的复杂度问题，复杂度-耦合度SpringBoot新一代JAVAEE开发标准，开箱即用的特性（不需要过多配置）微服', '2019-08-14 11:07:58', '2019-08-26 15:19:44', 23, 5, 3, '15', NULL, '', 1);
INSERT INTO `blog` VALUES (35, '项目从打包到部署', '说明本文以本网站作为案例进行说明SpringBoot后端系统的打包以及部署-打包项目成jar包IDEA操作!成功标志![微信图片_2019082', '2019-08-26 16:47:58', '2019-08-26 19:30:06', 10, 1, 1, '11', NULL, '', 1);
INSERT INTO `blog` VALUES (36, 'Java多线程', '一、Java多线程实现的几种方式1.继承Thread类创建线程类publicclassstudy{//1.定义一个继承Thread的子类publicstaticclasstestThreadexten', '2019-08-27 10:30:03', '2019-08-27 17:34:41', 30, 0, 1, '1', NULL, '', 1);
INSERT INTO `blog` VALUES (37, 'Java爬虫以及线程池利用', '概念介绍部分，不想看可以自行跳到后面实战为什么要使用线程池？1.操作的是重用存在的线程，减少对象创建、消亡的开销，性能佳。2.可有效控制最大并发线程数，提高系统资源的使用率，同时避免过多资源竞争，避免', '2019-08-27 16:56:18', '2019-08-28 10:01:40', 154, 14, 1, '1', NULL, '', 1);
INSERT INTO `blog` VALUES (38, '关于多线程内容的补充，ThreadPoolExecutor与Executors的区别', '简介看了多线程爬虫的文章后，对多线程应该有一定的了解了，那么，我们为什么要使用ThreadPoolExecutor来创建线程池而不使用Executors呢？居然阿里爸爸都说了不要使用Executors', '2019-08-28 16:03:44', '2019-08-28 16:03:44', 13, 4, 0, '1', NULL, '', 1);
INSERT INTO `blog` VALUES (39, 'quartz cron', 'Tips最近打算在项目中加入quartz任务调度，实现定时备份数据库，定时发送邮件提醒的功能，最近时间有限，先把cron的用法了解一下，详细的后面有空再做一、结构SecondsMinutesHours', '2019-08-29 20:05:24', '2019-08-29 20:05:24', 1, 3, 0, '1', NULL, '', 1);

-- ----------------------------
-- Table structure for blog_content
-- ----------------------------
DROP TABLE IF EXISTS `blog_content`;
CREATE TABLE `blog_content`  (
  `id` int(10) UNSIGNED NOT NULL COMMENT '主键，和对应博客一致',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '正文',
  PRIMARY KEY (`id`) USING BTREE,
  CONSTRAINT `blog_content_ibfk_1` FOREIGN KEY (`id`) REFERENCES `blog` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of blog_content
-- ----------------------------
INSERT INTO `blog_content` VALUES (1, '## 简介\n\n本博客前后端分离的轻量级个人博客系统。\n\n本博客系统由后端服务器系统、后台管理系统、前台系统三个子系统组成。其中，后端服务系统基于SpringBoot，后台管理系统和前台系统均基于Vue。每个子系统的详细内容可参看相应的Github地址。\n\n## 技术栈\n\nSpringBoot + Maven + Mybatis + PageHelper + MySQL + Redis\n\n## 传送门\n\n- [后端服务系统](https://github.com/yhuihu/blog-back)\n- [后台管理系统](https://github.com/yhuihu/yhhu_blog_admin)\n- [前台系统](https://github.com/yhuihu/yhhu_blog_front)\n\n> 注意：项目的图片上传到七牛云上了，所以图片路径为域名形式，可以根据自己的需求改写保存到本地\n## 目前问题\n- 文章中的图片还无法达到放大效果，正在处理中。（已完成，有进一步建议欢迎留言）\n- v-for下如何调用全局变量？评论删除暂时还未完成，遇到瓶颈\n- 用户目前只能通过github第三方登录，没有进行绑定操作，之后有必要会进行补充\n## BlahBlah\n\n如果本项目对您学习Vue或SpringBoot等有所帮助的话，请帮忙点颗⭐哦😁！\n\n如果您对本项目有什么意见或建议，也欢迎批评指正。\n\n## 打赏链接\n![微信图片_20190826205030.jpg](http://yhhu.xyz/NPZ9xcBMnrCduvtn4WWMITJi)     ![微信图片_20190826205212.jpg](http://yhhu.xyz/ScPrZMaVtT25quNbLSNtlYXk)');
INSERT INTO `blog_content` VALUES (10, '## 基本思想\n\n　　归并排序（MERGE-SORT）是利用归并的思想实现的排序方法，该算法采用经典的分治（divide-and-conquer）策略（分治法将问题分(divide)成一些小的问题然后递归求解，而治(conquer)的阶段则将分的阶段得到的各答案\"修补\"在一起，即分而治之)。\n  \n### 分而治之\n\n![](http://localhost/api/upload/2019/07/24/17da2791-65ca-49f1-a26e-f36f28f4a098.png)\n\n可以看到这种结构很像一棵完全二叉树，本文的归并排序我们采用递归去实现（也可采用迭代的方式去实现）。分阶段可以理解为就是递归拆分子序列的过程，递归深度为log2n。\n\n## 合并相邻有序子序列\n\n　　再来看看治阶段，我们需要将两个已经有序的子序列合并成一个有序序列，比如上图中的最后一次合并，要将[4,5,7,8]和[1,2,3,6]两个已经有序的子序列，合并为最终序列[1,2,3,4,5,6,7,8]，来看下实现步骤。\n  \n  ![](http://localhost/api/upload/2019/07/24/26f6b8ff-6d46-4578-b1c4-15df8ccf58df.png)\n  ![](http://localhost/api/upload/2019/07/24/8b75acaa-ef34-4566-a29c-7ac13fc58d98.png)\n  \n## 代码实现\n\n```java\npackage sortdemo;\n\nimport java.util.Arrays;\n\n/**\n * Created by chengxiao on 2016/12/8.\n */\npublic class MergeSort {\n    public static void main(String []args){\n        int []arr = {9,8,7,6,5,4,3,2,1};\n        sort(arr);\n        System.out.println(Arrays.toString(arr));\n    }\n    public static void sort(int []arr){\n        int []temp = new int[arr.length];//在排序前，先建好一个长度等于原数组长度的临时数组，避免递归中频繁开辟空间\n        sort(arr,0,arr.length-1,temp);\n    }\n    private static void sort(int[] arr,int left,int right,int []temp){\n        if(left<right){\n            int mid = (left+right)/2;\n            sort(arr,left,mid,temp);//左边归并排序，使得左子序列有序\n            sort(arr,mid+1,right,temp);//右边归并排序，使得右子序列有序\n            merge(arr,left,mid,right,temp);//将两个有序子数组合并操作\n        }\n    }\n    private static void merge(int[] arr,int left,int mid,int right,int[] temp){\n        int i = left;//左序列指针\n        int j = mid+1;//右序列指针\n        int t = 0;//临时数组指针\n        while (i<=mid && j<=right){\n            if(arr[i]<=arr[j]){\n                temp[t++] = arr[i++];\n            }else {\n                temp[t++] = arr[j++];\n            }\n        }\n        while(i<=mid){//将左边剩余元素填充进temp中\n            temp[t++] = arr[i++];\n        }\n        while(j<=right){//将右序列剩余元素填充进temp中\n            temp[t++] = arr[j++];\n        }\n        t = 0;\n        //将temp中的元素全部拷贝到原数组中\n        while(left <= right){\n            arr[left++] = temp[t++];\n        }\n    }\n}\n```\n执行结果\n>[1, 2, 3, 4, 5, 6, 7, 8, 9]\n\n## 最后\n\n　　归并排序是稳定排序，它也是一种十分高效的排序，能利用完全二叉树特性的排序一般性能都不会太差。java中Arrays.sort()采用了一种名为TimSort的排序算法，就是归并排序的优化版本。从上文的图中可看出，每次合并操作的平均时间复杂度为O(n)，而完全二叉树的深度为|log2n|。总的平均时间复杂度为O(nlogn)。而且，归并排序的最好，最坏，平均时间复杂度均为O(nlogn)。');
INSERT INTO `blog_content` VALUES (20, '## 问题描述\n\nVuex适合于中大型项目，在小型项目中使用往往是杀鸡用牛刀。\n\n官方说明如下:\n\n> 如果您不打算开发大型单页应用，使用 Vuex 可能是繁琐冗余的。确实是如此——如果您的应用够简单，您最好不要使用 Vuex。\n\n但最近使用自定义的全局变量时，却遇到了`computed`计算属性不能获取全局变量变更后的状态的问题。\n\n```js\n// src/state/index.js\nexport default {\n	state1: \'\',\n	state2: \'\'\n}\n\n// main.js\nimport state from \'./state\'\nVue.prototype.$state = state\n\n// demo.vue\ncomputed: {\n    state1() {\n        console.log(\'in computed...\')\n        return this.$state.state1\n    }\n},\nwatch: {\n    state1: function(newValue) {\n        console.log(\'newValue: \' + newValue)\n    }\n}    \n```\n\n\n\n当通过其他方式改变`state1`的值时，发现`computed`和`watch`的代码并没有执行，查阅了相关资料才发现这种方式创建的全局变量**是不可交互的**。\n\n## 解决方法\n\n```js\n// src/state/index.js\nexport default {\n	state1: \'\',\n	state2: \'\'\n}\n\n// main.js\nimport state from \'./state\'\nnew Vue({\n  el: \'#app\',\n  router,\n  components: { App },\n  template: \'<App/>\'\n  // 关键在此处，注意此处data不是函数形式\n  data: { // only place where data is not a function\n    state\n  },\n})\n\n// demo.vue\ncomputed: {\n    state1() {\n        console.log(\'in computed...\')\n        return this.$root.state.state1\n    }\n},\nwatch: {\n    state1: function(newValue) {\n        console.log(\'newValue: \' + newValue)\n    }\n}  \n```\n\n按上面写法就能成功创建可交互的全局变量了。\n\n\n\n## 参考\n\n[https://cn.vuejs.org/v2/guide/state-management.html#简单状态管理起步使用](https://cn.vuejs.org/v2/guide/state-management.html#简单状态管理起步使用)\n\n[https://stackoverflow.com/questions/51275301/how-to-react-to-a-global-variable-with-vue](https://stackoverflow.com/questions/51275301/how-to-react-to-a-global-variable-with-vue)');
INSERT INTO `blog_content` VALUES (33, '## 前提条件\n\n 1. Docker 运行在 CentOS 7 上，要求系统为64位、系统内核版本为 3.10 以上。\n 2. Docker 运行在 CentOS-6.5 或更高的版本的 CentOS 上，要求系统为64位、系统内核版本为 2.6.32-431 或者更高版本。\n\n## 移除旧版本以及相关内容\n\n```\n	sudo yum remove docker \\\n                  docker-client \\\n                  docker-client-latest \\\n                  docker-common \\\n                  docker-latest \\\n                  docker-latest-logrotate \\\n                  docker-logrotate \\\n                  docker-selinux \\\n                  docker-engine-selinux \\\n                  docker-engine\n```\n![在这里插入图片描述](https://img-blog.csdnimg.cn/201908102327229.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl8zOTYxMzY4OQ==,size_16,color_FFFFFF,t_70)\n## 安装依赖包\n```\nyum install -y yum-utils \\ device-mapper-persistent-data \\  lvm2\n```\n## 更新并安装Docker CE\n\n```\n//添加软件源\nyum-config-manager --add-repo http://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo\n//更新并安装docker-ce\nyum update && yum -y install docker-ce\n```\n### 验证安装\n\n - 启动docker\n```\nsystemctl start docker\n```\n - 查看信息\n\n```\ndocker version\n```\n![在这里插入图片描述](https://img-blog.csdnimg.cn/20190810234533945.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl8zOTYxMzY4OQ==,size_16,color_FFFFFF,t_70)\n## 设置阿里云镜像加速\n![在这里插入图片描述](https://img-blog.csdnimg.cn/2019081023571344.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl8zOTYxMzY4OQ==,size_16,color_FFFFFF,t_70)\n根据提示开通服务后执行以下操作（镜像加速器有很多，在此用的是阿里云的）\n![根据操作文档操作](https://img-blog.csdnimg.cn/2019081023593413.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl8zOTYxMzY4OQ==,size_16,color_FFFFFF,t_70)\n根据操作文档操作即可\n![在这里插入图片描述](https://img-blog.csdnimg.cn/20190811000152894.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl8zOTYxMzY4OQ==,size_16,color_FFFFFF,t_70)\n### 验证配置\n\n```\ndocker info\n```\n![在这里插入图片描述](https://img-blog.csdnimg.cn/20190811000452194.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl8zOTYxMzY4OQ==,size_16,color_FFFFFF,t_70)\n## 尝试安装tomcat镜像\n\n```\ndocker pull tomcat\n```\n![在这里插入图片描述](https://img-blog.csdnimg.cn/20190811000711255.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl8zOTYxMzY4OQ==,size_16,color_FFFFFF,t_70)\n## 启动tomcat\n\n```\ndocker run -p 8080:8080 tomcat\n```\n\n![在这里插入图片描述](https://img-blog.csdnimg.cn/20190811000930247.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl8zOTYxMzY4OQ==,size_16,color_FFFFFF,t_70)');
INSERT INTO `blog_content` VALUES (34, '### Spring\nIPC,AOP。是一个轻量级的JAVAEE开源框架，主要是为了解决企业级开发的复杂度问题，复杂度->耦合度\n### SpringBoot\n新一代JAVAEE开发标准，开箱即用的特性（不需要过多配置）\n### 微服务架构\n更好的进行分布式系统开发，拆分单体应用，将一个应用拆分成多个服务，每个服务都是一个可以独立运行的项目\n### 分布式系统开发一定会遇到的问题\n1. 客户端如何访问\n2. 服务间如何通信\n3. 服务如何治理\n4. 服务挂了怎么办\n5. 网络是不可靠的\n\n### 解决方案\nSpring Cloud是一套生态，是为了解决微服务架构遇到的问题。想要用Spring Cloud必须基于Spring Boot\n- Spring Cloud Netflix（18年进入维护，停止更新）\n- Apache Dubbo Zookeeper\n\n```\nDubbo是一个高性能的JAVA RPC通信框架\n服务注册于发现，Zookeeper\nAPI网关，没有，再找第三方或自己实现\n```\n- Spring Cloud Alibaba\n\n```\nSpring Cloud Alibaba 致力于提供微服务开发的一站式解决方案。\n此项目包含开发分布式应用微服务的必需组件，方便开发者通过 Spring Cloud 编程模型轻松使用这些组件来开发分布式应用服务。\n依托 Spring Cloud Alibaba，您只需要添加一些注解和少量配置，就可以将 Spring Cloud 应用接入阿里分布式应用解决方案，通过阿里中间件来迅速搭建分布式应用系统。\n```\n- 下一代微服务架构标准 Service Mesh服务网格(Istio)\n- 异步非阻塞通信\n\n```\nNetty -> NIO、AIO\nHTTP -> 应用层，跨防火墙，在不同局域网间通信\nRPC -> 远程过程调用，TCP（传输层） \n        优点：速度快\n        缺点：不能跨防火墙，仅支持局域网通信\n```\n');
INSERT INTO `blog_content` VALUES (35, '## 说明 \n本文以本网站作为案例进行说明\n### SpringBoot后端系统的打包以及部署\n- 打包项目成jar包(由于项目为个人项目，所有行为直接操作个人服务器，没有配置多个配置文件)\n\n> IDEA操作\n\n![微信图片编辑_20190826162745.jpg](http://yhhu.xyz/TnfgL1LWc3N5F5CsqaWVGkbz)\n> 成功标志\n\n![微信图片_20190826162156.png](http://yhhu.xyz/GEDZZj389uyvF0p6rZj8ccCw)\n\n> 命令行操作\n\n移动到项目目录\nmvn clean package -Dmaven.test.skip=true\n![微信图片_20190826163135.png](http://yhhu.xyz/03qeA9C8wDtp6O9Bkuytr1aA)\n在xshell中使用rz命令上传jar包(如果遇到rz无法使用的情况就先执行yum install lrzsz安装依赖)\n![微信图片_20190826163223.png](http://yhhu.xyz/OljD06SVxfPDoQVv2jJfw8VM)\n后台启动jar包\nnohup java -jar blog-back-0.0.1-SNAPSHOT.jar &\n此时可以在相同目录下看到nohup.out，jar包所有日志都在里面\n\n### VUE项目打包部署\n\n直接使用npm run build进行打包\n> 可能遇到的问题以及注意事项\n\n- build之前请确保能够正常运行，如果在build的时候出现错误，可以尝试将node_modules文件夹删除重新执行npm install，此处建议使用cnpm install，本人在使用npm install出现过包损坏的情况。\n- 在该案例中VUE的部署是使用express和pm2完成的，因此不用修改assetsPublicPath！\n- 如果有人出现问题此处会继续补充\n\n打包完成后相同目录下会出现dist文件夹\n在目录中新建app.js文件，将两内容一起打包成压缩包上传服务器\n```\nconst fs = require(\'fs\'); //文件模块\nconst path = require(\'path\'); //路径模块\nconst express = require(\'express\'); //express框架模块\nconst app = express();\nconst hostName = \'localhost\'; //ip\nconst port = 8002; //端口\napp.use(express.static(path.resolve(__dirname, \'./dist\'))); // 设置静态目录(例：app.js在demo目录下，那么目录则为demo/dist)。\napp.get(\'*\', function (req, res) {\n  const html = fs.readFileSync(path.resolve(__dirname, \'./dist/index.html\'), \'utf-8\'); // 设置所有访问服务请求默认返回index.html文件\n  res.send(html);\n});\napp.listen(port, hostName, function () {\n  console.log(`服务器运行在http://${hostName}:${port}`);\n```\n![微信图片_20190826164451.png](http://yhhu.xyz/DKUN166zjiloUQHDQCyX3Ij5)\n\n- 此后操作需要先在服务器上安装node(自行百度)\n\n在app.js+dist的目录下执行npm init，一路回车到底\n![微信图片_20190826174658.png](http://yhhu.xyz/ozOBwAocnqrE675n6d9eysun)\n- 项目安装express框架\ncnpm install express --save\n\n此时项目的目录结构如图\n![微信图片_20190826175009.png](http://yhhu.xyz/YNnTEZpuKN9MAiY6dZpOYxz4)\n- 启动项目(pm2托管方式)\npm2安装npm install pm2 -g\n\npm2 start app.js --name xxx\n后面的--name xxx用于指定该app的名称，类似于docker，可以不指定，直接用pm2 start app.js\n![微信图片_20190826175145.png](http://yhhu.xyz/jRrPJgAP0pRWSSzzHapjvLxj)\n\n- 如果想要使用反向代理可以继续往下看，否则已经可以正常运行了\n如果不使用反向代理，访问的方式应该为：www.xxx.xxx:端口号，如果使用方向代理，则可以将某个域名转发到特定端口上面，从而达到省略端口号访问。\n\n在此使用nginx作为方向代理工具，安装直接yum安装就好了，没什么好说的。\n安装完成后可以在/etc/nginx下看到nginx.conf配置文件\n![微信图片_20190826192950.png](http://yhhu.xyz/IJyETkUVYDlsqfmm4JovM7Er)');
INSERT INTO `blog_content` VALUES (36, '### 一、Java多线程实现的几种方式\n1. 继承Thread类创建线程类\n\n\n```\npublic class study {\n    //1.定义一个继承Thread的子类\n    public static class testThread extends Thread {\n        int i=0;\n        testThread(String name) {\n            super(name);\n        }\n\n        //2.重写Thread的run()方法\n        @Override\n        public void run() {\n            for (; i < 100; i++)\n                System.out.println(\"我是线程:\" + Thread.currentThread().getName() + \"----\" + i);\n        }\n    }\n\n    public static void main(String[] args) {\n        //3.创建Thread子类实例\n        testThread thread1 = new testThread(\"1\");\n        testThread thread2 = new testThread(\"2\");\n        //4.启动线程\n        thread1.start();\n        thread2.start();\n    }\n}\n```\n\n\n>运行一遍后发现线程1和2都将输出0-100，各线程数据时独立的\n\n2. 实现Runnable接口创建线程类  \n\n\n```\n//1.定义Runnable接口实现类\nclass testRunnable implements Runnable {\n    private int i=0;\n    //  2.重写run()方法\n    @Override\n    public void run() {\n        for(;i<100;i++){\n            System.out.println(i);\n        }\n    }\n}\n\npublic class study {\n\n    public static void main(String[] args) {\n        //3.创建实例\n        testRunnable testRunnable=new testRunnable();\n        //4.将该实例作为Thread的target对象\n        Thread thread=new Thread(testRunnable);\n        Thread thread1=new Thread(testRunnable);\n        //5.启动线程\n        thread.start();\n        thread1.start();\n    }\n}\n```\n\n\n>运行后可以发现只输出0-100中的数字一遍，证明Runable是可以实现数据共享的（多个线程共享Thread这个Runable对象代码）\n注意：这是线程不安全的，多运行几次可能会出现某个数字重复输出的情况，可以考虑互斥锁\n\n3. 通过Callable和Future创建线程\n\n\n```\n//1.创建Callable接口的实现类\nclass testCallable implements Callable<Integer> {\n    private int i = 0;\n    //2.实现call方法\n    @Override\n    public Integer call() throws Exception {\n        int sum = 0;\n        for (; i < 100; i++) {\n            System.out.println(Thread.currentThread().getName() + \" \" + i);\n            sum += i;\n        }\n        return sum;\n    }\n}\n\npublic class study {\n\n    public static void main(String[] args) {\n        //3.创建testCallable对象\n        Callable<Integer> testCallable=new testCallable();\n        //4.由Callable创建一个FutureTask对象\n        FutureTask<Integer> ft = new FutureTask<Integer>(testCallable);\n        for (int i = 0; i < 100; i++) {\n            System.out.println(Thread.currentThread().getName() + \" \" + i);\n            if (i == 30) {\n                //5.由FutureTask创建一个Thread对象\n                Thread thread = new Thread(ft);\n                //6.线程进入到就绪状态\n                thread.start();\n            }\n        }\n        System.out.println(\"主线程for循环执行完毕..\");\n        try {\n            int sum = ft.get();            //取得新创建的新线程中的call()方法返回的结果\n            System.out.println(\"sum = \" + sum);\n        } catch (InterruptedException | ExecutionException e) {\n            e.printStackTrace();\n        }\n    }\n}\n```\n\n\n### 二、线程的生命周期\n![图解](http://yhhu.xyz/LIHsiIkUz5NMhXqUO2pAJBGA)\n\n1. 新建状态\n用new关键字和Thread类或其子类建立一个线程对象后，该线程对象就处于新建状态。处于新生状态的线程有自己的内存空间，通过调用start方法进入就绪状态（runnable）。\n>注意：不能对已经启动的线程再次调用start()方法，否则会出现IllegalThreadStateException异常。\n2.  就绪状态\n处于就绪状态的线程已经具备了运行条件，但还没有分配到CPU，处于线程就绪队列（尽管是采用队列形式，事实上，把它称为可运行池而不是可运行队列。因为cpu的调度不一定是按照先进先出的顺序来调度的），等待系统为其分配CPU。等待状态并不是执行状态，当系统选定一个等待执行的Thread对象后，它就会从等待执行状态进入执行状态，系统挑选的动作称之为“cpu调度”。一旦获得CPU，线程就进入运行状态并自动调用自己的run方法。\n3. 运行状态\n处于就绪状态的线程，如果获得了cpu的调度，就会从就绪状态变为运行状态，执行run()方法中的任务。如果该线程失去了cpu资源，就会又从运行状态变为就绪状态。重新等待系统分配资源。也可以对在运行状态的线程调用yield()方法，它就会让出cpu资源，再次变为就绪状态。\n\n注： 当发生如下情况是，线程会从运行状态变为阻塞状态：\n\n- CPU执行片已经用完，JVM切换到其他线程执行\n- 线程调用sleep()\n- 线程调用了阻塞IO方法，该方法返回之前，线程会一直阻塞\n- 线程试图获取被其他线程持有的同步监视器\n- 线程在等待某个通知（notify）\n- 程序调用了线程的suspend()将线程挂起。（容易死锁，不推荐）\n\n4. 阻塞状态\n在阻塞状态的线程不能进入就绪队列。只有当引起阻塞的原因消除时，如睡眠时间已到，或等待的I/O设备空闲下来，线程便转入就绪状态，重新到就绪队列中排队等待，被系统选中后从原来停止的位置开始继续运行。\n5. 死亡状态\n当线程的run()方法执行完，或者被强制性地终止，就认为它死去。这个线程对象也许是活的，但是，它已经不是一个单独执行的线程。线程一旦死亡，就不能复生。 如果在一个死去的线程上调用start()方法，会抛出java.lang.IllegalThreadStateException异常。\n\n\n### 总结\n\n实现Runnable接口相比继承Thread类有如下优势：\n\n1. 可以避免由于Java的单继承特性而带来的局限；\n2. 增强程序的健壮性，代码能够被多个线程共享，代码与数据是独立的；\n3. 适合多个相同程序代码的线程区处理同一资源的情况。\n\n实现Runnable接口和实现Callable接口的区别:\n1. Callable规定的方法是call(),Runnable规定的方法是run()\n2. Callable的任务执行后可返回值，而Runnable的任务是不能返回值(是void)\n3. call方法可以抛出异常，run方法不可以\n4. 运行Callable任务可以拿到一个Future对象，表示异步计算的结果。它提供了检查计算是否完成的方法，以等待计算的完成，并检索计算的结果。通过Future对象可以了解任务执行情况，可取消任务的执行，还可获取执行结果。\n5. 加入线程池运行，Runnable使用ExecutorService的execute方法，Callable使用submit方法。\n\n#### 上述参考多处概念总结而出，如果需要更深层次的使用，可以看参考链接中的Java多线程详解\n## 参考\n[ java多线程—Thread、Runnable和Callable区别](https://blog.csdn.net/u012501054/article/details/80384996)\n\n[Java多线程详解](https://www.cnblogs.com/snow-flower/p/6114765.html)');
INSERT INTO `blog_content` VALUES (37, '## 概念介绍部分，不想看可以自行跳到后面实战\n### 为什么要使用线程池？\n1. 操作的是重用存在的线程，减少对象创建、消亡的开销，性能佳。\n2. 可有效控制最大并发线程数，提高系统资源的使用率，同时避免过多资源竞争，避免堵塞。\n3. 提供定时执行、定期执行、单线程、并发数控制等功能。\n\n### Java的五种线程池介绍\n1. newCachedThreadPool创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。(用过几次，可能因为电脑台渣，导致线程不够用时不够内存创建新线程报错)\n2. newFixedThreadPool 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。\n3. newScheduledThreadPool 创建一个定长线程池，支持定时及周期性任务执行。\n4. newSingleThreadExecutor 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，此线程池保证所有任务的执行顺序按照任务的提交顺序执行。\n5. newWorkStealingPool创建一个拥有多个任务队列的线程池，可以减少连接数，创建当前可用cpu数量的线程来并行执行，适用于大耗时的操作，可以并行来执行\n\n>Excutors创建线程池貌似不被推荐，推荐实用threadPoolExecutor，后续有时间会补充相关内容。《阿里巴巴Java开发手册》中强制线程池不允许使用 Executors 去创建，而是通过 new ThreadPoolExecutor 实例的方式，这样的处理方式让写的同学更加明确线程池的运行规则，规避资源耗尽的风险。\n\n### 线程池使用demo（代码都跑过，没问题，不贴结果图了）\n- newCachedThreadPool\n```\nclass test implements Runnable {\n    private static int i=0;\n    @Override\n    public void run() {\n        for (;i<100;i++)\n            System.out.println(i);\n    }\n}\npublic class PoolStudy {\n    public static void main(String[] args) {\n        ExecutorService pool = Executors.newCachedThreadPool();\n        long start = System.currentTimeMillis();\n        pool.execute(new test());\n        pool.execute(new test());\n        pool.execute(new test());\n        System.out.println(\"cache: \" + (System.currentTimeMillis() - start));\n    }\n}\n```\n\n>跑一遍会发现执行完成不会马上结束，默认需要等待60秒无操作才会终止或回收\n\n- newFixedThreadPool\n```\npublic class PoolStudy {\n    public static void main(String[] args) {\n        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);\n        for (int i = 0; i < 10; i++) {\n            final int index = i;\n            fixedThreadPool.execute(new Runnable() {\n                @Override\n                public void run() {\n                    try {\n                        System.out.println(index);\n                        Thread.sleep(2000);\n                    } catch (InterruptedException e) {\n                        e.printStackTrace();\n                    }\n                }\n            });\n        }\n        fixedThreadPool.shutdown();\n    }\n}\n```\n\n>通过shutdown()方法释放线程，否则会一直存在\n\n- newScheduledThreadPool\n```\npublic class PoolStudy {\n    public static void main(String[] args) {\n        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);\n        scheduledThreadPool.schedule(new Runnable() {\n            @Override\n            public void run() {\n                System.out.println(\"delay 3 seconds\");\n            }\n        }, 3, TimeUnit.SECONDS);\n    }\n}\n```\n\n- newSingleThreadExecutor \n```\npublic class PoolStudy {\n    public static void main(String[] args) {\n        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();\n        for (int i = 0; i < 10; i++) {\n            final int index = i;\n            singleThreadExecutor.execute(new Runnable() {\n                @Override\n                public void run() {\n                    try {\n                        System.out.println(Thread.currentThread().getName()+index);\n                        Thread.sleep(1000);\n                    } catch (InterruptedException e) {\n                        e.printStackTrace();\n                    }\n                }\n            });\n        }\n        singleThreadExecutor.shutdown();\n    }\n}\n```\n\n- newWorkStealingPool\n```\npublic class PoolStudy {\n    public static void main(String[] args) {\n        // 设置并行级别为2，即默认每时每刻只有2个线程同时执行\n        ExecutorService m = Executors.newWorkStealingPool(2);\n        for (int i = 1; i <= 10; i++) {\n            final int count=i;\n            Future future=m.submit(new Runnable() {\n                @Override\n                public void run() {\n                    Date now=new Date();\n                    System.out.println(\"线程\" + Thread.currentThread() + \"完成任务：\"\n                            + count+\"   时间为：\"+	now.getSeconds());\n                    try {\n                        Thread.sleep(1000);//此任务耗时1s\n                    } catch (InterruptedException e) {\n                        e.printStackTrace();\n                    }\n                }\n            });\n        }\n        while(true){\n            //主线程陷入死循环，来观察结果，否则是看不到结果的\n        }\n    }\n}\n```\n\n> 说明：线程执行任务有两种方法，一种是excute(new Runable(){...})，一种是submit(new Runable(){...})。不同点在于submit是可以接受到返回信息的，通过上述例子的future.get()可以获取到，如果执行成功返回的是null。\n\n### Java爬虫方法\n\n1. 通过自动化驱动selenium来完成操作，不推荐，速度慢，占用内存高\n2. jsoup，推荐，直接获取html进行解析，以下内容通过jsoup来完成\n\n#### 通过Jsoup+多线程方法爬取HTML内容解析（豆瓣图书的封面图片为例子）\n1. 前往[https://book.douban.com/](https://book.douban.com/)了解html结构\n![微信图片_20190827191502.png](http://yhhu.xyz/dMAUaqIE5m4fAoHAiNCNwsd8)\n多观察几个，发现图片都是在class属性为cover的div下的img，这就很方便了，直接一步到位进入代码环节\n```\n    public static void main(String[] args) {\n        String url=\"https://book.douban.com/\";\n        // 2 解析 html\n        try {\n            Document doc = Jsoup.connect(url).get();\n            //从 Doc 的树形结构中查找 img 标签\n            //.class 选择器\n            Elements els = doc.select(\".cover img\");\n            System.out.println(els.size());\n            // 创建一个线程池\n            ExecutorService pool = Executors.newFixedThreadPool(9);\n            for(Element e : els) {\n                // <img src=\"\"  width=\"\"  height=\"\" />\n                String src = e.attr(\"src\");\n                System.out.println(src);\n                // 下载每张图片\n                pool.execute(new DownloadTask(src));\n            }\n            //释放资源\n            pool.shutdown();\n        } catch (IOException e) {\n            // TODO Auto-generated catch block\n            e.printStackTrace();\n        }\n    }\n```\n\n#### 通过Jsoup+多线程方法爬取JSON内容解析（豆瓣电影为例子）\n\n![微信截图_20190827205211.png](http://yhhu.xyz/RZslPpd3vjvgYAUl74xwD0E8)\n\n在network中，我们观察到请求数据的地址\n我们试一下直接访问该地址看看获得了什么数据\n![微信截图_20190827205310.png](http://yhhu.xyz/i3IouiYFdfFfZWXVK9cUkRfg)\n\n果不其然，这就是我们需要的电影数据信息了，那么如何使用Jsoup来处理这些数据呢？\n在对JSON数据处理的时候，我的案例中使用的是阿里的fastJson\n```\npublic class Moive {\n    public static void main(String[] args) throws IOException {\n        String savePath = \"D:\" + File.separator + \"资源\" + File.separator + \"豆瓣电影排行榜\" + File.separator;\n        File file = new File(savePath);\n        if (!file.exists()) {\n            if (!file.mkdirs())\n                System.out.println(\"系统操作出现异常。\");\n        }\n        ExecutorService pool = Executors.newFixedThreadPool(10);\n        for (int i = 0; i < 10; i++) {\n            String url = \"https://movie.douban.com/j/search_subjects?type=movie&tag=%E8%B1%86%E7%93%A3%E9%AB%98%E5%88%86&sort=rank&page_limit=20&page_start=\" + String.valueOf(i * 20);\n            //尝试爬10页电影，按自己需求更改\n            Connection.Response res = Jsoup.connect(url).ignoreContentType(true).execute();\n            JSONObject json = JSONObject.parseObject(res.body());\n            JSONArray jsonArray = json.getJSONArray(\"subjects\");\n            for (Object o : jsonArray) {\n                JSONObject object = (JSONObject) o;\n                //电影名称\n                //System.out.println(object.getString(\"title\"));\n                //图片链接\n                //System.out.println(object.getString(\"cover\"));\n                // 下载每张图片\n                pool.execute(new Download(object.getString(\"title\"), object.getString(\"cover\")));\n            }\n        }\n        pool.shutdown();\n    }\n}\n```\n\n\n请求的方法都挺简单的，没有专门防止爬虫的拦截，不需要设置请求头之类的就可以直接捕获到数据，不得不说多线程在爬虫中真是一个利器，在对豆瓣电影进行的10页内容爬虫中，一共200张图片，总消耗时间不到20秒。。。下载文件的代码就不放了，如果有需要可以在留言中留下你的联系方式。\n\n>本案例仅作为参考，没有很深入的操作，例如如何翻页等等，如果需要进一步研究可以自行百度或留言联系我。完整DEMO也可以在留言中留下联系方式获取，如果看到会回复。\n\n## 参考\n[Java 四种线程池newCachedThreadPool,newFixedThreadPool,newScheduledThreadPool,newSingleThreadExecutor](https://www.cnblogs.com/zhujiabin/p/5404771.html)');
INSERT INTO `blog_content` VALUES (38, '### 简介\n看了多线程爬虫的文章后，对多线程应该有一定的了解了，那么，我们为什么要使用ThreadPoolExecutor来创建线程池而不使用Executors呢？居然阿里爸爸都说了不要使用Executors了，那肯定是有缺陷啊\n下面开始分析一下Executors的源代码（这里只放出关键代码，详细的自行查看）\n```\n    public static ExecutorService newFixedThreadPool(int nThreads) {\n        return new ThreadPoolExecutor(nThreads, nThreads,\n                                      0L, TimeUnit.MILLISECONDS,\n                                      new LinkedBlockingQueue<Runnable>());\n    }\n    public static ExecutorService newWorkStealingPool(int parallelism) {\n        return new ForkJoinPool\n            (parallelism,\n             ForkJoinPool.defaultForkJoinWorkerThreadFactory,\n             null, true);\n    }\n    public static ExecutorService newSingleThreadExecutor() {\n        return new FinalizableDelegatedExecutorService\n            (new ThreadPoolExecutor(1, 1,\n                                    0L, TimeUnit.MILLISECONDS,\n                                    new LinkedBlockingQueue<Runnable>()));\n    }\n    public static ExecutorService newCachedThreadPool() {\n        return new ThreadPoolExecutor(0, Integer.MAX_VALUE,\n                                      60L, TimeUnit.SECONDS,\n                                      new SynchronousQueue<Runnable>());\n    }\n    public static ScheduledExecutorService newSingleThreadScheduledExecutor() {\n        return new DelegatedScheduledExecutorService\n            (new ScheduledThreadPoolExecutor(1));\n    }\n```\n\n这是Executors.java对线程池的默认实现方法，可以看出来他们其实也是调用ThreadPoolExecutor来完成线程池的创建的，但是也可以看出这很容易造成OOM的情况（out of memory）\n- newFixedThreadPool 和SingleThreadPool\n堆积的请求队列会造成内存增大,甚至OOM\n- newCachedThreadPool 和newScheduledThreadPool\n最大线程量 Integer.MAX_VALUE ,可能引发OOM\n\n下面来详细说明一下ThreadPoolExecutor\n```\n    public ThreadPoolExecutor(int corePoolSize,\n                              int maximumPoolSize,\n                              long keepAliveTime,\n                              TimeUnit unit,\n                              BlockingQueue<Runnable> workQueue,\n                              RejectedExecutionHandler handler) {\n        this(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue,\n             Executors.defaultThreadFactory(), handler);\n    }\n```\n\n这是ThreadPoolExecutor的构造函数，每个字段的意思是\n- corePoolSize：核心线程数量，当有新任务在execute()方法提交时，会执行以下判断：\n如果运行的线程少于 corePoolSize，则创建新线程来处理任务，即使线程池中的其他线程是空闲的；\n如果线程池中的线程数量大于等于 corePoolSize 且小于 maximumPoolSize，则只有当workQueue满时才创建新的线程去处理任务；\n如果设置的corePoolSize 和 maximumPoolSize相同，则创建的线程池的大小是固定的，这时如果有新任务提交，若workQueue未满，则将请求放入workQueue中，等待有空闲的线程去从workQueue中取任务并处理；\n如果运行的线程数量大于等于maximumPoolSize，这时如果workQueue已经满了，则通过handler所指定的策略来处理任务；\n所以，任务提交时，判断的顺序为 corePoolSize –> workQueue –> maximumPoolSize。\n- maximumPoolSize：最大线程数量；\n- workQueue：等待队列，当任务提交时，如果线程池中的线程数量大于等于corePoolSize的时候，把该任务封装成一个Worker对象放入等待队列；当提交一个新的任务到线程池以后, 线程池会根据当前线程池中正在运行着的线程的数量来决定对该任务的处理方式，主要有以下几种处理方式:\n	1. 直接切换：这种方式常用的队列是SynchronousQueue，但现在还没有研究过该队列，这里暂时还没法介绍；\n	2. 使用无界队列：一般使用基于链表的阻塞队列LinkedBlockingQueue。如果使用这种方式，那么线程池中能够创建的最大线程数就是corePoolSize，而maximumPoolSize就不会起作用了（后面也会说到）。当线程池中所有的核心线程都是RUNNING状态时，这时一个新的任务提交就会放入等待队列中。\n	3. 使用有界队列：一般使用ArrayBlockingQueue。使用该方式可以将线程池的最大线程数量限制为maximumPoolSize，这样能够降低资源的消耗，但同时这种方式也使得线程池对线程的调度变得更困难，因为线程池和队列的容量都是有限的值，所以要想使线程池处理任务的吞吐率达到一个相对合理的范围，又想使线程调度相对简单，并且还要尽可能的降低线程池对资源的消耗，就需要合理的设置这两个数量。\n		1. 如果要想降低系统资源的消耗（包括CPU的使用率，操作系统资源的消耗，上下文环境切换的开销等）, 可以设置较大的队列容量和较小的线程池容量, 但这样也会降低线程处理任务的吞吐量。\n		2. 如果提交的任务经常发生阻塞，那么可以考虑通过调用 setMaximumPoolSize() 方法来重新设定线程池的容量。\n		3. 如果队列的容量设置的较小，通常需要将线程池的容量设置大一点，这样CPU的使用率会相对的高一些。但如果线程池的容量设置的过大，则在提交的任务数量太多的情况下，并发量会增加，那么线程之间的调度就是一个要考虑的问题，因为这样反而有可能降低处理任务的吞吐量。\n- keepAliveTime：线程池维护线程所允许的空闲时间。当线程池中的线程数量大于corePoolSize的时候，如果这时没有新的任务提交，核心线程外的线程不会立即销毁，而是会等待，直到等待的时间超过了keepAliveTime；\n- threadFactory：它是ThreadFactory类型的变量，用来创建新线程。默认使用Executors.defaultThreadFactory() 来创建线程。使用默认的ThreadFactory来创建线程时，会使新创建的线程具有相同的NORM_PRIORITY优先级并且是非守护线程，同时也设置了线程的名称。\n- handler：它是RejectedExecutionHandler类型的变量，表示线程池的饱和策略。如果阻塞队列满了并且没有空闲的线程，这时如果继续提交任务，就需要采取一种策略处理该任务。线程池提供了4种策略：\n	1. AbortPolicy：直接抛出异常，这是默认策略；\n	2. CallerRunsPolicy：用调用者所在的线程来执行任务；\n	3. DiscardOldestPolicy：丢弃阻塞队列中靠最前的任务，并执行当前任务；\n	4. DiscardPolicy：直接丢弃任务；\n\n\n\n\n### 参考\n[ThreadPoolExecutor 使用](https://blog.csdn.net/qq_27712229/article/details/89081396)\n');
INSERT INTO `blog_content` VALUES (39, '### Tips\n#### 最近打算在项目中加入quartz任务调度，实现定时备份数据库，定时发送邮件提醒的功能，最近时间有限，先把cron的用法了解一下，详细的后面有空再做\n### 一、结构\n	\n    Seconds Minutes Hours DayofMonth Month DayofWeek Year(可选)\n    corn从左到右（用空格隔开）：秒 分 小时 月份中的日期 月份 星期几 年份(可选)\n\n### 二、各字段的含义\n![微信图片_20190829165207.png](http://yhhu.xyz/cbmJbzPmgkUvSvzDXkcrpqGB)\n#### *注意事项\n每个字段可以出现的字符注意观察上表\n#### 特殊字符解释\n1. \\*：表示匹配该域的任意值。假如在Minutes域使用*, 即表示每分钟都会触发事件。\n2. ?：只能用在DayofMonth和DayofWeek两个域。用于指定“无特定值”。它也匹配域的任意值，但实际不会。因为DayofMonth和DayofWeek会相互影响。例如想在每月的20日触发调度，不管20日到底是星期几，则只能使用如下写法： 13 13 15 20 * ?, 其中最后一位只能用？，而不能使用*，如果使用*表示不管星期几都会触发，实际上并不是这样。');

-- ----------------------------
-- Table structure for carousel
-- ----------------------------
DROP TABLE IF EXISTS `carousel`;
CREATE TABLE `carousel`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '图片',
  `image_order` int(10) NOT NULL COMMENT '顺序',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '链接',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of carousel
-- ----------------------------
INSERT INTO `carousel` VALUES (1, 'http://yhhu.xyz/pYA3VyEiRqk5u83Pi9QNKyks', 1, '');
INSERT INTO `carousel` VALUES (3, 'http://yhhu.xyz/TZD9h01g8i414gSc6mMApPy4', 3, '');
INSERT INTO `carousel` VALUES (4, 'http://yhhu.xyz/KHzm6Fhn4lIydTbZ7IZB7D4X', 1, '');

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `blog_id` int(11) UNSIGNED NOT NULL COMMENT '对应的博客',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '评论内容',
  `comment_date` datetime(0) NOT NULL COMMENT '评论日期',
  `reader_id` int(10) UNSIGNED NOT NULL COMMENT '读者/评论者',
  `reply_comment_id` int(10) UNSIGNED NULL DEFAULT NULL COMMENT '回复的评论，空表示评论博客',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `comment_ibfk_1`(`blog_id`) USING BTREE,
  INDEX `reader_id`(`reader_id`) USING BTREE,
  INDEX `reply_comment_id`(`reply_comment_id`) USING BTREE,
  CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`blog_id`) REFERENCES `blog` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `comment_ibfk_2` FOREIGN KEY (`reader_id`) REFERENCES `reader` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `comment_ibfk_3` FOREIGN KEY (`reply_comment_id`) REFERENCES `comment` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 114 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES (108, 35, 'github第三方登录成功', '2019-08-26 19:19:07', 138, NULL);
INSERT INTO `comment` VALUES (109, 37, '多线程爬虫太狠了', '2019-08-27 19:20:53', 138, NULL);
INSERT INTO `comment` VALUES (111, 37, '本文章demo已经上传github\nhttps://github.com/yhuihu/Java_Spilder', '2019-08-28 10:06:07', 138, NULL);
INSERT INTO `comment` VALUES (112, 37, '牛批', '2019-08-28 17:11:40', 139, NULL);
INSERT INTO `comment` VALUES (120, 37, 'ddddddd', '2019-08-29 19:44:40', 138, NULL);
INSERT INTO `comment` VALUES (121, 37, '延迟发送测试', '2019-08-29 20:05:43', 138, NULL);
INSERT INTO `comment` VALUES (122, 37, '111111', '2019-08-29 20:06:46', 138, NULL);
INSERT INTO `comment` VALUES (123, 38, '11111', '2019-08-29 20:09:14', 138, NULL);
INSERT INTO `comment` VALUES (124, 38, '111111', '2019-08-29 20:10:06', 138, NULL);
INSERT INTO `comment` VALUES (125, 38, '22222222', '2019-08-29 20:15:22', 138, NULL);
INSERT INTO `comment` VALUES (126, 38, '莱多一个', '2019-08-29 20:16:07', 138, NULL);
INSERT INTO `comment` VALUES (127, 39, '##########', '2019-08-29 20:50:32', 138, NULL);
INSERT INTO `comment` VALUES (128, 39, '\"######\"\ndddd', '2019-08-29 20:51:04', 138, NULL);
INSERT INTO `comment` VALUES (129, 39, '\\n', '2019-08-29 20:53:00', 138, NULL);

-- ----------------------------
-- Table structure for link
-- ----------------------------
DROP TABLE IF EXISTS `link`;
CREATE TABLE `link`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `link_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '链接名称',
  `link_url` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '链接地址',
  `link_order` int(11) NOT NULL COMMENT '链接次序（小的在前）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of link
-- ----------------------------
INSERT INTO `link` VALUES (1, 'iconfont', 'http://www.iconfont.cn/', 4);
INSERT INTO `link` VALUES (2, 'github', 'https://github.com/yhuihu/', 6);
INSERT INTO `link` VALUES (5, '百度', 'http://www.baidu.com', 10);

-- ----------------------------
-- Table structure for project
-- ----------------------------
DROP TABLE IF EXISTS `project`;
CREATE TABLE `project`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '名称',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '项目地址',
  `image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '项目图片',
  `project_order` int(10) UNSIGNED NOT NULL COMMENT '项目顺序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of project
-- ----------------------------
INSERT INTO `project` VALUES (1, '客房预订系统', 'https://github.com/yhuihu/HotelsBook', 'http://yhhu.xyz/x4PJYPybcXTz9ikarRBSLrHA', 1);
INSERT INTO `project` VALUES (2, '个人博客系统', 'https://github.com/yhuihu/yhhu_blog_front', 'http://yhhu.xyz/8drzCCJGbrVu8wS0QWjW3Aco', 2);
INSERT INTO `project` VALUES (3, 'Java爬虫', 'https://github.com/yhuihu/Java_Spilder', 'http://yhhu.xyz/oOjL0WcQ10OMenyGofEBqT6P', 3);

-- ----------------------------
-- Table structure for reader
-- ----------------------------
DROP TABLE IF EXISTS `reader`;
CREATE TABLE `reader`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '登录用的账户',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '登录用的密码',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名字',
  `ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'ip地址',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像',
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `inform` int(1) NULL DEFAULT 0 COMMENT '收到回复时是否接收邮件',
  `uuid` int(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 140 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of reader
-- ----------------------------
INSERT INTO `reader` VALUES (138, NULL, NULL, 'SuperTig', '127.0.0.1', 'https://avatars3.githubusercontent.com/u/35516186?v=4', NULL, 0, 35516186);
INSERT INTO `reader` VALUES (139, NULL, NULL, '努力学习天天向上', '127.0.0.1', 'https://avatars2.githubusercontent.com/u/43103543?v=4', NULL, 0, 43103543);

-- ----------------------------
-- Table structure for send_comment
-- ----------------------------
DROP TABLE IF EXISTS `send_comment`;
CREATE TABLE `send_comment`  (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `comment_id` int(11) UNSIGNED NOT NULL,
  `blog_id` int(10) UNSIGNED NOT NULL,
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `reader_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `comment_id`(`comment_id`) USING BTREE,
  INDEX `blog_id`(`blog_id`) USING BTREE,
  CONSTRAINT `send_comment_ibfk_1` FOREIGN KEY (`comment_id`) REFERENCES `comment` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `send_comment_ibfk_2` FOREIGN KEY (`blog_id`) REFERENCES `blog` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tag_name
-- ----------------------------
DROP TABLE IF EXISTS `tag_name`;
CREATE TABLE `tag_name`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tag_name
-- ----------------------------
INSERT INTO `tag_name` VALUES (1, 'Java');
INSERT INTO `tag_name` VALUES (2, 'Spring');
INSERT INTO `tag_name` VALUES (4, 'JS');
INSERT INTO `tag_name` VALUES (5, 'HMTL');
INSERT INTO `tag_name` VALUES (6, 'Vue');
INSERT INTO `tag_name` VALUES (7, '数据库');
INSERT INTO `tag_name` VALUES (8, 'Linux');
INSERT INTO `tag_name` VALUES (9, '数据结构');
INSERT INTO `tag_name` VALUES (10, '算法');
INSERT INTO `tag_name` VALUES (11, '项目');
INSERT INTO `tag_name` VALUES (12, '笔记');
INSERT INTO `tag_name` VALUES (13, '其他');
INSERT INTO `tag_name` VALUES (14, 'CSS');
INSERT INTO `tag_name` VALUES (15, '微服务');

-- ----------------------------
-- Table structure for type_name
-- ----------------------------
DROP TABLE IF EXISTS `type_name`;
CREATE TABLE `type_name`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of type_name
-- ----------------------------
INSERT INTO `type_name` VALUES (1, '原创');
INSERT INTO `type_name` VALUES (2, '转载');
INSERT INTO `type_name` VALUES (3, '翻译');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `nickname` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名字',
  `profile` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '简介',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  `username` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 35516187 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'yhhu', '😄普通大学生，技术爱好者😄', 'http://yhhu.xyz/ft1Uo1DThKrCsVGosrEVxuGK', 'fT28i22g7aee7ref0ff0950720e00ba0e901b0f404c0780f', 'Tiger', '1357958736@qq.com');

SET FOREIGN_KEY_CHECKS = 1;
