package com.example.cyjentitycreater.serviceimpl;

import com.example.cyjentitycreater.entity.CreateVO;
import com.example.cyjentitycreater.entity.EntityPO;
import com.example.cyjentitycreater.utils.BeanUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static com.example.cyjentitycreater.utils.BeanUtils.*;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020/1/21 14:46
 */
@Component
public class PoServiceImpl extends BaseService {

    public String[] entityGenerate(CreateVO createVO, StringBuffer sb) {
        generatePackage1(createVO, sb);
        sb.append("import org.hibernate.annotations.GenericGenerator;\r\n");
        sb.append("\r\n");
        sb.append("import javax.persistence.*;\r\n");
        generatePackage2(createVO, sb);
        sb.append("@Entity\r\n");
        sb.append("@Table(name = ")
                .append(BeanUtils.captureName(BeanUtils.underline2Camel(createVO.getName())))
                .append(createVO.getType()).append(".T_").append(createVO.getName().toUpperCase()).append(")\r\n");
        sb.append("@Data\r\n");
        sb.append("@GenericGenerator(name = \"uuid2\", strategy = \"org.hibernate.id.UUIDGenerator\")\r\n");
        generateClass(createVO, sb);
        for (EntityPO entityPO:createVO.getPoList()){
            String idValue = "id";
            if (entityPO.getEntityName().equals(idValue)) {
                sb.append("    @Id\r\n");
                sb.append("    @GeneratedValue(generator = \"uuid2\")\r\n");
                sb.append("    @Column(name = \"").append(entityPO.getEntityName()).append("\", length = 36)\r\n");
            } else {
                sb.append("    @Column\r\n");
            }
            sb.append("    private ").append(entityPO.getEntityProperty()).append(" ")
                    .append(BeanUtils.underline2Camel(entityPO.getEntityName())).append(";\r\n");
            sb.append("\r\n");
        }
        sb.append("}");
        String entityData = sb.toString();
        return new String[]{entityData, entityName(createVO)};
    }

    public String[] daoGenerate(CreateVO createVO) {
        StringBuffer sb = new StringBuffer();
        String[] PathArr = createVO.getPath().split("java");
        //entity路径
        String poPath = PathArr[1].substring(1).replaceAll("\\\\", ".") + ".entity";
        //dao路径
        String poDaoPath = PathArr[1].substring(1).replaceAll("\\\\", ".") + ".dao";
        sb.append("package ").append(poDaoPath).append(";\r\n");
        String fileName = BeanUtils.captureName(BeanUtils.underline2Camel(createVO.getName()));
        sb.append("\r\n");
        sb.append("import ").append(poPath).append(".").append(fileName).append(createVO.getType()).append(";\r\n");
        sb.append("import org.springframework.data.jpa.repository.JpaRepository;\r\n");
        sb.append("\r\n");
        generateAuthor(sb);
        sb.append("public interface ").append(fileName).append("Dao extends JpaRepository<").append(fileName)
                .append(createVO.getType()).append(",String> {\r\n");
        sb.append("}\r\n");
        String daoData = sb.toString();
        return new String[]{daoData, entityDaoName(createVO)};
    }

    public String[] serviceGenerate(CreateVO createVO) {
        StringBuffer sb = new StringBuffer();
        String[] PathArr = createVO.getPath().split("java");
        //entity路径
        String poPath = PathArr[1].substring(1).replaceAll("\\\\", ".") + ".entity";
        //service路径
        String poServicePath = PathArr[1].substring(1).replaceAll("\\\\", ".") + ".service";
        sb.append("package ").append(poServicePath).append(";\r\n");
        String fileName = BeanUtils.captureName(BeanUtils.underline2Camel(createVO.getName()));
        sb.append("\r\n");
        sb.append("import ").append(poPath).append(".").append(fileName).append(createVO.getType()).append(";\r\n");
        sb.append("import org.springframework.data.domain.Page;");
        sb.append("\r\n");
        generateAuthor(sb);
        sb.append("public interface ").append(fileName).append("Service {\r\n");
        sb.append("\r\n");
        sb.append("    /**\r\n");
        sb.append("     * 添加实体\r\n");
        sb.append("     *\r\n");
        sb.append("     * @param po 实体\r\n");
        sb.append("     * @return 实体\r\n");
        sb.append("     */\r\n");
        sb.append("    ").append(fileName).append("PO addOne(").append(fileName).append("PO po);\r\n");
        sb.append("\r\n");
        sb.append("    /**\r\n");
        sb.append("     * 删除实体\r\n");
        sb.append("     *\r\n");
        sb.append("     * @param id 实体id\r\n");
        sb.append("     */\r\n");
        sb.append("    void deleteOne(String id);\r\n");
        sb.append("\r\n");
        sb.append("    /**\r\n");
        sb.append("     * 更新实体\r\n");
        sb.append("     *\r\n");
        sb.append("     * @param po 实体\r\n");
        sb.append("     * @return 实体\r\n");
        sb.append("     */\r\n");
        sb.append("    ").append(fileName).append("PO updateOne(").append(fileName).append("PO po);\r\n");
        sb.append("\r\n");
        sb.append("    /**\r\n");
        sb.append("     * 查找所有实体(分页排序)\r\n");
        sb.append("     *\r\n");
        sb.append("     * @param pageNumber 页码\r\n");
        sb.append("     * @param pageSize 条目\r\n");
        sb.append("     * @param sortCode 排序列\r\n");
        sb.append("     * @return 实体列表分页\r\n");
        sb.append("     */\r\n");
        sb.append("    Page<").append(fileName)
                .append("PO> findAll(Integer pageNumber, Integer pageSize, String sortCode);\r\n");
        sb.append("}\r\n");
        String daoData = sb.toString();
        return new String[]{daoData, entityServiceName(createVO)};
    }

