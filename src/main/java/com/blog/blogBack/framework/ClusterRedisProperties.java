//package com.blog.blogBack.framework;
//
//import lombok.Data;
//import lombok.Getter;
//import lombok.Setter;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.stereotype.Component;
//
///**
// * @description:Reids配置参数
// * @author: Tiger
// * @create: 2019-07-29
// **/
//@Getter
//@Setter
//@ConfigurationProperties(prefix = "redis.cluster")
//public class ClusterRedisProperties {
//    /**
//     *      * 最大连接数
//     *      
//     */
//
////    private int maxTotal;
////    /**
////     *      * 最大空闲数
////     *      
////     */
////
////    private int maxIdle;
////    /**
////     *      * 初始化连接数
////     *      
////     */
////
////    private int minIdle;
////
////    /**
////     *      * 建立连接时最大等待时间，单位毫秒
////     *      
////     */
////
////    private long maxWait;
////
////    /**
////     *      * 客户端超时时间，单位毫秒
////     *      
////     */
////
////    private int timeout;
////
////    /**
////     *      * 指明是否在从池中取出连接前进行检验,如果检验失败,则从池中去除连接并尝试取出另一个
////     *      
////     */
////
////    private boolean testOnBorrow;
////
////    /**
////     *      * 在return给pool时，是否提前进行validate操作
////     *      
////     */
////
////    private boolean testOnReturn;
////    /**
////     *      * 如果为true，表示有一个idle object evitor线程对idle object进行扫描，如果validate失败，此object会被从pool中drop掉；
////     *      
////     */
////
////    private boolean testWhileIdle;
////    /**
////     *      * 配合testWhileIdle使用，表示idle object evitor两次扫描之间要sleep的毫秒数
////     *      
////     */
////    private long timeBetweenEvictionRunsMillis;
//    /**
//     *      * 集群信息
//     *      
//     */
//    private String nodes;
//    private String password;
//}
