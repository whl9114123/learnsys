package net.xinhuamm.push.autoconfigure;


import net.xinhuamm.push.autoconfigure.properties.XyPushProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnection;
import org.springframework.data.redis.core.RedisOperations;

/**
 * @author wuhuanling
 */
@Configuration
@ConditionalOnClass({PersonService.class })
@EnableConfigurationProperties(XyPushProperties.class)
public class XyPushAutoConfiguration {
    @Autowired
    private XyPushProperties properties;

    @Bean
    @ConditionalOnMissingBean(PersonService.class)
    @ConditionalOnProperty(prefix = "xhmm.push" ,name = "isOpen",havingValue = "true")// 当容器中没有指定Bean的情况下，自动配置PersonService类
    public PersonService personService(){
        PersonService personService = new personServiceImpl(properties);
        return personService;
    }

}
