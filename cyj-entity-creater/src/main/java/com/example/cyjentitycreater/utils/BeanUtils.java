package com.example.cyjentitycreater.utils;

import com.example.cyjentitycreater.entity.CreateVO;
import com.example.cyjentitycreater.entity.EntityNamePO;
import com.example.cyjentitycreater.entity.EntityPO;

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

    public static String toLowerCaseFirstOne(String s) {
        if (Character.isLowerCase(s.charAt(0))) {
            return s;
        } else {
            return Character.toLowerCase(s.charAt(0)) + s.substring(1);
        }
    }

    public static String captureName(String name) {
        char[] cs = name.toCharArray();
        cs[0] -= 32;
        return String.valueOf(cs);
    }

    public static Boolean ifDate(List<EntityPO> poList) {
        for (EntityPO po : poList) {
            if ("Date".equals(po.getEntityProperty())) {
                return true;
            }
        }
        return false;
    }

    public static Boolean ifTimestamp(List<EntityPO> poList) {
        for (EntityPO po : poList) {
            if ("Timestamp".equals(po.getEntityProperty())) {
                return true;
            }
        }
        return false;
    }

    public static String entityName(CreateVO createVO) {
        return BeanUtils.captureName(BeanUtils.underline2Camel(createVO.getName())) + createVO.getType() + ".java";
    }

    public static String entityDaoName(EntityNamePO po) {
        return BeanUtils.captureName(BeanUtils.underline2Camel(po.getName())) + "Dao.java";
    }

    public static String entityServiceName(EntityNamePO po) {
        return BeanUtils.captureName(BeanUtils.underline2Camel(po.getName())) + "Service.java";
    }

    public static String entityServiceImplName(EntityNamePO po) {
        return BeanUtils.captureName(BeanUtils.underline2Camel(po.getName())) + "ServiceImpl.java";
    }

    public static String entityControllerName(EntityNamePO po) {
        return BeanUtils.captureName(BeanUtils.underline2Camel(po.getName())) + "Controller.java";
    }

    public static String entityControllerImplName(EntityNamePO po) {
        return BeanUtils.captureName(BeanUtils.underline2Camel(po.getName())) + "ControllerImpl.java";
    }

    public static String componentName(EntityNamePO po) {
        return BeanUtils.captureName(BeanUtils.underline2Camel(po.getName())) + ".jsx";
    }
}
