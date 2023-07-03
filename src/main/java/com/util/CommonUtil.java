package com.util;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CommonUtil {
    
    //VO -> Map
    public static Map<String, String> toMap(Object obj) throws Exception {
        Map<String, String> result = new LinkedHashMap<>();
        
        if(!Objects.isNull(obj)) {
            try {
                for(Field field : obj.getClass().getDeclaredFields()) {
                    field.setAccessible(true);
                    result.put(field.getName(), field.get(obj).toString());
                }
            }
            catch(Exception e) {
                log.error("{}", e);
                
                throw e;
            }
        }
        
        return result;
    }
}