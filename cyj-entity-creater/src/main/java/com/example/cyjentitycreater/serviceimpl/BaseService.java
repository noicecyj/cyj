package com.example.cyjentitycreater.serviceimpl;

import com.example.cyjentitycreater.entity.CreateVO;
import com.example.cyjentitycreater.utils.BeanUtils;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020/1/21 14:46
 */
public class BaseService {

    @Autowired
    @PersistenceContext
    protected EntityManager entityManager;

    protected JPAQueryFactory queryFactory;

    @PostConstruct
    public void init() {
        queryFactory = new JPAQueryFactory(entityManager);
    }

    /**
     * 生成作者
     *
     * @param sb 实体类生成字符串
     */
    public void generateAuthor(StringBuffer sb) {
        LocalDate localDate = LocalDate.now();
        sb.append("/**\r\n");
        sb.append(" * @author 曹元杰\r\n");
        sb.append(" * @version 1.0\r\n");
        sb.append(" * @date ").append(localDate).append("\r\n");
        sb.append(" */\r\n");
    }

    public void generateClass(CreateVO createVO, StringBuffer sb) {
        sb.append("public class ")
                .append(BeanUtils.captureName(BeanUtils.underline2Camel(createVO.getName())))
                .append(createVO.getType()).append(" implements Serializable {\r\n");
        sb.append("\r\n");
        sb.append("    static final String T_").append(createVO.getName().toUpperCase())
                .append(" = \"t_").append(createVO.getName()).append("\";\r\n");
        sb.append("\r\n");
    }

    public void generatePackage2(CreateVO createVO, StringBuffer sb) {
        sb.append("import java.io.Serializable;\r\n");
        if (BeanUtils.ifDate(createVO.getPoList())) {
            sb.append("import java.sql.Date;\r\n");
        }
        if (BeanUtils.ifTimestamp(createVO.getPoList())) {
            sb.append("import java.sql.Timestamp;\r\n");
        }
        sb.append("\r\n");
        generateAuthor(sb);
    }

    public void generatePackage1(CreateVO createVO, StringBuffer sb) {
        //pojo路径
        String[] poPathArr = createVO.getPath().split("java");
        String poPath = poPathArr[1].substring(1).replaceAll("\\\\", ".") + ".entity";
        sb.append("package ").append(poPath).append(";\r\n");
        sb.append("\r\n");
        sb.append("import lombok.Data;\r\n");
    }

    /**
     * 生成文件
     *
     * @param path 路径
     */
    public void createJavaFile(String path, String[] result) throws IOException {
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
    public List<File> getFiles(String path) {
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