    public String[] serviceImplGenerate(CreateVO createVO) {
        StringBuffer sb = new StringBuffer();
        String[] PathArr = createVO.getPath().split("java");
        //entity路径
        String poPath = PathArr[1].substring(1).replaceAll("\\\\", ".") + ".entity";
        //dao路径
        String poDaoPath = PathArr[1].substring(1).replaceAll("\\\\", ".") + ".dao";
        //service路径
        String poServicePath = PathArr[1].substring(1).replaceAll("\\\\", ".") + ".service";
        //serviceImpl路径
        String poServiceImplPath = PathArr[1].substring(1).replaceAll("\\\\", ".") + ".serviceimpl";
        sb.append("package ").append(poServiceImplPath).append(";\r\n");
        String fileName = BeanUtils.captureName(BeanUtils.underline2Camel(createVO.getName()));
        sb.append("\r\n");
        sb.append("import ").append(poPath).append(".").append(fileName).append(createVO.getType()).append(";\r\n");
        sb.append("import ").append(poDaoPath).append(".").append(fileName).append("Dao;\r\n");
        sb.append("import ").append(poServicePath).append(".").append(fileName).append("Service;\r\n");
        sb.append("import org.springframework.beans.factory.annotation.Autowired;\r\n");
        sb.append("import org.springframework.data.domain.Page;\r\n");
        sb.append("import org.springframework.data.domain.PageRequest;\r\n");
        sb.append("import org.springframework.data.domain.Pageable;\r\n");
        sb.append("import org.springframework.data.domain.Sort;\r\n");
        sb.append("import org.springframework.stereotype.Service;\r\n");
        sb.append("\r\n");
        generateAuthor(sb);
        sb.append("@Service\r\n");
        sb.append("public class ").append(fileName).append("ServiceImpl extends BaseService implements ")
                .append(fileName).append("Service {\r\n");
        sb.append("\r\n");
        sb.append("    private ").append(fileName).append("Dao ").append(BeanUtils.underline2Camel(createVO.getName()))
                .append("Dao;\r\n");
        sb.append("\r\n");
        sb.append("    @Autowired\r\n");
        sb.append("    public void set").append(fileName).append("Dao(").append(fileName).append("Dao ")
                .append(BeanUtils.underline2Camel(createVO.getName())).append("Dao) {\r\n");
        sb.append("        this.").append(BeanUtils.underline2Camel(createVO.getName())).append("Dao = ")
                .append(BeanUtils.underline2Camel(createVO.getName())).append("Dao;\r\n");
        sb.append("    }\r\n");
        sb.append("\r\n");
        sb.append("    @Override\r\n");
        sb.append("    public ").append(fileName).append("PO addOne(").append(fileName).append("PO po) {\r\n");
        sb.append("        return ").append(BeanUtils.underline2Camel(createVO.getName())).append("Dao.save(po);\r\n");
        sb.append("    }\r\n");
        sb.append("\r\n");
        sb.append("    @Override\r\n");
        sb.append("    public void deleteOne(String id) {\r\n");
        sb.append("        ").append(BeanUtils.underline2Camel(createVO.getName())).append("Dao.deleteById(id);\r\n");
        sb.append("    }\r\n");
        sb.append("\r\n");
        sb.append("    @Override\r\n");
        sb.append("    public ").append(fileName).append("PO updateOne(").append(fileName).append("PO po) {\r\n");
        sb.append("        return ").append(BeanUtils.underline2Camel(createVO.getName()))
                .append("Dao.saveAndFlush(po);\r\n");
        sb.append("    }\r\n");
        sb.append("\r\n");
        sb.append("    @Override\r\n");
        sb.append("    public Page<").append(fileName)
                .append("PO> findAll(Integer pageNumber, Integer pageSize, String sortCode) {\r\n");
        sb.append("        Sort sort = Sort.by(sortCode);\r\n");
        sb.append("        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);\r\n");
        sb.append("        return ").append(BeanUtils.underline2Camel(createVO.getName()))
                .append("Dao.findAll(pageable);\r\n");
        sb.append("    }\r\n");
        sb.append("\r\n");
        sb.append("}\r\n");
        String daoData = sb.toString();
        return new String[]{daoData, entityServiceImplName(createVO)};
    }

