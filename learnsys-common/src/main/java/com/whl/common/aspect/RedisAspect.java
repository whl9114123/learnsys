package com.whl.common.aspect;

import com.whl.common.exception.RRException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class RedisAspect {

    /**
     * 是否开启redis缓存  true开启   false关闭
     */
    @Value("${learnsys.redis.open: false}")
    private boolean open;

    @Around("execution(* com.whl.common.service.CacheService.*(..))")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Object result = null;
        log.error("redis 开关状态");
        if (open) {
            try {
                result = point.proceed();
            } catch (Exception e) {
                log.error("redis error", e);
                throw new RRException("Redis服务异常");
            }
        }
        return result;
    }
}
