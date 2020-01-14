package com.example.cyjentitycreater.service;

import com.example.cyjentitycreater.entity.Entity;

import java.util.List;

public interface beanService {
    void generateGetterAndSetter(List<Entity> entityList, StringBuffer sb);
    void generateToString(List<Entity> entityList, StringBuffer sb, String tableName);
    void generateEquals(List<Entity> entityList, StringBuffer sb, String tableName);
    void generateHashCode(List<Entity> entityList, StringBuffer sb);
}
