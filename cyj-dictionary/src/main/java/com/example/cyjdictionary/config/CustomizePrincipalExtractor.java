package com.example.cyjdictionary.config;

import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;

import java.util.Map;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020/1/21 14:46
 */
public class CustomizePrincipalExtractor implements PrincipalExtractor {

    /**
     * Extract the principal that should be used for the token.
     *
     * @param map the source map
     * @return the extracted principal or {@code null}
     */
    @Override
    public Object extractPrincipal(Map<String, Object> map) {
        // 这直接返回map本身，该map包含的认证中心对的principal的所有字段（key为字段名，value为字段值形式）
        // 这里也可以new一个user对象，将map对应字段值映射到user对象中返回user对象
        return map;
    }
}
