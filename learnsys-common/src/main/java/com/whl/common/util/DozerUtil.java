package com.whl.common.util;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.BeanUtils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DozerUtil {
    
    private static Mapper mapper = new DozerBeanMapper();

    public static <S, T> T mapper(S s, Class<T> t) {
        if (s == null) {
            return null;
        }
        return mapper.map(s, t);
    }

    public static void mapper(Object s, Object d) {
        mapper.map(s, d);
    }

    public static <S, T> List<T> mapList(List<S> sourceList, Class<T> t) {
        if (sourceList == null) {
            return null;
        }

        List<T> destinationList = Lists.newArrayList();
        for (Object sourceObject : sourceList) {
            T destinationObject = mapper.map(sourceObject, t);
            destinationList.add(destinationObject);
        }
        return destinationList;
    }

    public static <S, T> Page<T> mapPage(Page<S> sPage, Class<T> t) {
        if (sPage == null || sPage.getRecords() == null) {
            return null;
        }
        Page<T> tPage = new Page<>();
        BeanUtils.copyProperties(sPage, tPage);

        List<S> sourceList = sPage.getRecords();
        List<T> destinationList = Lists.newArrayList();
        for (Object sourceObject : sourceList) {
            T destinationObject = mapper.map(sourceObject, t);
            destinationList.add(destinationObject);
        }
        tPage.setRecords(destinationList);
        return tPage;
    }

    public static Map<String, Object> objectToMap(Object obj) throws Exception {
        if(obj == null)
            return null;

        Map<String, Object> map = new HashMap<>();

        BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor property : propertyDescriptors) {
            String key = property.getName();
            if (key.compareToIgnoreCase("class") == 0) {
                continue;
            }
            Method getter = property.getReadMethod();
            Object value = getter!=null ? getter.invoke(obj) : null;
            map.put(key, value);
        }

        return map;
    }

}
