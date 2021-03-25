package com.example.CyjUser.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRolePO is a Querydsl query type for RolePO
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QRolePO extends EntityPathBase<RolePO> {

    private static final long serialVersionUID = 2069962016L;

    public static final QRolePO rolePO = new QRolePO("rolePO");

    public final SetPath<AuthorityPO, QAuthorityPO> authorities = this.<AuthorityPO, QAuthorityPO>createSet("authorities", AuthorityPO.class, QAuthorityPO.class, PathInits.DIRECT2);

    public final StringPath description = createString("description");

    public final StringPath id = createString("id");

    public final StringPath name = createString("name");

    public final StringPath sortCode = createString("sortCode");

    public final NumberPath<Integer> status = createNumber("status", Integer.class);

    public final StringPath value = createString("value");

    public QRolePO(String variable) {
        super(RolePO.class, forVariable(variable));
    }

    public QRolePO(Path<? extends RolePO> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRolePO(PathMetadata metadata) {
        super(RolePO.class, metadata);
    }

}

