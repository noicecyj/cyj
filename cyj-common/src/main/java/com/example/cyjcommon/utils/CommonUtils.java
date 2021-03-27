package com.example.cyjcommon.utils;

import com.alibaba.fastjson.JSONArray;

import javax.persistence.Table;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020/1/21 14:46
 */
public class CommonUtils {

    public static <T> List<T> page(List<T> dataList, int pageSize, int currentPage) {
        List<T> currentPageList = new ArrayList<>();
        if (dataList != null && dataList.size() > 0) {
            int currIdx = (currentPage > 1 ? (currentPage - 1) * pageSize : 0);
            for (int i = 0; i < pageSize && i < dataList.size() - currIdx; i++) {
                T data = dataList.get(currIdx + i);
                currentPageList.add(data);
            }
        }
        return currentPageList;
    }

    public static <T> HashMap<String, T> listToMap(List<T> itemList, String name) {
        HashMap<String, T> map = new HashMap<>(16);
        for (T item : itemList) {
            try {
                map.put(Objects.requireNonNull(getFieldValueByName(item.getClass().getDeclaredField(name).getName(), item)).toString(), item);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
        return map;
    }

    private static Object getFieldValueByName(String fieldName, Object o) {
        try {
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String getter = "get" + firstLetter + fieldName.substring(1);
            Method method = o.getClass().getMethod(getter);
            return method.invoke(o);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 通过获取类上的@Table注解获取表名称
     *
     * @param clazz 类class
     * @return 表名
     */
    public static String getTableName(Class<?> clazz) {
        Table annotation = clazz.getAnnotation(Table.class);
        return annotation.name();
    }

    /**
     * json 转 List<T>
     */
    public static <T> List<T> jsonToList(String jsonString, Class<T> clazz) {
        return JSONArray.parseArray(jsonString, clazz);
    }

}
