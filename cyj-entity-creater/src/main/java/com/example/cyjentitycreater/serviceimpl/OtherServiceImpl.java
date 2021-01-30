package com.example.cyjentitycreater.serviceimpl;

import com.example.cyjentitycreater.entity.EntityNamePO;
import com.example.cyjentitycreater.entity.EntityPO;
import com.example.cyjentitycreater.utils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

import static com.example.cyjentitycreater.utils.BeanUtils.entityName;


/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020/1/21 14:46
 */
@Component
public class OtherServiceImpl extends BaseService {

    private EntityNameServiceImpl entityNameService;

    private EntityServiceImpl entityService;

    @Autowired
    public void setEntityNameService(EntityNameServiceImpl entityNameService) {
        this.entityNameService = entityNameService;
    }

    @Autowired
    public void setEntityService(EntityServiceImpl entityService) {
        this.entityService = entityService;
    }

    public String[] entityGenerate(EntityNamePO po) {
        List<EntityPO> poList = entityService.findListById(po.getId());
        StringBuilder sb = new StringBuilder();
        generatePackage1(po, sb);
        sb.append("\r\n");
        generatePackage2(poList, sb);
        sb.append("@Data\r\n");
        generateClass(po, sb);
        for (EntityPO entityPO : poList) {
            sb.append("    private ").append(entityPO.getEntityProperty()).append(" ")
                    .append(BeanUtils.underline2Camel(entityPO.getEntityName())).append(";\r\n");
            sb.append("\r\n");
        }
        sb.append("}");
        String entityData = sb.toString();
        return new String[]{entityData, entityName(po)};
    }


    public void createJavaFile(EntityNamePO po) throws IOException {
        String[] result = entityGenerate(po);
        createJavaFile(po.getPath(), result);
    }
}
