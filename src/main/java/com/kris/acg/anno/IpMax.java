package com.kris.acg.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ip 最大访问次数
 *
 * @author Kristin
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface IpMax {
    /**
     * @return 允许访问最大次数
     */
    int count() default 30;

//    /**
//     * 时间段，单位为秒  默认值三十秒
//     * @return 时间段
//     */
//    int time() default 30;
}
