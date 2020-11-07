package com.example.cyjentitycreater.serviceimpl;

import com.example.cyjentitycreater.entity.CreateVO;
import com.example.cyjentitycreater.entity.EntityNamePO;
import com.example.cyjentitycreater.entity.EntityPO;
import com.example.cyjentitycreater.utils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

    private EntityNameServiceImpl entityNameService;

    @Autowired
    public void setEntityNameService(EntityNameServiceImpl entityNameService) {
        this.entityNameService = entityNameService;
    }

    public void createJavaFile(CreateVO createVO) throws IOException {
        EntityNamePO po = entityNameService.findOneById(createVO.getId());
        String[] result;
        if (!createVO.getPoList().isEmpty()) {
            result = entityGenerate(createVO);
            createJavaFile(createVO.getPath() + "\\entity", result);
        }

        String[] daoResult = daoGenerate(po);
        createJavaFile(po.getPath() + "\\dao", daoResult);
        String[] serviceResult = serviceGenerate(po);
        createJavaFile(po.getPath() + "\\service", serviceResult);
        String[] serviceImplResult = serviceImplGenerate(po);
        createJavaFile(po.getPath() + "\\serviceimpl", serviceImplResult);
        String[] controllerInteResult = controllerInteGenerate(po);
        createJavaFile(po.getPath() + "\\controller", controllerInteResult);
        String[] controllerResult = controllerGenerate(po);
        createJavaFile(po.getPath() + "\\controller", controllerResult);
        if (po.getRelEntity() != null && !"".equals(po.getRelEntity())) {
            String[] relEntities = po.getRelEntity().split(",");
            for (String relEntity : relEntities) {
                String underRelEntity = BeanUtils.underline2Camel(relEntity);

            }
        }
    }

    public String[] entityGenerate(CreateVO createVO) {
        StringBuffer sb = new StringBuffer();
        generatePackage1(createVO, sb);
        sb.append("import org.hibernate.annotations.GenericGenerator;\r\n");
        sb.append("\r\n");
        sb.append("import javax.persistence.*;\r\n");
        generatePackage2(createVO, sb);
        sb.append("@Entity\r\n");
        sb.append("@Table(name = ").append(BeanUtils.captureName(BeanUtils.underline2Camel(createVO.getName()))).append(createVO.getType()).append(".T_").append(createVO.getName().toUpperCase()).append(")\r\n");
        sb.append("@Data\r\n");
        sb.append("@GenericGenerator(name = \"uuid2\", strategy = \"org.hibernate.id.UUIDGenerator\")\r\n");
        generateClass(createVO, sb);
        for (EntityPO entityPO : createVO.getPoList()) {
            String idValue = "id";
            if (entityPO.getEntityName().equals(idValue)) {
                sb.append("    @Id\r\n");
                sb.append("    @GeneratedValue(generator = \"uuid2\")\r\n");
                sb.append("    @Column(name = \"").append(entityPO.getEntityName()).append("\", length = 36)\r\n");
            } else {
                sb.append("    @Column(name = \"").append(entityPO.getEntityName()).append("\")\r\n");
            }
            sb.append("    private ").append(entityPO.getEntityProperty()).append(" ").append(BeanUtils.underline2Camel(entityPO.getEntityName())).append(";\r\n");
            sb.append("\r\n");
        }
        sb.append("}");
        String entityData = sb.toString();
        return new String[]{entityData, entityName(createVO)};
    }

    public String[] daoGenerate(EntityNamePO po) {
        StringBuffer sb = new StringBuffer();
        String[] PathArr = po.getPath().split("java");
        //entity路径
        String poPath = PathArr[1].substring(1).replaceAll("\\\\", ".") + ".entity";
        //dao路径
        String poDaoPath = PathArr[1].substring(1).replaceAll("\\\\", ".") + ".dao";
        sb.append("package ").append(poDaoPath).append(";\r\n");
        String fileName = BeanUtils.captureName(BeanUtils.underline2Camel(po.getName()));
        sb.append("\r\n");
        sb.append("import ").append(poPath).append(".").append(fileName).append(po.getType()).append(";\r\n");
        sb.append("import org.springframework.data.jpa.repository.JpaRepository;\r\n");
        sb.append("\r\n");
        generateAuthor(sb);
        sb.append("public interface ").append(fileName).append("Dao extends JpaRepository<").append(fileName).append(po.getType()).append(", String> {\r\n");
        sb.append("}\r\n");
        String entityDaoData = sb.toString();
        return new String[]{entityDaoData, entityDaoName(po)};
    }

    public String[] serviceGenerate(EntityNamePO po) {
        StringBuffer sb = new StringBuffer();
        String[] PathArr = po.getPath().split("java");
        //entity路径
        String poPath = PathArr[1].substring(1).replaceAll("\\\\", ".") + ".entity";
        //service路径
        String poServicePath = PathArr[1].substring(1).replaceAll("\\\\", ".") + ".service";
        sb.append("package ").append(poServicePath).append(";\r\n");
        String fileName = BeanUtils.captureName(BeanUtils.underline2Camel(po.getName()));
        sb.append("\r\n");
        sb.append("import ").append(poPath).append(".").append(fileName).append(po.getType()).append(";\r\n");
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
        sb.append("    Page<").append(fileName).append("PO> findAll(Integer pageNumber, Integer pageSize, String sortCode);\r\n");
        sb.append("}\r\n");
        String entityServiceData = sb.toString();
        return new String[]{entityServiceData, entityServiceName(po)};
    }

    public String[] serviceImplGenerate(EntityNamePO po) {
        StringBuffer sb = new StringBuffer();
        String[] PathArr = po.getPath().split("java");
        //entity路径
        String poPath = PathArr[1].substring(1).replaceAll("\\\\", ".") + ".entity";
        //dao路径
        String poDaoPath = PathArr[1].substring(1).replaceAll("\\\\", ".") + ".dao";
        //service路径
        String poServicePath = PathArr[1].substring(1).replaceAll("\\\\", ".") + ".service";
        //serviceImpl路径
        String poServiceImplPath = PathArr[1].substring(1).replaceAll("\\\\", ".") + ".serviceimpl";
        sb.append("package ").append(poServiceImplPath).append(";\r\n");
        String fileName = BeanUtils.captureName(BeanUtils.underline2Camel(po.getName()));
        sb.append("\r\n");
        sb.append("import ").append(poPath).append(".").append(fileName).append(po.getType()).append(";\r\n");
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
        sb.append("public class ").append(fileName).append("ServiceImpl extends BaseService implements ").append(fileName).append("Service {\r\n");
        sb.append("\r\n");
        sb.append("    private ").append(fileName).append("Dao ").append(BeanUtils.underline2Camel(po.getName())).append("Dao;\r\n");
        sb.append("\r\n");
        sb.append("    @Autowired\r\n");
        sb.append("    public void set").append(fileName).append("Dao(").append(fileName).append("Dao ").append(BeanUtils.underline2Camel(po.getName())).append("Dao) {\r\n");
        sb.append("        this.").append(BeanUtils.underline2Camel(po.getName())).append("Dao = ").append(BeanUtils.underline2Camel(po.getName())).append("Dao;\r\n");
        sb.append("    }\r\n");
        sb.append("\r\n");
        sb.append("    @Override\r\n");
        sb.append("    public ").append(fileName).append("PO addOne(").append(fileName).append("PO po) {\r\n");
        sb.append("        return ").append(BeanUtils.underline2Camel(po.getName())).append("Dao.save(po);\r\n");
        sb.append("    }\r\n");
        sb.append("\r\n");
        sb.append("    @Override\r\n");
        sb.append("    public void deleteOne(String id) {\r\n");
        sb.append("        ").append(BeanUtils.underline2Camel(po.getName())).append("Dao.deleteById(id);\r\n");
        sb.append("    }\r\n");
        sb.append("\r\n");
        sb.append("    @Override\r\n");
        sb.append("    public ").append(fileName).append("PO updateOne(").append(fileName).append("PO po) {\r\n");
        sb.append("        return ").append(BeanUtils.underline2Camel(po.getName())).append("Dao.saveAndFlush(po);\r\n");
        sb.append("    }\r\n");
        sb.append("\r\n");
        sb.append("    @Override\r\n");
        sb.append("    public Page<").append(fileName).append("PO> findAll(Integer pageNumber, Integer pageSize, String sortCode) {\r\n");
        sb.append("        Sort sort = Sort.by(sortCode);\r\n");
        sb.append("        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);\r\n");
        sb.append("        return ").append(BeanUtils.underline2Camel(po.getName())).append("Dao.findAll(pageable);\r\n");
        sb.append("    }\r\n");
        sb.append("\r\n");
        sb.append("}\r\n");
        String entityServiceImplData = sb.toString();
        return new String[]{entityServiceImplData, entityServiceImplName(po)};
    }

    public String[] controllerInteGenerate(EntityNamePO po) {
        StringBuffer sb = new StringBuffer();
        String[] PathArr = po.getPath().split("java");
        String fileName = BeanUtils.captureName(BeanUtils.underline2Camel(po.getName()));
        //entity路径
        String poPath = PathArr[1].substring(1).replaceAll("\\\\", ".") + ".entity.";
        //controller路径
        String poControllerPath = PathArr[1].substring(1).replaceAll("\\\\", ".") + ".controller";
        String entityPath = poPath + fileName + "PO";
        String resultPath = poPath + "ResultVO";
        sb.append("package ").append(poControllerPath).append(";\r\n");
        sb.append("\r\n");
        sb.append("import ").append(entityPath).append(";\r\n");
        sb.append("import ").append(resultPath).append(";\r\n");
        sb.append("import org.springframework.web.bind.annotation.PostMapping;\r\n");
        sb.append("import org.springframework.web.bind.annotation.RequestBody;\r\n");
        sb.append("import org.springframework.web.bind.annotation.RequestParam;\r\n");
        sb.append("\r\n");
        generateAuthor(sb);
        sb.append("public interface ").append(fileName).append("Controller {\r\n");
        sb.append("\r\n");
        sb.append("    /**\r\n");
        sb.append("     * 查询所有对象\r\n");
        sb.append("     *\r\n");
        sb.append("     * @param pageNumber 页码\r\n");
        sb.append("     * @param pageSize   条目\r\n");
        sb.append("     * @param sortCode   排序列\r\n");
        sb.append("     * @return 返回结果\r\n");
        sb.append("     */\r\n");
        sb.append("    @PostMapping(value = \"").append(BeanUtils.underline2Camel(po.getName())).append("Page\")\r\n");
        sb.append("    ResultVO ").append(BeanUtils.underline2Camel(po.getName())).append("FindAll(@RequestParam(\"pageNumber\") Integer pageNumber,\r\n");
        sb.append("                         @RequestParam(\"pageSize\") Integer pageSize,\r\n");
        sb.append("                         @RequestParam(\"sortCode\") String sortCode);\r\n");
        sb.append("\r\n");
        sb.append("    /**\r\n");
        sb.append("     * 保存对象\r\n");
        sb.append("     *\r\n");
        sb.append("     * @param po 对象\r\n");
        sb.append("     * @return 返回结果\r\n");
        sb.append("     */\r\n");
        sb.append("    @PostMapping(value = \"").append(BeanUtils.underline2Camel(po.getName())).append("Save\")\r\n");
        sb.append("    ResultVO ").append(BeanUtils.underline2Camel(po.getName())).append("Save(@RequestBody ").append(fileName).append("PO po);\r\n");
        sb.append("\r\n");
        sb.append("    /**\r\n");
        sb.append("     * 删除对象\r\n");
        sb.append("     *\r\n");
        sb.append("     * @param id 对象ID\r\n");
        sb.append("     */\r\n");
        sb.append("    @PostMapping(value = \"").append(BeanUtils.underline2Camel(po.getName())).append("Delete\")\r\n");
        sb.append("    void ").append(BeanUtils.underline2Camel(po.getName())).append("Delete(@RequestParam(\"id\") String id);\r\n");
        sb.append("\r\n");
        sb.append("}\r\n");
        String entityControllerData = sb.toString();
        return new String[]{entityControllerData, entityControllerName(po)};
    }

    public String[] controllerGenerate(EntityNamePO po) {
        StringBuffer sb = new StringBuffer();
        String[] PathArr = po.getPath().split("java");
        String fileName = BeanUtils.captureName(BeanUtils.underline2Camel(po.getName()));
        //entity路径
        String poPath = PathArr[1].substring(1).replaceAll("\\\\", ".") + ".entity.*";
        //serviceImpl路径
        String poServiceImplPath = PathArr[1].substring(1).replaceAll("\\\\", ".") + ".serviceimpl.*";
        //controller路径
        String poControllerPath = PathArr[1].substring(1).replaceAll("\\\\", ".") + ".controller";
        sb.append("package ").append(poControllerPath).append(";\r\n");
        sb.append("\r\n");
        sb.append("import ").append(poPath).append(";\r\n");
        sb.append("import ").append(poServiceImplPath).append(";\r\n");
        sb.append("import org.springframework.web.bind.annotation.*;\r\n");
        sb.append("import org.springframework.beans.factory.annotation.Autowired;\r\n");
        sb.append("\r\n");
        generateAuthor(sb);
        sb.append("@RestController\r\n");
        sb.append("@RequestMapping(value = \"").append(po.getApi()).append("\")\r\n");
        sb.append("public class ").append(fileName).append("ControllerImpl implements ").append(fileName).append("Controller {\r\n");
        sb.append("\r\n");
        sb.append("    private ").append(fileName).append("ServiceImpl ").append(BeanUtils.toLowerCaseFirstOne(fileName)).append("Service;\r\n");
        sb.append("\r\n");
        sb.append("    @Autowired\r\n");
        sb.append("    public void set").append(fileName).append("Service(").append(fileName).append("ServiceImpl ").append(BeanUtils.toLowerCaseFirstOne(fileName)).append("Service) {\r\n");
        sb.append("        this.").append(BeanUtils.toLowerCaseFirstOne(fileName)).append("Service = ").append(BeanUtils.toLowerCaseFirstOne(fileName)).append("Service;\r\n");
        sb.append("    }\r\n");
        sb.append("\r\n");
        sb.append("    @Override\r\n");
        sb.append("    public ResultVO ").append(BeanUtils.toLowerCaseFirstOne(fileName)).append("FindAll(Integer pageNumber, Integer pageSize, String sortCode) {\r\n");
        sb.append("        return ResultVO.success(").append(BeanUtils.toLowerCaseFirstOne(fileName)).append("Service.findAll(pageNumber - 1, pageSize, sortCode));\r\n");
        sb.append("    }\r\n");
        sb.append("\r\n");
        sb.append("    @Override\r\n");
        sb.append("    public ResultVO ").append(BeanUtils.toLowerCaseFirstOne(fileName)).append("Save").append("(").append(fileName).append("PO po) {\r\n");
        sb.append("        if (po.getId() == null) {\r\n");
        sb.append("            return ResultVO.success(").append(BeanUtils.toLowerCaseFirstOne(fileName)).append("Service.addOne(po));\r\n");
        sb.append("        }\r\n");
        sb.append("        return ResultVO.success(").append(BeanUtils.toLowerCaseFirstOne(fileName)).append("Service.updateOne(po));\r\n");
        sb.append("    }\r\n");
        sb.append("\r\n");
        sb.append("    @Override\r\n");
        sb.append("    public void ").append(BeanUtils.toLowerCaseFirstOne(fileName)).append("Delete(String id) {\r\n");
        sb.append("        ").append(BeanUtils.toLowerCaseFirstOne(fileName)).append("Service.deleteOne(id);\r\n");
        sb.append("    }\r\n");
        sb.append("\r\n");
        sb.append("}\r\n");
        String entityControllerImplData = sb.toString();
        return new String[]{entityControllerImplData, entityControllerImplName(po)};
    }

}