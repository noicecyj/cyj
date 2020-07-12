package com.example.cyjentitycreater.service;

import com.example.cyjentitycreater.entity.CreateVO;
import com.example.cyjentitycreater.entity.Entity;
import com.example.cyjentitycreater.entity.ResultVO;
import com.example.cyjentitycreater.utils.BeanUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

import static com.example.cyjentitycreater.utils.BeanUtils.entityName;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020/1/21 14:46
 */
@Component
public class PoServiceImpl implements BeanService {

    private final String idValue = "id";
    private final String integerValue = "Integer";
    private final String stringValue = "String";

    public ResultVO entityGenerate(CreateVO createVO) {
        StringBuffer sb = new StringBuffer();
        String yes = "Y";
        generateAnnotation(createVO, sb, yes);
        sb.append("public class ")
                .append(BeanUtils.captureName(BeanUtils.underline2Camel(createVO.getName())))
                .append(createVO.getType())
                .append(" implements Serializable {\r\n");
        sb.append("\r\n");
        sb.append("    static final String T_").append(createVO.getName().toUpperCase())
                .append(" = \"t_").append(createVO.getName()).append("\";\r\n");
        sb.append("\r\n");
        //生成属性注解
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
        return ResultVO.success(new String[]{entityData, entityName(createVO)});
    }

    @Override
    public void generateAnnotation(CreateVO createVO, StringBuffer sb, String yes) {
        LocalDate localDate = LocalDate.now();
        sb.append("package ").append("请填写包名").append(";\r\n");
        if (BeanUtils.ifDate(createVO.getEntityData())) {
            sb.append("import java.sql.Date;\r\n");
        }
        if (BeanUtils.ifTimestamp(createVO.getEntityData())) {
            sb.append("import java.sql.Timestamp;\r\n");
        }
        if (yes.equals(createVO.getLombok())) {
            sb.append("import lombok.Data;\r\n");
        }
        sb.append("import org.hibernate.annotations.GenericGenerator;\r\n");
        sb.append("import javax.persistence.*;\r\n");
        sb.append("import java.io.Serializable;\r\n");
        sb.append("\r\n");
        sb.append("/**\r\n");
        sb.append(" * @author 曹元杰\r\n");
        sb.append(" * @version 1.0\r\n");
        sb.append(" * @date ").append(localDate).append("\r\n");
        sb.append(" */\r\n");
        sb.append("@Entity\r\n");
        sb.append("@Table(name = ")
                .append(BeanUtils.captureName(BeanUtils.underline2Camel(createVO.getName()))).append(createVO.getType())
                .append(".T_").append(createVO.getName().toUpperCase()).append(")\r\n");
        if (yes.equals(createVO.getLombok())) {
            sb.append("@Data\r\n");
        }
        sb.append("@GenericGenerator(name = \"jpa-uuid\", strategy = \"uuid\")\r\n");
    }

    @Override
    public void generateProperty(CreateVO createVO, StringBuffer sb) {
        createVO.getEntityData().forEach(entity -> {
            if (entity.getId().equals(createVO.getPrimaryKey()) && entity.getEntityName().contains(idValue)) {
                sb.append("    @Id\r\n");
                sb.append("    @GeneratedValue(generator = \"jpa-uuid\")\r\n");
                sb.append("    @Column(name = \"").append(entity.getEntityName()).append("\",length = 32)\r\n");
            } else {
                sb.append("    @Column\r\n");
            }
            sb.append("    private ")
                    .append(entity.getEntityProperty()).append(" ")
                    .append(BeanUtils.underline2Camel(entity.getEntityName()))
                    .append(";\r\n");
            sb.append("\r\n");
        });
    }

    @Override
    public void generateConstructor(CreateVO createVO, StringBuffer sb) {
        //生成无参的构造方法
        sb.append("    public ")
                .append(BeanUtils.captureName(BeanUtils.underline2Camel(createVO.getName())))
                .append("() {\r\n")
                .append("    }\r\n")
                .append("\r\n");
        //生成有参的构造方法
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

    @Override
    public void generateGetterAndSetter(List<Entity> entityList, StringBuffer sb) {
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

    @Override
    public void generateToString(List<Entity> entityList, StringBuffer sb, String tableName) {
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

    @Override
    public void generateEquals(List<Entity> entityList, StringBuffer sb, String tableName) {
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

    @Override
    public void generateHashCode(List<Entity> entityList, StringBuffer sb) {
        sb.append("    @Override\r\n");
        sb.append("    public int hashCode() {\r\n");
        sb.append("        return Objects.hash(");
        entityList.forEach(entity -> sb.append(entity.getEntityName()).append(", "));
        sb.deleteCharAt(sb.length() - 2);
        sb.append(");\r\n");
        sb.append("    }\r\n");
    }
}
