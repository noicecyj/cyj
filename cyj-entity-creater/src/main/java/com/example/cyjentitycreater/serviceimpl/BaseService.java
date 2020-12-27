package com.example.cyjentitycreater.serviceimpl;

import com.example.cyjentitycreater.entity.EntityNamePO;
import com.example.cyjentitycreater.entity.EntityPO;
import com.example.cyjentitycreater.utils.BeanUtils;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

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

    private final Logger logger = LoggerFactory.getLogger(BaseService.class);

    @PostConstruct
    public void init() {
        queryFactory = new JPAQueryFactory(entityManager);
    }

    /**
     * 生成作者
     *
     * @param sb 实体类生成字符串
     */
    public void generateAuthor(StringBuilder sb) {
        LocalDate localDate = LocalDate.now();
        sb.append("/**\r\n");
        sb.append(" * @author 曹元杰\r\n");
        sb.append(" * @version 1.0\r\n");
        sb.append(" * @date ").append(localDate).append("\r\n");
        sb.append(" */\r\n");
    }

    public void generateClass(EntityNamePO po, StringBuilder sb) {
        sb.append("public class ")
                .append(BeanUtils.captureName(BeanUtils.underline2Camel(po.getName())))
                .append(po.getType()).append(" implements Serializable {\r\n");
        sb.append("\r\n");
        sb.append("    static final String T_").append(po.getName().toUpperCase())
                .append(" = \"t_").append(po.getName()).append("\";\r\n");
        sb.append("\r\n");
    }

    public void generatePackage1(EntityNamePO po, StringBuilder sb) {
        //pojo路径
        String[] poPathArr = po.getPath().split("java");
        String poPath = poPathArr[1].substring(1).replaceAll("\\\\", ".") + ".entity";
        sb.append("package ").append(poPath).append(";\r\n");
        sb.append("\r\n");
        sb.append("import lombok.Data;\r\n");
    }

    public void generatePackage2(List<EntityPO> poList, StringBuilder sb) {
        sb.append("import java.io.Serializable;\r\n");
        if (BeanUtils.ifDate(poList)) {
            sb.append("import java.sql.Date;\r\n");
        }
        if (BeanUtils.ifTimestamp(poList)) {
            sb.append("import java.sql.Timestamp;\r\n");
        }
        sb.append("\r\n");
        generateAuthor(sb);
    }

    /**
     * 生成文件
     *
     * @param path   路径
     * @param result 内容
     */
    public void createJavaFile(String path, String[] result) throws IOException {
        File file = new File(path);
        File file2 = new File(path + "/" + result[1]);
        //如果文件不存在，创建一个文件
        if (file.mkdirs()) {
            logger.info("生成路径文件夹");
        }
        if (file2.createNewFile()) {
            logger.info("生成文件,路径为：{}", path + "/" + result[1]);
        }
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            fw = new FileWriter(file2);
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

    /**
     * 生成文件夹
     *
     * @param path 路径
     */
    public void createJavaFile(String path) {
        File file = new File(path);
        if (!file.exists()) {
            if (file.mkdirs()) {
                logger.info("生成文件夹,路径为：{}", path);
            }
        }
    }
}
