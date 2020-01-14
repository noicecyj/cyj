package com.example.cyjentitycreater.utils;

import com.example.cyjentitycreater.entity.Entity;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class beanUtils {

    public static String underline2Camel(String line){
        StringBuilder sb=new StringBuilder();
        Pattern pattern=Pattern.compile("([A-Za-z\\d]+)(_)?");
        Matcher matcher=pattern.matcher(line);
        while(matcher.find()){
            String word=matcher.group();
            sb.append(matcher.start() == 0 ?Character.toLowerCase(word.charAt(0)):Character.toUpperCase(word.charAt(0)));
            int index=word.lastIndexOf('_');
            if(index>0){
                sb.append(word.substring(1, index).toLowerCase());
            }else{
                sb.append(word.substring(1).toLowerCase());
            }
        }
        return sb.toString();
    }

    public static String captureName(String name) {
        char[] cs=name.toCharArray();
        cs[0]-=32;
        return String.valueOf(cs);
    }

    public static Boolean ifDate(List<Entity> entityList){
        for (Entity entity:entityList){
            if (entity.getEntityProperty().equals("Date")){
                return true;
            }
        }
        return false;
    }

    public static Boolean ifTimestamp(List<Entity> entityList){
        for (Entity entity:entityList){
            if (entity.getEntityProperty().equals("Timestamp")){
                return true;
            }
        }
        return false;
    }

}
