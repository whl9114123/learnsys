package com.whl.learnsys.cms.config.swagger;

import com.whl.common.listener.RedisMessageListener;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

/**
 * @program: learnsys-parent
 * @description:
 * @author: whl
 * @create: 2020-03-27 13:36
 **/
public class RedisConfig {
    @Bean("container")
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
                                            MessageListenerAdapter listenerAdapter) {

        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        LettuceConnectionFactory lettuceConnectionFactory = (LettuceConnectionFactory) connectionFactory;
        //设置存储的节点
        lettuceConnectionFactory.setDatabase(0);
        container.setConnectionFactory(lettuceConnectionFactory);
        //这里要设定监听的主题是chat
        container.addMessageListener(listenerAdapter, new PatternTopic("chat"));
        return container;
    }

    @Bean
    MessageListenerAdapter listenerAdapter(RedisMessageListener receiver) {
        return new MessageListenerAdapter(receiver);
    }

    @Bean
    StringRedisTemplate template(RedisConnectionFactory connectionFactory) {
        return new StringRedisTemplate(connectionFactory);
    }

}
