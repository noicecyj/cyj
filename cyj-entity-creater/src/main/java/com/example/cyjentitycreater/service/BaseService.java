package com.example.cyjentitycreater.service;

import com.example.cyjentitycreater.entity.CreateVO;
import com.example.cyjentitycreater.entity.Entity;
import com.example.cyjentitycreater.utils.BeanUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.example.cyjentitycreater.utils.BeanUtils.entityName;

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

    /**
     * 生成需要重写的方法
     *
     * @param createVO 表单
     * @param sb       实体类生成字符串
     */
    String[] generateMethod(CreateVO createVO, StringBuffer sb) {
        String yes = "Y";
        generateProperty(createVO, sb);
        if (createVO.getMethod() != null && !yes.equals(createVO.getLombok())) {
            for (String method : createVO.getMethod()) {
                if ("Constructor".equals(method)) {
                    generateConstructor(createVO, sb);
                }
                if ("Getter and Setter".equals(method)) {
                    generateGetterAndSetter(createVO.getEntityData(), sb);
                    sb.append("\r\n");
                }
                if ("toString".equals(method)) {
                    generateToString(createVO.getEntityData(), sb, createVO.getName());
                    sb.append("\r\n");
                }
                if ("equals and hashCode".equals(method)) {
                    generateEquals(createVO.getEntityData(), sb, createVO.getName());
                    sb.append("\r\n");
                    generateHashCode(createVO.getEntityData(), sb);
                }
            }
        }
        sb.append("}");
        String entityData = sb.toString();
        return new String[]{entityData, entityName(createVO)};
    }

    /**
     * 生成需要的包
     *
     * @param createVO 表单
     * @param yes      是
     * @param sb       实体类生成字符串
     */
    void generatePackage(CreateVO createVO, StringBuffer sb, String yes) {
        //pojo路径
        String[] poPathArr = createVO.getPath().split("java");
        String poPath = poPathArr[1].substring(1).replaceAll("\\\\", ".") + ".entity";
        sb.append("package ").append(poPath).append(";\r\n");
        if (BeanUtils.ifDate(createVO.getEntityData())) {
            sb.append("import java.sql.Date;\r\n");
        }
        if (BeanUtils.ifTimestamp(createVO.getEntityData())) {
            sb.append("import java.sql.Timestamp;\r\n");
        }
        if (yes.equals(createVO.getLombok())) {
            sb.append("import lombok.Data;\r\n");
        }
    }

    /**
     * 生成作者
     *
     * @param sb 实体类生成字符串
     */
    void generateAuthor(StringBuffer sb) {
        LocalDate localDate = LocalDate.now();
        sb.append("/**\r\n");
        sb.append(" * @author 曹元杰\r\n");
        sb.append(" * @version 1.0\r\n");
        sb.append(" * @date ").append(localDate).append("\r\n");
        sb.append(" */\r\n");
    }

    /**
     * 生成文件
     *
     * @param path 路径
     */
    void createJavaFile(String path, String[] result) throws IOException {
        //文件放在src/main/java/ 目录下 命名为aaa.java
        File file = new File(path + "/" + result[1]);
        //如果文件不存在，创建一个文件
        if (file.createNewFile()) {
            FileWriter fw = null;
            BufferedWriter bw = null;
            try {
                fw = new FileWriter(file);
                bw = new BufferedWriter(fw);
                bw.write(result[0]);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                assert bw != null;
                bw.close();
                fw.close();
            }
        }
    }

    /**
     * 获取文件
     *
     * @param path 路径
     */
    List<File> getFiles(String path) {
        List<File> files = new ArrayList<>();
        File file = new File(path);
        File[] tempList = file.listFiles();
        for (int i = 0; i < Objects.requireNonNull(tempList).length; i++) {
            if (tempList[i].isFile()) {
                files.add(tempList[i]);
            }
        }
        return files;
    }
}
