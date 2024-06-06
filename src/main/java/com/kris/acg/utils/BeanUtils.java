package com.kris.acg.utils;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ReflectUtil;


/**
 * @author Kristin
 */
public class BeanUtils {

    public static <T> T copyPropertiesTo(Object source,Class<T> targetType){
        T target=ReflectUtil.newInstance(targetType);
        BeanUtil.copyProperties(source,target,false);
        return target;
    }

}
