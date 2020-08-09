package com.example.cyjentitycreater.service;

import com.example.cyjentitycreater.entity.CreateVO;
import com.example.cyjentitycreater.utils.BeanUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static com.example.cyjentitycreater.utils.BeanUtils.*;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020/1/21 14:46
 */
@Component
public class PoServiceImpl extends BaseService {

    private final String idValue = "id";

    public String[] entityGenerate(CreateVO createVO) {
        StringBuffer sb = new StringBuffer();
        String yes = "Y";
        generateAnnotation(createVO, sb, yes);
        sb.append("public class ")
                .append(BeanUtils.captureName(BeanUtils.underline2Camel(createVO.getName())))
                .append(createVO.getType())
                .append(" implements Serializable {\r\n");
        sb.append("    static final String T_").append(createVO.getName().toUpperCase())
                .append(" = \"t_").append(createVO.getName()).append("\";\r\n");
        return generateMethod(createVO, sb);
    }

    public String[] daoGenerate(CreateVO createVO) {
        StringBuffer sb = new StringBuffer();
        //pojo路径
        String[] poPathArr = createVO.getPath().split("java");
        String poPath = poPathArr[1].substring(1).replaceAll("\\\\", ".");
        //dao路径
        String[] poDaoPathArr = createVO.getDaoPath().split("java");
        String poDaoPath = poDaoPathArr[1].substring(1).replaceAll("\\\\", ".");
        sb.append("package ").append(poDaoPath).append(";\r\n");
        String fileName = BeanUtils.captureName(BeanUtils.underline2Camel(createVO.getName()));
        sb.append("import ").append(poPath).append(".").append(fileName).append(createVO.getType()).append(";\r\n");
        sb.append("import org.springframework.data.jpa.repository.JpaRepository;\r\n");
        generateAuthor(sb);
        sb.append("public interface ").append(fileName).append("Dao extends JpaRepository<").append(fileName)
                .append(createVO.getType()).append(",String> {\r\n");
        sb.append("}\r\n");
        String daoData = sb.toString();
        return new String[]{daoData, entityDaoName(createVO)};
    }

    public String[] serviceGenerate(CreateVO createVO) {
        StringBuffer sb = new StringBuffer();
        //service路径
        String[] poServicePathArr = createVO.getServicePath().split("java");
        String poServicePath = poServicePathArr[1].substring(1).replaceAll("\\\\", ".");
        sb.append("package ").append(poServicePath).append(";\r\n");
        String fileName = BeanUtils.captureName(BeanUtils.underline2Camel(createVO.getName()));
        generateAuthor(sb);
        sb.append("public interface ").append(fileName).append("Service {\r\n");
        sb.append("}\r\n");
        String daoData = sb.toString();
        return new String[]{daoData, entityServiceName(createVO)};
    }

    public String[] serviceImplGenerate(CreateVO createVO) {
        StringBuffer sb = new StringBuffer();
        //service路径
        String[] poServicePathArr = createVO.getServicePath().split("java");
        String poServicePath = poServicePathArr[1].substring(1).replaceAll("\\\\", ".");
        //serviceImpl路径
        String[] poServiceImplPathArr = createVO.getServiceImplPath().split("java");
        String poServiceImplPath = poServiceImplPathArr[1].substring(1).replaceAll("\\\\", ".");
        sb.append("package ").append(poServiceImplPath).append(";\r\n");
        String fileName = BeanUtils.captureName(BeanUtils.underline2Camel(createVO.getName()));
        sb.append("import ").append(poServicePath).append(".").append(fileName).append("Service").append(";\r\n");
        sb.append("import org.springframework.stereotype.Service").append(";\r\n");
        generateAuthor(sb);
        sb.append("@Service\r\n");
        sb.append("public class ").append(fileName).append("ServiceImpl extends BaseService implements ")
                .append(fileName).append("Service {\r\n");
        sb.append("}\r\n");
        String daoData = sb.toString();
        return new String[]{daoData, entityServiceImplName(createVO)};
    }

    public String[] controllerGenerate(CreateVO createVO) {
        StringBuffer sb = new StringBuffer();
        //controller路径
        String[] poControllerPathArr = createVO.getControllerPath().split("java");
        String poControllerPath = poControllerPathArr[1].substring(1).replaceAll("\\\\", ".");
        sb.append("package ").append(poControllerPath).append(";\r\n");
        sb.append("import org.springframework.web.bind.annotation.*;\r\n");
        generateAuthor(sb);
        sb.append("@RestController\r\n");
        sb.append("@RequestMapping(value = \"Api\")\r\n");
        sb.append("public class IndexController {\r\n");
        sb.append("}\r\n");
        String daoData = sb.toString();
        return new String[]{daoData, controllerName()};
    }

    public String[] createJavaFile(CreateVO createVO) throws IOException {
        String[] result = null;
        if (!createVO.getEntityData().isEmpty()) {
            result = entityGenerate(createVO);
            createJavaFile(createVO.getPath(), result);
        }
        if (!createVO.getDaoPath().isEmpty()) {
            String[] daoResult = daoGenerate(createVO);
            createJavaFile(createVO.getDaoPath(), daoResult);
        }
        if (!createVO.getServicePath().isEmpty()){
            String[] serviceResult = serviceGenerate(createVO);
            createJavaFile(createVO.getServicePath(), serviceResult);
        }
        if (!createVO.getServiceImplPath().isEmpty()){
            String[] serviceImplResult = serviceImplGenerate(createVO);
            createJavaFile(createVO.getServiceImplPath(), serviceImplResult);
        }
        if (!createVO.getControllerPath().isEmpty()){
            String[] controllerResult = controllerGenerate(createVO);
            createJavaFile(createVO.getControllerPath(), controllerResult);
        }
        return result;
    }

    @Override
    public void generateAnnotation(CreateVO createVO, StringBuffer sb, String yes) {
        generatePackage(createVO, sb, yes);
        sb.append("import org.hibernate.annotations.GenericGenerator;\r\n");
        sb.append("import javax.persistence.*;\r\n");
        sb.append("import java.io.Serializable;\r\n");
        sb.append("\r\n");
        generateAuthor(sb);
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
                sb.append("    @Column(name = \"").append(entity.getEntityName()).append("\" ,length = 32)\r\n");
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
}
