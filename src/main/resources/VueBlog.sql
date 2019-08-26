/*
 Navicat Premium Data Transfer

 Source Server         : 阿里云ces
 Source Server Type    : MySQL
 Source Server Version : 80017
 Source Host           : xxxxxxx:3306
 Source Schema         : VueBlog

 Target Server Type    : MySQL
 Target Server Version : 80017
 File Encoding         : 65001

 Date: 26/08/2019 15:59:04
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
) ENGINE = InnoDB AUTO_INCREMENT = 35 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of blog
-- ----------------------------
INSERT INTO `blog` VALUES (1, '关于本博客', '简介本博客是一个前后端分离的轻量级个人博客系统本博客系统由后端服务器系统、后台管理系统、前端系统三个子系统组成。其中，后端服务系统基于SpringBoot，Mybatis，Redis，后台管理系统和前', '2019-07-23 20:13:13', '2019-08-26 15:21:43', 231, 17, 28, '11,13', 'http://yhhu.xyz/5lQGI7TSaeU97bVgYoTMJasr', '', 1);
INSERT INTO `blog` VALUES (10, '图解排序算法(四)之归并排序', '基本思想　　归并排序（MERGE-SORT）是利用归并的思想实现的排序方法，该算法采用经典的分治（divide-and-conquer）策略（分治法将问题分。分而治之!(http://localhos', '2019-07-16 19:51:27', '2019-08-14 11:09:53', 9, 2, 1, '1,10', 'http://yhhu.xyz/6FuV57igPa9A0Iwru922IK8l', 'http://www.cnblogs.com/chengxiao/p/6194356.html', 2);
INSERT INTO `blog` VALUES (20, 'vue不使用vuex时创建响应式全局变量的方法', '问题描述Vuex适合于中大型项目，在小型项目中使用往往是杀鸡用牛刀。官方说明如下:如果您不打算开发大型单页应用，使用Vuex可能是繁琐冗余的。确实是如此——如果您的应用够简单，您最好不要使用Vuex。', '2019-07-23 20:13:13', '2019-08-14 11:14:19', 77, 15, 1, '4,6,12', 'http://yhhu.xyz/nAUpvLNjzkxwks6rvAu9a14i', '', 1);
INSERT INTO `blog` VALUES (24, 'Java并发编程笔记', '最近更新了一系列Java并发编程相关的笔记，不在此一篇篇发出来了，详情见笔记列表第一部分Java并发编程基础篇-(https://github.com/afkbrb/java-concurren', '2019-07-23 20:13:13', '2019-07-23 16:01:51', 1, 0, 0, '1,12', NULL, '', 1);
INSERT INTO `blog` VALUES (27, 'Java之美[从菜鸟到高手演练]之Linux篇——Linux下比较重要的8个命令', '**摘要:**Linux里有很丰富的各种命令，有些是很难用的。然而，学会了前面说的这8个命令，你已经能处理大量的log分析任务了，完全不需要用脚本语言写程序来处理它们。每个程序员，在职业生涯的某个时刻', '2019-07-23 10:13:46', '2019-07-23 10:22:19', 2, 0, 0, '8', NULL, 'https://blog.csdn.net/zhangerqing/article/details/8545715', 2);
INSERT INTO `blog` VALUES (30, 'Oracle约束(Constraint)详解', '概述　　约束是数据库用来确保数据满足业务规则的手段，不过在真正的企业开发中，除了主键约束这类具有强需求的约束，像外键约束，检查约束更多时候仅仅出现在数据库设计阶段，真实环境却很少应用，更多是放到程序逻', '2019-07-23 10:15:35', '2019-08-14 11:21:11', 5, 0, 1, '7', 'http://yhhu.xyz/GSRasMMk25o4Eymsp7hdetNO', 'https://www.cnblogs.com/chengxiao/p/6032183.html', 2);
INSERT INTO `blog` VALUES (31, 'JAVA 对象的序列化', '　　下面将介绍对象的序列化——一种将对象转成字节方便传送到别处或存储在硬盘上，并且再从转化成的字节重构对象的机制。　　序列化是分布式管理必备的工具，分布式处理中将对象从一个虚拟传到另一个虚拟机。序列化', '2019-07-23 10:16:43', '2019-07-23 10:16:43', 1, 0, 0, '1', NULL, 'https://liuyanzhao.com/7649.html', 2);
INSERT INTO `blog` VALUES (32, 'Java 之 BASE64 加密解密', '背景在给邮件发送URL链接找回密码时，会发送一个链接，像这样http://localhost:8080/resetpass?sid=c1b55b980db4eb74a4264a92d53cd953&a', '2019-07-23 10:17:59', '2019-07-23 15:54:39', 4, 1, 1, '1', NULL, 'https://liuyanzhao.com/7578.html', 2);
INSERT INTO `blog` VALUES (33, 'CentOS下docker安装', '前提条件1.Docker运行在CentOS7上，要求系统为64位、系统内核版本为3.10以上。2.Docker运行在CentOS-6.5或更高的版本的CentOS上，要求系统为64位、系统内核版本为2', '2019-08-14 11:02:55', '2019-08-14 11:06:09', 2, 0, 1, '15', NULL, '', 1);
INSERT INTO `blog` VALUES (34, 'SpringCloud学习笔记', 'SpringIPC,AOP。是一个轻量级的JAVAEE开源框架，主要是为了解决企业级开发的复杂度问题，复杂度-耦合度SpringBoot新一代JAVAEE开发标准，开箱即用的特性（不需要过多配置）微服', '2019-08-14 11:07:58', '2019-08-26 15:19:44', 21, 4, 3, '15', NULL, '', 1);

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
INSERT INTO `blog_content` VALUES (1, '## 简介\n\n本博客是一个前后端分离的轻量级个人博客系统\n\n本博客系统由后端服务器系统、后台管理系统、前端系统三个子系统组成。其中，后端服务系统基于SpringBoot，Mybatis，Redis，后台管理系统和前端系统均基于Vue。每个子系统的详细代码可参看相应的Github地址。\n\n## 传送门\n\n- [后端服务系统](https://github.com/yhuihu/blog-back)\n- [后台管理系统](https://github.com/yhuihu/yhhu_blog_admin)\n- [前台系统](https://github.com/yhuihu/yhhu_blog_front)\n\n## BlahBlah\n\n如果本项目对您学习Vue或SpringBoot等有所帮助的话，请帮忙点颗⭐哦😁！\n\n如果您对本项目有什么意见或建议，也欢迎批评指正。\n');
INSERT INTO `blog_content` VALUES (10, '## 基本思想\n\n　　归并排序（MERGE-SORT）是利用归并的思想实现的排序方法，该算法采用经典的分治（divide-and-conquer）策略（分治法将问题分(divide)成一些小的问题然后递归求解，而治(conquer)的阶段则将分的阶段得到的各答案\"修补\"在一起，即分而治之)。\n  \n### 分而治之\n\n![](http://localhost/api/upload/2019/07/24/17da2791-65ca-49f1-a26e-f36f28f4a098.png)\n\n可以看到这种结构很像一棵完全二叉树，本文的归并排序我们采用递归去实现（也可采用迭代的方式去实现）。分阶段可以理解为就是递归拆分子序列的过程，递归深度为log2n。\n\n## 合并相邻有序子序列\n\n　　再来看看治阶段，我们需要将两个已经有序的子序列合并成一个有序序列，比如上图中的最后一次合并，要将[4,5,7,8]和[1,2,3,6]两个已经有序的子序列，合并为最终序列[1,2,3,4,5,6,7,8]，来看下实现步骤。\n  \n  ![](http://localhost/api/upload/2019/07/24/26f6b8ff-6d46-4578-b1c4-15df8ccf58df.png)\n  ![](http://localhost/api/upload/2019/07/24/8b75acaa-ef34-4566-a29c-7ac13fc58d98.png)\n  \n## 代码实现\n\n```java\npackage sortdemo;\n\nimport java.util.Arrays;\n\n/**\n * Created by chengxiao on 2016/12/8.\n */\npublic class MergeSort {\n    public static void main(String []args){\n        int []arr = {9,8,7,6,5,4,3,2,1};\n        sort(arr);\n        System.out.println(Arrays.toString(arr));\n    }\n    public static void sort(int []arr){\n        int []temp = new int[arr.length];//在排序前，先建好一个长度等于原数组长度的临时数组，避免递归中频繁开辟空间\n        sort(arr,0,arr.length-1,temp);\n    }\n    private static void sort(int[] arr,int left,int right,int []temp){\n        if(left<right){\n            int mid = (left+right)/2;\n            sort(arr,left,mid,temp);//左边归并排序，使得左子序列有序\n            sort(arr,mid+1,right,temp);//右边归并排序，使得右子序列有序\n            merge(arr,left,mid,right,temp);//将两个有序子数组合并操作\n        }\n    }\n    private static void merge(int[] arr,int left,int mid,int right,int[] temp){\n        int i = left;//左序列指针\n        int j = mid+1;//右序列指针\n        int t = 0;//临时数组指针\n        while (i<=mid && j<=right){\n            if(arr[i]<=arr[j]){\n                temp[t++] = arr[i++];\n            }else {\n                temp[t++] = arr[j++];\n            }\n        }\n        while(i<=mid){//将左边剩余元素填充进temp中\n            temp[t++] = arr[i++];\n        }\n        while(j<=right){//将右序列剩余元素填充进temp中\n            temp[t++] = arr[j++];\n        }\n        t = 0;\n        //将temp中的元素全部拷贝到原数组中\n        while(left <= right){\n            arr[left++] = temp[t++];\n        }\n    }\n}\n```\n执行结果\n>[1, 2, 3, 4, 5, 6, 7, 8, 9]\n\n## 最后\n\n　　归并排序是稳定排序，它也是一种十分高效的排序，能利用完全二叉树特性的排序一般性能都不会太差。java中Arrays.sort()采用了一种名为TimSort的排序算法，就是归并排序的优化版本。从上文的图中可看出，每次合并操作的平均时间复杂度为O(n)，而完全二叉树的深度为|log2n|。总的平均时间复杂度为O(nlogn)。而且，归并排序的最好，最坏，平均时间复杂度均为O(nlogn)。');
INSERT INTO `blog_content` VALUES (20, '## 问题描述\n\nVuex适合于中大型项目，在小型项目中使用往往是杀鸡用牛刀。\n\n官方说明如下:\n\n> 如果您不打算开发大型单页应用，使用 Vuex 可能是繁琐冗余的。确实是如此——如果您的应用够简单，您最好不要使用 Vuex。\n\n但最近使用自定义的全局变量时，却遇到了`computed`计算属性不能获取全局变量变更后的状态的问题。\n\n```js\n// src/state/index.js\nexport default {\n	state1: \'\',\n	state2: \'\'\n}\n\n// main.js\nimport state from \'./state\'\nVue.prototype.$state = state\n\n// demo.vue\ncomputed: {\n    state1() {\n        console.log(\'in computed...\')\n        return this.$state.state1\n    }\n},\nwatch: {\n    state1: function(newValue) {\n        console.log(\'newValue: \' + newValue)\n    }\n}    \n```\n\n\n\n当通过其他方式改变`state1`的值时，发现`computed`和`watch`的代码并没有执行，查阅了相关资料才发现这种方式创建的全局变量**是不可交互的**。\n\n## 解决方法\n\n```js\n// src/state/index.js\nexport default {\n	state1: \'\',\n	state2: \'\'\n}\n\n// main.js\nimport state from \'./state\'\nnew Vue({\n  el: \'#app\',\n  router,\n  components: { App },\n  template: \'<App/>\'\n  // 关键在此处，注意此处data不是函数形式\n  data: { // only place where data is not a function\n    state\n  },\n})\n\n// demo.vue\ncomputed: {\n    state1() {\n        console.log(\'in computed...\')\n        return this.$root.state.state1\n    }\n},\nwatch: {\n    state1: function(newValue) {\n        console.log(\'newValue: \' + newValue)\n    }\n}  \n```\n\n按上面写法就能成功创建可交互的全局变量了。\n\n\n\n## 参考\n\n[https://cn.vuejs.org/v2/guide/state-management.html#简单状态管理起步使用](https://cn.vuejs.org/v2/guide/state-management.html#简单状态管理起步使用)\n\n[https://stackoverflow.com/questions/51275301/how-to-react-to-a-global-variable-with-vue](https://stackoverflow.com/questions/51275301/how-to-react-to-a-global-variable-with-vue)');
INSERT INTO `blog_content` VALUES (24, '最近更新了一系列Java并发编程相关的笔记，不在此一篇篇发出来了，详情见[java-concurrency-note](https://github.com/afkbrb/java-concurrency-note)\n\n### 笔记列表\n\n#### 第一部分 Java并发编程基础篇\n\n- [第1章 并发编程线程基础](https://github.com/afkbrb/java-concurrency-note/blob/master/01并发编程线程基础.md)\n\n- [第2章 并发编程的其他基础知识](https://github.com/afkbrb/java-concurrency-note/blob/master/02并发编程的其他基础知识.md)\n\n#### 第二部分 Java并发编程高级篇\n\n- [第3章 Java并发包中的ThreadLocalRandom类原理剖析](https://github.com/afkbrb/java-concurrency-note/blob/master/03Java并发包中的ThreadLocalRandom类原理剖析.md)\n\n- [第4章 Java并发包中原子操作类原理剖析](https://github.com/afkbrb/java-concurrency-note/blob/master/04Java并发包中原子操作类原理剖析.md)\n\n- [第5章 Java并发包中并发List源码剖析](https://github.com/afkbrb/java-concurrency-note/blob/master/05Java并发包中并发List源码剖析.md)\n\n- [第6章 Java并发包中锁原理剖析](https://github.com/afkbrb/java-concurrency-note/blob/master/06Java并发包中锁原理剖析.md)\n\n- [第7章 Java并发包中并发队列原理剖析](https://github.com/afkbrb/java-concurrency-note/blob/master/07Java并发包中并发队列原理剖析.md)\n\n- [第8章 Java并发包中线程池ThreadPoolExecutor原理探究](https://github.com/afkbrb/java-concurrency-note/blob/master/08Java并发包中线程池ThreadPoolExecutor原理探究.md)\n\n- [第9章 Java并发包中ScheduledThreadPoolExecutor原理探究](https://github.com/afkbrb/java-concurrency-note/blob/master/09Java并发包中ScheduledThreadPoolExecutor原理探究.md)\n\n- [第10章 Java并发包中线程同步器原理剖析](https://github.com/afkbrb/java-concurrency-note/blob/master/10Java并发包中线程同步器原理剖析.md)\n\n#### 第三部分 Java并发编程实践篇\n\n- [第11章 并发编程实践](https://github.com/afkbrb/java-concurrency-note/blob/master/11并发编程实践.md)');
INSERT INTO `blog_content` VALUES (27, '>**摘要: **Linux里有很丰富的各种命令，有些是很难用的。然而，学会了前面说的这8个命令，你已经能处理大量的log分析任务了，完全不需要用脚本语言写程序来处理它们。\n\n每个程序员，在职业生涯的某个时刻，总会发现自己需要知道一些Linux方面的知识。我并不是说你应该成为一个Linux专家，我的意思是，当面对Linux命令行任务时，你应该能很熟练的完成。事实上，学会了下面8个命令，我基本上能完成任何需要完成的任务。\n\n注意：下面的每个命令都有十分丰富的文档说明。这篇文章并不是来详尽的展示每个命令的各种功用的。我在这里要讲的是这几个最常用的命令的最常见用法。如果你对linux命令并不是很了解，你想找一些这方面的资料学习，那这篇文章将会给你一个基本的指导。\n\n让我们从处理一些数据开始。假设我们有两个文件，分别记录的订单清单和订单处理结果。\n\n>order.out.log  \n  8:22:19 111, 1, Patterns of Enterprise Architecture, Kindle edition, 39.99 \n  8:23:45 112, 1, Joy of Clojure, Hardcover, 29.99  \n  8:24:19 113, -1, Patterns of Enterprise Architecture, Kindle edition, 39.99 \n  order.in.log  \n  8:22:20 111, Order Complete  \n  8:23:50 112, Order sent to fulfillment  \n  8:24:20 113, Refund sent to processing \n\n#### cat -- 连接文件，并输出结果\n\ncat 命令非常的简单，你从下面的例子可以看到。\n\n>jfields$ cat order.out.log   \n8:22:19 111, 1, Patterns of Enterprise Architecture, Kindle edition, 39.99  \n8:23:45 112, 1, Joy of Clojure, Hardcover, 29.99  \n8:24:19 113, -1, Patterns of Enterprise Architecture, Kindle edition, 39.99 \n\n就像它的说明描述的，你可以用它来连接多个文件。\n\n>jfields$ cat order.*   \n8:22:20 111, Order Complete  \n8:23:50 112, Order sent to fulfillment  \n8:24:20 113, Refund sent to processing  \n8:22:19 111, 1, Patterns of Enterprise Architecture, Kindle edition, 39.99  \n8:23:45 112, 1, Joy of Clojure, Hardcover, 29.99  \n8:24:19 113, -1, Patterns of Enterprise Architecture, Kindle edition, 39.99 \n\n如果你想看这些log文件的内容，你可以把它们连接起来并输出到标准输出上，就是上面的例子展示的。这很有用，但输出的内容可以更有逻辑些。\n\n#### sort – 文件里的文字按行排序\n\n此时sort命令显然是你最佳的选择。\n\n>jfields$ cat order.* | sort  \n8::22:19 111, 1, Patterns of Enterprise Architecture, Kindle edition, 39.99  \n8:22:20 111, Order Complete  \n8:23:45 112, 1, Joy of Clojure, Hardcover, 29.99  \n8:23:50 112, Order sent to fulfillment  \n8:24:19 113, -1, Patterns of Enterprise Architecture, Kindle edition, 39.99  \n8:24:20 113, Refund sent to processing \n\n就像上面例子显示的，文件里的数据已经经过排序。对于一些小文件，你可以读取整个文件来处理它们，然而，真正的log文件通常有大量的内容，你不能不考虑这个情况。此时你应该考虑过滤出某些内容，把cat、sort后的内容通过管道传递给过滤工具。\n\n#### grep, egrep, fgrep – 打印出匹配条件的文字行\n\n假设我们只对Patterns of Enterprise Architecture这本书的订单感兴趣。使用grep，我们能限制只输出含有Patterns字符的订单。\n\n>jfields$ cat order.* | sort | grep Patterns  \n8:22:19 111, 1, Patterns of Enterprise Architecture, Kindle edition, 39.99  \n8:24:19 113, -1, Patterns of Enterprise Architecture, Kindle edition, 39.99 \n\n假设退款订单113出了一些问题，你希望查看所有相关订单——你又需要使用grep了。\n\n>jfields$ cat order.* | sort | grep \":\\d\\d 113, \"  \n8:24:19 113, -1, Patterns of Enterprise Architecture, Kindle edition, 39.99  \n8:24:20 113, Refund sent to processing \n\n你会发现在grep上的匹配模式除了“113”外还有一些其它的东西。这是因为113还可以匹配上书目或价格，加上额外的字符后，我们可以精确的搜索到我们想要的东西。\n\n现在我们已经知道了退货的详细信息，我们还想知道日销售和退款总额。但我们只关心《Patterns of Enterprise Architecture》这本书的信息，而且只关心数量和价格。我现在要做到是切除我们不关心的任何信息。\n\n#### cut – 删除文件中字符行上的某些区域\n\n又要使用grep，我们用grep过滤出我们想要的行。有了我们想要的行信息，我们就可以把它们切成小段，删除不需要的部分数据。\n\n>jfields$ cat order.* | sort | grep Patterns  \n8:22:19 111, 1, Patterns of Enterprise Architecture, Kindle edition, 39.99  \n8:24:19 113, -1, Patterns of Enterprise Architecture, Kindle edition, 39.99  \n jfields$ cat order.* | sort | grep Patterns | cut -d\",\" -f2,5  \n 1, 39.99  \n -1, 39.99 \n \n 现在，我们把数据缩减为我们计算想要的形式，把这些数据粘贴到Excel里立刻就能得到结果了。\n\ncut是用来消减信息、简化任务的，但对于输出内容，我们通常会有更复杂的形式。假设我们还需要知道订单的ID，这样可以用来关联相关的其他信息。我们用cut可以获得ID信息，但我们希望把ID放到行的最后，用单引号包上。\n\n#### sed – 一个流编辑器。它是用来在输入流上执行基本的文本变换。\n\n下面的例子展示了如何用sed命令变换我们的文件行，之后我们在再用cut移除无用的信息。\n\n>jfields$ cat order.* | sort | grep Patterns \\  \n>| sed s/\"[0-9\\:]* [0−9]∗[0−9]∗\\, .∗.∗\"/\"\\2, \'\\1\'\"/  \n1, Patterns of Enterprise Architecture, Kindle edition, 39.99, \'111\'  \n-1, Patterns of Enterprise Architecture, Kindle edition, 39.99, \'113\'  \n lmp-jfields01:~ jfields$ cat order.* | sort | grep Patterns \\  \n>| sed s/\"[0-9\\:]* [0−9]∗[0−9]∗\\, .∗.∗\"/\"\\2, \'\\1\'\"/ | cut -d\",\" -f1,4,5  \n1, 39.99, \'111\'  \n-1, 39.99, \'113\' \n\n我们对例子中使用的正则表达式多说几句，不过也没有什么复杂的。正则表达式做了下面几种事情\n\n- 删除时间戳\n- 捕捉订单号\n- 删除订单号后的逗号和空格\n- 捕捉余下的行信息\n\n里面的引号和反斜杠有点乱，但使用命令行时必须要用到这些。\n\n一旦捕捉到了我们想要的数据，我们可以使用 \\1 & \\2 来存储它们，并把它们输出成我们想要的格式。我们还在其中加入了要求的单引号，为了保持格式统一，我们还加入了逗号。最后，用cut命令把不必要的数据删除。\n\n现在我们有麻烦了。我们上面已经演示了如何把log文件消减成更简洁的订单形式，但我们的财务部门需要知道订单里一共有哪些书。\n\n#### uniq – 删除重复的行\n\n下面的例子展示了如何过滤出跟书相关的交易，删除不需要的信息，获得一个不重复的信息。\n\n>jfields$ cat order.out.log | grep \"Kindle∥HardcoverKindle‖Hardcover\" | cut -d\",\" -f3 | sort | uniq -c  \n   1  Joy of Clojure  \n   2  Patterns of Enterprise Architecture \n\n看起来这是一个很简单的任务。\n\n这都是很好用的命令，但前提是你要能找到你想要的文件。有时候你会发现一些文件藏在很深的文件夹里，你根本不知道它们在哪。但如果你是知道你要寻找的文件的名字的话，这对你就不是个问题了。\n\n#### find – 在文件目录中搜索文件\n\n在上面的例子中我们处理了order.in.log和order.out.log这两个文件。这两个文件放在我的home目录里的。下面了例子将向大家展示如何在一个很深的目录结构里找到这样的文件。\n\n>jfields$ find /Users -name \"order*\"  \nUsers/jfields/order.in.log  \nUsers/jfields/order.out.log \n\nfind命令有很多其它的参数，但99%的时间里我只需要这一个就够了。\n\n简单的一行，你就能找到你想要的文件，然后你可以用cat查看它，用cut修剪它。但文件很小时，你用管道把它们输出到屏幕上是可以的，但当文件大到超出屏幕时，你也许应该用管道把它们输出给less命令。\n\n#### less – 在文件里向前或向后移动\n\n让我们再回到简单的 cat | sort 例子中来，下面的命令就是将经过合并、排序后的内容输出到less命令里。在 less 命令，使用“/”来执行向前搜索，使用“？”命令执行向后搜索。搜索条件是一个正则表达式。\n\n>jfields$ cat order* | sort | less \n\n如果你在 less 命令里使用 /113.*，所有113订单的信息都会高亮。你也可以试试?.*112，所有跟订单112相关的时间戳都会高亮。最后你可以用 ‘q’ 来退出less命令。\n\nLinux里有很丰富的各种命令，有些是很难用的。然而，学会了前面说的这8个命令，你已经能处理大量的log分析任务了，完全不需要用脚本语言写程序来处理它们。\n\n这篇文章是之前保存的网络上的一篇文章，遗憾的是已经不知道谁是作者了，所以，如果有侵权，请速与本人联系，定会协作妥善处理！');
INSERT INTO `blog_content` VALUES (30, '## 概述\n\n　　约束是数据库用来确保数据满足业务规则的手段，不过在真正的企业开发中，除了主键约束这类具有强需求的约束，像外键约束，检查约束更多时候仅仅出现在数据库设计阶段，真实环境却很少应用，更多是放到程序逻辑中去进行处理。这也比较容易理解，约束会一定程度上较低数据库性能，有些规则直接在程序逻辑中处理就可以了，同时，也有可能在面对业务变更或是系统扩展时，数据库约束会使得处理不够方便。不过在我看来，数据库约束是保证数据准确性的最后一道防线，对于设计合理的系统，处于性能考虑数据库约束自然可有可无；不过若是面对关联关系较为复杂的系统，且对系统而言，数据的准确性完整性要高于性能要求，那么这些约束还是有必要的（否则，就会出现各种相对业务规则来说莫名其妙的脏数据，本人可是深有体会的。。）。总之，对于约束的选择无所谓合不合理，需要根据业务系统对于准确性和性能要求的侧重度来决定。\n  \n  数据库约束有五种：\n  - 主键约束（PRIMARY KEY）\n  -  唯一性约束（UNIQUE)\n  -  非空约束（NOT NULL)\n  -  外键约束（FOREIGN KEY)\n  -  检查约束（CHECK)\n  \n下面我们就分别来看下这五类约束：\n\n## 数据库约束\n\n### 主键约束（PRIMARY KEY)\n\n　　主键是定位表中单个行的方式，可唯一确定表中的某一行，关系型数据库要求所有表都应该有主键，不过Oracle没有遵循此范例要求，Oracle中的表可以没有主键（这种情况不多见）。关于主键有几个需要注意的点：\n  \n  1. 键列必须必须具有唯一性，且不能为空，其实主键约束 相当于 UNIQUE+NOT NULL\n  2. 一个表只允许有一个主键\n  3. 主键所在列必须具有索引（主键的唯一约束通过索引来实现），如果不存在，将会在索引添加的时候自动创建\n \n\n　　添加主键（约束的添加可在建表时创建，也可如下所示在建表后添加，一般推荐建表后添加，灵活度更高一些，建表时添加某些约束会有限制）\n  \n>SQL> alter table emp add constraint emp_id_pk primary key(id);\n\n### 唯一性约束（UNIQUE)\n\n唯一性约束可作用在单列或多列上，对于这些列或列组合，唯一性约束保证每一行的唯一性。\nUNIQUE需要注意：\n1.  对于UNIQUE约束来讲，索引是必须的。如果不存在，就自动创建一个（UNIQUE的唯一性本质上是通过索引来保证的）\n2.  UNIQUE允许null值，UNIQUE约束的列可存在多个null。这是因为，Unique唯一性通过btree索引来实现，而btree索引中不包含null。当然，这也造成了在where语句中用null值进行过滤会造成全表扫描。\n\n添加唯一约束\n>SQL> alter table emp add constraint emp_code_uq unique(code);\n\n### 非空约束（NOT NULL)\n\n非空约束作用的列也叫强制列。顾名思义，强制键列中必须有值，当然建表时候若使用default关键字指定了默认值，则可不输入。\n\n添加非空约束，语法较特别\n>SQL> alter table emp modify ename not null;\n\n### 外键约束（FOREIGN KEY）\n\n　　外键约束定义在具有父子关系的子表中，外键约束使得子表中的列对应父表的主键列，用以维护数据库的完整性。不过出于性能和后期的业务系统的扩展的考虑，很多时候，外键约束仅出现在数据库的设计中，实际会放在业务程序中进行处理。外键约束注意以下几点：\n  \n1. 外键约束的子表中的列和对应父表中的列数据类型必须相同，列名可以不同\n2. 对应的父表列必须存在主键约束（PRIMARY KEY）或唯一约束（UNIQUE）\n3. 外键约束列允许NULL值，对应的行就成了孤行了\n\n　　其实很多时候不使用外键，很多人认为会让删除操作比较麻烦，比如要删除父表中的某条数据，但某个子表中又有对该条数据的引用，这时就会导致删除失败。我们有两种方式来优化这种场景：\n\n　　第一种方式简单粗暴，删除的时候，级联删除掉子表中的所有匹配行，在创建外键时，通过 on delete cascade 子句指定该外键列可级联删除：\n  \n>SQL> alter table emp add constraint emp_deptno_fk foreign key(deptno) references dept (deptno) on delete cascade;\n\n第二种方式，删除父表中的对应行，会将对应子表中的所有匹配行的外键约束列置为NULL，通过 on delete set null 子句实施：\n\n>SQL> alter table emp add constraint emp_deptno_fk foreign key(deptno) references dept(deptno) on delete set null;\n\n　　实际上，外键约束列和对应的父表列可以在同一张表中，常见的就是表的业务逻辑含义是一棵树，最简单的例子如下（id为主键id，fid为父id，fid存储对id的引用），这种结构的表根据业务要求可通过Oracle的递归查询来获取这种层级关系\n  \n  ![](http://localhost:8081/static/userImage/2018/12/11/7d820bdb-a8b1-49b6-9d17-153c564ea3cc.png)\n  \n ### 检查约束（CHECK)\n \n 检查约束可用来实施一些简单的规则，比如列值必须在某个范围内。检查的规则必须是一个结果为true或false 的表达式，比如：\n \n >SQL> alter table emp add constraint emp_sex_ck check(sex in(\'男\',\'女\'));\n \n ## 约束状态\n \n　　很多时候由于业务需要，比如我们有大量的历史数据，需要和现有数据合并，当前表存在数据库约束（如非空约束），而这些历史数据又包含违背非空约束的数据行，为了避免导入时由于违反约束而导入失败，我们通过调整约束状态来达到目的。\n  \n  数据库约束有两类状态\n  \n**启用/禁用**（*enable/disable*）：是否对新变更的数据启用约束验证\n\n**验证/非验证** (*validate/novalidate*) ：是否对表中已客观存在的数据进行约束验证\n\n这两类四种状态从语法角度讲可以随意组合，默认是 *enable validate*\n\n下面我们来看着四类组合会分别出现什么样的效果：\n\n- *enable validate* : 默认的约束组合状态，无法添加违反约束的数据行，数据表中也不能存在违反约束的数据行；\n- *enable novalidate* : 无法添加违反约束的数据行，但对已存在的违反约束的数据行不做验证；\n- *disable validate* : 可以添加违反约束的数据行，但对已存在的违反约束的数据行会做约束验证（从描述中可以看出来，这本来就是一种相互矛盾的约束组合，只不过是语法上支持这种组合罢了，造成的结果就是会导致DML失败）\n- *disable novalidate* : 可以添加违法约束的数据行，对已存在的违反约束的数据行也不做验证。\n\n拿上面的例子来说，我们需要上传大量违反非空约束的历史数据（从业务角度讲这些数据不会造成系统功能异常），可以临时将约束状态转为 disable novalidate，以保证这些不合要求的数据导入表中\n>SQL> alter table emp modify constraint emp_ename_nn disable novalidate;\n\n在数据导入完成之后，我们再将约束状态转为enable novalidate 以确保之后添加的数据不会再违反约束\n>SQL> alter table emp modify constraint emp_ename_nn enable novalidate;\n\n## 总结\n\n　　本文介绍了数据库中的五类约束，也提到了数据库约束的四种状态组合，当你由于业务需求或是系统扩展，在一个约束严苛的系统中由于约束限制频繁操作失败的时候，不同组合的约束状态或许能给你另一种处理方案。谢谢支持。');
INSERT INTO `blog_content` VALUES (31, '　　下面将介绍对象的序列化——一种将对象转成字节方便传送到别处或存储在硬盘上，并且再从转化成的字节重构对象的机制。\n\n　　序列化是分布式管理必备的工具，分布式处理中将对象从一个虚拟传到另一个虚拟机。序列化也被用于故障转移和负载均衡方面，序列化对象可以从一个服务器移到另一个服务器。如果你开发过服务器端软件，就会经常需要序列化。下面介绍如何序列化。(摘自 《Core Java》)\n  \n  ## 一、简单的一个例子\n  \n  Person.java\n  \n  ```java\nimport java.io.Serializable;\n\npublic class Person implements Serializable {\n    private String name;\n    private int age;\n    private String sex;\n    public Person(String name, int age, String sex) {\n        this.name = name;\n        this.age = age;\n        this.sex = sex;\n    }\n    @Override\n    public String toString() {\n        return \"Person{\" +\n                \"name=\'\" + name + \'\\\'\' +\n                \", age=\" + age +\n                \", sex=\'\" + sex + \'\\\'\' +\n                \'}\';\n    }\n}\n  ```\n  \n  TestObjSerializeAndDeserialize.java\n  \n  ```java\nimport java.io.*;\n\npublic class TestObjSerializeAndDeserialize {\n    public static void main(String[] args) throws Exception {\n        //1、序列化Person对象\n        SerializePerson();\n        //2、反序列Person对象\n        Person p = DeserializePerson();\n        System.out.println(p);\n    }\n    /**\n     * 序列化\n     * @throws FileNotFoundException\n     * @throws IOException\n     */\n    private static void SerializePerson() throws FileNotFoundException, IOException {\n        Person person = new Person(\"言曌\",18,\"男\");\n        // ObjectOutputStream 对象输出流，将Person对象存储到指定路径下的Person.txt文件中，完成对Person对象的序列化操作\n        ObjectOutputStream oo = new ObjectOutputStream(new FileOutputStream(new File(\"/Users/liuyanzhao/Desktop/temp/Person.txt\")));\n        oo.writeObject(person);\n        System.out.println(\"Person对象序列化成功！\");\n        oo.close();\n    }\n    /**\n     * 反序列化\n     * @return\n     * @throws Exception\n     * @throws IOException\n     */\n    private static Person DeserializePerson() throws Exception, IOException {\n        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(\"/Users/liuyanzhao/Desktop/temp/Person.txt\")));\n        Person person = (Person) ois.readObject();\n        System.out.println(\"Person对象反序列化成功！\");\n        return person;\n    }\n}\n  ```\n  \n  1、我们可以先注释掉 main 方法里的反序列化的那两行代码，先执行序列化。然后就可以在指定目录，看到一个刚才创建的 Person.txt 文件。\n  使用 Sublime 打开以下\n  \n  >   aced 0005 7372 0006 5065 7273 6f6e 6299\no  e8ea 3077 30c4 0200 0349 0003 6167 654c\no  0004 6e61 6d65 7400 124c 6a61 7661 2f6c\no  616e 672f 5374 7269 6e67 3b4c 0003 7365\no  7871 007e 0001 7870 0000 0012 7400 06e8\no  a880 e69b 8c74 0003 e794 b7\n一堆十六进制编码。\n\n2、然后注释掉序列化对象部分，运行反序列化部分。\n运行效果图如下\n![](http://localhost:8081/static/userImage/2018/12/11/97314255-f173-4891-b781-615be6434df1.png)\n\n## 二、为什么要手动设置 serialVersionUID\n\n通常我们有时候在 Person 类里不写\n\n>   private static final long serialVersionUID = 1L;\no\n也能正常序列化和反序列化。\n\n因为系统会自带帮我们创建一个 serialVersionUID。\n\n下面测试一个例子，不设置 serialVersionUID ，当对象信息改变的时候，会出现什么状况。\n\n \n\n1、先把序列化的那行注释掉，不进行序列化操作。使用刚才生成的 Person.txt\n\n2、在 Person 里添加一个属性 phone\n\n3、运行反序列化，会报一个异常\n\n> Exception in thread \"main\" java.io.InvalidClassException: Person; local class incompatible: stream classdesc serialVersionUID = 899743677678844260, local class serialVersionUID = -2823344428596659768\nat java.io.ObjectStreamClass.initNonProxy(ObjectStreamClass.java:616)\nat java.io.ObjectInputStream.readNonProxyDesc(ObjectInputStream.java:1843)\nat java.io.ObjectInputStream.readClassDesc(ObjectInputStream.java:1713)\nat java.io.ObjectInputStream.readOrdinaryObject(ObjectInputStream.java:2000)\nat java.io.ObjectInputStream.readObject0(ObjectInputStream.java:1535)\nat java.io.ObjectInputStream.readObject(ObjectInputStream.java:422)\nat TestObjSerializeAndDeserialize.DeserializePerson(TestObjSerializeAndDeserialize.java:43)\nat TestObjSerializeAndDeserialize.main(TestObjSerializeAndDeserialize.java:15)\n\n因为在序列化的时候，将对象写入文件的时候，会写入类名和所有实例变量的名称和值。\n\n其中 serialVersionUID 因为没有设置默认值，系统会自动根据哈希值生成一个。如果类的实现发生改变，那么 serialVersionUID 也会发生改变。\n\n \n\n相反，如果我们在序列化之前加上\n\n>private static final long serialVersioLnUID = 1L;\n\n \n\n然后序列化，然后给 Person 类加上一个 phone 字段。\n\n这时候就不会报异常了。\n\n![](http://localhost:8081/static/userImage/2018/12/11/0239be42-3cd8-47be-8eb5-7c3355ca26e3.png)\n\n至于 serialVersioLnUID 等于几并不重要，但是该属性的修饰和类型必须为 final long。\n\n## 三、使用 transient 标记不需要序列化的字段\n\n有些实例变量是不需要序列化的——例如当一个对象保留缓存值的时候，一般也不需要序列化该缓存值，重新计算缓存值而不是存储缓存值可能更好。\n\n为了实现某些实例变量不序列化，简单的方法就是给这个变量添加一个 transient 修饰符，打过 transient 标记的字段在序列化的时候就会背忽略。\n\nPerson.java\n\n```java\nimport java.io.Serializable;\n\npublic class Person implements Serializable {\n    private static final long serialVersioLnUID = 1L;\n    private String name;\n    private transient int age;\n    private  String sex;\n    private transient String phone;\n    public Person(String name, int age, String sex, String phone) {\n        this.name = name;\n        this.age = age;\n        this.sex = sex;\n        this.phone = phone;\n    }\n    @Override\n    public String toString() {\n        return \"Person{\" +\n                \"name=\'\" + name + \'\\\'\' +\n                \", age=\" + age +\n                \", sex=\'\" + sex + \'\\\'\' +\n                \", phone=\'\" + phone + \'\\\'\' +\n                \'}\';\n    }\n}\n```\n\n进行序列化和反序列化，效果图如下\n\n![14.png](http://localhost/api/upload/2019/07/23/ad9342d0-363c-4c26-8f66-cd14466c6614.png)\n\n其中 age 和 phone 是没有被序列化的，所有反序列化的时候也是没有值的。因为 age 是 int 类型，默认值是 0，而 phone 是 String 类型的，默认是 null。\n\n>  参考：《Core Java for the Impatient》');
INSERT INTO `blog_content` VALUES (32, '# 背景\n\n在给邮件发送 URL 链接找回密码时，会发送一个链接，像这样\n\n`http://localhost:8080/resetpass?sid=c1b55b980db4eb74a4264a92d53cd953&account=saysky`\n\n验证原理就是，当用户点击链接，然后请求 控制器里的 resetpass 方法，获得 sid(密钥) 和 account(用户名) 两个参数。根据用户名去数据库中的 “邮件找回密码” 表(mail_retrieve)里寻找记录，获得 mailRetrieve 对象，然后比较 参数中的 sid 和 mailRetrieve.getSid() 即可，当然也可以加一个outtimes 超时时间 字段。\n\n如上链接，sid 是经过 MD5 加密，并且无需解密，只需要比较参数dis和数据库sid是否相等，而account 用户名一定要传过去，目前是直接明文显示，感觉不是特别好，所以这里需要给 accout 也加密一下比较好。然后在 控制器里接受参数的时候，然后在解密即可。\n\n目标是，无需太复杂，肉眼无法识别即可，但求效率高。\n# BASE64 加密解码\n```java\npackage com.liuyanzhao.chuyun.util;\nimport sun.misc.BASE64Decoder;\nimport sun.misc.BASE64Encoder;\nimport java.io.UnsupportedEncodingException;\n/**\n * @author 言曌\n * @date 2018/2/24 上午11:45\n */\npublic class Base64Util {\n    /**\n     * 加密\n     * @param str\n     * @return\n     */\n    @SuppressWarnings(\"restriction\")\n    public static String encode(String str) {\n        byte[] b = null;\n        String s = null;\n        try {\n            b = str.getBytes(\"utf-8\");\n        } catch (UnsupportedEncodingException e) {\n            e.printStackTrace();\n        }\n        if (b != null) {\n            s = new BASE64Encoder().encode(b);\n        }\n        return s;\n    }\n    /**\n     * 解密\n     * @param s\n     * @return\n     */\n    @SuppressWarnings(\"restriction\")\n    public static String decode(String s) {\n        byte[] b = null;\n        String result = null;\n        if (s != null) {\n            BASE64Decoder decoder = new BASE64Decoder();\n            try {\n                b = decoder.decodeBuffer(s);\n                result = new String(b, \"utf-8\");\n            } catch (Exception e) {\n                e.printStackTrace();\n            }\n        }\n        return result;\n    }\n    public static void main(String args[]) {\n        Long startTime = System.currentTimeMillis();\n        String rawString = \"loveluoqi\";\n        String encodedString = encode(rawString);\n        String decodedString = decode(encodedString);\n        System.out.println(rawString);\n        System.out.println(encodedString);\n        System.out.println(decodedString);\n        Long endTime = System.currentTimeMillis();\n        System.out.println(\"总共耗时毫秒数：\" + (endTime - startTime));\n    }\n}\n```\n运行结果如下\n![15.png](http://localhost/api/upload/2019/07/23/8bbbd84e-bf9a-4e1a-b23b-95d65e1aae04.png)');
INSERT INTO `blog_content` VALUES (33, '## 前提条件\n\n 1. Docker 运行在 CentOS 7 上，要求系统为64位、系统内核版本为 3.10 以上。\n 2. Docker 运行在 CentOS-6.5 或更高的版本的 CentOS 上，要求系统为64位、系统内核版本为 2.6.32-431 或者更高版本。\n\n## 移除旧版本以及相关内容\n\n```\n	sudo yum remove docker \\\n                  docker-client \\\n                  docker-client-latest \\\n                  docker-common \\\n                  docker-latest \\\n                  docker-latest-logrotate \\\n                  docker-logrotate \\\n                  docker-selinux \\\n                  docker-engine-selinux \\\n                  docker-engine\n```\n![在这里插入图片描述](https://img-blog.csdnimg.cn/201908102327229.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl8zOTYxMzY4OQ==,size_16,color_FFFFFF,t_70)\n## 安装依赖包\n```\nyum install -y yum-utils \\ device-mapper-persistent-data \\  lvm2\n```\n## 更新并安装Docker CE\n\n```\n//添加软件源\nyum-config-manager --add-repo http://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo\n//更新并安装docker-ce\nyum update && yum -y install docker-ce\n```\n### 验证安装\n\n - 启动docker\n```\nsystemctl start docker\n```\n - 查看信息\n\n```\ndocker version\n```\n![在这里插入图片描述](https://img-blog.csdnimg.cn/20190810234533945.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl8zOTYxMzY4OQ==,size_16,color_FFFFFF,t_70)\n## 设置阿里云镜像加速\n![在这里插入图片描述](https://img-blog.csdnimg.cn/2019081023571344.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl8zOTYxMzY4OQ==,size_16,color_FFFFFF,t_70)\n根据提示开通服务后执行以下操作（镜像加速器有很多，在此用的是阿里云的）\n![根据操作文档操作](https://img-blog.csdnimg.cn/2019081023593413.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl8zOTYxMzY4OQ==,size_16,color_FFFFFF,t_70)\n根据操作文档操作即可\n![在这里插入图片描述](https://img-blog.csdnimg.cn/20190811000152894.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl8zOTYxMzY4OQ==,size_16,color_FFFFFF,t_70)\n### 验证配置\n\n```\ndocker info\n```\n![在这里插入图片描述](https://img-blog.csdnimg.cn/20190811000452194.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl8zOTYxMzY4OQ==,size_16,color_FFFFFF,t_70)\n## 尝试安装tomcat镜像\n\n```\ndocker pull tomcat\n```\n![在这里插入图片描述](https://img-blog.csdnimg.cn/20190811000711255.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl8zOTYxMzY4OQ==,size_16,color_FFFFFF,t_70)\n## 启动tomcat\n\n```\ndocker run -p 8080:8080 tomcat\n```\n\n![在这里插入图片描述](https://img-blog.csdnimg.cn/20190811000930247.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl8zOTYxMzY4OQ==,size_16,color_FFFFFF,t_70)');
INSERT INTO `blog_content` VALUES (34, '### Spring\nIPC,AOP。是一个轻量级的JAVAEE开源框架，主要是为了解决企业级开发的复杂度问题，复杂度->耦合度\n### SpringBoot\n新一代JAVAEE开发标准，开箱即用的特性（不需要过多配置）\n### 微服务架构\n更好的进行分布式系统开发，拆分单体应用，将一个应用拆分成多个服务，每个服务都是一个可以独立运行的项目\n### 分布式系统开发一定会遇到的问题\n1. 客户端如何访问\n2. 服务间如何通信\n3. 服务如何治理\n4. 服务挂了怎么办\n5. 网络是不可靠的\n\n### 解决方案\nSpring Cloud是一套生态，是为了解决微服务架构遇到的问题。想要用Spring Cloud必须基于Spring Boot\n- Spring Cloud Netflix（18年进入维护，停止更新）\n- Apache Dubbo Zookeeper\n\n```\nDubbo是一个高性能的JAVA RPC通信框架\n服务注册于发现，Zookeeper\nAPI网关，没有，再找第三方或自己实现\n```\n- Spring Cloud Alibaba\n\n```\nSpring Cloud Alibaba 致力于提供微服务开发的一站式解决方案。\n此项目包含开发分布式应用微服务的必需组件，方便开发者通过 Spring Cloud 编程模型轻松使用这些组件来开发分布式应用服务。\n依托 Spring Cloud Alibaba，您只需要添加一些注解和少量配置，就可以将 Spring Cloud 应用接入阿里分布式应用解决方案，通过阿里中间件来迅速搭建分布式应用系统。\n```\n- 下一代微服务架构标准 Service Mesh服务网格(Istio)\n- 异步非阻塞通信\n\n```\nNetty -> NIO、AIO\nHTTP -> 应用层，跨防火墙，在不同局域网间通信\nRPC -> 远程过程调用，TCP（传输层） \n        优点：速度快\n        缺点：不能跨防火墙，仅支持局域网通信\n```\n');

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
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

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
) ENGINE = InnoDB AUTO_INCREMENT = 107 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES (106, 34, '1', '2019-08-25 20:57:21', 138, NULL);

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
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of project
-- ----------------------------
INSERT INTO `project` VALUES (1, '客房预订系统', 'https://github.com/yhuihu/HotelsBook', 'http://yhhu.xyz/x4PJYPybcXTz9ikarRBSLrHA', 1);
INSERT INTO `project` VALUES (2, 'github-avatar-generator', 'https://github.com/afkbrb/github-avatar-generator', 'http://yhhu.xyz/8drzCCJGbrVu8wS0QWjW3Aco', 2);

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
) ENGINE = InnoDB AUTO_INCREMENT = 139 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of reader
-- ----------------------------
INSERT INTO `reader` VALUES (138, NULL, NULL, 'SuperTig', '127.0.0.1', 'https://avatars3.githubusercontent.com/u/35516186?v=4', NULL, 0, 35516186);

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
