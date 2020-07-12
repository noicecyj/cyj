package com.example.cyjentitycreater.service;

import com.example.cyjentitycreater.entity.CreateVO;
import com.example.cyjentitycreater.entity.Entity;
import com.example.cyjentitycreater.entity.ResultVO;
import com.example.cyjentitycreater.utils.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.example.cyjentitycreater.utils.BeanUtils.entityName;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020/1/21 14:46
 */
@Component
public class VoServiceImpl extends BaseService {

    public ResultVO entityGenerate(CreateVO createVO) {

        return null;
    }

    @Override
    public void generateAnnotation(CreateVO createVO, StringBuffer sb, String yes) {

    }

    @Override
    public void generateProperty(CreateVO createVO, StringBuffer sb) {

    }
}
