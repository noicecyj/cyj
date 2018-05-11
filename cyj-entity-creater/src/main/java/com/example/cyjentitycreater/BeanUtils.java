package com.example.cyjentitycreater;

import com.example.cyjentitycreater.entity.Entity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class BeanUtils {

    @Value("${Path.path}")
    private String Path;

    @Value("${Path.referencePath}")
    private String referencePath;


    public void beanUtils(List<Entity> entityList,String tableName){
        StringBuffer sb = new StringBuffer();
        sb.append("package ").append(referencePath).append(";\r\n");
        sb = getClass(entityList,sb);
        sb = getBody(entityList,sb,tableName);
        try {
            FileWriter fw=new FileWriter(Path + captureName(underline2Camel(tableName)) + ".java");
            fw.write(sb.toString());
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(sb);
    }

    private StringBuffer getClass(List<Entity> entityList, StringBuffer sb){
            if (ifDate(entityList)){
                sb.append("import java.sql.Date;\r\n");
            }
            if (ifTimestamp(entityList)){
                sb.append("import java.sql.Timestamp;\r\n");
            }
        sb.append("import javax.persistence.Column;\r\n");
        sb.append("import javax.persistence.Entity;\r\n");
        sb.append("import javax.persistence.Id;\r\n");
        sb.append("import javax.persistence.Table;\r\n");
        return sb;
    }

    private Boolean ifDate(List<Entity> entityList){
        for (Entity entity:entityList){
            if (entity.getEntityProperty().equals("Date")){
                return true;
            }
        }
        return false;
    }

    private Boolean ifTimestamp(List<Entity> entityList){
        for (Entity entity:entityList){
            if (entity.getEntityProperty().equals("Timestamp")){
                return true;
            }
        }
        return false;
    }

    private StringBuffer getBody(List<Entity> entityList, StringBuffer sb, String tableName){
        sb.append("\r\n");
        sb.append("@Entity\r\n");
        sb.append("@Table(name = \"T_").append(captureName(underline2Camel(tableName))).append("\")\r\n");
        sb.append("public class ").append(captureName(underline2Camel(tableName))).append(" {\r\n");
        for (Entity entity:entityList){
            if (entity.getEntityAuto().equals("true")){
                sb.append("    @Id\r\n");
                sb.append("    private ")
                        .append(entity.getEntityProperty())
                        .append(" ")
                        .append(underline2Camel(entity.getEntityName()))
                        .append(";\r\n");
                sb.append("\r\n");
            }else {
                sb.append("    @Column\r\n");
                sb.append("    private ")
                        .append(entity.getEntityProperty())
                        .append(" ")
                        .append(underline2Camel(entity.getEntityName()))
                        .append(";\r\n");
                sb.append("\r\n");
            }
        }
        for (Entity entity:entityList){
            sb.append("    public ")
                    .append(entity.getEntityProperty())
                    .append(" ")
                    .append("get")
                    .append(captureName(underline2Camel(entity.getEntityName())))
                    .append("()")
                    .append(" {\r\n")
                    .append("        return ")
                    .append(underline2Camel(entity.getEntityName()))
                    .append(";\r\n")
                    .append("    }\r\n")
                    .append("\r\n")
                    .append("    public void ")
                    .append("set")
                    .append(captureName(underline2Camel(entity.getEntityName())))
                    .append("(")
                    .append(entity.getEntityProperty())
                    .append(" ")
                    .append(underline2Camel(entity.getEntityName()))
                    .append(") {\r\n")
                    .append("        this.")
                    .append(underline2Camel(entity.getEntityName()))
                    .append(" = ")
                    .append(underline2Camel(entity.getEntityName()))
                    .append(";\r\n")
                    .append("    }\r\n")
                    .append("\r\n");

        }
        sb.append("}");
        return sb;
    }

    private static String underline2Camel(String line){
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

    private static String captureName(String name) {
        char[] cs=name.toCharArray();
        cs[0]-=32;
        return String.valueOf(cs);
    }

}
