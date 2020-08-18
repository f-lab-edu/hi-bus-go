package com.younghun.hibusgo.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.Duration;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

  /**
   * ObjectMapper 설정 및 등록
   * ObjectMapper는 object와 json 파싱을 해주는 클래스
   * 직렬화를 하기 위해서는 getter가 있어야 값에 대한 접근이 가능하다.
   * 역직렬화를 하기 위해서는 객체 생성을 위한 기본 생성자가 반드시 존재해야하고,
   * 값을 매핑하기 위해서는 AllArgsConstructor나 setter가 존재해야한다.
   */
  @Bean
  public ObjectMapper objectMapper() {
    ObjectMapper mapper = new ObjectMapper();
    return mapper;
  }

  /**
   * Redis Connection Factory 설정
   * connectionFactory는 connection 객체를 생성하여 관리하는 인터페이스입니다.
   * Java의 Redis client는 Jedis와 Lettuce 2가지가 있습니다.
   *
   * Jedis - 멀티쓰레드환경에서 쓰레드 안전을 보장하지 않는다.
   * - Connection pool을 사용하여 성능, 안정성 개선이 가능하지만 Lettuce보다 상대적으로 하드웨어적인 자원이 많이 필요하다.
   * - 비동기 기능을 제공하지 않는다.
   *
   * Lettuce - Netty 기반 redis client library
   * - 비동기로 요청하기 때문에 Jedis에 비해 높은 성능을 가지고 있다.
   * - TPS, 자원사용량 모두 Jedis에 비해 우수한 성능을 보인다는 테스트 사례가 있다.
   */
  @Bean
  public RedisConnectionFactory connectionFactory() {
    LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory();
    return lettuceConnectionFactory;
  }

  /**
   * RedisTemplate은 키,값 및 해시 키/값에 직렬화/역직렬화하기 위한 템플릿 제공 및 설
   * 직렬화는 java object를 redis에 byte로 직렬화한다.
   * 시리얼 라이저를 설정하지 않으면 기본으로 JdkSerializationRedisSerializer 사용
   * JdkSerializationRedisSerializer : redis에 키값을 \xac\xed\x00\x05t\x00\x03key와 같은
   * 바이너리 값으로 저장되기 때문에 문자열로 키값을 저장하기 위해 StringRedisSerializer 설정
   * GenericJackson2JsonRedisSerializer : 객체를 json 형태로 직렬화/json을 객체로 역직렬화한다.
   */
  @Bean
  public RedisTemplate<Object, Object> redisTemplate() {
    RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
    redisTemplate.setConnectionFactory(connectionFactory());
    redisTemplate.setKeySerializer(new StringRedisSerializer());

    //객체를 json 형태로 깨지지 않고 받기 위한 직렬화 작업
    redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer(objectMapper()));
    return redisTemplate;
  }

  /**
   * Redis Cache를 사용하기 위한 cache manager 등록 및 설정
   * entryTtl - 캐시의 TTL(Time To Live)를 설정한다.
   */
  @Bean
  public CacheManager redisCacheManager() {
    RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration
        .defaultCacheConfig()
        .serializeKeysWith(RedisSerializationContext
            .SerializationPair
            .fromSerializer(new StringRedisSerializer()))
        .serializeValuesWith(RedisSerializationContext
            .SerializationPair
            .fromSerializer(new GenericJackson2JsonRedisSerializer()))
        .entryTtl(Duration.ofSeconds(1800));

    RedisCacheManager cacheManager = RedisCacheManager
        .RedisCacheManagerBuilder
        .fromConnectionFactory(connectionFactory())
        .cacheDefaults(redisCacheConfiguration)
        .build();

    return cacheManager;
  }

}
