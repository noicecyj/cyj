package com.example.cyjentitycreater.service;

import com.example.cyjentitycreater.entity.Entity;
import com.example.cyjentitycreater.utils.beanUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BeanServiceImpl implements beanService {

    public String[] entityGenerate(List<Entity> entityList, String tableName,String method){
        StringBuffer sb = new StringBuffer();
        sb.append("package ").append("请填写包名").append(";\r\n");
        if (beanUtils.ifDate(entityList)){
            sb.append("import java.sql.Date;\r\n");
        }
        if (beanUtils.ifTimestamp(entityList)){
            sb.append("import java.sql.Timestamp;\r\n");
        }
        sb.append("import javax.persistence.*;\r\n");
        sb.append("import java.io.Serializable;\r\n");
        sb.append("\r\n");
        sb.append("@Entity\r\n");
        sb.append("@Table(name = \"T_").append(beanUtils.captureName(beanUtils.underline2Camel(tableName))).append("\")\r\n");
        sb.append("public class ").append(beanUtils.captureName(beanUtils.underline2Camel(tableName))).append(" implements Serializable {\r\n");
        //生成无参的构造方法
        sb.append("    public ").append(beanUtils.captureName(beanUtils.underline2Camel(tableName))).append("() {\r\n").append("    }\r\n").append("\r\n");
        //生成有参的构造方法
        sb.append("    public ").append(beanUtils.captureName(beanUtils.underline2Camel(tableName))).append("(");
        entityList.forEach(entity -> sb.append(entity.getEntityProperty()).append(" ").append(beanUtils.underline2Camel(entity.getEntityName())).append(", "));
        sb.deleteCharAt(sb.length() - 2);
        sb.append(") {\r\n");
        entityList.forEach(entity -> sb.append("        this.").append(beanUtils.underline2Camel(entity.getEntityName())).append(" = ").append(beanUtils.underline2Camel(entity.getEntityName())).append(";\r\n"));
        sb.append("    }\r\n").append("\r\n");
        //生成属性注解
        entityList.forEach(entity -> {
            if (entity.getEntityName().equals("id")){
                sb.append("    @Id\r\n");
                sb.append("    @GeneratedValue(strategy=GenerationType.IDENTITY)\r\n");
            }else {
                sb.append("    @Column\r\n");
            }
            sb.append("    private ").append(entity.getEntityProperty()).append(" ").append(beanUtils.underline2Camel(entity.getEntityName())).append(";\r\n");
            sb.append("\r\n");
        });
        if(method.contains("A")){
            //生成重写getter和setter的方法
            generateGetterAndSetter(entityList,sb);
            sb.append("\r\n");
        }
        if(method.contains("B")){
            //生成重写toString的方法
            generateToString(entityList,sb,tableName);
            sb.append("\r\n");
        }
        if(method.contains("C")){
            //生成重写equals的方法
            generateEquals(entityList,sb,tableName);
            sb.append("\r\n");
        }
        if(method.contains("D")){
            //生成重写hashcode的方法
            generateHashCode(entityList,sb);
            sb.append("\r\n");
        }
        sb.append("}");
        return new String[]{sb.toString(), beanUtils.captureName(beanUtils.underline2Camel(tableName)) + ".java"};
    }

    @Override
    public void generateGetterAndSetter(List<Entity> entityList, StringBuffer sb){
        entityList.forEach(entity -> sb.append("    public ").append(entity.getEntityProperty()).append(" ").append("get")
                .append(beanUtils.captureName(beanUtils.underline2Camel(entity.getEntityName()))).append("()").append(" {\r\n").append("        return ")
                .append(beanUtils.underline2Camel(entity.getEntityName())).append(";\r\n").append("    }\r\n").append("\r\n")
                .append("    public void ").append("set").append(beanUtils.captureName(beanUtils.underline2Camel(entity.getEntityName())))
                .append("(").append(entity.getEntityProperty()).append(" ").append(beanUtils.underline2Camel(entity.getEntityName()))
                .append(") {\r\n").append("        this.").append(beanUtils.underline2Camel(entity.getEntityName()))
                .append(" = ").append(beanUtils.underline2Camel(entity.getEntityName())).append(";\r\n").append("    }\r\n").append("\r\n"));
    }

    @Override
    public void generateToString(List<Entity> entityList, StringBuffer sb, String tableName){
        sb.append("    @Override\r\n");
        sb.append("    public String toString() {\r\n");
        sb.append("        return \"").append(beanUtils.captureName(beanUtils.underline2Camel(tableName))).append("{\" +\r\n");
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
    @Override
    public void generateEquals(List<Entity> entityList, StringBuffer sb, String tableName){
        sb.append("    @Override\r\n");
        sb.append("    public boolean equals(Object o) {\r\n");
        sb.append("        if (this == o) return true;\r\n");
        sb.append("        if (o == null || getClass() != o.getClass()) return false;\r\n");
        sb.append("        ").append(beanUtils.captureName(beanUtils.underline2Camel(tableName))).append(" that = (").append(beanUtils.captureName(beanUtils.underline2Camel(tableName))).append(") o;\r\n");
        entityList.forEach(entity -> {
            if (entity.getEntityName().equals("id") && entity.getEntityProperty().equals("Integer")){
                sb.append("        return Objects.equals(id, that.id) &&\r\n");
            }else {
                sb.append("                Objects.equals(").append(entity.getEntityName()).append(", that.").append(entity.getEntityName()).append(") &&\r\n");
            }
        });
        sb.append("    }\r\n");
    }

    @Override
    public void generateHashCode(List<Entity> entityList, StringBuffer sb){
        sb.append("    @Override\r\n");
        sb.append("    public int hashCode() {\r\n");
        sb.append("        return Objects.hash(");
        entityList.forEach(entity -> sb.append(entity.getEntityName()).append(", "));
        sb.deleteCharAt(sb.length() - 2);
        sb.append(");\r\n");
        sb.append("    }\r\n");
    }

}
