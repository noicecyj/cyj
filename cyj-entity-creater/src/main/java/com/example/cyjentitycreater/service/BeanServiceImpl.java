package com.example.cyjentitycreater.service;

import com.example.cyjentitycreater.entity.CreateVO;
import com.example.cyjentitycreater.entity.Entity;
import com.example.cyjentitycreater.utils.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020/1/21 14:46
 */
@Component
public class BeanServiceImpl implements BeanService {

    String idValue = "id";
    String integerValue = "Integer";
    String stringValue = "String";
    String aValue = "A";
    String bValue = "B";
    String cValue = "C";
    String dValue = "D";

    public String[] entityGenerate(CreateVO createVO){
        StringBuffer sb = new StringBuffer();
        sb.append("package ").append("请填写包名").append(";\r\n");
        if (BeanUtils.ifDate(createVO.getEntityData())){
            sb.append("import java.sql.Date;\r\n");
        }
        if (BeanUtils.ifTimestamp(createVO.getEntityData())){
            sb.append("import java.sql.Timestamp;\r\n");
        }
        sb.append("import javax.persistence.*;\r\n");
        sb.append("import java.io.Serializable;\r\n");
        sb.append("\r\n");
        sb.append("@Entity\r\n");
        sb.append("@Table(name = \"T_").append(BeanUtils.captureName(BeanUtils.underline2Camel(createVO.getName()))).append("\")\r\n");
        sb.append("public class ").append(BeanUtils.captureName(BeanUtils.underline2Camel(createVO.getName()))).append(" implements Serializable {\r\n");
        //生成无参的构造方法
        sb.append("    public ").append(BeanUtils.captureName(BeanUtils.underline2Camel(createVO.getName()))).append("() {\r\n").append("    }\r\n").append("\r\n");
        //生成有参的构造方法
        sb.append("    public ").append(BeanUtils.captureName(BeanUtils.underline2Camel(createVO.getName()))).append("(");
        createVO.getEntityData().forEach(entity -> sb.append(entity.getEntityProperty()).append(" ").append(BeanUtils.underline2Camel(entity.getEntityName())).append(", "));
        sb.deleteCharAt(sb.length() - 2);
        sb.append(") {\r\n");
        createVO.getEntityData().forEach(entity -> sb.append("        this.").append(BeanUtils.underline2Camel(entity.getEntityName())).append(" = ").append(BeanUtils.underline2Camel(entity.getEntityName())).append(";\r\n"));
        sb.append("    }\r\n").append("\r\n");
        //生成属性注解
        createVO.getEntityData().forEach(entity -> {
            if (idValue.equals(entity.getEntityName())){
                sb.append("    @Id\r\n");
                sb.append("    @GeneratedValue(strategy=GenerationType.IDENTITY)\r\n");
            }else {
                sb.append("    @Column\r\n");
            }
            sb.append("    private ").append(entity.getEntityProperty()).append(" ").append(BeanUtils.underline2Camel(entity.getEntityName())).append(";\r\n");
            sb.append("\r\n");
        });
//        if(method.contains(aValue)){
//            //生成重写getter和setter的方法
//            generateGetterAndSetter(createVO.getEntityData(),sb);
//            sb.append("\r\n");
//        }
//        if(method.contains(bValue)){
//            //生成重写toString的方法
//            generateToString(createVO.getEntityData(),sb,createVO.getName());
//            sb.append("\r\n");
//        }
//        if(method.contains(cValue)){
//            //生成重写equals的方法
//            generateEquals(createVO.getEntityData(),sb,createVO.getName());
//            sb.append("\r\n");
//        }
//        if(method.contains(dValue)){
//            //生成重写hashcode的方法
//            generateHashCode(createVO.getEntityData(),sb);
//            sb.append("\r\n");
//        }
        sb.append("}");
        return new String[]{sb.toString(), BeanUtils.captureName(BeanUtils.underline2Camel(createVO.getName())) + ".java"};
    }

    @Override
    public void generateGetterAndSetter(List<Entity> entityList, StringBuffer sb){
        entityList.forEach(entity -> sb.append("    public ").append(entity.getEntityProperty()).append(" ").append("get")
                .append(BeanUtils.captureName(BeanUtils.underline2Camel(entity.getEntityName()))).append("()").append(" {\r\n").append("        return ")
                .append(BeanUtils.underline2Camel(entity.getEntityName())).append(";\r\n").append("    }\r\n").append("\r\n")
                .append("    public void ").append("set").append(BeanUtils.captureName(BeanUtils.underline2Camel(entity.getEntityName())))
                .append("(").append(entity.getEntityProperty()).append(" ").append(BeanUtils.underline2Camel(entity.getEntityName()))
                .append(") {\r\n").append("        this.").append(BeanUtils.underline2Camel(entity.getEntityName()))
                .append(" = ").append(BeanUtils.underline2Camel(entity.getEntityName())).append(";\r\n").append("    }\r\n").append("\r\n"));
    }

    @Override
    public void generateToString(List<Entity> entityList, StringBuffer sb, String tableName){
        sb.append("    @Override\r\n");
        sb.append("    public String toString() {\r\n");
        sb.append("        return \"").append(BeanUtils.captureName(BeanUtils.underline2Camel(tableName))).append("{\" +\r\n");
        entityList.forEach(entity -> {
            if (idValue.equals(entity.getEntityName()) && integerValue.equals(entity.getEntityProperty())){
                sb.append("            \"id=\" + id +\r\n");
            }else if (!stringValue.equals(entity.getEntityProperty())){
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
        sb.append("        ").append(BeanUtils.captureName(BeanUtils.underline2Camel(tableName))).append(" that = (").append(BeanUtils.captureName(BeanUtils.underline2Camel(tableName))).append(") o;\r\n");
        entityList.forEach(entity -> {
            if (idValue.equals(entity.getEntityName()) && integerValue.equals(entity.getEntityProperty())){
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
