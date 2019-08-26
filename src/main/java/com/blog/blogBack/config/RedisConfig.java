package com.blog.blogBack.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

/**
 * @description:
 * @author: Tiger
 * @create: 2019-07-29
 **/
@Configuration
//@EnableConfigurationProperties(ClusterRedisProperties.class)
public class RedisConfig extends CachingConfigurerSupport {

//    @Autowired
//    private ClusterRedisProperties clusterRedisProperties;
//
//    @Bean
//    public JedisPoolConfig jedisPoolConfig() {
//        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
//        jedisPoolConfig.setMaxTotal(clusterRedisProperties.getMaxTotal());
//        jedisPoolConfig.setMaxIdle(clusterRedisProperties.getMaxIdle());
//        jedisPoolConfig.setMinIdle(clusterRedisProperties.getMinIdle());
//        jedisPoolConfig.setMaxWaitMillis(clusterRedisProperties.getMaxWait());
//        jedisPoolConfig.setTestOnBorrow(clusterRedisProperties.isTestOnBorrow());
//        jedisPoolConfig.setTestOnReturn(clusterRedisProperties.isTestOnReturn());
//        jedisPoolConfig.setTestWhileIdle(clusterRedisProperties.isTestWhileIdle());
//        jedisPoolConfig.setTimeBetweenEvictionRunsMillis(clusterRedisProperties.getTimeBetweenEvictionRunsMillis());
//        return jedisPoolConfig;
//    }

    /**
     *      * redis集群配置
     *      * @return
     *      
     */
//    @Bean
//    public RedisClusterConfiguration redisClusterConfiguration() {
//        Map<String, Object> clusterPropertie = new HashMap<>();
//        clusterPropertie.put("spring.redis.cluster.nodes", clusterRedisProperties.getNodes());
//        clusterPropertie.put("spring.redis.cluster.max-redirects", clusterRedisProperties.getMaxRedirects());
//
//        MapPropertySource mapPropertySource = new MapPropertySource("redis.properties", clusterPropertie);
//        return new RedisClusterConfiguration(mapPropertySource);
//        RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration();
//        //Set<RedisNode> clusterNodes
//        String[] serverArray = clusterRedisProperties.getNodes().split(",");
//        Set<RedisNode> nodes = new HashSet<RedisNode>();
//        for(String ipPort:serverArray){
//            String[] ipAndPort = ipPort.split(":");
//            nodes.add(new RedisNode(ipAndPort[0].trim(),Integer.valueOf(ipAndPort[1])));
//        }
//        redisClusterConfiguration.setClusterNodes(nodes);
//        redisClusterConfiguration.setMaxRedirects(clusterRedisProperties.getMaxRedirects());
//        redisClusterConfiguration.setPassword(RedisPassword.of(clusterRedisProperties.getPassword()));
//        return redisClusterConfiguration;
//    }

    /**
     *      * 配置jedis连接工厂
     *      * @param redisClusterConfiguration
     *      * @param jedisPoolConfig
     *      * @return
     *      
     */
//    @Bean
//    public JedisConnectionFactory jedisConnectionFactory(RedisClusterConfiguration redisClusterConfiguration, LettucePoolingClientConfiguration ) {
//        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(redisClusterConfiguration, jedisPoolConfig);
//        jedisConnectionFactory.setTimeout(10000);
//        return jedisConnectionFactory;
//    }

    /**
     * 设置数据存入redis 的序列化方式
     * </br>redisTemplate序列化默认使用的jdkSerializeable,存储二进制字节码,导致key会出现乱码，所以自定义
     * 序列化类
     *
     * @param lettuceConnectionFactory
     */
    @Bean
    public RedisTemplate<Object, Object> redisTemplate(LettuceConnectionFactory lettuceConnectionFactory) {
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(lettuceConnectionFactory);
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    @Bean
    public CacheManager cacheManager(RedisTemplate redisTemplate) {
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofDays(1)); // 设置缓存有效期一天
        RedisConnectionFactory redisConnectionFactory = redisTemplate.getConnectionFactory();
        return RedisCacheManager
                .builder(RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory))
                .cacheDefaults(redisCacheConfiguration).build();
    }
}