package net.xinhuamm.push.autoconfigure;

import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;


@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(EnableXyPushImportSelector.class)
@Documented
public @interface EnableXyPush {

    @AliasFor("autowired")
    boolean value() default true;

    /**
     * 是否注入
     *
     * @return
     */
    @AliasFor("value")
    boolean autowired() default true;
}

