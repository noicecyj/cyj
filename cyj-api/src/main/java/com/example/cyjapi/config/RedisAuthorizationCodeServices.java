package com.example.cyjapi.config;

import lombok.Setter;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.code.RandomValueAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.store.redis.JdkSerializationStrategy;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStoreSerializationStrategy;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020/1/21 14:46
 */
@Setter
public class RedisAuthorizationCodeServices extends RandomValueAuthorizationCodeServices {


    private static final String AUTHORIZATION_CODE = "authorization:code:";

    /**
     * 授权码有效时长
     */
    private long expiration = 300L;
    /**
     * key 前缀
     */
    private String prefix = "";


    private final RedisConnectionFactory connectionFactory;
    private final RedisTokenStoreSerializationStrategy serializationStrategy = new JdkSerializationStrategy();


    public RedisAuthorizationCodeServices(RedisConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;

    }

    private RedisConnection getConnection() {
        return connectionFactory.getConnection();
    }

    /**
     * value序列化
     * @param object object
     * @return serialize
     */
    private byte[] serialize(Object object) {
        return serializationStrategy.serialize(object);
    }

    /**
     * key序列化
     * @param string string
     * @return serialize
     */
    private byte[] serialize(String string) {
        return serializationStrategy.serialize(string);
    }

    /**
     * key序列化
     * @param object object
     * @return serialize
     */
    private byte[] serializeKey(Object object) {
        return serialize(prefix + object);
    }


    /**
     * 反序列化
     * @param bytes bytes
     * @return deserialize
     */
    private OAuth2Authentication deserializeAuthentication(byte[] bytes) {
        return serializationStrategy.deserialize(bytes, OAuth2Authentication.class);
    }





    /**
     * 将随机生成的授权码存到redis中
     *
     * @param code code
     * @param authentication authentication
     */
    @Override
    protected void store(String code, OAuth2Authentication authentication) {
        byte[] serializedKey = serializeKey(AUTHORIZATION_CODE + code);
        byte[] serializedAuthentication = serialize(authentication);
        try (RedisConnection conn = getConnection()) {
            conn.openPipeline();
            conn.set(serializedKey, serializedAuthentication);
            conn.expire(serializedKey, expiration);
            conn.closePipeline();
        }

    }

    /**
     * 取出授权码并删除授权码(权限码只能用一次，调试时可不删除，code就可多次使用)
     *
     * @param code code
     * @return org.springframework.security.oauth2.provider.OAuth2Authentication
     */
    @Override
    protected OAuth2Authentication remove(String code) {
        byte[] serializedKey = serializeKey(AUTHORIZATION_CODE + code);
        RedisConnection conn = getConnection();
        byte[] bytes;
        try {
            bytes = conn.get(serializedKey);
            if (bytes != null) {
                conn.del(serializedKey);
            }
        } finally {
            conn.close();
        }
        return deserializeAuthentication(bytes);
    }


}
