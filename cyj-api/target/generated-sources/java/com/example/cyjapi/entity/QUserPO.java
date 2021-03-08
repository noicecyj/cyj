package com.example.cyjapi.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserPO is a Querydsl query type for UserPO
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUserPO extends EntityPathBase<UserPO> {

    private static final long serialVersionUID = -2090492450L;

    public static final QUserPO userPO = new QUserPO("userPO");

    public final StringPath id = createString("id");

    public final StringPath name = createString("name");

    public final StringPath password = createString("password");

    public final StringPath phone = createString("phone");

    public final StringPath sortCode = createString("sortCode");

    public final NumberPath<Integer> status = createNumber("status", Integer.class);

    public final StringPath userName = createString("userName");

    public QUserPO(String variable) {
        super(UserPO.class, forVariable(variable));
    }

    public QUserPO(Path<? extends UserPO> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserPO(PathMetadata metadata) {
        super(UserPO.class, metadata);
    }

}

