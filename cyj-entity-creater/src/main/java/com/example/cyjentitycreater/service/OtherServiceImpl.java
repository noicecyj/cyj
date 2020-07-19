package com.example.cyjentitycreater.service;

import com.example.cyjentitycreater.entity.CreateVO;
import com.example.cyjentitycreater.utils.BeanUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;


/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020/1/21 14:46
 */
@Component
public class OtherServiceImpl extends BaseService {

    public String[] entityGenerate(CreateVO createVO) {
        StringBuffer sb = new StringBuffer();
        String yes = "Y";
        generateAnnotation(createVO, sb, yes);
        sb.append("public class ")
                .append(BeanUtils.captureName(BeanUtils.underline2Camel(createVO.getName())))
                .append(createVO.getType())
                .append(" implements Serializable {\r\n");
        sb.append("\r\n");
        return generateMethod(createVO, sb);
    }

    public String[] createJavaFile(CreateVO createVO) throws IOException {
        String[] result = entityGenerate(createVO);
        createJavaFile(createVO, result);
        return result;
    }

    @Override
    public void generateAnnotation(CreateVO createVO, StringBuffer sb, String yes) {
        generatePackage(createVO, sb, yes);
        generateAuthor(sb);
        if (yes.equals(createVO.getLombok())) {
            sb.append("@Data\r\n");
        }
    }

    @Override
    public void generateProperty(CreateVO createVO, StringBuffer sb) {
        createVO.getEntityData().forEach(entity -> {
            sb.append("    private ")
                    .append(entity.getEntityProperty()).append(" ")
                    .append(BeanUtils.underline2Camel(entity.getEntityName()))
                    .append(";\r\n");
            sb.append("\r\n");
        });
    }
}
