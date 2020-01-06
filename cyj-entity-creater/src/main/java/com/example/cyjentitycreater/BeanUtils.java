package com.example.cyjentitycreater;

import com.example.cyjentitycreater.entity.Entity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class BeanUtils {


    public String[] beanUtils(List<Entity> entityList, String tableName){
        StringBuffer sb = new StringBuffer();
        sb.append("package ").append("请填写包名").append(";\r\n");
        getClass(entityList, sb);
        getBody(entityList, sb, tableName);
        return new String[]{sb.toString(), captureName(underline2Camel(tableName)) + ".java"};
    }

    private void getClass(List<Entity> entityList, StringBuffer sb){
            if (ifDate(entityList)){
                sb.append("import java.sql.Date;\r\n");
            }
            if (ifTimestamp(entityList)){
                sb.append("import java.sql.Timestamp;\r\n");
            }
        sb.append("import javax.persistence.*;\r\n");
        sb.append("import java.io.Serializable;\r\n");
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

    private void getBody(List<Entity> entityList, StringBuffer sb, String tableName){
        sb.append("\r\n");
        sb.append("@Entity\r\n");
        sb.append("@Table(name = \"T_").append(captureName(underline2Camel(tableName))).append("\")\r\n");
        sb.append("public class ").append(captureName(underline2Camel(tableName))).append(" implements Serializable {\r\n");
        //生成无参的构造方法
        sb.append("    public ").append(captureName(underline2Camel(tableName))).append("() {\r\n").append("    }\r\n").append("\r\n");
        //生成有参的构造方法
        sb.append("    public ").append(captureName(underline2Camel(tableName))).append("(");
        entityList.forEach(entity -> sb.append(entity.getEntityProperty()).append(" ").append(underline2Camel(entity.getEntityName())).append(", "));
        sb.deleteCharAt(sb.length() - 2);
        sb.append(") {\r\n");
        entityList.forEach(entity -> sb.append("        this.").append(underline2Camel(entity.getEntityName())).append(" = ").append(underline2Camel(entity.getEntityName())).append(";\r\n"));
        sb.append("    }\r\n").append("\r\n");
        //生成属性注解
        entityList.forEach(entity -> {
            if (entity.getEntityName().equals("id")){
                sb.append("    @Id\r\n");
                sb.append("    @GeneratedValue(strategy=GenerationType.IDENTITY)\r\n");
            }else {
                sb.append("    @Column\r\n");
            }
            sb.append("    private ").append(entity.getEntityProperty()).append(" ").append(underline2Camel(entity.getEntityName())).append(";\r\n");
            sb.append("\r\n");
        });
        //生成重写getter和setter的方法
        generateGetterAndSetter(entityList,sb,tableName);
        sb.append("\r\n");
        //生成重写toString的方法
        generateToString(entityList,sb,tableName);
        sb.append("\r\n");
        //生成重写equals的方法
        generateEquals(entityList,sb,tableName);
        sb.append("\r\n");
        //生成重写hashcode的方法
        generateHashCode(entityList,sb);
        sb.append("}");
    }

    private void generateGetterAndSetter(List<Entity> entityList, StringBuffer sb, String tableName){
        entityList.forEach(entity -> sb.append("    public ").append(entity.getEntityProperty()).append(" ").append("get")
                .append(captureName(underline2Camel(entity.getEntityName()))).append("()").append(" {\r\n").append("        return ")
                .append(underline2Camel(entity.getEntityName())).append(";\r\n").append("    }\r\n").append("\r\n")
                .append("    public void ").append("set").append(captureName(underline2Camel(entity.getEntityName())))
                .append("(").append(entity.getEntityProperty()).append(" ").append(underline2Camel(entity.getEntityName()))
                .append(") {\r\n").append("        this.").append(underline2Camel(entity.getEntityName()))
                .append(" = ").append(underline2Camel(entity.getEntityName())).append(";\r\n").append("    }\r\n").append("\r\n"));
    }

    private void generateToString(List<Entity> entityList, StringBuffer sb, String tableName){
        sb.append("    @Override\r\n");
        sb.append("    public String toString() {\r\n");
        sb.append("        return \"").append(captureName(underline2Camel(tableName))).append("{\" +\r\n");
        entityList.forEach(entity -> {
            if (entity.getEntityName().equals("id") && entity.getEntityProperty().equals("Integer")){
                sb.append("            \"id=\" + id +\r\n");
            }else if (!entity.getEntityProperty().equals("String")){
                sb.append("            \", ").append(entity.getEntityName()).append("=\" + ").append(entity.getEntityName()).append(" + \r\n");
            }else {
                sb.append("            \", ").append(entity.getEntityName()).append("='\" + ").append(entity.getEntityName()).append(" + ").append(" '\\'' ").append(" + \r\n");
            }
        });
        sb.append("            '}'\r\n");
        sb.append("    }\r\n");
    }

    private void generateEquals(List<Entity> entityList, StringBuffer sb, String tableName){
        sb.append("    @Override\r\n");
        sb.append("    public boolean equals(Object o) {\r\n");
        sb.append("        if (this == o) return true;\r\n");
        sb.append("        if (o == null || getClass() != o.getClass()) return false;\r\n");
        sb.append("        ").append(captureName(underline2Camel(tableName))).append(" that = (").append(captureName(underline2Camel(tableName))).append(") o;\r\n");
        entityList.forEach(entity -> {
            if (entity.getEntityName().equals("id") && entity.getEntityProperty().equals("Integer")){
                sb.append("        return Objects.equals(id, that.id) &&\r\n");
            }else {
                sb.append("                Objects.equals(").append(entity.getEntityName()).append(", that.").append(entity.getEntityName()).append(") &&\r\n");
            }
        });
        sb.append("    }\r\n");
    }

    private void generateHashCode(List<Entity> entityList, StringBuffer sb){
        sb.append("    @Override\r\n");
        sb.append("    public int hashCode() {\r\n");
        sb.append("        return Objects.hash(");
        entityList.forEach(entity -> sb.append(entity.getEntityName()).append(", "));
        sb.deleteCharAt(sb.length() - 2);
        sb.append(");\r\n");
        sb.append("    }\r\n");
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
