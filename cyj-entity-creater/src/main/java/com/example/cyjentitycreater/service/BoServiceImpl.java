package com.example.cyjentitycreater.service;

import com.example.cyjentitycreater.entity.CreateVO;
import com.example.cyjentitycreater.entity.Entity;
import com.example.cyjentitycreater.entity.ResultVO;

import java.util.List;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020/1/21 14:46
 */
public class BoServiceImpl implements BeanService {

    public ResultVO entityGenerate(CreateVO createVO) {

        return null;
    }
    @Override
    public void generateAnnotation(CreateVO createVO, StringBuffer sb, String yes) {

    }

    @Override
    public void generateProperty(CreateVO createVO, StringBuffer sb) {

    }

    @Override
    public void generateConstructor(CreateVO createVO, StringBuffer sb) {

    }

    @Override
    public void generateGetterAndSetter(List<Entity> entityList, StringBuffer sb) {

    }

    @Override
    public void generateToString(List<Entity> entityList, StringBuffer sb, String tableName) {

    }

    @Override
    public void generateEquals(List<Entity> entityList, StringBuffer sb, String tableName) {

    }

    @Override
    public void generateHashCode(List<Entity> entityList, StringBuffer sb) {

    }
}
