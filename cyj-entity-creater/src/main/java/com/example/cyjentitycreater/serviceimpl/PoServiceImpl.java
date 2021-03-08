package com.example.cyjentitycreater.serviceimpl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.example.cyjentitycreater.api.PageMenuApiService;
import com.example.cyjentitycreater.entity.AppServicePO;
import com.example.cyjentitycreater.entity.EntityNamePO;
import com.example.cyjentitycreater.entity.EntityPO;
import com.example.cyjentitycreater.service.AppServiceService;
import com.example.cyjentitycreater.utils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

    private EntityNameServiceImpl entityNameService;
    private PageMenuApiService pageMenuApiService;
    private EntityServiceImpl entityService;
    private AppServiceService appServiceService;

    @Autowired
    public void setAppServiceService(AppServiceService appServiceService) {
        this.appServiceService = appServiceService;
    }

    @Autowired
    public void setEntityNameService(EntityNameServiceImpl entityNameService) {
        this.entityNameService = entityNameService;
    }

    @Autowired
    public void setPageMenuApiService(PageMenuApiService pageMenuApiService) {
        this.pageMenuApiService = pageMenuApiService;
    }

    @Autowired
    public void setEntityService(EntityServiceImpl entityService) {
        this.entityService = entityService;
    }

    public void createJavaFile(EntityNamePO po, String[] choose) throws IOException {
        String fileName = BeanUtils.underline2Camel(po.getName());
        AppServicePO appServicePO = appServiceService.findOneById(po.getAppName());
        if (choose != null && choose.length != 0) {
            for (String cho : choose) {
                if ("entity".equals(cho)) {
                    String[] result = entityGenerate(po, appServicePO);
                    createJavaFile(appServicePO.getAppPath() + "\\entity", result);
                } else if ("dao".equals(cho)) {
                    String[] daoResult = daoGenerate(po, appServicePO, null);
                    createJavaFile(appServicePO.getAppPath() + "\\dao", daoResult);
                } else if ("service".equals(cho)) {
                    String[] serviceResult = serviceGenerate(po, appServicePO, null);
                    createJavaFile(appServicePO.getAppPath() + "\\service", serviceResult);
                    String[] serviceImplResult = serviceImplGenerate(po, appServicePO, null);
                    createJavaFile(appServicePO.getAppPath() + "\\serviceimpl", serviceImplResult);
                } else if ("controller".equals(cho)) {
                    String[] controllerInteResult = controllerInteGenerate(po, appServicePO, null);
                    createJavaFile(appServicePO.getAppPath() + "\\controller", controllerInteResult);
                    String[] controllerResult = controllerGenerate(po, appServicePO, null);
                    createJavaFile(appServicePO.getAppPath() + "\\controller", controllerResult);
                }
            }
            if (po.getRelEntity() != null && !"".equals(po.getRelEntity())) {
                String str = po.getRelEntity().substring(po.getRelEntity().indexOf("[") + 1, po.getRelEntity().indexOf("]"));
                String[] relEntities = str.split(",");
                for (String relEntity : relEntities) {
                    EntityNamePO subPo = entityNameService.findOneById(relEntity);
                    AppServicePO subAppServicePO = appServiceService.findOneById(subPo.getAppName());
                    String subFileName = BeanUtils.underline2Camel(subPo.getName());
                    for (String cho : choose) {
                        if ("entity".equals(cho)) {
                            String[] subResult = entityGenerate(subPo, subAppServicePO);
                            createJavaFile(subAppServicePO.getAppPath() + "\\entity", subResult);
                        } else if ("dao".equals(cho)) {
                            String[] subDaoResult = daoGenerate(subPo, subAppServicePO, po.getName());
                            createJavaFile(subAppServicePO.getAppPath() + "\\dao", subDaoResult);
                        } else if ("service".equals(cho)) {
                            String[] subServiceResult = serviceGenerate(subPo, subAppServicePO, po.getName());
                            createJavaFile(subAppServicePO.getAppPath() + "\\service", subServiceResult);
                            String[] subServiceImplResult = serviceImplGenerate(subPo, subAppServicePO, po.getName());
                            createJavaFile(subAppServicePO.getAppPath() + "\\serviceimpl", subServiceImplResult);
                        } else if ("controller".equals(cho)) {
                            String[] subControllerInteResult = controllerInteGenerate(subPo, subAppServicePO, po.getName());
                            createJavaFile(subAppServicePO.getAppPath() + "\\controller", subControllerInteResult);
                            String[] subControllerResult = controllerGenerate(subPo, subAppServicePO, po.getName());
                            createJavaFile(subAppServicePO.getAppPath() + "\\controller", subControllerResult);
                        }
                    }
                    createFormAndTable(subPo, subFileName);
                }
            }
        }
        createFormAndTable(po, fileName);
    }

    private void createFormAndTable(EntityNamePO subPo, String subFileName) {
        if (subPo.getFormModelCode() == null || subPo.getFormModelCode().isEmpty()) {
            subPo.setFormModelCode(subFileName + "Form");
            entityNameService.updateOne(subPo);
        }
        if (subPo.getTableModelCode() == null || subPo.getTableModelCode().isEmpty()) {
            subPo.setTableModelCode(subFileName + "Table");
            entityNameService.updateOne(subPo);
        }
        List<EntityPO> poList = entityService.findListById(subPo.getId());
        JSONArray array = JSONArray.parseArray(JSON.toJSONString(poList));
        pageMenuApiService.formAndTableGenerate(subFileName, array);
    }

    public String[] entityGenerate(EntityNamePO po, AppServicePO appServicePO) {
        List<EntityPO> poList = entityService.findListById(po.getId());
        String fileName = BeanUtils.captureName(BeanUtils.underline2Camel(po.getName()));
        StringBuilder sb = new StringBuilder();
        generatePackage1(appServicePO, sb);
        sb.append("import org.hibernate.annotations.GenericGenerator;\r\n");
        sb.append("\r\n");
        sb.append("import javax.persistence.*;\r\n");
        generatePackage2(poList, sb);
        sb.append("@Entity\r\n");
        sb.append("@Table(name = ").append(fileName).append(po.getType()).append(".T_").append(po.getName().toUpperCase()).append(")\r\n");
        sb.append("@Data\r\n");
        sb.append("@GenericGenerator(name = \"uuid2\", strategy = \"org.hibernate.id.UUIDGenerator\")\r\n");
        generateClass(po, sb);
        for (EntityPO entityPO : poList) {
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
        return new String[]{entityData, entityName(po)};
    }

    public String[] daoGenerate(EntityNamePO po, AppServicePO appServicePO, String entityName) {
        StringBuilder sb = new StringBuilder();
        String[] PathArr = appServicePO.getAppPath().split("java");
        String packetPath = PathArr[1].substring(1).replaceAll("\\\\", ".");
        //entity路径
        String poPath = packetPath + ".entity";
        //dao路径
        String poDaoPath = packetPath + ".dao";
        sb.append("package ").append(poDaoPath).append(";\r\n");
        String fileName = BeanUtils.captureName(BeanUtils.underline2Camel(po.getName()));
        sb.append("\r\n");
        sb.append("import ").append(poPath).append(".").append(fileName).append(po.getType()).append(";\r\n");
        sb.append("import org.springframework.data.jpa.repository.JpaRepository;\r\n");
        sb.append("\r\n");
        generateAuthor(sb);
        sb.append("public interface ").append(fileName).append("Dao extends JpaRepository<").append(fileName).append(po.getType()).append(", String> {\r\n");
        if (entityName != null) {
            sb.append("    /**\r\n");
            sb.append("     * 删除实体\r\n");
            sb.append("     *\r\n");
            sb.append("     * @param pid 实体pid\r\n");
            sb.append("     */\r\n");
            sb.append("    void deleteByPid(String pid);\r\n");
        }
        sb.append("}\r\n");
        String entityDaoData = sb.toString();
        return new String[]{entityDaoData, entityDaoName(po)};
    }

    public String[] serviceGenerate(EntityNamePO po, AppServicePO appServicePO, String entityName) {
        StringBuilder sb = new StringBuilder();
        String[] PathArr = appServicePO.getAppPath().split("java");
        String packetPath = PathArr[1].substring(1).replaceAll("\\\\", ".");
        //entity路径
        String poPath = packetPath + ".entity.*";
        //service路径
        String poServicePath = packetPath + ".service";
        sb.append("package ").append(poServicePath).append(";\r\n");
        String fileName = BeanUtils.captureName(BeanUtils.underline2Camel(po.getName()));
        sb.append("\r\n");
        sb.append("import ").append(poPath).append(";\r\n");
        sb.append("import org.springframework.data.domain.Page;");
        sb.append("\r\n");
        if (entityName != null) {
            sb.append("\r\n");
            sb.append("import java.util.List;\r\n");
        }
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
        sb.append("     * 删除所有实体\r\n");
        sb.append("     *\r\n");
        sb.append("     * @param id 实体id\r\n");
        sb.append("     */\r\n");
        sb.append("    void deleteAll(String id);\r\n");
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
        if (entityName != null) {
            sb.append("     * @param id         id\r\n");
        }
        sb.append("     * @param pageNumber 页码\r\n");
        sb.append("     * @param pageSize 条目\r\n");
        sb.append("     * @param sortCode 排序列\r\n");
        sb.append("     * @return 实体列表分页\r\n");
        sb.append("     */\r\n");
        if (entityName != null) {
            sb.append("    Page<").append(fileName).append("PO> findAll(String id, Integer pageNumber, Integer pageSize, String sortCode);\r\n");
        } else {
            sb.append("    Page<").append(fileName).append("PO> findAll(Integer pageNumber, Integer pageSize, String sortCode);\r\n");
        }
        sb.append("\r\n");
        if (entityName != null) {
            sb.append("    /**\r\n");
            sb.append("     * 查找实体列表\r\n");
            sb.append("     *\r\n");
            sb.append("     * @param id 实体id\r\n");
            sb.append("     * @return 实体\r\n");
            sb.append("     */\r\n");
            sb.append("    List<").append(fileName).append("PO> findListById(String id);\r\n");
        }
        sb.append("\r\n");
        sb.append("    /**\r\n");
        sb.append("     * 查找实体\r\n");
        sb.append("     *\r\n");
        sb.append("     * @param id 实体id\r\n");
        sb.append("     * @return 实体\r\n");
        sb.append("     */\r\n");
        sb.append("    ").append(fileName).append("PO findOneById(String id);\r\n");
        sb.append("\r\n");
        sb.append("}\r\n");
        String entityServiceData = sb.toString();
        return new String[]{entityServiceData, entityServiceName(po)};
    }

    public String[] serviceImplGenerate(EntityNamePO po, AppServicePO appServicePO, String entityName) {
        StringBuilder sb = new StringBuilder();
        String[] PathArr = appServicePO.getAppPath().split("java");
        String packetPath = PathArr[1].substring(1).replaceAll("\\\\", ".");
        //entity路径
        String poPath = packetPath + ".entity.*";
        //dao路径
        String poDaoPath = packetPath + ".dao.*";
        //service路径
        String poServicePath = packetPath + ".service.*";
        //serviceImpl路径
        String poServiceImplPath = packetPath + ".serviceimpl";
        sb.append("package ").append(poServiceImplPath).append(";\r\n");
        String fileName = BeanUtils.captureName(BeanUtils.underline2Camel(po.getName()));
        sb.append("\r\n");
        if (entityName != null) {
            sb.append("import com.example.cyjcommon.utils.CommonUtils;\r\n");
        }
        sb.append("import ").append(poPath).append(";\r\n");
        sb.append("import ").append(poDaoPath).append(";\r\n");
        sb.append("import ").append(poServicePath).append(";\r\n");
        sb.append("import org.springframework.beans.factory.annotation.Autowired;\r\n");
        sb.append("import org.springframework.data.domain.*;\r\n");
        sb.append("import org.springframework.stereotype.Service;\r\n");
        sb.append("import org.springframework.transaction.annotation.Transactional;\r\n");
        sb.append("\r\n");
        if (entityName != null) {
            sb.append("\r\n");
            sb.append("import java.util.List;\r\n");
        }
        generateAuthor(sb);
        sb.append("@Service\r\n");
        sb.append("@Transactional(rollbackFor = Exception.class)\r\n");
        sb.append("public class ").append(fileName).append("ServiceImpl extends BaseService implements ").append(fileName).append("Service {\r\n");
        sb.append("\r\n");
        sb.append("    private ").append(fileName).append("Dao ").append(BeanUtils.underline2Camel(po.getName())).append("Dao;\r\n");
        if (entityName == null) {
            if (po.getRelEntity() != null && !"".equals(po.getRelEntity())) {
                String str = po.getRelEntity().substring(po.getRelEntity().indexOf("[") + 1, po.getRelEntity().indexOf("]"));
                String[] relEntities = str.split(",");
                for (String relEntity : relEntities) {
                    EntityNamePO subPo = entityNameService.findOneById(relEntity);
                    String subFileName = BeanUtils.captureName(BeanUtils.underline2Camel(subPo.getName()));
                    sb.append("    private ").append(subFileName).append("Dao ").append(BeanUtils.underline2Camel(subPo.getName())).append("Dao;\r\n");
                }
            }
        }
        generateSet(po, sb, fileName);
        if (entityName == null) {
            if (po.getRelEntity() != null && !"".equals(po.getRelEntity())) {
                String str = po.getRelEntity().substring(po.getRelEntity().indexOf("[") + 1, po.getRelEntity().indexOf("]"));
                String[] relEntities = str.split(",");
                for (String relEntity : relEntities) {
                    EntityNamePO subPo = entityNameService.findOneById(relEntity);
                    String subFileName = BeanUtils.captureName(BeanUtils.underline2Camel(subPo.getName()));
                    generateSet(subPo, sb, subFileName);
                }
            }
        }
        sb.append("\r\n");
        sb.append("    @Override\r\n");
        sb.append("    public ").append(fileName).append("PO addOne(").append(fileName).append("PO po) {\r\n");
        sb.append("        return ").append(BeanUtils.underline2Camel(po.getName())).append("Dao.save(po);\r\n");
        sb.append("    }\r\n");
        sb.append("\r\n");
        sb.append("    @Override\r\n");
        sb.append("    public void deleteOne(String id) {\r\n");
        sb.append("        ").append(BeanUtils.underline2Camel(po.getName())).append("Dao.deleteById(id);\r\n");
        deleteByPid(po, entityName, sb);
        sb.append("    public void deleteAll(String id) {\r\n");
        deleteByPid(po, entityName, sb);
        sb.append("    public ").append(fileName).append("PO updateOne(").append(fileName).append("PO po) {\r\n");
        sb.append("        return ").append(BeanUtils.underline2Camel(po.getName())).append("Dao.saveAndFlush(po);\r\n");
        sb.append("    }\r\n");
        sb.append("\r\n");
        sb.append("    @Override\r\n");
        if (entityName != null) {
            sb.append("    public Page<").append(fileName).append("PO> findAll(String id, Integer pageNumber, Integer pageSize, String sortCode) {\r\n");
            sb.append("        Sort sort = Sort.by(sortCode);\r\n");
            sb.append("        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);\r\n");
            sb.append("        List<").append(fileName).append("PO> poList = findListById(id);\r\n");
            sb.append("        List<").append(fileName).append("PO> poPage = CommonUtils.page(poList, pageSize, pageNumber);\r\n");
            sb.append("        return new PageImpl<>(poPage, pageable, poList.size());\r\n");
        } else {
            sb.append("    public Page<").append(fileName).append("PO> findAll(Integer pageNumber, Integer pageSize, String sortCode) {\r\n");
            sb.append("        Sort sort = Sort.by(sortCode);\r\n");
            sb.append("        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);\r\n");
            sb.append("        return ").append(BeanUtils.underline2Camel(po.getName())).append("Dao.findAll(pageable);\r\n");
        }
        sb.append("    }\r\n");
        sb.append("\r\n");
        if (entityName != null) {
            sb.append("    @Override\r\n");
            sb.append("    public List<").append(fileName).append("PO> findListById(String id) {\r\n");
            entityName = BeanUtils.captureName(BeanUtils.underline2Camel(entityName));
            sb.append("        Q").append(fileName).append("PO q").append(fileName).append("PO = Q").append(fileName).append("PO.").append(BeanUtils.underline2Camel(po.getName())).append("PO;\r\n");
            sb.append("        Q").append(entityName).append("PO q").append(entityName).append("PO = Q").append(entityName).append("PO.").append(BeanUtils.toLowerCaseFirstOne(entityName)).append("PO;\r\n");
            sb.append("        return queryFactory.selectFrom(q").append(fileName).append("PO)\r\n");
            sb.append("                .innerJoin(q").append(entityName).append("PO)\r\n");
            sb.append("                .on(q").append(fileName).append("PO.pid.eq(q").append(entityName).append("PO.id))\r\n");
            sb.append("                .where(q").append(entityName).append("PO.id.eq(id))\r\n");
            sb.append("                .orderBy(q").append(fileName).append("PO.sortCode.asc()).fetch();\r\n");
            sb.append("    }\r\n");
        }
        sb.append("\r\n");
        sb.append("    @Override\r\n");
        sb.append("    public ").append(fileName).append("PO findOneById(String id) {\r\n");
        sb.append("        if (").append(BeanUtils.underline2Camel(po.getName())).append("Dao.findById(id).isPresent()) {\r\n");
        sb.append("            return ").append(BeanUtils.underline2Camel(po.getName())).append("Dao.findById(id).get();\r\n");
        sb.append("        }\r\n");
        sb.append("        return null;\r\n");
        sb.append("    }\r\n");
        sb.append("\r\n");
        sb.append("}\r\n");
        String entityServiceImplData = sb.toString();
        return new String[]{entityServiceImplData, entityServiceImplName(po)};
    }

    private void deleteByPid(EntityNamePO po, String entityName, StringBuilder sb) {
        if (entityName == null) {
            if (po.getRelEntity() != null && !"".equals(po.getRelEntity())) {
                String str = po.getRelEntity().substring(po.getRelEntity().indexOf("[") + 1, po.getRelEntity().indexOf("]"));
                String[] relEntities = str.split(",");
                for (String relEntity : relEntities) {
                    EntityNamePO subPo = entityNameService.findOneById(relEntity);
                    sb.append("        ").append(BeanUtils.underline2Camel(subPo.getName())).append("Dao.deleteByPid(id);\r\n");
                }
            }
        }
        sb.append("    }\r\n");
        sb.append("\r\n");
        sb.append("    @Override\r\n");
    }

    private void generateSet(EntityNamePO po, StringBuilder sb, String fileName) {
        sb.append("\r\n");
        sb.append("    @Autowired\r\n");
        sb.append("    public void set").append(fileName).append("Dao(").append(fileName).append("Dao ").append(BeanUtils.underline2Camel(po.getName())).append("Dao) {\r\n");
        sb.append("        this.").append(BeanUtils.underline2Camel(po.getName())).append("Dao = ").append(BeanUtils.underline2Camel(po.getName())).append("Dao;\r\n");
        sb.append("    }\r\n");
    }

    public String[] controllerInteGenerate(EntityNamePO po, AppServicePO appServicePO, String entityName) {
        StringBuilder sb = new StringBuilder();
        String[] PathArr = appServicePO.getAppPath().split("java");
        String fileName = BeanUtils.captureName(BeanUtils.underline2Camel(po.getName()));
        String packetPath = PathArr[1].substring(1).replaceAll("\\\\", ".");
        //controller路径
        String poControllerPath = packetPath + ".controller";
        sb.append("package ").append(poControllerPath).append(";\r\n");
        sb.append("\r\n");
        sb.append("import com.example.cyjcommon.utils.ResultVO;\r\n");
        sb.append("import org.springframework.web.bind.annotation.*;\r\n");
        sb.append("\r\n");
        sb.append("import java.util.Map;\r\n");
        sb.append("\r\n");
        generateAuthor(sb);
        sb.append("public interface ").append(fileName).append("Controller {\r\n");
        sb.append("\r\n");
        sb.append("    /**\r\n");
        sb.append("     * 查询所有对象\r\n");
        sb.append("     *\r\n");
        if (entityName != null) {
            sb.append("     * @param id         id\r\n");
        }
        sb.append("     * @param pageNumber 页码\r\n");
        sb.append("     * @param pageSize   条目\r\n");
        sb.append("     * @param sortCode   排序列\r\n");
        sb.append("     * @return 返回结果\r\n");
        sb.append("     */\r\n");
        sb.append("    @PostMapping(value = \"").append(BeanUtils.underline2Camel(po.getName())).append("Page\")\r\n");
        if (entityName != null) {
            sb.append("    ResultVO ").append(BeanUtils.underline2Camel(po.getName())).append("FindAll(@RequestParam(\"id\") String id,\r\n");
            sb.append("                         @RequestParam(\"pageNumber\") Integer pageNumber,\r\n");
        } else {
            sb.append("    ResultVO ").append(BeanUtils.underline2Camel(po.getName())).append("FindAll(@RequestParam(\"pageNumber\") Integer pageNumber,\r\n");
        }
        sb.append("                         @RequestParam(\"pageSize\") Integer pageSize,\r\n");
        sb.append("                         @RequestParam(\"sortCode\") String sortCode);\r\n");
        sb.append("\r\n");
        sb.append("    /**\r\n");
        sb.append("     * 保存对象\r\n");
        sb.append("     *\r\n");
        sb.append("     * @param vo 对象\r\n");
        sb.append("     * @return 返回结果\r\n");
        sb.append("     */\r\n");
        sb.append("    @PostMapping(value = \"").append(BeanUtils.underline2Camel(po.getName())).append("Save\")\r\n");
        sb.append("    ResultVO ").append(BeanUtils.underline2Camel(po.getName())).append("Save(@RequestBody Map<String, Object> vo);\r\n");
        sb.append("\r\n");
        sb.append("    /**\r\n");
        sb.append("     * 删除对象\r\n");
        sb.append("     *\r\n");
        sb.append("     * @param id 对象ID\r\n");
        sb.append("     */\r\n");
        sb.append("    @PostMapping(value = \"").append(BeanUtils.underline2Camel(po.getName())).append("Delete\")\r\n");
        sb.append("    void ").append(BeanUtils.underline2Camel(po.getName())).append("Delete(@RequestParam(\"id\") String id);\r\n");
        sb.append("\r\n");
        sb.append("    /**\r\n");
        sb.append("     * 根据ID查询\r\n");
        sb.append("     *\r\n");
        sb.append("     * @param id 对象ID\r\n");
        sb.append("     * @return 返回结果\r\n");
        sb.append("     */\r\n");
        sb.append("    @PostMapping(value = \"find").append(fileName).append("ById\")\r\n");
        sb.append("    ResultVO find").append(fileName).append("ById(@RequestParam(\"id\") String id);\r\n");
        sb.append("\r\n");
        sb.append("}\r\n");
        String entityControllerData = sb.toString();
        return new String[]{entityControllerData, entityControllerName(po)};
    }

    public String[] controllerGenerate(EntityNamePO po, AppServicePO appServicePO, String entityName) {
        StringBuilder sb = new StringBuilder();
        String[] PathArr = appServicePO.getAppPath().split("java");
        String fileName = BeanUtils.captureName(BeanUtils.underline2Camel(po.getName()));
        String packetPath = PathArr[1].substring(1).replaceAll("\\\\", ".");
        //entity路径
        String poPath = packetPath + ".entity.*";
        //serviceImpl路径
        String poServiceImplPath = packetPath + ".serviceimpl.*";
        //controller路径
        String poControllerPath = packetPath + ".controller";
        sb.append("package ").append(poControllerPath).append(";\r\n");
        sb.append("\r\n");
        sb.append("import com.example.cyjcommon.utils.ResultVO;\r\n");
        sb.append("import com.example.cyjcommon.utils.VoPoConverter;\r\n");
        sb.append("import ").append(poPath).append(";\r\n");
        sb.append("import ").append(poServiceImplPath).append(";\r\n");
        sb.append("import org.springframework.web.bind.annotation.*;\r\n");
        sb.append("import org.springframework.beans.factory.annotation.Autowired;\r\n");
        sb.append("\r\n");
        sb.append("import java.util.Map;\r\n");
        sb.append("\r\n");
        generateAuthor(sb);
        sb.append("@RestController\r\n");
        sb.append("@RequestMapping(value = \"").append(appServicePO.getAppApi()).append("\")\r\n");
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
        if (entityName != null) {
            sb.append("    public ResultVO ").append(BeanUtils.toLowerCaseFirstOne(fileName)).append("FindAll(String id, Integer pageNumber, Integer pageSize, String sortCode) {\r\n");
            sb.append("        return ResultVO.success(").append(BeanUtils.toLowerCaseFirstOne(fileName)).append("Service.findAll(id, pageNumber, pageSize, sortCode));\r\n");
        } else {
            sb.append("    public ResultVO ").append(BeanUtils.toLowerCaseFirstOne(fileName)).append("FindAll(Integer pageNumber, Integer pageSize, String sortCode) {\r\n");
            sb.append("        return ResultVO.success(").append(BeanUtils.toLowerCaseFirstOne(fileName)).append("Service.findAll(pageNumber - 1, pageSize, sortCode));\r\n");
        }
        sb.append("    }\r\n");
        sb.append("\r\n");
        sb.append("    @Override\r\n");
        sb.append("    public ResultVO ").append(BeanUtils.toLowerCaseFirstOne(fileName)).append("Save").append("(Map<String, Object> vo) {\r\n");
        sb.append("        ").append(fileName).append("PO po = new ").append(fileName).append("PO();\r\n");
        sb.append("        VoPoConverter.copyProperties(vo, po);\r\n");
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
        sb.append("    @Override\r\n");
        sb.append("    public ResultVO find").append(fileName).append("ById(String id) {\r\n");
        sb.append("        return ResultVO.success(").append(BeanUtils.toLowerCaseFirstOne(fileName)).append("Service.findOneById(id));\r\n");
        sb.append("    }\r\n");
        sb.append("\r\n");
        sb.append("}\r\n");
        String entityControllerImplData = sb.toString();
        return new String[]{entityControllerImplData, entityControllerImplName(po)};
    }

}