    public String[] controllerGenerate(CreateVO createVO) {
        StringBuffer sb = new StringBuffer();
        String[] PathArr = createVO.getPath().split("java");
        List<File> files = getFiles(createVO.getPath() + "\\service");
        //entity路径
        String poPath = PathArr[1].substring(1).replaceAll("\\\\", ".") + ".entity.*";
        //serviceImpl路径
        String poServiceImplPath = PathArr[1].substring(1).replaceAll("\\\\", ".") + ".serviceimpl.*";
        //controller路径
        String poControllerPath = PathArr[1].substring(1).replaceAll("\\\\", ".") + ".controller";
        sb.append("package ").append(poControllerPath).append(";\r\n");
        sb.append("import ").append(poPath).append(";\r\n");
        sb.append("import ").append(poServiceImplPath).append(";\r\n");
        sb.append("import org.springframework.web.bind.annotation.*;\r\n");
        sb.append("import org.springframework.beans.factory.annotation.Autowired;\r\n");
        generateAuthor(sb);
        sb.append("@RestController\r\n");
        sb.append("@RequestMapping(value = \"").append(createVO.getName()).append("Api\")\r\n");
        sb.append("public class IndexController {\r\n");
        sb.append("\r\n");
        for (File file : files) {
            String entityImpl = file.getName().split("\\.")[0];
            sb.append("    private ").append(entityImpl).append("Impl ")
                    .append(BeanUtils.toLowerCaseFirstOne(entityImpl)).append(";\r\n");
        }
        sb.append("\r\n");
        for (File file : files) {
            String entityImpl = file.getName().split("\\.")[0];
            sb.append("    @Autowired\r\n");
            sb.append("    public void set").append(entityImpl).append("(").append(entityImpl)
                    .append("Impl ").append(BeanUtils.toLowerCaseFirstOne(entityImpl)).append(") {\r\n");
            sb.append("        this.").append(BeanUtils.toLowerCaseFirstOne(entityImpl)).append(" = ")
                    .append(BeanUtils.toLowerCaseFirstOne(entityImpl)).append(";\r\n");
            sb.append("    }\r\n");
            sb.append("\r\n");
        }
        for (File file : files) {
            String entity = file.getName().split("Service")[0];
            String entityImpl = file.getName().split("\\.")[0];
            sb.append("    @PostMapping(value = \"").append(BeanUtils.toLowerCaseFirstOne(entity)).append("Page\")\r\n");
            sb.append("    public ResultVO ").append(BeanUtils.toLowerCaseFirstOne(entity))
                    .append("FindAll(@RequestParam(\"pageNumber\") Integer pageNumber,\r\n");
            sb.append("              @RequestParam(\"pageSize\") Integer pageSize,\r\n");
            sb.append("              @RequestParam(\"sortCode\") String sortCode) {\r\n");
            sb.append("        return ResultVO.success(").append(BeanUtils.toLowerCaseFirstOne(entityImpl))
                    .append(".findAll(pageNumber - 1, pageSize, sortCode));\r\n");
            sb.append("    }\r\n");
            sb.append("\r\n");
            sb.append("    @PostMapping(value = \"").append(BeanUtils.toLowerCaseFirstOne(entity))
                    .append("Save\")\r\n");
            sb.append("    public ResultVO save").append(entity).append("(@RequestBody ")
                    .append(entity).append("PO po) {\r\n");
            sb.append("        if (po.getId() == null) {\r\n");
            sb.append("            return ResultVO.success(").append(BeanUtils.toLowerCaseFirstOne(entityImpl))
                    .append(".addOne(po));\r\n");
            sb.append("        }\r\n");
            sb.append("        return ResultVO.success(").append(BeanUtils.toLowerCaseFirstOne(entityImpl))
                    .append(".updateOne(po));\r\n");
            sb.append("    }\r\n");
            sb.append("\r\n");
            sb.append("    @PostMapping(value = \"").append(BeanUtils.toLowerCaseFirstOne(entity))
                    .append("Delete\")\r\n");
            sb.append("    public void ").append(BeanUtils.toLowerCaseFirstOne(entity))
                    .append("DeleteOne(@RequestParam(\"id\") String id) {\r\n");
            sb.append("        ").append(BeanUtils.toLowerCaseFirstOne(entityImpl)).append(".deleteOne(id);\r\n");
            sb.append("    }\r\n");
        }
        sb.append("\r\n");
        sb.append("}\r\n");
        String daoData = sb.toString();
        return new String[]{daoData, controllerName()};
    }

    public void createJavaFile(CreateVO createVO) throws IOException {
        String[] result;
        StringBuffer sb = new StringBuffer();
        if (!createVO.getPoList().isEmpty()) {
            result = entityGenerate(createVO,sb);
            createJavaFile(createVO.getPath() + "\\entity", result);
        }
        String[] daoResult = daoGenerate(createVO);
        createJavaFile(createVO.getPath() + "\\dao", daoResult);
        String[] serviceResult = serviceGenerate(createVO);
        createJavaFile(createVO.getPath() + "\\service", serviceResult);
        String[] serviceImplResult = serviceImplGenerate(createVO);
        createJavaFile(createVO.getPath() + "\\serviceimpl", serviceImplResult);
        String[] controllerResult = controllerGenerate(createVO);
        createJavaFile(createVO.getPath() + "\\controller", controllerResult);
    }

}