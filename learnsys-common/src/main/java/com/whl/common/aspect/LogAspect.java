package com.whl.common.aspect;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Component
@Aspect
@Slf4j
/**
 * 全局拦截器，获取request
 *
 */
public class LogAspect {
//    @Autowired
//    private  cacheService;

//    @Pointcut("execution(* net.xinhuamm.noah.api.controller..*.*(..))")
    public void logPointCut() {

    }
//暂时去掉不用
//    @Around("logPointCut()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {

//        log.info("=====================================Method  start====================================");
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();

            long start = System.currentTimeMillis();
            try {
                //异步加上redis缓存
                String ip = request.getRemoteAddr();
                String key = "ip:" + ip;
//                asycRequestCount(key);

                Object result = joinPoint.proceed();
                long end = System.currentTimeMillis();

                log.info("请求地址:{}", request.getRequestURI());
                log.info("用户IP{}:", request.getRemoteAddr());
                log.info("参数: " + Arrays.toString(joinPoint.getArgs()));
                log.info("执行时间: " + (end - start) + " ms!");
                log.info("=====================================Method  End====================================");
                return result;
            } catch (Throwable e) {
                long end = System.currentTimeMillis();
                log.error("URL:" + request.getRequestURI());
                log.error("IP:" + request.getRemoteAddr());
                log.error("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
                log.error("ARGS : " + Arrays.toString(joinPoint.getArgs()));
                log.error("执行时间: " + (end - start) + " ms!");
                log.error("异常为{}", joinPoint.proceed());
                log.error("=====================================Method  End====================================");
            }

        }
        return joinPoint.proceed();
    }

//    @Async
//public   void  asycRequestCount(String key){
////    Long count = cacheService.getObject(key, Long.class);
//    if (ObjectUtils.isEmpty(count)) {
//        count = new Long(1L);
//    } else {
//        count++;
//    }
//    log.info("{}，count:{}",key,count);
//    cacheService.setObjectSecond(key, count, 100);
//
//
//}

}
