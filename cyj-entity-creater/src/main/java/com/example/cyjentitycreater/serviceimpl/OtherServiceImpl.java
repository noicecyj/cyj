package com.example.cyjentitycreater.serviceimpl;

import com.example.cyjentitycreater.entity.CreateVO;
import com.example.cyjentitycreater.entity.EntityPO;
import com.example.cyjentitycreater.utils.BeanUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static com.example.cyjentitycreater.utils.BeanUtils.entityName;


/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020/1/21 14:46
 */
@Component
public class OtherServiceImpl extends BaseService {

    public String[] entityGenerate(CreateVO createVO, StringBuffer sb) {
        generatePackage1(createVO, sb);
        sb.append("\r\n");
        generatePackage2(createVO, sb);
        sb.append("@Data\r\n");
        generateClass(createVO, sb);
        for (EntityPO entityPO : createVO.getPoList()) {
            sb.append("    private ").append(entityPO.getEntityProperty()).append(" ")
                    .append(BeanUtils.underline2Camel(entityPO.getEntityName())).append(";\r\n");
            sb.append("\r\n");
        }
        sb.append("}");
        String entityData = sb.toString();
        return new String[]{entityData, entityName(createVO)};
    }


    public String[] createJavaFile(CreateVO createVO) throws IOException {
        StringBuffer sb = new StringBuffer();
        String[] result = entityGenerate(createVO, sb);
        createJavaFile(createVO.getPath(), result);
        return result;
    }
}
