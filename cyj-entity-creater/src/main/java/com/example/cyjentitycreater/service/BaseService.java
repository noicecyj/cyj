package com.example.cyjentitycreater.service;

import com.example.cyjentitycreater.entity.CreateVO;
import com.example.cyjentitycreater.entity.Entity;
import com.example.cyjentitycreater.utils.BeanUtils;

import java.util.List;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020/1/21 14:46
 */
public abstract class BaseService {

    private final String idValue = "id";
    private final String integerValue = "Integer";
    private final String stringValue = "String";

    /**
     * 生成注解
     *
     * @param createVO 属性列表
     * @param sb       实体类生成字符串
     * @param yes      是否使用lombok
     */
    abstract void generateAnnotation(CreateVO createVO, StringBuffer sb, String yes);

    /**
     * 生成属性
     *
     * @param createVO 属性列表
     * @param sb       实体类生成字符串
     */
    abstract void generateProperty(CreateVO createVO, StringBuffer sb);

    /**
     * 生成重写构造方法
     *
     * @param createVO 属性列表
     * @param sb       实体类生成字符串
     */
    void generateConstructor(CreateVO createVO, StringBuffer sb) {
        sb.append("    public ")
                .append(BeanUtils.captureName(BeanUtils.underline2Camel(createVO.getName())))
                .append("() {\r\n")
                .append("    }\r\n")
                .append("\r\n");
        sb.append("    public ")
                .append(BeanUtils.captureName(BeanUtils.underline2Camel(createVO.getName())))
                .append("(");
        createVO.getEntityData().forEach(entity -> sb.append(entity.getEntityProperty()).append(" ")
                .append(BeanUtils.underline2Camel(entity.getEntityName())).append(", "));
        sb.deleteCharAt(sb.length() - 2);
        sb.append(") {\r\n");
        createVO.getEntityData().forEach(entity -> sb.append("        this.")
                .append(BeanUtils.underline2Camel(entity.getEntityName())).append(" = ")
                .append(BeanUtils.underline2Camel(entity.getEntityName())).append(";\r\n"));
        sb.append("    }\r\n")
                .append("\r\n");
    }

    /**
     * 生成重写getter和setter的方法
     *
     * @param entityList 属性列表
     * @param sb         实体类生成字符串
     */
    void generateGetterAndSetter(List<Entity> entityList, StringBuffer sb) {
        entityList.forEach(entity -> sb.append("    public ")
                .append(entity.getEntityProperty()).append(" ").append("get")
                .append(BeanUtils.captureName(BeanUtils.underline2Camel(entity.getEntityName())))
                .append("()").append(" {\r\n").append("        return ")
                .append(BeanUtils.underline2Camel(entity.getEntityName()))
                .append(";\r\n").append("    }\r\n").append("\r\n")
                .append("    public void ").append("set")
                .append(BeanUtils.captureName(BeanUtils.underline2Camel(entity.getEntityName())))
                .append("(").append(entity.getEntityProperty())
                .append(" ").append(BeanUtils.underline2Camel(entity.getEntityName()))
                .append(") {\r\n").append("        this.")
                .append(BeanUtils.underline2Camel(entity.getEntityName()))
                .append(" = ").append(BeanUtils.underline2Camel(entity.getEntityName()))
                .append(";\r\n").append("    }\r\n").append("\r\n"));
    }

    /**
     * 生成重写getter和setter的方法
     *
     * @param entityList 属性列表
     * @param sb         实体类生成字符串
     * @param tableName  表名
     */
    void generateToString(List<Entity> entityList, StringBuffer sb, String tableName) {
        sb.append("    @Override\r\n");
        sb.append("    public String toString() {\r\n");
        sb.append("        return \"")
                .append(BeanUtils.captureName(BeanUtils.underline2Camel(tableName))).append("{\" +\r\n");
        entityList.forEach(entity -> {
            if (idValue.equals(entity.getEntityName()) && integerValue.equals(entity.getEntityProperty())) {
                sb.append("            \"id=\" + id +\r\n");
            } else if (!stringValue.equals(entity.getEntityProperty())) {
                sb.append("            \", ").append(entity.getEntityName())
                        .append("=\" + ").append(entity.getEntityName()).append(" + \r\n");
            } else {
                sb.append("            \", ").append(entity.getEntityName())
                        .append("='\" + ").append(entity.getEntityName()).append(" + ")
                        .append(" '\\'' ").append(" + \r\n");
            }
        });
        sb.append("            '}'\r\n");
        sb.append("    }\r\n");
    }

    /**
     * 生成重写Equals的方法
     *
     * @param entityList 属性列表
     * @param sb         实体类生成字符串
     * @param tableName  表名
     */
    void generateEquals(List<Entity> entityList, StringBuffer sb, String tableName) {
        sb.append("    @Override\r\n");
        sb.append("    public boolean equals(Object o) {\r\n");
        sb.append("        if (this == o) return true;\r\n");
        sb.append("        if (o == null || getClass() != o.getClass()) return false;\r\n");
        sb.append("        ").append(BeanUtils.captureName(BeanUtils.underline2Camel(tableName)))
                .append(" that = (").append(BeanUtils.captureName(BeanUtils.underline2Camel(tableName)))
                .append(") o;\r\n");
        entityList.forEach(entity -> {
            if (idValue.equals(entity.getEntityName()) && integerValue.equals(entity.getEntityProperty())) {
                sb.append("        return Objects.equals(id, that.id) &&\r\n");
            } else {
                sb.append("                Objects.equals(")
                        .append(entity.getEntityName()).append(", that.")
                        .append(entity.getEntityName()).append(") &&\r\n");
            }
        });
        sb.append("    }\r\n");
    }

    /**
     * 生成重写HashCode的方法
     *
     * @param entityList 属性列表
     * @param sb         实体类生成字符串
     */
    void generateHashCode(List<Entity> entityList, StringBuffer sb) {
        sb.append("    @Override\r\n");
        sb.append("    public int hashCode() {\r\n");
        sb.append("        return Objects.hash(");
        entityList.forEach(entity -> sb.append(entity.getEntityName()).append(", "));
        sb.deleteCharAt(sb.length() - 2);
        sb.append(");\r\n");
        sb.append("    }\r\n");
    }
}
