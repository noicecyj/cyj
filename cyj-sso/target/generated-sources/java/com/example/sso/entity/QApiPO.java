package com.example.sso.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QApiPO is a Querydsl query type for ApiPO
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QApiPO extends EntityPathBase<ApiPO> {

    private static final long serialVersionUID = -1673430786L;

    public static final QApiPO apiPO = new QApiPO("apiPO");

    public final StringPath id = createString("id");

    public QApiPO(String variable) {
        super(ApiPO.class, forVariable(variable));
    }

    public QApiPO(Path<? extends ApiPO> path) {
        super(path.getType(), path.getMetadata());
    }

    public QApiPO(PathMetadata metadata) {
        super(ApiPO.class, metadata);
    }

}

