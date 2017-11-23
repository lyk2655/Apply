package com.ipin.service.rest.helpers;

import org.springframework.cache.interceptor.KeyGenerator;

import java.lang.reflect.Method;

/**
 * Created by longman on 1/14/16.
 */
public class KeyGeneratorImpl implements KeyGenerator {
    //@Override
    public Object generate(Object o, Method method, Object... objects) {
        // This will generate a unique key of the class name, the method name,
        // and all method parameters appended.
        StringBuilder sb = new StringBuilder();
        sb.append(o.getClass().getName());
        sb.append(method.getName());
        for (Object obj : objects) {
        	if(obj != null) {
        		sb.append(obj.toString());
        	}
        }
        return sb.toString();
    }
}
