package com.example.cyjentitycreater.utils;

import com.example.cyjentitycreater.entity.CreateVO;
import com.example.cyjentitycreater.entity.Entity;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020/1/21 14:46
 */
public class BeanUtils {

    private static final Pattern NUMBER_PATTERN = Pattern.compile("([A-Za-z\\d]+)(_)?");

    public static String underline2Camel(String line) {
        StringBuilder sb = new StringBuilder();
        Matcher matcher = NUMBER_PATTERN.matcher(line);
        while (matcher.find()) {
            String word = matcher.group();
            sb.append(matcher.start() == 0 ? Character.toLowerCase(word.charAt(0)) : Character.toUpperCase(word.charAt(0)));
            int index = word.lastIndexOf('_');
            if (index > 0) {
                sb.append(word.substring(1, index).toLowerCase());
            } else {
                sb.append(word.substring(1).toLowerCase());
            }
        }
        return sb.toString();
    }

    public static String captureName(String name) {
        char[] cs = name.toCharArray();
        cs[0] -= 32;
        return String.valueOf(cs);
    }

    public static Boolean ifDate(List<Entity> entityList) {
        for (Entity entity : entityList) {
            if ("Date".equals(entity.getEntityProperty())) {
                return true;
            }
        }
        return false;
    }

    public static Boolean ifTimestamp(List<Entity> entityList) {
        for (Entity entity : entityList) {
            if ("Timestamp".equals(entity.getEntityProperty())) {
                return true;
            }
        }
        return false;
    }

    public static String entityName(CreateVO createVO) {
        return BeanUtils.captureName(BeanUtils.underline2Camel(createVO.getName())) + createVO.getType() + ".java";
    }

    public static String entityDaoName(CreateVO createVO) {
        return BeanUtils.captureName(BeanUtils.underline2Camel(createVO.getName())) + "Dao.java";
    }

    public static String entityServiceName(CreateVO createVO) {
        return BeanUtils.captureName(BeanUtils.underline2Camel(createVO.getName())) + "Service.java";
    }

    public static String entityServiceImplName(CreateVO createVO) {
        return BeanUtils.captureName(BeanUtils.underline2Camel(createVO.getName())) + "ServiceImpl.java";
    }

    public static String controllerName() {
        return "IndexController.java";
    }

}
