package com.example.cyjentitycreater.service;

import com.example.cyjentitycreater.entity.CreateVO;
import com.example.cyjentitycreater.entity.Entity;

import java.util.List;
/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020/1/21 14:46
 */
public interface BeanService {
    /**
     * 生成重写构造方法
     *
     * @param createVO 属性列表
     * @param sb         实体类生成字符串
     */
    void generateConstructor(CreateVO createVO, StringBuffer sb);
    /**
     * 生成重写getter和setter的方法
     *
     * @param entityList 属性列表
     * @param sb         实体类生成字符串
     */
    void generateGetterAndSetter(List<Entity> entityList, StringBuffer sb);
    /**
     *
     * 生成重写getter和setter的方法
     * @param entityList 属性列表
     * @param sb         实体类生成字符串
     * @param tableName  表名
     */
    void generateToString(List<Entity> entityList, StringBuffer sb, String tableName);
    /**
     *
     * 生成重写Equals的方法
     * @param entityList 属性列表
     * @param sb         实体类生成字符串
     * @param tableName  表名
     */
    void generateEquals(List<Entity> entityList, StringBuffer sb, String tableName);
    /**
     * 生成重写HashCode的方法
     *
     * @param entityList 属性列表
     * @param sb         实体类生成字符串
     */
    void generateHashCode(List<Entity> entityList, StringBuffer sb);
}
